package com.buddy.tiki.util;

import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import com.buddy.tiki.helper.LocationHelper;
import com.google.gson.GsonBuilder;
import java.util.Locale;

public class UserAgentTool {
    private ArrayMap<String, String> f2476a = new ArrayMap();

    public UserAgentTool() {
        this.f2476a.put("db", Build.BRAND);
        this.f2476a.put("dm", Build.MODEL);
        this.f2476a.put("osv", VERSION.RELEASE);
        this.f2476a.put("lang", Locale.getDefault().getLanguage());
        this.f2476a.put("av", "1.2.11");
        Location location = LocationHelper.INSTANCE.getLocation();
        double latitude = 0.0d;
        double longitude = 0.0d;
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        this.f2476a.put("locs", latitude + "-" + longitude);
        this.f2476a.put("area", Locale.getDefault().getCountry());
    }

    public String toHeaderValue() {
        return "#top" + new GsonBuilder().create().toJson(this.f2476a) + "top#";
    }
}
