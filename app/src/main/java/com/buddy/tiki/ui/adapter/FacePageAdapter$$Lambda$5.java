package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.ui.adapter.FacePageAdapter.FaceViewHolder;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FacePageAdapter$$Lambda$5 implements OnClickListener {
    private final FacePageAdapter f1777a;
    private final FaceViewHolder f1778b;
    private final int f1779c;
    private final String f1780d;
    private final FaceUnity f1781e;

    private FacePageAdapter$$Lambda$5(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, int i, String str, FaceUnity faceUnity) {
        this.a = facePageAdapter;
        this.b = faceViewHolder;
        this.c = i;
        this.d = str;
        this.e = faceUnity;
    }

    public static OnClickListener lambdaFactory$(FacePageAdapter facePageAdapter, FaceViewHolder faceViewHolder, int i, String str, FaceUnity faceUnity) {
        return new FacePageAdapter$$Lambda$5(facePageAdapter, faceViewHolder, i, str, faceUnity);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1026a(this.b, this.c, this.d, this.e, view);
    }
}
