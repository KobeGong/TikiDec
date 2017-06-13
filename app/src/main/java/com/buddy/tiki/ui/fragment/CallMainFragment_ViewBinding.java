package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.BannedView;
import com.buddy.tiki.view.BlockView;
import com.buddy.tiki.view.RequestTextView;
import com.buddy.tiki.view.RoundTextView;
import com.buddy.tiki.view.UpwardTipsTextView;

public class CallMainFragment_ViewBinding implements Unbinder {
    private CallMainFragment f2039b;

    @UiThread
    public CallMainFragment_ViewBinding(CallMainFragment target, View source) {
        this.f2039b = target;
        target.mNick = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.nick, "field 'mNick'", AppCompatTextView.class);
        target.mFriendAction = (RoundTextView) Utils.findRequiredViewAsType(source, C0376R.id.friend_action, "field 'mFriendAction'", RoundTextView.class);
        target.mRequestTips = (RequestTextView) Utils.findRequiredViewAsType(source, C0376R.id.request_tips, "field 'mRequestTips'", RequestTextView.class);
        target.mReportBtn = (RoundTextView) Utils.findRequiredViewAsType(source, C0376R.id.report_btn, "field 'mReportBtn'", RoundTextView.class);
        target.mFlipBtn = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.flip_btn, "field 'mFlipBtn'", AppCompatImageView.class);
        target.mBlockContent = (BlockView) Utils.findRequiredViewAsType(source, C0376R.id.block_content, "field 'mBlockContent'", BlockView.class);
        target.mApplyTips = (UpwardTipsTextView) Utils.findRequiredViewAsType(source, C0376R.id.apply_tips, "field 'mApplyTips'", UpwardTipsTextView.class);
        target.mBannedView = (BannedView) Utils.findRequiredViewAsType(source, C0376R.id.banned_view, "field 'mBannedView'", BannedView.class);
        target.mRegionAndGenderInfo = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.region_and_gender_info, "field 'mRegionAndGenderInfo'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        CallMainFragment target = this.f2039b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2039b = null;
        target.mNick = null;
        target.mFriendAction = null;
        target.mRequestTips = null;
        target.mReportBtn = null;
        target.mFlipBtn = null;
        target.mBlockContent = null;
        target.mApplyTips = null;
        target.mBannedView = null;
        target.mRegionAndGenderInfo = null;
    }
}
