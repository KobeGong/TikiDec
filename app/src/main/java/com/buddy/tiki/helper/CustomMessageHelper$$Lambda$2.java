package com.buddy.tiki.helper;

import com.buddy.tiki.ChatApp;
import com.buddy.tiki.util.IncomingCallManager;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CustomMessageHelper$$Lambda$2 implements Runnable {
    private final String f530a;
    private final String f531b;
    private final String f532c;

    private CustomMessageHelper$$Lambda$2(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public static Runnable lambdaFactory$(String str, String str2, String str3) {
        return new CustomMessageHelper$$Lambda$2(str, str2, str3);
    }

    @Hidden
    public void run() {
        IncomingCallManager.getInstance().show(ChatApp.getInstance(), this.a, this.b, this.c);
    }
}
