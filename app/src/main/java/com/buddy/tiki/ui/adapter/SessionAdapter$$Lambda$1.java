package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.user.TikiUser;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SessionAdapter$$Lambda$1 implements OnClickListener {
    private final SessionAdapter f1826a;
    private final TikiUser f1827b;

    private SessionAdapter$$Lambda$1(SessionAdapter sessionAdapter, TikiUser tikiUser) {
        this.a = sessionAdapter;
        this.b = tikiUser;
    }

    public static OnClickListener lambdaFactory$(SessionAdapter sessionAdapter, TikiUser tikiUser) {
        return new SessionAdapter$$Lambda$1(sessionAdapter, tikiUser);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1051a(this.b, view);
    }
}
