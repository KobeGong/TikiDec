package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.user.SyncFriends;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouActivity$$Lambda$1 implements Consumer {
    private static final YouActivity$$Lambda$1 f1619a = new YouActivity$$Lambda$1();

    private YouActivity$$Lambda$1() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        YouActivity.m963a((SyncFriends) obj);
    }
}
