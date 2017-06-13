package com.buddy.tiki.ui.fragment;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.event.UserEvent.ModifyProfileEvent;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.IconHint;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.user.TikiAdministrator;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.service.view.RushNotificationManager;
import com.buddy.tiki.ui.activity.FragmentContainerActivity;
import com.buddy.tiki.ui.activity.ProfileActivity;
import com.buddy.tiki.ui.activity.SettingActivity;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.ResourceUrlUtil;
import com.buddy.tiki.util.RippleUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.view.WrapContentDraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.realm.Realm;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.swresample;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.parceler.Parcels;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class YouFragment extends BaseFragment {
    private static final TikiLog f2300a = TikiLog.getInstance(YouFragment.class.getSimpleName());
    private User f2301c;
    private CollapsingToolbarLayoutState f2302d = CollapsingToolbarLayoutState.COLLAPSED;
    private ConfigInfo f2303e;
    private OperInfo f2304f;
    private DisposableObserver<ConfigInfo> f2305g;
    private DisposableObserver<OperInfo> f2306h;
    private boolean f2307i;
    private YouFragmentEvent f2308j;
    private boolean f2309k = false;
    @BindView(2131820818)
    AppBarLayout mAppbarLayout;
    @BindView(2131820823)
    AppCompatImageView mBackVideoBtn;
    @BindView(2131820822)
    View mBackVideoLayout;
    @BindView(2131820819)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(2131820824)
    View mDiamond;
    @BindView(2131820826)
    AppCompatTextView mDiamondNum;
    @BindView(2131821001)
    View mExchangeStoreLayout;
    @BindView(2131820843)
    WrapContentDraweeView mGameBanner;
    @BindView(2131820841)
    View mGameButton;
    @BindView(2131820844)
    AppCompatTextView mGameTitle;
    @BindView(2131820839)
    View mInviteFriend;
    @BindView(2131820840)
    WrapContentDraweeView mInviteFriendBanner;
    @BindView(2131820833)
    View mPalIndex;
    @BindView(2131820835)
    WrapContentDraweeView mPalIndexBanner;
    @BindView(2131820846)
    WrapContentDraweeView mPromotionBanner;
    @BindView(2131820845)
    View mPromotionCodeView;
    @BindView(2131820838)
    WrapContentDraweeView mRankBanner;
    @BindView(2131820836)
    View mRankButton;
    @BindView(2131820697)
    SwipeRefreshLayout mRootLayout;
    @BindView(2131821002)
    View mRushModeLayout;
    @BindView(2131821003)
    SwitchCompat mRushModeSwitch;
    @BindView(2131820847)
    AppCompatTextView mSettingButton;
    @BindView(2131820827)
    View mTCoin;
    @BindView(2131821004)
    View mTMiLayout;
    @BindView(2131821005)
    AppCompatTextView mTMiNum;
    @BindView(2131820829)
    AppCompatTextView mTNum;
    @BindView(2131820821)
    AppCompatTextView mTikiNum;
    @BindView(2131820688)
    Toolbar mToolbar;
    @BindView(2131820794)
    SimpleDraweeView mUserAvatar;
    @BindView(2131820820)
    AppCompatTextView mUserInfo;
    @BindView(2131820755)
    AppCompatTextView mUserNick;

    public interface YouFragmentEvent {
        void onProfileBackToCall();
    }

    class C04751 extends DisposableObserver<ConfigInfo> {
        final /* synthetic */ YouFragment f2297a;

        C04751(YouFragment this$0) {
            this.f2297a = this$0;
        }

        public void onNext(ConfigInfo configInfo) {
            this.f2297a.f2303e = configInfo;
        }

        public void onError(Throwable e) {
        }

        public void onComplete() {
        }
    }

    class C04762 extends DisposableObserver<OperInfo> {
        final /* synthetic */ YouFragment f2298a;

        C04762(YouFragment this$0) {
            this.f2298a = this$0;
        }

        public void onNext(OperInfo operInfo) {
            this.f2298a.f2304f = operInfo;
        }

        public void onError(Throwable e) {
        }

        public void onComplete() {
        }
    }

    enum CollapsingToolbarLayoutState {
        COLLAPSED,
        INTERNEDIATE,
        EXPANDED
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_you;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1477c();
        m1478d();
        m1480f();
    }

    private void m1477c() {
        this.mRootLayout.setOnRefreshListener(YouFragment$$Lambda$1.lambdaFactory$(this));
        this.mRootLayout.setProgressViewOffset(true, -20, 100);
        m1203h().setSupportActionBar(this.mToolbar);
        if (m1203h().getSupportActionBar() != null) {
            m1203h().getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        this.f2307i = PreferenceUtil.getWorkingStatus();
        this.mRushModeSwitch.setChecked(this.f2307i);
        RippleUtil.setRippleBackground(m1203h(), this.mBackVideoLayout);
    }

    /* synthetic */ void m1494b() {
        if (!this.f2309k && this.f2302d == CollapsingToolbarLayoutState.EXPANDED) {
            this.f2309k = true;
            m1479e();
        }
    }

    private void m1478d() {
        this.mAppbarLayout.addOnOffsetChangedListener(YouFragment$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.mCollapsingToolbarLayout).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.mBackVideoLayout).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.mDiamond).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$5.lambdaFactory$(this));
        RxView.clicks(this.mTCoin).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$6.lambdaFactory$(this));
        RxView.clicks(this.mPromotionCodeView).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$7.lambdaFactory$(this));
        RxView.clicks(this.mInviteFriend).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$8.lambdaFactory$(this));
        RxView.clicks(this.mSettingButton).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$9.lambdaFactory$(this));
        RxView.clicks(this.mGameButton).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$10.lambdaFactory$(this));
        RxView.clicks(this.mPalIndex).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$11.lambdaFactory$(this));
        RxView.clicks(this.mRankButton).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$12.lambdaFactory$(this));
        RxView.clicks(this.mTMiLayout).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$13.lambdaFactory$(this));
        RxView.clicks(this.mExchangeStoreLayout).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(YouFragment$$Lambda$14.lambdaFactory$(this));
        RxCompoundButton.checkedChanges(this.mRushModeSwitch).subscribe(YouFragment$$Lambda$15.lambdaFactory$(this));
    }

    /* synthetic */ void m1486a(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset >= 0) {
            this.mRootLayout.setEnabled(true);
        } else {
            this.mRootLayout.setEnabled(false);
        }
        if (verticalOffset == 0) {
            if (this.f2302d != CollapsingToolbarLayoutState.EXPANDED) {
                this.f2302d = CollapsingToolbarLayoutState.EXPANDED;
                this.mUserNick.setVisibility(8);
            }
        } else if (Math.abs(verticalOffset) >= this.mAppbarLayout.getTotalScrollRange()) {
            if (this.f2302d != CollapsingToolbarLayoutState.COLLAPSED) {
                this.mUserNick.setVisibility(0);
                this.f2302d = CollapsingToolbarLayoutState.COLLAPSED;
            }
        } else if (this.f2302d != CollapsingToolbarLayoutState.INTERNEDIATE) {
            if (this.f2302d == CollapsingToolbarLayoutState.COLLAPSED) {
                this.mUserNick.setVisibility(8);
            }
            this.f2302d = CollapsingToolbarLayoutState.INTERNEDIATE;
        }
    }

    /* synthetic */ void m1506l(Object aVoid) throws Exception {
        Bundle args = new Bundle();
        args.putParcelable("PARAM_KEY_USER", Parcels.wrap(this.f2301c));
        m1202a(ProfileActivity.class, args);
    }

    /* synthetic */ void m1505k(Object aVoid) throws Exception {
        if (this.f2308j != null) {
            this.f2308j.onProfileBackToCall();
        }
    }

    /* synthetic */ void m1504j(Object aVoid) throws Exception {
        if (this.f2303e != null && !TextUtils.isEmpty(this.f2303e.getH5DiamondsUrl())) {
            WebBrowserActivity.launchWeb(m1203h(), this.f2303e.getH5DiamondsUrl());
        }
    }

    /* synthetic */ void m1503i(Object aVoid) throws Exception {
        if (this.f2303e != null && !TextUtils.isEmpty(this.f2303e.getH5TikisUrl())) {
            WebBrowserActivity.launchWeb(m1203h(), this.f2303e.getH5TikisUrl());
        }
    }

    /* synthetic */ void m1502h(Object aVoid) throws Exception {
        Bundle args = new Bundle();
        args.putString("PARAM_KEY_FRAGMENT", PromotionFragment.class.getName());
        m1202a(FragmentContainerActivity.class, args);
    }

    /* synthetic */ void m1501g(Object aVoid) throws Exception {
        if (this.f2301c != null && !TextUtils.isEmpty(this.f2301c.getInviteUrl())) {
            WebBrowserActivity.launchWeb(m1203h(), this.f2301c.getInviteUrl());
        }
    }

    /* synthetic */ void m1500f(Object aVoid) throws Exception {
        m1200a(SettingActivity.class);
    }

    /* synthetic */ void m1499e(Object aVoid) throws Exception {
        if (this.f2303e != null && !TextUtils.isEmpty(this.f2303e.getExploreH5Url())) {
            WebBrowserActivity.launchWeb(m1203h(), this.f2303e.getExploreH5Url());
        }
    }

    /* synthetic */ void m1498d(Object aVoid) throws Exception {
        if (this.f2303e != null && !TextUtils.isEmpty(this.f2303e.getTikiexp())) {
            WebBrowserActivity.launchWeb(m1203h(), this.f2303e.getTikiexp());
        }
    }

    /* synthetic */ void m1496c(Object aVoid) throws Exception {
        if (this.f2303e != null && !TextUtils.isEmpty(this.f2303e.getRankingUrl())) {
            WebBrowserActivity.launchWeb(m1203h(), this.f2303e.getRankingUrl());
        }
    }

    /* synthetic */ void m1495b(Object o) throws Exception {
        if (this.f2303e == null || TextUtils.isEmpty(this.f2303e.getTriceUrl())) {
            ToastUtil.getInstance().show(m1203h(), (int) C0376R.string.fetch_data_failed);
        } else {
            WebBrowserActivity.launchWeb(m1203h(), this.f2303e.getTriceUrl());
        }
    }

    /* synthetic */ void m1493a(Object o) throws Exception {
        if (this.f2303e == null || TextUtils.isEmpty(this.f2303e.getExchangeStoreUrl())) {
            ToastUtil.getInstance().show(m1203h(), (int) C0376R.string.fetch_data_failed);
        } else {
            WebBrowserActivity.launchWeb(m1203h(), this.f2303e.getExchangeStoreUrl());
        }
    }

    /* synthetic */ void m1490a(Boolean aBoolean) throws Exception {
        if (this.f2307i != aBoolean.booleanValue()) {
            this.f2307i = aBoolean.booleanValue();
            getDataLayer().getChatManager().setUberWorking(aBoolean.booleanValue()).compose(SchedulersCompat.applyIoSchedulers()).compose(bindUntilEvent(FragmentEvent.DESTROY)).doOnNext(YouFragment$$Lambda$22.lambdaFactory$(this, aBoolean)).subscribe(YouFragment$$Lambda$23.lambdaFactory$(), YouFragment$$Lambda$24.lambdaFactory$(this, aBoolean));
        }
    }

    /* synthetic */ void m1491a(Boolean aBoolean, Boolean result) throws Exception {
        if (!aBoolean.booleanValue()) {
            RushNotificationManager.getInstance().dismiss(getContext());
        }
    }

    static /* synthetic */ void m1475b(Boolean result) throws Exception {
    }

    /* synthetic */ void m1492a(Boolean aBoolean, Throwable throwable) throws Exception {
        boolean z;
        boolean z2 = true;
        f2300a.m264e("working fail", throwable);
        if (aBoolean.booleanValue()) {
            z = false;
        } else {
            z = true;
        }
        this.f2307i = z;
        SwitchCompat switchCompat = this.mRushModeSwitch;
        if (aBoolean.booleanValue()) {
            z2 = false;
        }
        switchCompat.setChecked(z2);
    }

    private void m1479e() {
        DataLayer.getInstance().getUserManager().userRequest(TopConfig.f408a).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(YouFragment$$Lambda$16.lambdaFactory$(this), YouFragment$$Lambda$17.lambdaFactory$(this));
    }

    /* synthetic */ void m1489a(User user) throws Exception {
        m1473a(user, false);
        this.mRootLayout.setRefreshing(false);
        this.f2309k = false;
    }

    /* synthetic */ void m1497c(Throwable throwable) throws Exception {
        f2300a.m264e("loadData: ", throwable);
        m1473a(null, false);
        this.mRootLayout.setRefreshing(false);
        this.f2309k = false;
    }

    private void m1480f() {
        this.f2305g = new C04751(this);
        this.f2306h = new C04762(this);
        getDataLayer().getAppManager().addSubscription(this.f2305g, ConfigInfo.class);
        getDataLayer().getAppManager().addSubscription(this.f2306h, OperInfo.class);
        DataLayer.getInstance().getAppManager().getConfigCache().compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(YouFragment$$Lambda$18.lambdaFactory$(this), YouFragment$$Lambda$19.lambdaFactory$());
        DataLayer.getInstance().getAppManager().getOperInfoCache().compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(YouFragment$$Lambda$20.lambdaFactory$(this), YouFragment$$Lambda$21.lambdaFactory$());
    }

    /* synthetic */ void m1487a(ConfigInfo configInfo) throws Exception {
        this.f2303e = configInfo;
        m1481g();
    }

    static /* synthetic */ void m1476b(Throwable throwable) throws Exception {
    }

    /* synthetic */ void m1488a(OperInfo operInfo) throws Exception {
        this.f2304f = operInfo;
        m1482j();
    }

    static /* synthetic */ void m1474a(Throwable throwable) throws Exception {
    }

    private void m1481g() {
        int i = 0;
        View view = this.mGameButton;
        int i2 = (this.f2303e == null || TextUtils.isEmpty(this.f2303e.getExploreH5Url())) ? 8 : 0;
        view.setVisibility(i2);
        view = this.mPalIndex;
        if (this.f2303e == null || TextUtils.isEmpty(this.f2303e.getTikiexp())) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        view.setVisibility(i2);
        View view2 = this.mRankButton;
        if (this.f2303e == null || TextUtils.isEmpty(this.f2303e.getRankingUrl())) {
            i = 8;
        }
        view2.setVisibility(i);
        if (this.f2303e != null && !TextUtils.isEmpty(this.f2303e.getExploreH5Text())) {
            this.mGameTitle.setText(this.f2303e.getExploreH5Text());
        }
    }

    private void m1482j() {
        if (this.f2304f == null || this.f2304f.getIconHint() == null) {
            m1483k();
            return;
        }
        IconHint iconHint = this.f2304f.getIconHint();
        if (TextUtils.isEmpty(iconHint.getCodeExchangeIcon())) {
            this.mPromotionBanner.setVisibility(8);
        } else {
            this.mPromotionBanner.setVisibility(0);
            FrescoUtil.setImageURI(this.mPromotionBanner, iconHint.getCodeExchangeIcon());
        }
        if (TextUtils.isEmpty(iconHint.getExploreH5Icon())) {
            this.mGameBanner.setVisibility(8);
        } else {
            this.mGameBanner.setVisibility(0);
            FrescoUtil.setImageURI(this.mGameBanner, iconHint.getExploreH5Icon());
        }
        if (TextUtils.isEmpty(iconHint.getInviteH5Icon())) {
            this.mInviteFriendBanner.setVisibility(8);
        } else {
            this.mInviteFriendBanner.setVisibility(0);
            FrescoUtil.setImageURI(this.mInviteFriendBanner, iconHint.getInviteH5Icon());
        }
        if (TextUtils.isEmpty(iconHint.getTikiexpIcon())) {
            this.mPalIndexBanner.setVisibility(8);
        } else {
            this.mPalIndexBanner.setVisibility(0);
            FrescoUtil.setImageURI(this.mPalIndexBanner, iconHint.getTikiexpIcon());
        }
        if (TextUtils.isEmpty(iconHint.getRankingIcon())) {
            this.mRankBanner.setVisibility(8);
            return;
        }
        this.mRankBanner.setVisibility(0);
        FrescoUtil.setImageURI(this.mRankBanner, iconHint.getRankingIcon());
    }

    private void m1483k() {
        this.mGameBanner.setVisibility(8);
        this.mPromotionBanner.setVisibility(8);
        this.mInviteFriendBanner.setVisibility(8);
        this.mPalIndexBanner.setVisibility(8);
    }

    private void m1473a(User user, boolean fromCache) {
        if (user == null) {
            Realm realm = Realm.getDefaultInstance();
            TikiAdministrator administrator = (TikiAdministrator) realm.where(TikiAdministrator.class).findFirst();
            User tmp = null;
            if (administrator != null && administrator.isLoaded() && administrator.isValid()) {
                tmp = new User(administrator);
            }
            realm.close();
            if (tmp != null) {
                m1473a(tmp, true);
                return;
            }
            return;
        }
        this.f2301c = user;
        FrescoUtil.setImageURI(this.mUserAvatar, ResourceUrlUtil.getNormalAvatar(user.getAvatar(), DisplayUtil.dip2px(60.0f)));
        this.mUserNick.setText(user.getNick());
        this.mUserInfo.setText(user.getNick());
        this.mTikiNum.setText(getString(C0376R.string.tiki_num_param, new Object[]{Long.valueOf(user.getTid())}));
        if (this.f2301c.isUber()) {
            this.mRushModeLayout.setVisibility(0);
            this.mTMiLayout.setVisibility(0);
        } else {
            this.mRushModeLayout.setVisibility(8);
            this.mTMiLayout.setVisibility(8);
        }
        Drawable drawable = null;
        switch (user.getGender()) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                this.mUserInfo.setCompoundDrawablesRelative(null, null, null, null);
                break;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                try {
                    drawable = ContextCompat.getDrawable(m1203h(), C0376R.mipmap.icon_male_suffix);
                } catch (NotFoundException e) {
                    f2300a.m264e("resource not found", e);
                }
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    this.mUserInfo.setCompoundDrawablesRelative(null, null, drawable, null);
                    break;
                }
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                try {
                    drawable = ContextCompat.getDrawable(m1203h(), C0376R.mipmap.icon_female_suffix);
                } catch (NotFoundException e2) {
                    f2300a.m264e("resource not found", e2);
                }
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    this.mUserInfo.setCompoundDrawablesRelative(null, null, drawable, null);
                    break;
                }
                break;
        }
        if (!fromCache) {
            this.mDiamondNum.setText(getString(C0376R.string.diamond_num_format, new Object[]{Long.valueOf(user.getDiamonds())}));
            this.mTNum.setText(getString(C0376R.string.t_currency_num_format, new Object[]{Long.valueOf(user.getTikis())}));
            this.f2307i = this.f2301c.isWorking();
            this.mRushModeSwitch.setChecked(this.f2301c.isWorking());
            this.mTMiNum.setText(String.valueOf(user.getTrice()));
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onNickChangeEvent(ModifyProfileEvent event) {
        if (event != null) {
            String nick = event.f512a;
            int gender = event.f513b;
            String avatar = event.f514c;
            EventBus.getDefault().removeStickyEvent(event);
            if (this.f2301c != null) {
                this.f2301c.setNick(nick);
                this.mUserNick.setText(nick);
                this.mUserInfo.setText(nick);
                this.f2301c.setGender(gender);
                Drawable drawable;
                switch (gender) {
                    case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                        this.mUserInfo.setCompoundDrawablesRelative(null, null, null, null);
                        break;
                    case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                        drawable = getResources().getDrawable(C0376R.mipmap.icon_male);
                        if (drawable != null) {
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            this.mUserInfo.setCompoundDrawablesRelative(null, null, drawable, null);
                            break;
                        }
                        break;
                    case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                        drawable = getResources().getDrawable(C0376R.mipmap.icon_female);
                        if (drawable != null) {
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            this.mUserInfo.setCompoundDrawablesRelative(null, null, drawable, null);
                            break;
                        }
                        break;
                }
                if (!TextUtils.isEmpty(avatar)) {
                    FrescoUtil.setImageURI(this.mUserAvatar, ResourceUrlUtil.getNormalAvatar(avatar, DisplayUtil.dip2px(60.0f)));
                }
            }
        }
    }

    public void onResume() {
        super.onResume();
        m1479e();
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f2308j = (YouFragmentEvent) activity;
        } catch (Exception e) {
            f2300a.m263e("get event fail");
        }
    }

    public void onDestroy() {
        this.f2305g.dispose();
        this.f2306h.dispose();
        super.onDestroy();
    }
}
