package com.son.googlemapsmarkeranimation.common.map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

public class MapOverlayLayout<V extends MarkerView> extends FrameLayout {

    protected List<V> markersList;
    protected GoogleMap googleMap;

    public MapOverlayLayout(final Context context) {
        this(context, null);
    }

    public MapOverlayLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        markersList = new ArrayList<>();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void addMarker(final V view) {
        markersList.add(view);
        addView(view);
    }

    protected void removeMarker(final V view) {
        markersList.remove(view);
        removeView(view);
    }

    public void showAllMarkers() {
        for (int i = 0; i < markersList.size(); i++) {
            markersList.get(i).show();
        }
    }

    public void hideAllMarkers() {
        for (int i = 0; i < markersList.size(); i++) {
            markersList.get(i).hide();
        }
    }

    public void showMarker(final int position) {
        markersList.get(position).show();
    }

    private void refresh(final int position, final Point point) {
        markersList.get(position).refresh(point);
    }

    public void setupMap(final GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public void refresh() {
        Projection projection = googleMap.getProjection();
        for (int i = 0; i < markersList.size(); i++) {
            refresh(i, projection.toScreenLocation(markersList.get(i).latLng()));
        }
    }

    public void setOnCameraIdleListener(final GoogleMap.OnCameraIdleListener listener) {
        googleMap.setOnCameraIdleListener(listener);
    }

    public void setOnCameraMoveListener(final GoogleMap.OnCameraMoveListener listener) {
        googleMap.setOnCameraMoveListener(listener);
    }

    public void moveCamera(final LatLngBounds latLngBounds) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 150));
    }

    public void animateCamera(final LatLngBounds bounds) {
        int width = getWidth();
        int height = getHeight();
        int padding = 30;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));
    }

    public LatLng getCurrentLatLng() {
        return new LatLng(googleMap.getCameraPosition().target.latitude, googleMap.getCameraPosition().target.longitude);
    }

}
