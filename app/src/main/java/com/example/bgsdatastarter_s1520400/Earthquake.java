package com.example.bgsdatastarter_s1520400;

import java.util.Date;

public class Earthquake {
    private String location;
    private String date;
    private String latitude;
    private String longitude;

    public Earthquake(){
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public Earthquake(String location, String date, String latitude, String longitude){
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public String getLocation(){
        return location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
