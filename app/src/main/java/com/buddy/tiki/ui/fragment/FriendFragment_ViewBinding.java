package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.RoundTextView;
import com.buddy.tiki.view.superrecyclerview.SuperNestedRecyclerView;

public class FriendFragment_ViewBinding implements Unbinder {
    private FriendFragment f2155b;

    @UiThread
    public FriendFragment_ViewBinding(FriendFragment target, View source) {
        this.f2155b = target;
        target.mVideoButton = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.video_btn, "field 'mVideoButton'", AppCompatImageView.class);
        target.mVideoLayout = Utils.findRequiredView(source, C0376R.id.video_layout, "field 'mVideoLayout'");
        target.addFriend = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.add_friend, "field 'addFriend'", AppCompatImageView.class);
        target.mSearchView = (FrameLayout) Utils.findRequiredViewAsType(source, C0376R.id.search_view, "field 'mSearchView'", FrameLayout.class);
        target.mApplyButton = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.apply_btn, "field 'mApplyButton'", AppCompatTextView.class);
        target.mApplyBtnLayout = (RelativeLayout) Utils.findRequiredViewAsType(source, C0376R.id.apply_btn_layout, "field 'mApplyBtnLayout'", RelativeLayout.class);
        target.mFriendList = (SuperNestedRecyclerView) Utils.findRequiredViewAsType(source, C0376R.id.friend_list, "field 'mFriendList'", SuperNestedRecyclerView.class);
        target.mApplyBadge = (RoundTextView) Utils.findRequiredViewAsType(source, C0376R.id.apply_badge, "field 'mApplyBadge'", RoundTextView.class);
        target.mApplyMessage = source.getContext().getResources().getString(C0376R.string.apply_message);
    }

    @CallSuper
    public void unbind() {
        FriendFragment target = this.f2155b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2155b = null;
        target.mVideoButton = null;
        target.mVideoLayout = null;
        target.addFriend = null;
        target.mSearchView = null;
        target.mApplyButton = null;
        target.mApplyBtnLayout = null;
        target.mFriendList = null;
        target.mApplyBadge = null;
    }
}
