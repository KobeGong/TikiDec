package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$5 implements Consumer {
    private final UpdateProfileFragment f2243a;

    private UpdateProfileFragment$$Lambda$5(UpdateProfileFragment updateProfileFragment) {
        this.a = updateProfileFragment;
    }

    public static Consumer lambdaFactory$(UpdateProfileFragment updateProfileFragment) {
        return new UpdateProfileFragment$$Lambda$5(updateProfileFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1467b(obj);
    }
}
