package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.superrecyclerview.SuperRecyclerView;

public class SystemMessageFragment_ViewBinding implements Unbinder {
    private SystemMessageFragment f2225b;

    @UiThread
    public SystemMessageFragment_ViewBinding(SystemMessageFragment target, View source) {
        this.f2225b = target;
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mMessageList = (SuperRecyclerView) Utils.findRequiredViewAsType(source, C0376R.id.data_list, "field 'mMessageList'", SuperRecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        SystemMessageFragment target = this.f2225b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2225b = null;
        target.mToolbar = null;
        target.mMessageList = null;
    }
}
