package com.example.hwsix;

//convert your data from JSON to instances of a custom Java class



public class TraficCamera {

    //changes for map
    private double latitude;
    private double longitude;
    private String id;
    private String url;
    private String location;
    private String type;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TraficCamera (double latitude, double longitude,
                         String id, String description, String url, String type) {
        this.id = id;
        this.type = type;
        this.location = description;
        this.description = description;
        setUrl(url);
        //map
        this.latitude=latitude;
        this.longitude=longitude;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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