package com.ioio.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ioio.tester.db.Constants;
import com.ioio.tester.test.TestActivity;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        nameEditText = findViewById(R.id.nameEditText);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                if (name.equals("")) {
                    nameEditText.setError(getString(R.string.please_write_your_name));
                    return;
                }
                startTesting(name);
            }
        });
    }

    private void startTesting(String name) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra(Constants.USERNAME_KEY, name);
        startActivity(intent);
    }
}