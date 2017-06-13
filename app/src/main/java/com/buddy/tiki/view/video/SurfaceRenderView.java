package com.buddy.tiki.view.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.BuildConfig;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.buddy.tiki.util.MeasureHelper;
import com.buddy.tiki.view.video.IRenderView.IRenderCallback;
import com.buddy.tiki.view.video.IRenderView.ISurfaceHolder;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.ISurfaceTextureHolder;

public class SurfaceRenderView extends SurfaceView implements IRenderView {
    private MeasureHelper f3436a;
    private SurfaceCallback f3437b;

    private static final class InternalSurfaceHolder implements ISurfaceHolder {
        private SurfaceRenderView f3427a;
        private SurfaceHolder f3428b;

        public InternalSurfaceHolder(@NonNull SurfaceRenderView surfaceView, @Nullable SurfaceHolder surfaceHolder) {
            this.f3427a = surfaceView;
            this.f3428b = surfaceHolder;
        }

        public void bindToMediaPlayer(IMediaPlayer mp) {
            if (mp != null) {
                if (VERSION.SDK_INT >= 16 && (mp instanceof ISurfaceTextureHolder)) {
                    ((ISurfaceTextureHolder) mp).setSurfaceTexture(null);
                }
                mp.setDisplay(this.f3428b);
            }
        }

        @NonNull
        public IRenderView getRenderView() {
            return this.f3427a;
        }

        @Nullable
        public SurfaceHolder getSurfaceHolder() {
            return this.f3428b;
        }

        @Nullable
        public SurfaceTexture getSurfaceTexture() {
            return null;
        }

        @Nullable
        public Surface openSurface() {
            if (this.f3428b == null) {
                return null;
            }
            return this.f3428b.getSurface();
        }
    }

    private static final class SurfaceCallback implements Callback {
        private SurfaceHolder f3429a;
        private boolean f3430b;
        private int f3431c;
        private int f3432d;
        private int f3433e;
        private WeakReference<SurfaceRenderView> f3434f;
        private Map<IRenderCallback, Object> f3435g = new ConcurrentHashMap();

        public SurfaceCallback(@NonNull SurfaceRenderView surfaceView) {
            this.f3434f = new WeakReference(surfaceView);
        }

        public void addRenderCallback(@NonNull IRenderCallback callback) {
            this.f3435g.put(callback, callback);
            ISurfaceHolder surfaceHolder = null;
            if (this.f3429a != null) {
                if (null == null) {
                    surfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.f3434f.get(), this.f3429a);
                }
                callback.onSurfaceCreated(surfaceHolder, this.f3432d, this.f3433e);
            }
            if (this.f3430b) {
                if (surfaceHolder == null) {
                    surfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.f3434f.get(), this.f3429a);
                }
                callback.onSurfaceChanged(surfaceHolder, this.f3431c, this.f3432d, this.f3433e);
            }
        }

        public void removeRenderCallback(@NonNull IRenderCallback callback) {
            this.f3435g.remove(callback);
        }

        public void surfaceCreated(SurfaceHolder holder) {
            this.f3429a = holder;
            this.f3430b = false;
            this.f3431c = 0;
            this.f3432d = 0;
            this.f3433e = 0;
            ISurfaceHolder surfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.f3434f.get(), this.f3429a);
            for (IRenderCallback renderCallback : this.f3435g.keySet()) {
                renderCallback.onSurfaceCreated(surfaceHolder, 0, 0);
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            this.f3429a = null;
            this.f3430b = false;
            this.f3431c = 0;
            this.f3432d = 0;
            this.f3433e = 0;
            ISurfaceHolder surfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.f3434f.get(), this.f3429a);
            for (IRenderCallback renderCallback : this.f3435g.keySet()) {
                renderCallback.onSurfaceDestroyed(surfaceHolder);
            }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            this.f3429a = holder;
            this.f3430b = true;
            this.f3431c = format;
            this.f3432d = width;
            this.f3433e = height;
            ISurfaceHolder surfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.f3434f.get(), this.f3429a);
            for (IRenderCallback renderCallback : this.f3435g.keySet()) {
                renderCallback.onSurfaceChanged(surfaceHolder, format, width, height);
            }
        }
    }

    public SurfaceRenderView(Context context) {
        super(context);
        m2160a(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m2160a(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m2160a(context);
    }

    @TargetApi(21)
    public SurfaceRenderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        m2160a(context);
    }

    private void m2160a(Context context) {
        this.f3436a = new MeasureHelper(this);
        this.f3437b = new SurfaceCallback(this);
        getHolder().addCallback(this.f3437b);
        getHolder().setType(0);
    }

    public View getView() {
        return this;
    }

    public boolean shouldWaitForResize() {
        return true;
    }

    public void setVideoSize(int videoWidth, int videoHeight) {
        if (videoWidth > 0 && videoHeight > 0) {
            this.f3436a.setVideoSize(videoWidth, videoHeight);
            getHolder().setFixedSize(videoWidth, videoHeight);
            requestLayout();
        }
    }

    public void setVideoSampleAspectRatio(int videoSarNum, int videoSarDen) {
        if (videoSarNum > 0 && videoSarDen > 0) {
            this.f3436a.setVideoSampleAspectRatio(videoSarNum, videoSarDen);
            requestLayout();
        }
    }

    public void setVideoRotation(int degree) {
        Log.e(BuildConfig.VERSION_NAME, "SurfaceView doesn't support rotation (" + degree + ")!\n");
    }

    public void setAspectRatio(int aspectRatio) {
        this.f3436a.setAspectRatio(aspectRatio);
        requestLayout();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.f3436a.doMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(this.f3436a.getMeasuredWidth(), this.f3436a.getMeasuredHeight());
    }

    public void addRenderCallback(IRenderCallback callback) {
        this.f3437b.addRenderCallback(callback);
    }

    public void removeRenderCallback(IRenderCallback callback) {
        this.f3437b.removeRenderCallback(callback);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(SurfaceRenderView.class.getName());
    }

    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        if (VERSION.SDK_INT >= 14) {
            info.setClassName(SurfaceRenderView.class.getName());
        }
    }
}
