package com.example.hwsix;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class NetworkConnection {
    final static String TAG = "NETWORK_CONNECTION";

    public static String getData(String url){
        try{
            return getData((new URL(url)));
        } catch(MalformedURLException ex){

        }
        return null;
    }
    public static String getData(String url, String...uriParams){
        Uri.Builder builder = Uri.parse(url).buildUpon();
        return getData(builder.build().toString());
        /*if ((uriParams.length % 2) !=0){
            Log.e(TAG, "odd number of params provided ");
            return null;
        }
        for(int i = 0; i<uriParams.length; i+=2){
            builder.appendQueryParameter(uriParams[i],uriParams[i +1]);
        }
        return getData(builder.build().toString());*/
    }
    public static String getData(URL url){
//create outside try catch
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String results = null;

        try{
            //open new connection
            urlConnection =(HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();        //port to port connection, external resource

            //get the inputStream-- its is an open connection
            InputStream inputStream = urlConnection.getInputStream();

            //Create a buffered reader from input steam.
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

}
