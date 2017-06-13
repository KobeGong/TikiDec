package com.buddy.tiki.helper;

import android.text.TextUtils;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$15 implements Predicate {
    private static final DownloadHelper$$Lambda$15 f663a = new DownloadHelper$$Lambda$15();

    private DownloadHelper$$Lambda$15() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return TextUtils.isEmpty((String) obj);
    }
}
