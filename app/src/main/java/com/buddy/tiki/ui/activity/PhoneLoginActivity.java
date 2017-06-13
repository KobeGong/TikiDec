package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.fragment.UpdateProfileFragment;
import com.buddy.tiki.util.SchedulersCompat;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import java.util.concurrent.TimeUnit;

public class PhoneLoginActivity extends BaseActivity {
    private DisposableObserver<Long> f1456a;
    private ConfigInfo f1457b;
    @BindView(2131820768)
    AppCompatEditText mAuthCodeInput;
    @BindView(2131820769)
    AppCompatButton mLoginButton;
    @BindView(2131820766)
    AppCompatEditText mPhoneInput;
    @BindView(2131820688)
    Toolbar mToolbar;
    @BindView(2131820767)
    AppCompatButton mVerifyButton;

    class C04371 implements CompletableObserver {
        final /* synthetic */ PhoneLoginActivity f1454a;

        C04371(PhoneLoginActivity this$0) {
            this.f1454a = this$0;
        }

        public void onSubscribe(Disposable d) {
            this.f1454a.m774h();
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            this.f1454a.f1456a.dispose();
        }
    }

    class C04382 extends DisposableObserver<Long> {
        final /* synthetic */ PhoneLoginActivity f1455a;

        C04382(PhoneLoginActivity this$0) {
            this.f1455a = this$0;
        }

        public void onStart() {
            this.f1455a.mVerifyButton.setEnabled(false);
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
        }

        public void onNext(Long aLong) {
            this.f1455a.mVerifyButton.setText(String.valueOf(60 - aLong.longValue()));
            if (aLong.longValue() >= 60) {
                dispose();
            }
        }
    }

    protected int mo2115a() {
        return C0376R.layout.activity_phone_login;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m770d();
        m771e();
        getDataLayer().getAppManager().getConfigCache().compose(SchedulersCompat.applyIoSchedulers()).compose(bindToLifecycle()).subscribe(PhoneLoginActivity$$Lambda$1.lambdaFactory$(this), PhoneLoginActivity$$Lambda$2.lambdaFactory$());
    }

    /* synthetic */ void m777a(ConfigInfo configInfo) throws Exception {
        this.f1457b = configInfo;
    }

    static /* synthetic */ void m769b(Throwable throwable) throws Exception {
    }

    protected int mo2117b() {
        return 0;
    }

    private void m770d() {
        setTitle(BuildConfig.VERSION_NAME);
        setSupportActionBar(this.mToolbar);
    }

    private void m771e() {
        RxToolbar.navigationClicks(this.mToolbar).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindToLifecycle()).subscribe(PhoneLoginActivity$$Lambda$3.lambdaFactory$(this));
        Observable.combineLatest(RxTextView.afterTextChangeEvents(this.mPhoneInput).compose(bindUntilEvent(ActivityEvent.DESTROY)).map(PhoneLoginActivity$$Lambda$4.lambdaFactory$()).doOnNext(RxView.enabled(this.mVerifyButton)), RxTextView.afterTextChangeEvents(this.mAuthCodeInput).compose(bindUntilEvent(ActivityEvent.DESTROY)).map(PhoneLoginActivity$$Lambda$5.lambdaFactory$()), PhoneLoginActivity$$Lambda$6.lambdaFactory$()).compose(bindToLifecycle()).subscribe(RxView.enabled(this.mLoginButton));
        RxView.clicks(this.mVerifyButton).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindToLifecycle()).subscribe(PhoneLoginActivity$$Lambda$7.lambdaFactory$(this));
        RxView.clicks(this.mLoginButton).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindToLifecycle()).subscribe(PhoneLoginActivity$$Lambda$8.lambdaFactory$(this));
    }

    /* synthetic */ void m783c(Object avoid) throws Exception {
        finish();
    }

    static /* synthetic */ Boolean m768b(TextViewAfterTextChangeEvent event) throws Exception {
        return Boolean.valueOf(event.editable().toString().trim().length() > 0);
    }

    static /* synthetic */ Boolean m763a(TextViewAfterTextChangeEvent event) throws Exception {
        return Boolean.valueOf(event.editable().toString().trim().length() > 0);
    }

    /* synthetic */ void m781b(Object aVoid) throws Exception {
        m772f();
    }

    /* synthetic */ void m778a(Object aVoid) throws Exception {
        m773g();
    }

    private void m772f() {
        getDataLayer().getUserManager().sendAuthCodeAction(this.mPhoneInput.getEditableText().toString().trim().trim(), 1).compose(bindToLifecycle()).compose(SchedulersCompat.applyIOCompletableSchedulers()).subscribe(new C04371(this));
    }

    private void m773g() {
        hideKeyBoard();
        String phone = this.mPhoneInput.getEditableText().toString().trim();
        if (phone.contains(" ")) {
            Snackbar.make(this.mPhoneInput, (int) C0376R.string.phone_blank_error, 0).show();
            return;
        }
        String authCode = this.mAuthCodeInput.getEditableText().toString().trim();
        getDataLayer().getUserManager().signInAction(phone, authCode, 86).compose(SchedulersCompat.applyIoSchedulers()).compose(bindToLifecycle()).subscribe(PhoneLoginActivity$$Lambda$9.lambdaFactory$(this, phone, authCode), PhoneLoginActivity$$Lambda$10.lambdaFactory$());
    }

    /* synthetic */ void m779a(String phone, String authCode, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue()) {
            Bundle args = new Bundle();
            args.putString("PARAM_KEY_FRAGMENT", UpdateProfileFragment.class.getName());
            Bundle userArgs = new Bundle();
            userArgs.putInt("PARAM_KEY_USER_TYPE", 1);
            userArgs.putString("PARAM_KEY_PHONE", phone);
            userArgs.putInt("PARAM_KEY_COUNTRYCODE", 86);
            userArgs.putString("PARAM_KEY_AUTHCODE", authCode);
            args.putBundle("PARAM_KEY_FRAGMENT_ARGS", userArgs);
            launchActivity(FragmentContainerActivity.class, args, 268468224);
        } else if (this.f1457b == null || this.f1457b.isHideRndMatch()) {
            launchActivity(FriendActivity.class, 268468224);
        } else {
            launchActivity(CallActivity.class, 268468224);
        }
        finish();
    }

    static /* synthetic */ void m766a(Throwable throwable) throws Exception {
    }

    private void m774h() {
        this.f1456a = new C04382(this);
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindUntilEvent(ActivityEvent.DESTROY)).unsubscribeOn(AndroidSchedulers.mainThread()).doOnDispose(PhoneLoginActivity$$Lambda$11.lambdaFactory$(this)).subscribe(this.f1456a);
    }

    /* synthetic */ void m782c() throws Exception {
        this.mVerifyButton.setEnabled(true);
        this.mVerifyButton.setText(C0376R.string.phone_verify);
    }
}
