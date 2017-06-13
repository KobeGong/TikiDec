package com.buddy.tiki.ui.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.base.BusinessDomain;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.drawable.CountDownBorderDrawable.SimpleCountDownListener;
import com.buddy.tiki.event.CallEvent.StopCountDownEvent;
import com.buddy.tiki.event.CallEvent.UpdateStatusEvent;
import com.buddy.tiki.helper.CustomMessageHelper;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.helper.DownloadHelper;
import com.buddy.tiki.helper.LocationHelper;
import com.buddy.tiki.helper.MediaHelper;
import com.buddy.tiki.helper.VersionHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.address.Address;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.MatchLimits;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.app.VersionInfo;
import com.buddy.tiki.model.constant.ChannelKeys;
import com.buddy.tiki.model.constant.RoomMessageType;
import com.buddy.tiki.model.event.Notice;
import com.buddy.tiki.model.exception.NetException;
import com.buddy.tiki.model.im.MatchResult;
import com.buddy.tiki.model.msg.MatchMessage;
import com.buddy.tiki.model.msg.RoomMessage;
import com.buddy.tiki.model.payment.SendGiftResult;
import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.protocol.im.FacechatIMEvents;
import com.buddy.tiki.service.base.ACache;
import com.buddy.tiki.service.base.Foreground;
import com.buddy.tiki.service.base.Foreground.Listener;
import com.buddy.tiki.ui.activity.base.ActivityManager;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.dialog.ConfirmDialog.Builder;
import com.buddy.tiki.ui.dialog.GiftDialog;
import com.buddy.tiki.ui.fragment.CallMainFragment;
import com.buddy.tiki.ui.fragment.CallMainFragment.OnCallEvents;
import com.buddy.tiki.ui.fragment.FriendFragment;
import com.buddy.tiki.ui.fragment.FriendFragment.FriendFragmentEvent;
import com.buddy.tiki.ui.fragment.YouFragment;
import com.buddy.tiki.ui.fragment.YouFragment.YouFragmentEvent;
import com.buddy.tiki.util.BitsUtil;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FUUtil;
import com.buddy.tiki.util.FUUtil.FaceDetectingEvent;
import com.buddy.tiki.util.FileUtil;
import com.buddy.tiki.util.MarketUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.util.VersionUtil;
import com.buddy.tiki.util.phonetype.HuaweiUtils;
import com.buddy.tiki.util.phonetype.MeizuUtils;
import com.buddy.tiki.view.BorderSurfaceView;
import com.buddy.tiki.view.BottomNavigationView;
import com.buddy.tiki.view.CountDownLayout;
import com.buddy.tiki.view.FaceDetectCover;
import com.buddy.tiki.view.GlMosaicDrawer;
import com.buddy.tiki.view.MatchFilterTextView;
import com.buddy.tiki.view.WebSocketConnectionHint;
import com.buddy.tiki.view.match.MatchLayout;
import com.buddy.tiki.view.match.MatchLayout.DragListener;
import com.buddy.tiki.view.match.SimpleMatchingView;
import com.buddy.tiki.wertc.BiuVideoCapturer2;
import com.buddy.tiki.wertc.PercentFrameLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.animated.base.AnimatedDrawable;
import com.facebook.imagepipeline.image.ImageInfo;
import com.igexin.download.Downloads;
import com.igexin.sdk.PushManager;
import com.jakewharton.rxbinding2.view.RxView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tapadoo.android.Alerter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;
import im.facechat.Rtc;
import im.facechat.Rtc.OnConstructCapturer;
import im.facechat.Rtc.OnConstructGlDrawer;
import im.facechat.common.protocol.FacechatCapturer;
import io.netty.handler.codec.rtsp.RtspHeaders.Values;
import io.netty.handler.traffic.AbstractTrafficShapingHandler;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.postproc;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.RendererCommon.GlDrawer;
import org.webrtc.RendererCommon.ScalingType;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Overlay.Style;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;
import tourguide.tourguide.TourGuide.Technique;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class CallActivity extends BaseActivity implements FacechatIMEvents, Listener, OnCallEvents, FriendFragmentEvent, YouFragmentEvent {
    private static final TikiLog f1249a = TikiLog.getInstance("CallActivity");
    private volatile boolean f1250A;
    private volatile boolean f1251B;
    private RtcReceiver f1252C;
    private int f1253D = -1;
    private boolean f1254E = true;
    private boolean f1255F = true;
    private TourGuide f1256G;
    private boolean f1257H = false;
    private boolean f1258I = false;
    private boolean f1259J = false;
    private boolean f1260K = false;
    private GlMosaicDrawer f1261L;
    private OnConstructGlDrawer f1262M = new C04221(this);
    private boolean f1263N = true;
    private long f1264O;
    private DisposableObserver<Long> f1265P;
    private Disposable f1266Q;
    private volatile boolean f1267R;
    private boolean f1268b;
    private boolean f1269d;
    private boolean f1270e;
    private boolean f1271f = true;
    private boolean f1272g = true;
    private MatchMessage f1273h;
    private DisposableObserver<Long> f1274i;
    private DisposableObserver<Long> f1275j;
    private int f1276k;
    private CallMainFragment f1277l;
    private FriendFragment f1278m;
    @BindView(2131820747)
    SeekBar mBlurRadiusSeekBar;
    @BindView(2131820746)
    TextView mBlurRadiusValue;
    @BindView(2131820752)
    BottomNavigationView mBottomNavigationView;
    @BindView(2131820731)
    FrameLayout mCallMainLayout;
    @BindView(2131820749)
    SeekBar mCheekThinningSeekBar;
    @BindView(2131820748)
    TextView mCheekThinningValue;
    @BindView(2131820745)
    SeekBar mColorLevelSeekBar;
    @BindView(2131820744)
    TextView mColorLevelValue;
    @BindView(2131820728)
    WebSocketConnectionHint mConnectionHint;
    @BindView(2131820732)
    View mDownGradientmask;
    @BindView(2131820751)
    SeekBar mEyeEnlargingSeekBar;
    @BindView(2131820750)
    TextView mEyeEnlargingValue;
    @BindView(2131820718)
    FaceDetectCover mFaceDetectCover;
    @BindView(2131820743)
    TextView mFaceUnityBeauty;
    @BindView(2131820730)
    View mFriendPanel;
    @BindView(2131820740)
    SimpleDraweeView mGiftShow;
    @BindView(2131820729)
    View mHorizontalPanel;
    @BindView(2131820736)
    CountDownLayout mLocalCountdownBorder;
    @BindView(2131820735)
    RelativeLayout mLocalPreviewLayout;
    @BindView(2131820738)
    BorderSurfaceView mLocalRender;
    @BindView(2131820727)
    MatchFilterTextView mMatchFilter;
    @BindView(2131820726)
    LinearLayout mMatchFilterLayout;
    @BindView(2131820717)
    MatchLayout mMatchLayout;
    @BindView(2131820719)
    View mMatchView1;
    @BindView(2131820721)
    View mMatchView2;
    @BindView(2131820723)
    View mMatchView3;
    @BindView(2131820734)
    View mMoveDetectView;
    @BindView(2131820742)
    TextView mPreviewResolution;
    @BindView(2131820733)
    View mProfilePanel;
    @BindView(2131820716)
    BorderSurfaceView mRemoteRender;
    @BindView(2131820715)
    PercentFrameLayout mRemoteRenderLayout;
    @BindView(2131820713)
    RelativeLayout mRootLayout;
    @BindView(2131820739)
    SimpleMatchingView mSimpleMatchingView;
    @BindView(2131820737)
    TextView mTMoneyIncrement;
    @BindView(2131820741)
    View mUnblockButton;
    @BindView(2131820725)
    View mUpGradientMask;
    private YouFragment f1279n;
    private AtomicBoolean f1280o;
    private LinkedList<Gift> f1281p;
    private VersionHelper f1282q;
    private volatile boolean f1283r;
    private int f1284s;
    private ConfigInfo f1285t;
    private User f1286u;
    private boolean f1287v;
    private String f1288w;
    private RxPermissions f1289x;
    private String f1290y;
    private String f1291z = "0";

    class C04221 implements OnConstructGlDrawer<GlDrawer> {
        final /* synthetic */ CallActivity f1231a;

        C04221(CallActivity this$0) {
            this.f1231a = this$0;
        }

        public GlDrawer newInstance() {
            if (this.f1231a.f1261L != null) {
                this.f1231a.f1261L.release();
            }
            this.f1231a.f1261L = new GlMosaicDrawer();
            this.f1231a.f1261L.setMosaicSize(0.0f, 0.0f);
            return this.f1231a.f1261L;
        }
    }

    class C04232 implements FaceDetectingEvent {
        final /* synthetic */ CallActivity f1233a;

        C04232(CallActivity this$0) {
            this.f1233a = this$0;
        }

        public void onFaceDetected(int numOfFaces) {
            CallActivity.f1249a.m261d("onFaceDetected:" + numOfFaces + " matched:" + this.f1233a.f1269d + " manualUncover:" + this.f1233a.f1260K);
            if (numOfFaces > 0) {
                this.f1233a.f1258I = true;
                if (this.f1233a.f1269d && !this.f1233a.f1260K) {
                    this.f1233a.m495R();
                    this.f1233a.runOnUiThread(CallActivity$2$$Lambda$1.lambdaFactory$(this.f1233a));
                }
            }
        }
    }

    class C04247 implements OnGlobalLayoutListener {
        final /* synthetic */ CallActivity f1235a;

        C04247(CallActivity this$0) {
            this.f1235a = this$0;
        }

        public void onGlobalLayout() {
            this.f1235a.mBottomNavigationView.getExploreButton().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            ToolTip toolTip = new ToolTip().setDescription(this.f1235a.getResources().getString(C0376R.string.explore_button_tips)).setGravity(48).setTextColor(ViewCompat.MEASURED_STATE_MASK).setBackgroundColor(ContextCompat.getColor(this.f1235a, C0376R.color.colorPrimary)).setShadow(true);
            this.f1235a.f1256G = TourGuide.init(this.f1235a).with(Technique.CLICK).setPointer(new Pointer(17, ContextCompat.getColor(this.f1235a, C0376R.color.colorPrimary))).setToolTip(toolTip).setOverlay(new Overlay(false, ContextCompat.getColor(this.f1235a, C0376R.color.black_alpha_normal), Style.CIRCLE).setOnClickListener(CallActivity$7$$Lambda$1.lambdaFactory$(this)).setHoleRadius(DisplayUtil.dip2px(36.0f)).setHoleOffsets(0, DisplayUtil.getNavigationBarHeight(this.f1235a))).playOn(this.f1235a.mBottomNavigationView.getExploreButton());
            this.f1235a.mBottomNavigationView.setTourGuide(this.f1235a.f1256G);
        }

        /* synthetic */ void m468a(View v) {
            this.f1235a.f1256G.cleanUp();
            PreferenceUtil.setExploreButtonTips();
        }
    }

    class C04258 extends DisposableObserver<Long> {
        final /* synthetic */ CallActivity f1244a;

        C04258(CallActivity this$0) {
            this.f1244a = this$0;
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
        }

        public void onNext(Long aLong) {
            CallActivity.f1249a.m263e("onNext connected");
            if (this.f1244a.f1273h != null && !TextUtils.isEmpty(this.f1244a.f1273h.getRoomId()) && this.f1244a.f1268b && this.f1244a.mLocalRender != null) {
                this.f1244a.mLocalRender.getRender().capture(CallActivity$8$$Lambda$1.lambdaFactory$(this, this.f1244a.f1273h.getRoomId()));
            }
        }

        /* synthetic */ void m474a(String roomId, Bitmap bmp) {
            Observable.defer(CallActivity$8$$Lambda$2.lambdaFactory$(bmp)).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io()).flatMap(CallActivity$8$$Lambda$3.lambdaFactory$(this)).flatMapCompletable(CallActivity$8$$Lambda$4.lambdaFactory$(this, roomId)).retry(2).subscribe(CallActivity$8$$Lambda$5.lambdaFactory$(), CallActivity$8$$Lambda$6.lambdaFactory$());
        }

        /* synthetic */ ObservableSource m473a(byte[] bytes) throws Exception {
            return this.f1244a.getDataLayer().getTikiResManager().uploadTempPic(bytes);
        }

        /* synthetic */ CompletableSource m472a(String roomId, String url) throws Exception {
            return this.f1244a.getDataLayer().getFeedbackManager().reportScreenshot(roomId, url);
        }

        static /* synthetic */ void m470a() throws Exception {
        }
    }

    class C04269 extends DisposableObserver<Long> {
        final /* synthetic */ CallActivity f1245a;

        C04269(CallActivity this$0) {
            this.f1245a = this$0;
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            CallActivity.f1249a.m264e("match check onError: ", e);
        }

        public void onNext(Long aLong) {
            if (this.f1245a.f1269d) {
                CallActivity.f1249a.m261d("mMatchCheckSubscriber:disconnect");
                this.f1245a.m489L();
            }
            this.f1245a.m586w();
        }
    }

    private class RtcReceiver extends BroadcastReceiver {
        final /* synthetic */ CallActivity f1248a;

        private RtcReceiver(CallActivity callActivity) {
            this.f1248a = callActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                Bundle args = intent.getBundleExtra("FACECHAT_VALUE");
                Object obj = -1;
                switch (action.hashCode()) {
                    case -1349257000:
                        if (action.equals("im.facechat.ACTION_FAIL")) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 1136054111:
                        if (action.equals("im.facechat.ACTION_TOKEN")) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                        if (args != null && !this.f1248a.isFinishing()) {
                            String token = args.getString("FACECHAT_TOKEN");
                            if (!TextUtils.isEmpty(token)) {
                                this.f1248a.getDataLayer().getUserManager().uploadRiverToken(token).subscribeOn(Schedulers.io()).subscribe(CallActivity$RtcReceiver$$Lambda$1.lambdaFactory$(), CallActivity$RtcReceiver$$Lambda$2.lambdaFactory$());
                                this.f1248a.m573n();
                                return;
                            }
                            return;
                        }
                        return;
                    case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                        if (args != null) {
                            int code = args.getInt("FACECHAT_ERROR_CODE");
                            String message = args.getString("FACECHAT_ERROR_MESSAGE");
                            CallActivity.f1249a.m263e("code " + code + "\nmessage " + message);
                            m475a(code, message);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }

        static /* synthetic */ void m476a(Boolean aBoolean) throws Exception {
        }

        static /* synthetic */ void m477a(Throwable throwable) throws Exception {
        }

        private void m475a(int code, String message) {
            switch (code) {
                case Downloads.STATUS_BAD_REQUEST /*400*/:
                    if ("\u65e0\u6cd5\u8fde\u63a5\u5230\u670d\u52a1\u5668".equalsIgnoreCase(message)) {
                        this.f1248a.mConnectionHint.setState(-1);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected int mo2115a() {
        return C0376R.layout.activity_call;
    }

    protected int mo2117b() {
        return C0376R.id.controller_fragment_container;
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f1267R = true;
        if (intent.getBooleanExtra("PARAM_KEY_RUSH_MODE", false)) {
            if (this.f1268b) {
                m490M();
            }
            m549f(false);
        }
        this.f1267R = false;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            m555h();
            m431d(this.mBottomNavigationView);
            DialogHelper.INSTANCE.resetIndex();
            m575o();
            m581r();
            m566l();
            m570m();
            m583t();
            m584u();
            m585v();
            m577p();
            m479B();
            m484G();
            m565k();
            EventBus.getDefault().register(this);
            m561j();
            m558i();
            if (!MeizuUtils.isMeizu() && !HuaweiUtils.isHuawei()) {
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                tintManager.setNavigationBarTintEnabled(true);
                tintManager.setNavigationBarTintResource(C0376R.color.black_alpha_normal);
            }
        }
    }

    private void m555h() {
        Bundle args = null;
        Intent intent = getIntent();
        if (intent != null) {
            args = intent.getBundleExtra("PARAM_KEY_FROM_NOTIFICATION");
        }
        if (args != null && !args.getBoolean("PARAM_KEY_RUSH_MODE")) {
        }
    }

    private void m558i() {
        this.mConnectionHint.setState(0);
        this.mConnectionHint.setVisibility(8);
        RxView.clicks(this.mConnectionHint).throttleFirst(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(CallActivity$$Lambda$1.lambdaFactory$(this));
    }

    /* synthetic */ void m625c(Object a) throws Exception {
        if (this.mConnectionHint.getState() == -1) {
            this.mConnectionHint.setState(1);
            Rtc.retryConnecting(getApplication());
        }
    }

    private void m561j() {
        this.mMatchFilter.enableClick(true);
        RxView.clicks(this.mMatchFilter).throttleFirst(1000, TimeUnit.MILLISECONDS).compose(bindToLifecycle()).subscribe(CallActivity$$Lambda$2.lambdaFactory$(this));
    }

    /* synthetic */ void m618b(Object aVoid) throws Exception {
        if (this.mMatchFilter.isClickable()) {
            m493P();
        }
    }

    private void m565k() {
        String none = Values.NONE;
        this.mPreviewResolution.setVisibility(8);
        FUUtil.getInstance().enableBeauty(true);
        FUUtil.getInstance().setColorLevel(1.0f);
        FUUtil.getInstance().setBlurLevel(5.0f);
        FUUtil.getInstance().setCheekThinning(0.5f);
        FUUtil.getInstance().setEyeEnlarging(1.0f);
        FUUtil.getInstance().setFaceDetectingEvent(new C04232(this));
        FUUtil.getInstance().resetFaceDetecting();
        this.mPreviewResolution.setVisibility(8);
        m518a(true);
        FUUtil.getInstance().clearFaceUnity();
    }

    private void m518a(boolean hide) {
        if (hide) {
            this.mFaceUnityBeauty.setVisibility(4);
            this.mColorLevelSeekBar.setVisibility(4);
            this.mColorLevelValue.setVisibility(4);
            this.mBlurRadiusSeekBar.setVisibility(4);
            this.mBlurRadiusValue.setVisibility(4);
            this.mCheekThinningSeekBar.setVisibility(4);
            this.mCheekThinningValue.setVisibility(4);
            this.mEyeEnlargingSeekBar.setVisibility(4);
            this.mEyeEnlargingValue.setVisibility(4);
            return;
        }
        this.mFaceUnityBeauty.setVisibility(0);
        this.mColorLevelSeekBar.setVisibility(0);
        this.mColorLevelValue.setVisibility(0);
        this.mBlurRadiusSeekBar.setVisibility(0);
        this.mBlurRadiusValue.setVisibility(0);
        this.mCheekThinningSeekBar.setVisibility(0);
        this.mCheekThinningValue.setVisibility(0);
        this.mEyeEnlargingSeekBar.setVisibility(0);
        this.mEyeEnlargingValue.setVisibility(0);
    }

    private void m566l() {
        if (!PreferenceUtil.getExploreButtonTips()) {
            this.mBottomNavigationView.getExploreButton().getViewTreeObserver().addOnGlobalLayoutListener(new C04247(this));
        }
    }

    private void m570m() {
        Rtc.initialize(getApplication());
        getDataLayer().getAppManager().getConfigCache().compose(SchedulersCompat.applyIoSchedulers()).doOnNext(CallActivity$$Lambda$3.lambdaFactory$(this)).subscribe(CallActivity$$Lambda$4.lambdaFactory$(this), CallActivity$$Lambda$5.lambdaFactory$());
    }

    /* synthetic */ void m616b(ConfigInfo configInfo) throws Exception {
        this.f1285t = configInfo;
    }

    /* synthetic */ void m599a(ConfigInfo configInfo) throws Exception {
        f1249a.m263e("getConfigInfo success");
        m510a(configInfo, true);
        m511a(configInfo.getVersion());
    }

    private synchronized void m510a(@NonNull ConfigInfo configInfo, boolean changeSwitch) {
        if (!this.f1251B) {
            MatchLimits matchLimits = configInfo.getMlimits();
            if (matchLimits == null || !matchLimits.isOpen()) {
                m512a(configInfo.getNotice(), CallActivity$$Lambda$6.lambdaFactory$(this));
            } else {
                if (this.f1277l != null && this.f1277l.isAdded()) {
                    this.f1277l.showBlockContent(matchLimits, 0);
                }
                this.mBottomNavigationView.setBanned(true);
                this.f1268b = false;
                this.f1280o.set(false);
                this.mUnblockButton.setVisibility(0);
                m485H();
                this.mUnblockButton.setOnClickListener(CallActivity$$Lambda$7.lambdaFactory$(this));
            }
        }
    }

    /* synthetic */ void m628d(DialogInterface dialog) {
        this.mBottomNavigationView.setBanned(false);
        this.mUnblockButton.setVisibility(8);
        if (this.f1277l != null && this.f1277l.isAdded()) {
            this.f1277l.showBlockContent(null, 8);
        }
    }

    /* synthetic */ void m624c(View v) {
        this.f1284s++;
        if (this.f1284s == 3) {
            this.f1284s = 0;
            this.mBottomNavigationView.setBanned(false);
            this.mUnblockButton.setVisibility(8);
            if (this.f1277l != null && this.f1277l.isAdded()) {
                this.f1277l.showBlockContent(null, 8);
            }
        }
    }

    private void m511a(VersionInfo versionInfo) {
        if (versionInfo == null || System.currentTimeMillis() - PreferenceUtil.getUpgradeShowTime() <= 3600000 || VersionUtil.compareVersion("1.2.11", versionInfo.getCurVersion()) >= 0) {
            return;
        }
        if (ChannelKeys.GOOGLE_MARKET.equalsIgnoreCase(BusinessDomain.f405c) || !versionInfo.getDownloadUrl().startsWith("http")) {
            DialogHelper.INSTANCE.showUpdateDialog(this, versionInfo, CallActivity$$Lambda$8.lambdaFactory$(this, versionInfo));
            return;
        }
        this.f1282q = new VersionHelper(this);
        this.f1282q.showDownloadDialog(versionInfo);
    }

    /* synthetic */ void m601a(VersionInfo versionInfo, View v) {
        MarketUtil.launchMarket(this, versionInfo.getDownloadUrl());
    }

    private void m512a(Notice notice, OnCancelListener listener) {
        if (notice == null) {
            listener.onCancel(null);
        } else {
            DialogHelper.INSTANCE.showActivityPage(this, notice, listener);
        }
    }

    private void m573n() {
        Rtc.setApnsToken(ChatApp.getInstance(), 3, PushManager.getInstance().getClientid(ChatApp.getInstance()), CallActivity$$Lambda$9.lambdaFactory$(this));
    }

    /* synthetic */ void m609a(boolean success, String reason) {
        if (!success) {
            Observable.timer(3000, TimeUnit.MILLISECONDS).observeOn(Schedulers.io()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(CallActivity$$Lambda$74.lambdaFactory$());
        }
    }

    private void m575o() {
        DownloadHelper.getInstance().downloadWebPResource();
    }

    private void m577p() {
        getDataLayer().getUserManager().userRequest(TopConfig.f408a).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(CallActivity$$Lambda$10.lambdaFactory$(this), CallActivity$$Lambda$11.lambdaFactory$());
        getDataLayer().getFollowManager().syncFriendsQuery(PreferenceUtil.getSyncTimepoint()).subscribeOn(Schedulers.io()).subscribe(CallActivity$$Lambda$12.lambdaFactory$(), CallActivity$$Lambda$13.lambdaFactory$());
    }

    /* synthetic */ void m634g(User user) throws Exception {
        this.f1286u = user;
        if (user == null || !user.isBlocked()) {
            this.f1251B = false;
            return;
        }
        this.f1251B = true;
        m579q();
        if (this.f1277l != null && this.f1277l.isAdded()) {
            this.f1277l.showBannedContent(user.getBlockText(), user.getBlockUrl());
        }
    }

    private synchronized void m579q() {
        if (!isFinishing()) {
            this.f1268b = false;
            this.f1280o.set(false);
            this.mUnblockButton.setVisibility(8);
            this.mBottomNavigationView.setBanned(true);
            if (this.f1277l != null && this.f1277l.isAdded()) {
                this.f1277l.showBlockContent(null, 8);
            }
            m485H();
        }
    }

    private void m581r() {
        this.f1276k = DisplayUtil.getRawWidth(this);
        this.f1289x = new RxPermissions(this);
        this.f1270e = true;
        this.f1280o = new AtomicBoolean(false);
        this.f1250A = false;
        this.f1288w = BuildConfig.VERSION_NAME;
        this.f1287v = false;
        this.f1283r = false;
        this.f1268b = false;
        this.f1269d = false;
        this.f1273h = null;
        this.f1281p = new LinkedList();
        this.mLocalRender.getRender().setZOrderMediaOverlay(false);
        this.mRemoteRender.getRender().setZOrderMediaOverlay(false);
        Foreground.get().addListener(this);
    }

    private boolean m582s() {
        return this.f1280o.get() || (this.f1273h != null && this.f1273h.isClockMode());
    }

    private void m583t() {
        this.f1277l = new CallMainFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add((int) C0376R.id.controller_fragment_container, this.f1277l);
        ft.commit();
    }

    private void m584u() {
        this.f1278m = new FriendFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add((int) C0376R.id.friend_fragment_container, this.f1278m);
        ft.commitAllowingStateLoss();
    }

    private void m585v() {
        this.f1279n = new YouFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add((int) C0376R.id.you_fragment_container, this.f1279n);
        ft.commitAllowingStateLoss();
    }

    private void m586w() {
        f1249a.m269w("try to request room: autoMatch=" + m582s() + " background=" + this.f1283r + " activityRunning=" + this.f1270e);
        if (m582s() && !this.f1283r && !this.f1267R) {
            if (this.f1250A) {
                m589z();
                Observable.timer(AbstractTrafficShapingHandler.DEFAULT_MAX_TIME, TimeUnit.MILLISECONDS).subscribe(this.f1274i);
                return;
            }
            Location location = LocationHelper.INSTANCE.getLocation();
            f1249a.m261d(location == null ? "null" : location.getLongitude() + " " + location.getLatitude());
            Address address = location == null ? null : new Address();
            if (address != null) {
                address.setLat(location.getLatitude());
                address.setLng(location.getLongitude());
            }
            m589z();
            getDataLayer().getChatManager().matchAction(address, this.f1288w).compose(SchedulersCompat.applyIoSchedulers()).retryWhen(CallActivity$$Lambda$14.lambdaFactory$(this)).compose(bindToLifecycle()).observeOn(AndroidSchedulers.mainThread()).flatMap(CallActivity$$Lambda$15.lambdaFactory$(this)).observeOn(AndroidSchedulers.mainThread()).subscribe(this.f1274i);
        }
    }

    /* synthetic */ ObservableSource m592a(Observable observable) throws Exception {
        return observable.flatMap(CallActivity$$Lambda$70.lambdaFactory$(this));
    }

    /* synthetic */ ObservableSource m635k(Throwable throwable) throws Exception {
        if (!(throwable instanceof NetException) || ((NetException) throwable).getCode() != -77) {
            return Observable.just(new Object()).delay(AbstractTrafficShapingHandler.DEFAULT_MAX_TIME, TimeUnit.MILLISECONDS);
        }
        this.f1250A = true;
        DialogHelper.INSTANCE.showLackInFilterDialog(this, CallActivity$$Lambda$71.lambdaFactory$(this), CallActivity$$Lambda$72.lambdaFactory$(this), CallActivity$$Lambda$73.lambdaFactory$(this));
        return Observable.error(throwable);
    }

    /* synthetic */ void m623c(DialogInterface dialog, int which) {
        m589z();
        Observable.timer(AbstractTrafficShapingHandler.DEFAULT_MAX_TIME, TimeUnit.MILLISECONDS).subscribe(this.f1274i);
        if (this.f1285t == null) {
            ToastUtil.getInstance().show((Context) this, (int) C0376R.string.fetch_data_failed);
        } else {
            WebBrowserActivity.launchWeb(this, this.f1285t.getH5DiamondsUrl());
        }
    }

    /* synthetic */ void m622c(DialogInterface dialog) {
        m587x();
    }

    /* synthetic */ void m614b(DialogInterface dialog, int which) {
        m587x();
    }

    /* synthetic */ ObservableSource m591a(MatchResult matchResult) throws Exception {
        if (matchResult == null) {
            return Observable.error(new RuntimeException("No matchResult"));
        }
        this.mMatchLayout.startMatch(matchResult.getPassport(), matchResult.getOnlines());
        return Observable.timer(AbstractTrafficShapingHandler.DEFAULT_MAX_TIME, TimeUnit.MILLISECONDS);
    }

    private void m587x() {
        this.f1250A = false;
        this.f1288w = BuildConfig.VERSION_NAME;
        this.f1290y = BuildConfig.VERSION_NAME;
        this.f1291z = "0";
        m589z();
        this.mMatchFilter.setText(C0376R.string.match_filter_default_text);
        this.mMatchFilter.setVisibility(8);
        Observable.timer(AbstractTrafficShapingHandler.DEFAULT_MAX_TIME, TimeUnit.MILLISECONDS).subscribe(this.f1274i);
    }

    private void m588y() {
        if (!(this.f1275j == null || this.f1275j.isDisposed())) {
            this.f1275j.dispose();
        }
        this.f1275j = new C04258(this);
    }

    private void m589z() {
        if (!(this.f1274i == null || this.f1274i.isDisposed())) {
            this.f1274i.dispose();
        }
        this.f1274i = new C04269(this);
    }

    private void m478A() {
        if (this.f1277l != null && this.f1277l.isAdded() && !isFinishing()) {
            User user;
            f1249a.m261d("updateControlFragment:mMatchedParameter:" + this.f1273h);
            CallMainFragment callMainFragment = this.f1277l;
            if (this.f1273h == null) {
                user = null;
            } else {
                user = this.f1273h.getMatchUser();
            }
            callMainFragment.updateStatusEvent(new UpdateStatusEvent(user, this.f1280o.get()));
        }
    }

    private void m479B() {
        this.f1252C = new RtcReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("im.facechat.ACTION_FAIL");
        filter.addAction("im.facechat.ACTION_TOKEN");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f1252C, filter);
        CustomMessageHelper.getInstance().initialize();
        CustomMessageHelper.getInstance().registerEvent(this);
        this.mBottomNavigationView.registerCallback(CallActivity$$Lambda$16.lambdaFactory$(this));
    }

    /* synthetic */ void m621c(int item) {
        switch (item) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                m497T();
                return;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                startMatch(true);
                return;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                m499V();
                return;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                m498U();
                return;
            case swscale.SWS_CS_FCC /*4*/:
                this.mMatchLayout.reset();
                this.f1280o.set(false);
                stopMatch();
                return;
            case swscale.SWS_CS_SMPTE170M /*5*/:
                this.mBottomNavigationView.setSendGiftTipVisibility(false);
                onShowGiftDialog();
                return;
            case postproc.PP_QUALITY_MAX /*6*/:
                m500W();
                return;
            default:
                return;
        }
    }

    public void stopMatch() {
        this.f1263N = true;
        getDataLayer().getChatManager().unMatchAction().subscribeOn(Schedulers.io()).subscribe(CallActivity$$Lambda$17.lambdaFactory$(), CallActivity$$Lambda$18.lambdaFactory$());
        this.f1280o.set(false);
        m490M();
        this.mMatchLayout.enableHorizontalDrag(true);
        this.mMatchLayout.enableVerticalDrag(false);
    }

    static /* synthetic */ void m541e(Boolean aBoolean1) throws Exception {
    }

    public void startMatch(boolean request) {
        this.f1280o.set(true);
        if (request) {
            m586w();
        }
        m485H();
        m478A();
        this.mMatchLayout.enableVerticalDrag(true);
        this.mMatchLayout.enableHorizontalDrag(false);
    }

    public void setStartMatchOnResume() {
        this.f1257H = true;
    }

    public boolean getMatchState() {
        return this.f1280o.get();
    }

    private void m480C() {
        this.f1271f = !this.f1271f;
        m485H();
    }

    private void m481D() {
        this.mLocalCountdownBorder.displayBorder();
    }

    private void m482E() {
        this.mLocalCountdownBorder.displayCountDown();
    }

    private void m483F() {
        this.mLocalCountdownBorder.stopCountDown();
    }

    private void m535d(int totalSecond) {
        this.mLocalCountdownBorder.startCountDown(totalSecond);
    }

    private void m539e(int increaseSecond) {
        this.mLocalCountdownBorder.increaseTime(increaseSecond);
    }

    private void m484G() {
        this.mRemoteRenderLayout.setPosition(0, 0, 100, 100);
        this.mRemoteRender.getRender().setScalingType(ScalingType.SCALE_ASPECT_FILL);
        this.mLocalRender.getRender().setScalingType(ScalingType.SCALE_ASPECT_FILL);
        this.mLocalRender.getRender().setMirror(this.f1272g);
        this.mLocalRender.getRender().setDrawRgb(false);
        this.mRemoteRender.getRender().setMirror(false);
        this.mRemoteRender.getRender().setDrawRgb(false);
        m547f(this.mRemoteRender);
        m552g(this.mLocalRender);
        this.mMatchLayout.init(this.mMatchView1, this.mMatchView2, this.mMatchView3, this.mHorizontalPanel, this.mMoveDetectView, this.mLocalPreviewLayout, this.mMatchFilterLayout, this.mUpGradientMask, this.mFaceDetectCover);
        this.mMatchLayout.enableHorizontalDrag(true);
        this.mMatchLayout.enableVerticalDrag(false);
        this.mMatchLayout.setDragListener(new DragListener(this) {
            final /* synthetic */ CallActivity f1224a;

            {
                this.f1224a = this$0;
            }

            public void onFlingDown() {
                CallActivity.f1249a.m261d("onFlingDown");
                this.f1224a.m489L();
                if (this.f1224a.f1277l != null && this.f1224a.f1277l.isAdded()) {
                    this.f1224a.f1277l.resetFriendButton();
                }
            }

            public void onFlingUp() {
                CallActivity.f1249a.m261d("onFlingUp");
            }

            public void onHorizontalPositionChanged(float position) {
                this.f1224a.mBottomNavigationView.translateView(position, Math.abs(position / ((float) DisplayUtil.getDisplayWidth())));
            }
        });
        RxView.clicks(this.mLocalCountdownBorder).throttleFirst(1000, TimeUnit.MILLISECONDS).compose(bindToLifecycle()).subscribe(CallActivity$$Lambda$19.lambdaFactory$(this));
        this.f1289x.request(new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}).subscribe(CallActivity$$Lambda$20.lambdaFactory$(this), CallActivity$$Lambda$21.lambdaFactory$());
        this.mSimpleMatchingView.setVisibility(8);
        m485H();
        this.mFaceDetectCover.setUncoverListener(CallActivity$$Lambda$22.lambdaFactory$(this));
        m501X();
    }

    /* synthetic */ void m608a(Object aVoid) throws Exception {
        m480C();
    }

    /* synthetic */ void m630d(Boolean granted) throws Exception {
        f1249a.m263e("granted " + granted);
        if (granted.booleanValue()) {
            m525b(true);
        } else {
            DialogHelper.INSTANCE.showPermissionMissDialog(this);
        }
    }

    /* synthetic */ void m615b(View v) {
        if (this.f1261L != null) {
            f1249a.m261d("mFaceDetectCover clicked");
            this.f1260K = true;
            m501X();
        }
    }

    private void m525b(boolean afterGranted) {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0) {
            Rtc.openCamera(this, this.mLocalRender.getRender(), this.mRemoteRender.getRender(), new OnConstructCapturer(this) {
                final /* synthetic */ CallActivity f1225a;

                {
                    this.f1225a = this$0;
                }

                public FacechatCapturer newInstance(String cameraName, CameraEventsHandler eventsHandler, boolean captureToTexture) {
                    FacechatCapturer result = BiuVideoCapturer2.create(cameraName, eventsHandler, captureToTexture);
                    if (result != null) {
                        ((BiuVideoCapturer2) result).setTextureCallback(this.f1225a.mLocalRender.getRender());
                    }
                    return result;
                }
            }, this.f1262M);
            if (afterGranted) {
                Rtc.onResume(this.mRemoteRender.getRender(), this.mLocalRender.getRender(), this.f1262M);
            }
        }
    }

    private void m547f(View view) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            this.mLocalCountdownBorder.addView(view, 0);
        }
    }

    private void m552g(View view) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            this.mRemoteRenderLayout.addView(view, 0);
        }
    }

    private void m532c(boolean visibile) {
        if (visibile) {
            this.mLocalCountdownBorder.setVisibility(0);
        } else {
            this.mLocalCountdownBorder.setVisibility(4);
        }
    }

    private void m485H() {
        if (!isFinishing()) {
            if (m582s() && this.f1268b) {
                this.mMatchFilter.setVisibility(8);
                m554g(true);
                this.mSimpleMatchingView.setVisibility(8);
                if (this.f1271f) {
                    if (this.f1253D != 3) {
                        m547f(this.mLocalRender);
                        m552g(this.mRemoteRender);
                        m532c(true);
                        this.mMatchLayout.reset();
                        this.f1253D = 3;
                        return;
                    }
                    return;
                } else if (this.f1253D != 4) {
                    m547f(this.mRemoteRender);
                    m552g(this.mLocalRender);
                    m532c(true);
                    this.mMatchLayout.reset();
                    this.f1253D = 4;
                    return;
                } else {
                    return;
                }
            }
            this.mMatchFilter.setVisibility(0);
            if (m582s()) {
                this.mMatchFilter.enableClick(false);
                m554g(false);
                if (TextUtils.isEmpty(this.f1291z) || "0".equals(this.f1291z)) {
                    this.mMatchFilter.setVisibility(8);
                } else {
                    this.mMatchFilter.setBackgroundColor(0);
                }
                if (this.f1271f) {
                    if (this.f1253D != 1) {
                        m547f(this.mLocalRender);
                        m552g(this.mRemoteRender);
                        m532c(true);
                        if (this.f1263N) {
                            this.mMatchLayout.initMatchWithAnime();
                            this.f1263N = false;
                        } else {
                            this.mMatchLayout.initMatch();
                        }
                        this.mSimpleMatchingView.setVisibility(8);
                        this.f1253D = 1;
                    }
                } else if (this.f1253D != 2) {
                    m552g(this.mLocalRender);
                    m547f(this.mRemoteRender);
                    m532c(true);
                    this.mMatchLayout.reset();
                    this.mSimpleMatchingView.setVisibility(0);
                    this.f1253D = 2;
                }
            } else if (this.f1253D != 0) {
                this.mMatchFilter.enableClick(true);
                this.mMatchFilter.setBackgroundResource(C0376R.drawable.bg_match_filter);
                m554g(true);
                m552g(this.mLocalRender);
                m547f(this.mRemoteRender);
                m532c(false);
                this.mMatchLayout.reset();
                this.mSimpleMatchingView.setVisibility(8);
                this.f1253D = 0;
            }
            m481D();
        }
    }

    private void m486I() {
        f1249a.m263e("callConnected");
        m478A();
        m485H();
        if (this.f1273h == null || ((this.f1273h.getMatchUser() == null || !BitsUtil.isFriend(this.f1273h.getMatchUser().getRelation())) && !this.f1273h.isClockMode())) {
            m482E();
            m535d(60);
            this.mLocalCountdownBorder.setCountDownListener(new SimpleCountDownListener(this) {
                final /* synthetic */ CallActivity f1226a;

                {
                    this.f1226a = this$0;
                }

                public void onTimeUp() {
                    this.f1226a.m489L();
                }

                public void onTimeLeft(int second) {
                    if (second == 20 && this.f1226a.f1277l != null && this.f1226a.f1277l.isAdded() && this.f1226a.mBottomNavigationView != null && this.f1226a.f1255F) {
                        this.f1226a.f1255F = false;
                        this.f1226a.mBottomNavigationView.setSendGiftTipVisibility(true);
                    }
                }
            });
        } else {
            m481D();
        }
        if (this.f1273h == null || !this.f1273h.isClockMode()) {
            if (!(this.f1265P == null || this.f1265P.isDisposed())) {
                this.f1265P.dispose();
            }
            if (this.f1266Q != null && !this.f1266Q.isDisposed()) {
                this.f1266Q.dispose();
                return;
            }
            return;
        }
        if (this.f1273h.isUber()) {
            m487J();
        } else if (!(this.f1266Q == null || this.f1266Q.isDisposed())) {
            this.f1266Q.dispose();
        }
        m488K();
    }

    private void m487J() {
        if (!(this.f1266Q == null || this.f1266Q.isDisposed())) {
            this.f1266Q.dispose();
        }
        this.f1266Q = Observable.interval(2, 2, TimeUnit.MINUTES).compose(bindUntilEvent(ActivityEvent.DESTROY)).observeOn(Schedulers.io()).flatMap(CallActivity$$Lambda$23.lambdaFactory$(this)).timeout(10, TimeUnit.SECONDS).filter(CallActivity$$Lambda$24.lambdaFactory$(this)).filter(CallActivity$$Lambda$25.lambdaFactory$()).observeOn(AndroidSchedulers.mainThread()).subscribe(CallActivity$$Lambda$26.lambdaFactory$(this), CallActivity$$Lambda$27.lambdaFactory$());
    }

    /* synthetic */ ObservableSource m593a(Long aLong) throws Exception {
        return getDataLayer().getUserManager().userRequest(TopConfig.f408a);
    }

    /* synthetic */ boolean m633f(User user) throws Exception {
        return this.f1273h != null && this.f1273h.isClockMode() && this.f1273h.isUber();
    }

    static /* synthetic */ boolean m544e(User user) throws Exception {
        return user != null && user.getDiamonds() <= -10;
    }

    /* synthetic */ void m629d(User user) throws Exception {
        m490M();
    }

    private void m488K() {
        if (!(this.f1265P == null || this.f1265P.isDisposed())) {
            this.f1265P.dispose();
        }
        this.f1265P = new DisposableObserver<Long>(this) {
            final /* synthetic */ CallActivity f1227a;

            {
                this.f1227a = this$0;
            }

            protected void onStart() {
                this.f1227a.mTMoneyIncrement.setVisibility(0);
                this.f1227a.mLocalCountdownBorder.setColors(ContextCompat.getColor(this.f1227a, C0376R.color.black), ContextCompat.getColor(this.f1227a, C0376R.color.colorPrimary), -1, -1);
                this.f1227a.mLocalCountdownBorder.displayCountDown();
            }

            public void onNext(Long aLong) {
                if (aLong.longValue() % 60 == 0 && this.f1227a.f1273h != null && this.f1227a.f1273h.isClockMode() && !this.f1227a.f1273h.isUber()) {
                    this.f1227a.f1264O = ((aLong.longValue() / 60) + 1) * this.f1227a.f1273h.getClockPrice();
                    this.f1227a.mTMoneyIncrement.setText(this.f1227a.getString(C0376R.string.increment_t_coin_format, new Object[]{Long.valueOf(this.f1227a.f1264O)}));
                }
                this.f1227a.mLocalCountdownBorder.setTime(String.format(Locale.getDefault(), "%02d:%02d", new Object[]{Long.valueOf(aLong.longValue() / 60), Long.valueOf(aLong.longValue() % 60)}));
            }

            public void onError(Throwable e) {
            }

            public void onComplete() {
            }
        };
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindUntilEvent(ActivityEvent.DESTROY)).doOnDispose(CallActivity$$Lambda$28.lambdaFactory$(this)).subscribe(this.f1265P);
    }

    /* synthetic */ void m632f() throws Exception {
        this.mTMoneyIncrement.setText(BuildConfig.VERSION_NAME);
        this.mTMoneyIncrement.setVisibility(4);
        this.mLocalCountdownBorder.setColors(-1, -1, -1, -1);
    }

    private void m489L() {
        runOnUiThread(CallActivity$$Lambda$29.lambdaFactory$(this));
    }

    private void m490M() {
        m492O();
        m491N();
    }

    private void m491N() {
        f1249a.m261d("updateLeaveView:" + this.f1273h);
        Alerter.clearCurrent(this);
        this.mTMoneyIncrement.setText(BuildConfig.VERSION_NAME);
        if (this.f1281p != null) {
            this.f1281p.clear();
        }
        if (this.mGiftShow != null) {
            this.mGiftShow.setVisibility(4);
        }
        MediaHelper.INSTANCE.stopMusic();
        m483F();
        m485H();
        m478A();
        this.f1258I = false;
        this.f1259J = false;
        this.f1260K = false;
        m501X();
    }

    private void m538d(boolean leave) {
        if (this.f1273h != null) {
            f1249a.m263e("matchmessaage " + this.f1273h.toString());
        }
        if (this.f1273h == null) {
            return;
        }
        if (!leave || this.f1273h.isConnected()) {
            if (leave && this.f1273h.isConnected()) {
                this.f1273h.setConnected(false);
            }
            if (!TextUtils.isEmpty(this.f1273h.getSession()) && this.f1273h.getMatchUser() != null) {
                getDataLayer().getChatManager().reportChatSession(this.f1273h.getSession(), this.f1273h.getMatchUser().getUid(), this.f1273h.getRoomId(), 2, leave, (int) this.f1264O).subscribeOn(Schedulers.io()).subscribe(CallActivity$$Lambda$30.lambdaFactory$(), CallActivity$$Lambda$31.lambdaFactory$());
                this.f1264O = 0;
            }
        }
    }

    static /* synthetic */ void m530c(Boolean aBoolean) throws Exception {
    }

    static /* synthetic */ void m553g(Throwable throwable) throws Exception {
    }

    private void m492O() {
        f1249a.m261d("leave room mMatched=" + this.f1269d);
        if (this.f1269d) {
            f1249a.m261d("leave room result is " + Rtc.leaveRoom(this.f1273h == null ? BuildConfig.VERSION_NAME : this.f1273h.getRoomId(), BuildConfig.VERSION_NAME));
        }
        m538d(true);
        this.f1273h = null;
        this.f1268b = false;
        this.f1271f = true;
        this.f1269d = false;
        if (!(this.f1275j == null || this.f1275j.isDisposed())) {
            this.f1275j.dispose();
            this.f1275j = null;
        }
        if (!(this.f1274i == null || this.f1274i.isDisposed())) {
            this.f1274i.dispose();
        }
        if (!(this.f1265P == null || this.f1265P.isDisposed())) {
            this.f1265P.dispose();
        }
        if (this.f1266Q != null && !this.f1266Q.isDisposed()) {
            this.f1266Q.dispose();
        }
    }

    private void m493P() {
        if (this.f1285t == null || TextUtils.isEmpty(this.f1285t.getMatchOptionsUrl())) {
            ToastUtil.getInstance().show((Context) this, (int) C0376R.string.fetch_data_failed);
        } else {
            WebBrowserActivity.launchWebForResult(this, this.f1285t.getMatchOptionsUrl(), 31);
        }
    }

    public void onMatch(MatchMessage matchMessage) {
        runOnUiThread(CallActivity$$Lambda$32.lambdaFactory$(this, matchMessage));
    }

    /* synthetic */ void m602a(MatchMessage matchMessage) {
        if (this.f1273h == null || !this.f1273h.isClockMode()) {
            boolean z;
            this.f1273h = matchMessage;
            this.f1258I = false;
            this.f1259J = false;
            if (matchMessage.isBlured()) {
                z = false;
            } else {
                z = true;
            }
            this.f1260K = z;
            FUUtil.getInstance().resetFaceDetecting();
            if (m582s()) {
                if (this.f1280o.compareAndSet(false, true)) {
                    this.mBottomNavigationView.translateView(0.0f, 0.0f);
                    startMatch(false);
                    this.mBottomNavigationView.startMatch(false);
                }
                User user = matchMessage.getMatchUser();
                String roomId = matchMessage.getRoomId();
                this.f1269d = true;
                this.mMatchLayout.matched(user);
                f1249a.m261d("[onMatchedInternal] join room result : " + Rtc.joinRoom(roomId, BuildConfig.VERSION_NAME));
                this.mMatchFilter.enableClick(false);
                m501X();
                return;
            }
            f1249a.m269w("not invalid connection");
        }
    }

    private void m494Q() {
        f1249a.m263e("onReceiveFaceDetection");
        this.f1259J = true;
        runOnUiThread(CallActivity$$Lambda$33.lambdaFactory$(this));
    }

    private void m495R() {
        f1249a.m261d("sendFaceDetection:r:" + this.f1259J + " l:" + this.f1258I);
        RoomMessage roomMessage = new RoomMessage();
        roomMessage.setType(RoomMessageType.FACE_DETECTION);
        roomMessage.setContent(Boolean.toString(true));
        f1249a.m263e("sendFaceDetection result:" + Rtc.sendRoomMessage(roomMessage.toJson()));
    }

    public void onApplyFriendRequest(String applyId, String applyUid) {
        runOnUiThread(CallActivity$$Lambda$34.lambdaFactory$(this, applyId, applyUid));
    }

    /* synthetic */ void m619b(String applyId, String applyUid) {
        if (this.f1277l.isAdded()) {
            this.f1277l.receiveFriendRequest(applyId, applyUid);
        }
    }

    public void onReceivePornMsg(boolean needReport) {
        this.f1287v = true;
    }

    public void onJoinRoom(String roomId, String payload) {
    }

    public void onLeaveRoom(String roomId, String payload) {
    }

    public void onRoomMessage(String roomId, String payload) {
        f1249a.m261d("onRoomMessage:roomId:" + roomId + " payload:" + payload);
        Observable.defer(CallActivity$$Lambda$35.lambdaFactory$(this, roomId, payload)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(CallActivity$$Lambda$36.lambdaFactory$(this), CallActivity$$Lambda$37.lambdaFactory$());
    }

    /* synthetic */ ObservableSource m594a(String roomId, String payload) throws Exception {
        f1249a.m261d("onRoomMessage:roomId:" + roomId + " payload:" + payload + " matchParameter:" + this.f1273h + " mp.roomId:" + (this.f1273h != null ? this.f1273h.getRoomId() : BuildConfig.VERSION_NAME));
        if (this.f1273h == null || !roomId.equalsIgnoreCase(this.f1273h.getRoomId())) {
            return Observable.empty();
        }
        try {
            JSONObject jsonObject = new JSONObject(payload);
            String type = jsonObject.optString("type");
            String content = jsonObject.optString("content");
            if (TextUtils.isEmpty(type)) {
                return Observable.error(new NullPointerException("room message type is empty "));
            }
            return Observable.just(new RoomMessage(type, content));
        } catch (JSONException e) {
            return Observable.error(e);
        }
    }

    /* synthetic */ void m603a(RoomMessage roomMessage) throws Exception {
        f1249a.m261d("onRoomMessage:type:" + roomMessage.getType() + " content:" + roomMessage.getContent());
        String type = roomMessage.getType();
        Object obj = -1;
        switch (type.hashCode()) {
            case 535305352:
                if (type.equals(RoomMessageType.FACE_DETECTION)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                if (!TextUtils.isEmpty(roomMessage.getContent()) && roomMessage.getContent().equalsIgnoreCase(Boolean.toString(true))) {
                    m494Q();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onRoomSession(String roomId, String session) {
        if (this.f1273h != null) {
            this.f1273h.setSession(session);
        }
    }

    public void onError(int code, String errorMsg) {
    }

    public void onBalanceMsg() {
        runOnUiThread(CallActivity$$Lambda$38.lambdaFactory$(this));
        getDataLayer().getUserManager().userRequest(TopConfig.f408a).compose(SchedulersCompat.applyIoSchedulers()).compose(bindUntilEvent(ActivityEvent.DESTROY)).timeout(10, TimeUnit.SECONDS).filter(CallActivity$$Lambda$39.lambdaFactory$(this)).filter(CallActivity$$Lambda$40.lambdaFactory$()).subscribe(CallActivity$$Lambda$41.lambdaFactory$(this), CallActivity$$Lambda$42.lambdaFactory$());
    }

    /* synthetic */ void m631e() {
        Alerter.create(this).setText(C0376R.string.diamond_not_enough).setBackgroundColor(C0376R.color.fail_text_background).setTextResColor(C0376R.color.fail_text_color).setTextSize(16.0f).enableInfiniteDuration(true).setIcon(C0376R.mipmap.icon_diamond_18).setOnClickListener(CallActivity$$Lambda$69.lambdaFactory$(this)).show();
    }

    /* synthetic */ void m598a(View v) {
        if (this.f1285t != null && !TextUtils.isEmpty(this.f1285t.getH5DiamondsUrl())) {
            Alerter.clearCurrent(this);
            WebBrowserActivity.launchWeb(this, this.f1285t.getH5DiamondsUrl());
        }
    }

    /* synthetic */ boolean m626c(User user) throws Exception {
        return this.f1273h != null && this.f1273h.isClockMode() && this.f1273h.isUber();
    }

    static /* synthetic */ boolean m526b(User user) throws Exception {
        return user != null && user.getDiamonds() <= -10;
    }

    /* synthetic */ void m606a(User user) throws Exception {
        m490M();
    }

    public void onStateChange(int state, @Nullable String roomId) {
        if (TextUtils.isEmpty(roomId) || this.f1273h == null || roomId.equalsIgnoreCase(this.f1273h.getRoomId())) {
            runOnUiThread(CallActivity$$Lambda$43.lambdaFactory$(this, state));
        }
    }

    /* synthetic */ void m612b(int state) {
        m546f(state);
    }

    private void m546f(int state) {
        switch (state) {
            case avutil.AV_SAMPLE_FMT_S64P /*11*/:
                f1249a.m261d("FacechatLifeState.CONNECTING");
                if (this.f1274i != null && !this.f1274i.isDisposed()) {
                    this.f1274i.dispose();
                    return;
                }
                return;
            case avutil.AV_SAMPLE_FMT_NB /*12*/:
                Alerter.clearCurrent(this);
                if (this.f1258I) {
                    m495R();
                }
                f1249a.m261d("FacechatLifeState.CONNECTED");
                this.f1255F = true;
                m538d(false);
                try {
                    this.f1273h.setConnected(true);
                } catch (NullPointerException e) {
                }
                if (!(this.f1275j == null || this.f1275j.isDisposed())) {
                    this.f1275j.dispose();
                }
                if (this.f1287v || !(this.f1286u == null || !this.f1286u.isNeedReport() || this.f1285t == null)) {
                    m588y();
                    Observable.interval((long) this.f1285t.getPornFirst(), (long) this.f1285t.getPornInterval(), TimeUnit.SECONDS).compose(bindToLifecycle()).subscribe(this.f1275j);
                }
                this.f1268b = true;
                m496S();
                m486I();
                return;
            case avutil.AV_PIX_FMT_YUVJ422P /*13*/:
                this.mBottomNavigationView.setSendGiftTipVisibility(false);
                m538d(true);
                if (!(this.f1275j == null || this.f1275j.isDisposed())) {
                    this.f1275j.dispose();
                }
                this.f1268b = false;
                m490M();
                if (!this.f1267R) {
                    m586w();
                    return;
                }
                return;
            case avutil.AV_PIX_FMT_YUVJ444P /*14*/:
                ToastUtil.getInstance().show(ChatApp.getInstance(), (int) C0376R.string.network_unstable, 1);
                return;
            default:
                return;
        }
    }

    private void m496S() {
        getDataLayer().getAppManager().getOperInfoCache().compose(bindToLifecycle()).subscribeOn(Schedulers.io()).filter(CallActivity$$Lambda$44.lambdaFactory$()).flatMap(CallActivity$$Lambda$45.lambdaFactory$(this)).observeOn(Schedulers.computation()).map(CallActivity$$Lambda$46.lambdaFactory$()).observeOn(Schedulers.io()).flatMap(CallActivity$$Lambda$47.lambdaFactory$()).observeOn(AndroidSchedulers.mainThread()).subscribe(CallActivity$$Lambda$48.lambdaFactory$(), CallActivity$$Lambda$49.lambdaFactory$());
    }

    static /* synthetic */ boolean m533c(OperInfo operInfo) throws Exception {
        return (operInfo == null || operInfo.isShowFaceunityDownload()) ? false : true;
    }

    /* synthetic */ ObservableSource m611b(OperInfo operInfo) throws Exception {
        return getDataLayer().getResourceManager().sysFaceunityRequest(1);
    }

    static /* synthetic */ FaceUnity m504a(List faceUnities) throws Exception {
        List<FaceUnity> list = new ArrayList();
        int total = 0;
        int currentRad = new Random().nextInt(10000);
        for (FaceUnity faceUnity : faceUnities) {
            if (faceUnity.isAutoLoading()) {
                total += faceUnity.getSalt();
                faceUnity.setSalt(total);
                list.add(faceUnity);
            }
        }
        Collections.sort(list, CallActivity$$Lambda$68.lambdaFactory$());
        for (FaceUnity faceUnity2 : list) {
            if (currentRad < faceUnity2.getSalt()) {
                return faceUnity2;
            }
        }
        return null;
    }

    static /* synthetic */ ObservableSource m507a(FaceUnity faceUnity) throws Exception {
        return faceUnity == null ? Observable.empty() : FileUtil.isAvatarInDiskAsync(faceUnity.getId());
    }

    static /* synthetic */ void m516a(String path) throws Exception {
        if (TextUtils.isEmpty(path)) {
            FUUtil.getInstance().clearFaceUnity();
        } else {
            FUUtil.getInstance().setFaceUnity(path);
        }
    }

    static /* synthetic */ void m537d(Throwable throwable) throws Exception {
    }

    public void onGiftReceived(Gift gift) {
        runOnUiThread(CallActivity$$Lambda$50.lambdaFactory$(this, gift));
    }

    /* synthetic */ void m604a(Gift gift) {
        m539e(gift.getDelayeds());
        this.f1281p.addLast(gift);
        m543e(false);
    }

    public void onCameraSwitch() {
    }

    public void onWebSocketState(int state) {
        if (this.f1270e) {
            runOnUiThread(CallActivity$$Lambda$51.lambdaFactory$(this, state));
        }
    }

    /* synthetic */ void m595a(int state) {
        switch (state) {
            case avutil.AV_SAMPLE_FMT_NONE /*-1*/:
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                this.mConnectionHint.setState(state);
                return;
            default:
                return;
        }
    }

    public void onReport() {
        this.mRemoteRender.getRender().capture(CallActivity$$Lambda$52.lambdaFactory$(this, this.f1273h.getMatchUser()));
    }

    /* synthetic */ void m607a(User user, Bitmap bmp) {
        runOnUiThread(CallActivity$$Lambda$67.lambdaFactory$(this, user, bmp));
    }

    /* synthetic */ void m617b(User user, Bitmap bmp) {
        DialogHelper.INSTANCE.showComplainDialog(this, user, bmp);
    }

    private void m497T() {
        this.mBottomNavigationView.animate().translationY((float) this.mBottomNavigationView.getMeasuredHeight()).setDuration(300).withEndAction(CallActivity$$Lambda$53.lambdaFactory$(this)).start();
    }

    /* synthetic */ void m627d() {
        DialogHelper.INSTANCE.showFaceuDialog(this, CallActivity$$Lambda$66.lambdaFactory$(this));
    }

    /* synthetic */ void m613b(DialogInterface dialog) {
        this.mBottomNavigationView.animate().translationY(0.0f).setDuration(300).setListener(null).start();
    }

    public void onShowGiftDialog() {
        this.mBottomNavigationView.animate().setDuration(300).translationY((float) this.mBottomNavigationView.getMeasuredHeight()).withEndAction(CallActivity$$Lambda$54.lambdaFactory$(this)).start();
    }

    /* synthetic */ void m620c() {
        Fragment dialog = new GiftDialog();
        dialog.setOnDismissListener(CallActivity$$Lambda$64.lambdaFactory$(this));
        dialog.setOnPresentListener(CallActivity$$Lambda$65.lambdaFactory$(this));
        getSupportFragmentManager().beginTransaction().add(dialog, GiftDialog.class.getName()).addToBackStack(null).commitAllowingStateLoss();
    }

    /* synthetic */ void m596a(DialogInterface dialog1) {
        this.mBottomNavigationView.animate().setDuration(300).translationY(0.0f).setListener(null).start();
    }

    private void m521b(@NonNull Gift gift) {
        if (this.f1273h == null || this.f1273h.getMatchUser() == null || !this.f1268b) {
            new Builder(this).setTitle((int) C0376R.string.send_gift_fail_title).setMessage((int) C0376R.string.send_gift_fail_message).setPositiveButton((int) C0376R.string.I_know, CallActivity$$Lambda$55.lambdaFactory$()).show(getSupportFragmentManager(), "GiftFailDialog");
            return;
        }
        getDataLayer().getFollowManager().sendGiftActionV2(this.f1273h.getMatchUser().getUid(), gift.getId()).compose(bindUntilEvent(ActivityEvent.DESTROY)).compose(SchedulersCompat.applyIoSchedulers()).filter(CallActivity$$Lambda$56.lambdaFactory$()).subscribe(CallActivity$$Lambda$57.lambdaFactory$(this, gift), CallActivity$$Lambda$58.lambdaFactory$());
    }

    static /* synthetic */ void m509a(DialogInterface dialog, int which) {
    }

    static /* synthetic */ boolean m519a(SendGiftResult giftResult) throws Exception {
        return giftResult != null;
    }

    /* synthetic */ void m605a(@NonNull Gift gift, SendGiftResult giftResult) throws Exception {
        if (giftResult.isResult()) {
            if (this.f1277l != null && this.f1277l.isAdded()) {
                this.f1277l.haveSendGift();
            }
            m539e(gift.getDelayeds());
            this.f1281p.addLast(gift);
            m543e(false);
        } else if (!TextUtils.isEmpty(giftResult.getTitle()) && !TextUtils.isEmpty(giftResult.getMsg())) {
            DialogHelper.INSTANCE.showFreeTimeOutDialog(this, giftResult.getTitle(), giftResult.getMsg());
        }
    }

    static /* synthetic */ void m531c(Throwable throwable) throws Exception {
    }

    private void m543e(boolean immediately) {
        if (this.f1268b && this.f1273h != null) {
            this.mGiftShow.setVisibility(0);
            if (this.f1281p.size() == 1 || immediately) {
                Gift gift = (Gift) this.f1281p.getFirst();
                if (!TextUtils.isEmpty(gift.getMusic())) {
                    String path = ACache.get((Context) this).getAsString(gift.getId());
                    if (path != null) {
                        MediaHelper.INSTANCE.playMusic(path, false);
                    } else {
                        MediaHelper.INSTANCE.playMusic(gift.getMusic(), false);
                    }
                }
                this.mGiftShow.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(gift.getSource()).setControllerListener(new BaseControllerListener<ImageInfo>(this) {
                    final /* synthetic */ CallActivity f1230a;

                    class C04211 implements AnimatorListener {
                        final /* synthetic */ AnonymousClass14 f1228a;

                        C04211(AnonymousClass14 this$1) {
                            this.f1228a = this$1;
                        }

                        public void onAnimationStart(Animator animation) {
                        }

                        public void onAnimationEnd(Animator animation) {
                            MediaHelper.INSTANCE.stopMusic();
                            if (this.f1228a.f1230a.f1281p.size() > 0) {
                                this.f1228a.f1230a.f1281p.removeFirst();
                            }
                            if (this.f1228a.f1230a.f1281p.size() > 0) {
                                this.f1228a.f1230a.m543e(true);
                            } else {
                                this.f1228a.f1230a.mGiftShow.setVisibility(4);
                            }
                        }

                        public void onAnimationCancel(Animator animation) {
                        }

                        public void onAnimationRepeat(Animator animation) {
                        }
                    }

                    {
                        this.f1230a = this$0;
                    }

                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        if (animatable != null && (animatable instanceof AnimatedDrawable)) {
                            ValueAnimator animator = ((AnimatedDrawable) animatable).createValueAnimator();
                            animator.setRepeatCount(0);
                            animator.start();
                            animator.addListener(new C04211(this));
                        }
                    }
                })).build());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stopCoundDownEvent(StopCountDownEvent event) {
        if (event != null && event.getUids() != null && this.f1273h != null && this.f1273h.getMatchUser() != null) {
            for (String user : event.getUids()) {
                if (!TextUtils.isEmpty(user) && user.equals(this.f1273h.getMatchUser().getUid())) {
                    m483F();
                    m481D();
                }
            }
        }
    }

    public void onFriendBackToCall() {
        m500W();
    }

    public void onProfileBackToCall() {
        m500W();
    }

    protected void onResume() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            Rtc.onResume(this.mRemoteRender.getRender(), this.mLocalRender.getRender(), this.f1262M);
        }
        super.onResume();
        this.f1250A = DialogHelper.INSTANCE.isBlockDialogShowing();
        this.f1270e = true;
        if (this.f1257H) {
            startMatch(true);
            this.f1257H = false;
        }
    }

    protected void onPause() {
        f1249a.m261d("onPause");
        this.f1270e = false;
        super.onPause();
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            Rtc.onPause();
        }
    }

    public void onBackPressed() {
        if (!this.mBottomNavigationView.onBackCall()) {
            super.onBackPressed();
        }
    }

    protected void onDestroy() {
        if (this.f1252C != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f1252C);
        }
        CustomMessageHelper.getInstance().unregisterEvent(this);
        CustomMessageHelper.getInstance().release();
        Rtc.shutdown();
        EventBus.getDefault().unregister(this);
        getDataLayer().getChatManager().unMatchAction().subscribeOn(Schedulers.io()).subscribe(CallActivity$$Lambda$59.lambdaFactory$(), CallActivity$$Lambda$60.lambdaFactory$());
        getDataLayer().getFollowManager().clearHistory();
        Foreground.get().removeListener(this);
        m489L();
        if (this.mLocalRender != null) {
            this.mLocalRender.getRender().release();
            this.mLocalRender = null;
        }
        if (this.mRemoteRender != null) {
            this.mRemoteRender.getRender().release();
            this.mRemoteRender = null;
        }
        if (this.f1282q != null) {
            this.f1282q.destroy();
        }
        MediaHelper.INSTANCE.release();
        super.onDestroy();
    }

    static /* synthetic */ void m522b(Boolean aBoolean) throws Exception {
    }

    static /* synthetic */ void m524b(Throwable throwable) throws Exception {
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case avutil.AV_PIX_FMT_GRAY16BE /*31*/:
                    if (data != null) {
                        this.f1288w = data.getStringExtra("PARAM_KEY_MATCH_OPTIONS_RESULT");
                        this.f1290y = data.getStringExtra("PARAM_KEY_MATCH_OPTIONS_DISPLAY");
                        this.f1291z = data.getStringExtra("PARAM_KEY_MATCH_OPTIONS_COST");
                        f1249a.m263e("data is : " + this.f1291z + " " + this.f1288w + " " + this.f1290y);
                        if (TextUtils.isEmpty(this.f1290y)) {
                            this.mMatchFilter.setText(C0376R.string.match_filter_default_text);
                        } else {
                            this.mMatchFilter.setText(this.f1290y);
                        }
                        this.f1270e = true;
                        this.mBottomNavigationView.startMatch();
                        break;
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void m498U() {
        f1249a.m261d("showProfile");
        if (this.f1279n == null) {
            m585v();
        }
        this.mMatchLayout.showRightPanel();
    }

    private void m499V() {
        f1249a.m261d("showFriend");
        if (this.f1278m == null) {
            m584u();
        } else {
            this.f1278m.onRestartPage();
        }
        this.mMatchLayout.showLeftPanel();
    }

    private void m549f(boolean smoothScroll) {
        if (this.f1278m == null) {
            m584u();
        }
        if (this.f1279n == null) {
            m585v();
        }
        if (smoothScroll) {
            this.mMatchLayout.hidePanelWithSmoothSlide();
        } else {
            this.mMatchLayout.hidePanel();
        }
    }

    private void m500W() {
        m549f(true);
        this.mMatchLayout.hidePanelWithSmoothSlide();
    }

    private void m554g(boolean visible) {
        if (visible) {
            this.mUpGradientMask.setVisibility(0);
            this.mDownGradientmask.setVisibility(0);
            return;
        }
        this.mUpGradientMask.setVisibility(8);
        this.mDownGradientmask.setVisibility(8);
    }

    private void m501X() {
        getDataLayer().getAppManager().getOperInfoCache().compose(bindToLifecycle()).observeOn(AndroidSchedulers.mainThread()).subscribe(CallActivity$$Lambda$61.lambdaFactory$(this));
    }

    /* synthetic */ void m600a(OperInfo operInfo) throws Exception {
        Object obj;
        boolean localFaceShow = this.f1258I;
        boolean remoteFaceShow = this.f1259J;
        boolean manualUncover = this.f1260K;
        TikiLog tikiLog = f1249a;
        StringBuilder append = new StringBuilder().append("updateMosaicCover:matched:").append(this.f1269d).append(" lfs:").append(localFaceShow).append(" rfs:").append(remoteFaceShow).append(" manualUncover:").append(manualUncover).append(" isFaceDectOff:");
        if (operInfo == null) {
            obj = "null";
        } else {
            obj = Boolean.valueOf(operInfo.isFaceDectOff());
        }
        tikiLog.m261d(append.append(obj).toString());
        if (operInfo != null) {
            if (manualUncover || operInfo.isFaceDectOff()) {
                manualUncover = true;
            } else {
                manualUncover = false;
            }
        }
        if (!this.f1269d) {
            this.mFaceDetectCover.setVisibility(8);
        } else if (manualUncover) {
            this.f1261L.setMosaicSize(0.0f, 0.0f);
            this.mFaceDetectCover.setVisibility(8);
        } else {
            this.mFaceDetectCover.setVisibility(0);
            int local = 0;
            if (localFaceShow) {
                local = 1;
            }
            int remote = 0;
            if (remoteFaceShow) {
                remote = 2;
            }
            f1249a.m261d("checkFaceDetection:" + (local | remote));
            this.mFaceDetectCover.setState(local | remote);
            if (localFaceShow && remoteFaceShow) {
                if (this.f1261L != null) {
                    this.f1261L.setMosaicSize(0.0f, 0.0f);
                }
            } else if (this.f1261L != null) {
                this.f1261L.setMosaicSize(0.0234375f, 0.041666668f);
            }
        }
    }

    public void onBecameForeground() {
        if (!(ActivityManager.getInstance().currentActivity() instanceof CallFriendActivity)) {
            f1249a.m261d("onBecameForeground: " + this.f1283r);
            if (this.f1283r) {
                this.f1283r = false;
                if (!this.f1268b && !this.f1269d && this.f1280o.get()) {
                    m586w();
                }
            }
        }
    }

    public void onBecameBackground() {
        if (!(ActivityManager.getInstance().currentActivity() instanceof CallFriendActivity)) {
            f1249a.m261d("onBecameBackground: " + this.f1283r);
            if (!this.f1283r) {
                this.f1283r = true;
                if (this.f1280o.get()) {
                    getDataLayer().getChatManager().unMatchAction().subscribeOn(Schedulers.io()).subscribe(CallActivity$$Lambda$62.lambdaFactory$(), CallActivity$$Lambda$63.lambdaFactory$());
                }
                if (this.f1274i != null && !this.f1274i.isDisposed()) {
                    this.f1274i.dispose();
                }
            }
        }
    }

    static /* synthetic */ void m515a(Boolean aBoolean1) throws Exception {
    }

    public boolean isVipUser() {
        return this.f1273h != null && this.f1273h.isClockMode();
    }
}
