package com.buddy.tiki.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import com.buddy.tiki.ui.adapter.GiftAdapter;
import com.buddy.tiki.ui.adapter.base.BaseAdapter.OnItemClick;
import com.buddy.tiki.ui.dialog.base.BaseRxBottomSheetDialogFragment;
import com.buddy.tiki.util.SchedulersCompat;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GiftDialog extends BaseRxBottomSheetDialogFragment implements OnItemClick<Gift> {
    private static final TikiLog f1912e = TikiLog.getInstance("GiftDialog");
    RecyclerView f1913a;
    AppCompatTextView f1914b;
    AppCompatTextView f1915c;
    AppCompatButton f1916d;
    private GiftAdapter f1917f;
    private Gift f1918g;
    private PresentListener f1919h;
    private ConfigInfo f1920i;
    private User f1921j;

    public interface PresentListener {
        void onPresent(Gift gift);
    }

    @StyleRes
    protected int mo2182a() {
        return C0376R.style.BottomDialogStyle;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(C0376R.layout.dialog_gift);
        m1112a(dialog);
        m1114b();
        m1116c();
        return dialog;
    }

    private void m1112a(Dialog dialog) {
        this.f1913a = (RecyclerView) dialog.findViewById(C0376R.id.data_list);
        this.f1914b = (AppCompatTextView) dialog.findViewById(C0376R.id.diamond_num);
        this.f1915c = (AppCompatTextView) dialog.findViewById(C0376R.id.recharge_btn);
        this.f1916d = (AppCompatButton) dialog.findViewById(C0376R.id.present_btn);
    }

    protected void mo2183a(Bundle savedInstanceState) {
    }

    private void m1114b() {
        this.f1913a.setLayoutManager(new LinearLayoutManager(getHoldingActivity(), 0, false));
        ItemAnimator itemAnimator = this.f1913a.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
        this.f1917f = new GiftAdapter(getContext(), this);
        this.f1913a.setAdapter(this.f1917f);
        RxView.clicks(this.f1916d).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(GiftDialog$$Lambda$1.lambdaFactory$(this));
        RxView.clicks(this.f1915c).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(GiftDialog$$Lambda$2.lambdaFactory$(this));
    }

    /* synthetic */ void m1124b(Object aVoid) throws Exception {
        dismiss();
        if (this.f1918g != null) {
            if (this.f1921j != null && this.f1921j.getDiamonds() < ((long) this.f1918g.getDiamonds()) && this.f1920i != null) {
                DialogHelper.INSTANCE.showLackBalanceDialog(getContext(), this.f1920i.getH5DiamondsUrl());
            } else if (this.f1919h != null) {
                this.f1919h.onPresent(this.f1918g);
            }
        }
    }

    /* synthetic */ void m1122a(Object aVoid) throws Exception {
        if (this.f1920i != null) {
            dismiss();
            WebBrowserActivity.launchWeb(getContext(), this.f1920i.getH5DiamondsUrl());
        }
    }

    private void m1116c() {
        getDataLayer().getAppManager().getConfigCache().compose(bindToLifecycle()).subscribeOn(Schedulers.io()).subscribe(GiftDialog$$Lambda$3.lambdaFactory$(this), GiftDialog$$Lambda$4.lambdaFactory$());
        getDataLayer().getUserManager().userRequest(TopConfig.f408a).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(GiftDialog$$Lambda$5.lambdaFactory$(this), GiftDialog$$Lambda$6.lambdaFactory$());
        getDataLayer().getResourceManager().sysGiftsRequest(1).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(GiftDialog$$Lambda$7.lambdaFactory$(this), GiftDialog$$Lambda$8.lambdaFactory$());
    }

    /* synthetic */ void m1120a(ConfigInfo configInfo) throws Exception {
        this.f1920i = configInfo;
    }

    /* synthetic */ void m1121a(User user) throws Exception {
        this.f1921j = user;
        this.f1914b.setText(String.valueOf(user.getDiamonds()));
    }

    /* synthetic */ void m1123a(List list) throws Exception {
        this.f1917f.clearDataList();
        this.f1917f.addDataList(list);
    }

    public void setOnPresentListener(PresentListener listener) {
        this.f1919h = listener;
    }

    public void click(View view, Gift data, int position) {
        if (data != null) {
            this.f1916d.setAlpha(1.0f);
            this.f1916d.setEnabled(true);
            this.f1918g = data;
        }
    }
}
