package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;

final /* synthetic */ class FacePageDialog$$Lambda$2 implements Consumer {
    private final FacePageDialog f1862a;

    private FacePageDialog$$Lambda$2(FacePageDialog facePageDialog) {
        this.a = facePageDialog;
    }

    public static Consumer lambdaFactory$(FacePageDialog facePageDialog) {
        return new FacePageDialog$$Lambda$2(facePageDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1086a((List) obj);
    }
}
