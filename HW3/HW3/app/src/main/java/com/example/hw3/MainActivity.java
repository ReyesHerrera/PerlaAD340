package com.example.hw3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

//    Context context;
//    RecyclerView recyclerView;
//    RelativeLayout relativeLayout;
//    RecyclerView.Adapter recyclerViewAdapter;
//    RecyclerView.LayoutManager recylerViewLayoutManager;
//    String[] subjects =
//            {
//                    "Movie 1",
//                    "Movie 2",
//                    "Movie 3",
//                    "Movie 4",
//                    "Movie 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickTopLeft(View button){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    public void onClick(View button){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}