package com.example.bgsdatastarter_s1520400;

// Name                 Matt Hedderly
// Student ID           S1520400
// Programme of Study   Computing



public class Earthquake {
    private String location;
    private String date;
    private String latitude;
    private String longitude;
    private String magnitude;
    private String depth;

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

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
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
        return  " location=" + location + '\n' +
                " date=" + date + '\n' +
                " latitude=" + latitude + '\n' +
                " longitude=" + longitude + '\n' +
                " magnitude=" + magnitude + '\n' +
                " depth=" + depth + '\n';
    }
}
