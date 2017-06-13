package com.buddy.tiki.util.phonetype;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.bytedeco.javacpp.swscale;

public class RomUtils {
    public static double getEmuiVersion() {
        try {
            String emuiVersion = getSystemProperty("ro.build.version.emui");
            return Double.parseDouble(emuiVersion.substring(emuiVersion.indexOf("_") + 1));
        } catch (Exception e) {
            e.printStackTrace();
            return 4.0d;
        }
    }

    public static int getMiuiVersion() {
        String version = getSystemProperty("ro.miui.ui.version.name");
        if (version != null) {
            try {
                return Integer.parseInt(version.substring(1));
            } catch (Exception e) {
                Log.e("RomUtils", "get miui version code error, version : " + version);
            }
        }
        return -1;
    }

    public static String getSystemProperty(String propName) {
        String line;
        IOException ex;
        Throwable th;
        BufferedReader input = null;
        try {
            BufferedReader input2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + propName).getInputStream()), swscale.SWS_SPLINE);
            try {
                line = input2.readLine();
                input2.close();
                if (input2 != null) {
                    try {
                        input2.close();
                    } catch (IOException e) {
                        Log.e("RomUtils", "Exception while closing InputStream", e);
                    }
                }
                input = input2;
            } catch (IOException e2) {
                ex = e2;
                input = input2;
                try {
                    Log.e("RomUtils", "Unable to read sysprop " + propName, ex);
                    line = null;
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e3) {
                            Log.e("RomUtils", "Exception while closing InputStream", e3);
                        }
                    }
                    return line;
                } catch (Throwable th2) {
                    th = th2;
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e32) {
                            Log.e("RomUtils", "Exception while closing InputStream", e32);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                input = input2;
                if (input != null) {
                    input.close();
                }
                throw th;
            }
        } catch (IOException e4) {
            ex = e4;
            Log.e("RomUtils", "Unable to read sysprop " + propName, ex);
            line = null;
            if (input != null) {
                input.close();
            }
            return line;
        }
        return line;
    }

    public static boolean checkIsHuaweiRom() {
        return Build.MANUFACTURER.contains("HUAWEI");
    }

    public static boolean checkIsMiuiRom() {
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
    }

    public static boolean checkIsMeizuRom() {
        String meizuFlymeOSFlag = getSystemProperty("ro.build.display.id");
        if (TextUtils.isEmpty(meizuFlymeOSFlag)) {
            return false;
        }
        if (meizuFlymeOSFlag.contains("flyme") || meizuFlymeOSFlag.toLowerCase().contains("flyme")) {
            return true;
        }
        return false;
    }

    public static boolean checkIs360Rom() {
        return Build.MANUFACTURER.contains("QiKU");
    }
}
