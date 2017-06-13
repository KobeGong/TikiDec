package com.buddy.tiki.helper;

import com.buddy.tiki.service.base.DataLayer;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$52 implements Function {
    private static final DialogHelper$$Lambda$52 f630a = new DialogHelper$$Lambda$52();

    private DialogHelper$$Lambda$52() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return DataLayer.getInstance().getTikiResManager().uploadTempPic((byte[]) obj);
    }
}
