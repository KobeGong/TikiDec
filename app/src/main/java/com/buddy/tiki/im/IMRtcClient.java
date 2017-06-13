package com.buddy.tiki.im;

import android.support.v4.util.ArrayMap;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.event.UserEvent.DeleteFriendEvent;
import com.buddy.tiki.helper.GsonHelper;
import com.buddy.tiki.helper.MD5Helper;
import com.buddy.tiki.im.TopIM.TopIMEvents;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.constant.DomainType;
import com.buddy.tiki.model.constant.MsgDataType;
import com.buddy.tiki.model.im.VideoMessage;
import com.buddy.tiki.model.resource.Border;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.protocol.im.AppRTCClient;
import com.buddy.tiki.protocol.im.AppRTCClient.SignalingEvents;
import com.buddy.tiki.protocol.im.AppRTCClient.SignalingParameters;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.service.base.Foreground;
import com.buddy.tiki.service.base.Foreground.Listener;
import com.buddy.tiki.util.IncomingCallManager;
import com.buddy.tiki.util.LooperExecutor;
import com.buddy.tiki.util.NetworkUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.UserChatRealmHelper;
import com.igexin.download.Downloads;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.netty.handler.codec.rtsp.RtspHeaders.Values;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.avcodec.AVCodecContext;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;
import org.webrtc.SessionDescription.Type;

public class IMRtcClient implements TopIMEvents, AppRTCClient, Listener {
    private static final TikiLog f837a = TikiLog.getInstance("IMRtcClient");
    private boolean f838b;
    private String f839c;
    private String f840d;
    private SignalingEvents f841e;
    private TopIM f842f;
    private LooperExecutor f843g;
    private ConnectionState f844h;
    private long f845i;
    private int f846j;
    private String f847k;
    private String f848l;
    private String f849m;
    private Runnable f850n;
    private boolean f851o;
    private List<IceCandidate> f852p;
    private IceCandidate f853q;
    private volatile boolean f854r;
    private volatile boolean f855s;
    private DisposableObserver<Long> f856t;

    class C03971 implements Runnable {
        final /* synthetic */ IMRtcClient f832a;

        C03971(IMRtcClient this$0) {
            this.f832a = this$0;
        }

        public void run() {
            if (this.f832a.f842f == null) {
                this.f832a.f842f = new TopIM(this.f832a.f843g, this.f832a);
            }
            if (this.f832a.f842f.isActivie()) {
                IMRtcClient.f837a.m261d("Check IMRtcClient Connection: active");
            } else if (NetworkUtil.getConnectivityStatus(ChatApp.getInstance()) == 0) {
                IMRtcClient.f837a.m261d("Check IMRtcClient Connection: no network");
            } else {
                IMRtcClient.f837a.m261d("Check IMRtcClient Connection:try connect");
                DataLayer.getInstance().getAppManager().configInfoRequest().compose(SchedulersCompat.applyIoSchedulers()).filter(IMRtcClient$1$$Lambda$1.lambdaFactory$()).doOnNext(IMRtcClient$1$$Lambda$2.lambdaFactory$()).subscribe(IMRtcClient$1$$Lambda$3.lambdaFactory$(), IMRtcClient$1$$Lambda$4.lambdaFactory$());
            }
        }

        static /* synthetic */ boolean m214c(ConfigInfo configInfo) throws Exception {
            return configInfo != null;
        }

        static /* synthetic */ void m213b(ConfigInfo configInfo) throws Exception {
            if (configInfo != null) {
                IMRtcClient.getInstance().connect(configInfo.getWsuris()[0] + String.format("?u=%s&s=%s&domain=%s&v=4", new Object[]{TopConfig.f408a, TopConfig.f409b, DomainType.BIZ}));
            }
        }

        static /* synthetic */ void m211a(ConfigInfo configInfo) throws Exception {
        }

        static /* synthetic */ void m212a(Throwable throwable) throws Exception {
        }
    }

    class C03982 extends DisposableObserver<Long> {
        int f833a = 0;
        final /* synthetic */ IMRtcClient f834b;

        C03982(IMRtcClient this$0) {
            this.f834b = this$0;
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
        }

