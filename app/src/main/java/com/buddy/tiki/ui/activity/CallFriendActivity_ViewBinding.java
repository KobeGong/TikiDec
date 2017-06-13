package com.buddy.tiki.ui.activity;

import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.BorderSurfaceView;
import com.buddy.tiki.view.BottomNavigationView;
import com.buddy.tiki.wertc.PercentFrameLayout;
import com.facebook.drawee.view.SimpleDraweeView;

public class CallFriendActivity_ViewBinding implements Unbinder {
    private CallFriendActivity f1398b;

    @UiThread
    public CallFriendActivity_ViewBinding(CallFriendActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public CallFriendActivity_ViewBinding(CallFriendActivity target, View source) {
        this.f1398b = target;
        target.mRootLayout = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.call_layout, "field 'mRootLayout'", RelativeLayout.class);
        target.mLocalRender = (BorderSurfaceView) Utils.findRequiredViewAsType(source, C0376R.id.local_video_view, "field 'mLocalRender'", BorderSurfaceView.class);
        target.mRemoteRender = (BorderSurfaceView) Utils.findRequiredViewAsType(source, C0376R.id.remote_video_view, "field 'mRemoteRender'", BorderSurfaceView.class);
        target.mRemoteRenderLayout = (PercentFrameLayout) Utils.findRequiredViewAsType(source, C0376R.id.remote_video_layout, "field 'mRemoteRenderLayout'", PercentFrameLayout.class);
        target.mGiftShow = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.gift_show, "field 'mGiftShow'", SimpleDraweeView.class);
        target.mLocalCountdownBorder = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.local_countdown_border, "field 'mLocalCountdownBorder'", RelativeLayout.class);
        target.mCallMainLayout = (FrameLayout) Utils.findRequiredViewAsType(source, C0376R.id.controller_fragment_container, "field 'mCallMainLayout'", FrameLayout.class);
        target.mNick = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.user_nick, "field 'mNick'", AppCompatTextView.class);
        target.mTime = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.connection_time, "field 'mTime'", AppCompatTextView.class);
        target.mPreviewResolution = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.preview_resolution, "field 'mPreviewResolution'", TextView.class);
        target.mBottomNavigationView = (BottomNavigationView) Utils.findRequiredViewAsType(source, C0376R.id.bottom_navi, "field 'mBottomNavigationView'", BottomNavigationView.class);
        Resources res = source.getContext().getResources();
        target.mFriendVideoCallTime = res.getString(C0376R.string.friend_video_time);
        target.mWithouAnswer = res.getString(C0376R.string.friend_video_call_without_answer);
    }

    @CallSuper
    public void unbind() {
        CallFriendActivity target = this.f1398b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1398b = null;
        target.mRootLayout = null;
        target.mLocalRender = null;
        target.mRemoteRender = null;
        target.mRemoteRenderLayout = null;
        target.mGiftShow = null;
        target.mLocalCountdownBorder = null;
        target.mCallMainLayout = null;
        target.mNick = null;
        target.mTime = null;
        target.mPreviewResolution = null;
        target.mBottomNavigationView = null;
    }
}
