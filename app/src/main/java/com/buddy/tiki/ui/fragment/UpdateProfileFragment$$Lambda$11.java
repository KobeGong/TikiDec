package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.wechat.WxUser;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$11 implements Predicate {
    private static final UpdateProfileFragment$$Lambda$11 f2228a = new UpdateProfileFragment$$Lambda$11();

    private UpdateProfileFragment$$Lambda$11() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return UpdateProfileFragment.m1438b((WxUser) obj);
    }
}
