package com.buddy.tiki.view.video;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.recyclerview.BuildConfig;
import com.buddy.tiki.C0376R;

public class Settings {
    private Context f3425a;
    private SharedPreferences f3426b = PreferenceManager.getDefaultSharedPreferences(this.f3425a);

    public Settings(Context context) {
        this.f3425a = context.getApplicationContext();
    }

    public boolean getEnableBackgroundPlay() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_enable_background_play), false);
    }

    public int getPlayer() {
        try {
            return Integer.valueOf(this.f3426b.getString(this.f3425a.getString(C0376R.string.pref_key_player), BuildConfig.VERSION_NAME)).intValue();
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean getUsingMediaCodec() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_using_media_codec), false);
    }

    public boolean getUsingMediaCodecAutoRotate() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_using_media_codec_auto_rotate), false);
    }

    public boolean getMediaCodecHandleResolutionChange() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_media_codec_handle_resolution_change), false);
    }

    public boolean getUsingOpenSLES() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_using_opensl_es), false);
    }

    public String getPixelFormat() {
        return this.f3426b.getString(this.f3425a.getString(C0376R.string.pref_key_pixel_format), BuildConfig.VERSION_NAME);
    }

    public boolean getEnableNoView() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_enable_no_view), false);
    }

    public boolean getEnableSurfaceView() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_enable_surface_view), false);
    }

    public boolean getEnableTextureView() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_enable_texture_view), false);
    }

    public boolean getEnableDetachedSurfaceTextureView() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_enable_detached_surface_texture), false);
    }

    public boolean getUsingMediaDataSource() {
        return this.f3426b.getBoolean(this.f3425a.getString(C0376R.string.pref_key_using_mediadatasource), false);
    }

    public String getLastDirectory() {
        return this.f3426b.getString(this.f3425a.getString(C0376R.string.pref_key_last_directory), "/");
    }

    public void setLastDirectory(String path) {
        this.f3426b.edit().putString(this.f3425a.getString(C0376R.string.pref_key_last_directory), path).apply();
    }
}
