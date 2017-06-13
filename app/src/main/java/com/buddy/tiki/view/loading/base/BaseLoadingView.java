package com.buddy.tiki.view.loading.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.buddy.tiki.view.loading.CircleBroodLoadingRenderer;

public class BaseLoadingView extends ImageView {
    private LoadingDrawable f3052a;

    public BaseLoadingView(Context context) {
        super(context);
    }

    public BaseLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m1900a(context, attrs);
    }

    private void m1900a(Context context, AttributeSet attrs) {
        setLoadingRenderer(new CircleBroodLoadingRenderer(context));
    }

    public void setLoadingRenderer(LoadingRenderer loadingRenderer) {
        this.f3052a = new LoadingDrawable(loadingRenderer);
        setImageDrawable(this.f3052a);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        m1899a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m1901b();
    }

    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == 0) {
            m1899a();
        } else {
            m1901b();
        }
    }

    private void m1899a() {
        if (this.f3052a != null) {
            this.f3052a.start();
        }
    }

    private void m1901b() {
        if (this.f3052a != null) {
            this.f3052a.stop();
        }
    }
}
