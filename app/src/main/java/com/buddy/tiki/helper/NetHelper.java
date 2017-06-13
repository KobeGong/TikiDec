package com.buddy.tiki.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;

public class NetHelper {
    public static boolean hadNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        if (cm == null) {
            return false;
        }
        NetworkInfo[] info = cm.getAllNetworkInfo();
        if (info == null) {
            return false;
        }
        for (NetworkInfo anInfo : info) {
            if (anInfo != null && anInfo.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCurNetwordAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        if (cm == null) {
            return false;
        }
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return false;
        }
        return true;
    }

    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context.getSystemService("connectivity");
        TelephonyManager mgrTel = (TelephonyManager) context.getSystemService("phone");
        return (mgrConn.getActiveNetworkInfo() != null && mgrConn.getActiveNetworkInfo().getState() == State.CONNECTED) || (mgrTel != null && mgrTel.getNetworkType() == 3);
    }

    public static boolean is3GNet(Context context) {
        NetworkInfo networkINfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return networkINfo != null && networkINfo.getType() == 0;
    }

    public static boolean isWifi(Context context) {
        NetworkInfo networkINfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkINfo == null || networkINfo.getType() != 1) {
            return false;
        }
        return true;
    }
}