        public void onNext(Long aLong) {
            this.f834b.sendLocalIceCandidateInternal(this.f834b.f853q);
            int i = this.f833a;
            this.f833a = i + 1;
            if (i == 5) {
                dispose();
            }
        }
    }

    private enum ConnectionState {
        NEW,
        CONNECTED,
        CLOSED,
        ERROR,
        CONNECTING
    }

    private static class SingletonHolder {
        private static final IMRtcClient f836a = new IMRtcClient();
    }

    private IMRtcClient() {
        this.f850n = new C03971(this);
        this.f851o = false;
        this.f852p = new ArrayList();
        this.f853q = null;
        this.f854r = false;
        this.f855s = false;
    }

    public static IMRtcClient getInstance() {
        return SingletonHolder.f836a;
    }

    public static void init(LooperExecutor executor) {
        getInstance().m220a(executor);
    }

    private void m227d() {
        if (!(this.f856t == null || this.f856t.isDisposed())) {
            this.f856t.dispose();
        }
        this.f856t = new C03982(this);
    }

    public void release() {
        m230e();
    }

    private void m230e() {
        if (!(this.f856t == null || this.f856t.isDisposed())) {
            this.f856t.dispose();
        }
        Foreground.get().removeListener(this);
        getInstance().disconnectRemote();
    }

    private void m220a(LooperExecutor executor) {
        if (executor == null) {
            throw new IllegalArgumentException("null LooperExecutor");
        }
        this.f843g = executor;
        this.f844h = ConnectionState.NEW;
        this.f842f = new TopIM(executor, this);
        this.f843g.requestStart();
        this.f843g.execute(this.f850n);
        this.f843g.scheduleAtFixedRate(this.f850n, 6000);
        Foreground.get().addListener(this);
    }

    public void onBecameForeground() {
        this.f843g.scheduleAtFixedRate(this.f850n, 6000);
    }

    public void onBecameBackground() {
        this.f843g.cancelScheduledTasks();
    }

    public boolean isActive() {
        if (this.f842f != null) {
            return this.f842f.isActivie();
        }
        return false;
    }

    public void connect(String url) {
        connect(url, false);
    }

    public void connect(String url, boolean forceConnect) {
        if (this.f843g != null) {
            this.f843g.execute(IMRtcClient$$Lambda$1.lambdaFactory$(this, url, forceConnect));
        }
    }

    /* synthetic */ void m235a(String url, boolean forceConnect) {
        this.f842f.connect(url, forceConnect);
        this.f844h = ConnectionState.NEW;
    }

    public void startTrackRelay() {
        if (this.f838b) {
            m227d();
            Observable.interval(500, 500, TimeUnit.MILLISECONDS, Schedulers.from(this.f843g)).subscribe(this.f856t);
        }
    }

    /* synthetic */ void m240b(SessionDescription sdp) {
        sendOfferSdpInternal(sdp);
    }

    public void sendOfferSdp(SessionDescription sdp) {
        this.f843g.execute(IMRtcClient$$Lambda$2.lambdaFactory$(this, sdp));
    }

    public void sendAnswerSdp(SessionDescription sdp) {
        this.f843g.execute(IMRtcClient$$Lambda$3.lambdaFactory$(this, sdp));
    }

    /* synthetic */ void m237a(SessionDescription sdp) {
        sendAnswerSdpInternal(sdp);
        setRemoteSDPReceived();
    }

    public void setRemoteSDPReceived() {
        if (!this.f851o) {
            this.f851o = true;
            for (IceCandidate candidate : this.f852p) {
                sendLocalIceCandidateInternal(candidate);
            }
            this.f852p.clear();
        }
    }

    public void sendLocalIceCandidate(IceCandidate candidate) {
        if (this.f843g != null) {
            this.f843g.execute(IMRtcClient$$Lambda$4.lambdaFactory$(this, candidate));
        }
    }

    /* synthetic */ void m236a(IceCandidate candidate) {
        if (this.f844h != ConnectionState.CONNECTED) {
            f837a.m263e("Sending ICE candidate in non connected state.");
        } else if (this.f851o) {
            sendLocalIceCandidateInternal(candidate);
        } else {
            this.f852p.add(candidate);
        }
    }

