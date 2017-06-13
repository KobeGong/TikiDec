package com.buddy.tiki.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class ConfirmDialog_ViewBinding implements Unbinder {
    private ConfirmDialog f1860b;

    @UiThread
    public ConfirmDialog_ViewBinding(ConfirmDialog target, View source) {
        this.f1860b = target;
        target.mDialogTitle = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.dialog_title, "field 'mDialogTitle'", AppCompatTextView.class);
        target.mMessageContent = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.message_content, "field 'mMessageContent'", AppCompatTextView.class);
        target.mDialogContent = (FrameLayout) Utils.findRequiredViewAsType(source, C0376R.id.dialog_content, "field 'mDialogContent'", FrameLayout.class);
        target.mPositiveBtn = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.positive_btn, "field 'mPositiveBtn'", AppCompatTextView.class);
        target.mNegativeBtn = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.negative_btn, "field 'mNegativeBtn'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        ConfirmDialog target = this.f1860b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1860b = null;
        target.mDialogTitle = null;
        target.mMessageContent = null;
        target.mDialogContent = null;
        target.mPositiveBtn = null;
        target.mNegativeBtn = null;
    }
}
