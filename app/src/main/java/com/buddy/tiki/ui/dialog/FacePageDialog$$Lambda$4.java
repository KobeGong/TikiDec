package com.buddy.tiki.ui.dialog;

import com.buddy.tiki.ui.adapter.FacePageAdapter.OnSelectedCallback;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FacePageDialog$$Lambda$4 implements OnSelectedCallback {
    private final FacePageDialog f1864a;

    private FacePageDialog$$Lambda$4(FacePageDialog facePageDialog) {
        this.a = facePageDialog;
    }

    public static OnSelectedCallback lambdaFactory$(FacePageDialog facePageDialog) {
        return new FacePageDialog$$Lambda$4(facePageDialog);
    }

    @Hidden
    public void onSelected(int i) {
        this.a.m1084a(i);
    }
}
