package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.CircleProgressView;
import com.buddy.tiki.view.SurfaceViewRenderer;
import com.facebook.drawee.view.SimpleDraweeView;

public class VideoRecordActivity_ViewBinding implements Unbinder {
    private VideoRecordActivity f1556b;

    @UiThread
    public VideoRecordActivity_ViewBinding(VideoRecordActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public VideoRecordActivity_ViewBinding(VideoRecordActivity target, View source) {
        this.f1556b = target;
        target.recordButton = (CircleProgressView) Utils.findRequiredViewAsType(source, C0376R.id.button_record, "field 'recordButton'", CircleProgressView.class);
        target.recordButtonIn = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.button_record_in, "field 'recordButtonIn'", AppCompatImageView.class);
        target.openMask = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_mask, "field 'openMask'", SimpleDraweeView.class);
        target.back = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_back, "field 'back'", AppCompatImageView.class);
        target.rotate = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_rotate, "field 'rotate'", AppCompatImageView.class);
        target.mRecordPreview = (SurfaceViewRenderer) Utils.findRequiredViewAsType(source, C0376R.id.video_record_preview, "field 'mRecordPreview'", SurfaceViewRenderer.class);
        target.recordTip = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.record_tip, "field 'recordTip'", LinearLayout.class);
        target.recordTipText = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.record_tip_text, "field 'recordTipText'", AppCompatTextView.class);
        target.maskTip = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.mask_tip, "field 'maskTip'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        VideoRecordActivity target = this.f1556b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1556b = null;
        target.recordButton = null;
        target.recordButtonIn = null;
        target.openMask = null;
        target.back = null;
        target.rotate = null;
        target.mRecordPreview = null;
        target.recordTip = null;
        target.recordTipText = null;
        target.maskTip = null;
    }
}
