package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ProfileFragment$$Lambda$6 implements Consumer {
    private final ProfileFragment f2185a;

    private ProfileFragment$$Lambda$6(ProfileFragment profileFragment) {
        this.a = profileFragment;
    }

    public static Consumer lambdaFactory$(ProfileFragment profileFragment) {
        return new ProfileFragment$$Lambda$6(profileFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1371a(obj);
    }
}
