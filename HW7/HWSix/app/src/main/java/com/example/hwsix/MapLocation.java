package com.example.hwsix;
import android.Manifest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;
import static android.widget.Toast.makeText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;


public class MapLocation extends FragmentActivity implements OnMapReadyCallback {
    private final static String TAG = "Map Location : ";

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted = false;
    private static final String ERROR_MSG = "PLAY SERVICES NOT AVAILABLE";

    public MapLocation(){
        //Required

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment);

        GoogleApiAvailability availability = GoogleApiAvailability.getInstance();
        int result = availability.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (!availability.isUserResolvableError(result)) {
                makeText(this, ERROR_MSG, Toast.LENGTH_SHORT).show();
            }
        }

        // Location of client
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //order matters?
        getLocationPermission();
        getLocation();


        Intent intent = getIntent();
        if (intent != null) {
            String[] cameraMeta = intent.getStringArrayExtra(MainActivity.CAM_INFO);
            Double latitude = Double.parseDouble(cameraMeta[2]);
            Double longitude = Double.parseDouble(cameraMeta[1]);
            LatLng coordinates = new LatLng(latitude, longitude);

            //Marker newMarker =
                    mMap.addMarker(
                    new MarkerOptions().position(coordinates).title(cameraMeta[0]));
        }


    }

    //Per Google Maps Platform and demo from class
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    1);
        }
    }
    @SuppressLint("MissingPermission")
    private void getLocation() {
        if (mLocationPermissionGranted){
            Task location = mFusedLocationProviderClient.getLastLocation();

            location.addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location actualLocation = task.getResult();
                    if (actualLocation !=null){
                        String latlong = String.format("Lat: %f, Long:%f",
                                actualLocation.getLatitude(),
                                actualLocation.getLongitude());
                        mMap.setMyLocationEnabled(true);
                        //mMap.getUiSettings().setMyLocationButtonEnable(true);

                        //update the map
                        LatLng here = new LatLng((actualLocation.getLatitude()),
                                actualLocation.getLongitude());
                        mMap.addMarker(new MarkerOptions().positon(here).title("Current Location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 10));

                        mMap.animateCamera((CameraUpdateFactory.zoomTo(10)));
                    }else{
                        Log.e(TAG, "Location is null");
                    }
                }
            });
            location.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG,e.getLocalizedMessage());

                }
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
        @NonNull String permissions[],
        @NonNull int[] grantResults) {

        mLocationPermissionGranted = false;

        switch (requestCode) {                // requestCode is our ID for request

            case 1 : {
                if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        mLocationPermissionGranted = true;
                        getLocation();
                }
            }
        }
    }

}
