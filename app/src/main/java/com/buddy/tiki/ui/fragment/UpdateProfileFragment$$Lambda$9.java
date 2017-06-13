package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$9 implements Consumer {
    private final UpdateProfileFragment f2249a;
    private final String f2250b;

    private UpdateProfileFragment$$Lambda$9(UpdateProfileFragment updateProfileFragment, String str) {
        this.a = updateProfileFragment;
        this.b = str;
    }

    public static Consumer lambdaFactory$(UpdateProfileFragment updateProfileFragment, String str) {
        return new UpdateProfileFragment$$Lambda$9(updateProfileFragment, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1468b(this.b, (Boolean) obj);
    }
}
