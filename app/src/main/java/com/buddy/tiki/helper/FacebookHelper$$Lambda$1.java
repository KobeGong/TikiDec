package com.buddy.tiki.helper;

import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.login.widget.LoginButton;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FacebookHelper$$Lambda$1 implements OnClickListener {
    private final Runnable f682a;
    private final LoginButton f683b;

    private FacebookHelper$$Lambda$1(Runnable runnable, LoginButton loginButton) {
        this.a = runnable;
        this.b = loginButton;
    }

    public static OnClickListener lambdaFactory$(Runnable runnable, LoginButton loginButton) {
        return new FacebookHelper$$Lambda$1(runnable, loginButton);
    }

    @Hidden
    public void onClick(View view) {
        FacebookHelper.m140a(this.a, this.b, view);
    }
}
