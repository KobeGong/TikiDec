package com.buddy.tiki.ui.fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.event.CallEvent.StopCountDownEvent;
import com.buddy.tiki.event.CallEvent.UpdateStatusEvent;
import com.buddy.tiki.event.UserEvent.AcceptFriendEvent;
import com.buddy.tiki.event.UserEvent.DeleteFriendEvent;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.MatchLimits;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.BitsUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.UserChatRealmHelper;
import com.buddy.tiki.view.BannedView;
import com.buddy.tiki.view.BlockView;
import com.buddy.tiki.view.RequestTextView;
import com.buddy.tiki.view.RoundTextView;
import com.buddy.tiki.view.UpwardTipsTextView;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.swscale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class CallMainFragment extends BaseFragment {
    private static final TikiLog f2032a = TikiLog.getInstance(CallMainFragment.class.getSimpleName());
    private OnCallEvents f2033c;
    private volatile boolean f2034d = true;
    private User f2035e;
    private ArrayMap<User, Boolean> f2036f = new ArrayMap();
    private boolean f2037g = true;
    private boolean f2038h;
    @BindView(2131820926)
    UpwardTipsTextView mApplyTips;
    @BindView(2131820935)
    BannedView mBannedView;
    @BindView(2131820934)
    BlockView mBlockContent;
    @BindView(2131820933)
    AppCompatImageView mFlipBtn;
    @BindView(2131820930)
    RoundTextView mFriendAction;
    @BindView(2131820775)
    AppCompatTextView mNick;
    @BindView(2131820929)
    AppCompatTextView mRegionAndGenderInfo;
    @BindView(2131820931)
    RoundTextView mReportBtn;
    @BindView(2131820932)
    RequestTextView mRequestTips;

    public interface OnCallEvents {
        void onCameraSwitch();

        void onReport();

        void onShowGiftDialog();
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1217b();
        m1218c();
        EventBus.getDefault().register(this);
    }

    private void m1217b() {
        if (this.f2038h) {
            this.mFlipBtn.setVisibility(0);
        } else {
            this.mFlipBtn.setVisibility(8);
        }
    }

    private void m1218c() {
        RxView.clicks(this.mFlipBtn).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).filter(CallMainFragment$$Lambda$1.lambdaFactory$(this)).subscribe(CallMainFragment$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.mReportBtn).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(CallMainFragment$$Lambda$3.lambdaFactory$(this));
    }

    /* synthetic */ boolean m1234e(Object aVoid) throws Exception {
        return this.f2033c != null;
    }

    /* synthetic */ void m1233d(Object aVoid) throws Exception {
        this.f2033c.onCameraSwitch();
    }

    /* synthetic */ void m1231c(Object aVoid) throws Exception {
        if (this.f2035e != null) {
            this.f2033c.onReport();
        }
    }

    public void resetFriendButton() {
        if (isAdded()) {
            this.f2035e = null;
            this.mRequestTips.setVisibility(8);
            this.mFriendAction.setVisibility(8);
            this.mReportBtn.setVisibility(8);
            this.mApplyTips.setVisibility(8);
            this.mRegionAndGenderInfo.setVisibility(8);
            this.f2037g = true;
        }
    }

    public void processFriendButton() {
        if (!isAdded()) {
            return;
        }
        if (this.f2035e == null) {
            f2032a.m261d("resetFriendButton");
            resetFriendButton();
            return;
        }
        this.mFriendAction.setVisibility(0);
        this.mReportBtn.setVisibility(0);
        if (BitsUtil.isFriend(this.f2035e.getRelation())) {
            m1216a(5);
            return;
        }
        m1216a(-1);
        RxView.clicks(this.mFriendAction).compose(bindToLifecycle()).subscribeOn(AndroidSchedulers.mainThread()).flatMap(CallMainFragment$$Lambda$4.lambdaFactory$(this)).observeOn(AndroidSchedulers.mainThread()).doOnNext(CallMainFragment$$Lambda$5.lambdaFactory$(this)).observeOn(Schedulers.io()).flatMap(CallMainFragment$$Lambda$6.lambdaFactory$(this)).observeOn(AndroidSchedulers.mainThread()).subscribe(CallMainFragment$$Lambda$7.lambdaFactory$(this), CallMainFragment$$Lambda$8.lambdaFactory$(this));
    }

    /* synthetic */ ObservableSource m1227b(Object aVoid) throws Exception {
        return Observable.just(Boolean.valueOf(this.f2037g));
    }

    /* synthetic */ void m1232d(Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue()) {
            this.mApplyTips.setVisibility(0);
            if (this.f2033c != null) {
                this.f2033c.onShowGiftDialog();
                return;
            }
            return;
        }
        this.mFriendAction.setEnabled(false);
    }

    /* synthetic */ ObservableSource m1230c(Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue()) {
            return Observable.empty();
        }
        return getDataLayer().getFollowManager().applyFriendAction(this.f2035e);
    }

    /* synthetic */ void m1228b(Boolean result) throws Exception {
        if (result != null) {
            m1216a(1);
        }
    }

    /* synthetic */ void m1229b(Throwable throwable) throws Exception {
        m1216a(-1);
    }

    public void receiveFriendRequest(String applyId, String applyUid) {
        if (this.f2035e != null && applyUid.equals(this.f2035e.getUid())) {
            this.mApplyTips.setVisibility(8);
            m1216a(3);
            RxView.clicks(this.mFriendAction).compose(bindToLifecycle()).doOnNext(CallMainFragment$$Lambda$9.lambdaFactory$(this)).observeOn(Schedulers.io()).flatMap(CallMainFragment$$Lambda$10.lambdaFactory$(this, applyId)).flatMap(CallMainFragment$$Lambda$11.lambdaFactory$(this)).observeOn(AndroidSchedulers.mainThread()).subscribe(CallMainFragment$$Lambda$12.lambdaFactory$(this, applyUid), CallMainFragment$$Lambda$13.lambdaFactory$(this));
        }
    }

    /* synthetic */ void m1224a(Object aVoid) throws Exception {
        this.mFriendAction.setEnabled(false);
    }

    /* synthetic */ ObservableSource m1222a(String applyId, Object aVoid) throws Exception {
        return getDataLayer().getFollowManager().passApplysAction(null, new String[]{applyId});
    }

    /* synthetic */ ObservableSource m1221a(Boolean aBoolean) throws Exception {
        return getDataLayer().getFollowManager().insertUser(this.f2035e);
    }

    /* synthetic */ void m1225a(String applyUid, Boolean aBoolean1) throws Exception {
        f2032a.m263e("onFinish");
        int applyNum = PreferenceUtil.getApplyNumber();
        if (applyNum > 0) {
            PreferenceUtil.setApplyNumber(applyNum - 1);
        }
        UserChatRealmHelper.getInstance().updateSession(applyUid);
        m1216a(5);
        EventBus.getDefault().post(new StopCountDownEvent(new String[]{applyUid}));
    }

    /* synthetic */ void m1226a(Throwable throwable) throws Exception {
        f2032a.m264e("receiveFriendRequest: ", throwable);
        m1216a(3);
    }

    private void m1216a(int status) {
        if (isAdded()) {
            Drawable drawable;
            switch (status) {
                case avutil.AV_SAMPLE_FMT_NONE /*-1*/:
                    this.mFriendAction.setEnabled(true);
                    this.mFriendAction.setText(C0376R.string.add_friend);
                    drawable = ContextCompat.getDrawable(m1203h(), C0376R.drawable.icon_friend_add);
                    if (drawable != null) {
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        this.mFriendAction.setCompoundDrawablesRelative(drawable, null, null, null);
                    }
                    this.mRequestTips.setVisibility(8);
                    return;
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    this.mFriendAction.setEnabled(false);
                    this.mFriendAction.setText(C0376R.string.request_sent);
                    drawable = ContextCompat.getDrawable(m1203h(), C0376R.drawable.icon_friend_apply);
                    if (drawable != null) {
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        this.mFriendAction.setCompoundDrawablesRelative(drawable, null, null, null);
                    }
                    this.mRequestTips.setVisibility(8);
                    return;
                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                    this.mFriendAction.setEnabled(true);
                    this.mFriendAction.setText(C0376R.string.request_received);
                    drawable = ContextCompat.getDrawable(m1203h(), C0376R.drawable.icon_friend_add);
                    if (drawable != null) {
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        this.mFriendAction.setCompoundDrawablesRelative(drawable, null, null, null);
                    }
                    this.mRequestTips.setVisibility(0);
                    return;
                case swscale.SWS_CS_SMPTE170M /*5*/:
                    this.mFriendAction.setEnabled(false);
                    this.mFriendAction.setText(C0376R.string.have_been_friend);
                    drawable = ContextCompat.getDrawable(m1203h(), C0376R.drawable.icon_friend_adopt);
                    if (drawable != null) {
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        this.mFriendAction.setCompoundDrawablesRelative(drawable, null, null, null);
                    }
                    this.mRequestTips.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_call_main;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f2033c = (OnCallEvents) activity;
        } catch (Exception e) {
        }
    }

    @UiThread
    public void updateStatusEvent(UpdateStatusEvent updateStatusEvent) {
        this.f2034d = updateStatusEvent.f496b;
        this.f2035e = updateStatusEvent.f495a;
        if (this.f2035e == null) {
            this.f2037g = true;
            this.f2036f.clear();
        } else {
            this.f2036f.put(this.f2035e, Boolean.valueOf(false));
        }
        if (isAdded()) {
            if (this.f2035e != null) {
                String gender;
                this.mNick.setText(this.f2035e.getMark());
                this.mNick.setEnabled(true);
                if (this.f2035e.getGender() == 2) {
                    gender = "; " + getResources().getString(C0376R.string.female);
                } else if (this.f2035e.getGender() == 1) {
                    gender = "; " + getResources().getString(C0376R.string.male);
                } else {
                    gender = "; " + getResources().getString(C0376R.string.unknown_net_error);
                }
                this.mRegionAndGenderInfo.setVisibility(0);
                this.mRegionAndGenderInfo.setText(this.f2035e.getAreaflag() + " " + this.f2035e.getAreaName() + gender);
            } else {
                this.mNick.setText(BuildConfig.VERSION_NAME);
                this.mNick.setEnabled(false);
                this.mRegionAndGenderInfo.setText(BuildConfig.VERSION_NAME);
            }
            processFriendButton();
        }
    }

    public void initCallFriend() {
        this.f2038h = true;
        this.mNick.setVisibility(4);
        this.mRegionAndGenderInfo.setVisibility(8);
        this.mFriendAction.setVisibility(4);
        showBlockContent(null, 8);
        this.mFlipBtn.setVisibility(0);
    }

    public void showSendGiftTips() {
        if (this.f2035e != null) {
            Boolean aBoolean = (Boolean) this.f2036f.get(this.f2035e);
            if (aBoolean != null && !aBoolean.booleanValue()) {
                this.f2036f.put(this.f2035e, Boolean.valueOf(true));
            }
        }
    }

    public void showBlockContent(MatchLimits limits, int visibility) {
        this.mBlockContent.setVisibility(visibility);
        if (limits != null && visibility == 0) {
            this.mBlockContent.fillData(m1203h(), limits);
        }
    }

    public boolean isBlocked() {
        return this.mBlockContent != null && this.mBlockContent.getVisibility() == 0;
    }

    public void showBannedContent(String content, String url) {
        this.mBannedView.setBannedContent(content, url);
    }

    public void hideBannedContent() {
        this.mBannedView.hideBannedContent();
    }

    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteFriendEvent(DeleteFriendEvent event) {
        if (event != null && !TextUtils.isEmpty(event.f509a) && this.f2035e != null && event.f509a.equals(this.f2035e.getUid())) {
            this.f2035e.setRelation(0);
            this.mRequestTips.setVisibility(8);
            processFriendButton();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAcceptFriendEvent(AcceptFriendEvent event) {
        if (event != null && event.f508b != null && event.f508b.length == 1 && !TextUtils.isEmpty(event.f508b[0]) && this.f2035e != null && event.f508b[0].equals(this.f2035e.getUid())) {
            EventBus.getDefault().post(new StopCountDownEvent(event.f508b));
            this.f2035e.setRelation(4);
            this.mRequestTips.setVisibility(8);
            this.mApplyTips.setVisibility(8);
            this.f2037g = true;
            m1219d();
            processFriendButton();
        }
    }

    private void m1219d() {
        this.f2036f.clear();
    }

    public void haveSendGift() {
        this.f2037g = false;
        if (isAdded()) {
            this.mApplyTips.setVisibility(8);
        }
    }
}
