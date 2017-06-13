package com.buddy.tiki.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.model.promo.PromoResult;
import com.buddy.tiki.ui.dialog.LoadingDialog;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.SchedulersCompat;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public class PromotionFragment extends BaseFragment {
    @BindView(2131820984)
    AppCompatButton mExchangeButton;
    @BindView(2131820975)
    AppCompatEditText mPromotionInput;
    @BindView(2131820688)
    Toolbar mToolbar;

    class C04671 implements Observer<PromoResult> {
        final /* synthetic */ PromotionFragment f2195a;

        C04671(PromotionFragment this$0) {
            this.f2195a = this$0;
        }

        public void onSubscribe(Disposable d) {
            LoadingDialog.startLoading(this.f2195a.m1203h(), (int) C0376R.string.exchanging);
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            LoadingDialog.stopLoading();
        }

        public void onNext(PromoResult promoResult) {
            LoadingDialog.stopLoading();
            if (promoResult != null) {
                DialogHelper.INSTANCE.showPromotionDialog(this.f2195a.m1203h(), promoResult);
            }
        }
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_promotion;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1378b();
        m1379c();
    }

    private void m1378b() {
    }

    private void m1379c() {
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(PromotionFragment$$Lambda$1.lambdaFactory$(this));
        RxTextView.afterTextChangeEvents(this.mPromotionInput).compose(bindToLifecycle()).map(PromotionFragment$$Lambda$4.lambdaFactory$()).subscribe(RxView.enabled(this.mExchangeButton));
        RxView.clicks(this.mExchangeButton).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(PromotionFragment$$Lambda$5.lambdaFactory$(this));
    }

    /* synthetic */ void m1384b(Object aVoid) throws Exception {
        m1203h().finish();
    }

    static /* synthetic */ Boolean m1376a(TextViewAfterTextChangeEvent event) throws Exception {
        return Boolean.valueOf(event.editable().length() > 0);
    }

    /* synthetic */ void m1383a(Object aVoid) throws Exception {
        m1380d();
    }

    private void m1380d() {
        getDataLayer().getUserManager().submitPromo(this.mPromotionInput.getEditableText().toString().trim()).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(new C04671(this));
    }
}
