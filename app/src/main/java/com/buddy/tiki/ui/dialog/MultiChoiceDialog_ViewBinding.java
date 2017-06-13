package com.buddy.tiki.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class MultiChoiceDialog_ViewBinding implements Unbinder {
    private MultiChoiceDialog f1972b;

    @UiThread
    public MultiChoiceDialog_ViewBinding(MultiChoiceDialog target, View source) {
        this.f1972b = target;
        target.mTitleContainer = (FrameLayout) Utils.findRequiredViewAsType(source, C0376R.id.title_container, "field 'mTitleContainer'", FrameLayout.class);
        target.mDialogTitle = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.dialog_title, "field 'mDialogTitle'", AppCompatTextView.class);
        target.mTitleDivider = Utils.findRequiredView(source, C0376R.id.dialog_title_divider, "field 'mTitleDivider'");
        target.mDialogContent = (ListView) Utils.findRequiredViewAsType(source, C0376R.id.dialog_content, "field 'mDialogContent'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        MultiChoiceDialog target = this.f1972b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1972b = null;
        target.mTitleContainer = null;
        target.mDialogTitle = null;
        target.mTitleDivider = null;
        target.mDialogContent = null;
    }
}
