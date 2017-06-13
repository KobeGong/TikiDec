package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.ui.adapter.FacePageAdapter.FaceViewHolder;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FacePageAdapter$$Lambda$6 implements OnClickListener {
    private final FacePageAdapter f1782a;
    private final FaceViewHolder f1783b;
    private final int f1784c;
    private final String f1785d;
    private final FaceUnity f1786e;

    private FacePageAdapter$$Lambda$6(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, int i, String str, FaceUnity faceUnity) {
        this.a = facePageAdapter;
        this.b = faceViewHolder;
        this.c = i;
        this.d = str;
        this.e = faceUnity;
    }

    public static OnClickListener lambdaFactory$(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, int i, String str, FaceUnity faceUnity) {
        return new FacePageAdapter$$Lambda$6(facePageAdapter, faceViewHolder, i, str, faceUnity);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1030b(this.b, this.c, this.d, this.e, view);
    }
}
