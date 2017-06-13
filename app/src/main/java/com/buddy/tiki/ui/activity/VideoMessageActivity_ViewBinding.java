package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.video.IjkVideoView;
import com.facebook.drawee.view.SimpleDraweeView;

public class VideoMessageActivity_ViewBinding implements Unbinder {
    private VideoMessageActivity f1497b;

    @UiThread
    public VideoMessageActivity_ViewBinding(VideoMessageActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public VideoMessageActivity_ViewBinding(VideoMessageActivity target, View source) {
        this.f1497b = target;
        target.mNickView = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.nick, "field 'mNickView'", TextView.class);
        target.mTime = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.time, "field 'mTime'", TextView.class);
        target.mReport = (ImageView) Utils.findRequiredViewAsType(source, C0376R.id.report, "field 'mReport'", ImageView.class);
        target.mClose = (ImageView) Utils.findRequiredViewAsType(source, C0376R.id.close, "field 'mClose'", ImageView.class);
        target.mVideoMessageView = (IjkVideoView) Utils.findRequiredViewAsType(source, C0376R.id.video_message, "field 'mVideoMessageView'", IjkVideoView.class);
        target.mFingerPrint = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.finger_print, "field 'mFingerPrint'", SimpleDraweeView.class);
        target.mCoverLayout = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.cover, "field 'mCoverLayout'", RelativeLayout.class);
        target.mPurchaseHint = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.purchase_hint, "field 'mPurchaseHint'", TextView.class);
        target.mVideoCover = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.video_cover, "field 'mVideoCover'", SimpleDraweeView.class);
        target.mReply = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.reply, "field 'mReply'", AppCompatTextView.class);
        target.formatString = source.getContext().getResources().getString(C0376R.string.video_message_time_len);
    }

    @CallSuper
    public void unbind() {
        VideoMessageActivity target = this.f1497b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1497b = null;
        target.mNickView = null;
        target.mTime = null;
        target.mReport = null;
        target.mClose = null;
        target.mVideoMessageView = null;
        target.mFingerPrint = null;
        target.mCoverLayout = null;
        target.mPurchaseHint = null;
        target.mVideoCover = null;
        target.mReply = null;
    }
}
