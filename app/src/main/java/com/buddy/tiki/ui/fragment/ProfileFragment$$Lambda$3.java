package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ProfileFragment$$Lambda$3 implements Consumer {
    private final ProfileFragment f2182a;

    private ProfileFragment$$Lambda$3(ProfileFragment profileFragment) {
        this.a = profileFragment;
    }

    public static Consumer lambdaFactory$(ProfileFragment profileFragment) {
        return new ProfileFragment$$Lambda$3(profileFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1374d(obj);
    }
}
