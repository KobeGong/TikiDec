package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$8 implements Consumer {
    private final UpdateProfileFragment f2247a;
    private final String f2248b;

    private UpdateProfileFragment$$Lambda$8(UpdateProfileFragment updateProfileFragment, String str) {
        this.a = updateProfileFragment;
        this.b = str;
    }

    public static Consumer lambdaFactory$(UpdateProfileFragment updateProfileFragment, String str) {
        return new UpdateProfileFragment$$Lambda$8(updateProfileFragment, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1469c(this.b, (Boolean) obj);
    }
}
