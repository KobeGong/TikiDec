package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.user.User;
import com.buddy.tiki.ui.activity.WebBrowserActivity.TikiOpen;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebBrowserActivity$TikiOpen$$Lambda$6 implements Consumer {
    private final TikiOpen f1604a;

    private WebBrowserActivity$TikiOpen$$Lambda$6(TikiOpen tikiOpen) {
        this.a = tikiOpen;
    }

    public static Consumer lambdaFactory$(TikiOpen tikiOpen) {
        return new WebBrowserActivity$TikiOpen$$Lambda$6(tikiOpen);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.startFriendCall((User) obj);
    }
}
