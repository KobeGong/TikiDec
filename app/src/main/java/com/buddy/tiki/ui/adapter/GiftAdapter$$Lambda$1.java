package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.resource.Gift;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class GiftAdapter$$Lambda$1 implements OnClickListener {
    private final GiftAdapter f1806a;
    private final GiftHolder f1807b;
    private final Gift f1808c;

    private GiftAdapter$$Lambda$1(GiftAdapter giftAdapter, GiftHolder giftHolder, Gift gift) {
        this.a = giftAdapter;
        this.b = giftHolder;
        this.c = gift;
    }

    public static OnClickListener lambdaFactory$(GiftAdapter giftAdapter, GiftHolder giftHolder, Gift gift) {
        return new GiftAdapter$$Lambda$1(giftAdapter, giftHolder, gift);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1036a(this.b, this.c, view);
    }
}
