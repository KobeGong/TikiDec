package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.ui.dialog.LoadingDialog;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ModifyNickFragment$$Lambda$6 implements Consumer {
    private static final ModifyNickFragment$$Lambda$6 f2161a = new ModifyNickFragment$$Lambda$6();

    private ModifyNickFragment$$Lambda$6() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        LoadingDialog.stopLoading();
    }
}
