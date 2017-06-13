package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FriendFragment$$Lambda$6 implements Consumer {
    private final FriendFragment f2145a;

    private FriendFragment$$Lambda$6(FriendFragment friendFragment) {
        this.a = friendFragment;
    }

    public static Consumer lambdaFactory$(FriendFragment friendFragment) {
        return new FriendFragment$$Lambda$6(friendFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1322a((ConfigInfo) obj);
    }
}
