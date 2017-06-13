package com.buddy.tiki.view;

import com.buddy.tiki.service.base.DataLayer;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class RushView$$Lambda$4 implements Function {
    private static final RushView$$Lambda$4 f2801a = new RushView$$Lambda$4();

    private RushView$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return DataLayer.getInstance().getChatManager().rushAction(null);
    }
}
