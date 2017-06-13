package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class ModifyNickFragment_ViewBinding implements Unbinder {
    private ModifyNickFragment f2165b;
    private View f2166c;

    @UiThread
    public ModifyNickFragment_ViewBinding(final ModifyNickFragment target, View source) {
        this.f2165b = target;
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        View view = Utils.findRequiredView(source, C0376R.id.save_btn, "field 'mSaveButton' and method 'onSave'");
        target.mSaveButton = (AppCompatTextView) Utils.castView(view, C0376R.id.save_btn, "field 'mSaveButton'", AppCompatTextView.class);
        this.f2166c = view;
        view.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ModifyNickFragment_ViewBinding f2164c;

            public void doClick(View p0) {
                target.onSave();
            }
        });
        target.mNickInput = (AppCompatEditText) Utils.findRequiredViewAsType(source, C0376R.id.nick_input, "field 'mNickInput'", AppCompatEditText.class);
    }

    @CallSuper
    public void unbind() {
        ModifyNickFragment target = this.f2165b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2165b = null;
        target.mToolbar = null;
        target.mSaveButton = null;
        target.mNickInput = null;
        this.f2166c.setOnClickListener(null);
        this.f2166c = null;
    }
}
