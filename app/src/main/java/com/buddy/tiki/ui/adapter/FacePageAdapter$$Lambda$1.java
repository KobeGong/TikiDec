package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.ui.adapter.FacePageAdapter.FaceViewHolder;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FacePageAdapter$$Lambda$1 implements Consumer {
    private final FacePageAdapter f1767a;
    private final FaceViewHolder f1768b;
    private final int f1769c;
    private final FaceUnity f1770d;

    private FacePageAdapter$$Lambda$1(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, int i, FaceUnity faceUnity) {
        this.a = facePageAdapter;
        this.b = faceViewHolder;
        this.c = i;
        this.d = faceUnity;
    }

    public static Consumer lambdaFactory$(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, int i, FaceUnity faceUnity) {
        return new FacePageAdapter$$Lambda$1(facePageAdapter, faceViewHolder, i, faceUnity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1029b(this.b, this.c, this.d, (String) obj);
    }
}
