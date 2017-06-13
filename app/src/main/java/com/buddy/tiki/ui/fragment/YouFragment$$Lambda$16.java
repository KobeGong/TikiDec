package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$16 implements Consumer {
    private final YouFragment f2277a;

    private YouFragment$$Lambda$16(YouFragment youFragment) {
        this.a = youFragment;
    }

    public static Consumer lambdaFactory$(YouFragment youFragment) {
        return new YouFragment$$Lambda$16(youFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1489a((User) obj);
    }
}
