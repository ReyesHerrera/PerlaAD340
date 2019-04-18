package com.example.inclassdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.net.Uri;


public class MainActivity extends AppCompatActivity {

    final private static String TAG = "Perla Says...";
    private static final int RESULT_ID = 1;

    public static final String MESSAGE_ID = "my.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, this.getLifecycle()
                .getCurrentState()
                .toString());
    }
    protected void onClick(View button){
        Log.i(TAG, "Button " + button.getId() + " clicked.");

        EditText textbox = (EditText)findViewById(R.id.message_box);
        String message = textbox.getText().toString();

        if (message.contains("https:")) {
            Intent browseIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(message));
            startActivity(browseIntent);
        } else {


            Intent intent = new Intent(this, SecondActivity.class);

            intent.putExtra(MESSAGE_ID, message);

            startActivityForResult(intent, RESULT_ID);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESULT_ID) {
            if (resultCode == RESULT_OK) {
                TextView label = (TextView)findViewById(R.id.message);
                String message = label.getText().toString();
                message += "\n\n" + data.getStringExtra(SecondActivity.RESULT);
                label.setText(message);
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG,  "Main Activity..starting");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG,  "Main Activity..resuming");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());

    }
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG,  "Main Activity..pausing");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG,  "Main Activity..stopping");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG,  "Main Activity..its destroyed");

    }
}
