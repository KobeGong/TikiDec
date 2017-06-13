package com.buddy.tiki.ui.fragment;

import android.content.pm.ApplicationInfo;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.IconHint;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.ui.activity.AboutActivity;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.view.WrapContentDraweeView;
import com.igexin.sdk.PushManager;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SettingFragment extends BaseFragment {
    private static final TikiLog f2214a = TikiLog.getInstance("SettingFragment");
    private ConfigInfo f2215c;
    private OperInfo f2216d;
    @BindView(2131820993)
    View mAboutButton;
    @BindView(2131820989)
    AppCompatTextView mAgreementButton;
    @BindView(2131820992)
    AppCompatTextView mDataUsage;
    @BindView(2131820990)
    View mDataUsageLayout;
    @BindView(2131820988)
    WrapContentDraweeView mFeedbackBanner;
    @BindView(2131820987)
    View mFeedbackButton;
    @BindView(2131820994)
    AppCompatTextView mLogoutButton;
    @BindView(2131820985)
    AppCompatTextView mModifyProfileButton;
    @BindView(2131820986)
    SwitchCompat mNotificationButton;
    @BindView(2131820977)
    ViewGroup mSpringLayout;
    @BindView(2131820688)
    Toolbar mToolbar;
    @BindView(2131820689)
    AppCompatTextView mVersionCode;

    protected int mo2192a() {
        return C0376R.layout.fragment_setting;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1394b();
        m1397d();
        m1399f();
    }

    private void m1394b() {
        this.mVersionCode.setText(getString(C0376R.string.tiki_version_name, new Object[]{"1.2.11"}));
        this.mNotificationButton.setChecked(PreferenceUtil.getPushTurnStatus());
        if (m1396c() < 0.0f) {
            this.mDataUsage.setText(C0376R.string.fail_fetch_data);
            return;
        }
        this.mDataUsage.setText(String.format(Locale.getDefault(), "%.2fMB", new Object[]{Float.valueOf(totalUsage)}));
    }

    private float m1396c() {
        for (ApplicationInfo app : m1203h().getPackageManager().getInstalledApplications(0)) {
            if (app.packageName.equals(m1203h().getPackageName())) {
                return (((float) ((TrafficStats.getUidTxBytes(app.uid) == -1 ? 0 : TrafficStats.getUidTxBytes(app.uid)) + (TrafficStats.getUidRxBytes(app.uid) == -1 ? 0 : TrafficStats.getUidRxBytes(app.uid)))) / 1024.0f) / 1024.0f;
            }
        }
        return -1.0f;
    }

    private void m1397d() {
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(SettingFragment$$Lambda$1.lambdaFactory$(this));
        RxView.clicks(this.mFeedbackButton).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(SettingFragment$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.mAboutButton).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(SettingFragment$$Lambda$3.lambdaFactory$(this));
        RxCompoundButton.checkedChanges(this.mNotificationButton).compose(bindToLifecycle()).subscribe(SettingFragment$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.mLogoutButton).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(SettingFragment$$Lambda$5.lambdaFactory$(this));
        RxView.clicks(this.mAgreementButton).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(SettingFragment$$Lambda$6.lambdaFactory$(this));
        RxView.clicks(this.mModifyProfileButton).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(SettingFragment$$Lambda$7.lambdaFactory$(this));
        RxView.clicks(this.mDataUsageLayout).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(SettingFragment$$Lambda$8.lambdaFactory$(this));
    }

    /* synthetic */ void m1411g(Object aVoid) throws Exception {
        m1204i();
    }

    /* synthetic */ void m1410f(Object aVoid) throws Exception {
        if (this.f2215c != null && !TextUtils.isEmpty(this.f2215c.getFeedbackUrl())) {
            WebBrowserActivity.launchWeb(m1203h(), this.f2215c.getFeedbackUrl());
        }
    }

    /* synthetic */ void m1409e(Object aVoid) throws Exception {
        m1200a(AboutActivity.class);
    }

    /* synthetic */ void m1404a(Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue()) {
            PushManager.getInstance().turnOnPush(m1203h());
        } else {
            PushManager.getInstance().turnOffPush(m1203h());
        }
        PreferenceUtil.setPushTurnFlag(aBoolean.booleanValue());
    }

    /* synthetic */ void m1408d(Object aVoid) throws Exception {
        DialogHelper.INSTANCE.showLogoutDialog(m1203h());
    }

    /* synthetic */ void m1407c(Object aVoid) throws Exception {
        if (this.f2215c != null) {
            WebBrowserActivity.launchWeb(m1203h(), this.f2215c.getPrivacyUrl());
        }
    }

    /* synthetic */ void m1406b(Object aVoid) throws Exception {
        m1199a(new ProfileFragment(), true);
    }

    /* synthetic */ void m1405a(Object aVoid) throws Exception {
        DialogHelper.INSTANCE.showExplainDataUsageDialog(m1203h());
    }

    private void m1398e() {
        if (this.f2216d == null || this.f2216d.getIconHint() == null) {
            this.mFeedbackBanner.setVisibility(8);
            return;
        }
        IconHint iconHint = this.f2216d.getIconHint();
        if (TextUtils.isEmpty(iconHint.getFeedbackIcon())) {
            this.mFeedbackBanner.setVisibility(8);
            return;
        }
        this.mFeedbackBanner.setVisibility(0);
        FrescoUtil.setImageURI(this.mFeedbackBanner, iconHint.getFeedbackIcon());
    }

    private void m1399f() {
        getDataLayer().getAppManager().getConfigCache().compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(SettingFragment$$Lambda$9.lambdaFactory$(this), SettingFragment$$Lambda$10.lambdaFactory$());
        getDataLayer().getAppManager().getOperInfoCache().compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(SettingFragment$$Lambda$11.lambdaFactory$(this), SettingFragment$$Lambda$12.lambdaFactory$());
    }

    /* synthetic */ void m1402a(ConfigInfo configInfo) throws Exception {
        this.f2215c = configInfo;
    }

    static /* synthetic */ void m1395b(Throwable throwable) throws Exception {
    }

    /* synthetic */ void m1403a(OperInfo operInfo) throws Exception {
        this.f2216d = operInfo;
        m1398e();
    }

    static /* synthetic */ void m1393a(Throwable throwable) throws Exception {
    }
}
