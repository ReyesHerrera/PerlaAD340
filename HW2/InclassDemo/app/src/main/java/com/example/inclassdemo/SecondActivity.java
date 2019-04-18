package com.example.inclassdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.net.Uri;

public class SecondActivity extends AppCompatActivity{

    final private static String TAG = "SecondActivity Says...";
    public static final String RESULT = "my.response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = "";

        if (intent.getAction() == Intent.ACTION_VIEW) {
            Uri data = intent.getData();
            message = data.toString();
        } else {

            intent.getStringExtra(MainActivity.MESSAGE_ID);
        }
        setContentView(R.layout.secondactivity);

        TextView label = (TextView)findViewById(R.id.intent_message);
        label.setText(message);

    }
    protected void onClick(View view) {
        EditText textBox = (EditText)findViewById(R.id.response);
        String message = textBox.getText().toString();

        Intent responseIntent = new Intent();
        responseIntent.putExtra(SecondActivity.RESULT, message);

        setResult(AppCompatActivity.RESULT_OK, responseIntent);
        finish();
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG,  "Second Activity..starting");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG,  "Second Activity..resuming");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG,  "Second Activity..pausing");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG,  "Second Activity..stopping");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG,  "Second Activity..its destroyed");

    }
}
