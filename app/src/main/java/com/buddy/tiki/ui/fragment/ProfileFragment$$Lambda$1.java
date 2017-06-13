package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ProfileFragment$$Lambda$1 implements Consumer {
    private final ProfileFragment f2180a;

    private ProfileFragment$$Lambda$1(ProfileFragment profileFragment) {
        this.a = profileFragment;
    }

    public static Consumer lambdaFactory$(ProfileFragment profileFragment) {
        return new ProfileFragment$$Lambda$1(profileFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1370a((User) obj);
    }
}
