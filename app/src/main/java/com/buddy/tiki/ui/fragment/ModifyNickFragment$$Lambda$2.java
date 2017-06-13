package com.buddy.tiki.ui.fragment;

import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ModifyNickFragment$$Lambda$2 implements Function {
    private static final ModifyNickFragment$$Lambda$2 f2157a = new ModifyNickFragment$$Lambda$2();

    private ModifyNickFragment$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return Integer.valueOf(((TextViewAfterTextChangeEvent) obj).editable().toString().length());
    }
}
