package com.buddy.tiki.service;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.event.CallEvent.StopCountDownEvent;
import com.buddy.tiki.event.UserEvent.AcceptFriendEvent;
import com.buddy.tiki.event.UserEvent.DeleteFriendEvent;
import com.buddy.tiki.model.payment.SendGiftResult;
import com.buddy.tiki.model.user.FlwApplyResponse;
import com.buddy.tiki.model.user.Friend;
import com.buddy.tiki.model.user.SyncFriends;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.model.user.UserChatMessage;
import com.buddy.tiki.model.user.UserChatSession;
import com.buddy.tiki.protocol.web.FollowApi;
import com.buddy.tiki.service.base.BaseManager;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunc;
import com.buddy.tiki.service.base.HttpRequestBody;
import com.buddy.tiki.util.PreferenceUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.EventBus;

public class FollowManager extends BaseManager {
    private FollowApi f929d;
    private ConcurrentHashMap<String, User> f930e = new ConcurrentHashMap();

    protected void mo2110b() {
        this.f929d = (FollowApi) this.b.getServiceInstance(FollowApi.class);
    }

    public Observable<Boolean> sendGiftAction(String touid, String giftId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("touid", touid);
        params.put("giftId", giftId);
        return this.f929d.sendGiftAction(HttpRequestBody.getInstance().generateRequestParams(params, "vi0RH5RL005KbEWPOgm")).map(new HttpResultFunc());
    }

    public Observable<SendGiftResult> sendGiftActionV2(String touid, String giftId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("touid", touid);
        params.put("giftId", giftId);
        return this.f929d.sendGiftActionV2(HttpRequestBody.getInstance().generateRequestParams(params, "lpGR5RL005L8GD65g1m")).map(new HttpResultFunc());
    }

    public Observable<SendGiftResult> sendGiftInChat(String touid, String giftId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("touid", touid);
        params.put("giftId", giftId);
        return this.f929d.sendGiftInChat(HttpRequestBody.getInstance().generateRequestParams(params, "lpGi98L005L8GD65g2m")).map(new HttpResultFunc());
    }

