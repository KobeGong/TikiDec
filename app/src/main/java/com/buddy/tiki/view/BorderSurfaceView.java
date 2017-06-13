package com.buddy.tiki.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.resource.Border;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import im.facechat.view.FCSurfaceView;

public class BorderSurfaceView extends FrameLayout {
    private SimpleDraweeView f2510a;
    private FCSurfaceView f2511b;
    private Border f2512c;

    public BorderSurfaceView(Context context) {
        this(context, null);
    }

    public BorderSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.f2512c = null;
        m1637a(context);
    }

    private void m1637a(Context context) {
        inflate(context, C0376R.layout.widget_border_surface, this);
        this.f2510a = (SimpleDraweeView) findViewById(C0376R.id.border);
        this.f2511b = (FCSurfaceView) findViewById(C0376R.id.render);
    }

    public SimpleDraweeView getBorder() {
        return this.f2510a;
    }

    public FCSurfaceView getRender() {
        return this.f2511b;
    }

    public void showBorder(@Nullable Border border) {
        this.f2512c = border;
        if (border == null || TextUtils.isEmpty(border.getSource())) {
            this.f2510a.setVisibility(4);
            return;
        }
        this.f2510a.setVisibility(0);
        this.f2510a.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(border.getSource()).setAutoPlayAnimations(true)).build());
    }

    public void hideBorder() {
        this.f2510a.setVisibility(4);
    }

    public Border getUsedBorder() {
        return this.f2512c;
    }
}
