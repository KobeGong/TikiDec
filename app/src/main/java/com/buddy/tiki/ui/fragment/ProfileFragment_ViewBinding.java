package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.facebook.drawee.view.SimpleDraweeView;

public class ProfileFragment_ViewBinding implements Unbinder {
    private ProfileFragment f2191b;

    @UiThread
    public ProfileFragment_ViewBinding(ProfileFragment target, View source) {
        this.f2191b = target;
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mAvatarLayout = Utils.findRequiredView(source, C0376R.id.avatar_layout, "field 'mAvatarLayout'");
        target.mAvatar = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.avatar, "field 'mAvatar'", SimpleDraweeView.class);
        target.mNickLayout = Utils.findRequiredView(source, C0376R.id.nick_layout, "field 'mNickLayout'");
        target.mNick = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.nick, "field 'mNick'", AppCompatTextView.class);
        target.mGenderLayout = Utils.findRequiredView(source, C0376R.id.gender_layout, "field 'mGenderLayout'");
        target.mGender = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.gender, "field 'mGender'", AppCompatTextView.class);
        target.mTikiLayout = Utils.findRequiredView(source, C0376R.id.tiki_layout, "field 'mTikiLayout'");
        target.mTiki = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.tiki, "field 'mTiki'", AppCompatTextView.class);
        target.mSpringLayout = (ViewGroup) Utils.findRequiredViewAsType(source, C0376R.id.spring_layout, "field 'mSpringLayout'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        ProfileFragment target = this.f2191b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2191b = null;
        target.mToolbar = null;
        target.mAvatarLayout = null;
        target.mAvatar = null;
        target.mNickLayout = null;
        target.mNick = null;
        target.mGenderLayout = null;
        target.mGender = null;
        target.mTikiLayout = null;
        target.mTiki = null;
        target.mSpringLayout = null;
    }
}
