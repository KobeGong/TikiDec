package com.buddy.tiki.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.promo.PromoResult;
import com.buddy.tiki.protocol.ui.BackHandleInterface;
import com.buddy.tiki.ui.activity.CallActivity;
import com.buddy.tiki.ui.dialog.LoadingDialog;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.SchedulersCompat;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public class NewbiePromoFragment extends BaseFragment implements BackHandleInterface {
    @BindView(2131820976)
    AppCompatButton mNextButton;
    @BindView(2131820975)
    AppCompatEditText mPromotionInput;
    @BindView(2131820758)
    AppCompatTextView mSkipButton;

    class C04661 implements Observer<PromoResult> {
        final /* synthetic */ NewbiePromoFragment f2170a;

        C04661(NewbiePromoFragment this$0) {
            this.f2170a = this$0;
        }

        public void onSubscribe(Disposable d) {
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            LoadingDialog.stopLoading();
        }

        public void onNext(PromoResult promoResult) {
            LoadingDialog.stopLoading();
            this.f2170a.m1347e();
        }
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_newbie_promo;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1344b();
        m1345c();
    }

    private void m1344b() {
    }

    private void m1345c() {
        RxTextView.afterTextChangeEvents(this.mPromotionInput).map(NewbiePromoFragment$$Lambda$1.lambdaFactory$()).compose(bindToLifecycle()).subscribe(RxView.enabled(this.mNextButton));
        RxView.clicks(this.mNextButton).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(NewbiePromoFragment$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.mSkipButton).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(NewbiePromoFragment$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ Boolean m1342a(TextViewAfterTextChangeEvent event) throws Exception {
        return Boolean.valueOf(event.editable().length() > 0);
    }

    /* synthetic */ void m1351b(Object aVoid) throws Exception {
        m1346d();
    }

    /* synthetic */ void m1350a(Object aVoid) throws Exception {
        m1347e();
    }

    private void m1346d() {
        getDataLayer().getUserManager().submitPromo(this.mPromotionInput.getEditableText().toString().trim()).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(new C04661(this));
    }

    private void m1347e() {
        m1200a(CallActivity.class);
        m1203h().finish();
    }

    public boolean handleBack() {
        return true;
    }
}
