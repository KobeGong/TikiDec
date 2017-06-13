package com.buddy.tiki.util;

import android.opengl.GLES20;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.buddy.tiki.event.ResourceEvent.UseFaceUnityEvent;
import com.buddy.tiki.log.TikiLog;
import com.faceunity.wrapper.faceunity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FUUtil {
    private static final String[] f2374a = new String[]{"nature", "delta", "electric", "slowlived", "tokyo", "warm"};
    private static final String f2375b = f2374a[0];
    private static final TikiLog f2376c = TikiLog.getInstance(FUUtil.class.getSimpleName());
    private static FUUtil f2377d = new FUUtil();
    private boolean f2378e = false;
    private ArrayMap<String, Integer> f2379f = new ArrayMap();
    private String f2380g = BuildConfig.VERSION_NAME;
    private Random f2381h = new Random();
    private int f2382i = 0;
    private boolean f2383j = true;
    private float f2384k = 1.0f;
    private float f2385l = 5.0f;
    private float f2386m = 0.5f;
    private float f2387n = 1.0f;
    private byte[] f2388o = new byte[]{(byte) -23, (byte) -3, (byte) -23, (byte) -61, (byte) -42, (byte) -86, (byte) 68, (byte) 23, (byte) 16, Byte.MIN_VALUE, (byte) -93, (byte) -123, (byte) -124, (byte) -42, (byte) -62, (byte) 106, (byte) 26, (byte) -75, (byte) 71, (byte) 8, (byte) -75, (byte) 22, (byte) -126, (byte) 97, (byte) 12, (byte) 97, (byte) -76, (byte) -99, (byte) 78, (byte) -80, (byte) 23, (byte) -40, (byte) -45, (byte) 34, (byte) -78, (byte) -86, (byte) -85, (byte) -9, (byte) 105, (byte) -37, (byte) -12, (byte) 81, (byte) 104, (byte) 111, (byte) 47, (byte) -111, (byte) -123, (byte) 122, (byte) 113, (byte) -109, (byte) 55, (byte) 64, (byte) 11, (byte) -77, (byte) -65, (byte) -7, (byte) -63, (byte) -80, (byte) -73, (byte) -19, (byte) 73, (byte) 103, (byte) 27, (byte) -70, (byte) -38, (byte) 85, (byte) -38, (byte) 88, (byte) 19, (byte) -70, (byte) 123, (byte) -80, (byte) 115, (byte) 111, (byte) 16, (byte) 113, (byte) 2, (byte) 74, (byte) -65, (byte) 93, (byte) -90, (byte) -14, (byte) 7, (byte) 46, (byte) -115, (byte) 19, (byte) -60, (byte) -113, (byte) -35, (byte) -62, (byte) 95, (byte) 45, (byte) -35, (byte) 52, (byte) -31, (byte) -64, (byte) 91, (byte) -112, (byte) 43, (byte) -90, (byte) 22, (byte) -36, (byte) 45, (byte) 107, (byte) -28, (byte) -43, (byte) 12, (byte) 81, (byte) 16, (byte) 39, (byte) 120, (byte) 76, (byte) 111, (byte) -30, (byte) -37, (byte) 72, (byte) 70, (byte) 69, (byte) -73, (byte) 97, (byte) 119, (byte) 69, (byte) -43, (byte) -4, (byte) 92, (byte) -90, (byte) 24, (byte) -72, (byte) 78, (byte) 4, (byte) -9, (byte) -70, (byte) -93, (byte) -101, (byte) -74, (byte) 66, (byte) -20, (byte) -102, (byte) -32, (byte) 88, (byte) -62, (byte) 43, (byte) 98, (byte) -98, (byte) 3, (byte) 9, (byte) 41, (byte) 99, (byte) -37, (byte) -15, (byte) -6, (byte) 100, (byte) 122, (byte) -48, (byte) 62, (byte) -80, (byte) 47, (byte) 9, (byte) -121, (byte) -80, (byte) 35, (byte) 11, (byte) 24, (byte) -61, (byte) 92, (byte) -44, (byte) -22, (byte) 42, (byte) -44, (byte) -69, (byte) 113, (byte) -43, (byte) 9, (byte) -58, (byte) 23, (byte) 112, (byte) -78, (byte) 7, (byte) 114, (byte) -72, (byte) 45, (byte) -51, (byte) -89, (byte) 38, (byte) 33, (byte) -100, (byte) 90, (byte) -32, (byte) -97, (byte) -61, (byte) 50, (byte) -20, (byte) -109, (byte) -17, (byte) 118, (byte) -61, (byte) -67, (byte) -26, (byte) -123, (byte) -33, (byte) -88, (byte) -82, (byte) 28, (byte) 28, (byte) -99, (byte) 74, (byte) -24, (byte) -94, (byte) -61, (byte) 125, (byte) -96, (byte) 50, (byte) -114, (byte) 90, (byte) 24, (byte) -59, (byte) 23, (byte) 106, (byte) -9, (byte) 102, (byte) -56, (byte) 125, (byte) 87, (byte) 42, (byte) 97, (byte) -29, (byte) 71, (byte) 116, (byte) -33, (byte) 59, (byte) -110, (byte) 61, (byte) 94, (byte) 84, (byte) -80, (byte) -25, (byte) -72, (byte) -31, (byte) 4, (byte) 73, (byte) -117, (byte) 57, (byte) 64, (byte) -113, (byte) 45, (byte) -81, (byte) 25, (byte) 60, (byte) 106, (byte) 43, (byte) 31, (byte) -11, (byte) 45, (byte) 7, (byte) -89, (byte) -54, (byte) 87, (byte) -91, (byte) 30, (byte) -101, (byte) 109, (byte) 77, (byte) -112, (byte) -120, (byte) 88, (byte) 77, (byte) 109, (byte) -63, (byte) 66, (byte) -50, (byte) 43, (byte) -106, (byte) -13, (byte) -105, (byte) 32, (byte) -52, (byte) 31, (byte) -52, (byte) -19, (byte) -53, (byte) 37, (byte) 41, (byte) -18, (byte) -104, (byte) 4, (byte) -10, (byte) 122, (byte) -62, (byte) 21, (byte) -31, (byte) 119, (byte) 118, (byte) -32, (byte) -116, (byte) 28, (byte) 20, (byte) -24, (byte) 81, (byte) -99, (byte) 49, (byte) 55, (byte) -39, (byte) -121, (byte) -24, (byte) -104, (byte) -78, (byte) -92, (byte) 121, (byte) 89, (byte) -56, (byte) 126, (byte) 67, (byte) 66, (byte) 106, (byte) 122, (byte) 41, (byte) 72, (byte) 70, (byte) 6, (byte) 70, (byte) -37, (byte) 119, (byte) -76, (byte) -1, (byte) 36, (byte) 97, (byte) -20, (byte) 37, (byte) -22, (byte) 11, (byte) -57, (byte) -61, (byte) 63, (byte) -46, (byte) 41, (byte) 45, (byte) 106, (byte) 48, (byte) -18, (byte) 10, (byte) 14, (byte) -49, (byte) 89, (byte) 76, (byte) 12, (byte) 28, (byte) 11, (byte) 107, (byte) -82, (byte) -58, (byte) -49, (byte) -46, (byte) 44, (byte) 82, (byte) 103, (byte) -91, (byte) 94, (byte) -126, (byte) -23, (byte) 69, (byte) -63, (byte) 34, (byte) -101, (byte) 1, (byte) -36, (byte) 98, (byte) -43, (byte) 56, (byte) -60, (byte) 77, (byte) -49, (byte) -48, (byte) 58, (byte) -62, (byte) -117, (byte) 31, (byte) -44, (byte) -102, (byte) -80, (byte) 97, (byte) -106, (byte) 93, (byte) -29, (byte) 46, (byte) -28, (byte) 24, (byte) 38, (byte) 85, (byte) -57, (byte) 117, (byte) 119, (byte) 12, (byte) -121, (byte) 96, (byte) -36, (byte) -24, (byte) 9, (byte) -13, (byte) 24, (byte) -82, (byte) 46, (byte) 10, (byte) -35, (byte) 71, (byte) -31, (byte) 65, (byte) -66, (byte) 36, (byte) 13, (byte) 13, (byte) -79, (byte) 100, (byte) 77, (byte) 26, (byte) 70, (byte) -58, (byte) 38, (byte) -64, (byte) 37, (byte) -46, (byte) -64, (byte) -108, (byte) -56, (byte) -27, (byte) 46, (byte) 93, (byte) 42, (byte) 65, (byte) -124, (byte) 63, (byte) -47, (byte) -96, (byte) 95, (byte) -7, (byte) 125, (byte) 68, (byte) -51, (byte) 117, (byte) -15, (byte) -62, (byte) -29, (byte) 106, (byte) -96, (byte) 9, (byte) 39, (byte) 5, (byte) -87, (byte) -17, (byte) -56, (byte) 115, (byte) 79, (byte) -124, (byte) -66, (byte) -125, (byte) -39, (byte) -3, (byte) -92, (byte) 19, (byte) -31, (byte) 61, (byte) 121, (byte) -97, (byte) 60, (byte) -36, (byte) 65, (byte) 51, (byte) -88, (byte) 107, (byte) -79, (byte) -15, (byte) 17, (byte) 64, (byte) -48, (byte) 45, (byte) 123, (byte) -5, (byte) 74, (byte) 76, (byte) -6, (byte) -93, (byte) -74, (byte) -112, (byte) 24, (byte) 126, (byte) 32, (byte) 99, (byte) 93, (byte) -52, (byte) 39, (byte) -45, (byte) -4, (byte) 89, (byte) 15, (byte) -37, (byte) 117, (byte) -35, (byte) -80, (byte) 97, (byte) -75, (byte) 104, (byte) 27, (byte) -42, (byte) 10, (byte) 19, (byte) -3, (byte) 73, (byte) 21, (byte) 79, (byte) -32, (byte) -63, (byte) -49, (byte) -91, (byte) 113, (byte) 41, (byte) -10, (byte) 126, (byte) -24, (byte) 105, (byte) -7, (byte) -126, (byte) -88, (byte) -63, (byte) -123, (byte) 67, (byte) -9, (byte) 100, (byte) -7, (byte) 34, (byte) 84, (byte) -79, (byte) -107, (byte) 48, (byte) -97, (byte) 39, (byte) -1, (byte) 22, (byte) 12, (byte) -10, (byte) 86, (byte) -68, (byte) -55, (byte) -97, (byte) -13, (byte) 6, (byte) -102, (byte) 120, (byte) -63, (byte) -12, (byte) -55, (byte) -1, (byte) 61, (byte) -82, (byte) -67, (byte) 104, (byte) 10, (byte) 74, (byte) -5, (byte) 123, (byte) -89, (byte) 122, (byte) 27, (byte) -19, (byte) 25, (byte) 25, (byte) 52, (byte) -118, (byte) 88, (byte) 89, (byte) 121, (byte) 106, (byte) 120, (byte) -28, (byte) 117, (byte) 126, (byte) -19, (byte) 119, (byte) 32, (byte) 118, (byte) -115, (byte) 52, (byte) -83, (byte) 55, (byte) 98, (byte) 55, (byte) 69, (byte) 6, (byte) 47, (byte) -19, (byte) 55, (byte) -112, (byte) 12, (byte) -65, Byte.MAX_VALUE, (byte) -57, (byte) -82, (byte) -127, (byte) 106, (byte) 20, (byte) 30, (byte) -69, (byte) 25, (byte) 72, (byte) -23, (byte) 52, (byte) -88, (byte) -121, (byte) 66, (byte) -39, (byte) -111, (byte) -100, (byte) -107, Byte.MAX_VALUE, (byte) 86, (byte) -105, (byte) -91, (byte) 52, (byte) -24, (byte) 67, (byte) -115, (byte) -104, (byte) 45, (byte) -34, (byte) -22, (byte) 114, (byte) 14, (byte) 54, (byte) -72, (byte) 34, (byte) -29, (byte) -102, (byte) 45, (byte) -86, (byte) -25, (byte) -91, (byte) 49, (byte) 102, (byte) -52, (byte) 46, Byte.MAX_VALUE, (byte) 45, (byte) -121, (byte) -67, (byte) 113, (byte) -119, (byte) -120, (byte) -121, (byte) -24, (byte) -26, (byte) 8, (byte) -22, (byte) 29, (byte) -77, (byte) -65, (byte) -111, (byte) 13, (byte) 23, (byte) -12, (byte) -102, (byte) -91, (byte) -31, (byte) -72, (byte) 61, (byte) -107, (byte) 115, (byte) -115, (byte) 5, (byte) 71, (byte) -38, (byte) -67, (byte) 62, (byte) -29, (byte) 88, (byte) 112, (byte) -57, (byte) -49, (byte) 113, (byte) -60, (byte) 57, (byte) -118, (byte) 61, (byte) 51, (byte) -15, (byte) -86, (byte) -74, (byte) -74, (byte) 74, (byte) 122, (byte) -43, (byte) -38, (byte) 122, (byte) 25, (byte) 98, (byte) 83, (byte) -65, (byte) 52, (byte) 99, (byte) -27, (byte) -59, (byte) -4, (byte) 52, (byte) -12, (byte) 97, (byte) 115, (byte) 118, (byte) 43, (byte) 48, (byte) -58, (byte) -11, (byte) -18, (byte) -42, (byte) 1, (byte) 126, (byte) -10, (byte) 73, (byte) -19, (byte) -78, (byte) 120, (byte) 62, (byte) 29, (byte) 40, (byte) -66, (byte) 82, (byte) -12, (byte) -53, (byte) -61, (byte) -65, (byte) 39, (byte) 46, (byte) -9, (byte) 15, (byte) 10, (byte) -4, (byte) 79, (byte) -118, (byte) 19, (byte) 36, (byte) 118, (byte) -13, (byte) 122, (byte) -74, (byte) 29, (byte) 85, (byte) -41, (byte) -19, (byte) -109, (byte) 94, (byte) -55, (byte) 29, (byte) -45, (byte) 14, (byte) -103, (byte) -23, (byte) 103, (byte) -122, (byte) -2, (byte) -124, (byte) 33, (byte) 108, (byte) -125, (byte) -39, (byte) 48, (byte) -4, (byte) -50, (byte) 70, (byte) 90, (byte) -7, (byte) -96, (byte) 106, (byte) 83, (byte) -17, (byte) 117, (byte) 16, (byte) 15, (byte) 10, (byte) -2, (byte) 114, (byte) -93, (byte) -13, (byte) -75, (byte) -72, (byte) -66, (byte) -44, (byte) -62, (byte) 65, (byte) -69, (byte) 28, (byte) 19, (byte) 78, (byte) -48, (byte) 13, (byte) -108, (byte) 97, (byte) 70, (byte) -49, (byte) -30, (byte) -47, (byte) -28, (byte) 30, (byte) 116, (byte) -121, (byte) 123, (byte) -9, (byte) 87, (byte) 73, (byte) -67, (byte) 70, (byte) -72, (byte) -9, (byte) -38, (byte) 7, (byte) 6, (byte) -11, (byte) 34, (byte) 2, (byte) 19, (byte) 107, (byte) 6, (byte) 59, (byte) 66, (byte) -56, (byte) -20, (byte) -71, (byte) 36, (byte) 17, (byte) -76, (byte) 71, (byte) 1, (byte) 98, (byte) 16, (byte) 37, (byte) -21, (byte) -101, (byte) -74, (byte) -84, (byte) 34, (byte) -66, (byte) -47, (byte) 120, (byte) 99, (byte) -126, (byte) 68, (byte) 111, (byte) 56, (byte) 1, (byte) 37, (byte) 32, (byte) 2, (byte) -98, (byte) -69, (byte) -38, (byte) 7, (byte) 30, (byte) 118, (byte) -35, (byte) -108, (byte) 1, (byte) -114, (byte) 0, (byte) 79, (byte) 48, (byte) 63, (byte) 61, (byte) 101, (byte) -117, (byte) -107, (byte) 25, (byte) -109, (byte) -60, (byte) 73, (byte) 94, (byte) -76, (byte) 122, (byte) -110, (byte) -110, (byte) 97, (byte) -68, (byte) -8, (byte) -40, (byte) 0, (byte) 24, (byte) -12, (byte) 108, (byte) 24, (byte) -13, (byte) -26, (byte) -61, (byte) -121, (byte) 12, (byte) -68, (byte) 74, (byte) -89, (byte) 99, (byte) 112, (byte) -10, (byte) -90, (byte) 11, (byte) -70, (byte) 47, (byte) -33, (byte) -41, (byte) -42, (byte) -88, (byte) 24, (byte) -49, (byte) -124, (byte) -8, (byte) 69, (byte) 66, (byte) -105, (byte) -121, (byte) 26, (byte) -7, (byte) 93, (byte) 117, (byte) -123, (byte) 104, (byte) -80, (byte) 82, (byte) -121, (byte) 102, (byte) 58, (byte) 97, (byte) -12, (byte) 100, (byte) -112, (byte) 84, (byte) -50, (byte) -15, (byte) 89, (byte) 77, (byte) -18, (byte) 72, (byte) 33, (byte) -127, (byte) -13, (byte) -102, (byte) 115, (byte) -22, (byte) -14, (byte) -63, (byte) 98, (byte) 84, (byte) 9, (byte) 56, (byte) -1, (byte) -76, (byte) -112, (byte) -123, (byte) 21, (byte) 21, (byte) 33, (byte) 80, (byte) 115, (byte) 49, (byte) 69, (byte) -40, (byte) -78, (byte) -79, (byte) -17, (byte) 86, (byte) -113, (byte) 3, Byte.MIN_VALUE, (byte) -1, (byte) 41, (byte) 29, (byte) 1, (byte) 84, (byte) -16, (byte) 59, (byte) -47, (byte) 85, (byte) -113, (byte) -61, (byte) -111, (byte) 85, (byte) -86, (byte) 72, (byte) -60, (byte) -63, (byte) 82, (byte) 11, (byte) 53, (byte) 63, (byte) -92, (byte) 123, (byte) 56, (byte) 110, (byte) 124, (byte) -93, (byte) -33, (byte) -95, (byte) 29, (byte) -1, (byte) 110, (byte) 88, (byte) 87, (byte) -46, (byte) 126, (byte) 114, (byte) -29, (byte) 98, (byte) 115, (byte) 28, (byte) 6, (byte) -116, (byte) -118, (byte) -43, (byte) 12, (byte) 77, (byte) -121, Byte.MIN_VALUE, (byte) -18, (byte) 13, (byte) -84, (byte) -93, (byte) -105, (byte) -42, (byte) 31, (byte) 17, (byte) -79, (byte) -30, (byte) 97, (byte) -57, (byte) 25, (byte) -94, (byte) 53, (byte) -59, (byte) 20, (byte) -82, (byte) -12, (byte) -64, (byte) 121, (byte) 54, (byte) -43, (byte) -91, (byte) 26, (byte) 74, (byte) 123, (byte) 59, (byte) -6, (byte) -59, (byte) 25, (byte) 25, (byte) -127, (byte) 6, (byte) 57, (byte) -87, (byte) -98, (byte) -98, (byte) -37, (byte) 61, (byte) 97, (byte) -33, (byte) 43, (byte) 122, (byte) 63, (byte) -15, (byte) -28, (byte) -102, (byte) 63, (byte) 41, (byte) 80, (byte) 76, (byte) 90, (byte) -92, (byte) 118, (byte) 5, (byte) 78, (byte) 76, (byte) -106, (byte) -49, (byte) 41, (byte) 99, (byte) 79, (byte) 32, (byte) 51, (byte) 97, (byte) -112, (byte) -126, (byte) -87, (byte) 53, (byte) 89, (byte) 35, (byte) -70, (byte) 113, (byte) 88, (byte) -27, (byte) -16, (byte) -25, (byte) 53, (byte) -12, (byte) 126, (byte) -5, (byte) 101, (byte) -48, (byte) 56, (byte) -24, (byte) 35, (byte) -52, (byte) -2, (byte) 11, (byte) -77, (byte) 76, (byte) 109, (byte) 93, (byte) -76, (byte) -69, (byte) 50, (byte) 125, (byte) -89, (byte) 46, (byte) 83, (byte) 42, (byte) 75, (byte) 28, (byte) 49, (byte) -16, (byte) -67, (byte) -51, (byte) -34, (byte) 50, (byte) 118, (byte) -83, (byte) 29, (byte) -71, (byte) 94, (byte) 66, (byte) 102, (byte) 108, (byte) -48, (byte) -31, (byte) 123, (byte) 28, (byte) 79, (byte) 38, (byte) -81, (byte) -104, (byte) -74, (byte) -22, (byte) 81};
    private boolean f2389p = false;
    private int[] f2390q = new int[1];
    private int[] f2391r = new int[1];
    private int[] f2392s = new int[1];
    private List<DeviceInfo> f2393t = new ArrayList();
    private List<Integer> f2394u = new ArrayList();
    private String f2395v;
    private int f2396w = 0;
    private FaceDetectingEvent f2397x;
    private boolean f2398y = false;
    private boolean f2399z = false;

    public interface FaceDetectingEvent {
        void onFaceDetected(int i);
    }

    private class DeviceInfo {
        public String f2371a;
        public String f2372b;
        final /* synthetic */ FUUtil f2373c;

        public DeviceInfo(FUUtil fUUtil, String brand, String model) {
            this.f2373c = fUUtil;
            this.f2371a = brand;
            this.f2372b = model;
        }
    }

    private FUUtil() {
        this.f2393t.add(new DeviceInfo(this, "Xiaomi", "2014011"));
        this.f2393t.add(new DeviceInfo(this, "Honor", "CHM-CL00"));
        this.f2393t.add(new DeviceInfo(this, "Honor", "CHM-TL00"));
        this.f2393t.add(new DeviceInfo(this, "Honor", "CHM-UL00"));
        this.f2393t.add(new DeviceInfo(this, "Honor", "CHM-TL00H"));
    }

    public static FUUtil getInstance() {
        return f2377d;
    }

    public void init() {
        if (!this.f2378e) {
            synchronized (this) {
                if (!this.f2378e) {
                    f2376c.m263e("MODEL:" + Build.MODEL + " BRAND:" + Build.BRAND);
                    byte[] v3Data = FileUtil.readAsset("v3.mp3");
                    if (v3Data != null && v3Data.length > 0) {
                        faceunity.fuSetup(v3Data, null, this.f2388o);
                        m1539d();
                        this.f2378e = true;
                    }
                    EventBus.getDefault().register(this);
                }
            }
        }
    }

    public void setFaceUnity(String unityName) {
        if (m1535a(unityName) && this.f2378e) {
            this.f2380g = unityName;
        }
    }

    public void clearFaceUnity() {
        this.f2380g = BuildConfig.VERSION_NAME;
        this.f2395v = BuildConfig.VERSION_NAME;
    }

    public void unloadAll() {
        if (this.f2379f != null && this.f2379f.size() > 0) {
            for (Integer i : this.f2379f.values()) {
                if (i != null) {
                    try {
                        faceunity.fuDestroyItem(i.intValue());
                    } catch (Exception e) {
                    }
                }
            }
            this.f2379f.clear();
        }
    }

    public void deivceLost() {
        f2376c.m261d("deviceLost");
        faceunity.fuOnDeviceLost();
    }

    public void cameraChange() {
        f2376c.m261d("cameraChange");
        faceunity.fuOnCameraChange();
    }

    @Deprecated
    private int m1533a(byte[] img, int w, int h) {
        int[] items = m1536a();
        if (items == null) {
            return -1;
        }
        int i = this.f2382i;
        this.f2382i = i + 1;
        return faceunity.fuRenderToNV21Image(img, w, h, i, items);
    }

    private int m1534a(byte[] img, int tex_in, int flags, int w, int h) {
        int[] items = m1536a();
        if (items == null) {
            return -1;
        }
        int i = this.f2382i;
        this.f2382i = i + 1;
        return faceunity.fuDualInputToTexture(img, tex_in, flags, w, h, i, items);
    }

    private int[] m1536a() {
        if (this.f2378e) {
            byte[] data;
            int itemIndex;
            this.f2394u.clear();
            String currentItem = this.f2380g;
            if (!TextUtils.isEmpty(currentItem) && m1538c()) {
                if (!this.f2379f.containsKey(currentItem)) {
                    data = FileUtil.readBytes(currentItem);
                    if (data == null || data.length <= 0) {
                        f2376c.m263e("readAsset:" + currentItem + " failed");
                        return null;
                    }
                    itemIndex = faceunity.fuCreateItemFromPackage(data);
                    this.f2379f.put(currentItem, Integer.valueOf(itemIndex));
                    f2376c.m263e("fuCreateItemFromPackage:" + currentItem + " item:" + itemIndex);
                }
                this.f2394u.add(this.f2379f.get(currentItem));
                if (this.f2379f.size() > 4) {
                    int countNeedRemove = this.f2379f.size() - 4;
                    for (String key : this.f2379f.keySet()) {
                        if (!(countNeedRemove <= 0 || key.equals(currentItem) || key.equals("face_beautification.mp3"))) {
                            f2376c.m261d("remove key:" + key);
                            int index = ((Integer) this.f2379f.get(key)).intValue();
                            this.f2379f.remove(key);
                            faceunity.fuDestroyItem(index);
                            countNeedRemove--;
                            continue;
                        }
                        if (countNeedRemove == 0) {
                            break;
                        }
                    }
                }
            }
            if (m1537b() && !this.f2379f.containsKey("face_beautification.mp3")) {
                data = FileUtil.readAsset("face_beautification.mp3");
                if (data == null || data.length <= 0) {
                    f2376c.m263e("readAsset:face_beautification.mp3 failed");
                } else {
                    itemIndex = faceunity.fuCreateItemFromPackage(data);
                    this.f2379f.put("face_beautification.mp3", Integer.valueOf(itemIndex));
                    f2376c.m263e("fuCreateItemFromPackage:face_beautification.mp3 item:" + itemIndex);
                }
            }
            if (m1537b() && this.f2379f.containsKey("face_beautification.mp3")) {
                int beautyItem = ((Integer) this.f2379f.get("face_beautification.mp3")).intValue();
                this.f2394u.add(Integer.valueOf(beautyItem));
                faceunity.fuItemSetParam(beautyItem, "filter_name", f2375b);
                faceunity.fuItemSetParam(beautyItem, "color_level", (double) this.f2384k);
                faceunity.fuItemSetParam(beautyItem, "blur_level", (double) this.f2385l);
                faceunity.fuItemSetParam(beautyItem, "cheek_thinning", (double) this.f2386m);
                faceunity.fuItemSetParam(beautyItem, "eye_enlarging", (double) this.f2387n);
            }
            if (this.f2394u.size() > 0) {
                int[] iArr = new int[this.f2394u.size()];
                for (int i = 0; i < this.f2394u.size(); i++) {
                    iArr[i] = ((Integer) this.f2394u.get(i)).intValue();
                }
                int trackingResult = faceunity.fuIsTracking();
                if (trackingResult <= 0 || this.f2396w != 0) {
                    this.f2396w = trackingResult;
                } else if (this.f2397x != null) {
                    this.f2396w = trackingResult;
                    this.f2397x.onFaceDetected(this.f2396w);
                }
                int frameId = this.f2382i;
                long time = System.currentTimeMillis();
                return iArr;
            }
        }
        return null;
    }

    private boolean m1537b() {
        return this.f2383j && !this.f2399z;
    }

    private boolean m1538c() {
        return !this.f2398y;
    }

    private void m1539d() {
        if (!this.f2389p) {
            GLES20.glGetIntegerv(35661, this.f2390q, 0);
            OpenGLUtil.checkGlError("glGetIntegerv:GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS");
            f2376c.m261d("GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS:" + this.f2390q[0]);
            GLES20.glGetIntegerv(34930, this.f2391r, 0);
            OpenGLUtil.checkGlError("glGetIntegerv:GL_MAX_TEXTURE_IMAGE_UNITS");
            f2376c.m261d("GL_MAX_TEXTURE_IMAGE_UNITS:" + this.f2391r[0]);
            GLES20.glGetIntegerv(35660, this.f2392s, 0);
            OpenGLUtil.checkGlError("glGetIntegerv:GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS");
            f2376c.m261d("GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS:" + this.f2392s[0]);
            this.f2389p = true;
        }
    }

    @Deprecated
    public int applyFaceUnity(byte[] data, int width, int height) {
        if (getInstance().isEnableBeauty() && isTextureEnough()) {
            getInstance().enableBeauty(true);
        } else {
            getInstance().enableBeauty(false);
        }
        getInstance().init();
        return getInstance().m1533a(data, width, height);
    }

    public int applyFaceUnity(byte[] data, int texId, int width, int height) {
        if (getInstance().isEnableBeauty() && isTextureEnough()) {
            getInstance().enableBeauty(true);
        } else {
            getInstance().enableBeauty(false);
        }
        getInstance().init();
        return getInstance().m1534a(data, texId, 1, width, height);
    }

    public void release() {
        if (this.f2378e) {
            synchronized (this) {
                if (this.f2378e) {
                    EventBus.getDefault().unregister(this);
                    faceunity.fuDone();
                    this.f2378e = false;
                }
            }
        }
    }

    public void enableBeauty(boolean enabled) {
        this.f2383j = enabled;
    }

    public boolean isEnableBeauty() {
        return this.f2383j;
    }

    public boolean isTextureEnough() {
        return this.f2391r[0] >= 10 || this.f2391r[0] == 0;
    }

    public float getColorLevel() {
        return this.f2384k;
    }

    public void setColorLevel(float level) {
        this.f2384k = level;
    }

    public float getBlurRadius() {
        return this.f2385l;
    }

    public void setBlurLevel(float blurRadius) {
        this.f2385l = blurRadius;
    }

    public float getEyeEnlarging() {
        return this.f2387n;
    }

    public void setEyeEnlarging(float eyeEnlarging) {
        this.f2387n = eyeEnlarging;
    }

    public float getCheekThinning() {
        return this.f2386m;
    }

    public void setCheekThinning(float cheekThinning) {
        this.f2386m = cheekThinning;
    }

    private boolean m1535a(String unityName) {
        if (TextUtils.isEmpty(unityName) || !new File(unityName).exists()) {
            f2376c.m261d("isLegalFaceUnity:false:" + unityName);
            return false;
        }
        f2376c.m261d("isLegalFaceUnity:true:" + unityName);
        return true;
    }

    public boolean isSupportFaceUnity() {
        int i = 0;
        while (i < this.f2393t.size()) {
            if (!TextUtils.isEmpty(Build.MODEL) && ((DeviceInfo) this.f2393t.get(i)).f2372b.toLowerCase().equals(Build.MODEL.toLowerCase()) && !TextUtils.isEmpty(Build.BRAND) && ((DeviceInfo) this.f2393t.get(i)).f2371a.toLowerCase().equals(Build.BRAND.toLowerCase())) {
                return false;
            }
            i++;
        }
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selectFaceunityEvent(UseFaceUnityEvent event) {
        f2376c.m261d("UseFaceUnityEvent:path" + event.f503a);
        this.f2395v = event.f505c;
        if (TextUtils.isEmpty(event.f503a)) {
            getInstance().clearFaceUnity();
        } else {
            getInstance().setFaceUnity(event.f503a);
        }
    }

    public String getCurrentCoverUrl() {
        return this.f2395v;
    }

    public void setFaceDetectingEvent(FaceDetectingEvent event) {
        this.f2397x = event;
    }

    public void resetFaceDetecting() {
        this.f2396w = 0;
    }
}
