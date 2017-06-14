package im.facechat;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.ShareRequestParam;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.sina.weibo.sdk.utils.AidTask;
import im.facechat.C1677t.C1630b;
import im.facechat.C1677t.C1730a;
import im.facechat.C1677t.C1731c;
import im.facechat.C1694k.C1676b;
import im.facechat.common.p043c.C1640a;
import im.facechat.common.p043c.C1643b;
import im.facechat.common.p043c.C1648f;
import im.facechat.common.p045b.C1639b;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;
import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;
import org.webrtc.SessionDescription.Type;
import tv.danmaku.ijk.media.player.BuildConfig;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: FacechatRtcClient */
class C1678h implements C1676b, C1677t {
    private static final Class<?> f11306a = C1678h.class;
    private final C1694k f11307b;
    private final C1699m f11308c;
    private final List<IceCandidate> f11309d;
    private final ConcurrentHashMap<String, Pair<Long, String>> f11310e;
    private final C1630b f11311f;
    private final Rtc$b f11312g;
    private final Object f11313h = new Object();
    private String f11314i;
    private boolean f11315j;
    private String f11316k;
    private C1675b f11317l;
    private boolean f11318m = false;
    private long f11319n;
    private int f11320o;
    private Timer f11321p;

    /* compiled from: FacechatRtcClient */
    interface C1664a {
        int mo4063a();
    }

    /* compiled from: FacechatRtcClient */
    class C16651 implements C1664a {
        final /* synthetic */ C1678h f11282a;

        C16651(C1678h c1678h) {
            this.f11282a = c1678h;
        }

        public int mo4063a() {
            return this.f11282a.f11312g.m7848a();
        }
    }

    /* compiled from: FacechatRtcClient */
    class C16694 extends TimerTask {
        final /* synthetic */ C1678h f11289a;

