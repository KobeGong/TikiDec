package com.buddy.tiki.ui.dialog.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

public abstract class BaseBottomSheetFragment extends BottomSheetDialogFragment {
    protected BottomSheetBehavior f1865a;
    protected OnDismissListener f1866b;

    protected abstract void mo2177a(View view);

    @LayoutRes
    protected abstract int mo2178b();

    @StyleRes
    protected abstract int mo2179c();

    protected abstract void mo2180d();

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = new BottomSheetDialog(getContext(), mo2179c());
        View view = View.inflate(getContext(), mo2178b(), null);
        mo2177a(view);
        dialog.setContentView(view);
        this.f1865a = BottomSheetBehavior.from((View) view.getParent());
        mo2180d();
        return dialog;
    }

    protected int mo2176a() {
        return -1;
    }

    public void onStart() {
        super.onStart();
        this.f1865a.setState(4);
        this.f1865a.setPeekHeight(mo2176a());
    }

    public void setOnDismissListener(@NonNull OnDismissListener listener) {
        this.f1866b = listener;
    }

    public void onDismiss(DialogInterface dialog) {
        if (this.f1866b != null) {
            this.f1866b.onDismiss(dialog);
        }
        super.onDismiss(dialog);
    }
}
