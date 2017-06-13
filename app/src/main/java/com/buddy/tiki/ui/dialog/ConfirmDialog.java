package com.buddy.tiki.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ui.dialog.base.BaseDialogFragment;

public class ConfirmDialog extends BaseDialogFragment {
    private View f1848c;
    private LayoutParams f1849d;
    private String f1850e;
    private String f1851f;
    private OnClickListener f1852g;
    private OnClickListener f1853h;
    private String f1854i;
    private String f1855j;
    private OnCancelListener f1856k;
    private OnDismissListener f1857l;
    private boolean f1858m = true;
    @BindView(2131820886)
    FrameLayout mDialogContent;
    @BindView(2131820884)
    AppCompatTextView mDialogTitle;
    @BindView(2131820885)
    AppCompatTextView mMessageContent;
    @BindView(2131820887)
    AppCompatTextView mNegativeBtn;
    @BindView(2131820888)
    AppCompatTextView mPositiveBtn;
    private boolean f1859n = true;

    public static class Builder {
        ConfirmDialog f1842a = new ConfirmDialog();
        Context f1843b;

        public Builder(Context context) {
            this.f1843b = context;
        }

        public Builder setView(View view) {
            this.f1842a.f1848c = view;
            this.f1842a.f1849d = new LayoutParams(-1, -1);
            return this;
        }

        public Builder setView(View view, LayoutParams params) {
            this.f1842a.f1848c = view;
            this.f1842a.f1849d = params;
            return this;
        }

        public Builder setMessage(String message) {
            this.f1842a.f1850e = message;
            return this;
        }

        public Builder setMessage(@StringRes int message) {
            this.f1842a.f1850e = this.f1843b.getString(message);
            return this;
        }

        public Builder setTitle(String title) {
            this.f1842a.f1851f = title;
            return this;
        }

        public Builder setTitle(@StringRes int title) {
            this.f1842a.f1851f = this.f1843b.getString(title);
            return this;
        }

        public Builder setPositiveButton(@StringRes int text, OnClickListener listener) {
            this.f1842a.f1855j = this.f1843b.getString(text);
            this.f1842a.f1852g = listener;
            return this;
        }

        public Builder setPositiveButton(String text, OnClickListener listener) {
            this.f1842a.f1855j = text;
            this.f1842a.f1852g = listener;
            return this;
        }

        public Builder setNegativeButton(@StringRes int text, OnClickListener listener) {
            this.f1842a.f1854i = this.f1843b.getString(text);
            this.f1842a.f1853h = listener;
            return this;
        }

        public Builder setNegativeButton(String text, OnClickListener listener) {
            this.f1842a.f1854i = text;
            this.f1842a.f1853h = listener;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener listener) {
            this.f1842a.f1856k = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.f1842a.f1858m = cancelable;
            return this;
        }

        public Builder setAutoDismiss(boolean autoDismiss) {
            this.f1842a.f1859n = autoDismiss;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener dismissListener) {
            this.f1842a.f1857l = dismissListener;
            return this;
        }

        public ConfirmDialog create() {
            return this.f1842a;
        }

        public void show(FragmentManager fragmentManager, String tag) {
            fragmentManager.beginTransaction().add(this.f1842a, tag).addToBackStack(null).commitAllowingStateLoss();
        }
    }

    protected int mo2172a() {
        return C0376R.layout.dialog_confirm;
    }

    protected void mo2173a(Bundle savedInstanceState) {
        m1067b();
        m1070c();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(1);
        dialog.getWindow().setBackgroundDrawableResource(C0376R.color.transparent);
        return dialog;
    }

    private void m1067b() {
        if (TextUtils.isEmpty(this.f1851f)) {
            this.mDialogTitle.setVisibility(8);
        } else {
            this.mDialogTitle.setText(this.f1851f);
        }
        if (this.f1848c != null) {
            this.mDialogContent.addView(this.f1848c, this.f1849d);
            this.mMessageContent.setVisibility(8);
        } else if (!TextUtils.isEmpty(this.f1850e)) {
            this.mMessageContent.setText(this.f1850e);
            this.mMessageContent.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.f1855j)) {
            this.mPositiveBtn.setText(this.f1855j);
        }
        if (!TextUtils.isEmpty(this.f1854i)) {
            this.mNegativeBtn.setText(this.f1854i);
        }
    }

    private void m1070c() {
        if (this.f1852g == null) {
            this.mPositiveBtn.setVisibility(8);
        } else {
            this.mPositiveBtn.setOnClickListener(ConfirmDialog$$Lambda$1.lambdaFactory$(this));
        }
        if (this.f1853h == null) {
            this.mNegativeBtn.setVisibility(8);
        } else {
            this.mNegativeBtn.setOnClickListener(ConfirmDialog$$Lambda$2.lambdaFactory$(this));
        }
        setCancelable(this.f1858m);
    }

    /* synthetic */ void m1075b(View v) {
        this.f1852g.onClick(getDialog(), 1);
        if (this.f1859n) {
            dismiss();
        }
    }

    /* synthetic */ void m1074a(View v) {
        this.f1853h.onClick(getDialog(), 0);
        if (this.f1859n) {
            dismiss();
        }
    }

    public void dismiss() {
        if (this.f1857l != null) {
            this.f1857l.onDismiss(getDialog());
        }
        super.dismiss();
    }

    public void onCancel(DialogInterface dialog) {
        if (this.f1856k != null) {
            this.f1856k.onCancel(dialog);
        }
    }
}
