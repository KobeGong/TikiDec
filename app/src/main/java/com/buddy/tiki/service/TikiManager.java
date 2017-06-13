package com.buddy.tiki.service;

import android.content.Intent;
import android.support.v7.recyclerview.BuildConfig;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.helper.FacebookHelper;
import com.buddy.tiki.helper.MediaHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.user.TikiAdministrator;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.UserChatMessage;
import com.buddy.tiki.model.user.UserChatSession;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.LoginActivity;
import com.buddy.tiki.ui.activity.base.ActivityManager;
import com.buddy.tiki.util.FUUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.facebook.drawee.backends.pipeline.Fresco;
import io.realm.Realm;
import org.greenrobot.eventbus.EventBus;

public class TikiManager {
    private static final TikiLog f944a = TikiLog.getInstance("TikiManager");

    private static class SingletonHolder {
        private static final TikiManager f943a = new TikiManager();
    }

    private TikiManager() {
    }

    public static TikiManager getInstance() {
        return SingletonHolder.f943a;
    }

    public synchronized void logout(boolean queryServer) {
        if (queryServer) {
            DataLayer.getInstance().getUserManager().logoutAction().compose(SchedulersCompat.applyIoSchedulers()).subscribe(TikiManager$$Lambda$1.lambdaFactory$(), TikiManager$$Lambda$2.lambdaFactory$());
        }
        if (PreferenceUtil.getFacebookLogin()) {
            FacebookHelper.logout();
        }
        PreferenceUtil.clearUserPreference();
        DataLayer.getInstance().getFollowManager().clearHistory();
        Intent intent = new Intent(ActivityManager.getInstance().currentActivity(), LoginActivity.class);
        intent.setFlags(268468224);
        ActivityManager.getInstance().currentActivity().startActivity(intent);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(TikiManager$$Lambda$3.lambdaFactory$());
        realm.close();
        TopConfig.f409b = BuildConfig.VERSION_NAME;
        TopConfig.f408a = BuildConfig.VERSION_NAME;
        PreferenceUtil.setTikiUidToken(BuildConfig.VERSION_NAME);
        PreferenceUtil.setTikiSessionToken(BuildConfig.VERSION_NAME);
    }

    static /* synthetic */ void m308a(Boolean aBoolean) throws Exception {
    }

    static /* synthetic */ void m307a(Realm r) {
        r.where(TikiAdministrator.class).findAll().deleteAllFromRealm();
        r.where(TikiUser.class).findAll().deleteAllFromRealm();
        r.where(UserChatSession.class).findAll().deleteAllFromRealm();
        r.where(UserChatMessage.class).findAll().deleteAllFromRealm();
    }

    public TikiAdministrator loadAdministrator() {
        Realm realm = Realm.getDefaultInstance();
        TikiAdministrator administrator = (TikiAdministrator) realm.copyFromRealm((TikiAdministrator) realm.where(TikiAdministrator.class).findFirst());
        realm.close();
        return administrator;
    }

    public int getGender() {
        Realm realm = Realm.getDefaultInstance();
        int gender = ((TikiAdministrator) realm.where(TikiAdministrator.class).findFirst()).getGender();
        realm.close();
        return gender;
    }

    public void cleanUpAfterQuit() {
        EventBus.getDefault().removeAllStickyEvents();
        Fresco.getImagePipeline().clearMemoryCaches();
        FUUtil.getInstance().clearFaceUnity();
        DialogHelper.INSTANCE.reset();
        DataLayer.getInstance().cleanup();
        MediaHelper.INSTANCE.release();
    }
}
