package com.buddy.tiki.view.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.view.video.IRenderView.IRenderCallback;
import com.buddy.tiki.view.video.IRenderView.ISurfaceHolder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import tv.danmaku.ijk.media.exo.IjkExoMediaPlayer;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener;
import tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener;
import tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener;
import tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener;
import tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.TextureMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;
import tv.danmaku.ijk.media.player.misc.IjkMediaFormat;

public class IjkVideoView extends RelativeLayout implements MediaPlayerControl {
    private static final TikiLog f3368f = TikiLog.getInstance(IjkVideoView.class.getSimpleName());
    private static final int[] f3369g = new int[]{0, 1, 2, 4, 5};
    private boolean f3370A;
    private boolean f3371B;
    private boolean f3372C;
    private Context f3373D;
    private Settings f3374E;
    private IRenderView f3375F;
    private int f3376G;
    private int f3377H;
    private InfoHudViewHolder f3378I;
    private long f3379J;
    private long f3380K;
    private long f3381L;
    private long f3382M;
    private OnCompletionListener f3383N;
    private OnInfoListener f3384O;
    private OnErrorListener f3385P;
    private OnBufferingUpdateListener f3386Q;
    private OnSeekCompleteListener f3387R;
    private int f3388S;
    private int f3389T;
    private List<Integer> f3390U;
    private int f3391V;
    private int f3392W;
    FrameLayout f3393a;
    private boolean aa;
    private boolean ab;
    private int ac;
    private int ad;
    private Timer ae;
    private int af;
    ProgressBar f3394b;
    OnVideoSizeChangedListener f3395c;
    OnPreparedListener f3396d;
    IRenderCallback f3397e;
    private String f3398h;
    private Uri f3399i;
    private Map<String, String> f3400j;
    private int f3401k;
    private int f3402l;
    private ISurfaceHolder f3403m;
    private IMediaPlayer f3404n;
    private int f3405o;
    private int f3406p;
    private int f3407q;
    private int f3408r;
    private int f3409s;
    private IMediaController f3410t;
    private OnCompletionListener f3411u;
    private OnPreparedListener f3412v;
    private int f3413w;
    private OnErrorListener f3414x;
    private OnInfoListener f3415y;
    private int f3416z;

    class C05451 implements OnVideoSizeChangedListener {
        final /* synthetic */ IjkVideoView f3358a;

        C05451(IjkVideoView this$0) {
            this.f3358a = this$0;
        }

