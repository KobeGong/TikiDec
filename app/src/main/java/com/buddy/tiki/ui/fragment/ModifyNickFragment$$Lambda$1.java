package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ModifyNickFragment$$Lambda$1 implements Consumer {
    private final ModifyNickFragment f2156a;

    private ModifyNickFragment$$Lambda$1(ModifyNickFragment modifyNickFragment) {
        this.a = modifyNickFragment;
    }

    public static Consumer lambdaFactory$(ModifyNickFragment modifyNickFragment) {
        return new ModifyNickFragment$$Lambda$1(modifyNickFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1340a(obj);
    }
}
