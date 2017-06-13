package com.buddy.tiki.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.helper.CustomMessageHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.exception.NetException;
import com.buddy.tiki.service.base.Foreground;
import com.buddy.tiki.service.view.RushNotificationManager;
import com.buddy.tiki.ui.activity.CallActivity;
import com.buddy.tiki.ui.activity.SplashActivity;
import com.buddy.tiki.ui.activity.base.ActivityManager;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.postproc;

public class RushView extends FrameLayout {
    private static final TikiLog f2808a = TikiLog.getInstance(RushView.class.getSimpleName());
    private TextView f2809b;
    private LinearLayout f2810c;
    private TextView f2811d;
    private AppCompatTextView f2812e;
    private AppCompatTextView f2813f;
    private DisposableObserver<Object> f2814g;
    private DisposableObserver<Boolean> f2815h;
    private Disposable f2816i;
    private Disposable f2817j;

    class C04961 extends DisposableObserver<Object> {
        final /* synthetic */ RushView f2805a;

        C04961(RushView this$0) {
            this.f2805a = this$0;
        }

        public void onNext(Object o) {
            RushNotificationManager.getInstance().dismiss(this.f2805a.getContext());
        }

        public void onError(Throwable e) {
        }

        public void onComplete() {
        }
    }

    class C04972 extends DisposableObserver<Boolean> {
        final /* synthetic */ RushView f2807a;

        C04972(RushView this$0) {
            this.f2807a = this$0;
        }

        public void onNext(Boolean result) {
            if (!(this.f2807a.f2816i == null || this.f2807a.f2816i.isDisposed())) {
                this.f2807a.f2816i.dispose();
            }
            this.f2807a.f2816i = Observable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(RushView$2$$Lambda$1.lambdaFactory$(this));
            if (!Foreground.get().isForeground()) {
                CustomMessageHelper.getInstance().ignoreNext(true);
                Intent intent = new Intent(this.f2807a.getContext(), SplashActivity.class);
                Bundle args = new Bundle();
                args.putBoolean("PARAM_KEY_RUSH_MODE", true);
                intent.putExtra("PARAM_KEY_FROM_NOTIFICATION", args);
                intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
                this.f2807a.getContext().startActivity(intent);
            }
        }

        /* synthetic */ void m1750a(Long aLong) throws Exception {
            if (aLong.longValue() < 10) {
                this.f2807a.m1755a(10 - aLong.longValue());
                return;
            }
            this.f2807a.m1758a(null);
            dispose();
        }

        public void onError(Throwable e) {
            if (e instanceof NetException) {
                this.f2807a.m1758a(((NetException) e).getMsg());
            } else {
                this.f2807a.m1758a(null);
            }
        }

        public void onComplete() {
        }
    }

    public RushView(Context context) {
        this(context, null);
    }

