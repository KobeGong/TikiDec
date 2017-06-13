package com.buddy.tiki.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.AudioRecord;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.camera.CameraHelper;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.im.IMRtcClient;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.constant.MsgDataType;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.dialog.LoadingDialog;
import com.buddy.tiki.util.BitmapUtil;
import com.buddy.tiki.util.FileUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.view.CircleProgressView;
import com.buddy.tiki.view.SurfaceViewRenderer;
import com.buddy.tiki.wertc.BiuVideoCapturer2;
import com.buddy.tiki.wertc.BiuVideoCapturer2.PreviewCallback;
import com.buddy.tiki.wertc.PeerConnectionClient;
import com.buddy.tiki.wertc.PeerConnectionClient.PeerConnectionParameters;
import com.buddy.tiki.wertc.PeerConnectionClient.SimplePeerConnectionEvents;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding2.view.RxView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.CameraVideoCapturer.CameraSwitchHandler;
import org.webrtc.EglBase;
import org.webrtc.GlRectDrawer;
import org.webrtc.MediaCodecVideoEncoder;

public class VideoRecordActivity extends BaseActivity {
    private static final TikiLog f1525d = TikiLog.getInstance("VideoRecordActivity");
    private AudioRecordRunnable f1526A;
    private Thread f1527B;
    private boolean f1528C = false;
    private HandlerThread f1529D;
    private Handler f1530E;
    private long f1531F;
    boolean f1532a = false;
    volatile boolean f1533b = true;
    @BindView(2131820783)
    AppCompatImageView back;
    private final int f1534e = 44100;
    private final int f1535f = 24;
    private final long f1536g = (TimeUnit.MILLISECONDS.toNanos(1000) / 24);
    private String f1537h;
    private boolean f1538i = false;
    private PeerConnectionClient f1539j;
    private PeerConnectionParameters f1540k;
    private EglBase f1541l;
    private boolean f1542m = true;
    @BindView(2131820782)
    SurfaceViewRenderer mRecordPreview;
    @BindView(2131820789)
    LinearLayout maskTip;
    private String f1543n;
    private String f1544o;
    @BindView(2131820790)
    SimpleDraweeView openMask;
    private boolean f1545p = true;
    private long f1546q;
    private long f1547r;
    @BindView(2131820785)
    CircleProgressView recordButton;
    @BindView(2131820786)
    AppCompatImageView recordButtonIn;
    @BindView(2131820787)
    LinearLayout recordTip;
    @BindView(2131820788)
    AppCompatTextView recordTipText;
    @BindView(2131820784)
    AppCompatImageView rotate;
    private boolean f1548s = false;
    private Frame f1549t = null;
    private FFmpegFrameRecorder f1550u;
    private int f1551v = 480;
    private int f1552w = 640;
    private long f1553x;
    private long f1554y = 0;
    private AudioRecord f1555z;

    class C04431 implements AnimationListener {
        final /* synthetic */ VideoRecordActivity f1515a;

        C04431(VideoRecordActivity this$0) {
            this.f1515a = this$0;
        }

        public void onAnimationStart(Animation animation) {
            if (PreferenceUtil.getRecordTip() == 0) {
                PreferenceUtil.setRecordTip(1);
                this.f1515a.recordTipText.setText(this.f1515a.getResources().getString(C0376R.string.click_to_stop_record));
            }
            this.f1515a.f1538i = false;
        }

