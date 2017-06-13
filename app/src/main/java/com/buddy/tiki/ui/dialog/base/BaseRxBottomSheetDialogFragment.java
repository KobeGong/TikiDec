package com.buddy.tiki.ui.dialog.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment;

public abstract class BaseRxBottomSheetDialogFragment extends RxAppCompatDialogFragment {
    private DataLayer f1887a = DataLayer.getInstance();
    private BaseActivity f1888b;
    protected OnCancelListener f1889o;

    @StyleRes
    protected abstract int mo2182a();

    protected abstract void mo2183a(@Nullable Bundle bundle);

    public DataLayer getDataLayer() {
        return this.f1887a;
    }

    public BaseActivity getHoldingActivity() {
        return this.f1888b;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getActivity(), mo2182a());
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mo2183a(savedInstanceState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            this.f1888b = (BaseActivity) activity;
        }
    }

    public void setOnDismissListener(OnCancelListener listener) {
        this.f1889o = listener;
    }

    public void onDismiss(DialogInterface dialog) {
        if (this.f1889o != null) {
            this.f1889o.onCancel(dialog);
        }
        super.onDismiss(dialog);
    }
}
