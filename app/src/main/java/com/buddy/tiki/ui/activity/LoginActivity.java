package com.buddy.tiki.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.event.UserEvent.WxTokenEvent;
import com.buddy.tiki.helper.FacebookHelper;
import com.buddy.tiki.helper.FacebookHelper.FacebookUserInfo;
import com.buddy.tiki.helper.FacebookHelper.FacebookUserInfoCallback;
import com.buddy.tiki.helper.LocationHelper;
import com.buddy.tiki.helper.WechatHelper;
import com.buddy.tiki.helper.WeiboServiceHelper;
import com.buddy.tiki.helper.WeiboServiceHelper.WeiboAuthCallback;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.weibo.WbToken;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.dialog.LoadingDialog;
import com.buddy.tiki.ui.fragment.UpdateProfileFragment;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.RippleUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.wxapi.WXEntryActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.jakewharton.rxbinding2.view.RxView;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.parceler.Parcels;

public class LoginActivity extends BaseActivity {
    private static final TikiLog f1436a = TikiLog.getInstance("LoginActivity");
    private LoginButton f1437b;
    private FacebookHelper f1438d;
    private SsoHandler f1439e = null;
    @BindView(2131820762)
    AppCompatImageView mFacebookLoginBtn;
    @BindView(2131820759)
    AppCompatImageView mPhoneLoginBtn;
    @BindView(2131820763)
    AppCompatTextView mServicesTerms;
    @BindView(2131820760)
    AppCompatImageView mWechatLoginBtn;
    @BindView(2131820761)
    AppCompatImageView mWeiboLoginBtn;

    class C04331 implements FacebookCallback<LoginResult> {
        final /* synthetic */ LoginActivity f1427a;

        C04331(LoginActivity this$0) {
            this.f1427a = this$0;
        }

        public void onSuccess(final LoginResult loginResult) {
            if (loginResult == null || loginResult.getAccessToken() == null) {
                ToastUtil.getInstance().show(this.f1427a, (int) C0376R.string.fail_fetch_fb_user_info);
            } else {
                this.f1427a.f1438d.getFacebookUserInfo(loginResult.getAccessToken(), new FacebookUserInfoCallback(this) {
                    final /* synthetic */ C04331 f1426b;

                    public void onSuccess(FacebookUserInfo userInfo) {
                        this.f1426b.f1427a.m733a(loginResult.getAccessToken(), userInfo);
                        Fresco.getImagePipeline().prefetchToDiskCache(ImageRequest.fromUri(userInfo.avatar), ChatApp.getInstance());
                    }

                    public void onFailed(String reason) {
                        ToastUtil.getInstance().show(this.f1426b.f1427a, (int) C0376R.string.fail_fetch_fb_user_info);
                    }
                });
            }
        }

        public void onCancel() {
            ToastUtil.getInstance().show(this.f1427a, (int) C0376R.string.cancel);
        }

        public void onError(FacebookException error) {
            ToastUtil.getInstance().show(this.f1427a, (int) C0376R.string.fail_fetch_fb_user_info);
        }
    }

    class C04353 implements WeiboAuthCallback {
        final /* synthetic */ LoginActivity f1431a;

        C04353(LoginActivity this$0) {
            this.f1431a = this$0;
        }

        public void onSuccess(String uid, String accessToken, long expiresTime) {
            this.f1431a.m734a(uid, accessToken, expiresTime);
        }

        public void onFail(String error) {
        }
    }

    protected int mo2117b() {
        return 0;
    }

    protected int mo2115a() {
        return C0376R.layout.activity_login;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m741e();
        m742f();
        m743g();
        EventBus.getDefault().register(this);
    }

    private void m741e() {
        if (!WechatHelper.getInstance().getIwxapi().isWXAppInstalled()) {
            this.mWechatLoginBtn.setVisibility(8);
        }
        RippleUtil.setRippleBackground(this, this.mFacebookLoginBtn, this.mWechatLoginBtn, this.mPhoneLoginBtn, this.mWeiboLoginBtn);
    }

    private void m742f() {
        m744h();
        RxView.clicks(this.mPhoneLoginBtn).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(LoginActivity$$Lambda$1.lambdaFactory$(this));
        RxView.clicks(this.mWechatLoginBtn).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(LoginActivity$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.mWeiboLoginBtn).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(LoginActivity$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.mServicesTerms).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(LoginActivity$$Lambda$4.lambdaFactory$(this));
    }

    /* synthetic */ void m762d(Object aVoid) throws Exception {
        m745i();
    }

    /* synthetic */ void m761c(Object aVoid) throws Exception {
        m746j();
    }

    /* synthetic */ void m759b(Object aVoid) throws Exception {
        m747k();
    }

    /* synthetic */ void m755a(Object aVoid) throws Exception {
        m748l();
    }

    private void m743g() {
        new RxPermissions(this).request(new String[]{"android.permission.ACCESS_FINE_LOCATION"}).filter(LoginActivity$$Lambda$5.lambdaFactory$()).subscribe(LoginActivity$$Lambda$6.lambdaFactory$(this));
    }

    static /* synthetic */ boolean m736b(Boolean aBoolean) throws Exception {
        return !aBoolean.booleanValue();
    }

    /* synthetic */ void m754a(Boolean aBoolean) throws Exception {
        LocationHelper.INSTANCE.requestLocation(this);
    }

    private void m744h() {
        if (this.f1437b == null) {
            this.f1437b = new LoginButton(this);
            this.f1438d = new FacebookHelper();
            this.f1438d.initFBLoginButton(this.mFacebookLoginBtn, this.f1437b, null, new C04331(this), LoginActivity$$Lambda$7.lambdaFactory$());
        }
    }

