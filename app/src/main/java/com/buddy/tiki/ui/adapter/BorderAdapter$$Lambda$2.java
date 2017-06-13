package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.resource.Border;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BorderAdapter$$Lambda$2 implements OnClickListener {
    private final BorderAdapter f1663a;
    private final BorderHolder f1664b;
    private final Border f1665c;

    private BorderAdapter$$Lambda$2(BorderAdapter borderAdapter, BorderHolder borderHolder, Border border) {
        this.a = borderAdapter;
        this.b = borderHolder;
        this.c = border;
    }

    public static OnClickListener lambdaFactory$(BorderAdapter borderAdapter, BorderHolder borderHolder, Border border) {
        return new BorderAdapter$$Lambda$2(borderAdapter, borderHolder, border);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m988a(this.b, this.c, view);
    }
}
