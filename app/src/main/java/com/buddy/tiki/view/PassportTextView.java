package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.util.DisplayUtil;

public class PassportTextView extends AppCompatTextView {
    private String f2768a;

    public PassportTextView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        m1743a();
    }

    public PassportTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PassportTextView(Context context) {
        this(context, null);
    }

    private void m1743a() {
        if (isInEditMode()) {
            setCompoundDrawablePadding(9);
        } else {
            int padding = DisplayUtil.dip2px(3.0f);
            setPadding(padding, 0, padding * 3, 0);
            setCompoundDrawablePadding(padding);
        }
        setGravity(17);
        setTextColor(getResources().getColor(C0376R.color.colorPrimary));
        setPassport(this.f2768a);
    }

    public String getPassport() {
        return this.f2768a;
    }

    public void setPassport(String passport) {
        this.f2768a = passport;
        Drawable drawable;
        if (TextUtils.isEmpty(this.f2768a)) {
            drawable = ResourcesCompat.getDrawable(getResources(), C0376R.drawable.icon_topic_layer, null);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                setCompoundDrawablesRelative(drawable, null, null, null);
            }
            setBackgroundResource(0);
            setText(BuildConfig.VERSION_NAME);
            return;
        }
        drawable = ResourcesCompat.getDrawable(getResources(), C0376R.drawable.icon_quit_topic_layer, null);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            setCompoundDrawablesRelative(drawable, null, null, null);
        }
        setBackgroundResource(C0376R.drawable.bg_passport_text_view);
        setText(this.f2768a);
    }
}
