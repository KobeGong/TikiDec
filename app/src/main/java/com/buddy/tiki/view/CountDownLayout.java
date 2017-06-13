package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.drawable.CountDownBorderDrawable;
import com.buddy.tiki.drawable.CountDownBorderDrawable.CountDownListener;
import com.buddy.tiki.util.DisplayUtil;
import java.util.Locale;

public class CountDownLayout extends RelativeLayout {
    private static final int f2606a = Color.parseColor("#7c000000");
    private static final int f2607b = Color.parseColor("#fffad200");
    private int f2608c;
    private int f2609d;
    private TextView f2610e;
    private CountDownBorderDrawable f2611f;
    private int f2612g;
    private int f2613h;
    private int f2614i;
    private CountDownListener f2615j;
    private CountDownListener f2616k;
    private int f2617l;
    private int f2618m;
    private int f2619n;
    private int f2620o;

    class C04931 implements CountDownListener {
        final /* synthetic */ CountDownLayout f2605a;

        C04931(CountDownLayout this$0) {
            this.f2605a = this$0;
        }

        public void onTimeUp() {
            if (this.f2605a.f2615j != null) {
                this.f2605a.f2615j.onTimeUp();
            }
        }

        public void onTimeLeft(int second) {
            if (this.f2605a.f2615j != null) {
                this.f2605a.f2615j.onTimeLeft(second);
            }
            this.f2605a.f2610e.setText(String.format(Locale.getDefault(), "%ds", new Object[]{Integer.valueOf(second)}));
        }
    }

    public CountDownLayout(Context context) {
        this(context, null);
    }

    public CountDownLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownLayout(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f2616k = new C04931(this);
        this.f2617l = getResources().getColor(C0376R.color.colorAccent);
        this.f2618m = getResources().getColor(C0376R.color.black_alpha_normal);
        this.f2619n = f2606a;
        this.f2620o = f2607b;
        this.f2611f = new CountDownBorderDrawable();
        this.f2608c = ContextCompat.getColor(context, C0376R.color.colorAccent);
        this.f2609d = ContextCompat.getColor(context, C0376R.color.black_alpha_normal);
        m1677a();
        m1679b();
        m1680c();
    }

    private void m1677a() {
        this.f2610e = new TextView(getContext());
        this.f2610e.setTextSize(2, 12.0f);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        layoutParams.addRule(14, -1);
        this.f2610e.setLayoutParams(layoutParams);
        this.f2610e.setGravity(17);
        addView(this.f2610e);
        this.f2611f.setCountDownListener(this.f2616k);
    }

    private void m1679b() {
        this.f2612g = ((((getPaddingBottom() + getPaddingTop()) + getPaddingLeft()) + getPaddingRight()) / 4) * 2;
        this.f2611f.setStrokeWidth(this.f2612g);
    }

    public void init(int borderWidth) {
        init(borderWidth, 0);
    }

    public void init(int borderWidth, int totalTime) {
        this.f2611f.setStrokeWidth(borderWidth);
        this.f2611f.setProgress(0, totalTime);
        this.f2612g = borderWidth;
        this.f2614i = totalTime;
        this.f2613h = 0;
        setPadding(borderWidth, borderWidth, borderWidth, borderWidth);
    }

    public void displayBorder() {
        setBackground(this.f2611f);
        int padding = DisplayUtil.dip2px(4.0f);
        setPadding(padding, padding, padding, padding);
        this.f2610e.setVisibility(4);
    }

    public void hideBorder() {
        setBackground(null);
        setPadding(0, 0, 0, 0);
        this.f2610e.setVisibility(4);
    }

    public void displayCountDown() {
        setBackground(this.f2611f);
        int padding = DisplayUtil.dip2px(4.0f);
        setPadding(padding, padding, padding, padding);
        this.f2610e.setVisibility(0);
    }

    public void hideCountDown() {
        setBackground(null);
        setPadding(0, 0, 0, 0);
        this.f2610e.setVisibility(4);
    }

    public void startCountDown(int totalSecond) {
        this.f2611f.start(totalSecond);
        this.f2610e.setText(String.format(Locale.getDefault(), "%ds", new Object[]{Integer.valueOf(totalSecond)}));
    }

    public void stopCountDown() {
        this.f2611f.stop();
    }

    public void increaseTime(int increaseSecond) {
        this.f2611f.increate(increaseSecond);
    }

    public void setColors(@ColorInt int textColor, @ColorInt int textBgColor, @ColorInt int borderProgressColor, @ColorInt int borderRemainderColor) {
        if (textColor == -1) {
            textColor = this.f2608c;
        }
        this.f2617l = textColor;
        if (textBgColor == -1) {
            textBgColor = this.f2609d;
        }
        this.f2618m = textBgColor;
        if (borderProgressColor == -1) {
            borderProgressColor = f2606a;
        }
        this.f2619n = borderProgressColor;
        if (borderRemainderColor == -1) {
            borderRemainderColor = f2607b;
        }
        this.f2620o = borderRemainderColor;
        m1680c();
    }

    public void setTime(String time) {
        this.f2610e.setText(time);
    }

    private void m1680c() {
        this.f2610e.setTextColor(this.f2617l);
        this.f2610e.setBackgroundColor(this.f2618m);
        this.f2611f.setProgressColor(this.f2619n);
        this.f2611f.setRemainderColor(this.f2620o);
        postInvalidate();
    }

    public void setCountDownListener(CountDownListener listener) {
        this.f2615j = listener;
    }
}
