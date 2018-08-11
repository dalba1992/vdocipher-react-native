package com.vdocipher.rnbridge;

import android.util.Log;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.vdocipher.aegis.player.VdoPlayer.VdoInitParams;

import java.util.Map;

import javax.annotation.Nullable;

public class VdoPlayerViewManager extends SimpleViewManager<ReactVdoPlayerView> {
    private static final String TAG = "VdoPlayerViewManager";

    private static final String REACT_CLASS = "RCTVdoPlayerView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public ReactVdoPlayerView createViewInstance(ThemedReactContext context) {
        ReactVdoPlayerView playerView = new ReactVdoPlayerView(context);
        Log.d(TAG, "created " + playerView.toString());
        return playerView;
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
        builder.put("onInitSuccess", MapBuilder.of("registrationName", "onInitSuccess"));
        builder.put("onInitFailure", MapBuilder.of("registrationName", "onInitFailure"));
        return builder.build();
    }

    @Override
    public void onDropViewInstance(ReactVdoPlayerView view) {
        Log.d(TAG, "dropped " + view.toString());
    }

    @ReactProp(name = "embedInfo")
    public void setEmbedInfo(ReactVdoPlayerView vdoPlayerView, @Nullable ReadableMap embedInfo) {
        if (embedInfo != null) {
            vdoPlayerView.load(
                    new VdoInitParams.Builder()
                    .setOtp(embedInfo.getString("otp"))
                    .setPlaybackInfo(embedInfo.getString("playbackInfo"))
                    //.setPreferredCaptionsLanguage(embedInfo.getString("lang??"))
                    .build()
            );
        }
    }
}
