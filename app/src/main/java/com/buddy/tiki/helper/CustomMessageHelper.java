package com.buddy.tiki.helper;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.buddy.tiki.event.FriendCallEvent.AcceptEvent;
import com.buddy.tiki.event.FriendCallEvent.CloseEvent;
import com.buddy.tiki.event.FriendCallEvent.RejectEvent;
import com.buddy.tiki.event.UserEvent.DeleteFriendEvent;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.constant.MsgDataType;
import com.buddy.tiki.model.im.VideoMessage;
import com.buddy.tiki.model.msg.MatchMessage;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.protocol.im.FacechatIMEvents;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.base.ActivityManager;
import com.buddy.tiki.util.IncomingCallManager;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.UserChatRealmHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import im.facechat.Rtc;
import im.facechat.Rtc.FCRoomEvent;
import io.netty.handler.codec.rtsp.RtspHeaders.Values;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import org.bytedeco.javacpp.avcodec.AVCodecContext;
import org.bytedeco.javacpp.avutil;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

public final class CustomMessageHelper implements FCRoomEvent {
    private static final Class<?> f538a = CustomMessageHelper.class;
    private static final TikiLog f539b = TikiLog.getInstance(CustomMessageHelper.class.getSimpleName());
    private final Stack<FacechatIMEvents> f540c;
    private final Handler f541d;
    private boolean f542e;
    private ConcurrentHashMap<Integer, Object> f543f;

    private static class SingletonHolder {
        private static final CustomMessageHelper f537a = new CustomMessageHelper();
    }

    private CustomMessageHelper() {
        HandlerThread handlerThread = new HandlerThread(f538a.getSimpleName(), -4);
        handlerThread.start();
        this.f541d = new Handler(handlerThread.getLooper());
        this.f540c = new Stack();
        this.f543f = new ConcurrentHashMap();
    }

    public static CustomMessageHelper getInstance() {
        return SingletonHolder.f537a;
    }

    public void initialize() {
        Rtc.registerRoomEvent(this);
    }

    public void ignoreNext(boolean ignore) {
        this.f542e = ignore;
    }

    public void registerEvent(FacechatIMEvents events) {
        this.f540c.push(events);
    }

    public void unregisterEvent(FacechatIMEvents events) {
        this.f540c.remove(events);
        if (this.f540c.size() == 1) {
            m114b();
        }
    }

    private void m114b() {
        if (this.f543f != null && this.f543f.size() > 0) {
            FacechatIMEvents events = (FacechatIMEvents) this.f540c.peek();
            for (Entry<Integer, Object> entry : this.f543f.entrySet()) {
                switch (((Integer) entry.getKey()).intValue()) {
                    case MsgDataType.MATCH /*232*/:
                        events.onMatch((MatchMessage) entry.getValue());
                        break;
                    default:
                        break;
                }
            }
            this.f543f.clear();
        }
    }

    public void release() {
        this.f540c.clear();
        Rtc.unregisterRoomEvent(this);
    }

