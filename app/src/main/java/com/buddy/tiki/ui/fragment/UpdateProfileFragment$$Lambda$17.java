package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.util.ToastUtil;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$17 implements Consumer {
    private static final UpdateProfileFragment$$Lambda$17 f2234a = new UpdateProfileFragment$$Lambda$17();

    private UpdateProfileFragment$$Lambda$17() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        ToastUtil.getInstance().show(ChatApp.getInstance(), (int) C0376R.string.promotion_invalid, 1);
    }
}