        C16694(C1678h c1678h) {
            this.f11289a = c1678h;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r10 = this;
            r0 = r10.f11289a;
            r1 = r0.f11313h;
            monitor-enter(r1);
            r0 = r10.f11289a;	 Catch:{ all -> 0x0077 }
            r0 = r0.f11317l;	 Catch:{ all -> 0x0077 }
            r2 = im.facechat.C1678h.C1675b.CONNECTED;	 Catch:{ all -> 0x0077 }
            if (r0 == r2) goto L_0x0034;
        L_0x0011:
            r0 = r10.f11289a;	 Catch:{ all -> 0x0077 }
            r0 = r0.f11310e;	 Catch:{ all -> 0x0077 }
            r2 = "JOIN_KEY";
            r2 = im.facechat.common.p043c.C1648f.m7954a(r2);	 Catch:{ all -> 0x0077 }
            r0 = r0.get(r2);	 Catch:{ all -> 0x0077 }
            r0 = (android.support.v4.util.Pair) r0;	 Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0032;
        L_0x0025:
            r2 = r10.f11289a;	 Catch:{ all -> 0x0077 }
            r2 = r2.f11307b;	 Catch:{ all -> 0x0077 }
            r0 = r0.second;	 Catch:{ all -> 0x0077 }
            r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0077 }
            r2.m8093a(r0);	 Catch:{ all -> 0x0077 }
        L_0x0032:
            monitor-exit(r1);	 Catch:{ all -> 0x0077 }
        L_0x0033:
            return;
        L_0x0034:
            monitor-exit(r1);	 Catch:{ all -> 0x0077 }
            r0 = r10.f11289a;
            r0 = r0.f11310e;
            r0 = r0.entrySet();
            r2 = android.os.SystemClock.elapsedRealtime();
            r4 = r0.iterator();
        L_0x0047:
            r0 = r4.hasNext();
            if (r0 == 0) goto L_0x0033;
        L_0x004d:
            r0 = r4.next();
            r0 = (java.util.Map.Entry) r0;
            r1 = r0.getValue();
            r1 = (android.support.v4.util.Pair) r1;
            r1 = r1.first;
            r1 = (java.lang.Long) r1;
            r6 = r1.longValue();
            r6 = r6 - r2;
            r8 = 3600; // 0xe10 float:5.045E-42 double:1.7786E-320;
            r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
            if (r1 < 0) goto L_0x0047;
        L_0x0068:
            r1 = r10.f11289a;
            r1 = r1.f11308c;
            r5 = new im.facechat.h$4$1;
            r5.<init>(r10, r0);
            r1.execute(r5);
            goto L_0x0047;
        L_0x0077:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0077 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: im.facechat.h.4.run():void");
        }
    }

    /* compiled from: FacechatRtcClient */
    class C16705 implements Runnable {
        final /* synthetic */ C1678h f11290a;

        C16705(C1678h c1678h) {
            this.f11290a = c1678h;
        }

        public void run() {
            this.f11290a.m8031g();
            synchronized (this.f11290a.f11313h) {
                this.f11290a.f11317l = C1675b.CLOSED;
            }
            if (this.f11290a.f11307b != null) {
                this.f11290a.f11307b.m8094b();
            }
            this.f11290a.f11308c.m8106b();
        }
    }

    /* compiled from: FacechatRtcClient */
    private enum C1675b {
        NEW,
        INIT,
        CONNECTED,
        CLOSED,
        ERROR
    }

    C1678h(URI uri, C1699m c1699m, C1630b c1630b, @NonNull Rtc$b rtc$b) {
        this.f11308c = c1699m;
        this.f11308c.m8105a();
        this.f11311f = c1630b;
        this.f11312g = rtc$b;
        this.f11317l = C1675b.NEW;
        this.f11309d = new CopyOnWriteArrayList();
        this.f11310e = new ConcurrentHashMap();
        this.f11307b = new C1694k(uri, c1699m, this, new C16651(this));
    }

    private static void m8020b(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    void m8039a() {
        this.f11307b.m8092a();
    }

    void m8047b() {
        this.f11308c.execute(new C16705(this));
    }

    public void m8046a(final SessionDescription sessionDescription) {
        this.f11308c.execute(new Runnable(this) {
            final /* synthetic */ C1678h f11292b;

            public void run() {
                boolean z = false;
                if (this.f11292b.f11317l != C1675b.CONNECTED || sessionDescription == null || sessionDescription.description == null) {
                    Class d = C1678h.f11306a;
                    StringBuilder append = new StringBuilder().append("connection not established or sdp null is ");
                    if (sessionDescription == null) {
                        z = true;
                    }
                    C1639b.m7921c(d, append.append(z).toString());
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                C1678h.m8020b(jSONObject, "sdp", sessionDescription.description);
                C1678h.m8020b(jSONObject, IjkMediaMeta.IJKM_KEY_TYPE, "offer");
                this.f11292b.m8019b(this.f11292b.f11307b.m8091a(4002, jSONObject, this.f11292b.f11314i), jSONObject.toString());
                this.f11292b.f11318m = false;
            }
        });
    }

    public void m8048b(final SessionDescription sessionDescription) {
        this.f11308c.execute(new Runnable(this) {
            final /* synthetic */ C1678h f11294b;

            public void run() {
                JSONObject jSONObject = new JSONObject();
                C1678h.m8020b(jSONObject, "sdp", sessionDescription.description);
                C1678h.m8020b(jSONObject, IjkMediaMeta.IJKM_KEY_TYPE, "answer");
                this.f11294b.m8019b(this.f11294b.f11307b.m8091a(4003, jSONObject, this.f11294b.f11314i), jSONObject.toString());
                this.f11294b.m8027e();
            }
        });
    }

    public void m8045a(final IceCandidate iceCandidate) {
        this.f11308c.execute(new Runnable(this) {
            final /* synthetic */ C1678h f11296b;

            public void run() {
                if (C1640a.m7922a(this.f11296b.f11319n, 2) && iceCandidate.sdp.contains("typ host")) {
                    C1639b.m7921c(C1678h.f11306a, "give up host udp");
                } else if (C1640a.m7922a(this.f11296b.f11319n, 8) && iceCandidate.sdp.contains("typ srflx")) {
                    C1639b.m7921c(C1678h.f11306a, "give up srflx udp");
                } else if (this.f11296b.f11318m) {
                    JSONObject jSONObject = new JSONObject();
                    C1678h.m8020b(jSONObject, IjkMediaMeta.IJKM_KEY_TYPE, "candidate");
                    C1678h.m8020b(jSONObject, "label", Integer.valueOf(iceCandidate.sdpMLineIndex));
                    C1678h.m8020b(jSONObject, "id", iceCandidate.sdpMid);
                    C1678h.m8020b(jSONObject, "candidate", iceCandidate.sdp);
                    this.f11296b.m8019b(this.f11296b.f11307b.m8091a(4001, jSONObject, this.f11296b.f11314i), jSONObject.toString());
                } else {
                    this.f11296b.f11309d.add(iceCandidate);
                }
            }
        });
    }

    void m8042a(final String str, final String str2) {
        this.f11308c.execute(new Runnable(this) {
            final /* synthetic */ C1678h f11299c;

            public void run() {
                this.f11299c.m8029f();
                this.f11299c.m8019b(this.f11299c.f11307b.m8090a(4004, str2, str), "JOIN_KEY");
            }
        });
    }

    void m8043a(@Nullable String str, @Nullable Map<String, Object> map, @Nullable String str2, boolean z) {
        final HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        final boolean z2 = z;
        final String str3 = str2;
        final String str4 = str;
        this.f11308c.execute(new Runnable(this) {
            final /* synthetic */ C1678h f11278e;

            public void run() {
                hashMap.put("nt", C1643b.m7939a());
                hashMap.put("isp", C1643b.m7942c());
                hashMap.put("locs", C1643b.m7943d());
                hashMap.put("suctalk", Boolean.valueOf(z2));
                this.f11278e.f11307b.m8090a(4005, this.f11278e.m8013a(hashMap, str3), TextUtils.isEmpty(str4) ? this.f11278e.f11314i : str4);
                this.f11278e.m8031g();
            }
        });
    }

    void m8041a(final String str) {
        this.f11308c.execute(new Runnable(this) {
            final /* synthetic */ C1678h f11280b;

            public void run() {
                this.f11280b.f11307b.m8090a(4014, str, this.f11280b.f11314i);
            }
        });
    }

    void m8049c() {
        this.f11308c.execute(new Runnable(this) {
            final /* synthetic */ C1678h f11281a;

            {
                this.f11281a = r1;
            }

            public void run() {
                this.f11281a.f11307b.m8090a(4010, this.f11281a.f11316k, this.f11281a.f11314i);
            }
        });
    }

    private String m8013a(Map<String, Object> map, @Nullable String str) {
        map.put("session", this.f11316k);
        map.put("quality", Integer.valueOf(this.f11320o));
        if (!TextUtils.isEmpty(str)) {
            map.put("payload", str);
        }
        return new JSONObject(map).toString();
    }

    private void m8027e() {
        C1639b.m7921c(f11306a, "class FacechatRtcClient :[setRemoteSDPReceived] mRemoteSDPReceived=" + this.f11318m);
        if (!this.f11318m) {
            this.f11318m = true;
            for (IceCandidate a : this.f11309d) {
                m8045a(a);
            }
            this.f11309d.clear();
        }
    }

    private void m8018b(final String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f11308c.execute(new Runnable(this) {
                final /* synthetic */ C1678h f11284b;

                public void run() {
                    this.f11284b.f11307b.m8090a(8005, str, BuildConfig.VERSION_NAME);
                }
            });
        }
    }

    private void m8022c(String str) {
        final String a = C1648f.m7954a(str);
        this.f11308c.execute(new Runnable(this) {
            final /* synthetic */ C1678h f11286b;

            public void run() {
                this.f11286b.f11307b.m8090a(4007, a, this.f11286b.f11314i);
            }
        });
    }

    private void m8019b(String str, String str2) {
        String a = C1648f.m7954a(str2);
        if (this.f11310e.get(a) == null) {
            this.f11310e.put(a, new Pair(Long.valueOf(SystemClock.elapsedRealtime()), str));
        }
    }

    private void m8025d(String str) {
        this.f11310e.remove(str);
    }

    private void m8029f() {
        if (this.f11321p != null) {
            this.f11321p.purge();
            this.f11321p.cancel();
            this.f11321p = null;
        }
        this.f11321p = new Timer();
        this.f11321p.scheduleAtFixedRate(new C16694(this), 4000, 2000);
    }

    public void mo4065a(JSONObject jSONObject, int i) {
        String str = null;
        String optString = jSONObject.optString("extras");
        String optString2 = jSONObject.optString(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA);
        switch (i) {
            case 4004:
                if (this.f11311f != null && this.f11314i.equalsIgnoreCase(optString)) {
                    this.f11311f.mo4050b(this.f11314i, optString2);
                    return;
                }
                return;
            case 4005:
                if (this.f11311f != null && this.f11314i.equalsIgnoreCase(optString)) {
                    this.f11311f.mo4047a(this.f11314i, optString2);
                    return;
                }
                return;
            case 4007:
                m8025d(optString2);
                return;
            case 4014:
                if (this.f11311f != null && this.f11314i.equalsIgnoreCase(optString)) {
                    this.f11311f.mo4051c(this.f11314i, optString2);
                    return;
                }
                return;
            case 8004:
            case 8008:
                m8047b();
                return;
            default:
                JSONObject jSONObject2;
                boolean optBoolean;
                try {
                    JSONObject jSONObject3;
                    if (TextUtils.isEmpty(optString2)) {
                        jSONObject3 = null;
                    } else {
                        jSONObject3 = new JSONObject(optString2);
                    }
                    jSONObject2 = jSONObject3;
                } catch (Throwable e) {
                    C1639b.m7917a(f11306a, "read data json fail", e);
                    jSONObject2 = null;
                }
                if (jSONObject2 != null) {
                    str = jSONObject2.optString(IjkMediaMeta.IJKM_KEY_TYPE);
                    optBoolean = jSONObject2.optBoolean("ack", false);
                } else {
                    optBoolean = false;
                }
                if (optBoolean) {
                    m8018b(jSONObject2.optString("id"));
                }
                if (jSONObject2 != null) {
                    SessionDescription sessionDescription;
                    switch (i) {
                        case 4001:
                            if ("candidate".equals(str) && optString.equals(this.f11314i) && this.f11317l == C1675b.CONNECTED) {
                                IceCandidate iceCandidate = new IceCandidate(jSONObject2.optString("id"), jSONObject2.optInt("label"), jSONObject2.optString("candidate"));
                                if (this.f11311f != null) {
                                    this.f11311f.mo4048a(iceCandidate);
                                }
                                m8022c(optString2);
                                return;
                            }
                            return;
                        case 4002:
                            if (!this.f11315j && optString.equals(this.f11314i) && this.f11317l == C1675b.CONNECTED) {
                                sessionDescription = new SessionDescription(Type.fromCanonicalForm("offer"), jSONObject2.optString("sdp"));
                                if (this.f11311f != null) {
                                    this.f11311f.mo4049a(this.f11315j, sessionDescription);
                                }
                                this.f11318m = false;
                                m8022c(optString2);
                                return;
                            }
                            return;
                        case 4003:
                            if (this.f11315j && optString.equals(this.f11314i) && this.f11317l == C1675b.CONNECTED) {
                                sessionDescription = new SessionDescription(Type.fromCanonicalForm(str), jSONObject2.optString("sdp"));
                                if (this.f11311f != null) {
                                    this.f11311f.mo4049a(this.f11315j, sessionDescription);
                                }
                                m8027e();
                                m8022c(optString2);
                                return;
                            }
                            return;
                        case 4006:
                            synchronized (this.f11313h) {
                                this.f11317l = C1675b.CONNECTED;
                                this.f11314i = optString;
                            }
                            return;
                        case 4008:
                            if (this.f11312g.m7848a() == 11) {
                                C1639b.m7921c(f11306a, "wtf " + this.f11312g.m7848a());
                                return;
                            }
                            this.f11315j = jSONObject2.optBoolean("request");
                            this.f11316k = jSONObject2.optString("session");
                            this.f11319n = (long) jSONObject2.optInt("noIces");
                            this.f11320o = jSONObject2.optInt("quality");
                            C1730a c1730a = new C1730a(jSONObject2.optString("turnUrl"), jSONObject2.optString("turnUser"), jSONObject2.optString("turnSecKey"), this.f11320o, this.f11319n);
                            if (this.f11311f != null) {
                                this.f11311f.mo4046a(new C1731c(this.f11315j, this.f11316k, this.f11314i, c1730a));
                            }
                            this.f11312g.m7820d((int) AidTask.WHAT_LOAD_AID_SUC);
                            return;
                        case 5001:
                            String optString3 = jSONObject2.optString("id");
                            Object optString4 = jSONObject2.optString("text");
                            if (!TextUtils.isEmpty(optString3)) {
                                m8018b(optString3);
                            }
                            if (!TextUtils.isEmpty(optString4) && this.f11311f != null) {
                                this.f11311f.mo4052d(optString3, optString4);
                                return;
                            }
                            return;
                        case 8006:
                            int optInt = jSONObject2.optInt(SelectCountryActivity.EXTRA_COUNTRY_CODE);
                            str = jSONObject2.optString("msg");
                            if (this.f11311f != null) {
                                this.f11311f.mo4045a(optInt, str);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
                return;
        }
    }

    public void mo4064a(int i) {
        synchronized (this.f11313h) {
            if (this.f11311f != null) {
                this.f11311f.mo4044a(i);
            }
        }
    }

    private void m8031g() {
        this.f11319n = 0;
        if (this.f11321p != null) {
            this.f11321p.cancel();
            this.f11321p.purge();
        }
        this.f11318m = false;
        this.f11314i = BuildConfig.VERSION_NAME;
        synchronized (this.f11313h) {
            this.f11317l = C1675b.INIT;
        }
        this.f11309d.clear();
        this.f11310e.clear();
    }
}
