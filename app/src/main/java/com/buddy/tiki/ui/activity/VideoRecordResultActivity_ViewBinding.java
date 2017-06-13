package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.video.IjkVideoView;
import com.facebook.drawee.view.SimpleDraweeView;

public class VideoRecordResultActivity_ViewBinding implements Unbinder {
    private VideoRecordResultActivity f1584b;

    @UiThread
    public VideoRecordResultActivity_ViewBinding(VideoRecordResultActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public VideoRecordResultActivity_ViewBinding(VideoRecordResultActivity target, View source) {
        this.f1584b = target;
        target.friendsView = (RecyclerView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_friends, "field 'friendsView'", RecyclerView.class);
        target.addFriend = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_add_friends, "field 'addFriend'", AppCompatImageView.class);
        target.sendVideoRecord = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_send, "field 'sendVideoRecord'", AppCompatImageView.class);
        target.close = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_close, "field 'close'", AppCompatImageView.class);
        target.arrow = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_show_pay_arrow, "field 'arrow'", AppCompatImageView.class);
        target.avatar = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.avatar, "field 'avatar'", SimpleDraweeView.class);
        target.notVipUserName = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_send_to_user, "field 'notVipUserName'", AppCompatTextView.class);
        target.diamondFree = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.diamond_free, "field 'diamondFree'", LinearLayout.class);
        target.diamondOne = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.diamond_one, "field 'diamondOne'", LinearLayout.class);
        target.diamondTwo = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.diamond_two, "field 'diamondTwo'", LinearLayout.class);
        target.diamondFreeText = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.diamond_free_text, "field 'diamondFreeText'", AppCompatTextView.class);
        target.diamondOneText = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.diamond_one_text, "field 'diamondOneText'", AppCompatTextView.class);
        target.diamondTwoText = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.diamond_two_text, "field 'diamondTwoText'", AppCompatTextView.class);
        target.diamondFreeIcon = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.diamond_free_icon, "field 'diamondFreeIcon'", AppCompatImageView.class);
        target.diamondOneIcon = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.diamond_one_icon, "field 'diamondOneIcon'", AppCompatImageView.class);
        target.diamondTwoIcon = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.diamond_two_icon, "field 'diamondTwoIcon'", AppCompatImageView.class);
        target.vipFriendLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.send_layout_vip, "field 'vipFriendLayout'", LinearLayout.class);
        target.notVipFriendLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.send_layout_notvip, "field 'notVipFriendLayout'", LinearLayout.class);
        target.showPayLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.video_record_show_pay, "field 'showPayLayout'", LinearLayout.class);
        target.setDiamonds = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.setDiamonds, "field 'setDiamonds'", LinearLayout.class);
        target.showPayNum = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.video_record_show_pay_num, "field 'showPayNum'", AppCompatTextView.class);
        target.notVipDiamondsNum = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.notvip_diamonds_num, "field 'notVipDiamondsNum'", AppCompatTextView.class);
        target.setDiamondOk = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.set_diamond_ok, "field 'setDiamondOk'", AppCompatButton.class);
        target.mVideoView = (IjkVideoView) Utils.findRequiredViewAsType(source, C0376R.id.video_view, "field 'mVideoView'", IjkVideoView.class);
        target.shadow = Utils.findRequiredView(source, C0376R.id.shadow, "field 'shadow'");
    }

    @CallSuper
    public void unbind() {
        VideoRecordResultActivity target = this.f1584b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1584b = null;
        target.friendsView = null;
        target.addFriend = null;
        target.sendVideoRecord = null;
        target.close = null;
        target.arrow = null;
        target.avatar = null;
        target.notVipUserName = null;
        target.diamondFree = null;
        target.diamondOne = null;
        target.diamondTwo = null;
        target.diamondFreeText = null;
        target.diamondOneText = null;
        target.diamondTwoText = null;
        target.diamondFreeIcon = null;
        target.diamondOneIcon = null;
        target.diamondTwoIcon = null;
        target.vipFriendLayout = null;
        target.notVipFriendLayout = null;
        target.showPayLayout = null;
        target.setDiamonds = null;
        target.showPayNum = null;
        target.notVipDiamondsNum = null;
        target.setDiamondOk = null;
        target.mVideoView = null;
        target.shadow = null;
    }
}
