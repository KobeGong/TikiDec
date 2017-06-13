package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FriendFragment$$Lambda$3 implements Consumer {
    private final FriendFragment f2142a;

    private FriendFragment$$Lambda$3(FriendFragment friendFragment) {
        this.a = friendFragment;
    }

    public static Consumer lambdaFactory$(FriendFragment friendFragment) {
        return new FriendFragment$$Lambda$3(friendFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1326c(obj);
    }
}
