package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.superrecyclerview.SuperRecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;

public class ChatMessageFragment_ViewBinding implements Unbinder {
    private ChatMessageFragment f2123b;

    @UiThread
    public ChatMessageFragment_ViewBinding(ChatMessageFragment target, View source) {
        this.f2123b = target;
        target.rootLayout = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.root_layout, "field 'rootLayout'", RelativeLayout.class);
        target.messageList = (SuperRecyclerView) Utils.findRequiredViewAsType(source, C0376R.id.msg_list, "field 'messageList'", SuperRecyclerView.class);
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mMenuButton = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.menu_btn, "field 'mMenuButton'", AppCompatImageView.class);
        target.mChatMessageNick = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.chat_message_nick, "field 'mChatMessageNick'", AppCompatTextView.class);
        target.swipeRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(source, C0376R.id.swipe_refresh, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
        target.record = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.msg_record_video, "field 'record'", AppCompatImageView.class);
        target.call = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.msg_call, "field 'call'", AppCompatImageView.class);
        target.videoChatTip = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.video_chat_tip, "field 'videoChatTip'", LinearLayout.class);
        target.videoChatTipText = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.video_chat_tip_text, "field 'videoChatTipText'", AppCompatTextView.class);
        target.mGiftShow = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.gift_show, "field 'mGiftShow'", SimpleDraweeView.class);
        target.giftLayout = Utils.findRequiredView(source, C0376R.id.gift_layout, "field 'giftLayout'");
        target.sendGift = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.send_gift, "field 'sendGift'", AppCompatImageView.class);
        target.sendText = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.send_text, "field 'sendText'", AppCompatButton.class);
        target.textMessage = (AppCompatEditText) Utils.findRequiredViewAsType(source, C0376R.id.text_message, "field 'textMessage'", AppCompatEditText.class);
        target.mGiftList = (RecyclerView) Utils.findRequiredViewAsType(source, C0376R.id.data_list, "field 'mGiftList'", RecyclerView.class);
        target.mDiamondText = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.diamond_num, "field 'mDiamondText'", AppCompatTextView.class);
        target.mRechargeButton = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.recharge_btn, "field 'mRechargeButton'", AppCompatTextView.class);
        target.mPresentButton = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.present_btn, "field 'mPresentButton'", AppCompatButton.class);
    }

    @CallSuper
    public void unbind() {
        ChatMessageFragment target = this.f2123b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2123b = null;
        target.rootLayout = null;
        target.messageList = null;
        target.mToolbar = null;
        target.mMenuButton = null;
        target.mChatMessageNick = null;
        target.swipeRefreshLayout = null;
        target.record = null;
        target.call = null;
        target.videoChatTip = null;
        target.videoChatTipText = null;
        target.mGiftShow = null;
        target.giftLayout = null;
        target.sendGift = null;
        target.sendText = null;
        target.textMessage = null;
        target.mGiftList = null;
        target.mDiamondText = null;
        target.mRechargeButton = null;
        target.mPresentButton = null;
    }
}
