package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ModifyNickFragment$$Lambda$4 implements Function {
    private final ModifyNickFragment f2159a;

    private ModifyNickFragment$$Lambda$4(ModifyNickFragment modifyNickFragment) {
        this.a = modifyNickFragment;
    }

    public static Function lambdaFactory$(ModifyNickFragment modifyNickFragment) {
        return new ModifyNickFragment$$Lambda$4(modifyNickFragment);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m1338a((String) obj);
    }
}
