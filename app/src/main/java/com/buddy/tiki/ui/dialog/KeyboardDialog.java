package com.buddy.tiki.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.FrameLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.event.UserEvent.InputDiamondsDialogDismissEvent;
import com.buddy.tiki.event.UserEvent.InputDiamondsEvent;
import com.buddy.tiki.ui.dialog.base.BaseRxBottomSheetDialogFragment;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

public class KeyboardDialog extends BaseRxBottomSheetDialogFragment {
    AppCompatEditText f1935a;
    AppCompatButton f1936b;
    AppCompatButton f1937c;
    AppCompatButton f1938d;
    AppCompatButton f1939e;
    AppCompatButton f1940f;
    AppCompatButton f1941g;
    AppCompatButton f1942h;
    AppCompatButton f1943i;
    AppCompatButton f1944j;
    AppCompatButton f1945k;
    AppCompatButton f1946l;
    FrameLayout f1947m;
    FrameLayout f1948n;

    class C04541 implements TextWatcher {
        final /* synthetic */ KeyboardDialog f1934a;

        C04541(KeyboardDialog this$0) {
            this.f1934a = this$0;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            int num = 0;
            if (this.f1934a.f1935a.getText().length() > 0 && this.f1934a.f1935a.getText().length() < 4) {
                num = Integer.parseInt(this.f1934a.f1935a.getText().toString());
                if (num > 500) {
                    EventBus.getDefault().post(new InputDiamondsEvent(0));
                    this.f1934a.f1948n.setVisibility(0);
                } else {
                    EventBus.getDefault().post(new InputDiamondsEvent(num));
                    this.f1934a.f1948n.setVisibility(8);
                }
            } else if (this.f1934a.f1935a.getText().length() == 0) {
                this.f1934a.f1948n.setVisibility(8);
            } else {
                this.f1934a.f1948n.setVisibility(0);
            }
            if (num == 0) {
                EventBus.getDefault().post(new InputDiamondsEvent(0));
            }
        }
    }

    protected void mo2183a(@Nullable Bundle savedInstanceState) {
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(C0376R.layout.dialog_keyboard);
        m1125a(dialog);
        return dialog;
    }

    protected int mo2182a() {
        return C0376R.style.BottomDialogKeyboardStyle;
    }

    private void m1125a(Dialog dialog) {
        this.f1948n = (FrameLayout) dialog.findViewById(C0376R.id.keyboard_tip);
        this.f1935a = (AppCompatEditText) dialog.findViewById(C0376R.id.input_diamonds);
        this.f1935a.setInputType(0);
        this.f1935a.addTextChangedListener(new C04541(this));
        this.f1946l = (AppCompatButton) dialog.findViewById(C0376R.id.diamonds_ok);
        this.f1936b = (AppCompatButton) dialog.findViewById(C0376R.id.btn_0);
        this.f1937c = (AppCompatButton) dialog.findViewById(C0376R.id.btn_1);
        this.f1938d = (AppCompatButton) dialog.findViewById(C0376R.id.btn_2);
        this.f1939e = (AppCompatButton) dialog.findViewById(C0376R.id.btn_3);
        this.f1940f = (AppCompatButton) dialog.findViewById(C0376R.id.btn_4);
        this.f1941g = (AppCompatButton) dialog.findViewById(C0376R.id.btn_5);
        this.f1942h = (AppCompatButton) dialog.findViewById(C0376R.id.btn_6);
        this.f1943i = (AppCompatButton) dialog.findViewById(C0376R.id.btn_7);
        this.f1944j = (AppCompatButton) dialog.findViewById(C0376R.id.btn_8);
        this.f1945k = (AppCompatButton) dialog.findViewById(C0376R.id.btn_9);
        this.f1947m = (FrameLayout) dialog.findViewById(C0376R.id.btn_del);
        RxView.clicks(this.f1937c).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$1.lambdaFactory$(this));
        RxView.clicks(this.f1938d).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.f1939e).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.f1940f).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.f1941g).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$5.lambdaFactory$(this));
        RxView.clicks(this.f1942h).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$6.lambdaFactory$(this));
        RxView.clicks(this.f1943i).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$7.lambdaFactory$(this));
        RxView.clicks(this.f1944j).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$8.lambdaFactory$(this));
        RxView.clicks(this.f1945k).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$9.lambdaFactory$(this));
        RxView.clicks(this.f1936b).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$10.lambdaFactory$(this));
        RxView.clicks(this.f1946l).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$11.lambdaFactory$(this));
        RxView.clicks(this.f1947m).compose(bindToLifecycle()).subscribe(KeyboardDialog$$Lambda$12.lambdaFactory$(this));
    }

    /* synthetic */ void m1139l(Object aVoid) throws Exception {
        this.f1935a.append(this.f1937c.getText());
    }

    /* synthetic */ void m1138k(Object aVoid) throws Exception {
        this.f1935a.append(this.f1938d.getText());
    }

    /* synthetic */ void m1137j(Object aVoid) throws Exception {
        this.f1935a.append(this.f1939e.getText());
    }

    /* synthetic */ void m1136i(Object aVoid) throws Exception {
        this.f1935a.append(this.f1940f.getText());
    }

    /* synthetic */ void m1135h(Object aVoid) throws Exception {
        this.f1935a.append(this.f1941g.getText());
    }

    /* synthetic */ void m1134g(Object aVoid) throws Exception {
        this.f1935a.append(this.f1942h.getText());
    }

    /* synthetic */ void m1133f(Object aVoid) throws Exception {
        this.f1935a.append(this.f1943i.getText());
    }

    /* synthetic */ void m1132e(Object aVoid) throws Exception {
        this.f1935a.append(this.f1944j.getText());
    }

    /* synthetic */ void m1131d(Object aVoid) throws Exception {
        this.f1935a.append(this.f1945k.getText());
    }

    /* synthetic */ void m1130c(Object aVoid) throws Exception {
        if (this.f1935a.getText().length() > 0) {
            this.f1935a.append(this.f1936b.getText());
        }
    }

    /* synthetic */ void m1129b(Object aVoid) throws Exception {
        if (this.f1935a.getText().length() > 0 && this.f1935a.getText().length() < 4 && Integer.parseInt(this.f1935a.getText().toString()) <= 500) {
            dismiss();
        }
    }

    /* synthetic */ void m1128a(Object aVoid) throws Exception {
        if (this.f1935a.getText().length() > 1) {
            this.f1935a.getText().delete(this.f1935a.getText().length() - 1, this.f1935a.getText().length());
        } else {
            this.f1935a.setText(BuildConfig.VERSION_NAME);
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        EventBus.getDefault().post(new InputDiamondsDialogDismissEvent());
    }
}
