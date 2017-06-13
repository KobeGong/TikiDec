package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.ui.adapter.FacePageAdapter.FaceViewHolder;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FacePageAdapter$$Lambda$7 implements OnClickListener {
    private final FacePageAdapter f1787a;
    private final FaceViewHolder f1788b;
    private final FaceUnity f1789c;
    private final int f1790d;

    private FacePageAdapter$$Lambda$7(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, FaceUnity faceUnity, int i) {
        this.a = facePageAdapter;
        this.b = faceViewHolder;
        this.c = faceUnity;
        this.d = i;
    }

    public static OnClickListener lambdaFactory$(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, FaceUnity faceUnity, int i) {
        return new FacePageAdapter$$Lambda$7(facePageAdapter, faceViewHolder, faceUnity, i);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1027a(this.b, this.c, this.d, view);
    }
}
