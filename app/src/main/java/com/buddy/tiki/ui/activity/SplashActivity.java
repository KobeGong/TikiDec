package com.buddy.tiki.ui.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.base.BusinessDomain;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.constant.ChannelKeys;
import com.buddy.tiki.model.user.TikiAdministrator;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.util.MatchStatisticsUtil;
import com.buddy.tiki.util.NotificationUtil;
import com.buddy.tiki.util.PermissionUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.Crashlytics.Builder;
import com.crashlytics.android.core.CrashlyticsCore;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.igexin.sdk.PushManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;
import im.facechat.Rtc;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends BaseActivity {
    private static final TikiLog f1469a = TikiLog.getInstance(SplashActivity.class.getSimpleName());
    private boolean f1470b = false;
    private RxPermissions f1471d;

    class C04391 implements Observer<Boolean> {
        final /* synthetic */ SplashActivity f1468a;

        C04391(SplashActivity this$0) {
            this.f1468a = this$0;
        }

        public void onSubscribe(Disposable d) {
        }

        public void onNext(Boolean aBoolean) {
            if (!aBoolean.booleanValue()) {
                this.f1468a.m809n();
            } else if (this.f1468a.f1471d.isGranted("android.permission.WRITE_EXTERNAL_STORAGE")) {
                this.f1468a.m808m();
            } else {
                this.f1468a.m807l();
            }
        }

        public void onError(Throwable e) {
        }

        public void onComplete() {
        }
    }

    protected int mo2115a() {
        return C0376R.layout.activity_splash;
    }

    protected void onActivityCreate(Bundle savedInstanceState) {
        this.f1471d = new RxPermissions(this);
        m798d();
        m800e();
        m801f();
        m805j();
        m803h();
        m806k();
        m804i();
    }

    protected int mo2117b() {
        return 0;
    }

    private void m798d() {
        Crashlytics crashlytics = new Builder().core(new CrashlyticsCore.Builder().disabled(false).build()).build();
        Fabric.with(this, new Kit[]{crashlytics, new CrashlyticsNdk()});
    }

    private void m800e() {
        if (!TextUtils.isEmpty(BusinessDomain.f405c) && ChannelKeys.GOOGLE_MARKET.toLowerCase().equals(BusinessDomain.f405c.toLowerCase())) {
            try {
                Class appsFlyerLib = Class.forName("com.appsflyer.AppsFlyerLib");
                Object appsFlyerInstance = appsFlyerLib.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                appsFlyerLib.getMethod("startTracking", new Class[]{Application.class, String.class}).invoke(appsFlyerInstance, new Object[]{getApplication(), "CTLZgf3yk7otXV2892n6tf"});
            } catch (Exception e) {
            }
        }
    }

    private void m801f() {
        PushManager.getInstance().initialize(getApplicationContext(), null);
    }

    private void m802g() {
        TikiAdministrator administrator = (TikiAdministrator) this.c.where(TikiAdministrator.class).findFirst();
        if (administrator != null && administrator.isLoaded() && administrator.isValid() && !administrator.isNew()) {
            this.f1470b = true;
        }
        getDataLayer().getAppManager().configInfoRequest().compose(bindUntilEvent(ActivityEvent.DESTROY)).compose(SchedulersCompat.applyIoSchedulers()).observeOn(AndroidSchedulers.mainThread()).subscribe(SplashActivity$$Lambda$1.lambdaFactory$(this), SplashActivity$$Lambda$2.lambdaFactory$(this));
    }

    /* synthetic */ void m815c(Throwable throwable) throws Exception {
        m791a(null);
    }

    private void m791a(@Nullable ConfigInfo configInfo) {
        if (!isFinishing()) {
            Observable.timer(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(SplashActivity$$Lambda$3.lambdaFactory$(this, configInfo));
        }
    }

    /* synthetic */ void m812a(@Nullable ConfigInfo configInfo, Long aLong) throws Exception {
        if (this.f1470b) {
            if (!PreferenceUtil.getResetTikiSyncPoint()) {
                PreferenceUtil.resetTikiSyncPoint();
            }
            if (configInfo == null || !configInfo.isHideRndMatch()) {
                launchActivity(CallActivity.class);
            } else {
                launchActivity(FriendActivity.class);
            }
            MatchStatisticsUtil.removeReported();
            MatchStatisticsUtil.resetStatus();
            MatchStatisticsUtil.reportAll();
        } else {
            if (!PreferenceUtil.getResetTikiSyncPoint()) {
                PreferenceUtil.putResetFlag();
            }
            if (PreferenceUtil.getShowIntroduceFlag()) {
                launchActivity(LoginActivity.class);
                overridePendingTransition(0, 0);
            } else {
                launchActivity(IntroduceActivity.class);
            }
        }
        finish();
        overridePendingTransition(C0376R.anim.activity_still, C0376R.anim.activity_still);
    }

    private void m803h() {
        if (!PreferenceUtil.getActiveStatus()) {
            getDataLayer().getAppManager().activeAction().subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(SplashActivity$$Lambda$4.lambdaFactory$(), SplashActivity$$Lambda$5.lambdaFactory$());
        }
    }

    private void m804i() {
        getDataLayer().getAppManager().operInfoRequest().subscribeOn(Schedulers.io()).subscribe(SplashActivity$$Lambda$6.lambdaFactory$(), SplashActivity$$Lambda$7.lambdaFactory$());
    }

    static /* synthetic */ void m792a(OperInfo operInfo) throws Exception {
    }

    static /* synthetic */ void m794a(Throwable throwable) throws Exception {
    }

    private void m805j() {
        NotificationUtil.clearAllNotification(this);
    }

    private void m806k() {
        Rtc.enableLog(false);
    }

    private void m807l() {
        this.f1471d.request(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}).subscribe(new C04391(this));
    }

    protected void onResume() {
        super.onResume();
        m807l();
    }

    private void m808m() {
        if (!PreferenceUtil.isShowFloatWindowPermission() || PermissionUtil.checkAlertWindowPermission(this)) {
            m802g();
        } else {
            PermissionUtil.applyAlertWindowPermission(this, SplashActivity$$Lambda$10.lambdaFactory$(this));
        }
    }

    /* synthetic */ void m814c() {
        m802g();
    }

    private void m809n() {
        DialogHelper.INSTANCE.showPermissionMissDialog(this);
    }
}
