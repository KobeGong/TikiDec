package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import butterknife.BindString;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;

public class AboutActivity extends BaseActivity {
    @BindView(2131820688)
    Toolbar mToolbar;
    @BindView(2131820689)
    AppCompatTextView mVersionCode;
    @BindString(2131296587)
    String mVersionName;

    protected int mo2115a() {
        return C0376R.layout.activity_about;
    }

    protected int mo2117b() {
        return 0;
    }

    protected void onActivityCreate(Bundle savedInstanceState) {
        m433c();
        m434d();
    }

    private void m433c() {
        this.mVersionCode.setText(String.format(this.mVersionName, new Object[]{"1.2.11"}));
    }

    private void m434d() {
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(AboutActivity$$Lambda$1.lambdaFactory$(this));
    }

    /* synthetic */ void m437a(Object aVoid) throws Exception {
        removeFragment();
    }
}
