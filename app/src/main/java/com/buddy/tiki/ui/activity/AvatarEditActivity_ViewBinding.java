package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.PinchToZoomDraweeView;
import com.buddy.tiki.view.UnlimitedSizeLayout;

public class AvatarEditActivity_ViewBinding implements Unbinder {
    private AvatarEditActivity f1136b;

    @UiThread
    public AvatarEditActivity_ViewBinding(AvatarEditActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public AvatarEditActivity_ViewBinding(AvatarEditActivity target, View source) {
        this.f1136b = target;
        target.mUpperCover = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.upper_cover, "field 'mUpperCover'", RelativeLayout.class);
        target.mChooseArea = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.choose_area, "field 'mChooseArea'", RelativeLayout.class);
        target.mLowerCover = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.lower_cover, "field 'mLowerCover'", RelativeLayout.class);
        target.mImagePreviewLayout = (UnlimitedSizeLayout) Utils.findRequiredViewAsType(source, C0376R.id.image_preview_layout, "field 'mImagePreviewLayout'", UnlimitedSizeLayout.class);
        target.mImagePreview = (PinchToZoomDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.image_preview, "field 'mImagePreview'", PinchToZoomDraweeView.class);
        target.mCancel = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.cancel_btn, "field 'mCancel'", AppCompatTextView.class);
        target.mChoose = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.choose_btn, "field 'mChoose'", AppCompatTextView.class);
        target.mRootLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.root_layout, "field 'mRootLayout'", LinearLayout.class);
        target.mMask = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.mask, "field 'mMask'", RelativeLayout.class);
    }

    @CallSuper
    public void unbind() {
        AvatarEditActivity target = this.f1136b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1136b = null;
        target.mUpperCover = null;
        target.mChooseArea = null;
        target.mLowerCover = null;
        target.mImagePreviewLayout = null;
        target.mImagePreview = null;
        target.mCancel = null;
        target.mChoose = null;
        target.mRootLayout = null;
        target.mMask = null;
    }
}
