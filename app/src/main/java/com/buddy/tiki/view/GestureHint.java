package com.buddy.tiki.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.util.PreferenceUtil;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;

public class GestureHint extends RelativeLayout {
    private static final TikiLog f2656a = TikiLog.getInstance(GestureHint.class.getSimpleName());
    private AppCompatTextView f2657b;

    public GestureHint(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        inflate(context, C0376R.layout.view_gesture_hint, this);
        this.f2657b = (AppCompatTextView) findViewById(C0376R.id.confirm_btn);
        m1699a();
    }

    public GestureHint(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureHint(Context context) {
        this(context, null);
    }

    private void m1699a() {
        RxView.clicks(this.f2657b).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(GestureHint$$Lambda$1.lambdaFactory$(this), GestureHint$$Lambda$2.lambdaFactory$());
    }

    /* synthetic */ void m1701a(Object aVoid) throws Exception {
        PreferenceUtil.setGestureHint(true);
        ViewParent viewParent = getParent();
        if (viewParent != null && (viewParent instanceof ViewGroup)) {
            ((ViewGroup) viewParent).removeView(this);
        }
    }
}
