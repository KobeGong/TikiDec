package com.buddy.tiki.service;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.helper.LooperHandlerHelper;
import com.buddy.tiki.model.address.Address;
import com.buddy.tiki.model.promo.PromoResult;
import com.buddy.tiki.model.user.SessionInfo;
import com.buddy.tiki.model.user.TikiAdministrator;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.model.user.UserChatMessage;
import com.buddy.tiki.model.user.UserChatSession;
import com.buddy.tiki.protocol.web.UserApi;
import com.buddy.tiki.service.base.BaseManager;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunc;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunction;
import com.buddy.tiki.service.base.HttpRequestBody;
import com.buddy.tiki.util.BitsUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmList;
import io.realm.RealmModel;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class UserManager extends BaseManager {
    private UserApi f1009d;

    private class SessionHandler implements ObservableTransformer<SessionInfo, Boolean> {
        final /* synthetic */ UserManager f1008a;

        private SessionHandler(UserManager userManager) {
            this.f1008a = userManager;
        }

        public Observable<Boolean> apply(Observable<SessionInfo> sessionInfoObservable) {
            return sessionInfoObservable.doOnNext(UserManager$SessionHandler$$Lambda$1.lambdaFactory$(this)).flatMap(UserManager$SessionHandler$$Lambda$2.lambdaFactory$());
        }

        static /* synthetic */ ObservableSource m319b(SessionInfo sessionInfo) throws Exception {
            return sessionInfo == null ? Observable.empty() : Observable.just(Boolean.valueOf(sessionInfo.isIsNew()));
        }

        private void m320c(SessionInfo sessionInfo) {
            if (sessionInfo != null && !TextUtils.isEmpty(sessionInfo.getSession()) && !TextUtils.isEmpty(sessionInfo.getUid())) {
                PreferenceUtil.setTikiUidToken(sessionInfo.getUid());
                PreferenceUtil.setTikiSessionToken(sessionInfo.getSession());
                TopConfig.f409b = sessionInfo.getSession();
                TopConfig.f408a = sessionInfo.getUid();
                LooperHandlerHelper.getInstance().getLooperHandler().post(UserManager$SessionHandler$$Lambda$3.lambdaFactory$(sessionInfo));
            }
        }

        static /* synthetic */ void m314a(SessionInfo sessionInfo) {
            Realm realm = Realm.getDefaultInstance();
            Transaction lambdaFactory$ = UserManager$SessionHandler$$Lambda$4.lambdaFactory$(sessionInfo);
            realm.getClass();
            realm.executeTransactionAsync(lambdaFactory$, UserManager$SessionHandler$$Lambda$5.lambdaFactory$(realm), UserManager$SessionHandler$$Lambda$6.lambdaFactory$(realm));
        }

        static /* synthetic */ void m315a(SessionInfo sessionInfo, Realm innerRealm) {
            RealmModel tikiAdministrator = (TikiAdministrator) innerRealm.where(TikiAdministrator.class).findFirst();
            if (tikiAdministrator == null || !tikiAdministrator.isValid()) {
                tikiAdministrator = (TikiAdministrator) innerRealm.createObject(TikiAdministrator.class, sessionInfo.getUid());
            }
            tikiAdministrator.setSession(sessionInfo.getSession());
            innerRealm.insertOrUpdate(tikiAdministrator);
        }
    }

    protected void mo2110b() {
        this.f1009d = (UserApi) this.b.getServiceInstance(UserApi.class);
    }

    protected long mo2112c() {
        return 5000;
    }

    public Observable<User> userRequest(String uid) {
        String type = "1000" + uid;
        if (uid.compareTo(TopConfig.f408a) == 0 && m274a(type)) {
            return Observable.just((User) ((Pair) this.a.get(type)).second);
        }
        ArrayMap<String, Object> params = new ArrayMap();
        params.put(Oauth2AccessToken.KEY_UID, uid);
        return this.f1009d.userRequest(HttpRequestBody.getInstance().generateRequestParams(params, "Lj1UdzwP0x54nWKEpOnZ")).map(new HttpResultFunc()).doOnNext(UserManager$$Lambda$1.lambdaFactory$(this, uid, type));
    }

    /* synthetic */ void m342a(String uid, String type, User user) throws Exception {
        if (user == null) {
            return;
        }
        if (uid.equals(TopConfig.f408a)) {
            this.a.put(type, new Pair(Long.valueOf(SystemClock.elapsedRealtime()), user));
            m333c(user);
            PreferenceUtil.setSysMsgNumber(user.getMessages());
        } else if (BitsUtil.isFriend(user.getRelation())) {
            m337d(user);
        }
    }

    public Observable<Boolean> signInAction(String phone, String authcode, int countryCode) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("phone", phone);
        params.put("authcode", authcode);
        params.put("countryCode", Integer.valueOf(countryCode));
        return this.f1009d.signInAction(HttpRequestBody.getInstance().generateRequestParams(params, "fKdtKFLbJ5akO4gDc3AJ")).map(new HttpResultFunc()).compose(new SessionHandler());
    }

    public Observable<Boolean> signUpAciton(String phone, int countryCode, String authcode, String avatar, String nick, int gender) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("phone", phone);
        params.put("countryCode", Integer.valueOf(countryCode));
        params.put("authcode", authcode);
        params.put("avatar", avatar);
        params.put("nick", nick);
        params.put("gender", Integer.valueOf(gender));
        return this.f1009d.signUpAction(HttpRequestBody.getInstance().generateRequestParams(params, "oYexOsMWa496ul1TVVQN")).map(new HttpResultFunc()).compose(new SessionHandler()).doOnNext(UserManager$$Lambda$2.lambdaFactory$(this, nick, gender));
    }

    /* synthetic */ void m340a(String nick, int gender, Boolean aBoolean) throws Exception {
        m336c(BuildConfig.VERSION_NAME, nick, gender);
    }

    public Observable<Boolean> sinaOauthAction(String token, long tid) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("token", token);
        params.put("tid", Long.valueOf(tid));
        return this.f1009d.sinaOauthAction(HttpRequestBody.getInstance().generateRequestParams(params, "fKdtKFLbJ5akO4gDc3AJ")).map(new HttpResultFunc()).compose(new SessionHandler());
    }

    public Observable<Boolean> sinaRegisterAction(String token, long tid, String avatar, String desc, String name, String gender, String province, String city) {
        int genderType;
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("token", token);
        params.put("tid", Long.valueOf(tid));
        params.put("avatar", avatar);
        params.put("desc", desc);
        params.put("name", name);
        params.put("gender", gender);
        params.put("province", province);
        params.put("city", city);
        Object obj = -1;
        switch (gender.hashCode()) {
            case avutil.AV_PIX_FMT_YUVA444P10LE /*102*/:
                if (gender.equals("f")) {
                    obj = null;
                    break;
                }
                break;
            case avutil.AV_PIX_FMT_VDPAU /*109*/:
                if (gender.equals("m")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                genderType = 2;
                break;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                genderType = 1;
                break;
            default:
                genderType = 0;
                break;
        }
        return this.f1009d.sinaRegisterAction(HttpRequestBody.getInstance().generateRequestParams(params, "fKdtKFLbJ5akO4gDc3AJ")).map(new HttpResultFunc()).compose(new SessionHandler()).doOnNext(UserManager$$Lambda$3.lambdaFactory$(this, avatar, name, genderType));
    }

    /* synthetic */ void m347c(String avatar, String name, int genderType, Boolean aBoolean) throws Exception {
        m336c(avatar, name, genderType);
    }

    public Observable<Boolean> wechatOauthAction(String token, String openid, String unionid) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("token", token);
        params.put("openid", openid);
        params.put("unionid", unionid);
        return this.f1009d.wechatOauthAction(HttpRequestBody.getInstance().generateRequestParams(params, "pKdtKFLbJ5akO4gDapod")).map(new HttpResultFunc()).compose(new SessionHandler());
    }

    public Observable<Boolean> wechatRegisterAction(String token, String openid, String unionid, String avatar, String desc, String name, int sex, String province, String city) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("token", token);
        params.put("openid", openid);
        params.put("unionid", unionid);
        params.put("avatar", avatar);
        params.put("desc", desc);
        params.put("name", name);
        params.put("sex", Integer.valueOf(sex));
        params.put("province", province);
        params.put("city", city);
        return this.f1009d.wechatRegisterAction(HttpRequestBody.getInstance().generateRequestParams(params, "NFLbKFLbJ5akO4gDRigl")).map(new HttpResultFunc()).compose(new SessionHandler()).doOnNext(UserManager$$Lambda$4.lambdaFactory$(this, avatar, name, sex));
    }

    /* synthetic */ void m345b(String avatar, String name, int sex, Boolean aBoolean) throws Exception {
        m336c(avatar, name, sex);
    }

    public Completable sendAuthCodeAction(String phone, int authcodeType) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("phone", phone);
        params.put("authcodeType", Integer.valueOf(authcodeType));
        return this.f1009d.sendAuthCodeAction(HttpRequestBody.getInstance().generateRequestParams(params, "CeP3H61JchwTPkmZCiuE")).flatMapCompletable(new HttpResultFunction());
    }

    public Completable setAvatarNickGenderAction(String avatar, String nick, int genderType) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("genderType", Integer.valueOf(genderType));
        params.put("avatar", avatar);
        params.put("nick", nick);
        return this.f1009d.setAvatarNickGenderAction(HttpRequestBody.getInstance().generateRequestParams(params, "89JBnxtVopRigljwzBNM")).flatMapCompletable(new HttpResultFunction()).doOnComplete(UserManager$$Lambda$5.lambdaFactory$(nick, genderType, avatar)).doOnComplete(UserManager$$Lambda$6.lambdaFactory$(this, avatar, nick, genderType));
    }

    /* synthetic */ void m344b(String avatar, String nick, int genderType) throws Exception {
        m336c(avatar, nick, genderType);
    }

    public Observable<Boolean> qqOauthAction(String token, String openid) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("token", token);
        params.put("openid", openid);
        return this.f1009d.qqOauthAction(HttpRequestBody.getInstance().generateRequestParams(params, "pKdtKFLbJ5akO4gDapod")).map(new HttpResultFunc()).compose(new SessionHandler());
    }

    public Observable<Boolean> qqRegisterAction(String token, String openid, String avatar, String desc, String name, int sex, String province, String city) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("token", token);
        params.put("openid", openid);
        params.put("avatar", avatar);
        params.put("desc", desc);
        params.put("name", name);
        params.put("sex", Integer.valueOf(sex));
        params.put("province", province);
        params.put("city", city);
        return this.f1009d.qqRegisterAction(HttpRequestBody.getInstance().generateRequestParams(params, "NFLbKFLbJ5akO4gDRigl")).map(new HttpResultFunc()).compose(new SessionHandler()).doOnNext(UserManager$$Lambda$7.lambdaFactory$(this, avatar, name, sex));
    }

    /* synthetic */ void m341a(String avatar, String name, int sex, Boolean aBoolean) throws Exception {
        m336c(avatar, name, sex);
    }

    public Observable<Boolean> uploadDeviceTokenAction(String deviceToken) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("deviceToken", deviceToken);
        return this.f1009d.uploadDeviceTokenAction(HttpRequestBody.getInstance().generateRequestParams(params, "92cpdEOZFLJh0fR1uu36")).map(new HttpResultFunc());
    }

    public Observable<Boolean> matchAction(int gender, Address addr) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("gender", Integer.valueOf(gender));
        params.put("addr", addr);
        return this.f1009d.matchAction(HttpRequestBody.getInstance().generateRequestParams(params, "6XYuS4WFInCCSYsfRcUc")).map(new HttpResultFunc());
    }

    public Observable<String> oauthAction(String appId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("appId", appId);
        return this.f1009d.oauthAction(HttpRequestBody.getInstance().generateRequestParams(params, "NFLbKFLb54akO4gDRi00")).map(new HttpResultFunc());
    }

    public Observable<Boolean> logoutAction() {
        return this.f1009d.logoutAction(HttpRequestBody.getInstance().generateRequestParams(null, "MNKghfTbkStVGC2V9uu0")).map(new HttpResultFunc());
    }

    public Observable<User> searchTikiQuery(String tikiId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("tikiId", tikiId);
        return this.f1009d.searchTikiQuery(HttpRequestBody.getInstance().generateRequestParams(params, "90XCU90OPYfKb9xPxXS5CD")).map(new HttpResultFunc());
    }

    public Observable<PromoResult> submitPromo(String code) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("code", code);
        return this.f1009d.submitPromo(HttpRequestBody.getInstance().generateRequestParams(params, "6UU90OPYfKb9xPxXS5CG")).map(new HttpResultFunc());
    }

    public Observable<Boolean> facebookRegisterAction(String token, String fid, String avatar, String name, int gender) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("token", token);
        params.put("fid", fid);
        params.put("avatar", avatar);
        params.put("name", name);
        params.put("gender", Integer.valueOf(gender));
        return this.f1009d.facebookRegisterAction(HttpRequestBody.getInstance().generateRequestParams(params, "98dtKFLbJ5akO4gDc01O")).map(new HttpResultFunc()).compose(new SessionHandler());
    }

    public Observable<Boolean> facebookOauthAction(String token, String fid) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("token", token);
        params.put("fid", fid);
        return this.f1009d.facebookOauthAction(HttpRequestBody.getInstance().generateRequestParams(params, "nKdtK0OKI5akO4gDc6AJ")).map(new HttpResultFunc()).compose(new SessionHandler());
    }

    public Observable<Boolean> unMatchAction() {
        return this.f1009d.unMatchAction(HttpRequestBody.getInstance().generateRequestParams(null, "6XYuS4WFInCCSYsfRcUc")).map(new HttpResultFunc());
    }

    public Observable<long[]> getSendGiftRemain(String[] giftIds) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("giftIds", giftIds);
        return this.f1009d.getGiftSendRemains(HttpRequestBody.getInstance().generateRequestParams(params, "2j1UdzwP0x54nWK5pOnL")).map(new HttpResultFunc());
    }

    public Observable<Boolean> uploadRiverToken(String riverToken) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("riverToken", riverToken);
        return this.f1009d.uploadRiverTokenAction(HttpRequestBody.getInstance().generateRequestParams(params, "XXDcpdEOZFLJh0fR1uu36")).map(new HttpResultFunc());
    }

    public void deleteUserAsync(@NonNull String uid) {
        Realm realm = Realm.getDefaultInstance();
        Transaction lambdaFactory$ = UserManager$$Lambda$8.lambdaFactory$(uid);
        realm.getClass();
        realm.executeTransactionAsync(lambdaFactory$, UserManager$$Lambda$9.lambdaFactory$(realm), UserManager$$Lambda$10.lambdaFactory$(realm));
    }

    static /* synthetic */ void m326a(@NonNull String uid, Realm innerRealm) {
        TikiUser findUser = (TikiUser) innerRealm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, uid).findFirst();
        if (findUser != null && findUser.isValid()) {
            findUser.deleteFromRealm();
        }
        UserChatSession userChatSession = (UserChatSession) innerRealm.where(UserChatSession.class).equalTo("sessionId", uid).findFirst();
        if (userChatSession != null && userChatSession.isValid()) {
            RealmList<UserChatMessage> chatMessages = userChatSession.getMessages();
            if (chatMessages != null && chatMessages.isValid()) {
                chatMessages.deleteAllFromRealm();
            }
            userChatSession.deleteFromRealm();
        }
    }

    public void deleteUser(@NonNull String uid) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        TikiUser findUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, uid).findFirst();
        if (findUser != null && findUser.isValid()) {
            findUser.deleteFromRealm();
        }
        UserChatSession userChatSession = (UserChatSession) realm.where(UserChatSession.class).equalTo("sessionId", uid).findFirst();
        if (userChatSession != null && userChatSession.isValid()) {
            RealmList<UserChatMessage> chatMessages = userChatSession.getMessages();
            if (chatMessages != null && chatMessages.isValid()) {
                chatMessages.deleteAllFromRealm();
            }
            userChatSession.deleteFromRealm();
        }
        realm.commitTransaction();
        realm.close();
    }

    private void m333c(@NonNull User netUser) {
        LooperHandlerHelper.getInstance().getLooperHandler().post(UserManager$$Lambda$11.lambdaFactory$(netUser));
    }

    static /* synthetic */ void m329b(@NonNull User netUser) {
        Realm realm = Realm.getDefaultInstance();
        Transaction lambdaFactory$ = UserManager$$Lambda$20.lambdaFactory$(netUser);
        realm.getClass();
        realm.executeTransactionAsync(lambdaFactory$, UserManager$$Lambda$21.lambdaFactory$(realm), UserManager$$Lambda$22.lambdaFactory$(realm));
    }

    static /* synthetic */ void m330b(@NonNull User netUser, Realm innerRealm) {
        RealmModel user = (TikiAdministrator) innerRealm.where(TikiAdministrator.class).findFirst();
        if (user == null || !user.isValid()) {
            user = (TikiAdministrator) innerRealm.createObject(TikiAdministrator.class, netUser.getUid());
        }
        user.setTid(netUser.getTid());
        user.setAvatar(netUser.getAvatar());
        user.setNick(netUser.getNick());
        user.setGender(netUser.getGender());
        user.setVipSend(netUser.isVipSend());
        user.setUber(netUser.isUber());
        innerRealm.insertOrUpdate(user);
    }

    private void m336c(String avatar, String nick, int genderType) {
        LooperHandlerHelper.getInstance().getLooperHandler().post(UserManager$$Lambda$12.lambdaFactory$(avatar, nick, genderType));
    }

    static /* synthetic */ void m327a(String avatar, String nick, int genderType) {
        Realm realm = Realm.getDefaultInstance();
        Transaction lambdaFactory$ = UserManager$$Lambda$17.lambdaFactory$(avatar, nick, genderType);
        realm.getClass();
        realm.executeTransactionAsync(lambdaFactory$, UserManager$$Lambda$18.lambdaFactory$(realm), UserManager$$Lambda$19.lambdaFactory$(realm));
    }

    static /* synthetic */ void m328a(String avatar, String nick, int genderType, Realm innerRealm) {
        RealmModel user = (TikiAdministrator) innerRealm.where(TikiAdministrator.class).findFirst();
        if (user != null && user.isValid()) {
            user.setAvatar(avatar);
            user.setNick(nick);
            user.setGender(genderType);
            innerRealm.insertOrUpdate(user);
        }
    }

    private void m337d(@NonNull User user) {
        LooperHandlerHelper.getInstance().getLooperHandler().post(UserManager$$Lambda$13.lambdaFactory$(user));
    }

    static /* synthetic */ void m321a(@NonNull User user) {
        Realm realm = Realm.getDefaultInstance();
        Transaction lambdaFactory$ = UserManager$$Lambda$14.lambdaFactory$(user);
        realm.getClass();
        realm.executeTransactionAsync(lambdaFactory$, UserManager$$Lambda$15.lambdaFactory$(realm), UserManager$$Lambda$16.lambdaFactory$(realm));
    }

    static /* synthetic */ void m322a(@NonNull User user, Realm innerRealm) {
        TikiUser tikiUser = (TikiUser) innerRealm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, user.getUid()).findFirst();
        if (tikiUser != null && tikiUser.isValid()) {
            tikiUser.setAvatar(user.getAvatar());
            tikiUser.setNick(user.getNick());
            tikiUser.setMark(user.getMark());
            tikiUser.setTid(user.getTid());
            tikiUser.setGender(user.getGender());
            tikiUser.setOper(user.isOper());
            tikiUser.setRelation(4);
        }
    }
}
