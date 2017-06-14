package im.facechat.common.p043c;

import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.ArrayMap;
import android.telephony.TelephonyManager;
import com.sina.weibo.sdk.component.GameManager;
import im.facechat.common.p045b.C1639b;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
import org.webrtc.StatsReport;
import org.webrtc.StatsReport.Value;
import p042u.aly.cp;
import p042u.aly.dd;
import tourguide.tourguide.C2509R;
import tv.danmaku.ijk.media.player.BuildConfig;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* compiled from: CollectUtil */
public class C1643b {
    private static final Class<?> f11228a = C1643b.class;

    /* compiled from: CollectUtil */
    public static class C1641a {
        private final ArrayMap<String, String> f11218a = new ArrayMap();

        private C1641a m7923c() {
            this.f11218a.put("nt", C1643b.m7939a());
            return this;
        }

        private C1641a m7924d() {
            this.f11218a.put("isp", C1643b.m7942c());
            return this;
        }

        private C1641a m7925e() {
            this.f11218a.put("locs", C1643b.m7943d());
            return this;
        }

        private C1641a m7926f() {
            this.f11218a.put("area", C1643b.m7944e());
            return this;
        }

        private C1641a m7927g() {
            this.f11218a.put("lang", C1643b.m7945f());
            return this;
        }

        private C1641a m7928h() {
            this.f11218a.put("osv", C1643b.m7946g());
            return this;
        }

        private C1641a m7929i() {
            this.f11218a.put("av", C1643b.m7947h());
            return this;
        }

        private C1641a m7930j() {
            this.f11218a.put("db", Build.BRAND);
            return this;
        }

        private C1641a m7931k() {
            this.f11218a.put("dm", Build.DEVICE);
            return this;
        }

        public Map<String, String> m7932a() {
            m7923c().m7924d().m7926f().m7927g().m7925e().m7928h().m7930j().m7931k().m7929i();
            try {
                this.f11218a.put("isp", URLEncoder.encode((String) this.f11218a.get("isp"), GameManager.DEFAULT_CHARSET));
            } catch (Throwable e) {
                C1639b.m7917a(C1643b.f11228a, "buildMapForUserAgent : ", e);
            }
            return this.f11218a;
        }

        public String m7933b() {
            return "#top" + new JSONObject(m7932a()).toString() + "top#";
        }
    }

    /* compiled from: CollectUtil */
    public static class C1642b {
        long f11219a;
        long f11220b;
        long f11221c;
        long f11222d;
        long f11223e;
        long f11224f;
        float f11225g;
        float f11226h;
        int f11227i;

        public Map<String, Object> m7934a() {
            Map hashMap = new HashMap();
            hashMap.put("rsb", Long.valueOf(this.f11221c));
            hashMap.put("rrb", Long.valueOf(this.f11222d));
            hashMap.put("sr", Long.valueOf(this.f11223e / ((this.f11220b - this.f11219a) / 1000)));
            hashMap.put("rr", Long.valueOf(this.f11224f / ((this.f11220b - this.f11219a) / 1000)));
            hashMap.put("sl", Float.valueOf(this.f11225g));
            hashMap.put("rl", Float.valueOf(this.f11226h));
            hashMap.put("link", Integer.valueOf(this.f11227i));
            return hashMap;
        }

        public JSONObject m7935b() {
            return new JSONObject(m7934a());
        }

        public String toString() {
            return m7935b().toString();
        }
    }

