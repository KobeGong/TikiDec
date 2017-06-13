package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.ui.adapter.FacePageAdapter.FaceViewHolder;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FacePageAdapter$$Lambda$3 implements Consumer {
    private final FacePageAdapter f1772a;
    private final FaceViewHolder f1773b;
    private final int f1774c;
    private final FaceUnity f1775d;

    private FacePageAdapter$$Lambda$3(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, int i, FaceUnity faceUnity) {
        this.a = facePageAdapter;
        this.b = faceViewHolder;
        this.c = i;
        this.d = faceUnity;
    }

    public static Consumer lambdaFactory$(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, int i, FaceUnity faceUnity) {
        return new FacePageAdapter$$Lambda$3(facePageAdapter, faceViewHolder, i, faceUnity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1025a(this.b, this.c, this.d, (String) obj);
    }
}
