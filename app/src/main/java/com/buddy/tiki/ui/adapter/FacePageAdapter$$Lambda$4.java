package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.ui.adapter.FacePageAdapter.FaceViewHolder;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FacePageAdapter$$Lambda$4 implements Consumer {
    private final FaceViewHolder f1776a;

    private FacePageAdapter$$Lambda$4(FaceViewHolder faceViewHolder) {
        this.a = faceViewHolder;
    }

    public static Consumer lambdaFactory$(FaceViewHolder faceViewHolder) {
        return new FacePageAdapter$$Lambda$4(faceViewHolder);
    }

    @Hidden
    public void accept(Object obj) {
        FacePageAdapter.m1021a(this.a, (Throwable) obj);
    }
}
