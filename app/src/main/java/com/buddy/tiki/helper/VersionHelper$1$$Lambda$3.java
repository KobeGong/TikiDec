package com.buddy.tiki.helper;

import com.buddy.tiki.util.IntentUtil;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VersionHelper$1$$Lambda$3 implements Consumer {
    private static final VersionHelper$1$$Lambda$3 f763a = new VersionHelper$1$$Lambda$3();

    private VersionHelper$1$$Lambda$3() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        IntentUtil.startInstallApp((String) obj);
    }
}
