package com.buddy.tiki.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;
import com.buddy.tiki.ChatApp;

public class ToastUtil {
    private static volatile ToastUtil f2473a = null;
    private Handler f2474b = new Handler(Looper.getMainLooper());
    private Toast f2475c;

    private ToastUtil() {
    }

    public static ToastUtil getInstance() {
        if (f2473a == null) {
            synchronized (ToastUtil.class) {
                if (f2473a == null) {
                    f2473a = new ToastUtil();
                }
            }
        }
        return f2473a;
    }

    public void show(@StringRes int resId) {
        show(ChatApp.getInstance(), resId);
    }

    public void show(@NonNull Context context, @StringRes int resId) {
        show(context, resId, 0);
    }

    public void show(@NonNull Context context, @StringRes int resId, int duration) {
        this.f2474b.post(ToastUtil$$Lambda$1.lambdaFactory$(this, context, resId, duration));
    }

    /* synthetic */ void m1619a(@NonNull Context context, @StringRes int resId, int duration) {
        if (this.f2475c == null) {
            this.f2475c = Toast.makeText(context, resId, duration);
            this.f2475c.show();
            return;
        }
        this.f2475c.setText(resId);
        this.f2475c.setDuration(duration);
        this.f2475c.show();
    }

    public void show(String text) {
        show(ChatApp.getInstance(), text);
    }

    public void show(@NonNull Context context, String text) {
        show(context, text, 0);
    }

    public void show(@NonNull Context context, String text, int duration) {
        this.f2474b.post(ToastUtil$$Lambda$2.lambdaFactory$(this, context, text, duration));
    }

    /* synthetic */ void m1620a(@NonNull Context context, String text, int duration) {
        if (this.f2475c == null) {
            this.f2475c = Toast.makeText(context, text, duration);
            this.f2475c.show();
            return;
        }
        this.f2475c.setText(text);
        this.f2475c.setDuration(duration);
        this.f2475c.show();
    }
}
