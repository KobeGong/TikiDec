package com.buddy.tiki.view.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
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
import tv.danmaku.ijk.media.player.ISurfaceTextureHost;

@TargetApi(14)
public class TextureRenderView extends TextureView implements IRenderView {
    private MeasureHelper f3455a;
    private SurfaceCallback f3456b;

    private static final class InternalSurfaceHolder implements ISurfaceHolder {
        private TextureRenderView f3443a;
        private SurfaceTexture f3444b;
        private ISurfaceTextureHost f3445c;

        public InternalSurfaceHolder(@NonNull TextureRenderView textureView, @Nullable SurfaceTexture surfaceTexture, @NonNull ISurfaceTextureHost surfaceTextureHost) {
            this.f3443a = textureView;
            this.f3444b = surfaceTexture;
            this.f3445c = surfaceTextureHost;
        }

        @TargetApi(16)
        public void bindToMediaPlayer(IMediaPlayer mp) {
            if (mp != null) {
                if (VERSION.SDK_INT < 16 || !(mp instanceof ISurfaceTextureHolder)) {
                    mp.setSurface(openSurface());
                    return;
                }
                ISurfaceTextureHolder textureHolder = (ISurfaceTextureHolder) mp;
                this.f3443a.f3456b.setOwnSurfaceTexture(false);
                SurfaceTexture surfaceTexture = textureHolder.getSurfaceTexture();
                if (surfaceTexture != null) {
                    this.f3443a.setSurfaceTexture(surfaceTexture);
                    return;
                }
                textureHolder.setSurfaceTexture(this.f3444b);
                textureHolder.setSurfaceTextureHost(this.f3443a.f3456b);
            }
        }

        @NonNull
        public IRenderView getRenderView() {
            return this.f3443a;
        }

        @Nullable
        public SurfaceHolder getSurfaceHolder() {
            return null;
        }

        @Nullable
        public SurfaceTexture getSurfaceTexture() {
            return this.f3444b;
        }

        @Nullable
        public Surface openSurface() {
            if (this.f3444b == null) {
                return null;
            }
            return new Surface(this.f3444b);
        }
    }

    private static final class SurfaceCallback implements SurfaceTextureListener, ISurfaceTextureHost {
        private SurfaceTexture f3446a;
        private boolean f3447b;
        private int f3448c;
        private int f3449d;
        private boolean f3450e = true;
        private boolean f3451f = false;
        private boolean f3452g = false;
        private WeakReference<TextureRenderView> f3453h;
        private Map<IRenderCallback, Object> f3454i = new ConcurrentHashMap();

        public SurfaceCallback(@NonNull TextureRenderView renderView) {
            this.f3453h = new WeakReference(renderView);
        }

        public void setOwnSurfaceTexture(boolean ownSurfaceTexture) {
            this.f3450e = ownSurfaceTexture;
        }