    public void disconnectRemote() {
        try {
            this.f843g.execute(IMRtcClient$$Lambda$5.lambdaFactory$(this));
            this.f843g.requestStop();
        } catch (Exception e) {
        }
    }

    private void m231f() {
        this.f844h = ConnectionState.CLOSED;
        if (this.f842f != null) {
            this.f842f.disconnect();
        }
    }

    private void m226c(String errorMessage) {
        f837a.m263e(errorMessage);
    }

    public void sendOfferSdpInternal(SessionDescription sdp) {
        try {
            JSONObject data = new JSONObject();
            data.put("sdp", sdp.description).put("type", "offer");
            this.f842f.send(4013, data.toString(), TopConfig.f408a, this.f839c);
            this.f851o = false;
        } catch (JSONException e) {
            f837a.m263e("sendOfferSdp: error " + e.getMessage());
        }
    }

    public void sendAnswerSdpInternal(SessionDescription sdp) {
        try {
            JSONObject data = new JSONObject();
            data.put("sdp", sdp.description).put("type", "answer");
            this.f842f.send(4014, data.toString(), TopConfig.f408a, this.f839c);
        } catch (JSONException e) {
            f837a.m263e("sendAnswerSdp error : " + e.getMessage());
        }
    }

    public void sendLocalIceCandidateInternal(IceCandidate candidate) {
        f837a.m261d("sendLocalIceCandidateInternal");
        if (candidate != null) {
            if ((this.f845i & 2) != 2 || !candidate.sdp.contains("typ host")) {
                if ((this.f845i & 8) != 8 || !candidate.sdp.contains("typ srflx")) {
                    IceCandidate lastCandidate = null;
                    if (this.f852p.size() > 0) {
                        lastCandidate = (IceCandidate) this.f852p.get(this.f852p.size() - 1);
                    }
                    if (!(!this.f838b || lastCandidate == null || lastCandidate.sdp.contains("relay") || !candidate.sdp.contains("relay") || this.f853q == null || this.f854r)) {
                        this.f853q = candidate;
                        m227d();
                        Observable.interval(500, 500, TimeUnit.MILLISECONDS, Schedulers.from(this.f843g)).subscribe(this.f856t);
                    }
                    if (this.f842f == null) {
                        f837a.m263e("sendLocalIceCandidateInternal: the socketConnection is lost");
                        return;
                    }
                    try {
                        JSONObject data = new JSONObject();
                        data.put("type", "candidate");
                        data.put("label", candidate.sdpMLineIndex);
                        data.put("id", candidate.sdpMid);
                        data.put("candidate", candidate.sdp);
                        this.f842f.send(4012, data.toString(), TopConfig.f408a, this.f839c);
                    } catch (JSONException e) {
                        f837a.m263e("sendLocalIceCandidate error: " + e.getMessage());
                    }
                }
            }
        }
    }

    public void sendJoinMsg(String sessionId) {
        f837a.m261d("sendJoinMsg:" + sessionId);
        if (this.f843g != null) {
            this.f843g.execute(IMRtcClient$$Lambda$6.lambdaFactory$(this, sessionId));
        }
    }

    /* synthetic */ void m239b(String sessionId) {
        if (this.f842f != null) {
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("cmd", Integer.valueOf(4017));
            arrayMap.put(Downloads.COLUMN_EXTRAS, sessionId);
            arrayMap.put("from", TopConfig.f408a + "@facechat.im");
            this.f842f.send(arrayMap);
        }
    }

    public void sendLeaveMsg() {
        f837a.m261d("sendLeaveMsg: ");
        this.f840d = BuildConfig.VERSION_NAME;
        this.f845i = 0;
        this.f846j = 0;
        if (!(this.f856t == null || this.f856t.isDisposed())) {
            this.f856t.dispose();
        }
        try {
            this.f843g.execute(IMRtcClient$$Lambda$7.lambdaFactory$(this));
        } catch (Exception e) {
        }
    }

    /* synthetic */ void m238b() {
        if (!(this.f842f == null || TextUtils.isEmpty(this.f839c) || this.f844h != ConnectionState.CONNECTED)) {
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("cmd", Integer.valueOf(4018));
            arrayMap.put(Downloads.COLUMN_EXTRAS, this.f839c);
            arrayMap.put("from", TopConfig.f408a + "@facechat.im");
            this.f842f.send(arrayMap);
        }
        this.f839c = BuildConfig.VERSION_NAME;
        this.f844h = ConnectionState.NEW;
    }

