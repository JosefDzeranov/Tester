package com.ioio.tester.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ioio.tester.Adapter;
import com.ioio.tester.App;
import com.ioio.tester.CongratsActivity;
import com.ioio.tester.R;
import com.ioio.tester.TestListener;
import com.ioio.tester.db.Constants;
import com.ioio.tester.db.Database;
import com.ioio.tester.db.model.Question;

import java.util.List;


public class TestActivity extends AppCompatActivity implements TestListener {

    AlertDialog dialog;
    TestViewModel viewModel;
    Adapter adapter;
    ViewPager2 pager;
    FloatingActionButton prevButton;
    FloatingActionButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        String name = getIntent().getExtras().getString(Constants.USERNAME_KEY);
        if (name == null) {
            Toast.makeText(this, "Неверное имя", Toast.LENGTH_LONG).show();
            finish();
        }
        createDialog();
        pager = findViewById(R.id.pager);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pager.getCurrentItem() == 4) pager.setCurrentItem(0);
                else
                    pager.setCurrentItem(pager.getCurrentItem() + 1);
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pager.getCurrentItem() == 0) pager.setCurrentItem(4);
                else
                    pager.setCurrentItem(pager.getCurrentItem() - 1);
            }
        });


        viewModel = new ViewModelProvider(this).get(TestViewModel.class);
        viewModel.getQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                initAdapter(questions);
            }
        });
        viewModel.setName(name);
        Database db = ((App) getApplication()).getDatabase();
        viewModel.setDatabase(db);
        viewModel.loadQuestions();

    }

    private void createDialog() {
        dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.are_you_sure)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                        showCongrats();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                }).create();
    }

    private void showCongrats() {
        Intent intent = new Intent(this, CongratsActivity.class);
        intent.putExtra(Constants.USERNAME_KEY, viewModel.getName());
        intent.putExtra(Constants.RIGHT_ANSWERS_KEY, viewModel.getResults());
        startActivity(intent);
        finish();
    }

    @Override
    public void onChange(int questionNumber, boolean isRight) {
        viewModel.setAnswer(questionNumber, isRight);
    }

    private void initAdapter(List<Question> questions) {
        adapter = new Adapter(questions, this);
        pager.setOffscreenPageLimit(5);
        pager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dialog.show();
        return true;
    }
}