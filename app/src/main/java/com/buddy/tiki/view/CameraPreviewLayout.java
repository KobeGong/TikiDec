package com.buddy.tiki.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.buddy.tiki.wertc.PercentFrameLayout;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class CameraPreviewLayout extends PercentFrameLayout {
    private ViewDragHelper f2580a;

    private class DragHelperCallback extends Callback {
        final /* synthetic */ CameraPreviewLayout f2575a;

        private DragHelperCallback(CameraPreviewLayout cameraPreviewLayout) {
            this.f2575a = cameraPreviewLayout;
        }

        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return Math.min(Math.max(left, this.f2575a.getPaddingLeft()), this.f2575a.getWidth() - child.getWidth());
        }

        public int clampViewPositionVertical(View child, int top, int dy) {
            return Math.min(Math.max(top, this.f2575a.getPaddingTop()), this.f2575a.getHeight() - child.getHeight());
        }
    }

    public CameraPreviewLayout(Context context) {
        this(context, null);
    }

    public CameraPreviewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraPreviewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.f2580a = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                this.f2580a.cancel();
                break;
        }
        return this.f2580a.shouldInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.f2580a.processTouchEvent(event);
        return true;
    }
}
