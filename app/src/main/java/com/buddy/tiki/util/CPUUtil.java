package com.buddy.tiki.util;

import android.support.v7.recyclerview.BuildConfig;
import com.buddy.tiki.ChatApp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class CPUUtil {
    private static CpuMonitor f2323a;

    public static CpuMonitor monitor() {
        if (f2323a == null) {
            synchronized (CPUUtil.class) {
                if (f2323a == null) {
                    f2323a = new CpuMonitor(ChatApp.getInstance());
                }
            }
        }
        return f2323a;
    }

    public static void release() {
        if (f2323a != null) {
            synchronized (CPUUtil.class) {
                if (f2323a != null) {
                    f2323a.release();
                    f2323a = null;
                }
            }
        }
    }

    public static String getMaxCpuFreq() {
        String result = BuildConfig.VERSION_NAME;
        try {
            InputStream in = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"}).start().getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = "N/A";
        }
        return result.trim();
    }

    public static String getMinCpuFreq() {
        String result = BuildConfig.VERSION_NAME;
        try {
            InputStream in = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"}).start().getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = "N/A";
        }
        return result.trim();
    }

    public static String getCurCpuFreq() {
        String result = "N/A";
        try {
            result = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq")).readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getCpuName() {
        try {
            return new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2)[1];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
