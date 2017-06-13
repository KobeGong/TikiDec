package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$7 implements Consumer {
    private final UpdateProfileFragment f2245a;
    private final String f2246b;

    private UpdateProfileFragment$$Lambda$7(UpdateProfileFragment updateProfileFragment, String str) {
        this.a = updateProfileFragment;
        this.b = str;
    }

    public static Consumer lambdaFactory$(UpdateProfileFragment updateProfileFragment, String str) {
        return new UpdateProfileFragment$$Lambda$7(updateProfileFragment, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1470d(this.b, (Boolean) obj);
    }
}
