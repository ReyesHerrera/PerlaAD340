package com.example.hwsix;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    //private TextView results;
    private final static String TAG = "Main Activity";
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_adapter);
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info !=null && info.isConnected()){

            //results.setText(getResources().getString(R.string.waiting));

            Bundle bundle = new Bundle();
            getSupportLoaderManager().restartLoader(0, bundle, this);
        }else {
            //could use
            Toast toast = Toast.makeText(this, "Not Connected!", Toast.LENGTH_LONG);
            toast.show();
            //results.setText(getResources().getString((R.string.no_connection)));
        }
        /*Button button = findViewById(R.id.btn_get_data);
        button.setOnClickListener(this);


        results = findViewById(R.id.lbl_results);
*/

    }

    /*@Override
    public void onClick(View view) {

    }*/

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }
        return new  LocAsyncTaskLoader(this, queryString);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

        String id ="";
        String description="";
        String url ="";
        String type="";
        try{
            //capturing JSON Object
            JSONObject rootObject= new JSONObject(s);
            JSONArray features = rootObject.getJSONArray("Features");
            //List of the Objects
            TraficCamera[] cameras = new TraficCamera[features.length()];

            for ( int i = 0; i<features.length(); i++) {
                JSONObject currentImage = features.getJSONObject(i);

                JSONArray cams = currentImage.getJSONArray("Cameras");
                JSONObject firstCamera = cams.getJSONObject(0);

                id = firstCamera.getString("Id");
                description = firstCamera.getString("Description");
                url = firstCamera.getString("ImageUrl");
                type = firstCamera.getString("Type");

                TraficCamera traficCamera = new TraficCamera(id, description, url, type);
                cameras[i] = traficCamera;
            }

            RecyclerView recyclerView = findViewById(R.id.recycler_view_adapter);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager a_manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(a_manager);
            adapter = new RecyclerViewAdapter(cameras);
            recyclerView.setAdapter(adapter);
        }catch(Exception e){
            Log.e(TAG, e.getLocalizedMessage());    //in case something goes wrong
        }
        //results.setText(s);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
