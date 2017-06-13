package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.view.Surface;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.bytedeco.javacpp.swscale;

public class VideoFilterPlayer extends GLSurfaceView implements OnFrameAvailableListener, Renderer {
    private Context f2902a;
    private MediaPlayer f2903b;
    private Uri f2904c;
    private int f2905d;
    private SurfaceTexture f2906e;
    private float[] f2907f;
    private int f2908g;
    private int f2909h;
    private PlayerInitializeCallback f2910i;
    private PlayCompletionCallback f2911j;
    private PlayPreparedCallback f2912k;

    public interface PlayCompletionCallback {
        void playComplete(MediaPlayer mediaPlayer);

        boolean playFailed(MediaPlayer mediaPlayer);
    }

    public interface PlayPreparedCallback {
        void playPrepared(MediaPlayer mediaPlayer);
    }

    public interface PlayerInitializeCallback {
        void initPlayer(MediaPlayer mediaPlayer);
    }

    public VideoFilterPlayer(Context context) {
        this(context, null);
    }

    public VideoFilterPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.f2907f = new float[16];
        this.f2902a = context;
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(1);
        setRenderer(this);
        setRenderMode(0);
        setZOrderOnTop(true);
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        requestRender();
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glDisable(2929);
        GLES20.glDisable(2960);
        if (this.f2904c == null) {
            return;
        }
        if (this.f2906e == null || this.f2905d == 0) {
            this.f2905d = getSurfaceTextureId();
            this.f2906e = new SurfaceTexture(this.f2905d);
            this.f2906e.setOnFrameAvailableListener(this);
            m1811c();
        }
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void onDrawFrame(GL10 gl) {
        if (this.f2906e != null) {
            this.f2906e.updateTexImage();
            if (this.f2903b.isPlaying()) {
                this.f2906e.getTransformMatrix(this.f2907f);
                GLES20.glBindFramebuffer(36160, 0);
                GLES20.glClear(swscale.SWS_FULL_CHR_H_INP);
                GLES20.glEnable(3042);
                GLES20.glDisable(3042);
            }
        }
    }

    public void release() {
        if (this.f2903b != null) {
            queueEvent(VideoFilterPlayer$$Lambda$1.lambdaFactory$(this));
        }
    }

    /* synthetic */ void m1814b() {
        if (this.f2903b != null) {
            this.f2903b.setSurface(null);
            if (this.f2903b.isPlaying()) {
                this.f2903b.stop();
            }
            this.f2903b.release();
            this.f2903b = null;
        }
        if (this.f2906e != null) {
            this.f2906e.release();
            this.f2906e = null;
        }
        if (this.f2905d != 0) {
            GLES20.glDeleteTextures(1, new int[]{this.f2905d}, 0);
            this.f2905d = 0;
        }
        this.f2912k = null;
        this.f2911j = null;
    }

    public synchronized void setVideoUri(Uri uri, PlayPreparedCallback preparedCallback, PlayCompletionCallback completeCallback) {
        this.f2904c = uri;
        this.f2912k = preparedCallback;
        this.f2911j = completeCallback;
        queueEvent(VideoFilterPlayer$$Lambda$2.lambdaFactory$(this));
    }

    /* synthetic */ void m1812a() {
        if (this.f2906e == null || this.f2905d == 0) {
            this.f2905d = getSurfaceTextureId();
            this.f2906e = new SurfaceTexture(this.f2905d);
            this.f2906e.setOnFrameAvailableListener(this);
        }
        m1811c();
    }

    public void setPlayInitializeCallback(PlayerInitializeCallback playInitializeCallback) {
        this.f2910i = playInitializeCallback;
    }

    private int getSurfaceTextureId() {
        int[] texId = new int[1];
        GLES20.glGenTextures(1, texId, 0);
        GLES20.glBindTexture(36197, texId[0]);
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        return texId[0];
    }

    private void m1811c() {
        if (this.f2903b != null) {
            this.f2903b.stop();
            this.f2903b.reset();
        } else {
            this.f2903b = new MediaPlayer();
        }
        try {
            this.f2903b.setDataSource(this.f2902a, this.f2904c);
            this.f2903b.setSurface(new Surface(this.f2906e));
        } catch (Exception e) {
        }
        this.f2903b.setOnPreparedListener(VideoFilterPlayer$$Lambda$3.lambdaFactory$(this));
    }

    /* synthetic */ void m1813a(MediaPlayer mp) {
        this.f2908g = mp.getVideoWidth();
        this.f2909h = mp.getVideoHeight();
    }
}
