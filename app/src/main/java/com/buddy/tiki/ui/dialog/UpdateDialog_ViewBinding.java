package com.buddy.tiki.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class UpdateDialog_ViewBinding implements Unbinder {
    private UpdateDialog f2000b;

    @UiThread
    public UpdateDialog_ViewBinding(UpdateDialog target, View source) {
        this.f2000b = target;
        target.mUpdateContent = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.update_content, "field 'mUpdateContent'", AppCompatTextView.class);
        target.mUpdateBtn = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.update_btn, "field 'mUpdateBtn'", AppCompatButton.class);
    }

    @CallSuper
    public void unbind() {
        UpdateDialog target = this.f2000b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2000b = null;
        target.mUpdateContent = null;
        target.mUpdateBtn = null;
    }
}
