package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.ui.dialog.LoadingDialog;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$6$$Lambda$2 implements Consumer {
    private static final UpdateProfileFragment$6$$Lambda$2 f2257a = new UpdateProfileFragment$6$$Lambda$2();

    private UpdateProfileFragment$6$$Lambda$2() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        LoadingDialog.stopLoading();
    }
}
