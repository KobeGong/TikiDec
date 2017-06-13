package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.facebook.drawee.view.SimpleDraweeView;

public class UpdateProfileFragment_ViewBinding implements Unbinder {
    private UpdateProfileFragment f2270b;

    @UiThread
    public UpdateProfileFragment_ViewBinding(UpdateProfileFragment target, View source) {
        this.f2270b = target;
        target.mGenderGroup = (RadioGroup) Utils.findRequiredViewAsType(source, C0376R.id.gender_group, "field 'mGenderGroup'", RadioGroup.class);
        target.mGenderMale = (AppCompatRadioButton) Utils.findRequiredViewAsType(source, C0376R.id.gender_male, "field 'mGenderMale'", AppCompatRadioButton.class);
        target.mGenderFemale = (AppCompatRadioButton) Utils.findRequiredViewAsType(source, C0376R.id.gender_female, "field 'mGenderFemale'", AppCompatRadioButton.class);
        target.mNickInput = (AppCompatEditText) Utils.findRequiredViewAsType(source, C0376R.id.nick_input, "field 'mNickInput'", AppCompatEditText.class);
        target.mDoneButton = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.done_btn, "field 'mDoneButton'", AppCompatButton.class);
        target.mAvatar = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.avatar, "field 'mAvatar'", RelativeLayout.class);
        target.mAvatarImage = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.avatar_image, "field 'mAvatarImage'", SimpleDraweeView.class);
        target.mPromotionCodeInput = (AppCompatEditText) Utils.findRequiredViewAsType(source, C0376R.id.promotion_code_input, "field 'mPromotionCodeInput'", AppCompatEditText.class);
        target.mGenderChooseTips = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.gender_choose_tips, "field 'mGenderChooseTips'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        UpdateProfileFragment target = this.f2270b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2270b = null;
        target.mGenderGroup = null;
        target.mGenderMale = null;
        target.mGenderFemale = null;
        target.mNickInput = null;
        target.mDoneButton = null;
        target.mAvatar = null;
        target.mAvatarImage = null;
        target.mPromotionCodeInput = null;
        target.mGenderChooseTips = null;
    }
}
