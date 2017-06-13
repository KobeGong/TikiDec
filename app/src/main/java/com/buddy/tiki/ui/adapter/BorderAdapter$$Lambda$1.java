package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.resource.Border;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BorderAdapter$$Lambda$1 implements OnClickListener {
    private final BorderAdapter f1660a;
    private final BorderHolder f1661b;
    private final Border f1662c;

    private BorderAdapter$$Lambda$1(BorderAdapter borderAdapter, BorderHolder borderHolder, Border border) {
        this.a = borderAdapter;
        this.b = borderHolder;
        this.c = border;
    }

    public static OnClickListener lambdaFactory$(BorderAdapter borderAdapter, BorderHolder borderHolder, Border border) {
        return new BorderAdapter$$Lambda$1(borderAdapter, borderHolder, border);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m990b(this.b, this.c, view);
    }
}
