package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FriendAdapter$$Lambda$2 implements Consumer {
    private static final FriendAdapter$$Lambda$2 f1800a = new FriendAdapter$$Lambda$2();

    private FriendAdapter$$Lambda$2() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        FriendAdapter.m1031a((User) obj);
    }
}
