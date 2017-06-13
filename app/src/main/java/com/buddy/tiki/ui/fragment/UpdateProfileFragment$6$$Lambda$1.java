package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.ui.fragment.UpdateProfileFragment.C04746;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$6$$Lambda$1 implements Consumer {
    private final C04746 f2256a;

    private UpdateProfileFragment$6$$Lambda$1(C04746 c04746) {
        this.a = c04746;
    }

    public static Consumer lambdaFactory$(C04746 c04746) {
        return new UpdateProfileFragment$6$$Lambda$1(c04746);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1423a((String) obj);
    }
}
