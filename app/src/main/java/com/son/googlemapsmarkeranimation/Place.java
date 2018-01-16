package com.son.googlemapsmarkeranimation;

import com.google.android.gms.maps.model.LatLng;

public class Place {

    private String name;

    private double lat;

    private double lng;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(final double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(final double lng) {
        this.lng = lng;
    }

    public LatLng getLatLng() {
        return new LatLng(lat, lng);
    }
}
