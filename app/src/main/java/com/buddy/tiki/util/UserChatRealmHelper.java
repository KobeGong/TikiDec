package com.buddy.tiki.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.im.VideoMessage;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.model.user.UserChatMessage;
import com.buddy.tiki.model.user.UserChatSession;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmModel;

public class UserChatRealmHelper {
    private static final TikiLog f2480a = TikiLog.getInstance(UserChatRealmHelper.class.getSimpleName());
    private static UserChatRealmHelper f2481b;

    public static UserChatRealmHelper getInstance() {
        if (f2481b == null) {
            synchronized (UserChatRealmHelper.class) {
                if (f2481b == null) {
                    f2481b = new UserChatRealmHelper();
                }
            }
        }
        return f2481b;
    }

    public void setVideoMessageRead(String uid, String videoId) {
        if (!TextUtils.isEmpty(uid) && !TextUtils.isEmpty(videoId)) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            UserChatMessage message = (UserChatMessage) realm.where(UserChatMessage.class).equalTo(Oauth2AccessToken.KEY_UID, uid).equalTo("videoId", videoId).findFirst();
            if (message != null) {
                message.setRead(true);
            }
            realm.commitTransaction();
            realm.close();
        }
    }

    public void insertVideoMessage(VideoMessage message) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        insertVideoMessage(realm, message);
        realm.commitTransaction();
        realm.close();
    }

    public void insertVideoMessage(Realm realm, VideoMessage message) {
        if (message != null && message.getFrom() != null) {
            m1622a(realm, message.getFrom().getUid(), 3, message.getCtime(), null, null, null, 0, message.getVideoId(), message.getTikiPrice() > 0, 0, false, false, message.getCover(), null, message.getFrom(), message.getTikiPrice(), message.getId(), ChatApp.getInstance().getResources().getString(C0376R.string.sight_video));
        }
    }

    public void insertCallOfflineMessage(@NonNull Realm realm, @NonNull String sessionId, @NonNull String msgId, long timestamp, @NonNull User fromUser) {
        if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(msgId)) {
            m1622a(realm, fromUser.getUid(), 1, timestamp, ChatApp.getInstance().getResources().getString(C0376R.string.msg_bubble_cancel_other), null, null, 5, null, false, 0, false, false, null, null, fromUser, 0, msgId, ChatApp.getInstance().getResources().getString(C0376R.string.video_message_not_response_friend));
        }
    }

    public void insertCallRejectMessage(@NonNull Realm realm, @NonNull String sessionId, @NonNull String msgId, long timestamp, @NonNull User fromUser) {
        if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(msgId)) {
            m1623a(realm, fromUser.getUid(), 2, timestamp, ChatApp.getInstance().getResources().getString(C0376R.string.msg_bubble_refuse), null, null, 4, null, false, 0, false, false, null, fromUser, 0, msgId, ChatApp.getInstance().getResources().getString(C0376R.string.video_message_reject));
        }
    }

    public void insertRejectMessage(@NonNull Realm realm, @NonNull String sessionId, @NonNull String msgId, long timestamp, @NonNull User fromUser) {
        if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(msgId)) {
            m1623a(realm, fromUser.getUid(), 1, timestamp, ChatApp.getInstance().getResources().getString(C0376R.string.msg_bubble_refuse_other), null, null, 2, null, false, 0, false, false, null, fromUser, 0, msgId, null);
        }
    }

    public void insertOfflineMessage(@NonNull Realm realm, @NonNull String sessionId, @NonNull String msgId, long timestamp, @NonNull User fromUser) {
        if (!TextUtils.isEmpty(sessionId)) {
            m1623a(realm, fromUser.getUid(), 2, timestamp, ChatApp.getInstance().getResources().getString(C0376R.string.msg_bubble_cancel), null, null, 5, null, false, 0, false, false, null, fromUser, 0, msgId, ChatApp.getInstance().getResources().getString(C0376R.string.friend_video_call_without_answer));
        }
    }

    public void insertCancelMessage(@NonNull Realm realm, @NonNull String sessionId, @NonNull String msgId, long timestamp, @NonNull User fromUser) {
        if (!TextUtils.isEmpty(sessionId)) {
            m1623a(realm, fromUser.getUid(), 2, timestamp, ChatApp.getInstance().getResources().getString(C0376R.string.msg_bubble_cancel), null, null, 1, null, false, 0, false, false, null, fromUser, 0, msgId, ChatApp.getInstance().getResources().getString(C0376R.string.friend_video_call_without_answer));
        }
    }

    public void insertSendTimeMessage(@NonNull Realm realm, @NonNull String msgId, @NonNull String msgText, @NonNull String lastMsgText, long timestamp, @NonNull User fromUser) {
        m1623a(realm, fromUser.getUid(), 2, timestamp, msgText, null, null, 6, null, false, 0, false, false, null, fromUser, 0, msgId, lastMsgText);
    }

    public void insertReceiveTimeMessage(@NonNull Realm realm, @NonNull String msgId, @NonNull String msgText, @NonNull String lastMsgText, long timestamp, @NonNull User fromUser) {
        m1623a(realm, fromUser.getUid(), 1, timestamp, msgText, null, null, 6, null, false, 0, false, false, null, fromUser, 0, msgId, lastMsgText);
    }

    public void insertReceiveTextMessage(@NonNull String msgId, @NonNull String msgText, String url, String urlMark, String lastMsgText, long timestamp, @NonNull User fromUser) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        insertReceiveTextMessage(realm, msgId, msgText, url, urlMark, lastMsgText, timestamp, fromUser);
        realm.commitTransaction();
        realm.close();
    }

    public void insertReceiveTextMessage(Realm realm, @NonNull String msgId, @NonNull String msgText, String url, String urlMark, String lastMsgText, long timestamp, @NonNull User fromUser) {
        m1622a(realm, fromUser.getUid(), 6, timestamp, msgText, url, urlMark, 0, null, false, 0, false, false, null, null, fromUser, 0, msgId, lastMsgText);
    }

    public void insertGiftInChatMessage(@NonNull String msgId, @NonNull Gift gift, long timestamp, @NonNull User fromUser) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        insertGiftInChatMessage(realm, msgId, gift, timestamp, fromUser);
        realm.commitTransaction();
        realm.close();
    }

    public void insertGiftInChatMessage(Realm realm, @NonNull String msgId, @NonNull Gift gift, long timestamp, @NonNull User fromUser) {
        m1622a(realm, fromUser.getUid(), 8, timestamp, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, 0, BuildConfig.VERSION_NAME, false, 0, false, false, BuildConfig.VERSION_NAME, gift, fromUser, gift.getTikis(), msgId, ChatApp.getInstance().getResources().getString(C0376R.string.format_gift_message, new Object[]{gift.getName()}));
    }

    private void m1622a(Realm realm, String sessionId, int msgType, long timestamp, String msgText, String url, String urlMark, int actionType, String videoId, boolean needPay, long during, boolean videoFail, boolean isRead, String videoThumb, @Nullable Gift gift, @NonNull User fromUser, int coin, String msgId, String lastMsgText) {
        if (!TextUtils.isEmpty(msgId) && !TextUtils.isEmpty(sessionId) && realm != null) {
            if (((UserChatMessage) realm.where(UserChatMessage.class).equalTo("msgId", msgId).findFirst()) != null) {
                f2480a.m263e("UserChatMessage of id[" + msgId + "] already exist");
                return;
            }
            long now = System.currentTimeMillis();
            UserChatMessage chatMsg = (UserChatMessage) realm.createObject(UserChatMessage.class, msgId);
            chatMsg.setMsgType(msgType);
            chatMsg.setTimestamp(timestamp);
            chatMsg.setMsgText(msgText);
            chatMsg.setUrl(url);
            chatMsg.setUrlMark(urlMark);
            chatMsg.setActionType(actionType);
            chatMsg.setVideoId(videoId);
            chatMsg.setNeedPay(needPay);
            chatMsg.setDuring(during);
            chatMsg.setVideoFail(videoFail);
            chatMsg.setRead(isRead);
            chatMsg.setVideoThumb(videoThumb);
            chatMsg.setUid(fromUser.getUid());
            chatMsg.setCoin(coin);
            chatMsg.setGift(gift);
            UserChatSession session = (UserChatSession) realm.where(UserChatSession.class).equalTo("sessionId", sessionId).findFirst();
            if (session == null) {
                session = (UserChatSession) realm.createObject(UserChatSession.class, sessionId);
                realm.copyToRealm((RealmModel) session);
            }
            session.getMessages().add(chatMsg);
            session.setTimestamp(System.currentTimeMillis());
            TikiUser tikiUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, chatMsg.getUid()).findFirst();
            if (tikiUser == null) {
                TikiUser newUser = (TikiUser) realm.createObject(TikiUser.class, chatMsg.getUid());
                newUser.setMark(fromUser.getMark());
                newUser.setAvatar(fromUser.getAvatar());
                newUser.setGender(fromUser.getGender());
                newUser.setNick(fromUser.getNick());
                newUser.setTid(fromUser.getTid());
                newUser.setRelation(4);
                newUser.setUnread(0);
                session.setUnread(0);
                tikiUser = newUser;
            }
            if (tikiUser != null && tikiUser.isLoaded() && tikiUser.isValid() && !TextUtils.isEmpty(lastMsgText)) {
                tikiUser.setLastMessage(lastMsgText);
                tikiUser.setLastMessageTime(now);
                tikiUser.setUnread(tikiUser.getUnread() + 1);
                session.setUnread(tikiUser.getUnread());
            }
        }
    }

    private void m1623a(Realm realm, String sessionId, int msgType, long timestamp, String msgText, String url, String urlMark, int actionType, String videoId, boolean needPay, long during, boolean videoFail, boolean isRead, String videoThumb, @NonNull User fromUser, int coin, String msgId, String lastMsgText) {
        if (!TextUtils.isEmpty(msgId) && !TextUtils.isEmpty(sessionId) && realm != null) {
            if (((UserChatMessage) realm.where(UserChatMessage.class).equalTo("msgId", msgId).findFirst()) != null) {
                f2480a.m263e("UserChatMessage of id[" + msgId + "] already exist");
                return;
            }
            long now = System.currentTimeMillis();
            UserChatMessage chatMsg = (UserChatMessage) realm.createObject(UserChatMessage.class, msgId);
            chatMsg.setMsgType(msgType);
            chatMsg.setTimestamp(timestamp);
            chatMsg.setMsgText(msgText);
            chatMsg.setUrl(url);
            chatMsg.setUrlMark(urlMark);
            chatMsg.setActionType(actionType);
            chatMsg.setVideoId(videoId);
            chatMsg.setNeedPay(needPay);
            chatMsg.setDuring(during);
            chatMsg.setVideoFail(videoFail);
            chatMsg.setRead(isRead);
            chatMsg.setVideoThumb(videoThumb);
            chatMsg.setUid(fromUser.getUid());
            chatMsg.setCoin(coin);
            UserChatSession session = (UserChatSession) realm.where(UserChatSession.class).equalTo("sessionId", sessionId).findFirst();
            if (session == null) {
                session = (UserChatSession) realm.createObject(UserChatSession.class, sessionId);
                realm.copyToRealm((RealmModel) session);
            }
            session.getMessages().add(chatMsg);
            session.setTimestamp(System.currentTimeMillis());
            TikiUser tikiUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, chatMsg.getUid()).findFirst();
            if (tikiUser == null) {
                TikiUser newUser = (TikiUser) realm.createObject(TikiUser.class, chatMsg.getUid());
                newUser.setMark(fromUser.getMark());
                newUser.setAvatar(fromUser.getAvatar());
                newUser.setGender(fromUser.getGender());
                newUser.setNick(fromUser.getNick());
                newUser.setTid(fromUser.getTid());
                newUser.setRelation(4);
                tikiUser = newUser;
            }
            if (tikiUser != null && tikiUser.isLoaded() && tikiUser.isValid() && !TextUtils.isEmpty(lastMsgText)) {
                tikiUser.setLastMessage(lastMsgText);
                tikiUser.setLastMessageTime(now);
            }
        }
    }

    public void updateSession(String uid) {
        Realm realm = Realm.getDefaultInstance();
        Transaction lambdaFactory$ = UserChatRealmHelper$$Lambda$1.lambdaFactory$(uid);
        realm.getClass();
        realm.executeTransactionAsync(lambdaFactory$, UserChatRealmHelper$$Lambda$2.lambdaFactory$(realm), UserChatRealmHelper$$Lambda$3.lambdaFactory$(realm));
    }

    static /* synthetic */ void m1625a(String uid, Realm innerRealm) {
        UserChatSession session = (UserChatSession) innerRealm.where(UserChatSession.class).equalTo("sessionId", uid).findFirst();
        if (session == null) {
            session = (UserChatSession) innerRealm.createObject(UserChatSession.class, uid);
        }
        session.setTimestamp(System.currentTimeMillis());
    }
}
