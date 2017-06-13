package com.buddy.tiki.ui.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.event.UserEvent.InputDiamondsDialogDismissEvent;
import com.buddy.tiki.event.UserEvent.InputDiamondsEvent;
import com.buddy.tiki.event.UserEvent.UploadStateEvent;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.user.TikiAdministrator;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.UserChatMessage;
import com.buddy.tiki.model.user.UserChatSession;
import com.buddy.tiki.ui.activity.base.ActivityManager;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.adapter.SendFriendsAdapter;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.Emojis;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.ResourceUrlUtil;
import com.buddy.tiki.view.video.IjkVideoView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding2.view.RxView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener;

public class VideoRecordResultActivity extends BaseActivity {
    private static final TikiLog f1570a = TikiLog.getInstance("VideoRecordResultActivity");
    @BindView(2131820797)
    AppCompatImageView addFriend;
    @BindView(2131820804)
    AppCompatImageView arrow;
    @BindView(2131820794)
    SimpleDraweeView avatar;
    private TikiAdministrator f1571b;
    @BindView(2131820792)
    AppCompatImageView close;
    private String f1572d;
    @BindView(2131820807)
    LinearLayout diamondFree;
    @BindView(2131820808)
    AppCompatImageView diamondFreeIcon;
    @BindView(2131820809)
    AppCompatTextView diamondFreeText;
    @BindView(2131820810)
    LinearLayout diamondOne;
    @BindView(2131820811)
    AppCompatImageView diamondOneIcon;
    @BindView(2131820812)
    AppCompatTextView diamondOneText;
    @BindView(2131820813)
    LinearLayout diamondTwo;
    @BindView(2131820814)
    AppCompatImageView diamondTwoIcon;
    @BindView(2131820815)
    AppCompatTextView diamondTwoText;
    private SendFriendsAdapter f1573e;
    private RealmResults<TikiUser> f1574f;
    @BindView(2131820798)
    RecyclerView friendsView;
    private int f1575g = 0;
    private String[] f1576h = null;
    private String f1577i;
    private String f1578j;
    private TextView f1579k;
    private TableLayout f1580l;
    private long f1581m;
    @BindView(2131820791)
    IjkVideoView mVideoView;
    private boolean f1582n = false;
    @BindView(2131820816)
    AppCompatTextView notVipDiamondsNum;
    @BindView(2131820793)
    LinearLayout notVipFriendLayout;
    @BindView(2131820795)
    AppCompatTextView notVipUserName;
    private boolean f1583o = false;
    @BindView(2131820799)
    AppCompatImageView sendVideoRecord;
    @BindView(2131820817)
    AppCompatButton setDiamondOk;
    @BindView(2131820806)
    LinearLayout setDiamonds;
    @BindView(2131820800)
    View shadow;
    @BindView(2131820803)
    LinearLayout showPayLayout;
    @BindView(2131820805)
    AppCompatTextView showPayNum;
    @BindView(2131820796)
    LinearLayout vipFriendLayout;

    class C04461 implements OnPreparedListener {
        final /* synthetic */ VideoRecordResultActivity f1569a;

        C04461(VideoRecordResultActivity this$0) {
            this.f1569a = this$0;
        }

        public void onPrepared(IMediaPlayer iMediaPlayer) {
            this.f1569a.f1581m = iMediaPlayer.getDuration() / 1000;
        }
    }

    @LayoutRes
    protected int mo2115a() {
        return C0376R.layout.activity_video_record_result;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m914c();
        m915d();
        m916e();
        m917f();
        m919h();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ((AudioManager) getSystemService("audio")).setMode(0);
        m432e(this.setDiamonds);
        m432e(this.showPayLayout);
        m432e(this.close);
        m431d(this.notVipFriendLayout);
        m431d(this.vipFriendLayout);
        m431d(this.sendVideoRecord);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setNavigationBarTintResource(C0376R.color.black_alpha_normal);
    }

