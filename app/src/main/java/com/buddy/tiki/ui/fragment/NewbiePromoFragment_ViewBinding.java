package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class NewbiePromoFragment_ViewBinding implements Unbinder {
    private NewbiePromoFragment f2171b;

    @UiThread
    public NewbiePromoFragment_ViewBinding(NewbiePromoFragment target, View source) {
        this.f2171b = target;
        target.mPromotionInput = (AppCompatEditText) Utils.findRequiredViewAsType(source, C0376R.id.promotion_code_input, "field 'mPromotionInput'", AppCompatEditText.class);
        target.mNextButton = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.next_btn, "field 'mNextButton'", AppCompatButton.class);
        target.mSkipButton = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.skip_btn, "field 'mSkipButton'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        NewbiePromoFragment target = this.f2171b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2171b = null;
        target.mPromotionInput = null;
        target.mNextButton = null;
        target.mSkipButton = null;
    }
}
