package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class AboutActivity_ViewBinding implements Unbinder {
    private AboutActivity f1114b;

    @UiThread
    public AboutActivity_ViewBinding(AboutActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public AboutActivity_ViewBinding(AboutActivity target, View source) {
        this.f1114b = target;
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mVersionCode = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.version_code, "field 'mVersionCode'", AppCompatTextView.class);
        target.mVersionName = source.getContext().getResources().getString(C0376R.string.version_name);
    }

    @CallSuper
    public void unbind() {
        AboutActivity target = this.f1114b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1114b = null;
        target.mToolbar = null;
        target.mVersionCode = null;
    }
}
