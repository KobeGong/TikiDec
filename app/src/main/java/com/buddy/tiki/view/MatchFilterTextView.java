package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.util.DisplayUtil;

public class MatchFilterTextView extends AppCompatTextView {
    public MatchFilterTextView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
    }

    public MatchFilterTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatchFilterTextView(Context context) {
        this(context, null);
    }

    public void enableClick(boolean clickable) {
        if (clickable != isClickable()) {
            Drawable drawable;
            setClickable(clickable);
            if (isClickable()) {
                drawable = getResources().getDrawable(C0376R.mipmap.icon_filter);
            } else {
                drawable = getResources().getDrawable(C0376R.mipmap.icon_filter_pressed);
            }
            if (drawable != null) {
                drawable.setBounds(0, 0, DisplayUtil.dip2px(18.0f), DisplayUtil.dip2px(18.0f));
                setCompoundDrawablesRelative(drawable, null, null, null);
            }
        }
    }
}
