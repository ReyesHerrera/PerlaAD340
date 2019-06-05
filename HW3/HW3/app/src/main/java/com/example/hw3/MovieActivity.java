package com.example.hw3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MovieActivity extends AppCompatActivity {

    private static final String TAG = "MovieActivity";

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        Intent intent = getIntent();
        String[] movieInfo = intent.getStringArrayExtra(SecondActivity.RES);

        setContentView(R.layout.recyclerviewitems);

        TextView title = (TextView)findViewById(R.id.movie_title);
        TextView year = (TextView)findViewById(R.id.movie_year);
        //TextView director = (TextView)findViewById(R.id.director);
        TextView description = (TextView)findViewById(R.id.movie_review);
        WebView image = (WebView)findViewById(R.id.image);

        title.setText(movieInfo[0]);
        year.setText(movieInfo[1]);
        //director.setText(movieInfo[2]);
        image.loadUrl(movieInfo[3]);
        description.setText(movieInfo[4]);



        image.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });

        image.getSettings().setLoadWithOverviewMode(true);
        image.getSettings().setUseWideViewPort(true);
    }
}