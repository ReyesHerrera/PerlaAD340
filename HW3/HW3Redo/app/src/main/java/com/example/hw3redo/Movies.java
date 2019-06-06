package com.example.hw3redo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

//moved intent here
public class Movies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        TextView movie = findViewById(R.id.movie_title);
        TextView year = findViewById(R.id.movie_year);
        TextView director = findViewById(R.id.movie_director);
        TextView review = findViewById(R.id.movie_review);

        Intent intent = getIntent();
        movie.setText(intent.getStringExtra("movie"));
        year.setText(intent.getStringExtra("year"));
        director.setText(intent.getStringExtra("director"));
        review.setText(intent.getStringExtra("description"));

    }

}