    static /* synthetic */ void m737c() {
    }

    private void m733a(final AccessToken accessToken, final FacebookUserInfo userInfo) {
        getDataLayer().getUserManager().facebookOauthAction(accessToken.getToken(), userInfo.id).compose(bindUntilEvent(ActivityEvent.DESTROY)).compose(SchedulersCompat.applyIoSchedulers()).subscribe(new DisposableObserver<Boolean>(this) {
            final /* synthetic */ LoginActivity f1430c;

            public void onStart() {
                LoadingDialog.startLoading(this.f1430c, (int) C0376R.string.logining);
            }

            public void onComplete() {
            }

            public void onError(Throwable e) {
                LoginActivity.f1436a.m264e("facebook authorize fail", e);
                LoadingDialog.stopLoading();
            }

            public void onNext(Boolean aBoolean) {
                LoadingDialog.stopLoading();
                if (aBoolean.booleanValue()) {
                    Bundle args = new Bundle();
                    args.putParcelable("PARAM_KEY_FB_USER", Parcels.wrap(userInfo));
                    args.putString("PARAM_KEY_FBTOKEN", accessToken.getToken());
                    args.putInt("PARAM_KEY_USER_TYPE", 16);
                    this.f1430c.m738c(args);
                    return;
                }
                PreferenceUtil.setFacebookLogin();
                this.f1430c.m750n();
            }
        });
    }

    private void m745i() {
        this.f1439e = null;
        launchActivity(PhoneLoginActivity.class);
    }

    private void m746j() {
        this.f1439e = null;
        Bundle args = new Bundle();
        args.putInt("PARAM_KEY_WX_OP_TYPE", 1);
        launchActivity(WXEntryActivity.class, args);
    }

    private void m747k() {
        this.f1439e = null;
        this.f1439e = WeiboServiceHelper.getInstance().loginWeibo(this, new C04353(this));
    }

    private void m748l() {
        DataLayer.getInstance().getAppManager().getConfigCache().compose(SchedulersCompat.applyIoSchedulers()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(LoginActivity$$Lambda$8.lambdaFactory$(this), LoginActivity$$Lambda$9.lambdaFactory$(this));
    }

    /* synthetic */ void m758b(ConfigInfo configInfo) throws Exception {
        WebBrowserActivity.launchWeb(this, configInfo.getPrivacyUrl());
    }

    /* synthetic */ void m760b(Throwable throwable) throws Exception {
        WebBrowserActivity.launchWeb(this, "http://live.tikiapp.im/law.html");
    }

    private void m734a(String uid, String accessToken, long expiresTime) {
        final String str = uid;
        final String str2 = accessToken;
        final long j = expiresTime;
        getDataLayer().getUserManager().sinaOauthAction(accessToken, Long.valueOf(uid).longValue()).compose(SchedulersCompat.applyIoSchedulers()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Observer<Boolean>(this) {
            final /* synthetic */ LoginActivity f1435d;

            public void onSubscribe(Disposable d) {
                LoadingDialog.startLoading(this.f1435d, (int) C0376R.string.logining);
            }

            public void onComplete() {
            }

            public void onError(Throwable e) {
                LoginActivity.f1436a.m264e("weibo error", e);
                LoadingDialog.stopLoading();
            }

            public void onNext(Boolean aBoolean) {
                LoadingDialog.stopLoading();
                if (aBoolean.booleanValue()) {
                    Bundle args = new Bundle();
                    args.putParcelable("PARAM_KEY_WBTOKEN", Parcels.wrap(new WbToken(str, str2, j)));
                    args.putInt("PARAM_KEY_USER_TYPE", 8);
                    this.f1435d.m738c(args);
                    return;
                }
                this.f1435d.m749m();
            }
        });
    }

    private void m749m() {
        m750n();
    }

    private void m738c(Bundle userArgs) {
        Bundle args = new Bundle();
        args.putString("PARAM_KEY_FRAGMENT", UpdateProfileFragment.class.getName());
        args.putBundle("PARAM_KEY_FRAGMENT_ARGS", userArgs);
        launchActivity(FragmentContainerActivity.class, args);
        finish();
    }

    private void m750n() {
        getDataLayer().getAppManager().getConfigCache().compose(SchedulersCompat.applyIoSchedulers()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(LoginActivity$$Lambda$10.lambdaFactory$(this), LoginActivity$$Lambda$11.lambdaFactory$(this));
    }

    /* synthetic */ void m753a(ConfigInfo configInfo) throws Exception {
        if (configInfo == null || configInfo.isHideRndMatch()) {
            launchActivity(FriendActivity.class);
        } else {
            launchActivity(CallActivity.class);
        }
        finish();
    }

    /* synthetic */ void m756a(Throwable throwable) throws Exception {
        launchActivity(FriendActivity.class);
    }

    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        WeiboServiceHelper.getInstance().reset();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.f1439e != null) {
            this.f1439e.authorizeCallBack(requestCode, resultCode, data);
        } else if (this.f1438d != null) {
            this.f1438d.getFacebookCallbackManager().onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWxTokenEvent(WxTokenEvent event) {
        if (event.f527a) {
            Bundle args = new Bundle();
            args.putParcelable("PARAM_KEY_WXTOKEN", Parcels.wrap(event.f528b));
            args.putInt("PARAM_KEY_USER_TYPE", 4);
            m738c(args);
            return;
        }
        m750n();
    }
}