        public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
            this.f3358a.f3405o = mp.getVideoWidth();
            this.f3358a.f3406p = mp.getVideoHeight();
            this.f3358a.f3376G = mp.getVideoSarNum();
            this.f3358a.f3377H = mp.getVideoSarDen();
            if (this.f3358a.f3405o != 0 && this.f3358a.f3406p != 0) {
                if (this.f3358a.f3375F != null) {
                    this.f3358a.f3375F.setVideoSize(this.f3358a.f3405o, this.f3358a.f3406p);
                    this.f3358a.f3375F.setVideoSampleAspectRatio(this.f3358a.f3376G, this.f3358a.f3377H);
                }
                this.f3358a.requestLayout();
            }
        }
    }

    class C05462 implements OnCompletionListener {
        final /* synthetic */ IjkVideoView f3359a;

        C05462(IjkVideoView this$0) {
            this.f3359a = this$0;
        }

        public void onCompletion(IMediaPlayer mp) {
            this.f3359a.f3401k = 5;
            this.f3359a.f3402l = 5;
            if (this.f3359a.f3410t != null) {
                this.f3359a.f3410t.hide();
            }
            if (this.f3359a.f3411u != null) {
                this.f3359a.f3411u.onCompletion(this.f3359a.f3404n);
            }
        }
    }

    class C05473 implements OnInfoListener {
        final /* synthetic */ IjkVideoView f3360a;

        C05473(IjkVideoView this$0) {
            this.f3360a = this$0;
        }

        public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
            if (this.f3360a.f3415y != null) {
                this.f3360a.f3415y.onInfo(mp, arg1, arg2);
            }
            switch (arg1) {
                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_VIDEO_RENDERING_START:");
                    break;
                case IMediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING /*700*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                    break;
                case IMediaPlayer.MEDIA_INFO_BUFFERING_START /*701*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_BUFFERING_START:");
                    break;
                case IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_BUFFERING_END:");
                    break;
                case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /*703*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                    break;
                case IMediaPlayer.MEDIA_INFO_BAD_INTERLEAVING /*800*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_BAD_INTERLEAVING:");
                    break;
                case IMediaPlayer.MEDIA_INFO_NOT_SEEKABLE /*801*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_NOT_SEEKABLE:");
                    break;
                case IMediaPlayer.MEDIA_INFO_METADATA_UPDATE /*802*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_METADATA_UPDATE:");
                    break;
                case IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE /*901*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    break;
                case IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT /*902*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    break;
                case IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED /*10001*/:
                    this.f3360a.f3409s = arg2;
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                    if (this.f3360a.f3375F != null) {
                        this.f3360a.f3375F.setVideoRotation(arg2);
                        break;
                    }
                    break;
                case IMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START /*10002*/:
                    IjkVideoView.f3368f.m261d("MEDIA_INFO_AUDIO_RENDERING_START:");
                    break;
            }
            return true;
        }
    }

    class C05494 implements OnErrorListener {
        final /* synthetic */ IjkVideoView f3362a;

        class C05481 implements OnClickListener {
            final /* synthetic */ C05494 f3361a;

            C05481(C05494 this$1) {
                this.f3361a = this$1;
            }

            public void onClick(DialogInterface dialog, int whichButton) {
                if (this.f3361a.f3362a.f3411u != null) {
                    this.f3361a.f3362a.f3411u.onCompletion(this.f3361a.f3362a.f3404n);
                }
            }
        }

        C05494(IjkVideoView this$0) {
            this.f3362a = this$0;
        }

        public boolean onError(IMediaPlayer mp, int framework_err, int impl_err) {
            IjkVideoView.f3368f.m261d("Error: " + framework_err + "," + impl_err);
            this.f3362a.f3401k = -1;
            this.f3362a.f3402l = -1;
            if (this.f3362a.f3410t != null) {
                this.f3362a.f3410t.hide();
            }
            if ((this.f3362a.f3414x == null || !this.f3362a.f3414x.onError(this.f3362a.f3404n, framework_err, impl_err)) && this.f3362a.getWindowToken() != null) {
                int messageId;
                Resources r = this.f3362a.f3373D.getResources();
                if (framework_err == IMediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK) {
                    messageId = C0376R.string.VideoView_error_text_invalid_progressive_playback;
                } else {
                    messageId = C0376R.string.VideoView_error_text_unknown;
                }
                new Builder(this.f3362a.getContext()).setMessage(messageId).setPositiveButton((int) C0376R.string.VideoView_error_button, new C05481(this)).setCancelable(false).show();
            }
            return true;
        }
    }

    class C05505 implements OnBufferingUpdateListener {
        final /* synthetic */ IjkVideoView f3363a;

        C05505(IjkVideoView this$0) {
            this.f3363a = this$0;
        }

        public void onBufferingUpdate(IMediaPlayer mp, int percent) {
            this.f3363a.f3413w = percent;
        }
    }

    class C05516 implements OnSeekCompleteListener {
        final /* synthetic */ IjkVideoView f3364a;

        C05516(IjkVideoView this$0) {
            this.f3364a = this$0;
        }

        public void onSeekComplete(IMediaPlayer mp) {
            this.f3364a.f3382M = System.currentTimeMillis();
            if (this.f3364a.f3378I != null) {
                this.f3364a.f3378I.updateSeekCost(this.f3364a.f3382M - this.f3364a.f3381L);
            }
        }
    }

    class C05527 implements OnPreparedListener {
        final /* synthetic */ IjkVideoView f3365a;

        C05527(IjkVideoView this$0) {
            this.f3365a = this$0;
        }

        public void onPrepared(IMediaPlayer mp) {
            this.f3365a.f3380K = System.currentTimeMillis();
            if (this.f3365a.f3378I != null) {
                this.f3365a.f3378I.updateLoadCost(this.f3365a.f3380K - this.f3365a.f3379J);
            }
            this.f3365a.f3401k = 2;
            if (this.f3365a.f3412v != null) {
                this.f3365a.f3412v.onPrepared(this.f3365a.f3404n);
            }
            if (this.f3365a.f3410t != null) {
                this.f3365a.f3410t.setEnabled(true);
            }
            this.f3365a.f3405o = mp.getVideoWidth();
            this.f3365a.f3406p = mp.getVideoHeight();
            int seekToPosition = this.f3365a.f3416z;
            if (seekToPosition != 0) {
                this.f3365a.seekTo(seekToPosition);
            }
            if (this.f3365a.f3405o == 0 || this.f3365a.f3406p == 0) {
                if (this.f3365a.f3402l == 3) {
                    this.f3365a.start();
                }
            } else if (this.f3365a.f3375F != null) {
                this.f3365a.f3375F.setVideoSize(this.f3365a.f3405o, this.f3365a.f3406p);
                this.f3365a.f3375F.setVideoSampleAspectRatio(this.f3365a.f3376G, this.f3365a.f3377H);
                if (!this.f3365a.f3375F.shouldWaitForResize() || (this.f3365a.f3407q == this.f3365a.f3405o && this.f3365a.f3408r == this.f3365a.f3406p)) {
                    if (this.f3365a.f3402l == 3) {
                        this.f3365a.start();
                        if (this.f3365a.f3410t != null) {
                            this.f3365a.f3410t.show();
                        }
                    } else if (!this.f3365a.isPlaying() && ((seekToPosition != 0 || this.f3365a.getCurrentPosition() > 0) && this.f3365a.f3410t != null)) {
                        this.f3365a.f3410t.show(0);
                    }
                }
            }
            long duration = mp.getDuration();
            this.f3365a.f3394b.setMax((int) duration);
            this.f3365a.f3394b.setProgress(0);
            this.f3365a.af = 100;
            this.f3365a.m2105a(true);
            this.f3365a.m2127h();
            IjkVideoView.f3368f.m261d("onPrepared: video duration:" + duration + " delta:" + this.f3365a.af);
        }
    }

    class C05538 implements IRenderCallback {
        final /* synthetic */ IjkVideoView f3366a;

        C05538(IjkVideoView this$0) {
            this.f3366a = this$0;
        }

        public void onSurfaceChanged(@NonNull ISurfaceHolder holder, int format, int w, int h) {
            if (holder.getRenderView() != this.f3366a.f3375F) {
                IjkVideoView.f3368f.m263e("onSurfaceChanged: unmatched render callback\n");
                return;
            }
            this.f3366a.f3407q = w;
            this.f3366a.f3408r = h;
            boolean isValidState;
            if (this.f3366a.f3402l == 3) {
                isValidState = true;
            } else {
                isValidState = false;
            }
            boolean hasValidSize;
            if (!this.f3366a.f3375F.shouldWaitForResize() || (this.f3366a.f3405o == w && this.f3366a.f3406p == h)) {
                hasValidSize = true;
            } else {
                hasValidSize = false;
            }
            if (this.f3366a.f3404n != null && isValidState && hasValidSize) {
                if (this.f3366a.f3416z != 0) {
                    this.f3366a.seekTo(this.f3366a.f3416z);
                }
                this.f3366a.start();
            }
        }

        public void onSurfaceCreated(@NonNull ISurfaceHolder holder, int width, int height) {
            if (holder.getRenderView() != this.f3366a.f3375F) {
                IjkVideoView.f3368f.m263e("onSurfaceCreated: unmatched render callback\n");
                return;
            }
            this.f3366a.f3403m = holder;
            if (this.f3366a.f3404n != null) {
                this.f3366a.m2104a(this.f3366a.f3404n, holder);
            } else {
                this.f3366a.m2109b();
            }
        }

        public void onSurfaceDestroyed(@NonNull ISurfaceHolder holder) {
            if (holder.getRenderView() != this.f3366a.f3375F) {
                IjkVideoView.f3368f.m263e("onSurfaceDestroyed: unmatched render callback\n");
                return;
            }
            this.f3366a.f3403m = null;
            this.f3366a.releaseWithoutStop();
        }
    }

    class C05549 extends TimerTask {
        final /* synthetic */ IjkVideoView f3367a;

        C05549(IjkVideoView this$0) {
            this.f3367a = this$0;
        }

        public void run() {
            if (this.f3367a.f3404n != null) {
                this.f3367a.f3394b.setProgress((int) ((this.f3367a.f3404n.getCurrentPosition() * ((long) this.f3367a.f3394b.getMax())) / this.f3367a.f3404n.getDuration()));
            }
        }
    }

    public IjkVideoView(Context context) {
        this(context, null);
    }

    public IjkVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IjkVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.f3398h = "IjkVideoView";
        this.f3401k = 0;
        this.f3402l = 0;
        this.f3403m = null;
        this.f3404n = null;
        this.f3370A = true;
        this.f3371B = true;
        this.f3372C = true;
        this.f3395c = new C05451(this);
        this.f3379J = 0;
        this.f3380K = 0;
        this.f3381L = 0;
        this.f3382M = 0;
        this.f3383N = new C05462(this);
        this.f3384O = new C05473(this);
        this.f3385P = new C05494(this);
        this.f3386Q = new C05505(this);
        this.f3387R = new C05516(this);
        this.f3388S = 1;
        this.f3389T = f3369g[1];
        this.f3390U = new ArrayList();
        this.f3391V = 0;
        this.f3392W = 0;
        this.aa = false;
        this.ab = false;
        this.ad = 2;
        this.f3396d = new C05527(this);
        this.f3397e = new C05538(this);
        inflate(context, C0376R.layout.view_video_message, this);
        this.f3393a = (FrameLayout) findViewById(C0376R.id.video_container);
        this.f3394b = (ProgressBar) findViewById(C0376R.id.video_progress);
        m2100a(context);
    }

    @TargetApi(21)
    public IjkVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.f3398h = "IjkVideoView";
        this.f3401k = 0;
        this.f3402l = 0;
        this.f3403m = null;
        this.f3404n = null;
        this.f3370A = true;
        this.f3371B = true;
        this.f3372C = true;
        this.f3395c = new C05451(this);
        this.f3379J = 0;
        this.f3380K = 0;
        this.f3381L = 0;
        this.f3382M = 0;
        this.f3383N = new C05462(this);
        this.f3384O = new C05473(this);
        this.f3385P = new C05494(this);
        this.f3386Q = new C05505(this);
        this.f3387R = new C05516(this);
        this.f3388S = 1;
        this.f3389T = f3369g[1];
        this.f3390U = new ArrayList();
        this.f3391V = 0;
        this.f3392W = 0;
        this.aa = false;
        this.ab = false;
        this.ad = 2;
        this.f3396d = new C05527(this);
        this.f3397e = new C05538(this);
        inflate(context, C0376R.layout.view_video_message, this);
        this.f3393a = (FrameLayout) findViewById(C0376R.id.video_container);
        this.f3394b = (ProgressBar) findViewById(C0376R.id.video_progress);
        m2100a(context);
    }

    @NonNull
    public static String getRenderText(Context context, int render) {
        switch (render) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                return context.getString(C0376R.string.VideoView_render_none);
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                return context.getString(C0376R.string.VideoView_render_surface_view);
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                return context.getString(C0376R.string.VideoView_render_texture_view);
            default:
                return context.getString(C0376R.string.N_A);
        }
    }

    @NonNull
    public static String getPlayerText(Context context, int player) {
        switch (player) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                return context.getString(C0376R.string.VideoView_player_AndroidMediaPlayer);
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                return context.getString(C0376R.string.VideoView_player_IjkMediaPlayer);
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                return context.getString(C0376R.string.VideoView_player_IjkExoMediaPlayer);
            default:
                return context.getString(C0376R.string.N_A);
        }
    }

    private void m2100a(Context context) {
        this.f3373D = context.getApplicationContext();
        this.f3374E = new Settings(this.f3373D);
        m2124g();
        m2121f();
        this.f3405o = 0;
        this.f3406p = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.f3401k = 0;
        this.f3402l = 0;
        m2105a(false);
    }

    public void setRenderView(IRenderView renderView) {
        if (this.f3375F != null) {
            if (this.f3404n != null) {
                this.f3404n.setDisplay(null);
            }
            View renderUIView = this.f3375F.getView();
            this.f3375F.removeRenderCallback(this.f3397e);
            this.f3375F = null;
            this.f3393a.removeView(renderUIView);
        }
        if (renderView != null) {
            this.f3375F = renderView;
            renderView.setAspectRatio(this.f3389T);
            if (this.f3405o > 0 && this.f3406p > 0) {
                renderView.setVideoSize(this.f3405o, this.f3406p);
            }
            if (this.f3376G > 0 && this.f3377H > 0) {
                renderView.setVideoSampleAspectRatio(this.f3376G, this.f3377H);
            }
            renderUIView = this.f3375F.getView();
            renderUIView.setLayoutParams(new LayoutParams(-2, -2, 17));
            this.f3393a.addView(renderUIView);
            this.f3394b.bringToFront();
            this.f3375F.addRenderCallback(this.f3397e);
            this.f3375F.setVideoRotation(this.f3409s);
        }
    }

    public void setRender(int render) {
        switch (render) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                setRenderView(null);
                return;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                setRenderView(new SurfaceRenderView(getContext()));
                return;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                TextureRenderView renderView = new TextureRenderView(getContext());
                if (this.f3404n != null) {
                    renderView.getSurfaceHolder().bindToMediaPlayer(this.f3404n);
                    renderView.setVideoSize(this.f3404n.getVideoWidth(), this.f3404n.getVideoHeight());
                    renderView.setVideoSampleAspectRatio(this.f3404n.getVideoSarNum(), this.f3404n.getVideoSarDen());
                    renderView.setAspectRatio(this.f3389T);
                }
                setRenderView(renderView);
                return;
            default:
                f3368f.m263e(String.format(Locale.getDefault(), "invalid render %d\n", new Object[]{Integer.valueOf(render)}));
                return;
        }
    }

    public void setHudView(TableLayout tableLayout) {
        this.f3378I = new InfoHudViewHolder(getContext(), tableLayout);
    }

    public void setVideoPath(String path) {
        setVideoURI(Uri.parse(path));
    }

    public void setVideoURI(Uri uri) {
        m2101a(uri, null);
    }

    private void m2101a(Uri uri, Map<String, String> headers) {
        this.f3399i = uri;
        this.f3400j = headers;
        this.f3416z = 0;
        m2109b();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        if (this.f3404n != null) {
            m2130i();
            this.f3404n.stop();
            this.f3404n.release();
            this.f3404n = null;
            if (this.f3378I != null) {
                this.f3378I.setMediaPlayer(null);
            }
            this.f3401k = 0;
            this.f3402l = 0;
            ((AudioManager) this.f3373D.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    @TargetApi(23)
    private void m2109b() {
        if (this.f3399i != null && this.f3403m != null) {
            release(false);
            ((AudioManager) this.f3373D.getSystemService("audio")).requestAudioFocus(null, 3, 1);
            try {
                this.f3404n = createPlayer(this.ad);
                Context context = getContext();
                this.f3404n.setOnPreparedListener(this.f3396d);
                this.f3404n.setOnVideoSizeChangedListener(this.f3395c);
                this.f3404n.setOnCompletionListener(this.f3383N);
                this.f3404n.setOnErrorListener(this.f3385P);
                this.f3404n.setOnInfoListener(this.f3384O);
                this.f3404n.setOnBufferingUpdateListener(this.f3386Q);
                this.f3404n.setOnSeekCompleteListener(this.f3387R);
                this.f3413w = 0;
                String scheme = this.f3399i.getScheme();
                if (VERSION.SDK_INT >= 23 && this.f3374E.getUsingMediaDataSource() && (TextUtils.isEmpty(scheme) || scheme.equalsIgnoreCase("file"))) {
                    this.f3404n.setDataSource(new FileMediaDataSource(new File(this.f3399i.toString())));
                } else if (VERSION.SDK_INT >= 14) {
                    this.f3404n.setDataSource(this.f3373D, this.f3399i, this.f3400j);
                } else {
                    this.f3404n.setDataSource(this.f3399i.toString());
                }
                m2104a(this.f3404n, this.f3403m);
                this.f3404n.setAudioStreamType(3);
                this.f3404n.setScreenOnWhilePlaying(true);
                this.f3379J = System.currentTimeMillis();
                this.f3404n.prepareAsync();
                if (this.f3378I != null) {
                    this.f3378I.setMediaPlayer(this.f3404n);
                }
                this.f3401k = 1;
                m2112c();
            } catch (IOException ex) {
                f3368f.m270w("Unable to open content: " + this.f3399i, ex);
                this.f3401k = -1;
                this.f3402l = -1;
                this.f3385P.onError(this.f3404n, 1, 0);
            } catch (IllegalArgumentException ex2) {
                f3368f.m270w("Unable to open content: " + this.f3399i, ex2);
                this.f3401k = -1;
                this.f3402l = -1;
                this.f3385P.onError(this.f3404n, 1, 0);
            }
        }
    }

    public void setMediaController(IMediaController controller) {
        if (this.f3410t != null) {
            this.f3410t.hide();
        }
        this.f3410t = controller;
        m2112c();
    }

    private void m2112c() {
        if (this.f3404n != null && this.f3410t != null) {
            View anchorView;
            this.f3410t.setMediaPlayer(this);
            if (getParent() instanceof View) {
                anchorView = (View) getParent();
            } else {
                anchorView = this;
            }
            this.f3410t.setAnchorView(anchorView);
            this.f3410t.setEnabled(m2118e());
        }
    }

    public void setOnPreparedListener(OnPreparedListener l) {
        this.f3412v = l;
    }

    public void setOnCompletionListener(OnCompletionListener l) {
        this.f3411u = l;
    }

    public void setOnErrorListener(OnErrorListener l) {
        this.f3414x = l;
    }

    public void setOnInfoListener(OnInfoListener l) {
        this.f3415y = l;
    }

    private void m2104a(IMediaPlayer mp, ISurfaceHolder holder) {
        if (mp != null) {
            if (holder == null) {
                mp.setDisplay(null);
            } else {
                holder.bindToMediaPlayer(mp);
            }
        }
    }

    public void releaseWithoutStop() {
        if (this.f3404n != null) {
            this.f3404n.setDisplay(null);
        }
    }

    public void release(boolean cleartargetstate) {
        if (this.f3404n != null) {
            m2130i();
            this.f3404n.reset();
            this.f3404n.release();
            this.f3404n = null;
            this.f3401k = 0;
            if (cleartargetstate) {
                this.f3402l = 0;
            }
            ((AudioManager) this.f3373D.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (m2118e() && this.f3410t != null) {
            m2115d();
        }
        return false;
    }

    public boolean onTrackballEvent(MotionEvent ev) {
        if (m2118e() && this.f3410t != null) {
            m2115d();
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isKeyCodeSupported = (keyCode == 4 || keyCode == 24 || keyCode == 25 || keyCode == avcodec.AV_CODEC_ID_TSCC2 || keyCode == 82 || keyCode == 5 || keyCode == 6) ? false : true;
        if (m2118e() && isKeyCodeSupported && this.f3410t != null) {
            if (keyCode == 79 || keyCode == 85) {
                if (this.f3404n.isPlaying()) {
                    pause();
                    this.f3410t.show();
                    return true;
                }
                start();
                this.f3410t.hide();
                return true;
            } else if (keyCode == avutil.AV_PIX_FMT_QSV) {
                if (this.f3404n.isPlaying()) {
                    return true;
                }
                start();
                this.f3410t.hide();
                return true;
            } else if (keyCode != 86 && keyCode != avutil.AV_PIX_FMT_MMAL) {
                m2115d();
            } else if (!this.f3404n.isPlaying()) {
                return true;
            } else {
                pause();
                this.f3410t.show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void m2115d() {
        if (this.f3410t.isShowing()) {
            this.f3410t.hide();
        } else {
            this.f3410t.show();
        }
    }

    public void start() {
        if (m2118e()) {
            this.f3404n.setVolume((float) this.ac, (float) this.ac);
            this.f3404n.setLooping(this.ab);
            this.f3404n.start();
            this.f3401k = 3;
        }
        this.f3402l = 3;
    }

    public void pause() {
        if (m2118e() && this.f3404n.isPlaying()) {
            this.f3404n.pause();
            this.f3401k = 4;
        }
        this.f3402l = 4;
    }

    public void suspend() {
        release(false);
    }

    public void resume() {
        m2109b();
    }

    public int getDuration() {
        if (m2118e()) {
            return (int) this.f3404n.getDuration();
        }
        return -1;
    }

    public int getCurrentPosition() {
        if (m2118e()) {
            return (int) this.f3404n.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int msec) {
        if (m2118e()) {
            this.f3381L = System.currentTimeMillis();
            this.f3404n.seekTo((long) msec);
            this.f3416z = 0;
            return;
        }
        this.f3416z = msec;
    }

    public boolean isPlaying() {
        return m2118e() && this.f3404n.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.f3404n != null) {
            return this.f3413w;
        }
        return 0;
    }

    private boolean m2118e() {
        return (this.f3404n == null || this.f3401k == -1 || this.f3401k == 0 || this.f3401k == 1) ? false : true;
    }

    public boolean canPause() {
        return this.f3370A;
    }

    public boolean canSeekBackward() {
        return this.f3371B;
    }

    public boolean canSeekForward() {
        return this.f3372C;
    }

    public int getAudioSessionId() {
        return 0;
    }

    public int toggleAspectRatio() {
        this.f3388S++;
        this.f3388S %= f3369g.length;
        this.f3389T = f3369g[this.f3388S];
        if (this.f3375F != null) {
            this.f3375F.setAspectRatio(this.f3389T);
        }
        return this.f3389T;
    }

    private void m2121f() {
        this.f3390U.clear();
        if (VERSION.SDK_INT >= 14) {
            this.f3390U.add(Integer.valueOf(2));
        }
        if (this.f3390U.isEmpty()) {
            this.f3390U.add(Integer.valueOf(1));
        }
        this.f3392W = ((Integer) this.f3390U.get(this.f3391V)).intValue();
        setRender(this.f3392W);
    }

    public int toggleRender() {
        this.f3391V++;
        this.f3391V %= this.f3390U.size();
        this.f3392W = ((Integer) this.f3390U.get(this.f3391V)).intValue();
        setRender(this.f3392W);
        return this.f3392W;
    }

    public int togglePlayer() {
        if (this.f3404n != null) {
            this.f3404n.release();
        }
        if (this.f3375F != null) {
            this.f3375F.getView().invalidate();
        }
        m2109b();
        return this.f3374E.getPlayer();
    }

    public IMediaPlayer createPlayer(int playerType) {
        IMediaPlayer mediaPlayer;
        switch (playerType) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                mediaPlayer = new AndroidMediaPlayer();
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                mediaPlayer = new IjkExoMediaPlayer(this.f3373D);
                break;
            default:
                IjkMediaPlayer ijkMediaPlayer = null;
                if (this.f3399i != null) {
                    ijkMediaPlayer = new IjkMediaPlayer();
                    IjkMediaPlayer.native_setLogLevel(3);
                    if (this.f3374E.getUsingMediaCodec()) {
                        ijkMediaPlayer.setOption(4, "mediacodec", 1);
                        if (this.f3374E.getUsingMediaCodecAutoRotate()) {
                            ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 1);
                        } else {
                            ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 0);
                        }
                        if (this.f3374E.getMediaCodecHandleResolutionChange()) {
                            ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 1);
                        } else {
                            ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 0);
                        }
                    } else {
                        ijkMediaPlayer.setOption(4, "mediacodec", 0);
                    }
                    if (this.f3374E.getUsingOpenSLES()) {
                        ijkMediaPlayer.setOption(4, "opensles", 1);
                    } else {
                        ijkMediaPlayer.setOption(4, "opensles", 0);
                    }
                    String pixelFormat = this.f3374E.getPixelFormat();
                    if (TextUtils.isEmpty(pixelFormat)) {
                        ijkMediaPlayer.setOption(4, "overlay-format", 842225234);
                    } else {
                        ijkMediaPlayer.setOption(4, "overlay-format", pixelFormat);
                    }
                    ijkMediaPlayer.setOption(4, "framedrop", 1);
                    ijkMediaPlayer.setOption(4, "start-on-prepared", 0);
                    ijkMediaPlayer.setOption(1, "http-detect-range-support", 0);
                    ijkMediaPlayer.setOption(2, "skip_loop_filter", 48);
                }
                mediaPlayer = ijkMediaPlayer;
                break;
        }
        if (this.f3374E.getEnableDetachedSurfaceTextureView()) {
            return new TextureMediaPlayer(mediaPlayer);
        }
        return mediaPlayer;
    }

    private void m2124g() {
        this.aa = this.f3374E.getEnableBackgroundPlay();
        if (this.aa) {
            MediaPlayerService.intentToStart(getContext());
            this.f3404n = MediaPlayerService.getMediaPlayer();
            if (this.f3378I != null) {
                this.f3378I.setMediaPlayer(this.f3404n);
            }
        }
    }

    public boolean isBackgroundPlayEnabled() {
        return this.aa;
    }

    public void enterBackground() {
        MediaPlayerService.setMediaPlayer(this.f3404n);
    }

    public void stopBackgroundPlay() {
        MediaPlayerService.setMediaPlayer(null);
    }

    public void showMediaInfo() {
        if (this.f3404n != null) {
            int selectedVideoTrack = MediaPlayerCompat.getSelectedTrack(this.f3404n, 1);
            int selectedAudioTrack = MediaPlayerCompat.getSelectedTrack(this.f3404n, 2);
            TableLayoutBinder builder = new TableLayoutBinder(getContext());
            builder.appendSection((int) C0376R.string.mi_player);
            builder.appendRow2((int) C0376R.string.mi_player, MediaPlayerCompat.getName(this.f3404n));
            builder.appendSection((int) C0376R.string.mi_media);
            builder.appendRow2((int) C0376R.string.mi_resolution, m2097a(this.f3405o, this.f3406p, this.f3376G, this.f3377H));
            builder.appendRow2((int) C0376R.string.mi_length, m2098a(this.f3404n.getDuration()));
            ITrackInfo[] trackInfos = this.f3404n.getTrackInfo();
            if (trackInfos != null) {
                int index = -1;
                for (ITrackInfo trackInfo : trackInfos) {
                    index++;
                    int trackType = trackInfo.getTrackType();
                    if (index == selectedVideoTrack) {
                        builder.appendSection(getContext().getString(C0376R.string.mi_stream_fmt1, new Object[]{Integer.valueOf(index)}) + " " + getContext().getString(C0376R.string.mi__selected_video_track));
                    } else if (index == selectedAudioTrack) {
                        builder.appendSection(getContext().getString(C0376R.string.mi_stream_fmt1, new Object[]{Integer.valueOf(index)}) + " " + getContext().getString(C0376R.string.mi__selected_audio_track));
                    } else {
                        builder.appendSection(getContext().getString(C0376R.string.mi_stream_fmt1, new Object[]{Integer.valueOf(index)}));
                    }
                    builder.appendRow2((int) C0376R.string.mi_type, m2096a(trackType));
                    builder.appendRow2((int) C0376R.string.mi_language, m2099a(trackInfo.getLanguage()));
                    IMediaFormat mediaFormat = trackInfo.getFormat();
                    if (mediaFormat != null && (mediaFormat instanceof IjkMediaFormat)) {
                        switch (trackType) {
                            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                                builder.appendRow2((int) C0376R.string.mi_codec, mediaFormat.getString("ijk-codec-long-name-ui"));
                                builder.appendRow2((int) C0376R.string.mi_profile_level, mediaFormat.getString("ijk-profile-level-ui"));
                                builder.appendRow2((int) C0376R.string.mi_pixel_format, mediaFormat.getString("ijk-pixel-format-ui"));
                                builder.appendRow2((int) C0376R.string.mi_resolution, mediaFormat.getString("ijk-resolution-ui"));
                                builder.appendRow2((int) C0376R.string.mi_frame_rate, mediaFormat.getString("ijk-frame-rate-ui"));
                                builder.appendRow2((int) C0376R.string.mi_bit_rate, mediaFormat.getString("ijk-bit-rate-ui"));
                                break;
                            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                                builder.appendRow2((int) C0376R.string.mi_codec, mediaFormat.getString("ijk-codec-long-name-ui"));
                                builder.appendRow2((int) C0376R.string.mi_profile_level, mediaFormat.getString("ijk-profile-level-ui"));
                                builder.appendRow2((int) C0376R.string.mi_sample_rate, mediaFormat.getString("ijk-sample-rate-ui"));
                                builder.appendRow2((int) C0376R.string.mi_channels, mediaFormat.getString("ijk-channel-ui"));
                                builder.appendRow2((int) C0376R.string.mi_bit_rate, mediaFormat.getString("ijk-bit-rate-ui"));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            Builder adBuilder = builder.buildAlertDialogBuilder();
            adBuilder.setTitle((int) C0376R.string.media_information);
            adBuilder.setNegativeButton((int) C0376R.string.close, null);
            adBuilder.show();
        }
    }

    private String m2097a(int width, int height, int sarNum, int sarDen) {
        StringBuilder sb = new StringBuilder();
        sb.append(width);
        sb.append(" x ");
        sb.append(height);
        if (sarNum > 1 || sarDen > 1) {
            sb.append("[");
            sb.append(sarNum);
            sb.append(":");
            sb.append(sarDen);
            sb.append("]");
        }
        return sb.toString();
    }

    private String m2098a(long duration) {
        long total_seconds = duration / 1000;
        long hours = total_seconds / 3600;
        long minutes = (total_seconds % 3600) / 60;
        long seconds = total_seconds % 60;
        if (duration <= 0) {
            return "--:--";
        }
        if (hours >= 100) {
            return String.format(Locale.US, "%d:%02d:%02d", new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)});
        } else if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)});
        } else {
            return String.format(Locale.US, "%02d:%02d", new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)});
        }
    }

    private String m2096a(int type) {
        Context context = getContext();
        switch (type) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                return context.getString(C0376R.string.TrackType_video);
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                return context.getString(C0376R.string.TrackType_audio);
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                return context.getString(C0376R.string.TrackType_timedtext);
            case swscale.SWS_CS_FCC /*4*/:
                return context.getString(C0376R.string.TrackType_subtitle);
            case swscale.SWS_CS_SMPTE170M /*5*/:
                return context.getString(C0376R.string.TrackType_metadata);
            default:
                return context.getString(C0376R.string.TrackType_unknown);
        }
    }

    private String m2099a(String language) {
        if (TextUtils.isEmpty(language)) {
            return "und";
        }
        return language;
    }

    public ITrackInfo[] getTrackInfo() {
        if (this.f3404n == null) {
            return null;
        }
        return this.f3404n.getTrackInfo();
    }

    public void selectTrack(int stream) {
        MediaPlayerCompat.selectTrack(this.f3404n, stream);
    }

    public void deselectTrack(int stream) {
        MediaPlayerCompat.deselectTrack(this.f3404n, stream);
    }

    public int getSelectedTrack(int trackType) {
        return MediaPlayerCompat.getSelectedTrack(this.f3404n, trackType);
    }

    private void m2105a(boolean display) {
        if (display) {
            this.f3394b.setVisibility(0);
        } else {
            this.f3394b.setVisibility(8);
        }
    }

    private void m2127h() {
        if (this.ae != null) {
            this.ae.cancel();
            this.ae = null;
        }
        this.ae = new Timer();
        this.ae.scheduleAtFixedRate(new C05549(this), 0, 100);
    }

    private void m2130i() {
        if (this.ae != null) {
            this.ae.cancel();
            this.ae = null;
        }
    }

    public void setLooping(boolean looping) {
        this.ab = looping;
    }

    public void setVolume(int volume) {
        this.ac = volume;
    }

    public void setPlayerType(int type) {
        if (type == 1 || type == 3 || type == 2) {
            this.ad = type;
        }
    }
}
