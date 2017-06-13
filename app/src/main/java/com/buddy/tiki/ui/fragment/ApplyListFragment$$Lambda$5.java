package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.user.FlwApplyResponse;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListFragment$$Lambda$5 implements Predicate {
    private static final ApplyListFragment$$Lambda$5 f2005a = new ApplyListFragment$$Lambda$5();

    private ApplyListFragment$$Lambda$5() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return ApplyListFragment.m1207b((FlwApplyResponse) obj);
    }
}
