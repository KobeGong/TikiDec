package com.buddy.tiki.im;

import android.support.v4.util.ArrayMap;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.helper.GsonHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.util.LooperExecutor;
import com.buddy.tiki.util.NetworkUtil;
import com.geekint.flying.im.lib.msg.RiverMessage;
import com.geekint.flying.im.tool.IMListener;
import com.igexin.download.Downloads;
import de.tavendo.autobahn.WebSocket.WebSocketConnectionObserver.WebSocketCloseNotification;
import de.tavendo.autobahn.WebSocketConnectionHandler;
import io.reactivex.observers.DisposableObserver;
import java.util.Map.Entry;
import java.util.Random;
import org.bytedeco.javacpp.swresample;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class TopIM {
    private static final TikiLog f870a = TikiLog.getInstance("TopIMOUT");
    private final Object f871b = new Object();
    private final LooperExecutor f872c;
    private WebSocketObserver f873d;
    private String f874e;
    private WebSocketConnectionState f875f;
    private long f876g = 0;
    private DisposableObserver<Long> f877h;
    private TopIMEvents f878i;
    private com.geekint.flying.im.tool.TopIM f879j;
    private Random f880k = new Random();
    private Object f881l = new Object();

    public interface TopIMEvents {
        void onWebSocketClose();

        void onWebSocketMessage(JSONObject jSONObject);
    }

    class C03991 implements IMListener {
        final /* synthetic */ TopIM f863a;

        C03991(TopIM this$0) {
            this.f863a = this$0;
        }

        public void onMessage(RiverMessage riverMessage) {
            this.f863a.f873d.onTextMessage(GsonHelper.getInstance().getGsonInstance().toJson((Object) riverMessage));
        }

        public void onOpen() {
            this.f863a.f873d.onOpen();
        }

        public void onClose(int i, String s) {
            this.f863a.f875f = WebSocketConnectionState.CLOSED;
            if (NetworkUtil.getConnectivityStatus(ChatApp.getInstance()) != 0) {
                this.f863a.f872c.execute(TopIM$1$$Lambda$1.lambdaFactory$(this), (this.f863a.f880k.nextInt(8) + 1) * PointerIconCompat.TYPE_DEFAULT);
            }
            this.f863a.f873d.onClose(WebSocketCloseNotification.NORMAL, s);
        }

        /* synthetic */ void m246b() {
            if (!TextUtils.isEmpty(this.f863a.f874e)) {
                TopIM.f870a.m261d("onClose:reconnect");
                this.f863a.connect(this.f863a.f874e, false);
            }
        }

        public void onKickout() {
            TopIM.f870a.m261d("onKickout");
        }

        public void onError(Throwable throwable) {
            TopIM.f870a.m264e("onError:", throwable);
            this.f863a.f875f = WebSocketConnectionState.ERROR;
            if (NetworkUtil.getConnectivityStatus(ChatApp.getInstance()) != 0) {
                this.f863a.f872c.execute(TopIM$1$$Lambda$2.lambdaFactory$(this), (this.f863a.f880k.nextInt(15) + 5) * PointerIconCompat.TYPE_DEFAULT);
            }
        }

        /* synthetic */ void m245a() {
            if (!TextUtils.isEmpty(this.f863a.f874e)) {
                TopIM.f870a.m261d("onError:reconnect");
                this.f863a.connect(this.f863a.f874e, false);
            }
        }
    }

    static /* synthetic */ class C04002 {
        static final /* synthetic */ int[] f864a = new int[WebSocketConnectionState.values().length];

        static {
            try {
                f864a[WebSocketConnectionState.CLOSED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public enum WebSocketConnectionState {
        NEW,
        CONNECTED,
        CLOSED,
        ERROR,
        CONNECTING
    }

    private class WebSocketObserver extends WebSocketConnectionHandler {
        final /* synthetic */ TopIM f869a;

        private WebSocketObserver(TopIM topIM) {
            this.f869a = topIM;
        }

        public void onOpen() {
            TopIM.f870a.m261d("onOpen");
            this.f869a.f876g = System.currentTimeMillis();
            this.f869a.f875f = WebSocketConnectionState.CONNECTED;
        }

        public void onClose(WebSocketCloseNotification code, String reason) {
            TopIM.f870a.m261d("onClose : " + reason + " code : " + code);
            this.f869a.f872c.execute(TopIM$WebSocketObserver$$Lambda$1.lambdaFactory$(this));
        }

        /* synthetic */ void m247a() {
            this.f869a.f878i.onWebSocketClose();
        }

        public void onTextMessage(String payload) {
            if (TextUtils.isEmpty(payload)) {
                TopIM.f870a.m263e("onTextMessage: message is empty");
                return;
            }
            TopIM.f870a.m261d("onTextMessage: " + payload);
            try {
                JSONObject jsonObject = new JSONObject(payload);
                int cmd = jsonObject.getInt("cmd");
                this.f869a.f876g = System.currentTimeMillis();
                switch (cmd) {
                    case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                        TopIM.f870a.m263e("onTextMessage: get error from server");
                        return;
                    case 4002:
                        this.f869a.m254b();
                        return;
                    case 4003:
                        TopIM.f870a.m261d("onTextMessage: server pong");
                        return;
                    case 4004:
                        TopIM.f870a.m261d("onTextMessage: kick out");
                        return;
                    default:
                        this.f869a.f872c.execute(TopIM$WebSocketObserver$$Lambda$2.lambdaFactory$(this, jsonObject));
                        return;
                }
            } catch (JSONException e) {
                TopIM.f870a.m263e("onTextMessage: error " + e.getMessage());
            }
            TopIM.f870a.m263e("onTextMessage: error " + e.getMessage());
        }

        /* synthetic */ void m248a(JSONObject jsonObject) {
            if (this.f869a.f875f == WebSocketConnectionState.CONNECTED) {
                this.f869a.f878i.onWebSocketMessage(jsonObject);
            }
        }
    }

    public TopIM(LooperExecutor executor, TopIMEvents events) {
        this.f872c = executor;
        this.f875f = WebSocketConnectionState.NEW;
        this.f878i = events;
    }

    public boolean isActivie() {
        if (this.f879j != null) {
            return this.f879j.isActive();
        }
        return false;
    }

    public void connect(String wsUrl) {
        connect(wsUrl, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(java.lang.String r13, boolean r14) {
        /*
        r12 = this;
        r7 = 0;
        r12.m256c();
        r11 = android.net.Uri.parse(r13);
        r0 = 0;
        r5 = 63;
        r5 = r13.indexOf(r5);
        r1 = r13.substring(r0, r5);
        r0 = "u";
        r2 = r11.getQueryParameter(r0);
        r0 = "s";
        r3 = r11.getQueryParameter(r0);
        r0 = "domain";
        r4 = r11.getQueryParameter(r0);
        r0 = android.text.TextUtils.isEmpty(r1);
        if (r0 != 0) goto L_0x003d;
    L_0x002b:
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 != 0) goto L_0x003d;
    L_0x0031:
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 != 0) goto L_0x003d;
    L_0x0037:
        r0 = android.text.TextUtils.isEmpty(r4);
        if (r0 == 0) goto L_0x0074;
    L_0x003d:
        r0 = f870a;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Error wsUrl:url:";
        r5 = r5.append(r6);
        r5 = r5.append(r1);
        r6 = " u:";
        r5 = r5.append(r6);
        r5 = r5.append(r2);
        r6 = " s:";
        r5 = r5.append(r6);
        r5 = r5.append(r3);
        r6 = " domain:";
        r5 = r5.append(r6);
        r5 = r5.append(r4);
        r5 = r5.toString();
        r0.m263e(r5);
    L_0x0073:
        return;
    L_0x0074:
        if (r14 != 0) goto L_0x0084;
    L_0x0076:
        r0 = r12.isActivie();
        if (r0 == 0) goto L_0x0084;
    L_0x007c:
        r0 = f870a;
        r5 = "has already connected";
        r0.m263e(r5);
        goto L_0x0073;
    L_0x0084:
        r5 = r12.f881l;
        monitor-enter(r5);
        if (r14 != 0) goto L_0x00a1;
    L_0x0089:
        r0 = r12.f875f;	 Catch:{ all -> 0x009e }
        r6 = com.buddy.tiki.im.TopIM.WebSocketConnectionState.CONNECTED;	 Catch:{ all -> 0x009e }
        if (r0 == r6) goto L_0x0095;
    L_0x008f:
        r0 = r12.f875f;	 Catch:{ all -> 0x009e }
        r6 = com.buddy.tiki.im.TopIM.WebSocketConnectionState.ERROR;	 Catch:{ all -> 0x009e }
        if (r0 != r6) goto L_0x00a1;
    L_0x0095:
        r0 = f870a;	 Catch:{ all -> 0x009e }
        r6 = "connecting or connected";
        r0.m263e(r6);	 Catch:{ all -> 0x009e }
        monitor-exit(r5);	 Catch:{ all -> 0x009e }
        goto L_0x0073;
    L_0x009e:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x009e }
        throw r0;
    L_0x00a1:
        r0 = com.buddy.tiki.im.TopIM.WebSocketConnectionState.CONNECTING;	 Catch:{ all -> 0x009e }
        r12.f875f = r0;	 Catch:{ all -> 0x009e }
        monitor-exit(r5);	 Catch:{ all -> 0x009e }
        r0 = f870a;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "connect: start ";
        r5 = r5.append(r6);
        r5 = r5.append(r13);
        r5 = r5.toString();
        r0.m261d(r5);
        r12.f874e = r13;
        r0 = new com.buddy.tiki.im.TopIM$WebSocketObserver;
        r0.<init>();
        r12.f873d = r0;
        r0 = r12.f879j;
        if (r0 == 0) goto L_0x00d2;
    L_0x00cb:
        r0 = r12.f879j;
        r0.close();
        r12.f879j = r7;
    L_0x00d2:
        r0 = new com.geekint.flying.im.tool.TopIM;
        r0.<init>();
        r12.f879j = r0;
        r0 = r12.f879j;	 Catch:{ Exception -> 0x00e9 }
        r5 = 0;
        r6 = 0;
        r7 = 2;
        r8 = 60;
        r9 = new com.buddy.tiki.im.TopIM$1;	 Catch:{ Exception -> 0x00e9 }
        r9.<init>(r12);	 Catch:{ Exception -> 0x00e9 }
        r0.connect(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e9 }
        goto L_0x0073;
    L_0x00e9:
        r10 = move-exception;
        r0 = f870a;
        r5 = r10.getMessage();
        r0.m263e(r5);
        goto L_0x0073;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.im.TopIM.connect(java.lang.String, boolean):void");
    }

    public void send(String jsonString) {
        switch (C04002.f864a[this.f875f.ordinal()]) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                f870a.m263e("WebSocket send() in error or closed state : " + jsonString);
                return;
            default:
                synchronized (this.f871b) {
                    this.f879j.sendMessage((RiverMessage) GsonHelper.getInstance().getGsonInstance().fromJson(jsonString, RiverMessage.class));
                }
                return;
        }
    }

    public void send(int cmd, String message, String from, String extras) {
        JSONObject json = new JSONObject();
        try {
            json.put("cmd", cmd).put("data", message).put("from", from + "@facechat.im").put(Downloads.COLUMN_EXTRAS, extras);
            String msg = json.toString();
            f870a.m261d("send: message " + msg);
            send(msg);
        } catch (JSONException e) {
            f870a.m263e("send: error " + e.getMessage());
        }
    }

    public void send(int cmd) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd", cmd);
            f870a.m261d("send: cmd = " + cmd);
            send(jsonObject.toString());
        } catch (JSONException e) {
            f870a.m263e("send: error " + e.getMessage());
        }
    }

    public void send(ArrayMap<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        for (Entry<String, Object> entry : map.entrySet()) {
            try {
                jsonObject.put((String) entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                f870a.m264e("send: map ", e);
            }
        }
        f870a.m261d("send: " + jsonObject.toString());
        send(jsonObject.toString());
    }

    public void disconnect() {
        if (!(this.f877h == null || this.f877h.isDisposed())) {
            this.f877h.dispose();
            this.f877h = null;
        }
        if (this.f879j != null) {
            this.f879j.close();
            this.f875f = WebSocketConnectionState.CLOSED;
            this.f879j = null;
        }
    }

    private void m254b() {
        if (this.f879j != null) {
            send(4003);
        }
    }

    private void m256c() {
        if (!this.f872c.checkOnLooperThread()) {
            throw new IllegalStateException("WebSocket method is not called on valid thread");
        }
    }

    public WebSocketConnectionState getState() {
        return this.f875f;
    }

    public void setState(WebSocketConnectionState mState) {
        this.f875f = mState;
    }
}
