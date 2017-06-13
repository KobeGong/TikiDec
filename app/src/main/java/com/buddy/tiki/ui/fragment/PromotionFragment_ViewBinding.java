package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class PromotionFragment_ViewBinding implements Unbinder {
    private PromotionFragment f2196b;

    @UiThread
    public PromotionFragment_ViewBinding(PromotionFragment target, View source) {
        this.f2196b = target;
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mPromotionInput = (AppCompatEditText) Utils.findRequiredViewAsType(source, C0376R.id.promotion_code_input, "field 'mPromotionInput'", AppCompatEditText.class);
        target.mExchangeButton = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.exchange_button, "field 'mExchangeButton'", AppCompatButton.class);
    }

    @CallSuper
    public void unbind() {
        PromotionFragment target = this.f2196b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2196b = null;
        target.mToolbar = null;
        target.mPromotionInput = null;
        target.mExchangeButton = null;
    }
}
