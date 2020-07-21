package com.ioio.tester.test;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ioio.tester.db.Database;
import com.ioio.tester.db.model.Question;
import com.ioio.tester.db.model.Result;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestViewModel extends ViewModel {
    private String name = "";
    private Database database;
    private MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<>();
    private CompositeDisposable d = new CompositeDisposable();
    private boolean[] rightAnswers = new boolean[5];

    public LiveData<List<Question>> getQuestions() {
        return questionsLiveData;
    }

    public void setAnswer(int number, boolean isRightAnswer) {
        rightAnswers[number] = isRightAnswer;
    }

    public void loadQuestions() {
        Disposable disposable = database
                .questionsDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(new Consumer<List<Question>>() {
                    @Override
                    public void accept(List<Question> questions) {
                        questionsLiveData.postValue(questions);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                })
                .subscribe();
        d.add(disposable);
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        d.dispose();
    }

    public int getResults() {
        int right = 0;
        for (int i = 0; i < 5; i++) {
            if(rightAnswers[i]) right++;
        }
        return right;
    }
}