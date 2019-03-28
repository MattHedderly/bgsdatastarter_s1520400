package com.example.bgsdatastarter_s1520400;


public class Earthquake {
    private String location;
    private String date;
    private String latitude;
    private String longitude;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return  "location=" + location + '\n' +
                ", date=" + date + '\n' +
                ", latitude=" + latitude + '\n' +
                ", longitude=" + longitude + '\n';
    }
}
