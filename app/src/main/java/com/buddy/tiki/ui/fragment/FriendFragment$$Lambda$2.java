package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FriendFragment$$Lambda$2 implements Consumer {
    private final FriendFragment f2141a;

    private FriendFragment$$Lambda$2(FriendFragment friendFragment) {
        this.a = friendFragment;
    }

    public static Consumer lambdaFactory$(FriendFragment friendFragment) {
        return new FriendFragment$$Lambda$2(friendFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1327d(obj);
    }
}
