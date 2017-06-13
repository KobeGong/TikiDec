package com.buddy.tiki.ui.fragment;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.event.UserEvent.ReSendGiftEvent;
import com.buddy.tiki.event.UserEvent.ReSendTextEvent;
import com.buddy.tiki.event.UserEvent.UploadStateEvent;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.helper.MediaHelper;
import com.buddy.tiki.helper.UploadHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.exception.NetException;
import com.buddy.tiki.model.payment.SendGiftResult;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.model.user.TikiAdministrator;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.model.user.UserChatMessage;
import com.buddy.tiki.model.user.UserChatSession;
import com.buddy.tiki.service.base.ACache;
import com.buddy.tiki.ui.activity.VideoRecordActivity;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import com.buddy.tiki.ui.adapter.ChatMessageAdapter;
import com.buddy.tiki.ui.adapter.ChatRoomGiftAdapter;
import com.buddy.tiki.ui.adapter.base.BaseAdapter.OnItemClick;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.DateUtil;
import com.buddy.tiki.util.MessageUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.RippleUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.view.spring.ScaleSpringListener;
import com.buddy.tiki.view.superrecyclerview.SuperRecyclerView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.animated.base.AnimatedDrawable;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.view.RxView;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.swresample;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class ChatMessageFragment extends BaseFragment implements OnItemClick<Gift> {
    private static final TikiLog f2093c = TikiLog.getInstance("ChatMessageFragment");
    private Spring f2094A;
    private Spring f2095B;
    private ScaleSpringListener f2096C;
    private ScaleSpringListener f2097D;
    private ScaleSpringListener f2098E;
    UserChatSession f2099a;
    @BindView(2131820944)
    AppCompatImageView call;
    private ChatRoomGiftAdapter f2100d;
    private Gift f2101e;
    private ConfigInfo f2102f;
    private User f2103g;
    @BindView(2131820945)
    View giftLayout;
    private List<Gift> f2104h;
    private ChatMessageAdapter f2105i;
    private List<UserChatMessage> f2106j;
    private TikiUser f2107k;
    private TikiAdministrator f2108l;
    private RealmResults<UserChatMessage> f2109m;
    @BindView(2131820936)
    AppCompatTextView mChatMessageNick;
    @BindView(2131820826)
    AppCompatTextView mDiamondText;
    @BindView(2131820770)
    RecyclerView mGiftList;
    @BindView(2131820740)
    SimpleDraweeView mGiftShow;
    @BindView(2131820937)
    AppCompatImageView mMenuButton;
    @BindView(2131820897)
    AppCompatButton mPresentButton;
    @BindView(2131820895)
    AppCompatTextView mRechargeButton;
    @BindView(2131820688)
    Toolbar mToolbar;
    @BindView(2131820939)
    SuperRecyclerView messageList;
    private int f2110n = 1;
    private Handler f2111o = new Handler();
    private int f2112p = 0;
    private int f2113q = 0;
    private boolean f2114r = false;
    @BindView(2131820943)
    AppCompatImageView record;
    @BindView(2131820697)
    RelativeLayout rootLayout;
    private Runnable f2115s = ChatMessageFragment$$Lambda$1.lambdaFactory$(this);
    @BindView(2131820942)
    AppCompatImageView sendGift;
    @BindView(2131820941)
    AppCompatButton sendText;
    @BindView(2131820938)
    SwipeRefreshLayout swipeRefreshLayout;
    private RealmChangeListener f2116t = ChatMessageFragment$$Lambda$2.lambdaFactory$(this);
    @BindView(2131820940)
    AppCompatEditText textMessage;
    private OnRefreshListener f2117u = ChatMessageFragment$$Lambda$3.lambdaFactory$(this);
    private RealmChangeListener f2118v = ChatMessageFragment$$Lambda$4.lambdaFactory$(this);
    @BindView(2131820946)
    LinearLayout videoChatTip;
    @BindView(2131820947)
    AppCompatTextView videoChatTipText;
    private String f2119w;
    private boolean f2120x = false;
    private boolean f2121y = false;
    private Spring f2122z;

    class C04591 implements TextWatcher {
        final /* synthetic */ ChatMessageFragment f2088a;

        C04591(ChatMessageFragment this$0) {
            this.f2088a = this$0;
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                this.f2088a.sendText.setVisibility(0);
            } else {
                this.f2088a.sendText.setVisibility(8);
            }
        }
    }

    class C04602 implements OnLayoutChangeListener {
        final /* synthetic */ ChatMessageFragment f2089a;

        C04602(ChatMessageFragment this$0) {
            this.f2089a = this$0;
        }

        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (oldBottom != 0 && bottom != 0 && oldBottom > bottom) {
                this.f2089a.f2114r = true;
                ChatMessageFragment.f2093c.m263e("\u5c0f\u952e\u76d8\u6253\u5f00");
                this.f2089a.videoChatTip.setVisibility(8);
                this.f2089a.giftLayout.setVisibility(8);
                this.f2089a.m1252k();
            } else if (oldBottom != 0 && bottom != 0 && bottom > oldBottom) {
                this.f2089a.f2114r = false;
                ChatMessageFragment.f2093c.m263e("\u5c0f\u952e\u76d8\u6536\u8d77");
            }
        }
    }

    class C04613 implements Runnable {
        final /* synthetic */ ChatMessageFragment f2090a;

        C04613(ChatMessageFragment this$0) {
            this.f2090a = this$0;
        }

        public void run() {
            this.f2090a.giftLayout.setVisibility(0);
            this.f2090a.m1252k();
        }
    }

    class C04634 extends BaseControllerListener<ImageInfo> {
        final /* synthetic */ ChatMessageFragment f2092a;

        class C04621 implements AnimatorListener {
            final /* synthetic */ C04634 f2091a;

            C04621(C04634 this$1) {
                this.f2091a = this$1;
            }

            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                MediaHelper.INSTANCE.stopMusic();
                if (this.f2091a.f2092a.mGiftShow != null) {
                    this.f2091a.f2092a.mGiftShow.setVisibility(8);
                }
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        }

        C04634(ChatMessageFragment this$0) {
            this.f2092a = this$0;
        }

        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            if (animatable != null && (animatable instanceof AnimatedDrawable)) {
                ValueAnimator animator = ((AnimatedDrawable) animatable).createValueAnimator();
                animator.setRepeatCount(0);
                animator.start();
                animator.addListener(new C04621(this));
            }
        }
    }

    /* synthetic */ void m1289f() {
        this.f2110n++;
        this.swipeRefreshLayout.setRefreshing(false);
        if ((this.f2110n - 1) * 10 < this.f2109m.size()) {
            this.f2106j = MessageUtil.addMessagesWithTimeline(this.f2110n, this.f2109m);
            this.f2105i.setData(this.f2106j);
            this.messageList.getRecyclerView().scrollToPosition((this.f2106j.size() - this.f2112p) + 1);
            this.f2112p = this.f2106j.size();
            if (this.f2110n * 10 >= this.f2109m.size()) {
                this.swipeRefreshLayout.setEnabled(false);
                return;
            }
            return;
        }
        this.swipeRefreshLayout.setEnabled(false);
    }

    /* synthetic */ void m1294j(Object element) {
        m1239a((RealmResults) element);
    }

    /* synthetic */ void m1286e() {
        this.f2111o.postDelayed(this.f2115s, 500);
    }

    /* synthetic */ void m1293i(Object element) {
        if (element != null) {
            TikiUser user = (TikiUser) element;
            if (user.isValid()) {
                if (this.mChatMessageNick != null) {
                    this.mChatMessageNick.setText(user.getMark());
                }
                if (user.isOper()) {
                    this.mMenuButton.setVisibility(8);
                    this.call.setVisibility(8);
                    return;
                }
                return;
            }
            m1204i();
        }
    }

    private void m1239a(RealmResults<UserChatMessage> element) {
        if (this.f2113q < element.size()) {
            this.f2113q = element.size();
            this.f2106j = MessageUtil.addMessagesWithTimeline(this.f2110n, element);
            this.f2105i.setData(this.f2106j);
            if (this.f2106j != null && this.f2106j.size() > 0) {
                this.f2112p = this.f2106j.size();
                m1252k();
                if (((UserChatMessage) this.f2106j.get(this.f2112p - 1)).getActionType() == 5 && ((UserChatMessage) this.f2106j.get(this.f2112p - 1)).getMsgType() == 2) {
                    this.videoChatTip.setVisibility(0);
                    this.videoChatTipText.setText(getString(C0376R.string.chat_no_response));
                    Observable.timer(3000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY)).subscribe(ChatMessageFragment$$Lambda$5.lambdaFactory$(this));
                }
            }
        }
    }

    /* synthetic */ void m1282c(Long aLong) throws Exception {
        PreferenceUtil.setVideoChatTip(1);
        this.videoChatTip.setVisibility(8);
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_chat_msg;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1251j();
        m1256o();
        m1253l();
        m1255n();
        SpringSystem springSystem = SpringSystem.create();
        this.f2122z = springSystem.createSpring();
        this.f2094A = springSystem.createSpring();
        this.f2095B = springSystem.createSpring();
        this.f2096C = new ScaleSpringListener(this.call);
        this.f2097D = new ScaleSpringListener(this.record);
        this.f2098E = new ScaleSpringListener(this.sendGift);
        this.f2105i = new ChatMessageAdapter(m1203h(), this.f2106j, this.f2107k, this.f2108l);
        this.messageList.setAdapter(this.f2105i);
        if (this.f2106j != null && this.f2106j.size() > 0) {
            this.messageList.getRecyclerView().scrollToPosition(this.f2106j.size() - 1);
            this.f2112p = this.f2106j.size();
        }
        if (this.f2109m != null && this.f2110n * 10 >= this.f2109m.size()) {
            this.swipeRefreshLayout.setEnabled(false);
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (PreferenceUtil.getVideoChatTip() == 0) {
            this.videoChatTip.setVisibility(0);
            Observable.timer(3000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY)).subscribe(ChatMessageFragment$$Lambda$6.lambdaFactory$(this));
        }
        this.textMessage.addTextChangedListener(new C04591(this));
    }

    /* synthetic */ void m1278b(Long aLong) throws Exception {
        PreferenceUtil.setVideoChatTip(1);
        this.videoChatTip.setVisibility(8);
    }

    private void m1251j() {
        RippleUtil.setRippleBackground(m1203h(), this.mMenuButton);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.messageList.setLayoutManager(linearLayoutManager);
        this.rootLayout.addOnLayoutChangeListener(new C04602(this));
        ItemAnimator itemAnimator = this.messageList.getRecyclerView().getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
        this.mMenuButton.setVisibility(8);
        this.call.setVisibility(8);
    }

    private void m1252k() {
        if (this.messageList != null && this.messageList.getRecyclerView() != null && this.f2106j != null && this.f2106j.size() > 0) {
            this.messageList.getRecyclerView().scrollToPosition(this.f2106j.size() - 1);
        }
    }

    private void m1253l() {
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$7.lambdaFactory$(this));
        RxView.clicks(this.mMenuButton).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$8.lambdaFactory$(this));
        RxView.clicks(this.mGiftShow).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$9.lambdaFactory$(this));
        RxView.clicks(this.textMessage).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$10.lambdaFactory$(this));
        RxView.clicks(this.sendText).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$11.lambdaFactory$(this));
        RxView.touches(this.sendGift).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$12.lambdaFactory$(this));
        RxView.touches(this.record).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$13.lambdaFactory$(this));
        RxView.touches(this.call).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$14.lambdaFactory$(this));
        RxView.clicks(this.messageList.getEmptyView()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$15.lambdaFactory$(this));
        this.swipeRefreshLayout.setColorSchemeResources(C0376R.color.colorPrimary);
        this.swipeRefreshLayout.setEnabled(true);
        this.swipeRefreshLayout.setOnRefreshListener(this.f2117u);
    }

    /* synthetic */ void m1292h(Object aVoid) throws Exception {
        if (m1203h() == null) {
            return;
        }
        if (this.f2120x) {
            m1203h().setResult(-1);
            m1203h().finish();
            return;
        }
        m1203h().setResult(0);
        m1203h().finish();
    }

    /* synthetic */ void m1291g(Object aVoid) throws Exception {
        DialogHelper.INSTANCE.showFriendDialog(m1203h(), this.f2107k);
    }

    /* synthetic */ void m1290f(Object aVoid) throws Exception {
        if (!(this.mGiftShow == null || this.mGiftShow.getController() == null || this.mGiftShow.getController().getAnimatable() == null)) {
            this.mGiftShow.getController().getAnimatable().stop();
        }
        MediaHelper.INSTANCE.stopMusic();
        if (this.mGiftShow != null) {
            this.mGiftShow.setVisibility(8);
        }
    }

    /* synthetic */ void m1287e(Object aVoid) throws Exception {
        this.videoChatTip.setVisibility(8);
        this.giftLayout.setVisibility(8);
    }

    /* synthetic */ void m1285d(Object aVoid) throws Exception {
        f2093c.m263e("text = " + this.textMessage.getText().toString());
        m1241a(this.textMessage.getText().toString(), false, BuildConfig.VERSION_NAME);
        this.textMessage.setText(BuildConfig.VERSION_NAME);
    }

    /* synthetic */ void m1281c(MotionEvent event) throws Exception {
        switch (event.getAction()) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                this.f2095B.setEndValue(1.0d);
                return;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                if (this.giftLayout.getVisibility() == 8) {
                    new Handler().postDelayed(ChatMessageFragment$$Lambda$38.lambdaFactory$(this), 300);
                    break;
                }
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                break;
            default:
                return;
        }
        this.f2095B.setEndValue(0.0d);
    }

    /* synthetic */ void m1284d() {
        m1260s();
    }

    /* synthetic */ void m1276b(MotionEvent event) throws Exception {
        PreferenceUtil.setVideoChatTip(1);
        this.videoChatTip.setVisibility(8);
        switch (event.getAction()) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                this.f2094A.setEndValue(1.0d);
                return;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                new Handler().postDelayed(ChatMessageFragment$$Lambda$35.lambdaFactory$(this), 300);
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                break;
            default:
                return;
        }
        this.f2094A.setEndValue(0.0d);
    }

    /* synthetic */ void m1280c() {
        if (this.f2107k == null || !this.f2107k.isValid()) {
            m1204i();
        } else {
            getDataLayer().getAppManager().getOperInfoCache().compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$36.lambdaFactory$(this), ChatMessageFragment$$Lambda$37.lambdaFactory$(this));
        }
    }

    /* synthetic */ void m1266a(OperInfo operInfo) throws Exception {
        if (operInfo == null || !operInfo.isVideoRecOff()) {
            m1254m();
        } else {
            ToastUtil.getInstance().show(m1203h(), (int) C0376R.string.unsupported_in_current_version);
        }
    }

    /* synthetic */ void m1288e(Throwable throwable) throws Exception {
        m1254m();
    }

    /* synthetic */ void m1264a(MotionEvent event) throws Exception {
        switch (event.getAction()) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                this.f2122z.setEndValue(1.0d);
                return;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                new Handler().postDelayed(ChatMessageFragment$$Lambda$34.lambdaFactory$(this), 300);
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                break;
            default:
                return;
        }
        this.f2122z.setEndValue(0.0d);
    }

    /* synthetic */ void m1275b() {
        this.f2120x = true;
        DialogHelper.INSTANCE.startVideoChat(m1203h(), this.f2107k);
    }

    /* synthetic */ void m1283c(Object aVoid) throws Exception {
        hideGiftLayout();
    }

    private void m1254m() {
        this.f2120x = true;
        Bundle args = new Bundle();
        args.putString("PARAM_KEY_UID", this.f2107k.getUid());
        m1202a(VideoRecordActivity.class, args);
    }

    private void m1255n() {
        this.mGiftList.setLayoutManager(new LinearLayoutManager(m1203h(), 0, false));
        ItemAnimator itemAnimator = this.mGiftList.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
        this.f2100d = new ChatRoomGiftAdapter(m1203h(), this);
        this.mGiftList.setAdapter(this.f2100d);
        RxView.clicks(this.mPresentButton).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$16.lambdaFactory$(this));
        RxView.clicks(this.mRechargeButton).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ChatMessageFragment$$Lambda$17.lambdaFactory$(this));
    }

    /* synthetic */ void m1279b(Object aVoid) throws Exception {
        if (this.f2101e != null) {
            if (this.f2103g == null || this.f2103g.getDiamonds() >= ((long) this.f2101e.getDiamonds()) || this.f2102f == null) {
                m1235a(this.f2101e, false, BuildConfig.VERSION_NAME);
            } else {
                DialogHelper.INSTANCE.showLackBalanceDialog(getContext(), this.f2102f.getH5DiamondsUrl());
            }
        }
    }

    /* synthetic */ void m1269a(Object aVoid) throws Exception {
        if (this.f2102f != null) {
            m1259r();
            WebBrowserActivity.launchWeb(getContext(), this.f2102f.getH5DiamondsUrl());
        }
    }

    private void m1256o() {
        if (getArguments() != null) {
            this.f2119w = getArguments().getString("PARAM_KEY_UID");
            Realm realm = Realm.getDefaultInstance();
            this.f2108l = (TikiAdministrator) realm.where(TikiAdministrator.class).findFirst();
            this.f2107k = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, this.f2119w).findFirst();
            if (this.f2107k != null && this.f2107k.isValid()) {
                this.f2107k.addChangeListener(this.f2118v);
                this.f2099a = (UserChatSession) realm.where(UserChatSession.class).equalTo("sessionId", this.f2107k.getUid()).findFirst();
                realm.beginTransaction();
                if (this.f2099a == null) {
                    this.f2099a = (UserChatSession) realm.createObject(UserChatSession.class, this.f2107k.getUid());
                    realm.copyToRealm(this.f2099a);
                }
                if (this.f2107k.getUnread() > 0) {
                    this.f2107k.setUnread(0);
                }
                this.f2099a.setUnread(0);
                realm.commitTransaction();
                this.f2109m = this.f2099a.getMessages().where().findAll().sort("timestamp");
                this.f2113q = this.f2109m.size();
                f2093c.m263e("message size = " + this.f2113q);
                this.f2109m.addChangeListener(this.f2116t);
                this.f2106j = MessageUtil.addMessagesWithTimeline(this.f2110n, this.f2109m);
                this.mChatMessageNick.setText(this.f2107k.getMark());
                this.messageList.setEmptyText(DateUtil.computeTimeDiff(this.f2107k.getLastMessageTime(), true, true));
                if (!this.f2107k.isOper()) {
                    this.mMenuButton.setVisibility(0);
                    this.call.setVisibility(0);
                }
            }
            realm.close();
            m1257p();
        }
    }

    private void m1257p() {
        getDataLayer().getAppManager().getServerTime().compose(SchedulersCompat.applyIoSchedulers()).observeOn(AndroidSchedulers.mainThread()).subscribe(ChatMessageFragment$$Lambda$18.lambdaFactory$(this), ChatMessageFragment$$Lambda$19.lambdaFactory$());
    }

    /* synthetic */ void m1268a(Long time) throws Exception {
        f2093c.m263e("time=" + time);
        if (this.f2105i != null && time != null && time.longValue() > 0) {
            this.f2105i.setServerTime(time.longValue());
        }
    }

    private void m1258q() {
        this.videoChatTip.setVisibility(8);
        this.giftLayout.postDelayed(new C04613(this), 300);
        hideSoftKeybord();
    }

    public void hideSoftKeybord() {
        if (this.f2114r) {
            InputMethodManager imm = (InputMethodManager) m1203h().getSystemService("input_method");
            imm.showSoftInput(this.textMessage, 2);
            imm.hideSoftInputFromWindow(this.textMessage.getWindowToken(), 0);
        }
    }

    private void m1259r() {
        this.giftLayout.setVisibility(8);
    }

    private void m1260s() {
        m1258q();
        m1261t();
    }

    private void m1261t() {
        getDataLayer().getAppManager().getConfigCache().compose(bindToLifecycle()).subscribeOn(Schedulers.io()).subscribe(ChatMessageFragment$$Lambda$20.lambdaFactory$(this), ChatMessageFragment$$Lambda$21.lambdaFactory$());
        getDataLayer().getUserManager().userRequest(TopConfig.f408a).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(ChatMessageFragment$$Lambda$22.lambdaFactory$(this), ChatMessageFragment$$Lambda$23.lambdaFactory$());
        getDataLayer().getResourceManager().sysGiftsRequest(1).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(ChatMessageFragment$$Lambda$24.lambdaFactory$(this), ChatMessageFragment$$Lambda$25.lambdaFactory$());
    }

    /* synthetic */ void m1265a(ConfigInfo configInfo) throws Exception {
        this.f2102f = configInfo;
    }

    /* synthetic */ void m1267a(User user) throws Exception {
        this.f2103g = user;
        this.mDiamondText.setText(String.valueOf(user.getDiamonds()));
    }

    /* synthetic */ void m1270a(List list) throws Exception {
        if (list != null) {
            Iterator<Gift> iter = list.iterator();
            while (iter.hasNext()) {
                if (((Gift) iter.next()).getDiamonds() == 0) {
                    iter.remove();
                }
            }
        }
        this.f2104h = list;
        this.f2100d.clearDataList();
        this.f2100d.addDataList(list);
    }

    private void m1235a(@NonNull Gift gift, boolean isReSend, String msgId) {
        long now = System.currentTimeMillis();
        getDataLayer().getFollowManager().sendGiftInChat(this.f2107k.getUid(), gift.getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(ChatMessageFragment$$Lambda$26.lambdaFactory$()).subscribe(ChatMessageFragment$$Lambda$27.lambdaFactory$(this, isReSend, now, msgId), ChatMessageFragment$$Lambda$28.lambdaFactory$(this, isReSend, now));
    }

    static /* synthetic */ boolean m1244a(SendGiftResult giftResult) throws Exception {
        return giftResult != null;
    }

    /* synthetic */ void m1271a(boolean isReSend, long now, String msgId, SendGiftResult giftResult) throws Exception {
        if (giftResult == null) {
            return;
        }
        if (isReSend) {
            m1240a(msgId, false);
        } else {
            m1243a(false, now);
        }
    }

    /* synthetic */ void m1272a(boolean isReSend, long now, Throwable throwable) throws Exception {
        if ((throwable instanceof NetException) && ((NetException) throwable).getCode() == -77) {
            DialogHelper.INSTANCE.showLackBalanceDialog(getContext(), this.f2102f.getH5DiamondsUrl());
        } else if (!isReSend) {
            m1243a(true, now);
        }
    }

    private void m1240a(String msgId, boolean flag) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserChatMessage msg = (UserChatMessage) realm.where(UserChatMessage.class).equalTo("msgId", msgId).findFirst();
        if (msg != null) {
            msg.setSendFailed(flag);
        }
        realm.commitTransaction();
        realm.close();
    }

    private void m1243a(boolean failed, long timestamp) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserChatSession session = (UserChatSession) realm.where(UserChatSession.class).equalTo("sessionId", this.f2107k.getUid()).findFirst();
        if (session == null || !session.isValid()) {
            session = (UserChatSession) realm.createObject(UserChatSession.class, this.f2107k.getUid());
        }
        UserChatMessage chatMsg = (UserChatMessage) realm.createObject(UserChatMessage.class, UUID.randomUUID().toString());
        chatMsg.setMsgType(9);
        chatMsg.setTimestamp(timestamp);
        chatMsg.setGiftCover(this.f2101e.getCover());
        chatMsg.setGiftId(this.f2101e.getId());
        chatMsg.setGiftMusic(this.f2101e.getMusic());
        chatMsg.setGiftSource(this.f2101e.getSource());
        chatMsg.setGiftName(this.f2101e.getName());
        chatMsg.setUid(this.f2107k.getUid());
        chatMsg.setCoin(2);
        chatMsg.setSendFailed(failed);
        session.getMessages().add(chatMsg);
        session.setTimestamp(timestamp);
        TikiUser tikiUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, this.f2107k.getUid()).findFirst();
        if (tikiUser != null && tikiUser.isLoaded() && tikiUser.isValid()) {
            f2093c.m263e("---set gift message---");
            tikiUser.setLastMessage(ChatApp.getInstance().getResources().getString(C0376R.string.format_gift_message, new Object[]{this.f2101e.getName()}));
            tikiUser.setLastMessageTime(timestamp);
        }
        realm.commitTransaction();
        realm.close();
    }

    private void m1241a(@NonNull String textMessage, boolean isReSend, String msgId) {
        getDataLayer().getMessageManager().sendTextMessage(this.f2107k.getUid(), textMessage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ChatMessageFragment$$Lambda$29.lambdaFactory$(this, isReSend, textMessage, msgId), ChatMessageFragment$$Lambda$30.lambdaFactory$(this, isReSend, textMessage));
    }

    /* synthetic */ void m1273a(boolean isReSend, @NonNull String textMessage, String msgId) throws Exception {
        if (isReSend) {
            m1240a(msgId, false);
        } else {
            m1246b(textMessage, false);
        }
    }

    /* synthetic */ void m1274a(boolean isReSend, @NonNull String textMessage, Throwable throwable) throws Exception {
        if (!isReSend) {
            m1246b(textMessage, true);
        }
    }

    private void m1246b(String text, boolean failed) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserChatSession session = (UserChatSession) realm.where(UserChatSession.class).equalTo("sessionId", this.f2107k.getUid()).findFirst();
        f2093c.m263e("uid = " + this.f2107k.getUid());
        if (session == null || !session.isValid()) {
            session = (UserChatSession) realm.createObject(UserChatSession.class, this.f2107k.getUid());
        }
        long now = System.currentTimeMillis();
        UserChatMessage chatMsg = (UserChatMessage) realm.createObject(UserChatMessage.class, UUID.randomUUID().toString());
        chatMsg.setMsgType(7);
        chatMsg.setTimestamp(now);
        chatMsg.setMsgText(text);
        chatMsg.setUid(this.f2107k.getUid());
        chatMsg.setSendFailed(failed);
        session.getMessages().add(chatMsg);
        session.setTimestamp(now);
        TikiUser tikiUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, this.f2107k.getUid()).findFirst();
        if (tikiUser != null && tikiUser.isLoaded() && tikiUser.isValid()) {
            f2093c.m263e("---set last message---");
            tikiUser.setLastMessage(text);
            tikiUser.setLastMessageTime(now);
        }
        realm.commitTransaction();
        realm.close();
    }

    public void showGift(boolean immediately, String music, String id, String source) {
        hideSoftKeybord();
        this.mGiftShow.setVisibility(0);
        if (immediately) {
            if (!TextUtils.isEmpty(music)) {
                String path = ACache.get(m1203h()).getAsString(id);
                if (path != null) {
                    MediaHelper.INSTANCE.playMusic(path, false);
                } else {
                    MediaHelper.INSTANCE.playMusic(music, false);
                }
            }
            this.mGiftShow.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(source).setControllerListener(new C04634(this))).build());
        }
    }

    public boolean isGiftLayoutShowing() {
        return this.giftLayout.getVisibility() == 0;
    }

    public void hideGiftLayout() {
        m1259r();
        hideSoftKeybord();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onUploadStateEvent(UploadStateEvent event) {
        UploadHelper.getInstance().uploadVideoMessage(event.f524e, event.f525f, event.f521b, event.f522c, event.f523d, event.f520a, event.f526g);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReSendGiftEvent(ReSendGiftEvent event) {
        Gift gift = new Gift();
        gift.setId(event.f517b);
        m1235a(gift, true, event.f516a);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReSendTextEvent(ReSendTextEvent event) {
        m1241a(event.f519b, true, event.f518a);
    }

    public void onResume() {
        super.onResume();
        this.f2122z.addListener(this.f2096C);
        this.f2094A.addListener(this.f2097D);
        this.f2095B.addListener(this.f2098E);
    }

    public void onPause() {
        super.onPause();
        if (!(this.mGiftShow == null || this.mGiftShow.getController() == null || this.mGiftShow.getController().getAnimatable() == null)) {
            this.mGiftShow.getController().getAnimatable().stop();
        }
        this.f2122z.removeListener(this.f2096C);
        this.f2094A.removeListener(this.f2097D);
        this.f2095B.removeListener(this.f2098E);
        if (!TextUtils.isEmpty(this.f2119w)) {
            Realm outRealm = Realm.getDefaultInstance();
            Transaction lambdaFactory$ = ChatMessageFragment$$Lambda$31.lambdaFactory$(this);
            outRealm.getClass();
            outRealm.executeTransactionAsync(lambdaFactory$, ChatMessageFragment$$Lambda$32.lambdaFactory$(outRealm), ChatMessageFragment$$Lambda$33.lambdaFactory$(outRealm));
        }
    }

    /* synthetic */ void m1277b(Realm realm) {
        TikiUser user = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, this.f2119w).findFirst();
        if (user != null && user.isValid() && user.getUnread() > 0) {
            user.setUnread(0);
            if (this.f2099a != null) {
                this.f2099a.setUnread(0);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (this.f2111o != null) {
            this.f2111o.removeCallbacks(this.f2115s);
        }
        if (this.f2109m != null) {
            this.f2109m.removeChangeListener(this.f2116t);
        }
        if (this.f2107k != null) {
            this.f2107k.removeChangeListener(this.f2118v);
        }
    }

    public void click(View view, Gift data, int position) {
        if (data != null) {
            this.mPresentButton.setEnabled(true);
            this.f2101e = data;
        }
    }
}
