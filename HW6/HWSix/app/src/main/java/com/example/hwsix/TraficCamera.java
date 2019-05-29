package com.example.hwsix;

//convert your data from JSON to instances of a custom Java class

public class TraficCamera {

    private String id;
    private String url;
    private String location;
    private String type;

    public TraficCamera (String id, String description, String url, String type) {
        this.id = id;
        this.type = type;
        this.location = description;
        setUrl(url);
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

    public String getUrl() {
        return this.url;
    }

    public String getLocation() {
        return this.location;
    }

}