package com.buddy.tiki.service;

import com.buddy.tiki.util.PreferenceUtil;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatManager$$Lambda$1 implements Consumer {
    private final boolean f909a;

    private ChatManager$$Lambda$1(boolean z) {
        this.a = z;
    }

    public static Consumer lambdaFactory$(boolean z) {
        return new ChatManager$$Lambda$1(z);
    }

    @Hidden
    public void accept(Object obj) {
        PreferenceUtil.saveWorkingStatus(this.a);
    }
}
