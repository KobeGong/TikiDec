package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class PhoneLoginActivity_ViewBinding implements Unbinder {
    private PhoneLoginActivity f1458b;

    @UiThread
    public PhoneLoginActivity_ViewBinding(PhoneLoginActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public PhoneLoginActivity_ViewBinding(PhoneLoginActivity target, View source) {
        this.f1458b = target;
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mPhoneInput = (AppCompatEditText) Utils.findRequiredViewAsType(source, C0376R.id.phone_input, "field 'mPhoneInput'", AppCompatEditText.class);
        target.mVerifyButton = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.verify_btn, "field 'mVerifyButton'", AppCompatButton.class);
        target.mAuthCodeInput = (AppCompatEditText) Utils.findRequiredViewAsType(source, C0376R.id.authcode_input, "field 'mAuthCodeInput'", AppCompatEditText.class);
        target.mLoginButton = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.login_btn, "field 'mLoginButton'", AppCompatButton.class);
    }

    @CallSuper
    public void unbind() {
        PhoneLoginActivity target = this.f1458b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1458b = null;
        target.mToolbar = null;
        target.mPhoneInput = null;
        target.mVerifyButton = null;
        target.mAuthCodeInput = null;
        target.mLoginButton = null;
    }
}
