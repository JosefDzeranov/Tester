package com.ioio.tester;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.ioio.tester.db.Constants;

public class CongratsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        String name = getIntent().getExtras().getString(Constants.USERNAME_KEY);
        int answers = getIntent().getExtras().getInt(Constants.RIGHT_ANSWERS_KEY);
        ((TextView)findViewById(R.id.answersText)).setText(answers+"/5");
        ((TextView)findViewById(R.id.congrats_name)).setText(name+", ваш результат: "+answers+"/5");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}