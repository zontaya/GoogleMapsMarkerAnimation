package com.son.googlemapsmarkeranimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.son.googlemapsmarkeranimation.common.map.PulseOverlayLayout;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private PulseOverlayLayout mapOverlayLayout;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapOverlayLayout = (PulseOverlayLayout) findViewById(R.id.mapOverlayLayout);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapFragment.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapFragment.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapOverlayLayout.setupMap(googleMap);

        Place p =new Place();
        p.setName("test Name");
        p.setLat(13.852);
        p.setLng(100.692);


        final List<Place> list = new ArrayList<>();
        list.add(p);

        mapOverlayLayout.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                for (int i = 0; i < list.size(); i++) {
                    mapOverlayLayout.createAndShowMarker(i, list.get(i).getLatLng());
                }
                mapOverlayLayout.setOnCameraIdleListener(null);

                mapOverlayLayout.animateCamera(provideLatLngBoundsForAllPlaces(list));
            }
        });

        mapOverlayLayout.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                mapOverlayLayout.refresh();
            }
        });
    }

    public LatLngBounds provideLatLngBoundsForAllPlaces(List<Place> list) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(Place place : list) {
            builder.include(new LatLng(place.getLat(), place.getLng()));
        }
        return builder.build();
    }
}

