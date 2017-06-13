package com.buddy.tiki.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.view.PageGridView.PageIndicator;

public class PageIndicatorView extends LinearLayout implements PageIndicator {
    private static final int f2767a = DisplayUtil.dip2px(4.0f);

    public PageIndicatorView(Context context) {
        this(context, null);
    }

    public PageIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public PageIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initIndicatorItems(int itemsNumber) {
        removeAllViews();
        for (int i = 0; i < itemsNumber; i++) {
            AppCompatImageView imageView = new AppCompatImageView(getContext());
            imageView.setImageResource(C0376R.drawable.dot_normal);
            imageView.setPadding(f2767a, 0, f2767a, 0);
            addView(imageView);
        }
    }

    public void onPageSelected(int pageIndex) {
        AppCompatImageView imageView = (AppCompatImageView) getChildAt(pageIndex);
        if (imageView != null) {
            imageView.setImageResource(C0376R.drawable.dot_selected);
        }
    }

    public void onPageUnSelected(int pageIndex) {
        AppCompatImageView imageView = (AppCompatImageView) getChildAt(pageIndex);
        if (imageView != null) {
            imageView.setImageResource(C0376R.drawable.dot_normal);
        }
    }
}
