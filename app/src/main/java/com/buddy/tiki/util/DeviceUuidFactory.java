package com.buddy.tiki.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.util.UUID;

public class DeviceUuidFactory {
    private static UUID f2359a;

    public static UUID getDeviceUuid(Context context) {
        if (f2359a == null) {
            synchronized (DeviceUuidFactory.class) {
                if (f2359a == null) {
                    SharedPreferences prefs = context.getSharedPreferences("device_id.xml", 0);
                    String id = prefs.getString("device_id", null);
                    if (id != null) {
                        f2359a = UUID.fromString(id);
                    } else {
                        String androidId = Secure.getString(context.getContentResolver(), "android_id");
                        try {
                            if ("9774d56d682e549c".equals(androidId)) {
                                UUID nameUUIDFromBytes;
                                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                                if (deviceId != null) {
                                    nameUUIDFromBytes = UUID.nameUUIDFromBytes(deviceId.getBytes("utf8"));
                                } else {
                                    nameUUIDFromBytes = UUID.randomUUID();
                                }
                                f2359a = nameUUIDFromBytes;
                            } else {
                                f2359a = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                            }
                        } catch (Exception e) {
                        }
                        prefs.edit().putString("device_id", f2359a.toString()).apply();
                    }
                }
            }
        }
        return f2359a;
    }
}