    public static String m7939a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) C1646c.m7950a().m7952b().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "invalid";
        }
        String typeName = activeNetworkInfo.getTypeName();
        if (typeName.equalsIgnoreCase("WIFI")) {
            return "wifi";
        }
        if (typeName.equalsIgnoreCase("MOBILE")) {
            return C1643b.m7941b() ? "3g" : "2g";
        } else {
            return "invalid";
        }
    }

    public static boolean m7941b() {
        switch (((TelephonyManager) C1646c.m7950a().m7952b().getSystemService("phone")).getNetworkType()) {
            case dd.f15932a /*0*/:
                return false;
            case dd.f15933b /*1*/:
                return false;
            case dd.f15934c /*2*/:
                return false;
            case dd.f15935d /*3*/:
                return true;
            case dd.f15936e /*4*/:
                return false;
            case cp.f15921f /*5*/:
                return true;
            case IjkMediaPlayer.IJK_LOG_ERROR /*6*/:
                return true;
            case IjkMediaPlayer.IJK_LOG_FATAL /*7*/:
                return false;
            case IjkMediaPlayer.IJK_LOG_SILENT /*8*/:
                return true;
            case C2509R.styleable.TextAppearance_textAllCaps /*9*/:
                return true;
            case C2509R.styleable.SwitchCompat_switchMinWidth /*10*/:
                return true;
            case C2509R.styleable.Toolbar_popupTheme /*11*/:
                return false;
            case C2509R.styleable.Toolbar_titleTextAppearance /*12*/:
                return true;
            case C2509R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                return true;
            case C2509R.styleable.SearchView_suggestionRowLayout /*14*/:
                return true;
            case C2509R.styleable.Toolbar_titleMarginStart /*15*/:
                return true;
            default:
                return false;
        }
    }

    public static String m7942c() {
        TelephonyManager telephonyManager = (TelephonyManager) C1646c.m7950a().m7952b().getSystemService("phone");
        int simState = telephonyManager.getSimState();
        if (simState < 2 || simState > 5) {
            return BuildConfig.VERSION_NAME;
        }
        return telephonyManager.getSimOperatorName();
    }

    public static String m7943d() {
        if (VERSION.SDK_INT < 23 || ActivityCompat.checkSelfPermission(C1646c.m7950a().m7952b(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(C1646c.m7950a().m7952b(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            Location lastKnownLocation = ((LocationManager) C1646c.m7950a().m7952b().getSystemService("location")).getLastKnownLocation("passive");
            if (lastKnownLocation != null) {
                return lastKnownLocation.getLongitude() + "-" + lastKnownLocation.getLatitude();
            }
        }
        return "0-0";
    }

    public static String m7944e() {
        return Locale.getDefault().getCountry();
    }

    public static String m7945f() {
        return Locale.getDefault().getLanguage();
    }

    public static String m7946g() {
        return VERSION.RELEASE;
    }

    public static String m7947h() {
        return "0.1";
    }

    public static C1642b m7938a(StatsReport[] statsReportArr) {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        long j10 = 0;
        int i = 4;
        int length = statsReportArr.length;
        int i2 = 0;
        while (i2 < length) {
            long j11;
            int i3;
            long j12;
            StatsReport statsReport = statsReportArr[i2];
            Map a;
            String str;
            long j13;
            if (statsReport.type.equals("ssrc") && statsReport.id.contains("ssrc") && statsReport.id.contains("send")) {
                a = C1643b.m7940a(statsReport);
                str = (String) a.get("googTrackId");
                if (str == null || !str.contains("ARDAMSv0")) {
                    j13 = j6;
                    j11 = j5;
                    j6 = j3;
                } else {
                    j6 = C1643b.m7936a((String) a.get("bytesSent"));
                    j11 = C1643b.m7936a((String) a.get("packetsLost"));
                    j13 = C1643b.m7936a((String) a.get("packetsSent"));
                }
                j5 = j13;
                j3 = j11;
                i3 = i;
                j11 = j10;
                j12 = j6;
                j10 = j2;
                j6 = j9;
                j9 = j;
            } else if (statsReport.type.equals("ssrc") && statsReport.id.contains("ssrc") && statsReport.id.contains("recv")) {
                a = C1643b.m7940a(statsReport);
                str = (String) a.get("googTrackId");
                if (str == null || !str.contains("ARDAMSv0")) {
                    j13 = j8;
                    j11 = j7;
                    j8 = j4;
                } else {
                    j8 = C1643b.m7936a((String) a.get("bytesReceived"));
                    j11 = C1643b.m7936a((String) a.get("packetsLost"));
                    j13 = C1643b.m7936a((String) a.get("packetsReceived"));
                }
                j7 = j11;
                j4 = j8;
                j8 = j13;
                j11 = j10;
                i3 = i;
                j10 = j2;
                j12 = j3;
                j3 = j5;
                j5 = j6;
                j6 = j9;
                j9 = j;
            } else if (statsReport.id.equals("bweforvideo")) {
                a = C1643b.m7940a(statsReport);
                j11 = C1643b.m7936a((String) a.get("googAvailableSendBandwidth"));
                int i4 = i;
                j12 = j3;
                j3 = j5;
                j5 = j6;
                j6 = j9;
                j9 = C1643b.m7936a((String) a.get("googAvailableReceiveBandwidth"));
                i3 = i4;
                long j14 = j11;
                j11 = j10;
                j10 = j14;
            } else {
                if (statsReport.type.equals("googCandidatePair")) {
                    Map a2 = C1643b.m7940a(statsReport);
                    str = (String) a2.get("googActiveConnection");
                    if (str != null && str.equals("true")) {
                        str = (String) a2.get("googLocalCandidateType");
                        String str2 = (String) a2.get("googRemoteCandidateType");
                        j12 = C1643b.m7936a((String) a2.get("googbytesSent"));
                        j11 = C1643b.m7936a((String) a2.get("googbytesReceived"));
                        long j15;
                        if (!str.equalsIgnoreCase(str2)) {
                            i3 = 4;
                            j10 = j2;
                            j9 = j;
                            j15 = j5;
                            j5 = j6;
                            j6 = j12;
                            j12 = j3;
                            j3 = j15;
                        } else if (str.equalsIgnoreCase("local")) {
                            i3 = 1;
                            j10 = j2;
                            j9 = j;
                            j15 = j5;
                            j5 = j6;
                            j6 = j12;
                            j12 = j3;
                            j3 = j15;
                        } else if (str.equalsIgnoreCase("relay")) {
                            i3 = 3;
                            j10 = j2;
                            j9 = j;
                            j15 = j5;
                            j5 = j6;
                            j6 = j12;
                            j12 = j3;
                            j3 = j15;
                        } else if (str.equalsIgnoreCase("prflx")) {
                            i3 = 2;
                            j10 = j2;
                            j9 = j;
                            j15 = j5;
                            j5 = j6;
                            j6 = j12;
                            j12 = j3;
                            j3 = j15;
                        } else {
                            i3 = 4;
                            j10 = j2;
                            j9 = j;
                            j15 = j5;
                            j5 = j6;
                            j6 = j12;
                            j12 = j3;
                            j3 = j15;
                        }
                    }
                }
                i3 = i;
                j11 = j10;
                j12 = j3;
                j10 = j2;
                j3 = j5;
                j5 = j6;
                j6 = j9;
                j9 = j;
            }
            i2++;
            j2 = j10;
            j = j9;
            j9 = j6;
            j10 = j11;
            j6 = j5;
            j5 = j3;
            j3 = j12;
            i = i3;
        }
        C1642b c1642b = new C1642b();
        c1642b.f11227i = i;
        c1642b.f11220b = SystemClock.elapsedRealtime();
        c1642b.f11226h = j8 == 0 ? 0.0f : (((float) j7) * 1.0f) / ((float) j8);
        c1642b.f11225g = j6 == 0 ? 0.0f : (((float) j5) * 1.0f) / ((float) j6);
        c1642b.f11221c = j2;
        c1642b.f11222d = j;
        c1642b.f11223e = j3 + j9;
        c1642b.f11224f = j4 + j10;
        return c1642b;
    }

    public static C1642b m7937a(@Nullable C1642b c1642b, C1642b c1642b2) {
        if (c1642b == null) {
            c1642b2.f11219a = c1642b2.f11220b - 1000;
            return c1642b2;
        }
        c1642b.f11227i = c1642b2.f11227i;
        c1642b.f11220b = c1642b2.f11220b;
        c1642b.f11225g = c1642b2.f11225g;
        c1642b.f11226h = c1642b2.f11226h;
        c1642b.f11222d = c1642b2.f11222d;
        c1642b.f11221c = c1642b2.f11221c;
        c1642b.f11223e += c1642b2.f11223e;
        c1642b.f11224f += c1642b2.f11224f;
        return c1642b;
    }

    private static long m7936a(String str) {
        long j = 0;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (Throwable e) {
                C1639b.m7917a(f11228a, "bad value = " + str, e);
            }
        }
        return j;
    }

    private static ConcurrentHashMap<String, String> m7940a(StatsReport statsReport) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();
        for (Value value : statsReport.values) {
            concurrentHashMap.put(value.name, value.value);
        }
        return concurrentHashMap;
    }
}
