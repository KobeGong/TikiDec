package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$3 implements Consumer {
    private final UpdateProfileFragment f2241a;

    private UpdateProfileFragment$$Lambda$3(UpdateProfileFragment updateProfileFragment) {
        this.a = updateProfileFragment;
    }

    public static Consumer lambdaFactory$(UpdateProfileFragment updateProfileFragment) {
        return new UpdateProfileFragment$$Lambda$3(updateProfileFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1464a((Boolean) obj);
    }
}
