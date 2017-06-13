package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.BorderSurfaceView;
import com.buddy.tiki.view.BottomNavigationView;
import com.buddy.tiki.view.CountDownLayout;
import com.buddy.tiki.view.FaceDetectCover;
import com.buddy.tiki.view.MatchFilterTextView;
import com.buddy.tiki.view.WebSocketConnectionHint;
import com.buddy.tiki.view.match.MatchLayout;
import com.buddy.tiki.view.match.SimpleMatchingView;
import com.buddy.tiki.wertc.PercentFrameLayout;
import com.facebook.drawee.view.SimpleDraweeView;

public class CallActivity_ViewBinding implements Unbinder {
    private CallActivity f1292b;

    @UiThread
    public CallActivity_ViewBinding(CallActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public CallActivity_ViewBinding(CallActivity target, View source) {
        this.f1292b = target;
        target.mRootLayout = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.call_layout, "field 'mRootLayout'", RelativeLayout.class);
        target.mLocalRender = (BorderSurfaceView) Utils.findRequiredViewAsType(source, C0376R.id.local_video_view, "field 'mLocalRender'", BorderSurfaceView.class);
        target.mRemoteRender = (BorderSurfaceView) Utils.findRequiredViewAsType(source, C0376R.id.remote_video_view, "field 'mRemoteRender'", BorderSurfaceView.class);
        target.mRemoteRenderLayout = (PercentFrameLayout) Utils.findRequiredViewAsType(source, C0376R.id.large_preview, "field 'mRemoteRenderLayout'", PercentFrameLayout.class);
        target.mLocalPreviewLayout = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.small_preview_layout, "field 'mLocalPreviewLayout'", RelativeLayout.class);
        target.mLocalCountdownBorder = (CountDownLayout) Utils.findRequiredViewAsType(source, C0376R.id.small_preview, "field 'mLocalCountdownBorder'", CountDownLayout.class);
        target.mGiftShow = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.gift_show, "field 'mGiftShow'", SimpleDraweeView.class);
        target.mUnblockButton = Utils.findRequiredView(source, C0376R.id.unblock_button, "field 'mUnblockButton'");
        target.mCallMainLayout = (FrameLayout) Utils.findRequiredViewAsType(source, C0376R.id.controller_fragment_container, "field 'mCallMainLayout'", FrameLayout.class);
        target.mPreviewResolution = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.preview_resolution, "field 'mPreviewResolution'", TextView.class);
        target.mFaceUnityBeauty = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.face_unity_beauty, "field 'mFaceUnityBeauty'", TextView.class);
        target.mColorLevelSeekBar = (SeekBar) Utils.findRequiredViewAsType(source, C0376R.id.color_level, "field 'mColorLevelSeekBar'", SeekBar.class);
        target.mColorLevelValue = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.color_level_value, "field 'mColorLevelValue'", TextView.class);
        target.mBlurRadiusSeekBar = (SeekBar) Utils.findRequiredViewAsType(source, C0376R.id.blur_radius, "field 'mBlurRadiusSeekBar'", SeekBar.class);
        target.mBlurRadiusValue = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.blur_radius_value, "field 'mBlurRadiusValue'", TextView.class);
        target.mCheekThinningSeekBar = (SeekBar) Utils.findRequiredViewAsType(source, C0376R.id.cheek_thinning, "field 'mCheekThinningSeekBar'", SeekBar.class);
        target.mCheekThinningValue = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.cheek_thinning_value, "field 'mCheekThinningValue'", TextView.class);
        target.mEyeEnlargingSeekBar = (SeekBar) Utils.findRequiredViewAsType(source, C0376R.id.eye_enlarging, "field 'mEyeEnlargingSeekBar'", SeekBar.class);
        target.mEyeEnlargingValue = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.eye_enlarging_value, "field 'mEyeEnlargingValue'", TextView.class);
        target.mMatchLayout = (MatchLayout) Utils.findRequiredViewAsType(source, C0376R.id.match_layout, "field 'mMatchLayout'", MatchLayout.class);
        target.mMatchView1 = Utils.findRequiredView(source, C0376R.id.match_1, "field 'mMatchView1'");
        target.mMatchView2 = Utils.findRequiredView(source, C0376R.id.match_2, "field 'mMatchView2'");
        target.mMatchView3 = Utils.findRequiredView(source, C0376R.id.match_3, "field 'mMatchView3'");
        target.mFriendPanel = Utils.findRequiredView(source, C0376R.id.friend_fragment_container, "field 'mFriendPanel'");
        target.mProfilePanel = Utils.findRequiredView(source, C0376R.id.you_fragment_container, "field 'mProfilePanel'");
        target.mHorizontalPanel = Utils.findRequiredView(source, C0376R.id.horizontal_panel, "field 'mHorizontalPanel'");
        target.mMoveDetectView = Utils.findRequiredView(source, C0376R.id.move_detect_view, "field 'mMoveDetectView'");
        target.mMatchFilter = (MatchFilterTextView) Utils.findRequiredViewAsType(source, C0376R.id.filter_match_btn, "field 'mMatchFilter'", MatchFilterTextView.class);
        target.mMatchFilterLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.filter_match_layout, "field 'mMatchFilterLayout'", LinearLayout.class);
        target.mSimpleMatchingView = (SimpleMatchingView) Utils.findRequiredViewAsType(source, C0376R.id.simple_match_view, "field 'mSimpleMatchingView'", SimpleMatchingView.class);
        target.mBottomNavigationView = (BottomNavigationView) Utils.findRequiredViewAsType(source, C0376R.id.bottom_navi, "field 'mBottomNavigationView'", BottomNavigationView.class);
        target.mUpGradientMask = Utils.findRequiredView(source, C0376R.id.up_gradient_mask, "field 'mUpGradientMask'");
        target.mDownGradientmask = Utils.findRequiredView(source, C0376R.id.down_gradient_mask, "field 'mDownGradientmask'");
        target.mFaceDetectCover = (FaceDetectCover) Utils.findRequiredViewAsType(source, C0376R.id.face_detect_cover, "field 'mFaceDetectCover'", FaceDetectCover.class);
        target.mConnectionHint = (WebSocketConnectionHint) Utils.findRequiredViewAsType(source, C0376R.id.connection_hint, "field 'mConnectionHint'", WebSocketConnectionHint.class);
        target.mTMoneyIncrement = (TextView) Utils.findRequiredViewAsType(source, C0376R.id.t_money_increment, "field 'mTMoneyIncrement'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        CallActivity target = this.f1292b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1292b = null;
        target.mRootLayout = null;
        target.mLocalRender = null;
        target.mRemoteRender = null;
        target.mRemoteRenderLayout = null;
        target.mLocalPreviewLayout = null;
        target.mLocalCountdownBorder = null;
        target.mGiftShow = null;
        target.mUnblockButton = null;
        target.mCallMainLayout = null;
        target.mPreviewResolution = null;
        target.mFaceUnityBeauty = null;
        target.mColorLevelSeekBar = null;
        target.mColorLevelValue = null;
        target.mBlurRadiusSeekBar = null;
        target.mBlurRadiusValue = null;
        target.mCheekThinningSeekBar = null;
        target.mCheekThinningValue = null;
        target.mEyeEnlargingSeekBar = null;
        target.mEyeEnlargingValue = null;
        target.mMatchLayout = null;
        target.mMatchView1 = null;
        target.mMatchView2 = null;
        target.mMatchView3 = null;
        target.mFriendPanel = null;
        target.mProfilePanel = null;
        target.mHorizontalPanel = null;
        target.mMoveDetectView = null;
        target.mMatchFilter = null;
        target.mMatchFilterLayout = null;
        target.mSimpleMatchingView = null;
        target.mBottomNavigationView = null;
        target.mUpGradientMask = null;
        target.mDownGradientmask = null;
        target.mFaceDetectCover = null;
        target.mConnectionHint = null;
        target.mTMoneyIncrement = null;
    }
}
