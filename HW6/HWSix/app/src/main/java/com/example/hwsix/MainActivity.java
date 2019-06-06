package com.example.hwsix;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
//for map
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    //private TextView results;
    private final static String TAG = "Main Activity";
    private RecyclerViewAdapter adapter;
    static Context context;
    private TraficCamera[] traficCamera;


    //for google
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    protected static String CAM_INFO = "Query: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_adapter);
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        //Per Google Maps Platform
        // Construct a GeoDataClient,Construct a PlaceDetectionClient.
        // Construct a FusedLocationProviderClient.
        //mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.map);
        //mapFragment.getMapAsync((OnMapReadyCallback) this);

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

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }
        return new  LocAsyncTaskLoader(this, queryString);

    }

    /*@Override
    public void onClick(View v) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Bundle bundle = new Bundle();
            getSupportLoaderManager().restartLoader(0, bundle, this);
        } else {
            Toast.makeText(this,
                    getResources().getString(R.string.no_connection),
                    Toast.LENGTH_SHORT).show();
        }

        Bundle bundle = new Bundle();
        bundle.putString("queryString", "Fauntleroy_SW_Cloverdale_NS.jpg");
        getSupportLoaderManager().restartLoader(0, bundle, this);

    }*/
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
                JSONArray coordinates = currentImage.getJSONArray("PointCoordinate");
                double latitude = coordinates.getDouble(0);
                double longitude = coordinates.getDouble(1);

                JSONArray cams = currentImage.getJSONArray("Cameras");
                JSONObject firstCamera = cams.getJSONObject(0);

                id = firstCamera.getString("Id");
                description = firstCamera.getString("Description");
                url = firstCamera.getString("ImageUrl");
                type = firstCamera.getString("Type");

                TraficCamera traficCamera = new TraficCamera(latitude, longitude,id, description, url, type);
                cameras[i] = traficCamera;
            }

            RecyclerView recyclerView = findViewById(R.id.recycler_view_adapter);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager a_manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(a_manager);
            adapter = new RecyclerViewAdapter(cameras);
            recyclerView.setAdapter(adapter);

            adapter.setListener(new RecyclerViewAdapter.Listener(){
                @Override
                public void onClick(int position) {
                    Log.i(TAG, "Clicked " + traficCamera[position]);
                    Intent intent = new Intent(getApplicationContext(),
                            MapLocation.class);
                    String[] cameraPackage = new String[4];
                    cameraPackage[0] = traficCamera[position].getLocation();
                    cameraPackage[1] = Double.toString(traficCamera[position].getLatitude());
                    cameraPackage[2] = Double.toString(traficCamera[position].getLongitude());
                    cameraPackage[3] = traficCamera[position].getUrl();
                    intent.putExtra(CAM_INFO, cameraPackage);
                    startActivity(intent);
                }
            });

        }catch(Exception e){
            Log.e(TAG, e.getLocalizedMessage());    //in case something goes wrong
        }
        //results.setText(s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

}