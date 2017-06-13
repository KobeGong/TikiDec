package com.buddy.tiki.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.wertc.Camera2Enumerator;
import com.buddy.tiki.wertc.PeerConnectionClient.PeerConnectionParameters;

public class CameraHelper {
    public static PeerConnectionParameters loadParamsFromPref(@NonNull Context context) {
        return loadParamsFromPref(context, false);
    }

    public static PeerConnectionParameters loadParamsFromPref(@NonNull Context context, boolean loopback) {
        PreferenceManager.setDefaultValues(context, C0376R.xml.preferences, false);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        boolean useCamera2 = Camera2Enumerator.isSupported();
        String videoCodec = "VP8";
        String audioCodec = sp.getString(context.getString(C0376R.string.pref_audiocodec_key), context.getString(C0376R.string.pref_audiocodec_default));
        boolean hwCodec = sp.getBoolean(context.getString(C0376R.string.pref_hwcodec_key), Boolean.valueOf(context.getString(C0376R.string.pref_hwcodec_default)).booleanValue());
        boolean captureToTexture = sp.getBoolean(context.getString(C0376R.string.pref_capturetotexture_key), Boolean.valueOf(context.getString(C0376R.string.pref_capturetotexture_default)).booleanValue());
        boolean noAudioProcessing = sp.getBoolean(context.getString(C0376R.string.pref_noaudioprocessing_key), Boolean.valueOf(context.getString(C0376R.string.pref_noaudioprocessing_default)).booleanValue());
        boolean aecDump = sp.getBoolean(context.getString(C0376R.string.pref_aecdump_key), Boolean.valueOf(context.getString(C0376R.string.pref_aecdump_default)).booleanValue());
        boolean useOpenSLES = sp.getBoolean(context.getString(C0376R.string.pref_opensles_key), Boolean.valueOf(context.getString(C0376R.string.pref_opensles_default)).booleanValue());
        int videoWidth = 0;
        int videoHeight = 0;
        String[] dimensions = sp.getString(context.getString(C0376R.string.pref_resolution_key), context.getString(C0376R.string.pref_resolution_default)).split("[ x]+");
        if (dimensions.length == 2) {
            try {
                videoWidth = Integer.parseInt(dimensions[0]);
                videoHeight = Integer.parseInt(dimensions[1]);
            } catch (NumberFormatException e) {
                videoWidth = 0;
                videoHeight = 0;
            }
        }
        int cameraFps = 0;
        String[] fpsValues = sp.getString(context.getString(C0376R.string.pref_fps_key), context.getString(C0376R.string.pref_fps_default)).split("[ x]+");
        if (fpsValues.length == 2) {
            try {
                cameraFps = Integer.parseInt(fpsValues[0]);
            } catch (NumberFormatException e2) {
            }
        }
        boolean captureQualitySlider = sp.getBoolean(context.getString(C0376R.string.pref_capturequalityslider_key), Boolean.valueOf(context.getString(C0376R.string.pref_capturequalityslider_default)).booleanValue());
        int videoStartBitrate = 0;
        String bitrateTypeDefault = context.getString(C0376R.string.pref_startvideobitrate_default);
        if (!sp.getString(context.getString(C0376R.string.pref_startaudiobitrate_key), bitrateTypeDefault).equals(bitrateTypeDefault)) {
            videoStartBitrate = Integer.parseInt(sp.getString(context.getString(C0376R.string.pref_startaudiobitratevalue_key), context.getString(C0376R.string.pref_startvideobitratevalue_default)));
        }
        int audioStartBitrate = 0;
        bitrateTypeDefault = context.getString(C0376R.string.pref_startaudiobitrate_default);
        if (!sp.getString(context.getString(C0376R.string.pref_startaudiobitrate_key), bitrateTypeDefault).equals(bitrateTypeDefault)) {
            audioStartBitrate = Integer.parseInt(sp.getString(context.getString(C0376R.string.pref_startaudiobitratevalue_key), context.getString(C0376R.string.pref_startaudiobitratevalue_default)));
        }
        boolean displayHud = sp.getBoolean(context.getString(C0376R.string.pref_displayhud_key), Boolean.valueOf(context.getString(C0376R.string.pref_displayhud_default)).booleanValue());
        boolean tracing = sp.getBoolean(context.getString(C0376R.string.pref_tracing_key), Boolean.valueOf(context.getString(C0376R.string.pref_tracing_default)).booleanValue());
        return new PeerConnectionParameters(true, loopback, false, useCamera2, videoWidth, videoHeight, cameraFps, videoStartBitrate, videoCodec, true, captureToTexture, audioStartBitrate, audioCodec, noAudioProcessing, false, useOpenSLES, false, false, false, true);
    }
}
