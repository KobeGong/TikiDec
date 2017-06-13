package com.buddy.tiki.helper;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import com.buddy.tiki.ChatApp;

public enum LocationHelper {
    INSTANCE;
    
    private volatile Location mLastLocation;
    private LocationManager mLocationManager;

    class C03871 implements LocationListener {
        final /* synthetic */ LocationHelper f690a;

        C03871(LocationHelper this$0) {
            this.f690a = this$0;
        }

        public void onLocationChanged(Location location) {
            this.f690a.mLastLocation = location;
            if (ContextCompat.checkSelfPermission(ChatApp.getInstance(), "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(ChatApp.getInstance(), "android.permission.ACCESS_COARSE_LOCATION") == 0 && this.f690a.mLocationManager != null) {
                this.f690a.mLocationManager.removeUpdates(this);
                this.f690a.mLocationManager = null;
            }
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    }

    public void requestLocation(@NonNull Context context) {
        if (VERSION.SDK_INT < 23 || (VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
            if (this.mLocationManager == null) {
                this.mLocationManager = (LocationManager) ChatApp.getInstance().getSystemService("location");
            }
            this.mLastLocation = this.mLocationManager.getLastKnownLocation("passive");
            this.mLocationManager.requestSingleUpdate("passive", new C03871(this), null);
        }
    }

    @Nullable
    public Location getLocation() {
        return this.mLastLocation;
    }
}
