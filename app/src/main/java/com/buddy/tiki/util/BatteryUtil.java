package com.buddy.tiki.util;

import android.content.Intent;
import android.content.IntentFilter;
import com.buddy.tiki.ChatApp;

public class BatteryUtil {
    public static int getBatteryLevel() {
        Intent intent = ChatApp.getInstance().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intent == null) {
            return -1;
        }
        return intent.getIntExtra("level", 100);
    }

    public static int getBatteryScale() {
        Intent intent = ChatApp.getInstance().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intent == null) {
            return 100;
        }
        return intent.getIntExtra("scale", 100);
    }

    public static int getBatteryPercentLevel() {
        try {
            Intent intent = ChatApp.getInstance().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (intent != null) {
                int batteryScale = intent.getIntExtra("scale", 100);
                if (batteryScale > 0) {
                    int batteryLevel = (int) ((100.0f * ((float) intent.getIntExtra("level", 0))) / ((float) batteryScale));
                }
            }
        } catch (IllegalArgumentException e) {
        } catch (Throwable th) {
        }
        return 0;
    }

    public static float getBatteryPercent() {
        try {
            Intent intent = ChatApp.getInstance().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (intent != null) {
                int batteryScale = intent.getIntExtra("scale", 100);
                if (batteryScale > 0) {
                    float batteryPercent = ((float) intent.getIntExtra("level", 0)) / ((float) batteryScale);
                }
            }
        } catch (IllegalArgumentException e) {
        } catch (Throwable th) {
        }
        return 0.0f;
    }
}
