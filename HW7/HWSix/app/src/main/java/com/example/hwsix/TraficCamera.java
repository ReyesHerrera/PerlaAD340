package com.example.hwsix;

//convert your data from JSON to instances of a custom Java class

import android.widget.TextView;

import static com.example.hwsix.MainActivity.context;

public class TraficCamera {

    //changes for map
    private double latitude;
    private double longtitude;
    private String id;
    private String url;
    private String location;
    private String type;
    private String description;

    public TraficCamera (double latitude, double longtitude,
                         String id, String description, String url, String type) {
        this.id = id;
        this.type = type;
        this.location = description;
        this.description = description;
        setUrl(url);
        //map
        this.latitude=latitude;
        this.longtitude=longtitude;
    }

    private void setUrl(String url) {

        String SeattleGovBaseUrl = "http://www.seattle.gov/trafficcams/images/";
        String WashingtonStateBaseUrl = "http://images.wsdot.wa.gov/nw/";

        if (type.equals("sdot")) {
            this.url = SeattleGovBaseUrl + url;
        } else if (type.equals("wsdot")) {
            this.url = WashingtonStateBaseUrl + url;
        }

    }

    //public void setLocation(String location) {
    //    this.location = location;
    //}

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUrl() {
        return this.url;
    }

    public String getLocation() {
        return this.location;
    }


    public String getDescription() {
        return this.description;
    }
}