package com.buddy.tiki.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.TextUtils;
import android.widget.RadioGroup;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import com.buddy.tiki.ui.dialog.base.BaseRxBottomSheetDialogFragment;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.view.CenterRadioButton;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class FilterMatchDialog extends BaseRxBottomSheetDialogFragment {
    private AppCompatTextView f1890a;
    private RadioGroup f1891b;
    private CenterRadioButton f1892c;
    private CenterRadioButton f1893d;
    private CenterRadioButton f1894e;
    private AppCompatTextView f1895f;
    private AppCompatTextView f1896g;
    private AppCompatTextView f1897h;
    private AppCompatButton f1898i;
    private ConfigInfo f1899j;
    private OperInfo f1900k;
    private User f1901l;
    private int f1902m;
    private MatchFilterCallback f1903n;

    public interface MatchFilterCallback {
        void filterMatch(int i, String str);
    }

    public static FilterMatchDialog newInstance(int type) {
        FilterMatchDialog dialog = new FilterMatchDialog();
        Bundle args = new Bundle();
        args.putInt("PARAM_KEY_GENDER_TYPE", type);
        dialog.setArguments(args);
        return dialog;
    }

    protected void mo2183a(Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            this.f1902m = args.getInt("PARAM_KEY_GENDER_TYPE", 0);
        }
    }

    protected int mo2182a() {
        return C0376R.style.BottomDialogStyle;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(C0376R.layout.dialog_filter_match);
        m1093a(dialog);
        m1097b();
        m1100c();
        return dialog;
    }

    private void m1093a(Dialog dialog) {
        this.f1890a = (AppCompatTextView) dialog.findViewById(C0376R.id.advanced_btn);
        this.f1891b = (RadioGroup) dialog.findViewById(C0376R.id.match_options_group);
        this.f1892c = (CenterRadioButton) dialog.findViewById(C0376R.id.check_random);
        this.f1894e = (CenterRadioButton) dialog.findViewById(C0376R.id.check_female);
        this.f1893d = (CenterRadioButton) dialog.findViewById(C0376R.id.check_male);
        this.f1895f = (AppCompatTextView) dialog.findViewById(C0376R.id.filter_match_tips);
        this.f1896g = (AppCompatTextView) dialog.findViewById(C0376R.id.diamond_num);
        this.f1897h = (AppCompatTextView) dialog.findViewById(C0376R.id.recharge_btn);
        this.f1898i = (AppCompatButton) dialog.findViewById(C0376R.id.ok_btn);
        switch (this.f1902m) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                this.f1893d.setChecked(true);
                return;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                this.f1894e.setChecked(true);
                return;
            default:
                this.f1892c.setChecked(true);
                return;
        }
    }

    private void m1097b() {
        RxView.clicks(this.f1890a).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(FilterMatchDialog$$Lambda$1.lambdaFactory$(this));
        RxRadioGroup.checkedChanges(this.f1891b).compose(bindToLifecycle()).doOnNext(FilterMatchDialog$$Lambda$2.lambdaFactory$(this)).map(FilterMatchDialog$$Lambda$3.lambdaFactory$()).subscribe(RxView.enabled(this.f1898i));
        RxView.clicks(this.f1897h).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(FilterMatchDialog$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.f1898i).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(FilterMatchDialog$$Lambda$5.lambdaFactory$(this));
    }

    /* synthetic */ void m1111c(Object aVoid) throws Exception {
        if (this.f1900k != null && this.f1900k.getRateOptions() != null && !TextUtils.isEmpty(this.f1900k.getRateOptions()[1])) {
            dismiss();
            WebBrowserActivity.launchWeb(getHoldingActivity(), this.f1900k.getRateOptions()[1]);
        }
    }

    /* synthetic */ void m1109b(Integer integer) throws Exception {
        switch (integer.intValue()) {
            case C0376R.id.check_random:
                this.f1895f.setVisibility(8);
                return;
            case C0376R.id.check_female:
            case C0376R.id.check_male:
                this.f1895f.setVisibility(0);
                return;
            default:
                return;
        }
    }

    /* synthetic */ void m1110b(Object aVoid) throws Exception {
        if (this.f1899j != null) {
            dismiss();
            WebBrowserActivity.launchWeb(getHoldingActivity(), this.f1899j.getH5DiamondsUrl());
        }
    }

    /* synthetic */ void m1107a(Object aVoid) throws Exception {
        if (this.f1900k == null || this.f1901l == null) {
            dismiss();
            return;
        }
        int type = 0;
        switch (this.f1891b.getCheckedRadioButtonId()) {
            case C0376R.id.check_random:
                type = 0;
                break;
            case C0376R.id.check_female:
                type = 2;
                break;
            case C0376R.id.check_male:
                type = 1;
                break;
        }
        if (type == 0 || this.f1901l.getDiamonds() >= ((long) this.f1900k.getGenderRate())) {
            if (this.f1903n != null) {
                this.f1903n.filterMatch(type, BuildConfig.VERSION_NAME);
            }
            dismiss();
            return;
        }
        DialogHelper.INSTANCE.showLackInFilterDialog(getHoldingActivity(), FilterMatchDialog$$Lambda$13.lambdaFactory$(this), FilterMatchDialog$$Lambda$14.lambdaFactory$(), FilterMatchDialog$$Lambda$15.lambdaFactory$());
        dismiss();
    }

    /* synthetic */ void m1108b(DialogInterface dialog, int which) {
        WebBrowserActivity.launchWeb(getHoldingActivity(), this.f1899j.getH5DiamondsUrl());
    }

    static /* synthetic */ void m1094a(DialogInterface dialog) {
    }

    static /* synthetic */ void m1095a(DialogInterface dialog, int which) {
    }

    private void m1100c() {
        DataLayer.getInstance().getUserManager().userRequest(TopConfig.f408a).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).filter(FilterMatchDialog$$Lambda$6.lambdaFactory$()).subscribe(FilterMatchDialog$$Lambda$7.lambdaFactory$(this), FilterMatchDialog$$Lambda$8.lambdaFactory$());
        getDataLayer().getAppManager().getConfigCache().compose(bindToLifecycle()).subscribeOn(Schedulers.io()).subscribe(FilterMatchDialog$$Lambda$9.lambdaFactory$(this), FilterMatchDialog$$Lambda$10.lambdaFactory$());
        getDataLayer().getAppManager().getOperInfoCache().compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(FilterMatchDialog$$Lambda$11.lambdaFactory$(this), FilterMatchDialog$$Lambda$12.lambdaFactory$());
    }

    static /* synthetic */ boolean m1099b(User user) throws Exception {
        return user != null;
    }

    /* synthetic */ void m1106a(User user) throws Exception {
        this.f1901l = user;
        this.f1896g.setText(String.valueOf(user.getDiamonds()));
    }

    static /* synthetic */ void m1101c(Throwable throwable) throws Exception {
    }

    /* synthetic */ void m1104a(ConfigInfo configInfo) throws Exception {
        this.f1899j = configInfo;
    }

    static /* synthetic */ void m1098b(Throwable throwable) throws Exception {
    }

    /* synthetic */ void m1105a(OperInfo operInfo) throws Exception {
        this.f1900k = operInfo;
        if (!(this.f1900k.getRateOptions() == null || TextUtils.isEmpty(this.f1900k.getRateOptions()[1]))) {
            this.f1890a.setText(this.f1900k.getRateOptions()[0]);
            this.f1890a.setVisibility(0);
        }
        this.f1895f.setText(Html.fromHtml(getString(C0376R.string.filter_match_tips, new Object[]{Integer.valueOf(operInfo.getGenderRate())})));
    }

    static /* synthetic */ void m1096a(Throwable throwable) throws Exception {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MatchFilterCallback) {
            this.f1903n = (MatchFilterCallback) activity;
        }
    }
}
