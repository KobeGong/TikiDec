package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.superrecyclerview.SuperRecyclerView;

public class ApplyListFragment_ViewBinding implements Unbinder {
    private ApplyListFragment f2016b;

    @UiThread
    public ApplyListFragment_ViewBinding(ApplyListFragment target, View source) {
        this.f2016b = target;
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mAddFriend = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.add_friend, "field 'mAddFriend'", AppCompatTextView.class);
        target.mApplyList = (SuperRecyclerView) Utils.findRequiredViewAsType(source, C0376R.id.apply_list, "field 'mApplyList'", SuperRecyclerView.class);
        target.mApplyTips = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.apply_tips, "field 'mApplyTips'", AppCompatTextView.class);
        target.mDividerLine = Utils.findRequiredView(source, C0376R.id.divider_line, "field 'mDividerLine'");
    }

    @CallSuper
    public void unbind() {
        ApplyListFragment target = this.f2016b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2016b = null;
        target.mToolbar = null;
        target.mAddFriend = null;
        target.mApplyList = null;
        target.mApplyTips = null;
        target.mDividerLine = null;
    }
}
