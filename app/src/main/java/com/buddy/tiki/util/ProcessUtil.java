package com.buddy.tiki.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;

public class ProcessUtil {
    public static boolean isMainProcess(Context context) {
        int pid = Process.myPid();
        ActivityManager activityMag = (ActivityManager) context.getSystemService("activity");
        String processName = context.getApplicationInfo().processName;
        for (RunningAppProcessInfo appProcess : activityMag.getRunningAppProcesses()) {
            if (appProcess.pid == pid && appProcess.processName.equalsIgnoreCase(processName)) {
                return true;
            }
        }
        return false;
    }
}