    private void m229d(String msgId) {
        if (this.f843g != null) {
            this.f843g.execute(IMRtcClient$$Lambda$8.lambdaFactory$(this, msgId));
        }
    }

    /* synthetic */ void m234a(String msgId) {
        if (this.f842f != null) {
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("cmd", Integer.valueOf(4011));
            arrayMap.put("data", msgId);
            this.f842f.send(arrayMap);
        }
    }

    private void m218a(int dataType, JSONObject msg) {
        String callId;
        String uid;
        JSONObject friendJSON;
        String id;
        User fromUser;
        JSONObject callerJSON;
        JSONObject apns;
        TikiUser tikiUser;
        long ctime;
        String videoId;
        switch (dataType) {
            case MsgDataType.MATCH /*232*/:
                this.f838b = msg.optBoolean("request");
                this.f839c = msg.optString("roomId");
                this.f847k = msg.optString("turnSecKey");
                this.f848l = msg.optString("turnUrl");
                this.f849m = msg.optString("turnUser");
                this.f845i = msg.optLong("noIces");
                this.f846j = msg.optInt("quality");
                String matchUserText = msg.optString("matchUser");
                if (!TextUtils.isEmpty(matchUserText) && this.f844h != ConnectionState.CONNECTING) {
                    this.f844h = ConnectionState.CONNECTING;
                    User user = (User) GsonHelper.getInstance().getGsonInstance().fromJson(matchUserText, User.class);
                    this.f840d = user.getUid();
                    if (this.f841e != null) {
                        this.f841e.onMatch(new SignalingParameters(this.f839c, this.f838b, user, this.f847k, this.f848l, this.f849m), this.f846j);
                    }
                    sendJoinMsg(this.f839c);
                    return;
                }
                return;
            case MsgDataType.APPLY_FRIEND_MSG /*233*/:
                String applyId = msg.optString("applyId");
                JSONObject applyUser = msg.optJSONObject("user");
                String applyUid = null;
                if (applyUser != null) {
                    applyUid = applyUser.optString(Oauth2AccessToken.KEY_UID);
                }
                if (!TextUtils.isEmpty(applyId) && !TextUtils.isEmpty(applyUid)) {
                    PreferenceUtil.setApplyNumber(PreferenceUtil.getApplyNumber() + 1);
                    if (this.f841e != null) {
                        this.f841e.onApplyFriendRequest(applyId, applyUid);
                        return;
                    }
                    return;
                }
                return;
            case MsgDataType.ACCEPT_FRIEND_MSG /*234*/:
                String acceptFriendApplyId = msg.optString("applyId");
                String acceptFriendUid = msg.optString(Oauth2AccessToken.KEY_UID);
                if (!TextUtils.isEmpty(acceptFriendApplyId) && !TextUtils.isEmpty(acceptFriendUid)) {
                    DataLayer.getInstance().getFollowManager().afterAcceptingFriend(acceptFriendApplyId, acceptFriendUid);
                    return;
                }
                return;
            case MsgDataType.GIFT /*235*/:
                String giftText = msg.optString("gift");
                if (!TextUtils.isEmpty(giftText)) {
                    Gift gift = (Gift) GsonHelper.getInstance().getGsonInstance().fromJson(giftText, Gift.class);
                    if (this.f841e != null) {
                        this.f841e.onGiftReceived(gift);
                        return;
                    }
                    return;
                }
                return;
            case MsgDataType.BORDER /*236*/:
                String borderText = msg.optString("border");
                Border border = null;
                if (!TextUtils.isEmpty(borderText)) {
                    border = (Border) GsonHelper.getInstance().getGsonInstance().fromJson(borderText, Border.class);
                }
                if (this.f841e != null) {
                    this.f841e.onBorderReceived(border);
                    return;
                }
                return;
            case MsgDataType.CALL_FRIEND_MSG /*237*/:
                this.f838b = false;
                f837a.m261d("CALL_FRIEND_MSG:237");
                callId = msg.optString("callId");
                String nick = BuildConfig.VERSION_NAME;
                uid = BuildConfig.VERSION_NAME;
                friendJSON = msg.optJSONObject("caller");
                if (friendJSON != null) {
                    nick = friendJSON.optString("nick");
                    uid = friendJSON.optString(Oauth2AccessToken.KEY_UID);
                }
                if (this.f841e != null) {
                    this.f841e.onFriendRequestCall(callId, nick, uid);
                    return;
                }
                return;
            case MsgDataType.CALL_REJECT_MSG /*239*/:
                this.f838b = true;
                f837a.m261d("CALL_REJECT_MSG:239");
                id = msg.optString("id");
                fromUser = null;
                callerJSON = msg.optJSONObject("from");
                if (callerJSON != null) {
                    fromUser = new User();
                    fromUser.setNick(callerJSON.optString("nick"));
                    fromUser.setUid(callerJSON.optString(Oauth2AccessToken.KEY_UID));
                    fromUser.setGender(callerJSON.optInt("gender"));
                    fromUser.setRelation(callerJSON.optInt("relation"));
                }
                if (this.f841e != null) {
                    this.f841e.onFriendRejectCall();
                }
                apns = msg.optJSONObject("apns");
                if (apns != null) {
                    uid = apns.optJSONObject("params").optString(Oauth2AccessToken.KEY_UID);
                    if (!TextUtils.isEmpty(uid)) {
                        Realm realm1 = Realm.getDefaultInstance();
                        long now = System.currentTimeMillis();
                        tikiUser = (TikiUser) realm1.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, uid).findFirst();
                        if (tikiUser != null && tikiUser.isLoaded() && tikiUser.isValid()) {
                            realm1.beginTransaction();
                            UserChatRealmHelper.getInstance().insertCallRejectMessage(realm1, uid, id, now, fromUser);
                            realm1.commitTransaction();
                        }
                        realm1.close();
                        return;
                    }
                    return;
                }
                return;
            case MsgDataType.CALL_CLOSE_MSG /*240*/:
                f837a.m261d("CALL_CLOSE_MSG:240");
                callId = BuildConfig.VERSION_NAME;
                apns = msg.optJSONObject("apns");
                if (apns != null) {
                    JSONObject params = apns.optJSONObject("params");
                    if (params != null) {
                        callId = params.optString("callId");
                    }
                }
                if (this.f841e != null) {
                    this.f841e.onFriendCloseCall(callId);
                    return;
                }
                return;
            case MsgDataType.CALL_RECEIVE_MSG /*242*/:
                f837a.m261d("CALL_RECEIVE_MSG:242");
                callId = msg.optString("callId");
                return;
            case MsgDataType.CALL_ACCEPT_MSG /*243*/:
                this.f838b = true;
                f837a.m261d("CALL_ACCEPT_MSG:243");
                callId = msg.optString("callId");
                String turnSecKey = msg.optString("turnSecKey");
                String turnUrl = msg.optString("turnUrl");
                String turnUsr = msg.optString("turnUser");
                this.f839c = msg.optString("roomId");
                this.f845i = msg.optLong("noIces");
                this.f846j = msg.optInt("quality");
                User friend = null;
                friendJSON = msg.optJSONObject("friend");
                if (friendJSON != null) {
                    friend = new User();
                    friend.setNick(friendJSON.optString("nick"));
                    friend.setUid(friendJSON.optString(Oauth2AccessToken.KEY_UID));
                    friend.setGender(friendJSON.optInt("gender"));
                    friend.setRelation(friendJSON.optInt("relation"));
                }
                if (this.f841e != null) {
                    this.f841e.onFriendAcceptCall(callId, friend, this.f839c, turnSecKey, turnUrl, turnUsr, this.f846j);
                    return;
                }
                return;
            case AVCodecContext.FF_PROFILE_H264_HIGH_444_PREDICTIVE /*244*/:
                f837a.m261d("CALL_OFFLINE_MSG:244");
                callId = msg.optString("callId");
                id = msg.optString("id");
                User caller = null;
                callerJSON = msg.optJSONObject("caller");
                if (callerJSON != null) {
                    caller = new User();
                    caller.setNick(callerJSON.optString("nick"));
                    caller.setUid(callerJSON.optString(Oauth2AccessToken.KEY_UID));
                    caller.setGender(callerJSON.optInt("gender"));
                    caller.setRelation(callerJSON.optInt("relation"));
                }
                if (!(caller == null || TextUtils.isEmpty(caller.getUid()))) {
                    Realm friendRealm = Realm.getDefaultInstance();
                    tikiUser = (TikiUser) friendRealm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, caller.getUid()).findFirst();
                    if (tikiUser != null && tikiUser.isLoaded() && tikiUser.isValid()) {
                        friendRealm.beginTransaction();
                        UserChatRealmHelper.getInstance().insertCallOfflineMessage(friendRealm, caller.getUid(), id, System.currentTimeMillis(), caller);
                        friendRealm.commitTransaction();
                    }
                    friendRealm.close();
                }
                IncomingCallManager.getInstance().dismiss(callId);
                return;
            case MsgDataType.DELETE_FRIEND_MSG /*245*/:
                String from = msg.optString("from");
                if (!TextUtils.isEmpty(from)) {
                    User delete = (User) GsonHelper.getInstance().getGsonInstance().fromJson(from, User.class);
                    if (delete != null) {
                        Realm realm = Realm.getDefaultInstance();
                        Transaction lambdaFactory$ = IMRtcClient$$Lambda$9.lambdaFactory$(delete);
                        realm.getClass();
                        realm.executeTransactionAsync(lambdaFactory$, IMRtcClient$$Lambda$10.lambdaFactory$(realm), IMRtcClient$$Lambda$11.lambdaFactory$(realm));
                        EventBus.getDefault().post(new DeleteFriendEvent(delete.getUid()));
                        return;
                    }
                    return;
                }
                return;
            case MsgDataType.PORN_MSG /*250*/:
                this.f841e.onReceivePornMsg(msg.optBoolean("needReport"));
                return;
            case MsgDataType.VIDEO_MSG /*261*/:
                f837a.m261d("VIDEO_MSG:261");
                ctime = msg.optLong("ctime");
                int tikiPrice = msg.optInt("tikiPrice");
                int timeLen = msg.optInt("timelen");
                videoId = msg.optString("videoId");
                String cover = msg.optString("cover");
                fromUser = (User) JSON.parseObject(msg.optString("from"), User.class);
                id = msg.optString("id");
                VideoMessage message = new VideoMessage();
                message.setFrom(fromUser);
                message.setCover(cover);
                message.setTikiPrice(tikiPrice);
                message.setTimelen(timeLen);
                message.setVideoId(videoId);
                message.setId(id);
                message.setCtime(ctime);
                UserChatRealmHelper.getInstance().insertVideoMessage(message);
                return;
            case MsgDataType.EARN_MSG /*262*/:
                f837a.m261d("EARN_MSG:262");
                return;
            case MsgDataType.BUY_VIDEO_MESSAGE /*264*/:
                f837a.m261d("BUY_VIDEO_MESSAGE:264");
                User buyer = (User) JSON.parseObject(msg.optString("buyer"), User.class);
                videoId = msg.optString("videoId");
                if (buyer != null && !TextUtils.isEmpty(buyer.getUid())) {
                    UserChatRealmHelper.getInstance().setVideoMessageRead(buyer.getUid(), videoId);
                    return;
                }
                return;
            case MsgDataType.TIKI_ASSISTANT_MSG /*265*/:
                f837a.m261d("TIKI_ASSISTANT_MSG:265");
                String content = msg.optString("content");
                String url = msg.optString(Values.URL);
                String urlMark = msg.optString("urlMark");
                id = msg.optString("id");
                fromUser = (User) JSON.parseObject(msg.optString("user"), User.class);
                ctime = msg.optLong("ctime");
                if (!TextUtils.isEmpty(id) && fromUser != null && !TextUtils.isEmpty(fromUser.getUid())) {
                    UserChatRealmHelper.getInstance().insertReceiveTextMessage(id, content, url, urlMark, BuildConfig.VERSION_NAME, ctime, fromUser);
                    return;
                }
                return;
            default:
                return;
        }
    }

    static /* synthetic */ void m219a(User delete, Realm innerRealm) {
        TikiUser findUser = (TikiUser) innerRealm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, delete.getUid()).findFirst();
        if (findUser != null && findUser.isValid()) {
            findUser.deleteFromRealm();
        }
    }

    public void onWebSocketMessage(JSONObject msgJson) {
        try {
            int cmd = msgJson.getInt("cmd");
            String extra = msgJson.optString(Downloads.COLUMN_EXTRAS);
            if (cmd == 4018) {
                if (!(this.f856t == null || this.f856t.isDisposed())) {
                    this.f856t.dispose();
                }
                this.f844h = ConnectionState.NEW;
                if (!TextUtils.isEmpty(extra) && extra.equals(this.f839c)) {
                    this.f841e.onChannelClose(extra);
                    this.f839c = BuildConfig.VERSION_NAME;
                }
                this.f845i = 0;
                this.f846j = 0;
                this.f855s = false;
                this.f854r = false;
                this.f853q = null;
                this.f852p.clear();
            } else if (cmd == 4019) {
                this.f844h = ConnectionState.CONNECTED;
                if (this.f841e != null) {
                    this.f841e.onJoined(extra);
                }
            } else {
                String dataText = msgJson.optString("data");
                if (cmd == 4035) {
                    String localMd5 = MD5Helper.getMD5(this.f839c + "relay" + this.f840d);
                    f837a.m261d("localMD5 " + localMd5);
                    if (localMd5.equals(dataText)) {
                        if (!(this.f856t == null || this.f856t.isDisposed())) {
                            this.f856t.dispose();
                        }
                        this.f854r = true;
                        this.f853q = null;
                        return;
                    }
                    return;
                }
                if (TextUtils.isEmpty(dataText)) {
                    f837a.m263e("onWebSocketMessage: data text is empty");
                }
                JSONObject json = new JSONObject(dataText);
                String type = json.optString("type");
                if (json.optBoolean("ack", false)) {
                    m229d(json.optString("id"));
                }
                switch (cmd) {
                    case 4005:
                        m218a(msgJson.optInt("dataType"), json);
                        return;
                    case 4012:
                        if (type.equals("candidate") && extra.equals(this.f839c) && this.f844h == ConnectionState.CONNECTED) {
                            IceCandidate candidate = new IceCandidate(json.getString("id"), json.getInt("label"), json.getString("candidate"));
                            if (!this.f855s) {
                                this.f855s = true;
                                m232g();
                            }
                            this.f841e.onRemoteIceCandidate(candidate);
                            return;
                        }
                        return;
                    case 4013:
                        if (!this.f838b && extra.equals(this.f839c) && this.f844h == ConnectionState.CONNECTED) {
                            this.f841e.onRemoteDescription(new SessionDescription(Type.fromCanonicalForm("offer"), json.getString("sdp")));
                            this.f851o = false;
                            return;
                        }
                        m226c("Received offer for call receiver: " + msgJson);
                        return;
                    case 4014:
                        if (this.f838b && extra.equals(this.f839c) && this.f844h == ConnectionState.CONNECTED) {
                            this.f841e.onRemoteDescription(new SessionDescription(Type.fromCanonicalForm(type), json.getString("sdp")));
                            setRemoteSDPReceived();
                            return;
                        }
                        m226c("Received answer for call initiator: " + msgJson);
                        return;
                    default:
                        return;
                }
            }
        } catch (JSONException e) {
            f837a.m264e("onWebSocketMessage: error ", e);
        } catch (Exception e2) {
            f837a.m264e("onWebSocketMessage: ", e2);
        }
    }

    private void m232g() {
        this.f843g.execute(IMRtcClient$$Lambda$12.lambdaFactory$(this));
    }

    /* synthetic */ void m233a() {
        if (this.f842f != null) {
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("cmd", Integer.valueOf(4035));
            arrayMap.put(Downloads.COLUMN_EXTRAS, this.f839c);
            arrayMap.put("data", MD5Helper.getMD5(this.f839c + "relay" + TopConfig.f408a));
            arrayMap.put("from", TopConfig.f408a + "@facechat.im");
            this.f842f.send(arrayMap);
        }
    }

    public void onWebSocketClose() {
        try {
            this.f841e.onChannelClose(this.f839c);
        } catch (Exception e) {
        }
    }
}
