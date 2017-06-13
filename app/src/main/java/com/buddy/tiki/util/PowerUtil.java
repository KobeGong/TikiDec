package com.buddy.tiki.util;

import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.buddy.tiki.ChatApp;

public class PowerUtil {
    public static void screenOn() {
        try {
            WakeLock wakeLock = ((PowerManager) ChatApp.getInstance().getSystemService("power")).newWakeLock(268435462, "WakeTikiLock");
            wakeLock.acquire();
            wakeLock.release();
        } catch (Throwable th) {
        }
    }

    public static boolean isScreenOn() {
        PowerManager powerManager = (PowerManager) ChatApp.getInstance().getSystemService("power");
        if (VERSION.SDK_INT <= 19) {
            return powerManager.isScreenOn();
        }
        return powerManager.isInteractive();
    }
}
