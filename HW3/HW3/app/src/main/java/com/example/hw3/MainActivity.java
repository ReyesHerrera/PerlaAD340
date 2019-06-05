package com.example.hw3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickTopLeft(View button){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    public void onClick(View view){
        Button btn = findViewById(R.id.empty_btn_again_again);
        Toast toast = Toast.makeText(this, "Empty", Toast.LENGTH_SHORT);
        toast.show();
    }
}