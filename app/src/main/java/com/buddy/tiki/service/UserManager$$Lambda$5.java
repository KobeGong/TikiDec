package com.buddy.tiki.service;

import com.buddy.tiki.event.UserEvent.ModifyProfileEvent;
import io.reactivex.functions.Action;
import java.lang.invoke.LambdaForm.Hidden;
import org.greenrobot.eventbus.EventBus;

final /* synthetic */ class UserManager$$Lambda$5 implements Action {
    private final String f989a;
    private final int f990b;
    private final String f991c;

    private UserManager$$Lambda$5(String str, int i, String str2) {
        this.a = str;
        this.b = i;
        this.c = str2;
    }

    public static Action lambdaFactory$(String str, int i, String str2) {
        return new UserManager$$Lambda$5(str, i, str2);
    }

    @Hidden
    public void run() {
        EventBus.getDefault().postSticky(new ModifyProfileEvent(this.a, this.b, this.c));
    }
}
