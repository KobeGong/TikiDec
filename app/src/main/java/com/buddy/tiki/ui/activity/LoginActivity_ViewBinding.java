package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class LoginActivity_ViewBinding implements Unbinder {
    private LoginActivity f1440b;

    @UiThread
    public LoginActivity_ViewBinding(LoginActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public LoginActivity_ViewBinding(LoginActivity target, View source) {
        this.f1440b = target;
        target.mFacebookLoginBtn = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.login_facebook, "field 'mFacebookLoginBtn'", AppCompatImageView.class);
        target.mPhoneLoginBtn = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.login_phone, "field 'mPhoneLoginBtn'", AppCompatImageView.class);
        target.mWechatLoginBtn = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.login_wechat, "field 'mWechatLoginBtn'", AppCompatImageView.class);
        target.mWeiboLoginBtn = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.login_weibo, "field 'mWeiboLoginBtn'", AppCompatImageView.class);
        target.mServicesTerms = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.service_terms, "field 'mServicesTerms'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        LoginActivity target = this.f1440b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1440b = null;
        target.mFacebookLoginBtn = null;
        target.mPhoneLoginBtn = null;
        target.mWechatLoginBtn = null;
        target.mWeiboLoginBtn = null;
        target.mServicesTerms = null;
    }
}
