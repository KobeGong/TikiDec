package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.user.TikiUser;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FriendAdapter$$Lambda$1 implements OnClickListener {
    private final FriendAdapter f1798a;
    private final TikiUser f1799b;

    private FriendAdapter$$Lambda$1(FriendAdapter friendAdapter, TikiUser tikiUser) {
        this.a = friendAdapter;
        this.b = tikiUser;
    }

    public static OnClickListener lambdaFactory$(FriendAdapter friendAdapter, TikiUser tikiUser) {
        return new FriendAdapter$$Lambda$1(friendAdapter, tikiUser);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1033a(this.b, view);
    }
}
