package com.buddy.tiki.ui.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$2 implements OnOffsetChangedListener {
    private final YouFragment f2289a;

    private YouFragment$$Lambda$2(YouFragment youFragment) {
        this.a = youFragment;
    }

    public static OnOffsetChangedListener lambdaFactory$(YouFragment youFragment) {
        return new YouFragment$$Lambda$2(youFragment);
    }

    @Hidden
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        this.a.m1486a(appBarLayout, i);
    }
}
