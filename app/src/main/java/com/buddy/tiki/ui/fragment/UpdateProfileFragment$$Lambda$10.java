package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$10 implements Consumer {
    private final UpdateProfileFragment f2226a;
    private final String f2227b;

    private UpdateProfileFragment$$Lambda$10(UpdateProfileFragment updateProfileFragment, String str) {
        this.a = updateProfileFragment;
        this.b = str;
    }

    public static Consumer lambdaFactory$(UpdateProfileFragment updateProfileFragment, String str) {
        return new UpdateProfileFragment$$Lambda$10(updateProfileFragment, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1466a(this.b, (Boolean) obj);
    }
}
