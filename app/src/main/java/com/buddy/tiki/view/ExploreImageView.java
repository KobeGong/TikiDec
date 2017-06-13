package com.buddy.tiki.view;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.buddy.tiki.C0376R;
import com.facebook.drawee.view.SimpleDraweeView;

public class ExploreImageView extends FrameLayout {
    protected ImageView f2646a;
    protected SimpleDraweeView f2647b;

    public ExploreImageView(Context context) {
        this(context, null);
    }

    public ExploreImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExploreImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m1694a(context);
    }

    private void m1694a(Context context) {
        inflate(context, C0376R.layout.widget_explore_image_view, this);
        this.f2646a = (ImageView) findViewById(C0376R.id.under_layer);
        this.f2647b = (SimpleDraweeView) findViewById(C0376R.id.top_layer);
        this.f2646a.setImageResource(C0376R.drawable.circle_drawable);
    }

    @UiThread
    protected void m1695a(@FloatRange(from = 0.0d, to = 1.0d) float percent) {
        float scaleSize = 1.0f - (0.25f * percent);
        float actualAlpha = 0.1f + (0.9f * percent);
        setScaleX(scaleSize);
        setScaleY(scaleSize);
        this.f2646a.setAlpha(actualAlpha);
    }
}