    private void m115b(String msgId, String message) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(message);
        } catch (JSONException e) {
            f539b.m264e("parse json fail ", e);
        }
        if (jsonObject != null) {
            m111a(jsonObject.optInt("type"), msgId, jsonObject);
        }
    }

    private void m111a(int dataType, String msgId, JSONObject msg) {
        Gson gson = new GsonBuilder().create();
        MatchMessage matchMessage;
        String giftText;
        String callId;
        String uid;
        String id;
        User fromUser;
        JSONObject callerJSON;
        TikiUser tikiUser;
        User user;
        long ctime;
        String videoId;
        String content;
        switch (dataType) {
            case MsgDataType.MATCH /*232*/:
                matchMessage = (MatchMessage) gson.fromJson(msg.toString(), MatchMessage.class);
                if (matchMessage == null || !matchMessage.isParseValid()) {
                    f539b.m269w("invalid matchMessage");
                    return;
                }
                if (matchMessage.isClockMode() && !matchMessage.isUber()) {
                    if (this.f540c.size() == 2) {
                        this.f543f.put(Integer.valueOf(MsgDataType.MATCH), matchMessage);
                    }
                    AndroidSchedulers.mainThread().createWorker().schedule(CustomMessageHelper$$Lambda$1.lambdaFactory$());
                }
                if (this.f540c != null && !this.f540c.empty()) {
                    ((FacechatIMEvents) this.f540c.peek()).onMatch(matchMessage);
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
                    if (this.f540c != null && !this.f540c.empty()) {
                        ((FacechatIMEvents) this.f540c.peek()).onApplyFriendRequest(applyId, applyUid);
                        return;
                    }
                    return;
                }
                return;
            case MsgDataType.ACCEPT_FRIEND_MSG /*234*/:
                String acceptFriendApplyId = msg.optString("applyId");
                String acceptFriendUid = msg.optString(Oauth2AccessToken.KEY_UID);
                if (!TextUtils.isEmpty(acceptFriendApplyId) && !TextUtils.isEmpty(acceptFriendUid)) {
                    UserChatRealmHelper.getInstance().updateSession(acceptFriendUid);
                    DataLayer.getInstance().getFollowManager().afterAcceptingFriend(acceptFriendApplyId, acceptFriendUid);
                    return;
                }
                return;
            case MsgDataType.GIFT /*235*/:
                giftText = msg.optString("gift");
                if (!TextUtils.isEmpty(giftText)) {
                    Gift gift = (Gift) gson.fromJson(giftText, Gift.class);
                    if (this.f540c != null && !this.f540c.empty()) {
                        ((FacechatIMEvents) this.f540c.peek()).onGiftReceived(gift);
                        return;
                    }
                    return;
                }
                return;
            case MsgDataType.CALL_FRIEND_MSG /*237*/:
                f539b.m263e("CALL_FRIEND_MSG:237");
                callId = msg.optString("callId");
                JSONObject friendJSON = msg.optJSONObject("caller");
                if (friendJSON != null) {
                    String nick = friendJSON.optString("nick");
                    uid = friendJSON.optString(Oauth2AccessToken.KEY_UID);
                    Activity currentActivity = ActivityManager.getInstance().currentActivity();
                    if (currentActivity != null && !TextUtils.isEmpty(callId) && !TextUtils.isEmpty(nick) && !TextUtils.isEmpty(uid)) {
                        currentActivity.runOnUiThread(CustomMessageHelper$$Lambda$2.lambdaFactory$(callId, nick, uid));
                        return;
                    }
                    return;
                }
                return;
            case MsgDataType.CALL_REJECT_MSG /*239*/:
                f539b.m261d("CALL_REJECT_MSG:239");
                id = msgId;
                fromUser = null;
                callerJSON = msg.optJSONObject("from");
                if (callerJSON != null) {
                    fromUser = new User();
                    fromUser.setNick(callerJSON.optString("nick"));
                    fromUser.setUid(callerJSON.optString(Oauth2AccessToken.KEY_UID));
                    fromUser.setGender(callerJSON.optInt("gender"));
                    fromUser.setRelation(callerJSON.optInt("relation"));
                }
                callId = msg.optString("callId");
                uid = fromUser.getUid();
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
                }
                if (!TextUtils.isEmpty(callId)) {
                    EventBus.getDefault().post(new RejectEvent(callId));
                    return;
                }
                return;
            case MsgDataType.CALL_CLOSE_MSG /*240*/:
                callId = msg.optString("callId");
                f539b.m261d("CALL_CLOSE_MSG:240 callId:" + callId);
                if (!TextUtils.isEmpty(callId)) {
                    EventBus.getDefault().post(new CloseEvent(callId));
                    return;
                }
                return;
            case MsgDataType.CALL_ACCEPT_MSG /*243*/:
                f539b.m261d("CALL_ACCEPT_MSG:243");
                String roomId = msg.optString("roomId");
                callId = msg.optString("callId");
                String friend = msg.optString("friend");
                user = (User) gson.fromJson(friend, User.class);
                matchMessage = new MatchMessage();
                matchMessage.setMatchUser(user);
                matchMessage.setRoomId(roomId);
                if (!TextUtils.isEmpty(roomId)) {
                    if (Rtc.joinRoom(roomId, BuildConfig.VERSION_NAME)) {
                        EventBus.getDefault().post(new AcceptEvent(roomId, callId, matchMessage));
                        return;
                    }
                    return;
                }
                return;
            case AVCodecContext.FF_PROFILE_H264_HIGH_444_PREDICTIVE /*244*/:
                f539b.m261d("CALL_OFFLINE_MSG:244");
                callId = msg.optString("callId");
                id = msgId;
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
                    User delete = (User) gson.fromJson(from, User.class);
                    if (delete != null) {
                        DataLayer.getInstance().getUserManager().deleteUser(delete.getUid());
                        EventBus.getDefault().post(new DeleteFriendEvent(delete.getUid()));
                        return;
                    }
                    return;
                }
                return;
            case MsgDataType.PORN_MSG /*250*/:
                boolean needReport = msg.optBoolean("needReport");
                if (this.f540c != null && !this.f540c.empty()) {
                    ((FacechatIMEvents) this.f540c.peek()).onReceivePornMsg(needReport);
                    return;
                }
                return;
            case MsgDataType.VIDEO_MSG /*261*/:
                f539b.m261d("VIDEO_MSG:261");
                ctime = msg.optLong("ctime");
                int tikiPrice = msg.optInt("tikiPrice");
                int timeLen = msg.optInt("timelen");
                videoId = msg.optString("videoId");
                String cover = msg.optString("cover");
                fromUser = (User) JSON.parseObject(msg.optString("from"), User.class);
                id = msgId;
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
                f539b.m261d("EARN_MSG:262");
                return;
            case MsgDataType.BUY_VIDEO_MESSAGE /*264*/:
                f539b.m261d("BUY_VIDEO_MESSAGE:264");
                User buyer = (User) JSON.parseObject(msg.optString("buyer"), User.class);
                videoId = msg.optString("videoId");
                if (buyer != null && !TextUtils.isEmpty(buyer.getUid())) {
                    UserChatRealmHelper.getInstance().setVideoMessageRead(buyer.getUid(), videoId);
                    return;
                }
                return;
            case MsgDataType.TIKI_ASSISTANT_MSG /*265*/:
                f539b.m263e("TIKI_ASSISTANT_MSG:265");
                content = msg.optString("content");
                String url = msg.optString(Values.URL);
                String urlMark = msg.optString("urlMark");
                id = msgId;
                String msgUser = msg.optString("user");
                if (msgUser == null) {
                    f539b.m269w("user is null");
                    return;
                }
                fromUser = (User) gson.fromJson(msgUser, User.class);
                ctime = msg.optLong("ctime");
                ctime = System.currentTimeMillis();
                if (!TextUtils.isEmpty(id) && fromUser != null && !TextUtils.isEmpty(fromUser.getUid())) {
                    UserChatRealmHelper.getInstance().insertReceiveTextMessage(id, content, url, urlMark, content, ctime, fromUser);
                    return;
                }
                return;
            case MsgDataType.TEXT_MSG /*266*/:
                content = msg.optString("content", BuildConfig.VERSION_NAME).trim();
                String userText = msg.optString("user", BuildConfig.VERSION_NAME).trim();
                ctime = System.currentTimeMillis();
                if (TextUtils.isEmpty(content) || TextUtils.isEmpty(userText)) {
                    f539b.m269w("content is empty");
                    return;
                }
                user = (User) gson.fromJson(userText, User.class);
                if (user != null && !TextUtils.isEmpty(user.getUid())) {
                    UserChatRealmHelper.getInstance().insertReceiveTextMessage(msgId, content, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, content, ctime, user);
                    return;
                }
                return;
            case MsgDataType.GIFT_IN_CHAT /*269*/:
                giftText = msg.optString("gift");
                String fromUserText = msg.optString("fromUser");
                ctime = msg.optLong("ctime");
                if (TextUtils.isEmpty(giftText) || TextUtils.isEmpty(fromUserText)) {
                    f539b.m269w("content is empty");
                    return;
                }
                UserChatRealmHelper.getInstance().insertGiftInChatMessage(msgId, (Gift) gson.fromJson(giftText, Gift.class), ctime, (User) gson.fromJson(fromUserText, User.class));
                return;
            case avutil.AV_PIX_FMT_YUV420P14BE /*301*/:
                if (this.f540c != null && !this.f540c.empty()) {
                    ((FacechatIMEvents) this.f540c.peek()).onBalanceMsg();
                    return;
                }
                return;
            case avutil.AV_PIX_FMT_YUV420P14LE /*302*/:
                if (this.f542e) {
                    f539b.m269w("rush message ignored");
                } else {
                    f539b.m261d("rush message accepted");
                    String text = msg.optString("text");
                    AndroidSchedulers.mainThread().createWorker().schedule(CustomMessageHelper$$Lambda$3.lambdaFactory$(text));
                }
                this.f542e = false;
                return;
            default:
                return;
        }
    }

    public void onJoinRoom(String roomId, String payload) {
        if (this.f540c != null && !this.f540c.empty()) {
            ((FacechatIMEvents) this.f540c.peek()).onJoinRoom(roomId, payload);
        }
    }

    public void onLeaveRoom(String roomId, String payload) {
        if (this.f540c != null && !this.f540c.empty()) {
            ((FacechatIMEvents) this.f540c.peek()).onLeaveRoom(roomId, payload);
        }
    }

    public void onRoomMessage(String roomId, String payload) {
        if (this.f540c != null && !this.f540c.empty()) {
            ((FacechatIMEvents) this.f540c.peek()).onRoomMessage(roomId, payload);
        }
    }

    public void onRoomSession(String roomId, String session) {
        if (this.f540c != null && !this.f540c.empty()) {
            ((FacechatIMEvents) this.f540c.peek()).onRoomSession(roomId, session);
        }
    }

    /* synthetic */ void m116a(String msgId, String s) {
        m115b(msgId, s);
    }

    public void onSystemMessage(String msgId, String s) {
        this.f541d.post(CustomMessageHelper$$Lambda$4.lambdaFactory$(this, msgId, s));
    }

    public void onError(int errorCode, String errorMessage) {
        if (this.f540c != null && !this.f540c.empty()) {
            ((FacechatIMEvents) this.f540c.peek()).onError(errorCode, errorMessage);
        }
    }

    public void onStateChange(int state, @Nullable String roomId) {
        f539b.m263e("onStateChange:" + state);
        if (this.f540c != null && !this.f540c.empty()) {
            ((FacechatIMEvents) this.f540c.peek()).onStateChange(state, roomId);
        }
    }

    public void onWebSocketState(int state) {
        if (this.f540c != null && !this.f540c.empty()) {
            ((FacechatIMEvents) this.f540c.peek()).onWebSocketState(state);
        }
    }
}
