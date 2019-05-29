package com.example.hwsix;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
//runs in the background

public class GitHubAsynctask extends AsyncTask<String, Void, String> {

    //prevents leaks
    private WeakReference <TextView> mTextView;


    public GitHubAsynctask(TextView textView){
        //WeakReference allows for garage collection, is a best practice
        this.mTextView = new WeakReference<>(textView);

    }
    //...array of strings
    @Override

    protected String doInBackground(String... strings) {
        //create outside try catch
        HttpsURLConnection urlConnection = null;
        BufferedReader reader = null;
        String results = "";

        try{
            URL requestURL = new URL( strings[0]);
            //open new connection
            urlConnection =(HttpsURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();        //port to port connection, external resource

            //get the inputStream-- its is an open connection
            InputStream inputStream = urlConnection.getInputStream();

            //Create a buffered reader from that input steam.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Use a StringBuilder to hold the incoming response
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);

            }
            results = builder.toString();


        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null){
                try{
                    reader.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
    }
}
