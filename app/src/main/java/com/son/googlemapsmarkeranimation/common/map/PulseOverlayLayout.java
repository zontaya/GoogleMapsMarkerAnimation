package com.son.googlemapsmarkeranimation.common.map;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.maps.model.LatLng;
import com.son.googlemapsmarkeranimation.R;

public class PulseOverlayLayout extends MapOverlayLayout {
    private static final int ANIMATION_DELAY_FACTOR = 100;
    private int scaleAnimationDelay = 100;
    public PulseOverlayLayout(final Context context) {
        this(context, null);
    }

    public PulseOverlayLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.pulse_wrapper_layout, this);
    }

    public void createAndShowMarker(final int position, final LatLng latLng) {
        PulseMarkerView marker = createPulseMarkerView(position, googleMap.getProjection().toScreenLocation(latLng), latLng);
        //marker.showWithDelay(scaleAnimationDelay);
        marker.playAnimation();
        scaleAnimationDelay += ANIMATION_DELAY_FACTOR;
    }

    @NonNull
    private PulseMarkerView createPulseMarkerView(final int position, final Point point, final LatLng latLng) {
        PulseMarkerView pulseMarkerView = new PulseMarkerView(getContext(), latLng, point);
        addMarker(pulseMarkerView);
        return pulseMarkerView;
    }

}