    public RushView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RushView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, C0376R.layout.rush_mode_notification, this);
        this.f2809b = (TextView) findViewById(C0376R.id.grab_status);
        this.f2810c = (LinearLayout) findViewById(C0376R.id.normal_layout);
        this.f2811d = (TextView) findViewById(C0376R.id.text);
        this.f2812e = (AppCompatTextView) findViewById(C0376R.id.ignore_button);
        this.f2813f = (AppCompatTextView) findViewById(C0376R.id.grab_button);
        m1754a();
    }

    private void m1754a() {
        this.f2814g = new C04961(this);
        this.f2815h = new C04972(this);
        RxView.clicks(this.f2812e).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(this.f2814g);
        RxView.clicks(this.f2813f).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).doOnNext(RushView$$Lambda$1.lambdaFactory$()).doOnNext(RushView$$Lambda$2.lambdaFactory$(this)).doOnNext(RushView$$Lambda$3.lambdaFactory$(this)).observeOn(Schedulers.io()).flatMap(RushView$$Lambda$4.lambdaFactory$()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.f2815h);
    }

    /* synthetic */ void m1765c(Object o) throws Exception {
        this.f2813f.setClickable(false);
    }

    /* synthetic */ void m1763b(Object o) throws Exception {
        if (!(this.f2816i == null || this.f2816i.isDisposed())) {
            this.f2816i.dispose();
        }
        this.f2816i = Observable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(RushView$$Lambda$7.lambdaFactory$(this));
        if (!Foreground.get().isForeground()) {
            CustomMessageHelper.getInstance().ignoreNext(true);
            Intent intent = new Intent(getContext(), SplashActivity.class);
            Bundle args = new Bundle();
            args.putBoolean("PARAM_KEY_RUSH_MODE", true);
            intent.putExtra("PARAM_KEY_FROM_NOTIFICATION", args);
            intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
            getContext().startActivity(intent);
        }
    }

    /* synthetic */ void m1764c(Long aLong) throws Exception {
        if (aLong.longValue() < 10) {
            m1755a(10 - aLong.longValue());
        } else {
            m1758a(null);
        }
    }

    private void m1755a(long left) {
        this.f2810c.setVisibility(8);
        this.f2809b.setVisibility(0);
        this.f2809b.setGravity(17);
        this.f2809b.setBackgroundColor(ContextCompat.getColor(getContext(), C0376R.color.colorPrimary));
        this.f2809b.setText(getResources().getString(C0376R.string.grab_ordering_format, new Object[]{Long.valueOf(left)}));
        this.f2809b.setTextColor(ContextCompat.getColor(getContext(), C0376R.color.black));
        this.f2809b.setCompoundDrawablesRelative(null, null, null, null);
    }

    public void showSuccess() {
        m1759b();
        this.f2810c.setVisibility(8);
        this.f2809b.setVisibility(0);
        this.f2809b.setGravity(16);
        this.f2809b.setBackgroundColor(ContextCompat.getColor(getContext(), C0376R.color.colorPrimary));
        this.f2809b.setText(C0376R.string.grab_order_successfully);
        this.f2809b.setTextColor(ContextCompat.getColor(getContext(), C0376R.color.black));
        Drawable drawable = ContextCompat.getDrawable(getContext(), C0376R.mipmap.icon_success);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.f2809b.setCompoundDrawablesRelative(drawable, null, null, null);
        this.f2817j = Observable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(RushView$$Lambda$5.lambdaFactory$(this));
        if (!(ActivityManager.getInstance().currentActivity() instanceof CallActivity)) {
            ActivityManager.getInstance().popAndFinishAboveAndWithoutClass(CallActivity.class);
        }
        Intent intent = new Intent(getContext(), CallActivity.class);
        intent.putExtra("PARAM_KEY_RUSH_MODE", true);
        intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
        getContext().startActivity(intent);
    }

    /* synthetic */ void m1762b(Long aLong) throws Exception {
        RushNotificationManager.getInstance().dismiss(getContext());
    }

    private void m1758a(@Nullable String errorMsg) {
        m1759b();
        this.f2810c.setVisibility(8);
        this.f2809b.setVisibility(0);
        this.f2809b.setGravity(17);
        this.f2809b.setBackgroundColor(ContextCompat.getColor(getContext(), C0376R.color.fail_text_background));
        if (TextUtils.isEmpty(errorMsg)) {
            this.f2809b.setText(C0376R.string.grab_order_failed);
        } else {
            this.f2809b.setText(errorMsg);
        }
        this.f2809b.setTextColor(ContextCompat.getColor(getContext(), C0376R.color.fail_text_color));
        this.f2809b.setCompoundDrawablesRelative(null, null, null, null);
        this.f2817j = Observable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(RushView$$Lambda$6.lambdaFactory$(this));
    }

    /* synthetic */ void m1761a(Long aLong) throws Exception {
        RushNotificationManager.getInstance().dismiss(getContext());
    }

    public void setText(String text) {
        this.f2811d.setText(text);
    }

    private void m1759b() {
        if (!(this.f2814g == null || this.f2814g.isDisposed())) {
            this.f2814g.dispose();
        }
        if (!(this.f2815h == null || this.f2815h.isDisposed())) {
            this.f2815h.dispose();
        }
        if (!(this.f2816i == null || this.f2816i.isDisposed())) {
            this.f2816i.dispose();
        }
        if (this.f2817j != null && !this.f2817j.isDisposed()) {
            this.f2817j.dispose();
        }
    }

    protected void onDetachedFromWindow() {
        m1759b();
        super.onDetachedFromWindow();
    }
}
