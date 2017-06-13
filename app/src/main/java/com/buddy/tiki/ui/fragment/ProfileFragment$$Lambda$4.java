package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ProfileFragment$$Lambda$4 implements Consumer {
    private final ProfileFragment f2183a;

    private ProfileFragment$$Lambda$4(ProfileFragment profileFragment) {
        this.a = profileFragment;
    }

    public static Consumer lambdaFactory$(ProfileFragment profileFragment) {
        return new ProfileFragment$$Lambda$4(profileFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1373c(obj);
    }
}
