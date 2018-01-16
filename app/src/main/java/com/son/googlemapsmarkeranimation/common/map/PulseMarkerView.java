package com.son.googlemapsmarkeranimation.common.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.google.android.gms.maps.model.LatLng;
import com.son.googlemapsmarkeranimation.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class PulseMarkerView extends MarkerView {

    private final Context context;
    private AnimatorSet showAnimatorSet;

    public PulseMarkerView(final Context context, final LatLng latLng, final Point point) {
        super(context, latLng, point);
        this.context = context;

        JSONObject obj = null;
        try {
            obj = new JSONObject(readJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setAnimation(obj);
        setSpeed(1);

        loop(true);

        setVisibility(View.VISIBLE);
        invalidate();

    }

    private String readJSONFromAsset() {
        String json;
        try {
            InputStream is = context.getAssets().open("pin_jump.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }





    @Override
    public void setLayoutParams(final ViewGroup.LayoutParams params) {
        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        frameParams.width = (int) GuiUtils.dpToPx(context, 44);
        frameParams.height = (int) GuiUtils.dpToPx(context, 44);
        frameParams.leftMargin = point.x - frameParams.width / 2;
        frameParams.topMargin = point.y - frameParams.height / 2;
        super.setLayoutParams(frameParams);
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void hide() {

    }

    @Override
    public void refresh(final Point point) {
        this.point = point;
        updatePulseViewLayoutParams(point);
    }

    @Override
    public void show() {
       showAnimatorSet.start();

    }

    public void updatePulseViewLayoutParams(final Point point) {
        this.point = point;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.width = (int) GuiUtils.dpToPx(context, 100);
        params.height = (int) GuiUtils.dpToPx(context, 100);
        params.leftMargin = point.x - params.width / 2;
        params.topMargin = point.y - params.height / 2;
        super.setLayoutParams(params);
        invalidate();
    }
}
