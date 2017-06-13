package com.buddy.tiki.ui.dialog;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import android.widget.TextView;
import com.buddy.tiki.C0376R;
import java.lang.ref.WeakReference;

public class LoadingDialog extends AppCompatDialog {
    private static Handler f1950a = new Handler(Looper.getMainLooper());
    private static WeakReference<LoadingDialog> f1951b;
    private static final Runnable f1952c = LoadingDialog$$Lambda$1.lambdaFactory$();
    private TextView f1953d;

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public static LoadingDialog startLoading(@NonNull Context context) {
        return startLoading(context, BuildConfig.VERSION_NAME, null);
    }

    public static LoadingDialog startLoading(@NonNull Context context, int resId) {
        return startLoading(context, resId, null);
    }

    public static LoadingDialog startLoading(@NonNull Context context, int resId, OnCancelListener listener) {
        return startLoading(context, resId <= 0 ? BuildConfig.VERSION_NAME : context.getString(resId), listener);
    }

    public static LoadingDialog startLoading(@NonNull Context context, String text) {
        return startLoading(context, text, null);
    }

    public static LoadingDialog startLoading(@NonNull Context context, String text, OnCancelListener listener) {
        if (f1951b == null || f1951b.get() == null) {
            f1951b = new WeakReference(new LoadingDialog(context, C0376R.style.LoadingDialogStyle));
        } else if (((LoadingDialog) f1951b.get()).isShowing()) {
            if (!TextUtils.isEmpty(text)) {
                m1140a().setText(text);
            }
            f1950a.removeCallbacks(f1952c);
            f1950a.postDelayed(f1952c, 30000);
            return m1140a();
        }
        m1140a().setContentView((int) C0376R.layout.widget_loading_view);
        m1140a().setOnCancelListener(listener);
        m1140a().setCanceledOnTouchOutside(false);
        m1140a().setCancelable(false);
        m1140a().show();
        if (!TextUtils.isEmpty(text)) {
            m1140a().setText(text);
        }
        f1950a.postDelayed(f1952c, 30000);
        return m1140a();
    }

    private static LoadingDialog m1140a() {
        return (LoadingDialog) f1951b.get();
    }

    public static void stopLoading() {
        if (f1951b != null) {
            f1950a.removeCallbacks(f1952c);
            m1140a().dismiss();
            f1951b = null;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.f1953d = (TextView) findViewById(C0376R.id.info);
    }

    public void setText(int resId) {
        if (f1951b != null && this.f1953d != null) {
            this.f1953d.setText(resId);
        }
    }

    public void setText(String s) {
        if (f1951b != null && this.f1953d != null) {
            this.f1953d.setText(s);
        }
    }

    public void dismiss() {
        if (!(f1950a == null || f1952c == null)) {
            f1950a.removeCallbacks(f1952c);
        }
        f1951b = null;
        super.dismiss();
    }

    public void cancel() {
        if (!(f1950a == null || f1952c == null)) {
            f1950a.removeCallbacks(f1952c);
        }
        super.cancel();
    }
}
