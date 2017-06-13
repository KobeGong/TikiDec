package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FaceDetectCover$$Lambda$1 implements Consumer {
    private final FaceDetectCover f2648a;

    private FaceDetectCover$$Lambda$1(FaceDetectCover faceDetectCover) {
        this.a = faceDetectCover;
    }

    public static Consumer lambdaFactory$(FaceDetectCover faceDetectCover) {
        return new FaceDetectCover$$Lambda$1(faceDetectCover);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1698a(obj);
    }
}