    private void m914c() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.friendsView.setLayoutManager(linearLayoutManager);
        ItemAnimator itemAnimator = this.friendsView.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
        this.diamondOneText.setText("1 " + Emojis.getEmojiStringByUnicode(128142));
        this.diamondTwoText.setText("2 " + Emojis.getEmojiStringByUnicode(128142));
    }

    private void m915d() {
        if (getArguments() != null) {
            this.f1572d = getArguments().getString("PARAM_KEY_UID");
            this.f1577i = getArguments().getString("PARAM_KEY_VIDEO_PATH");
            this.f1578j = getArguments().getString("PARAM_KEY_VIDEO_COVER_PATH");
            this.f1581m = getArguments().getLong("PARAM_KEY_VIDEO_LENGTH", 0);
            this.f1582n = getArguments().getBoolean("PARAM_KEY_VIDEO_IS_MPEG4");
            f1570a.m265i("initData: uId:" + this.f1572d + " videoPath:" + this.f1577i + "cover:" + this.f1578j + " timelen:" + this.f1581m + " isMpeg4:" + this.f1582n);
        }
        if (TextUtils.isEmpty(this.f1572d)) {
            m910a(0);
        }
        Realm realm = Realm.getDefaultInstance();
        this.f1571b = (TikiAdministrator) realm.where(TikiAdministrator.class).findFirst();
        this.f1574f = realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, this.f1572d).findAll();
        if (this.f1571b != null && this.f1571b.isVipSend()) {
            this.f1573e = new SendFriendsAdapter(this, this.f1574f);
            this.friendsView.setAdapter(this.f1573e);
        }
        realm.close();
        this.f1576h = new String[]{this.f1572d};
    }

    private void m916e() {
        this.f1579k = (TextView) findViewById(C0376R.id.toast_text_view);
        this.f1580l = (TableLayout) findViewById(C0376R.id.hud_view);
        this.mVideoView = (IjkVideoView) findViewById(C0376R.id.video_view);
        int maxVolume = ((AudioManager) getSystemService("audio")).getStreamMaxVolume(3);
        this.mVideoView.setPlayerType(1);
        this.mVideoView.setLooping(true);
        this.mVideoView.setVolume(maxVolume / 2);
        this.mVideoView.setOnPreparedListener(new C04461(this));
        if (TextUtils.isEmpty(this.f1577i)) {
            f1570a.m263e("Null Data Source\n");
            m910a(0);
            return;
        }
        this.mVideoView.setVideoPath(this.f1577i);
        this.mVideoView.start();
    }

    private void m917f() {
        RxView.clicks(this.showPayLayout).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(VideoRecordResultActivity$$Lambda$1.lambdaFactory$(this));
        RxView.clicks(this.close).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(VideoRecordResultActivity$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.addFriend).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(VideoRecordResultActivity$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.sendVideoRecord).throttleFirst(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(VideoRecordResultActivity$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.setDiamondOk).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(VideoRecordResultActivity$$Lambda$5.lambdaFactory$(this));
        RxView.clicks(this.diamondFree).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(VideoRecordResultActivity$$Lambda$6.lambdaFactory$(this));
        RxView.clicks(this.diamondOne).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(VideoRecordResultActivity$$Lambda$7.lambdaFactory$(this));
        RxView.clicks(this.diamondTwo).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(VideoRecordResultActivity$$Lambda$8.lambdaFactory$(this));
    }

    /* synthetic */ void m931h(Object aVoid) throws Exception {
        if (this.f1571b.isVipSend()) {
            m913a(false);
            this.showPayNum.setText(getResources().getString(C0376R.string.diamonds_set));
            DialogHelper.INSTANCE.showKeyboardDialog(this, null);
        } else if (this.setDiamonds.getVisibility() == 8) {
            m913a(false);
            this.showPayNum.setText(getResources().getString(C0376R.string.diamonds_set));
            this.setDiamonds.setVisibility(0);
        } else {
            m913a(true);
            m918g();
            this.setDiamonds.setVisibility(8);
        }
    }

    /* synthetic */ void m930g(Object aVoid) throws Exception {
        m910a(-1);
    }

    /* synthetic */ void m929f(Object aVoid) throws Exception {
        Intent intent = new Intent();
        Bundle args = new Bundle();
        args.putString("PARAM_KEY_UID", this.f1572d);
        if (this.f1576h != null) {
            args.putStringArray("PARAM_KEY_SELECTED_FRIENDS", this.f1576h);
        }
        args.putBoolean("PARAM_KEY_VIDEO_TO_CONTACTS", true);
        intent.putExtra("PARAMS_KEY", args);
        intent.setClass(this, AddSendFriendsActivity.class);
        ActivityManager.getInstance().currentActivity().startActivityForResult(intent, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    /* synthetic */ void m928e(Object aVoid) throws Exception {
        String[] msgIds = new String[this.f1576h.length];
        for (int i = 0; i < this.f1576h.length; i++) {
            msgIds[i] = "local_" + UUID.randomUUID().toString();
        }
        Realm outRealm = Realm.getDefaultInstance();
        Transaction lambdaFactory$ = VideoRecordResultActivity$$Lambda$9.lambdaFactory$(this, msgIds);
        outRealm.getClass();
        outRealm.executeTransactionAsync(lambdaFactory$, VideoRecordResultActivity$$Lambda$10.lambdaFactory$(outRealm), VideoRecordResultActivity$$Lambda$11.lambdaFactory$(outRealm));
        EventBus.getDefault().post(new UploadStateEvent(msgIds, this.f1578j, this.f1577i, this.f1576h, this.f1575g, (int) this.f1581m, this.f1583o));
        HashSet<Class> finishActivity = new HashSet();
        finishActivity.add(VideoRecordActivity.class);
        finishActivity.add(VideoRecordResultActivity.class);
        ActivityManager.getInstance().popAndFinishWithClass(finishActivity);
    }

    /* synthetic */ void m923a(String[] msgIds, Realm realm) {
        int i = 0;
        for (String uid : this.f1576h) {
            f1570a.m263e("uid=" + uid);
            UserChatSession session = (UserChatSession) realm.where(UserChatSession.class).equalTo("sessionId", uid).findFirst();
            if (session == null || !session.isValid()) {
                session = (UserChatSession) realm.createObject(UserChatSession.class, uid);
            }
            UserChatMessage chatMsg = (UserChatMessage) realm.createObject(UserChatMessage.class, msgIds[i]);
            chatMsg.setMsgType(4);
            chatMsg.setTimestamp(System.currentTimeMillis());
            chatMsg.setVideoId(this.f1577i);
            chatMsg.setNeedPay(this.f1575g != 0);
            chatMsg.setDuring(this.f1581m);
            chatMsg.setVideoThumb(this.f1578j);
            chatMsg.setVideoPath(this.f1577i);
            chatMsg.setUid(uid);
            chatMsg.setCoin(this.f1575g);
            chatMsg.setUploadState(1);
            session.getMessages().add(chatMsg);
            session.setTimestamp(System.currentTimeMillis());
            i++;
        }
        Iterator it = realm.where(TikiUser.class).in(Oauth2AccessToken.KEY_UID, this.f1576h).findAll().iterator();
        while (it.hasNext()) {
            TikiUser tikiUser = (TikiUser) it.next();
            if (tikiUser != null && tikiUser.isValid()) {
                tikiUser.setLastMessage(ChatApp.getInstance().getString(C0376R.string.sight_video));
                tikiUser.setLastMessageTime(System.currentTimeMillis());
            }
        }
    }

    /* synthetic */ void m927d(Object aVoid) throws Exception {
        m913a(true);
        m918g();
        this.setDiamonds.setVisibility(8);
    }

    /* synthetic */ void m926c(Object aVoid) throws Exception {
        this.f1575g = 0;
        this.notVipDiamondsNum.setText(getResources().getString(C0376R.string.show_pay_tip));
        this.diamondFreeIcon.setImageResource(C0376R.mipmap.icon_gouxuan);
        this.diamondFreeText.setTextColor(getResources().getColor(C0376R.color.colorPrimary));
        this.diamondOneIcon.setImageResource(C0376R.mipmap.icon_kexuan);
        this.diamondOneText.setTextColor(getResources().getColor(C0376R.color.keyboard_text_hint));
        this.diamondTwoIcon.setImageResource(C0376R.mipmap.icon_kexuan);
        this.diamondTwoText.setTextColor(getResources().getColor(C0376R.color.keyboard_text_hint));
    }

    /* synthetic */ void m925b(Object aVoid) throws Exception {
        this.f1575g = 1;
        this.notVipDiamondsNum.setText(getResources().getString(C0376R.string.diamonds_tip, new Object[]{Integer.valueOf(this.f1575g)}));
        this.diamondFreeIcon.setImageResource(C0376R.mipmap.icon_kexuan);
        this.diamondFreeText.setTextColor(getResources().getColor(C0376R.color.keyboard_text_hint));
        this.diamondOneIcon.setImageResource(C0376R.mipmap.icon_gouxuan);
        this.diamondOneText.setTextColor(getResources().getColor(C0376R.color.colorPrimary));
        this.diamondTwoIcon.setImageResource(C0376R.mipmap.icon_kexuan);
        this.diamondTwoText.setTextColor(getResources().getColor(C0376R.color.keyboard_text_hint));
    }

    /* synthetic */ void m922a(Object aVoid) throws Exception {
        this.f1575g = 2;
        this.notVipDiamondsNum.setText(getResources().getString(C0376R.string.diamonds_tip, new Object[]{Integer.valueOf(this.f1575g)}));
        this.diamondFreeIcon.setImageResource(C0376R.mipmap.icon_kexuan);
        this.diamondFreeText.setTextColor(getResources().getColor(C0376R.color.keyboard_text_hint));
        this.diamondOneIcon.setImageResource(C0376R.mipmap.icon_kexuan);
        this.diamondOneText.setTextColor(getResources().getColor(C0376R.color.keyboard_text_hint));
        this.diamondTwoIcon.setImageResource(C0376R.mipmap.icon_gouxuan);
        this.diamondTwoText.setTextColor(getResources().getColor(C0376R.color.colorPrimary));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInputDiamondsEvent(InputDiamondsEvent event) {
        this.f1575g = event.f510a;
    }

    private void m918g() {
        if (this.f1575g == 0) {
            this.showPayNum.setText(getResources().getString(C0376R.string.show_pay_tip));
            return;
        }
        this.showPayNum.setText(getResources().getString(C0376R.string.diamonds_tip, new Object[]{Integer.valueOf(this.f1575g)}));
    }

    private void m910a(int result) {
        setResult(result);
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInputDiamondsDialogDismissEvent(InputDiamondsDialogDismissEvent event) {
        m918g();
        m913a(true);
    }

    private void m913a(boolean flag) {
        if (flag) {
            this.close.setClickable(true);
            this.sendVideoRecord.setClickable(true);
            this.shadow.setVisibility(8);
            this.arrow.setBackgroundResource(C0376R.mipmap.icon_arrow_down);
            return;
        }
        this.close.setClickable(false);
        this.sendVideoRecord.setClickable(false);
        this.shadow.setVisibility(0);
        this.arrow.setBackgroundResource(C0376R.mipmap.icon_arrow_up);
    }

    private void m919h() {
        if (this.f1571b.isVipSend()) {
            this.vipFriendLayout.setVisibility(0);
            this.notVipFriendLayout.setVisibility(8);
            return;
        }
        this.vipFriendLayout.setVisibility(8);
        this.notVipFriendLayout.setVisibility(0);
        if (this.f1574f != null && this.f1574f.size() > 0) {
            TikiUser user = (TikiUser) this.f1574f.first();
            this.notVipUserName.setText(user.getMark());
            if (TextUtils.isEmpty(user.getAvatar()) || !user.getAvatar().startsWith("http://")) {
                this.avatar.setImageURI("res://" + ChatApp.getInstance().getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
            } else {
                FrescoUtil.setImageURI(this.avatar, ResourceUrlUtil.getNormalAvatar(user.getAvatar(), DisplayUtil.dip2px(36.0f)));
            }
        }
    }

    protected int mo2117b() {
        return 0;
    }

    protected void onResume() {
        super.onResume();
        f1570a.m263e("mVideoView.resume");
        this.mVideoView.resume();
    }

    protected void onPause() {
        f1570a.m263e("mVideoView.suspend");
        this.mVideoView.suspend();
        super.onPause();
    }

    protected void onDestroy() {
        ((AudioManager) getSystemService("audio")).setMode(3);
        f1570a.m263e("mVideoView.release");
        this.mVideoView.release(true);
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
            f1570a.m261d("onActivityResult:RC_ADD_FRIEND");
            if (data != null && data.getExtras() != null) {
                this.f1583o = data.getExtras().getBoolean("PARAM_KEY_VIDEO_TO_ALL");
                this.f1576h = data.getExtras().getStringArray("PARAM_KEY_VIDEO_RECORD_SELECT_USERS");
                if (this.f1576h != null && this.f1576h.length > 0) {
                    this.f1573e.setData(m909a(this.f1576h));
                    return;
                }
                return;
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private RealmResults<TikiUser> m909a(String[] selectUsers) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<TikiUser> users = realm.where(TikiUser.class).in(Oauth2AccessToken.KEY_UID, selectUsers).findAll();
        realm.close();
        return users;
    }
}
