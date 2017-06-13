package com.buddy.tiki.service;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$1 implements Consumer {
    private final UserManager f972a;
    private final String f973b;
    private final String f974c;

    private UserManager$$Lambda$1(UserManager userManager, String str, String str2) {
        this.a = userManager;
        this.b = str;
        this.c = str2;
    }

    public static Consumer lambdaFactory$(UserManager userManager, String str, String str2) {
        return new UserManager$$Lambda$1(userManager, str, str2);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m342a(this.b, this.c, (User) obj);
    }
}
