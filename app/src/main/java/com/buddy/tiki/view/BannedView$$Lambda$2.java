package com.buddy.tiki.view;

import android.view.View;
import android.view.View.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BannedView$$Lambda$2 implements OnClickListener {
    private final BannedView f2501a;
    private final String f2502b;

    private BannedView$$Lambda$2(BannedView bannedView, String str) {
        this.a = bannedView;
        this.b = str;
    }

    public static OnClickListener lambdaFactory$(BannedView bannedView, String str) {
        return new BannedView$$Lambda$2(bannedView, str);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1634a(this.b, view);
    }
}