    public Observable<Boolean> setMarkAction(String touid, String mark) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("touid", touid);
        params.put("mark", mark);
        return this.f929d.setMarkAction(HttpRequestBody.getInstance().generateRequestParams(params, "Pi0RH5Rpj55KbEWPO6T")).map(new HttpResultFunc());
    }

    public Observable<Boolean> userBorderAction(String touid, String borderId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("touid", touid);
        params.put("borderId", borderId);
        return this.f929d.userBorderAction(HttpRequestBody.getInstance().generateRequestParams(params, "Pi0RH5RL005KbEWP1uy")).map(new HttpResultFunc());
    }

    public Observable<Boolean> applyFriendAction(@NonNull User user) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("touid", user.getUid());
        return this.f929d.applyFriendAction(HttpRequestBody.getInstance().generateRequestParams(params, "vYF0SFD3qTm2F1GTbSY3")).map(new HttpResultFunc()).doOnNext(FollowManager$$Lambda$1.lambdaFactory$(this, user));
    }

    /* synthetic */ void m298b(@NonNull User user, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue()) {
            this.f930e.put(user.getUid(), user);
        }
    }

    public Observable<Boolean> applyFriendForSearchAction(@NonNull User user) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("touid", user.getUid());
        return this.f929d.applyFriendForSearchAction(HttpRequestBody.getInstance().generateRequestParams(params, "9PLO4D3qTm2F1GTbL8j")).map(new HttpResultFunc()).doOnNext(FollowManager$$Lambda$2.lambdaFactory$(this, user));
    }

    /* synthetic */ void m295a(@NonNull User user, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue()) {
            this.f930e.put(user.getUid(), user);
        }
    }

    public Observable<Boolean> passApplysAction(String[] toUids, String[] applyIds) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("applyIds", applyIds);
        return this.f929d.passApplysAction(HttpRequestBody.getInstance().generateRequestParams(params, "460Rvhjpj55KbLLKr983")).map(new HttpResultFunc()).doOnNext(FollowManager$$Lambda$3.lambdaFactory$(toUids, applyIds));
    }

    static /* synthetic */ void m292a(String[] toUids, String[] applyIds, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue() && toUids != null) {
            EventBus.getDefault().post(new StopCountDownEvent(toUids));
            EventBus.getDefault().post(new AcceptFriendEvent(applyIds, toUids));
        }
    }

    public Observable<Boolean> unfollowAction(String touid) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("touid", touid);
        return this.f929d.unfollowAction(HttpRequestBody.getInstance().generateRequestParams(params, "7T0Rvhjpj55KbEWRrVg8")).map(new HttpResultFunc()).doOnNext(FollowManager$$Lambda$4.lambdaFactory$(touid));
    }

    static /* synthetic */ void m290a(String touid, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue()) {
            EventBus.getDefault().post(new DeleteFriendEvent(touid));
        }
    }

    public Observable<FlwApplyResponse> applysQuery(long maxTimestamp) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("maxTimestamp", Long.valueOf(maxTimestamp));
        return this.f929d.applysQuery(HttpRequestBody.getInstance().generateRequestParams(params, "0Qxhkfdqopdjsk3hLO3wq")).map(new HttpResultFunc());
    }

    public Observable<SyncFriends> syncFriendsQuery(long timepoint) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("timepoint", Long.valueOf(timepoint));
        return this.f929d.syncFriendsQuery(HttpRequestBody.getInstance().generateRequestParams(params, "1QxhkfdLLLdjskshLOhwy")).map(new HttpResultFunc()).subscribeOn(Schedulers.io()).filter(FollowManager$$Lambda$5.lambdaFactory$()).observeOn(Schedulers.newThread()).doOnNext(FollowManager$$Lambda$6.lambdaFactory$(this));
    }

    static /* synthetic */ boolean m293a(SyncFriends syncFriends) throws Exception {
        return syncFriends != null;
    }

    private void m294b(@NonNull SyncFriends syncFriends) {
        int length;
        int i = 0;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        String[] ids = syncFriends.getDeleteds();
        if (ids != null) {
            for (String id : ids) {
                if (!TextUtils.isEmpty(id)) {
                    TikiUser findUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, id).findFirst();
                    if (findUser != null && findUser.isValid()) {
                        findUser.deleteFromRealm();
                    }
                    UserChatSession userChatSession = (UserChatSession) realm.where(UserChatSession.class).equalTo("sessionId", id).findFirst();
                    if (userChatSession != null && userChatSession.isValid()) {
                        RealmList<UserChatMessage> chatMessages = userChatSession.getMessages();
                        if (chatMessages != null && chatMessages.isValid()) {
                            chatMessages.deleteAllFromRealm();
                        }
                        userChatSession.deleteFromRealm();
                    }
                }
            }
        }
        Friend[] friends = syncFriends.getModifies();
        if (friends != null) {
            length = friends.length;
            while (i < length) {
                Friend friend = friends[i];
                if (friend != null) {
                    TikiUser tikiUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, friend.getUid()).findFirst();
                    if (tikiUser == null || !tikiUser.isValid()) {
                        tikiUser = (TikiUser) realm.createObject(TikiUser.class, friend.getUid());
                        tikiUser.setLastMessageTime(friend.getAddTime());
                        tikiUser.setLastMessage(ChatApp.getInstance().getString(C0376R.string.have_been_friend_tips));
                    }
                    tikiUser.setTid(friend.getTid());
                    tikiUser.setAvatar(friend.getAvatar());
                    tikiUser.setNick(friend.getNick());
                    tikiUser.setGender(friend.getGender());
                    tikiUser.setMark(friend.getMark());
                    tikiUser.setRelation(4);
                }
                i++;
            }
        }
        realm.commitTransaction();
        PreferenceUtil.setSyncTimepoint(syncFriends.getTimepoint());
        realm.close();
    }

    public void afterAcceptingFriend(String applyId, String uid) {
        User user = (User) this.f930e.get(uid);
        if (user != null) {
            Observable.just(user).subscribeOn(Schedulers.io()).flatMap(FollowManager$$Lambda$7.lambdaFactory$(this)).subscribe(FollowManager$$Lambda$8.lambdaFactory$(this, uid, applyId), FollowManager$$Lambda$9.lambdaFactory$());
        }
    }

    /* synthetic */ void m296a(String uid, String applyId, Boolean aBoolean) throws Exception {
        EventBus.getDefault().post(new StopCountDownEvent(new String[]{uid}));
        EventBus.getDefault().post(new AcceptFriendEvent(applyId, uid));
        this.f930e.remove(uid);
    }

    static /* synthetic */ void m291a(Throwable throwable) throws Exception {
    }

    public void clearHistory() {
        this.f930e.clear();
    }

    public Observable<Boolean> insertUser(@NonNull User user) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        TikiUser tikiUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, user.getUid()).findFirst();
        if (tikiUser == null || !tikiUser.isValid()) {
            tikiUser = (TikiUser) realm.createObject(TikiUser.class, user.getUid());
            tikiUser.setLastMessageTime(System.currentTimeMillis());
            tikiUser.setLastMessage(ChatApp.getInstance().getString(C0376R.string.have_been_friend_tips));
        }
        tikiUser.setTid(user.getTid());
        tikiUser.setAvatar(user.getAvatar());
        tikiUser.setNick(user.getNick());
        tikiUser.setGender(user.getGender());
        tikiUser.setMark(user.getMark());
        tikiUser.setRelation(4);
        realm.commitTransaction();
        realm.close();
        return Observable.just(Boolean.valueOf(true));
    }
}
