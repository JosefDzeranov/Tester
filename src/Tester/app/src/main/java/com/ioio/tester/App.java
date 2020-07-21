package com.ioio.tester;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ioio.tester.db.Constants;
import com.ioio.tester.db.Converter;
import com.ioio.tester.db.Database;
import com.ioio.tester.db.model.Question;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class App extends Application {
    private Database database;

    public Database getDatabase() {
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, Database.class, "database")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                    }
                }).build();
        initDB();

    }
    private void initDB(){
        database.questionsDao().getAll()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<Question>, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(List<Question> questions) throws Exception {
                        Question[] q = Constants.generateQuestions();
                        return database.questionsDao().insert(q);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Object o) {
                        int x = 0;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}