        public void addRenderCallback(@NonNull IRenderCallback callback) {
            this.f3454i.put(callback, callback);
            ISurfaceHolder surfaceHolder = null;
            if (this.f3446a != null) {
                if (null == null) {
                    surfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.f3453h.get(), this.f3446a, this);
                }
                callback.onSurfaceCreated(surfaceHolder, this.f3448c, this.f3449d);
            }
            if (this.f3447b) {
                if (surfaceHolder == null) {
                    surfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.f3453h.get(), this.f3446a, this);
                }
                callback.onSurfaceChanged(surfaceHolder, 0, this.f3448c, this.f3449d);
            }
        }

        public void removeRenderCallback(@NonNull IRenderCallback callback) {
            this.f3454i.remove(callback);
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            this.f3446a = surface;
            this.f3447b = false;
            this.f3448c = 0;
            this.f3449d = 0;
            ISurfaceHolder surfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.f3453h.get(), surface, this);
            for (IRenderCallback renderCallback : this.f3454i.keySet()) {
                renderCallback.onSurfaceCreated(surfaceHolder, 0, 0);
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            this.f3446a = surface;
            this.f3447b = true;
            this.f3448c = width;
            this.f3449d = height;
            ISurfaceHolder surfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.f3453h.get(), surface, this);
            for (IRenderCallback renderCallback : this.f3454i.keySet()) {
                renderCallback.onSurfaceChanged(surfaceHolder, 0, width, height);
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            this.f3446a = surface;
            this.f3447b = false;
            this.f3448c = 0;
            this.f3449d = 0;
            ISurfaceHolder surfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.f3453h.get(), surface, this);
            for (IRenderCallback renderCallback : this.f3454i.keySet()) {
                renderCallback.onSurfaceDestroyed(surfaceHolder);
            }
            Log.d("TextureRenderView", "onSurfaceTextureDestroyed: destroy: " + this.f3450e);
            return this.f3450e;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }

        public void releaseSurfaceTexture(SurfaceTexture surfaceTexture) {
            if (surfaceTexture == null) {
                Log.d("TextureRenderView", "releaseSurfaceTexture: null");
            } else if (this.f3452g) {
                if (surfaceTexture != this.f3446a) {
                    Log.d("TextureRenderView", "releaseSurfaceTexture: didDetachFromWindow(): release different SurfaceTexture");
                    surfaceTexture.release();
                } else if (this.f3450e) {
                    Log.d("TextureRenderView", "releaseSurfaceTexture: didDetachFromWindow(): already released by TextureView");
                } else {
                    Log.d("TextureRenderView", "releaseSurfaceTexture: didDetachFromWindow(): release detached SurfaceTexture");
                    surfaceTexture.release();
                }
            } else if (this.f3451f) {
                if (surfaceTexture != this.f3446a) {
                    Log.d("TextureRenderView", "releaseSurfaceTexture: willDetachFromWindow(): release different SurfaceTexture");
                    surfaceTexture.release();
                } else if (this.f3450e) {
                    Log.d("TextureRenderView", "releaseSurfaceTexture: willDetachFromWindow(): will released by TextureView");
                } else {
                    Log.d("TextureRenderView", "releaseSurfaceTexture: willDetachFromWindow(): re-attach SurfaceTexture to TextureView");
                    setOwnSurfaceTexture(true);
                }
            } else if (surfaceTexture != this.f3446a) {
                Log.d("TextureRenderView", "releaseSurfaceTexture: alive: release different SurfaceTexture");
                surfaceTexture.release();
            } else if (this.f3450e) {
                Log.d("TextureRenderView", "releaseSurfaceTexture: alive: will released by TextureView");
            } else {
                Log.d("TextureRenderView", "releaseSurfaceTexture: alive: re-attach SurfaceTexture to TextureView");
                setOwnSurfaceTexture(true);
            }
        }

        public void willDetachFromWindow() {
            Log.d("TextureRenderView", "willDetachFromWindow()");
            this.f3451f = true;
        }

        public void didDetachFromWindow() {
            Log.d("TextureRenderView", "didDetachFromWindow()");
            this.f3452g = true;
        }
    }

    public TextureRenderView(Context context) {
        super(context);
        m2163a(context);
    }

    public TextureRenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m2163a(context);
    }

    public TextureRenderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m2163a(context);
    }

    @TargetApi(21)
    public TextureRenderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        m2163a(context);
    }

    private void m2163a(Context context) {
        this.f3455a = new MeasureHelper(this);
        this.f3456b = new SurfaceCallback(this);
        setSurfaceTextureListener(this.f3456b);
    }

    public View getView() {
        return this;
    }

    public boolean shouldWaitForResize() {
        return false;
    }

    protected void onDetachedFromWindow() {
        this.f3456b.willDetachFromWindow();
        super.onDetachedFromWindow();
        this.f3456b.didDetachFromWindow();
    }

    public void setVideoSize(int videoWidth, int videoHeight) {
        if (videoWidth > 0 && videoHeight > 0) {
            this.f3455a.setVideoSize(videoWidth, videoHeight);
            requestLayout();
        }
    }

    public void setVideoSampleAspectRatio(int videoSarNum, int videoSarDen) {
        if (videoSarNum > 0 && videoSarDen > 0) {
            this.f3455a.setVideoSampleAspectRatio(videoSarNum, videoSarDen);
            requestLayout();
        }
    }

    public void setVideoRotation(int degree) {
        this.f3455a.setVideoRotation(degree);
        setRotation((float) degree);
    }

    public void setAspectRatio(int aspectRatio) {
        this.f3455a.setAspectRatio(aspectRatio);
        requestLayout();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.f3455a.doMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(this.f3455a.getMeasuredWidth(), this.f3455a.getMeasuredHeight());
    }

    public ISurfaceHolder getSurfaceHolder() {
        return new InternalSurfaceHolder(this, this.f3456b.f3446a, this.f3456b);
    }

    public void addRenderCallback(IRenderCallback callback) {
        this.f3456b.addRenderCallback(callback);
    }

    public void removeRenderCallback(IRenderCallback callback) {
        this.f3456b.removeRenderCallback(callback);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(TextureRenderView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(TextureRenderView.class.getName());
    }
}
