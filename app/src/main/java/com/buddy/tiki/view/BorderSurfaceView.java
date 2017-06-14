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
    private SimpleDraweeView borderView;
    private FCSurfaceView surfaceView;
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
        init(context);
    }

    private void init(Context context) {
        inflate(context, C0376R.layout.widget_border_surface, this);
        this.borderView = (SimpleDraweeView) findViewById(C0376R.id.border);
        this.surfaceView = (FCSurfaceView) findViewById(C0376R.id.render);
    }

    public SimpleDraweeView getBorder() {
        return this.borderView;
    }

    public FCSurfaceView getRender() {
        return this.surfaceView;
    }

    public void showBorder(@Nullable Border border) {
        this.f2512c = border;
        if (border == null || TextUtils.isEmpty(border.getSource())) {
            this.borderView.setVisibility(4);
            return;
        }
        this.borderView.setVisibility(0);
        this.borderView.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(border.getSource()).setAutoPlayAnimations(true)).build());
    }

    public void hideBorder() {
        this.borderView.setVisibility(4);
    }

    public Border getUsedBorder() {
        return this.f2512c;
    }
}
