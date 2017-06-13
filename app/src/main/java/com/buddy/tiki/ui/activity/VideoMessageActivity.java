package com.buddy.tiki.ui.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.media.AudioManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.exception.NetException;
import com.buddy.tiki.model.im.VideoMessage;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.model.user.UserChatMessage;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.util.BlurProcessor;
import com.buddy.tiki.util.DateUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.view.video.IjkVideoView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.animated.base.AnimatedDrawable;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jakewharton.rxbinding2.view.RxView;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.swresample;
import org.parceler.Parcels;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class VideoMessageActivity extends BaseActivity {
    private static final TikiLog f1489a = TikiLog.getInstance("VideoMessageActivity");
    private Animatable f1490b;
    private ValueAnimator f1491d;
    private long f1492e;
    private VideoMessage f1493f;
    @BindString(2131296606)
    String formatString;
    private Uri f1494g = new Builder().scheme("res").path(String.valueOf(C0376R.raw.finger_print)).build();
    private int f1495h = 0;
    private boolean f1496i = false;
    @BindView(2131820778)
    ImageView mClose;
    @BindView(2131820773)
    RelativeLayout mCoverLayout;
    @BindView(2131820780)
    SimpleDraweeView mFingerPrint;
    @BindView(2131820775)
    TextView mNickView;
    @BindView(2131820779)
    TextView mPurchaseHint;
    @BindView(2131820781)
    AppCompatTextView mReply;
    @BindView(2131820777)
    ImageView mReport;
    @BindView(2131820776)
    TextView mTime;
    @BindView(2131820774)
    SimpleDraweeView mVideoCover;
    @BindView(2131820772)
    IjkVideoView mVideoMessageView;

    class C04401 extends BaseControllerListener<ImageInfo> {
        final /* synthetic */ VideoMessageActivity f1482a;

        C04401(VideoMessageActivity this$0) {
            this.f1482a = this$0;
        }

        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            this.f1482a.f1490b = animatable;
        }
    }

    class C04412 implements OnTouchListener {
        long f1483a;
        long f1484b;
        final /* synthetic */ VideoMessageActivity f1485c;

        C04412(VideoMessageActivity this$0) {
            this.f1485c = this$0;
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getActionMasked()) {
                case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                    this.f1483a = System.currentTimeMillis();
                    this.f1485c.m837j();
                    break;
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    this.f1484b = System.currentTimeMillis();
                    this.f1485c.m838k();
                    break;
                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                    this.f1485c.m838k();
                    break;
            }
            return true;
        }
    }

    class C04423 implements AnimatorListener {
        long f1486a;
        long f1487b;
        final /* synthetic */ VideoMessageActivity f1488c;

        C04423(VideoMessageActivity this$0) {
            this.f1488c = this$0;
        }

        public void onAnimationStart(Animator animation) {
            this.f1486a = System.currentTimeMillis();
            VideoMessageActivity.f1489a.m261d("finger print anime start:" + this.f1486a);
        }

        public void onAnimationEnd(Animator animation) {
            this.f1487b = System.currentTimeMillis();
            VideoMessageActivity.f1489a.m261d("finger print anime end:" + this.f1487b);
            if (this.f1487b - this.f1486a >= this.f1488c.f1491d.getDuration()) {
                this.f1488c.m839l();
            } else {
                this.f1488c.m838k();
            }
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    private static void m821a(BaseActivity baseActivity, VideoMessage videoMessage, long timestamp, boolean isSendMsg) {
        if (baseActivity != null && videoMessage != null) {
            Bundle args = new Bundle();
            args.putParcelable("PARAM_KEY_VIDEO_MESSAGE", Parcels.wrap(videoMessage));
            args.putLong("PARAM_KEY_VIDEO_TIMESTAMP", timestamp);
            args.putBoolean("PARAM_KEY_VIDEO_IS_SEND_MSG", isSendMsg);
            baseActivity.launchActivity(VideoMessageActivity.class, args);
        }
    }

    public static void launchVideoMessage(BaseActivity baseActivity, UserChatMessage userChatMessage) {
        boolean z = false;
        if (baseActivity != null && userChatMessage != null) {
            if (userChatMessage.getMsgType() == 3 || userChatMessage.getMsgType() == 4) {
                int coin;
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                String nick = ((TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, userChatMessage.getUid()).findFirst()).getNick();
                realm.commitTransaction();
                realm.close();
                VideoMessage msg = new VideoMessage();
                msg.setCover(userChatMessage.getVideoThumb());
                if (userChatMessage.isNeedPay()) {
                    coin = userChatMessage.getCoin();
                } else {
                    coin = 0;
                }
                msg.setTikiPrice(coin);
                User user = new User();
                user.setNick(nick);
                user.setUid(userChatMessage.getUid());
                msg.setFrom(user);
                if (userChatMessage.getMsgType() == 4) {
                    msg.setVideoId(userChatMessage.getVideoPath());
                } else {
                    msg.setVideoId(userChatMessage.getVideoId());
                }
                long timestamp = userChatMessage.getTimestamp();
                if (userChatMessage.getMsgType() == 4) {
                    z = true;
                }
                m821a(baseActivity, msg, timestamp, z);
            }
        }
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m830d();
        m832e();
        m833f();
        m834g();
    }

    protected int mo2115a() {
        return C0376R.layout.activity_video_message;
    }

    protected int mo2117b() {
        return C0376R.id.fragment_container;
    }

    private void m830d() {
        if (getArguments() != null) {
            this.f1493f = (VideoMessage) Parcels.unwrap(getArguments().getParcelable("PARAM_KEY_VIDEO_MESSAGE"));
            this.f1492e = getArguments().getLong("PARAM_KEY_VIDEO_TIMESTAMP");
            this.f1496i = getArguments().getBoolean("PARAM_KEY_VIDEO_IS_SEND_MSG");
            if (this.f1493f != null && this.f1493f.getFrom() != null) {
                f1489a.m265i("initData: uId:" + this.f1493f.getFrom().getUid() + " videoId:" + this.f1493f.getVideoId() + " cover:" + this.f1493f.getCover() + " timelen:" + this.f1493f.getTimelen() + " isSendMsg:" + this.f1496i + " price:" + this.f1493f.getTikiPrice());
            }
        }
    }

    private void m832e() {
        if (!TextUtils.isEmpty(this.f1493f.getCover())) {
            if (this.f1493f.getTikiPrice() > 0) {
                FrescoUtil.setImageURI((Context) this, this.mVideoCover, Uri.parse(this.f1493f.getCover()), new BlurProcessor(this));
            } else {
                FrescoUtil.setImageURI(this.mVideoCover, this.f1493f.getCover());
            }
        }
        this.mVideoMessageView.setPlayerType(2);
        this.mVideoMessageView.setLooping(true);
        m826b(false);
        if (this.f1493f != null) {
            if (this.f1493f.getFrom() != null) {
                this.mNickView.setText(this.f1493f.getFrom().getNick());
                this.mTime.setText(DateUtil.computeTimeDiff(this.f1492e, false, true));
            }
            if (this.f1493f.getTikiPrice() <= 0) {
                this.mPurchaseHint.setText(BuildConfig.VERSION_NAME);
            } else {
                String prefix = getString(C0376R.string.video_message_purchase_hint_prefix);
                String hint = prefix + String.format(getString(C0376R.string.video_message_purchase_hint_postfix), new Object[]{Integer.valueOf(this.f1493f.getTikiPrice())});
                Spannable spannable = new SpannableString(hint);
                spannable.setSpan(new ForegroundColorSpan(getResources().getColor(C0376R.color.colorPrimary)), prefix.length(), hint.length(), 17);
                this.mPurchaseHint.setText(spannable);
            }
        }
        if (this.f1496i) {
            m822a(false);
            this.mReport.setVisibility(8);
            m829c(false);
            m826b(false);
        } else {
            m822a(true);
            m829c(false);
        }
        m431d(this.mReply);
    }

    private void m833f() {
        f1489a.m261d("initMediaPlayer");
        if (this.f1493f != null && !TextUtils.isEmpty(this.f1493f.getVideoId())) {
            if (this.f1496i) {
                m824b(this.f1493f.getVideoId());
            } else {
                getDataLayer().getMessageManager().videoMessageRequest(this.f1493f.getVideoId()).compose(SchedulersCompat.applyIoSchedulers()).observeOn(AndroidSchedulers.mainThread()).subscribe(VideoMessageActivity$$Lambda$1.lambdaFactory$(this), VideoMessageActivity$$Lambda$2.lambdaFactory$(this));
            }
        }
    }

    /* synthetic */ void m848a(String uri) throws Exception {
        m824b(uri);
    }

    /* synthetic */ void m853c(Throwable throwable) throws Exception {
    }

    private void m824b(String uri) {
        if (!TextUtils.isEmpty(uri)) {
            if (this.f1493f != null && this.f1493f.getTikiPrice() == 0) {
                m829c(true);
                m826b(false);
                m822a(false);
                this.mVideoMessageView.setVolume(((AudioManager) getSystemService("audio")).getStreamMaxVolume(3) / 2);
                this.mVideoMessageView.setVideoPath(uri);
                this.mVideoMessageView.start();
            } else if (this.f1496i) {
                this.mVideoMessageView.setVolume(((AudioManager) getSystemService("audio")).getStreamMaxVolume(3) / 2);
                this.mVideoMessageView.setVideoPath(uri);
                this.mVideoMessageView.start();
            } else {
                m836i();
                this.mVideoMessageView.setVolume(0);
                this.mVideoMessageView.setVideoPath(uri);
            }
        }
    }

    private void m834g() {
        RxView.clicks(this.mClose).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindToLifecycle()).subscribe(VideoMessageActivity$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.mReport).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindToLifecycle()).subscribe(VideoMessageActivity$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.mReply).throttleFirst(500, TimeUnit.MILLISECONDS).compose(bindToLifecycle()).subscribe(VideoMessageActivity$$Lambda$5.lambdaFactory$(this));
    }

    /* synthetic */ void m852c(Object aVoid) throws Exception {
        m841n();
    }

    /* synthetic */ void m851b(Object aVoid) throws Exception {
        if (this.f1493f != null && !TextUtils.isEmpty(this.f1493f.getVideoId())) {
            DialogHelper.INSTANCE.showComplainDialog((Context) this, this.f1493f);
        } else if (this.f1493f == null) {
            ToastUtil.getInstance().show((Context) this, (int) C0376R.string.video_message_error_null_message);
        } else if (TextUtils.isEmpty(this.f1493f.getVideoId())) {
            ToastUtil.getInstance().show((Context) this, (int) C0376R.string.video_message_error_empty_video_id);
        }
    }

    /* synthetic */ void m847a(Object aVoid) throws Exception {
        getDataLayer().getAppManager().getOperInfoCache().compose(bindToLifecycle()).subscribe(VideoMessageActivity$$Lambda$10.lambdaFactory$(this));
    }

    /* synthetic */ void m845a(OperInfo operInfo) throws Exception {
        if (operInfo != null && operInfo.isVideoRecOff()) {
            ToastUtil.getInstance().show((Context) this, (int) C0376R.string.unsupported_in_current_version);
        } else if (this.f1493f != null && this.f1493f.getFrom() != null && !TextUtils.isEmpty(this.f1493f.getFrom().getUid())) {
            Bundle args = new Bundle();
            args.putString("PARAM_KEY_UID", this.f1493f.getFrom().getUid());
            launchActivity(VideoRecordActivity.class, args);
        }
    }

    private void m835h() {
        this.mFingerPrint.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(this.f1494g).setControllerListener(new C04401(this))).setAutoPlayAnimations(false)).build());
    }

    private void m836i() {
        f1489a.m261d("initFingerPrint");
        m826b(true);
        m835h();
        this.mVideoMessageView.setOnTouchListener(new C04412(this));
    }

    private void m837j() {
        f1489a.m261d("startAnime");
        if ((this.f1491d == null || !this.f1491d.isStarted()) && this.f1490b != null && (this.f1490b instanceof AnimatedDrawable)) {
            this.f1491d = ((AnimatedDrawable) this.f1490b).createValueAnimator();
            this.f1491d.setRepeatCount(0);
            this.f1491d.setDuration(2000);
            this.f1491d.addListener(new C04423(this));
            this.f1491d.start();
        }
    }

    private void m838k() {
        f1489a.m261d("resetAnime");
        if (this.f1491d != null && this.f1491d.isRunning()) {
            this.f1491d.cancel();
        }
        this.mFingerPrint.setImageURI((String) null);
        m835h();
    }

    private void m839l() {
        if (this.f1495h != 2 && this.f1495h == 0) {
            synchronized (this) {
                if (this.f1495h == 0) {
                    m840m();
                }
            }
        }
    }

    private void m840m() {
        f1489a.m261d("buyVideoMessage");
        if (this.f1493f != null && !TextUtils.isEmpty(this.f1493f.getVideoId())) {
            this.f1495h = 1;
            getDataLayer().getPaymentManager().buyVideoMessage(this.f1493f.getVideoId()).compose(SchedulersCompat.applyIoSchedulers()).observeOn(AndroidSchedulers.mainThread()).subscribe(VideoMessageActivity$$Lambda$6.lambdaFactory$(this), VideoMessageActivity$$Lambda$7.lambdaFactory$(this));
        }
    }

    /* synthetic */ void m846a(Boolean result) throws Exception {
        if (result.booleanValue()) {
            m829c(true);
            m826b(false);
            m822a(false);
            this.mVideoMessageView.start();
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            ((UserChatMessage) realm.where(UserChatMessage.class).equalTo("videoId", this.f1493f.getVideoId()).findFirst()).setNeedPay(false);
            realm.commitTransaction();
            realm.close();
            this.f1495h = 2;
            return;
        }
        ToastUtil.getInstance().show((Context) this, (int) C0376R.string.video_message_error_buy_failed);
        m838k();
        this.f1495h = 0;
    }

    /* synthetic */ void m849a(Throwable throwable) throws Exception {
        m838k();
        String errorMessage = getResources().getString(C0376R.string.video_message_error_buy_failed);
        if (throwable instanceof NetException) {
            int returnCode = ((NetException) throwable).getCode();
            errorMessage = errorMessage + "(" + returnCode + ":" + ((NetException) throwable).getMsg() + ")";
            if (returnCode == -77) {
                getDataLayer().getAppManager().getConfigCache().compose(SchedulersCompat.applyIoSchedulers()).subscribe(VideoMessageActivity$$Lambda$8.lambdaFactory$(this), VideoMessageActivity$$Lambda$9.lambdaFactory$());
            } else if (returnCode == -91) {
            }
        }
        this.f1495h = 0;
    }

    /* synthetic */ void m844a(ConfigInfo configInfo) throws Exception {
        if (configInfo != null && !TextUtils.isEmpty(configInfo.getH5DiamondsUrl())) {
            DialogHelper.INSTANCE.showLackBalanceDialog(this, configInfo.getH5DiamondsUrl());
        }
    }

    static /* synthetic */ void m825b(Throwable throwable1) throws Exception {
    }

    private void m822a(boolean display) {
        if (display) {
            this.mCoverLayout.setBackgroundResource(C0376R.color.black_alpha_normal);
            this.mVideoCover.setVisibility(0);
            return;
        }
        this.mCoverLayout.setBackgroundResource(C0376R.color.transparent);
        this.mVideoCover.setVisibility(8);
    }

    private void m826b(boolean display) {
        if (!display || this.f1493f == null || this.f1493f.getTikiPrice() <= 0) {
            this.mPurchaseHint.setVisibility(8);
            this.mFingerPrint.setVisibility(8);
            return;
        }
        this.mPurchaseHint.setVisibility(0);
        this.mFingerPrint.setVisibility(0);
    }

    private void m829c(boolean display) {
        if (!display || this.f1496i) {
            this.mReply.setVisibility(8);
        } else {
            this.mReply.setVisibility(0);
        }
    }

    private void m841n() {
        this.mVideoMessageView.stopPlayback();
        this.mVideoMessageView.release(true);
        finish();
    }

    protected void onResume() {
        super.onResume();
        f1489a.m263e("mVideoMessageView.resume");
        this.mVideoMessageView.resume();
    }

    protected void onPause() {
        f1489a.m263e("mVideoMessageView.suspend");
        this.mVideoMessageView.suspend();
        super.onPause();
    }

    protected void onDestroy() {
        f1489a.m263e("mVideoMessageView.release");
        this.mVideoMessageView.release(true);
        super.onDestroy();
    }
}
