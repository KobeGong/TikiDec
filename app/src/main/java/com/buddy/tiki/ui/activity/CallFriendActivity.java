package com.buddy.tiki.ui.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.base.Constants;
import com.buddy.tiki.event.FriendCallEvent.AcceptEvent;
import com.buddy.tiki.event.FriendCallEvent.CloseEvent;
import com.buddy.tiki.event.FriendCallEvent.RejectEvent;
import com.buddy.tiki.helper.CustomMessageHelper;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.helper.MediaHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.im.CallReceiveMessage;
import com.buddy.tiki.model.msg.MatchMessage;
import com.buddy.tiki.model.payment.SendGiftResult;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.protocol.im.FacechatIMEvents;
import com.buddy.tiki.service.base.ACache;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.service.base.Foreground;
import com.buddy.tiki.service.base.Foreground.Listener;
import com.buddy.tiki.ui.activity.base.ActivityManager;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.dialog.ConfirmDialog.Builder;
import com.buddy.tiki.ui.dialog.GiftDialog;
import com.buddy.tiki.ui.fragment.CallMainFragment;
import com.buddy.tiki.ui.fragment.CallMainFragment.OnCallEvents;
import com.buddy.tiki.util.DateUtil;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.util.UserChatRealmHelper;
import com.buddy.tiki.view.BorderSurfaceView;
import com.buddy.tiki.view.BottomNavigationView;
import com.buddy.tiki.view.DotTailTextView;
import com.buddy.tiki.view.TimerTextView;
import com.buddy.tiki.view.match.MatchingView;
import com.buddy.tiki.wertc.PercentFrameLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.animated.base.AnimatedDrawable;
import com.facebook.imagepipeline.image.ImageInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.tbruyelle.rxpermissions2.RxPermissions;
import im.facechat.Rtc;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.util.LinkedList;
import java.util.UUID;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.parceler.Parcels;
import org.webrtc.RendererCommon.ScalingType;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class CallFriendActivity extends BaseActivity implements FacechatIMEvents, Listener, OnCallEvents {
    private static final TikiLog f1351d = TikiLog.getInstance(CallFriendActivity.class.getSimpleName());
    private static final String[] f1352e = new String[]{"android.permission.MODIFY_AUDIO_SETTINGS", "android.permission.RECORD_AUDIO", "android.permission.INTERNET", "android.permission.CAMERA"};
    private LinkedList<Gift> f1353A = new LinkedList();
    private boolean f1354B = false;
    private int f1355C;
    private CountDownTouchListener f1356D = new CountDownTouchListener();
    private int f1357E = 1;
    private Boolean f1358F = null;
    private CallReceiveMessage f1359G;
    private String f1360H;
    private String f1361I;
    private String f1362J;
    private boolean f1363K = false;
    private boolean f1364L = false;
    private boolean f1365M = true;
    private int f1366N = 3;
    private boolean f1367O = false;
    private String f1368P;
    private int f1369Q = 0;
    private MatchMessage f1370R;
    private int f1371S = 1;
    private boolean f1372T = false;
    private boolean f1373U = true;
    private Runnable f1374V = new C04271(this);
    DotTailTextView f1375a;
    TimerTextView f1376b;
    private final int f1377f = 1;
    private final int f1378g = 2;
    private final int f1379h = 3;
    private final float f1380i = 5000.0f;
    private final Object f1381j = new Object();
    private boolean f1382k;
    private boolean f1383l = true;
    private boolean f1384m;
    @BindView(2131820752)
    BottomNavigationView mBottomNavigationView;
    @BindView(2131820731)
    FrameLayout mCallMainLayout;
    @BindString(2131296397)
    String mFriendVideoCallTime;
    @BindView(2131820740)
    SimpleDraweeView mGiftShow;
    @BindView(2131820754)
    RelativeLayout mLocalCountdownBorder;
    @BindView(2131820738)
    BorderSurfaceView mLocalRender;
    @BindView(2131820755)
    AppCompatTextView mNick;
    @BindView(2131820742)
    TextView mPreviewResolution;
    @BindView(2131820716)
    BorderSurfaceView mRemoteRender;
    @BindView(2131820753)
    PercentFrameLayout mRemoteRenderLayout;
    @BindView(2131820713)
    RelativeLayout mRootLayout;
    @BindView(2131820756)
    AppCompatTextView mTime;
    @BindString(2131296396)
    String mWithouAnswer;
    private boolean f1385n = true;
    private boolean f1386o = true;
    private Handler f1387p = new Handler();
    private int f1388q;
    private long f1389r = 200;
    private int f1390s;
    private int f1391t;
    private int f1392u;
    private int f1393v;
    private int f1394w;
    private int f1395x;
    private CallMainFragment f1396y;
    private boolean f1397z = true;

    class C04271 implements Runnable {
        final /* synthetic */ CallFriendActivity f1342a;

        C04271(CallFriendActivity this$0) {
            this.f1342a = this$0;
        }

        public void run() {
            if (this.f1342a.f1365M) {
                this.f1342a.f1375a.stop();
                this.f1342a.f1376b.stop();
                this.f1342a.f1387p.postDelayed(CallFriendActivity$1$$Lambda$1.lambdaFactory$(this), 1500);
            }
        }

        /* synthetic */ void m636a() {
            this.f1342a.mTime.setText(C0376R.string.friend_call_timeout);
            MediaHelper.INSTANCE.stopMusic();
            this.f1342a.m684o();
        }
    }

    class C04282 implements OnGlobalLayoutListener {
        final /* synthetic */ CallFriendActivity f1343a;

        C04282(CallFriendActivity this$0) {
            this.f1343a = this$0;
        }

        public void onGlobalLayout() {
            this.f1343a.f1396y.initCallFriend();
        }
    }

    class C04303 extends BaseControllerListener<ImageInfo> {
        final /* synthetic */ CallFriendActivity f1345a;

        class C04291 implements AnimatorListener {
            final /* synthetic */ C04303 f1344a;

            C04291(C04303 this$1) {
                this.f1344a = this$1;
            }

            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                MediaHelper.INSTANCE.stopMusic();
                if (this.f1344a.f1345a.f1353A.size() > 0) {
                    this.f1344a.f1345a.f1353A.removeFirst();
                }
                if (this.f1344a.f1345a.f1353A.size() > 0) {
                    this.f1344a.f1345a.m661c(true);
                } else {
                    this.f1344a.f1345a.mGiftShow.setVisibility(4);
                }
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        }

        C04303(CallFriendActivity this$0) {
            this.f1345a = this$0;
        }

        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            if (animatable != null && (animatable instanceof AnimatedDrawable)) {
                ValueAnimator animator = ((AnimatedDrawable) animatable).createValueAnimator();
                animator.setRepeatCount(0);
                animator.start();
                animator.addListener(new C04291(this));
            }
        }
    }

    private class CountDownTouchListener implements OnTouchListener {
        final /* synthetic */ CallFriendActivity f1346a;
        private MotionEvent f1347b;
        private MotionEvent f1348c;
        private long f1349d;
        private boolean f1350e;

        private CountDownTouchListener(CallFriendActivity callFriendActivity) {
            this.f1346a = callFriendActivity;
            this.f1350e = false;
        }

        public boolean onTouch(View v, MotionEvent event) {
            CallFriendActivity.f1351d.m261d("onTouch: " + event);
            long now;
            double distance;
            switch (event.getActionMasked()) {
                case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                    this.f1347b = MotionEvent.obtain(event);
                    this.f1349d = System.currentTimeMillis();
                    break;
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    if (this.f1347b != null) {
                        now = System.currentTimeMillis();
                        distance = Math.hypot((double) (event.getRawX() - this.f1347b.getRawX()), (double) (event.getRawY() - this.f1347b.getRawY()));
                        if (now - this.f1349d < this.f1346a.f1389r && distance < ((double) this.f1346a.f1355C)) {
                            if (this.f1346a.f1385n && this.f1346a.mLocalCountdownBorder.getChildAt(0) == this.f1346a.mLocalRender) {
                                this.f1346a.m685p();
                            } else if (!this.f1346a.f1385n && this.f1346a.mLocalCountdownBorder.getChildAt(0) == this.f1346a.mRemoteRender) {
                                this.f1346a.m685p();
                            }
                        }
                    }
                    this.f1350e = false;
                    this.f1348c = null;
                    this.f1347b = null;
                    break;
                case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                    if (!(this.f1350e || this.f1347b == null)) {
                        now = System.currentTimeMillis();
                        distance = Math.hypot((double) (event.getRawX() - this.f1347b.getRawX()), (double) (event.getRawY() - this.f1347b.getRawY()));
                        if (now - this.f1349d >= this.f1346a.f1389r && distance >= ((double) this.f1346a.f1355C)) {
                            this.f1350e = true;
                        }
                    }
                    if (this.f1350e && this.f1348c != null) {
                        float tx = v.getTranslationX() + (event.getRawX() - this.f1348c.getRawX());
                        float ty = v.getTranslationY() + (event.getRawY() - this.f1348c.getRawY());
                        if (tx < 0.0f) {
                            tx = 0.0f;
                        }
                        if (tx > ((float) (DisplayUtil.getDisplayWidth() - v.getMeasuredWidth()))) {
                            tx = (float) (DisplayUtil.getDisplayWidth() - v.getMeasuredWidth());
                        }
                        if (ty < 0.0f) {
                            ty = 0.0f;
                        }
                        if (ty > ((float) (this.f1346a.f1392u - v.getMeasuredHeight()))) {
                            ty = (float) (this.f1346a.f1392u - v.getMeasuredHeight());
                        }
                        v.setTranslationX(tx);
                        v.setTranslationY(ty);
                        break;
                    }
            }
            this.f1348c = MotionEvent.obtain(event);
            return true;
        }
    }

    protected int mo2115a() {
        return C0376R.layout.activity_call_friend;
    }

    protected int mo2117b() {
        return C0376R.id.controller_fragment_container;
    }

    protected void onActivityCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            m674j();
            m671h();
            m678l();
            m680m();
            m686q();
            m676k();
            this.f1366N = 3;
        } else {
            m687r();
        }
        EventBus.getDefault().register(this);
    }

    private void m671h() {
        f1351d.m261d("initRtcClient:");
        if (this.f1363K) {
            Rtc.initialize(getApplication());
            CustomMessageHelper.getInstance().initialize();
        }
        new RxPermissions(this).request(new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}).subscribe(CallFriendActivity$$Lambda$1.lambdaFactory$(this));
        CustomMessageHelper.getInstance().registerEvent(this);
        if (!this.f1358F.booleanValue()) {
            f1351d.m261d("initRtcClient:1");
            f1351d.m261d("join room:" + Rtc.joinRoom(this.f1368P, BuildConfig.VERSION_NAME));
        }
    }

    /* synthetic */ void m706d(Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue()) {
            m672i();
        } else {
            DialogHelper.INSTANCE.showPermissionMissDialog(this);
        }
    }

    private void m672i() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0) {
            Rtc.openCamera(this, this.mLocalRender.getRender(), this.mRemoteRender.getRender(), CallFriendActivity$$Lambda$2.lambdaFactory$(), null);
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.mRootLayout.setSystemUiVisibility(5894);
        }
    }

    private void m674j() {
        this.f1354B = false;
        this.f1376b = new TimerTextView(this.mTime);
        this.f1375a = new DotTailTextView(this.mTime, false);
        if (getArguments() != null && getArguments().containsKey("PARAM_KEY_IS_CALLER")) {
            this.f1358F = Boolean.valueOf(getArguments().getBoolean("PARAM_KEY_IS_CALLER"));
            if (this.f1358F.booleanValue()) {
                f1351d.m261d("initvalue:sender");
                this.f1361I = getArguments().getString("PARAM_KEY_UID");
                this.f1362J = getArguments().getString("PARAM_KEY_NICK");
                if (!TextUtils.isEmpty(this.f1362J)) {
                    this.mNick.setText(this.f1362J);
                }
                this.mTime.setText(C0376R.string.waiting_friend_accept);
                this.f1387p.postDelayed(this.f1374V, 60000);
            } else {
                f1351d.m261d("initvalue:receiver1");
                this.f1359G = (CallReceiveMessage) Parcels.unwrap(getArguments().getParcelable("PARAM_KEY_CALL_RECEIVE_MSG"));
                if (!(this.f1359G == null || this.f1359G.getFriend() == null || TextUtils.isEmpty(this.f1359G.getFriend().getNick()))) {
                    f1351d.m261d("initvalue:receiver");
                    this.mNick.setText(this.f1359G.getFriend().getNick());
                    this.f1371S = this.f1359G.getQuality();
                    this.f1368P = this.f1359G.getRoomId();
                    this.f1370R = new MatchMessage();
                    this.f1370R.setMatchUser(this.f1359G.getFriend());
                    this.f1370R.setRoomId(this.f1368P);
                }
                this.mTime.setText(C0376R.string.friend_connection_setting_up);
            }
            this.f1375a.start();
            this.f1364L = getArguments().getBoolean("PARAM_KEY_IS_MATCH", false);
            this.f1363K = getArguments().getBoolean("PARAM_KEY_FROM_APNS", false);
        }
        this.f1355C = ViewConfiguration.get(this).getScaledTouchSlop();
        this.f1388q = DisplayUtil.getStatusBarHeight();
        this.f1390s = MatchingView.getIndicatorHeight(this, DisplayUtil.getDisplayWidth(), DisplayUtil.getDisplayHeight());
        this.f1391t = (this.f1390s * 4) / 5;
        this.f1392u = DisplayUtil.getDisplayHeight() + (this.f1388q * 2);
        this.f1393v = DisplayUtil.getDisplayWidth();
        this.f1394w = ViewConfiguration.get(this).getScaledMinimumFlingVelocity();
        this.f1395x = ViewConfiguration.get(this).getScaledMaximumFlingVelocity();
        this.f1382k = false;
        Foreground.get().addListener(this);
    }

    private void m676k() {
        if (!this.f1363K) {
            this.f1367O = false;
            if (this.f1358F == null) {
                return;
            }
            if (this.f1358F.booleanValue()) {
                if (!TextUtils.isEmpty(this.f1361I)) {
                    DataLayer.getInstance().getChatManager().requestCallAction(this.f1361I).subscribeOn(Schedulers.io()).subscribe(CallFriendActivity$$Lambda$3.lambdaFactory$(this), CallFriendActivity$$Lambda$4.lambdaFactory$(), CallFriendActivity$$Lambda$5.lambdaFactory$());
                }
            } else if (this.f1359G != null && !TextUtils.isEmpty(this.f1359G.getRoomId())) {
            }
        }
    }

    /* synthetic */ void m697a(String s) throws Exception {
        this.f1360H = s;
        MediaHelper.INSTANCE.playMusic(ChatApp.getInstance(), Constants.f406a, true);
    }

    static /* synthetic */ void m665e(Throwable throwable) throws Exception {
    }

    static /* synthetic */ void m667f() throws Exception {
    }

    private void m678l() {
        this.f1396y = new CallMainFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add((int) C0376R.id.controller_fragment_container, this.f1396y);
        ft.commit();
        this.mRootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new C04282(this));
    }

    private void m680m() {
        this.mLocalCountdownBorder.setOnTouchListener(this.f1356D);
        this.mBottomNavigationView.registerCallback(CallFriendActivity$$Lambda$6.lambdaFactory$(this));
    }

    /* synthetic */ void m701b(int item) {
        switch (item) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                showFaceDialog();
                return;
            case swscale.SWS_CS_SMPTE170M /*5*/:
                onShowGiftDialog();
                return;
            case swscale.SWS_CS_SMPTE240M /*7*/:
                m682n();
                return;
            default:
                return;
        }
    }

    private void m682n() {
        new Builder(this).setTitle((int) C0376R.string.friend_call_quit_title).setMessage((int) C0376R.string.friend_call_quit_message).setPositiveButton((int) C0376R.string.ok, CallFriendActivity$$Lambda$7.lambdaFactory$(this)).setNegativeButton((int) C0376R.string.cancel, CallFriendActivity$$Lambda$8.lambdaFactory$()).show(getSupportFragmentManager(), "CloseVideoDialog");
    }

    /* synthetic */ void m704c(DialogInterface dialog, int which) {
        m684o();
    }

    static /* synthetic */ void m649b(DialogInterface dialog1, int which1) {
    }

    private synchronized void m684o() {
        m656b(true);
        long callTime = this.f1376b == null ? 0 : this.f1376b.getElapseTimeInMills();
        if (this.f1369Q == 2) {
            f1351d.m261d("onClose:state:CLOSED");
        } else if (this.f1369Q != 0) {
            f1351d.m261d("onClose:unknown state: " + this.f1369Q);
        } else {
            f1351d.m261d("onClose:state:init");
            if (!TextUtils.isEmpty(this.f1360H)) {
                getDataLayer().getChatManager().closeCallAction(this.f1360H).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(CallFriendActivity$$Lambda$9.lambdaFactory$(), CallFriendActivity$$Lambda$10.lambdaFactory$());
            } else if (!(this.f1359G == null || TextUtils.isEmpty(this.f1359G.getCallId()))) {
                getDataLayer().getChatManager().closeCallAction(this.f1359G.getCallId()).subscribeOn(Schedulers.io()).subscribe(CallFriendActivity$$Lambda$11.lambdaFactory$(), CallFriendActivity$$Lambda$12.lambdaFactory$());
            }
            String id;
            long now;
            Realm outRealm;
            Transaction lambdaFactory$;
            if (!this.f1358F.booleanValue() || TextUtils.isEmpty(this.f1361I) || this.f1354B) {
                id = "local_" + UUID.randomUUID().toString();
                now = System.currentTimeMillis();
                if (!(this.f1358F.booleanValue() || this.f1359G == null || this.f1359G.getFriend() == null)) {
                    String uid = this.f1359G.getFriend().getUid();
                    User fromUser = new User();
                    fromUser.setUid(uid);
                    outRealm = Realm.getDefaultInstance();
                    lambdaFactory$ = CallFriendActivity$$Lambda$16.lambdaFactory$(this, uid, callTime, id, now, fromUser);
                    outRealm.getClass();
                    outRealm.executeTransactionAsync(lambdaFactory$, CallFriendActivity$$Lambda$17.lambdaFactory$(outRealm), CallFriendActivity$$Lambda$18.lambdaFactory$(outRealm));
                }
            } else {
                id = "local_" + UUID.randomUUID().toString();
                now = System.currentTimeMillis();
                outRealm = Realm.getDefaultInstance();
                lambdaFactory$ = CallFriendActivity$$Lambda$13.lambdaFactory$(this, callTime, id, now);
                outRealm.getClass();
                outRealm.executeTransactionAsync(lambdaFactory$, CallFriendActivity$$Lambda$14.lambdaFactory$(outRealm), CallFriendActivity$$Lambda$15.lambdaFactory$(outRealm));
            }
            m688s();
            MediaHelper.INSTANCE.stopMusic();
            if (this.mLocalRender != null) {
                this.mLocalRender.getRender().release();
                this.mLocalRender = null;
            }
            if (this.mRemoteRender != null) {
                this.mRemoteRender.getRender().release();
                this.mRemoteRender = null;
            }
            MediaHelper.INSTANCE.release();
            if (!this.f1373U && ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
                Rtc.switchCamera(null);
            }
            setResult(-1);
            finish();
            this.f1369Q = 2;
            if (this.f1364L && (ActivityManager.getInstance().getTopSecondActivity() instanceof CallActivity)) {
                ((CallActivity) ActivityManager.getInstance().getTopSecondActivity()).setStartMatchOnResume();
            }
        }
    }

    static /* synthetic */ void m659c(Boolean aBoolean) throws Exception {
    }

    static /* synthetic */ void m663d(Throwable throwable) throws Exception {
    }

    static /* synthetic */ void m654b(Boolean aBoolean) throws Exception {
    }

    static /* synthetic */ void m660c(Throwable throwable) throws Exception {
    }

    /* synthetic */ void m692a(long callTime, String id, long now, Realm realm) {
        TikiUser user = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, this.f1361I).findFirst();
        if (user != null && user.isLoaded() && user.isValid()) {
            User fromUser = new User();
            fromUser.setUid(this.f1361I);
            if (callTime == 0) {
                UserChatRealmHelper.getInstance().insertOfflineMessage(realm, this.f1361I, id, now, fromUser);
                return;
            }
            long s = (callTime - (60000 * (callTime / 60000))) / 1000;
            String msgText = DateUtil.getChatTimespan(callTime);
            String lastMsgText = String.format(this.mFriendVideoCallTime, new Object[]{Long.valueOf(m), Long.valueOf(s)});
            UserChatRealmHelper.getInstance().insertSendTimeMessage(realm, id, msgText, lastMsgText, now, fromUser);
            f1351d.m261d("insertSendTimeMessage:ms:" + callTime + " msgText:" + msgText + " lastMsgText:" + lastMsgText);
        }
    }

    /* synthetic */ void m698a(String uid, long callTime, String id, long now, User fromUser, Realm realm) {
        TikiUser user = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, uid).findFirst();
        if (user != null && user.isLoaded() && user.isValid()) {
            long s = (callTime - (60000 * (callTime / 60000))) / 1000;
            String msgText = DateUtil.getChatTimespan(callTime);
            String lastMsgText = String.format(this.mFriendVideoCallTime, new Object[]{Long.valueOf(m), Long.valueOf(s)});
            if (callTime > 0) {
                UserChatRealmHelper.getInstance().insertReceiveTimeMessage(realm, id, msgText, lastMsgText, now, fromUser);
                f1351d.m261d("insertReceiveTimeMessage:ms" + callTime + " msgText:" + msgText + " lastMsgText:" + lastMsgText);
                return;
            }
            UserChatRealmHelper.getInstance().insertCallOfflineMessage(realm, uid, id, now, fromUser);
        }
    }

    private void m685p() {
        this.f1385n = !this.f1385n;
        m687r();
    }

    private void m686q() {
        this.mPreviewResolution.setVisibility(8);
        this.mRemoteRenderLayout.setPosition(0, 0, 100, 100);
        this.mRemoteRender.getRender().setScalingType(ScalingType.SCALE_ASPECT_FILL);
        float x = ((float) (DisplayUtil.getDisplayWidth() * 72)) / 100.0f;
        float y = ((float) (DisplayUtil.getDisplayHeight() * 12)) / 100.0f;
        float height = ((float) (DisplayUtil.getDisplayHeight() * 25)) / 100.0f;
        LayoutParams layoutParams = (LayoutParams) this.mLocalCountdownBorder.getLayoutParams();
        layoutParams.width = (int) (((float) (DisplayUtil.getDisplayWidth() * 25)) / 100.0f);
        layoutParams.height = (int) height;
        this.mLocalCountdownBorder.setLayoutParams(layoutParams);
        this.mLocalCountdownBorder.setTranslationX(x);
        this.mLocalCountdownBorder.setTranslationY(y);
        this.mLocalCountdownBorder.setVisibility(8);
        this.mLocalRender.getRender().setScalingType(ScalingType.SCALE_ASPECT_FILL);
        this.mLocalRender.getRender().setMirror(this.f1386o);
        this.mRemoteRender.getRender().setMirror(false);
        m638a(this.mRemoteRender);
        m650b(this.mLocalRender);
        this.mLocalRender.requestLayout();
        this.mRemoteRender.requestLayout();
    }

    private void m638a(View view) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            this.mLocalCountdownBorder.addView(view, 0);
            view.requestLayout();
        }
    }

    private void m650b(View view) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            this.mRemoteRenderLayout.addView(view, 0);
            view.requestLayout();
        }
    }

    private void m645a(boolean visibile) {
        if (visibile) {
            this.mLocalCountdownBorder.setVisibility(0);
        } else {
            this.mLocalCountdownBorder.setVisibility(4);
        }
    }

    private void m687r() {
        f1351d.m261d("ICE:" + this.f1382k + " local:" + this.f1385n);
        try {
            if (this.f1382k) {
                this.mLocalCountdownBorder.setVisibility(0);
                if (this.f1385n) {
                    m638a(this.mLocalRender);
                    m650b(this.mRemoteRender);
                    m645a(true);
                } else {
                    m638a(this.mRemoteRender);
                    m650b(this.mLocalRender);
                    m645a(true);
                }
            } else {
                this.mLocalCountdownBorder.setVisibility(8);
                m650b(this.mLocalRender);
                m638a(this.mRemoteRender);
                m645a(false);
            }
            this.mLocalRender.getRender().setMirror(this.f1386o);
            this.mRemoteRender.getRender().setMirror(false);
            this.mLocalRender.requestLayout();
            this.mRemoteRender.requestLayout();
        } catch (Exception e) {
        }
    }

    private void m688s() {
        runOnUiThread(CallFriendActivity$$Lambda$19.lambdaFactory$(this));
    }

    private void m689t() {
        if (this.f1353A != null) {
            this.f1353A.clear();
        }
        if (this.mGiftShow != null) {
            this.mGiftShow.setVisibility(4);
        }
        this.f1370R = null;
        this.f1382k = false;
        this.f1385n = false;
        boolean result = Rtc.leaveRoom(this.f1368P, BuildConfig.VERSION_NAME);
        m687r();
    }

    private void m656b(boolean leave) {
        if (this.f1370R == null) {
            return;
        }
        if (!leave || this.f1370R.isConnected()) {
            if (leave && this.f1370R.isConnected()) {
                this.f1370R.setConnected(false);
            }
            if (!TextUtils.isEmpty(this.f1370R.getSession()) && this.f1370R.getMatchUser() != null) {
                getDataLayer().getChatManager().reportChatSession(this.f1370R.getSession(), this.f1370R.getMatchUser().getUid(), this.f1370R.getRoomId(), 1, leave, 0).subscribeOn(Schedulers.io()).subscribe(CallFriendActivity$$Lambda$22.lambdaFactory$(), CallFriendActivity$$Lambda$23.lambdaFactory$());
            }
        }
    }

    static /* synthetic */ void m643a(Boolean aBoolean) throws Exception {
    }

    static /* synthetic */ void m655b(Throwable throwable) throws Exception {
    }

    public void onMatch(MatchMessage matchMessage) {
    }

    public void onJoinRoom(String roomId, String payload) {
        f1351d.m261d("onJoinRoom");
    }

    public void onLeaveRoom(String roomId, String payload) {
        runOnUiThread(CallFriendActivity$$Lambda$24.lambdaFactory$(this));
    }

    /* synthetic */ void m707e() {
        this.f1375a.stop();
        this.f1376b.stop();
        this.mTime.setText(C0376R.string.friend_close_call);
        this.f1372T = true;
        this.f1387p.postDelayed(CallFriendActivity$$Lambda$39.lambdaFactory$(this), 2000);
    }

    public void onRoomMessage(String roomId, String payload) {
        f1351d.m261d("onRoomMessage");
    }

    public void onRoomSession(String roomId, String session) {
        f1351d.m261d("onRoomSession");
        if (this.f1370R != null) {
            this.f1370R.setSession(session);
        }
    }

    public void onError(int errorCode, String errorMessage) {
        f1351d.m261d("onError:code:" + errorCode + " msg:" + errorMessage);
    }

    public void onBalanceMsg() {
    }

    public void onStateChange(int state, @Nullable String roomId) {
        if (TextUtils.isEmpty(roomId) || this.f1370R == null || roomId.equalsIgnoreCase(this.f1370R.getRoomId())) {
            runOnUiThread(CallFriendActivity$$Lambda$25.lambdaFactory$(this, state));
        }
    }

    /* synthetic */ void m691a(int state) {
        m657c(state);
    }

    private void m657c(int state) {
        switch (state) {
            case avutil.AV_SAMPLE_FMT_NB /*12*/:
                f1351d.m261d("onStateChangeInternal:CONNECTED");
                m656b(false);
                this.f1370R.setConnected(true);
                this.f1382k = true;
                this.f1385n = true;
                m687r();
                this.f1375a.stop();
                this.f1376b.start();
                MediaHelper.INSTANCE.stopMusic();
                return;
            case avutil.AV_PIX_FMT_YUVJ422P /*13*/:
                m656b(true);
                if (!this.f1372T) {
                    this.f1382k = false;
                    m687r();
                    m684o();
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

    public void onWebSocketState(int state) {
    }

    public void onGiftReceived(Gift gift) {
        runOnUiThread(CallFriendActivity$$Lambda$26.lambdaFactory$(this, gift));
    }

    /* synthetic */ void m695a(Gift gift) {
        this.f1353A.addLast(gift);
        m661c(false);
    }

    public void onApplyFriendRequest(String applyId, String applyUid) {
    }

    public void onReceivePornMsg(boolean needReport) {
    }

    public void onCameraSwitch() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            Rtc.switchCamera(CallFriendActivity$$Lambda$27.lambdaFactory$(this));
        }
    }

    /* synthetic */ void m699a(boolean b, String s) {
        if (b) {
            this.f1373U = !this.f1373U;
            if (this.mLocalRender != null && this.mLocalRender.getRender() != null) {
                this.mLocalRender.getRender().toggleMirror();
                return;
            }
            return;
        }
        f1351d.m261d("onCameraSwitchError:");
        ToastUtil.getInstance().show((int) C0376R.string.switch_camera_error);
    }

    public void onReport() {
    }

    public void showFaceDialog() {
        this.mBottomNavigationView.animate().translationY((float) this.mBottomNavigationView.getMeasuredHeight()).setDuration(300).withEndAction(CallFriendActivity$$Lambda$28.lambdaFactory$(this)).start();
    }

    /* synthetic */ void m705d() {
        DialogHelper.INSTANCE.showFaceuDialog(this, CallFriendActivity$$Lambda$38.lambdaFactory$(this));
    }

    /* synthetic */ void m702b(DialogInterface dialog) {
        this.mBottomNavigationView.animate().translationY(0.0f).setDuration(300).setListener(null).start();
    }

    public void onShowGiftDialog() {
        this.mBottomNavigationView.animate().setDuration(300).translationY((float) this.mBottomNavigationView.getMeasuredHeight()).withEndAction(CallFriendActivity$$Lambda$29.lambdaFactory$(this)).start();
    }

    /* synthetic */ void m703c() {
        Fragment dialog = new GiftDialog();
        dialog.setOnDismissListener(CallFriendActivity$$Lambda$36.lambdaFactory$(this));
        dialog.setOnPresentListener(CallFriendActivity$$Lambda$37.lambdaFactory$(this));
        getSupportFragmentManager().beginTransaction().add(dialog, GiftDialog.class.getName()).addToBackStack(null).commitAllowingStateLoss();
    }

    /* synthetic */ void m693a(DialogInterface dialog1) {
        this.mBottomNavigationView.animate().setDuration(300).translationY(0.0f).setListener(null).start();
    }

    private void m651b(@NonNull Gift gift) {
        if (this.f1370R == null || this.f1370R.getMatchUser() == null || !this.f1382k) {
            new Builder(this).setTitle((int) C0376R.string.send_gift_fail_title).setMessage((int) C0376R.string.send_gift_fail_message).setPositiveButton((int) C0376R.string.I_know, CallFriendActivity$$Lambda$30.lambdaFactory$()).show(getSupportFragmentManager(), "GiftFailDialog");
            return;
        }
        getDataLayer().getFollowManager().sendGiftActionV2(this.f1370R.getMatchUser().getUid(), gift.getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(CallFriendActivity$$Lambda$31.lambdaFactory$()).subscribe(CallFriendActivity$$Lambda$32.lambdaFactory$(this, gift), CallFriendActivity$$Lambda$33.lambdaFactory$());
    }

    static /* synthetic */ void m637a(DialogInterface dialog, int which) {
    }

    static /* synthetic */ boolean m646a(SendGiftResult giftResult) throws Exception {
        return giftResult != null;
    }

    /* synthetic */ void m696a(@NonNull Gift gift, SendGiftResult giftResult) throws Exception {
        if (giftResult.isResult()) {
            this.f1353A.addLast(gift);
            m661c(false);
            return;
        }
        DialogHelper.INSTANCE.showFreeTimeOutDialog(this, giftResult.getTitle(), giftResult.getMsg());
    }

    static /* synthetic */ void m644a(Throwable throwable) throws Exception {
    }

    private void m661c(boolean immediately) {
        if (this.f1382k && this.f1370R != null) {
            this.mGiftShow.setVisibility(0);
            if (this.f1353A.size() == 1 || immediately) {
                Gift gift = (Gift) this.f1353A.getFirst();
                if (!TextUtils.isEmpty(gift.getMusic())) {
                    String path = ACache.get((Context) this).getAsString(gift.getId());
                    if (path != null) {
                        MediaHelper.INSTANCE.playMusic(path, false);
                    } else {
                        MediaHelper.INSTANCE.playMusic(gift.getMusic(), false);
                    }
                }
                this.mGiftShow.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(gift.getSource()).setControllerListener(new C04303(this))).build());
            }
        }
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onResume() {
        f1351d.m261d("onResume");
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            Rtc.onResume(this.mRemoteRender.getRender(), this.mLocalRender.getRender(), null);
        }
        super.onResume();
        this.f1384m = true;
    }

    protected void onPause() {
        f1351d.m261d("onPause");
        super.onPause();
        this.f1384m = false;
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            Rtc.onPause();
        }
    }

    protected void onDestroy() {
        f1351d.m261d("onDestroy");
        if (this.f1369Q != 2) {
            m684o();
        }
        EventBus.getDefault().unregister(this);
        CustomMessageHelper.getInstance().unregisterEvent(this);
        super.onDestroy();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return false;
        }
        m682n();
        return true;
    }

    public void onBecameForeground() {
    }

    public void onBecameBackground() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void acceptCallEvent(AcceptEvent event) {
        f1351d.m261d("acceptCallEvent:roomId" + event.f497a + " callId:" + event.f498b + " storedCallId:" + this.f1360H);
        if (this.f1358F.booleanValue() && !TextUtils.isEmpty(event.f498b) && event.f498b.equals(this.f1360H)) {
            this.f1370R = event.f499c;
            MediaHelper.INSTANCE.stopMusic();
            this.f1368P = event.f497a;
            this.f1365M = false;
            this.f1387p.removeCallbacks(this.f1374V);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void rejectCallEvent(RejectEvent event) {
        f1351d.m261d("rejectCallEvent:" + event.f501a);
        if (this.f1358F.booleanValue() && !TextUtils.isEmpty(event.f501a) && event.f501a.equals(this.f1360H)) {
            MediaHelper.INSTANCE.stopMusic();
            this.f1365M = false;
            this.f1354B = true;
            this.f1387p.removeCallbacks(this.f1374V);
            this.f1375a.stop();
            this.f1376b.stop();
            this.mTime.setText(C0376R.string.friend_reject_call);
            this.f1387p.postDelayed(CallFriendActivity$$Lambda$34.lambdaFactory$(this), 2000);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void closeCallEvent(CloseEvent event) {
        f1351d.m261d("closeCallEvent:" + event.f500a + " mCallId:" + this.f1360H);
        if (!TextUtils.isEmpty(event.f500a) && event.f500a.equals(this.f1360H)) {
            this.f1375a.stop();
            this.f1376b.stop();
            this.mTime.setText(C0376R.string.friend_close_call);
            this.f1387p.postDelayed(CallFriendActivity$$Lambda$35.lambdaFactory$(this), 2000);
        }
    }
}
