package com.example.LocateMe;

import com.google.android.gms.maps.model.LatLng;

public class CustomMarker {
    private String title;
    private String description;
    private String icon;
    private Double lat;
    private Double lng;
    private LatLng latLng;

    public CustomMarker(String title, String icon, Double lat, Double lng) {
        this.title = title;
        this.icon = icon;
        this.lat = lat;
        this.lng = lng;
    }

    public CustomMarker(String title, String description, String icon, Double lat, Double lng) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.lat = lat;
        this.lng = lng;
    }

    public CustomMarker(String title, String icon, LatLng latLng) {
        this.title = title;
        this.icon = icon;
        this.latLng = latLng;
    }

    public CustomMarker(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CustomMarker(LatLng latLng) {
        this.latLng = latLng;
    }
}
