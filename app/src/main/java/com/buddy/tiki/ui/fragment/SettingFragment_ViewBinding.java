package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.WrapContentDraweeView;

public class SettingFragment_ViewBinding implements Unbinder {
    private SettingFragment f2217b;

    @UiThread
    public SettingFragment_ViewBinding(SettingFragment target, View source) {
        this.f2217b = target;
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mModifyProfileButton = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.modify_profile_btn, "field 'mModifyProfileButton'", AppCompatTextView.class);
        target.mNotificationButton = (SwitchCompat) Utils.findRequiredViewAsType(source, C0376R.id.notification_btn, "field 'mNotificationButton'", SwitchCompat.class);
        target.mFeedbackButton = Utils.findRequiredView(source, C0376R.id.feedback_btn, "field 'mFeedbackButton'");
        target.mAboutButton = Utils.findRequiredView(source, C0376R.id.about_layout, "field 'mAboutButton'");
        target.mVersionCode = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.version_code, "field 'mVersionCode'", AppCompatTextView.class);
        target.mLogoutButton = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.logout_btn, "field 'mLogoutButton'", AppCompatTextView.class);
        target.mAgreementButton = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.agreement_btn, "field 'mAgreementButton'", AppCompatTextView.class);
        target.mFeedbackBanner = (WrapContentDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.feedback_banner, "field 'mFeedbackBanner'", WrapContentDraweeView.class);
        target.mDataUsage = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.data_usage, "field 'mDataUsage'", AppCompatTextView.class);
        target.mDataUsageLayout = Utils.findRequiredView(source, C0376R.id.data_usage_layout, "field 'mDataUsageLayout'");
        target.mSpringLayout = (ViewGroup) Utils.findRequiredViewAsType(source, C0376R.id.spring_layout, "field 'mSpringLayout'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        SettingFragment target = this.f2217b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2217b = null;
        target.mToolbar = null;
        target.mModifyProfileButton = null;
        target.mNotificationButton = null;
        target.mFeedbackButton = null;
        target.mAboutButton = null;
        target.mVersionCode = null;
        target.mLogoutButton = null;
        target.mAgreementButton = null;
        target.mFeedbackBanner = null;
        target.mDataUsage = null;
        target.mDataUsageLayout = null;
        target.mSpringLayout = null;
    }
}