        public void onAnimationEnd(Animation animation) {
            this.f1515a.recordButtonIn.setVisibility(8);
            if (!this.f1515a.recordButton.isRecording()) {
                this.f1515a.recordButton.setRecording(true);
                this.f1515a.m884j();
                Observable.interval(0, 100, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(this.f1515a.lifecycle(), ActivityEvent.DESTROY)).compose(this.f1515a.bindUntilEvent(ActivityEvent.PAUSE)).subscribe(VideoRecordActivity$1$$Lambda$1.lambdaFactory$(this));
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* synthetic */ void m854a(java.lang.Long r7) throws java.lang.Exception {
            /*
            r6 = this;
            r2 = 0;
            r0 = r7.intValue();
            r1 = r6.f1515a;
            r1 = r1.recordButton;
            r1 = r1.getMax();
            if (r0 > r1) goto L_0x001b;
        L_0x000f:
            r0 = r6.f1515a;
            r0 = r0.recordButton;
            r1 = r7.intValue();
            r0.setProgress(r1);
        L_0x001a:
            return;
        L_0x001b:
            r0 = r6.f1515a;
            r0 = r0.recordButton;
            r0.setRecording(r2);
            r0 = r6.f1515a;
            r0 = r0.recordButton;
            r0.setProgress(r2);
            r0 = r6.f1515a;
            r0 = r0.f1538i;
            if (r0 != 0) goto L_0x001a;
        L_0x0031:
            r1 = r6.f1515a;
            monitor-enter(r1);
            r0 = r6.f1515a;	 Catch:{ all -> 0x0066 }
            r0 = r0.f1538i;	 Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x0074;
        L_0x003c:
            r0 = r6.f1515a;	 Catch:{ all -> 0x0066 }
            r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x0066 }
            r0.f1547r = r2;	 Catch:{ all -> 0x0066 }
            r0 = r6.f1515a;	 Catch:{ all -> 0x0066 }
            r2 = r0.f1547r;	 Catch:{ all -> 0x0066 }
            r0 = r6.f1515a;	 Catch:{ all -> 0x0066 }
            r4 = r0.f1546q;	 Catch:{ all -> 0x0066 }
            r2 = r2 - r4;
            r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r0 >= 0) goto L_0x0069;
        L_0x0058:
            r0 = com.buddy.tiki.util.ToastUtil.getInstance();	 Catch:{ all -> 0x0066 }
            r2 = r6.f1515a;	 Catch:{ all -> 0x0066 }
            r3 = 2131296594; // 0x7f090152 float:1.821111E38 double:1.053000428E-314;
            r0.show(r2, r3);	 Catch:{ all -> 0x0066 }
            monitor-exit(r1);	 Catch:{ all -> 0x0066 }
            goto L_0x001a;
        L_0x0066:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0066 }
            throw r0;
        L_0x0069:
            r0 = r6.f1515a;	 Catch:{ all -> 0x0066 }
            r2 = 1;
            r0.f1538i = r2;	 Catch:{ all -> 0x0066 }
            r0 = r6.f1515a;	 Catch:{ all -> 0x0066 }
            r0.m886k();	 Catch:{ all -> 0x0066 }
        L_0x0074:
            monitor-exit(r1);	 Catch:{ all -> 0x0066 }
            goto L_0x001a;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.ui.activity.VideoRecordActivity.1.a(java.lang.Long):void");
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    class C04442 implements CameraSwitchHandler {
        final /* synthetic */ VideoRecordActivity f1516a;

        C04442(VideoRecordActivity this$0) {
            this.f1516a = this$0;
        }

        public void onCameraSwitchDone(boolean b) {
            this.f1516a.f1542m = !this.f1516a.f1542m;
            if (this.f1516a.mRecordPreview != null) {
                this.f1516a.mRecordPreview.setMirror(this.f1516a.f1542m);
            }
        }

        public void onCameraSwitchError(String s) {
            VideoRecordActivity.f1525d.m261d("onCameraSwitchError:" + s);
            ToastUtil.getInstance().show((int) C0376R.string.switch_camera_error);
        }
    }

    class C04453 implements PreviewCallback {
        final /* synthetic */ VideoRecordActivity f1517a;

        C04453(VideoRecordActivity this$0) {
            this.f1517a = this$0;
        }

        public void onPreviewFrame(byte[] data, int width, int height, int rotation, long timstampNs) {
            if (this.f1517a.f1530E != null && this.f1517a.f1532a) {
                this.f1517a.f1530E.post(new VideoRecordRunnable(this.f1517a, data, width, height, rotation, timstampNs));
            }
        }
    }

    class AudioRecordRunnable implements Runnable {
        final /* synthetic */ VideoRecordActivity f1518a;

        AudioRecordRunnable(VideoRecordActivity this$0) {
            this.f1518a = this$0;
        }

        public void run() {
            Process.setThreadPriority(-19);
            int bufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
            this.f1518a.f1555z = new AudioRecord(1, 44100, 16, 2, bufferSize);
            ShortBuffer audioData = ShortBuffer.allocate(bufferSize);
            VideoRecordActivity.f1525d.m261d("audioRecord.startRecording()");
            this.f1518a.f1555z.startRecording();
            while (this.f1518a.f1533b) {
                int bufferReadResult = this.f1518a.f1555z.read(audioData.array(), 0, audioData.capacity());
                if (bufferReadResult > 0) {
                    audioData.limit(bufferReadResult);
                    VideoRecordActivity.f1525d.m261d("bufferReadResult: " + bufferReadResult);
                    if (this.f1518a.f1532a) {
                        try {
                            VideoRecordActivity.f1525d.m261d("recorder.recordSamples");
                            this.f1518a.f1550u.recordSamples(new Buffer[]{audioData});
                        } catch (Exception e) {
                            VideoRecordActivity.f1525d.m261d(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }
            VideoRecordActivity.f1525d.m261d("AudioThread Finished, release audioRecord");
            if (this.f1518a.f1555z != null) {
                this.f1518a.f1555z.stop();
                this.f1518a.f1555z.release();
                this.f1518a.f1555z = null;
                VideoRecordActivity.f1525d.m261d("audioRecord released");
            }
        }
    }

    class VideoRecordRunnable implements Runnable {
        final /* synthetic */ VideoRecordActivity f1519a;
        private byte[] f1520b;
        private int f1521c;
        private int f1522d;
        private int f1523e;
        private long f1524f;

        public VideoRecordRunnable(VideoRecordActivity this$0, byte[] data, int width, int height, int rotation, long timestampNs) {
            this.f1519a = this$0;
            if (data != null) {
                this.f1520b = new byte[data.length];
                System.arraycopy(data, 0, this.f1520b, 0, data.length);
            }
            this.f1521c = width;
            this.f1522d = height;
            this.f1523e = rotation;
            this.f1524f = timestampNs;
        }

        public void run() {
            if (this.f1519a.f1555z == null || this.f1519a.f1555z.getRecordingState() != 3) {
                this.f1519a.f1554y = SystemClock.elapsedRealtimeNanos();
                this.f1519a.f1531F = this.f1519a.f1554y;
            } else if (this.f1519a.f1549t != null && this.f1519a.f1532a) {
                byte[] rotatedData;
                int rotatedWidth = this.f1522d;
                int rotatedHeight = this.f1521c;
                long start = SystemClock.elapsedRealtimeNanos();
                if (this.f1519a.f1542m) {
                    rotatedData = BitmapUtil.rotateNV21Degree270AndHorizontalFlip(this.f1520b, this.f1521c, this.f1522d);
                } else {
                    rotatedData = BitmapUtil.rotateNV21Degree90(this.f1520b, this.f1521c, this.f1522d);
                }
                VideoRecordActivity.f1525d.m261d("rotate time:" + (SystemClock.elapsedRealtimeNanos() - start));
                if (rotatedData != null) {
                    if (this.f1519a.f1545p) {
                        synchronized (this.f1519a) {
                            if (this.f1519a.f1545p) {
                                this.f1519a.m861a(rotatedData, rotatedWidth, rotatedHeight);
                                this.f1519a.f1545p = false;
                            }
                        }
                    }
                    ((ByteBuffer) this.f1519a.f1549t.g[0].position(0)).put(rotatedData, 0, Math.min(this.f1519a.f1549t.g[0].capacity(), rotatedData.length));
                    this.f1519a.f1549t.g[0].position(0);
                    try {
                        long now = SystemClock.elapsedRealtimeNanos();
                        if (now - this.f1519a.f1531F > this.f1519a.f1536g) {
                            VideoRecordActivity.f1525d.m261d("Writing Frame:now:" + now + " + last:" + this.f1519a.f1531F + " min:" + this.f1519a.f1536g + " start:" + this.f1519a.f1554y);
                            long t = TimeUnit.NANOSECONDS.toMicros(now - this.f1519a.f1554y);
                            if (t > this.f1519a.f1550u.getTimestamp()) {
                                this.f1519a.f1550u.setTimestamp(t);
                            }
                            this.f1519a.f1550u.record(this.f1519a.f1549t);
                            this.f1519a.f1531F = now;
                        }
                    } catch (Exception e) {
                        VideoRecordActivity.f1525d.m264e(e.getMessage(), e);
                    }
                }
            }
        }
    }

    static {
        System.loadLibrary("avutil");
    }

    @LayoutRes
    protected int mo2115a() {
        return C0376R.layout.activity_video_record;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m873d();
        m875e();
        m876f();
        m878g();
        m881h();
        m432e(this.back);
        m432e(this.rotate);
        m431d(this.recordButton);
        m431d(this.recordButtonIn);
        m431d(this.recordTip);
        m431d(this.maskTip);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setNavigationBarTintResource(C0376R.color.black_alpha_normal);
    }

    private void m873d() {
        this.f1541l = EglBase.create(null, EglBase.CONFIG_PLAIN);
        this.mRecordPreview.init(this.f1541l.getEglBaseContext(), null, EglBase.CONFIG_PLAIN, new GlRectDrawer());
        this.mRecordPreview.setZOrderMediaOverlay(false);
        this.mRecordPreview.setMirror(this.f1542m);
        this.recordButton.setMax(avutil.AV_PIX_FMT_YUV420P12LE);
        int recordTipValue = PreferenceUtil.getRecordTip();
        if (recordTipValue < 2) {
            this.recordTip.setVisibility(0);
            if (recordTipValue == 0) {
                this.recordTipText.setText(getResources().getString(C0376R.string.click_to_start_record));
            } else {
                this.recordTipText.setText(getResources().getString(C0376R.string.click_to_stop_record));
            }
        } else {
            this.recordTip.setVisibility(8);
        }
        if (PreferenceUtil.getMaskTip() == 0) {
            this.maskTip.setVisibility(0);
        } else {
            this.maskTip.setVisibility(8);
        }
        m870c(true);
    }

    private void m875e() {
        if (getArguments() != null) {
            this.f1537h = getArguments().getString("PARAM_KEY_UID");
        }
        if (TextUtils.isEmpty(this.f1537h)) {
            finish();
        }
        boolean z = VERSION.SDK_INT < 19 && !MediaCodecVideoEncoder.isH264HwSupported();
        this.f1528C = z;
    }

    private void m876f() {
        if (this.f1540k == null) {
            this.f1540k = CameraHelper.loadParamsFromPref(this);
        }
        if (this.f1539j == null) {
            this.f1539j = PeerConnectionClient.getInstance();
        }
        this.f1539j.setQuality(1);
        this.f1539j.createPeerConnectionFactory(this, this.f1540k, new SimplePeerConnectionEvents());
        this.f1539j.setOnPeerConnectionCreated(VideoRecordActivity$$Lambda$1.lambdaFactory$(this));
        this.f1539j.createPeerConnection(this.f1541l.getEglBaseContext(), this.mRecordPreview, IMRtcClient.getInstance(), null);
    }

    /* synthetic */ void m896a(PeerConnectionClient peerConnectionClient) {
        m883i();
        this.f1539j.setOnPeerConnectionCreated(null);
    }

    private void m878g() {
        RxView.clicks(this.recordButton).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), ActivityEvent.DESTROY)).subscribe(VideoRecordActivity$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.recordButtonIn).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), ActivityEvent.DESTROY)).subscribe(VideoRecordActivity$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.back).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), ActivityEvent.DESTROY)).subscribe(VideoRecordActivity$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.rotate).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), ActivityEvent.DESTROY)).subscribe(VideoRecordActivity$$Lambda$5.lambdaFactory$(this));
        RxView.clicks(this.openMask).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), ActivityEvent.DESTROY)).subscribe(VideoRecordActivity$$Lambda$6.lambdaFactory$(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* synthetic */ void m907e(java.lang.Object r5) throws java.lang.Exception {
        /*
        r4 = this;
        r1 = 1;
        r2 = 0;
        r0 = r4.recordButton;
        r0 = r0.isRecording();
        if (r0 == 0) goto L_0x004a;
    L_0x000a:
        r0 = com.buddy.tiki.util.PreferenceUtil.getRecordTip();
        if (r0 != r1) goto L_0x001b;
    L_0x0010:
        r0 = 2;
        com.buddy.tiki.util.PreferenceUtil.setRecordTip(r0);
        r0 = r4.recordTip;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x001b:
        r0 = r4.recordButton;
        r0.setRecording(r2);
        r0 = r4.recordButton;
        r0.setProgress(r2);
        r0 = r4.f1538i;
        if (r0 != 0) goto L_0x004a;
    L_0x0029:
        monitor-enter(r4);
        r0 = r4.f1538i;	 Catch:{ all -> 0x0053 }
        if (r0 != 0) goto L_0x0051;
    L_0x002e:
        r0 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x0053 }
        r4.f1547r = r0;	 Catch:{ all -> 0x0053 }
        r0 = r4.f1547r;	 Catch:{ all -> 0x0053 }
        r2 = r4.f1546q;	 Catch:{ all -> 0x0053 }
        r0 = r0 - r2;
        r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x004b;
    L_0x003f:
        r0 = com.buddy.tiki.util.ToastUtil.getInstance();	 Catch:{ all -> 0x0053 }
        r1 = 2131296594; // 0x7f090152 float:1.821111E38 double:1.053000428E-314;
        r0.show(r4, r1);	 Catch:{ all -> 0x0053 }
        monitor-exit(r4);	 Catch:{ all -> 0x0053 }
    L_0x004a:
        return;
    L_0x004b:
        r0 = 1;
        r4.f1538i = r0;	 Catch:{ all -> 0x0053 }
        r4.m886k();	 Catch:{ all -> 0x0053 }
    L_0x0051:
        monitor-exit(r4);	 Catch:{ all -> 0x0053 }
        goto L_0x004a;
    L_0x0053:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0053 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.ui.activity.VideoRecordActivity.e(java.lang.Object):void");
    }

    /* synthetic */ void m906d(Object aVoid) throws Exception {
        Animation animation = AnimationUtils.loadAnimation(this, C0376R.anim.recording);
        animation.setAnimationListener(new C04431(this));
        animation.setInterpolator(new LinearInterpolator());
        this.recordButtonIn.clearAnimation();
        this.recordButtonIn.startAnimation(animation);
    }

    /* synthetic */ void m905c(Object aVoid) throws Exception {
        finish();
    }

    /* synthetic */ void m904b(Object aVoid) throws Exception {
        if (this.f1539j != null) {
            this.f1539j.switchCamera(new C04442(this));
        }
    }

    /* synthetic */ void m898a(Object aVoid) throws Exception {
        if (PreferenceUtil.getMaskTip() == 0) {
            PreferenceUtil.setMaskTip(1);
            this.maskTip.setVisibility(8);
        }
        m860a(true);
        DialogHelper.INSTANCE.showFaceuDialog(this, VideoRecordActivity$$Lambda$13.lambdaFactory$(this));
    }

    /* synthetic */ void m894a(DialogInterface dialog) {
        m860a(false);
    }

    protected int mo2117b() {
        return 0;
    }

    protected void onResume() {
        super.onResume();
        long delay = 0;
        if (this.f1548s) {
            if (this.f1541l == null) {
                this.f1541l = EglBase.create(null, EglBase.CONFIG_PLAIN);
                this.mRecordPreview.init(this.f1541l.getEglBaseContext(), null);
            }
            if (this.mRecordPreview != null) {
                this.f1539j.setLocalRender(this.mRecordPreview);
            }
            m876f();
            m881h();
            this.f1548s = false;
            delay = 2000;
        }
        Observable.timer(delay, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), ActivityEvent.DESTROY)).subscribe(VideoRecordActivity$$Lambda$7.lambdaFactory$(this));
        this.recordButtonIn.setVisibility(0);
    }

    /* synthetic */ void m903b(Long aLong) throws Exception {
        if (this.f1539j != null) {
            f1525d.m261d("onResume: startVideoSource");
            this.f1539j.startVideoSource();
        }
    }

    protected void onPause() {
        if (this.f1539j != null) {
            this.f1539j.stopVideoSource();
        }
        super.onPause();
    }

    protected void onDestroy() {
        if (this.mRecordPreview != null) {
            this.mRecordPreview.release();
            this.mRecordPreview = null;
        }
        super.onDestroy();
    }

    private void m881h() {
        try {
            f1525d.m265i("init recorder");
            this.f1553x = SystemClock.elapsedRealtime();
            this.f1543n = FileUtil.newCacheFile("Video") + File.separator + this.f1537h + this.f1553x + ".mp4";
            this.f1544o = FileUtil.newCacheFile("VideoCover") + File.separator + this.f1537h + this.f1553x + ".jpg";
            this.f1549t = new Frame(this.f1551v, this.f1552w, 8, 2);
            f1525d.m265i("create yuvImage");
            this.f1550u = new FFmpegFrameRecorder(this.f1543n, this.f1551v, this.f1552w, 1);
            this.f1550u.setFormat("mp4");
            this.f1550u.setSampleRate(44100);
            this.f1550u.setVideoBitrate((((this.f1551v * this.f1552w) * 10) * 24) / 100);
            this.f1550u.setFrameRate(24.0d);
            Observable.interval(1000, TimeUnit.MILLISECONDS, Schedulers.newThread()).compose(bindUntilEvent(ActivityEvent.PAUSE)).subscribe(VideoRecordActivity$$Lambda$8.lambdaFactory$());
            if (this.f1528C) {
                this.f1551v = MsgDataType.CALL_CLOSE_MSG;
                this.f1552w = avutil.AV_PIX_FMT_BAYER_BGGR16LE;
            } else {
                this.f1550u.setVideoCodec(28);
            }
            f1525d.m265i("recorder initialize success");
            this.f1526A = new AudioRecordRunnable(this);
            this.f1527B = new Thread(this.f1526A);
            this.f1533b = true;
            m870c(false);
            this.f1529D = new HandlerThread("VideoThread");
            this.f1529D.start();
            this.f1530E = new Handler(this.f1529D.getLooper());
            return;
        } catch (Throwable th) {
        }
        f1525d.m263e("initRecord failed");
        finish();
    }

    private void m883i() {
        f1525d.m261d("initVideoCapturerCallback");
        if (this.f1539j != null) {
            f1525d.m261d("initVideoCapturerCallback 1");
            CameraVideoCapturer videoCapturer = this.f1539j.getVideoCapturer();
            if (videoCapturer != null && (videoCapturer instanceof BiuVideoCapturer2)) {
                f1525d.m261d("initVideoCapturerCallback 2");
                int width = ((BiuVideoCapturer2) videoCapturer).getPreviewWidth();
                int height = ((BiuVideoCapturer2) videoCapturer).getPreviewHeight();
                ((BiuVideoCapturer2) videoCapturer).setPreviewCallback(new C04453(this));
            }
        }
    }

    private void m884j() {
        try {
            f1525d.m261d("recorder:" + this.f1550u);
            this.f1546q = this.f1553x;
            m865b(true);
            this.f1550u.start();
            long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            this.f1554y = elapsedRealtimeNanos;
            this.f1531F = elapsedRealtimeNanos;
            this.f1532a = true;
            this.f1527B.start();
            return;
        } catch (Throwable th) {
        }
        finish();
    }

    private void m886k() {
        LoadingDialog.startLoading((Context) this, (int) C0376R.string.processing);
        m860a(false);
        this.recordButton.setProgress(0);
        this.recordButton.setRecording(false);
        CameraVideoCapturer videoCapturer = this.f1539j.getVideoCapturer();
        if (videoCapturer != null && (videoCapturer instanceof BiuVideoCapturer2)) {
            ((BiuVideoCapturer2) videoCapturer).setPreviewCallback(null);
        }
        Observable.just(Integer.valueOf(1)).compose(RxLifecycle.bindUntilEvent(lifecycle(), ActivityEvent.DESTROY)).subscribeOn(Schedulers.newThread()).doOnNext(VideoRecordActivity$$Lambda$9.lambdaFactory$(this)).observeOn(AndroidSchedulers.mainThread()).subscribe(VideoRecordActivity$$Lambda$10.lambdaFactory$(this), VideoRecordActivity$$Lambda$11.lambdaFactory$(this));
    }

    /* synthetic */ void m902b(Integer value) throws Exception {
        m889l();
    }

    /* synthetic */ void m897a(Integer value) throws Exception {
        LoadingDialog.stopLoading();
        m891m();
    }

    /* synthetic */ void m899a(Throwable throwable) throws Exception {
        f1525d.m264e(throwable.getMessage(), throwable);
        ToastUtil.getInstance().show((Context) this, (int) C0376R.string.record_failed);
    }

    private void m889l() {
        this.f1533b = false;
        try {
            this.f1527B.join();
        } catch (InterruptedException e) {
            f1525d.m264e(e.getMessage(), e);
        }
        this.f1526A = null;
        this.f1527B = null;
        if (this.f1550u != null && this.f1532a) {
            this.f1532a = false;
            this.f1529D.quitSafely();
            f1525d.m261d("Finishing recording, calling stop and release on recorder");
            try {
                this.f1550u.stop();
                this.f1550u.release();
            } catch (Exception e2) {
                f1525d.m264e(e2.getMessage(), e2);
            }
            this.f1550u = null;
        }
    }

    private void m861a(byte[] data, int width, int height) {
        Observable.just(Integer.valueOf(1)).observeOn(Schedulers.io()).subscribe(VideoRecordActivity$$Lambda$12.lambdaFactory$(this, data, width, height));
    }

    /* synthetic */ void m900a(byte[] data, int width, int height, Integer value) throws Exception {
        if (data != null) {
            YuvImage image = new YuvImage(data, 17, width, height, null);
            Rect rect = new Rect(0, 0, width, height);
            File coverOutputFile = new File(this.f1544o);
            if (coverOutputFile.exists()) {
                coverOutputFile.delete();
            }
            try {
                coverOutputFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(coverOutputFile);
                image.compressToJpeg(rect, 75, fos);
                fos.flush();
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    private void m891m() {
        this.f1545p = true;
        Bundle args = new Bundle();
        args.putString("PARAM_KEY_UID", this.f1537h);
        args.putString("PARAM_KEY_VIDEO_PATH", this.f1543n);
        args.putString("PARAM_KEY_VIDEO_COVER_PATH", this.f1544o);
        args.putLong("PARAM_KEY_VIDEO_LENGTH", TimeUnit.MILLISECONDS.toSeconds(this.f1547r - this.f1546q));
        args.putBoolean("PARAM_KEY_VIDEO_IS_MPEG4", this.f1528C);
        launchActivityForResult(VideoRecordResultActivity.class, 8737, args);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 8737) {
            f1525d.m261d("onActivityResult:RC_CALL_VIDEO_RECORD_RESULT");
            this.f1548s = true;
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void m860a(boolean hide) {
        if (hide) {
            this.back.setVisibility(8);
            this.rotate.setVisibility(8);
            this.openMask.setVisibility(8);
            this.recordButton.setVisibility(8);
            this.maskTip.setVisibility(8);
            return;
        }
        this.back.setVisibility(0);
        this.rotate.setVisibility(0);
        this.openMask.setVisibility(0);
        this.recordButton.setVisibility(0);
        if (PreferenceUtil.getMaskTip() == 0) {
            this.maskTip.setVisibility(0);
        }
    }

    private void m865b(boolean hide) {
        if (hide) {
            this.back.setVisibility(8);
            this.rotate.setVisibility(8);
            this.openMask.setVisibility(8);
            this.maskTip.setVisibility(8);
            return;
        }
        this.back.setVisibility(0);
        this.rotate.setVisibility(0);
        this.openMask.setVisibility(0);
        if (PreferenceUtil.getMaskTip() == 0) {
            this.maskTip.setVisibility(0);
        }
    }

    private void m870c(boolean hide) {
        if (hide) {
            this.recordButton.setVisibility(8);
        } else {
            this.recordButton.setVisibility(0);
        }
    }
}
