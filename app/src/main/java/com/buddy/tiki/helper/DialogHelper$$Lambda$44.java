package com.buddy.tiki.helper;

import com.buddy.tiki.model.user.TikiUser;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$44 implements Consumer {
    private final TikiUser f616a;
    private final String f617b;

    private DialogHelper$$Lambda$44(TikiUser tikiUser, String str) {
        this.a = tikiUser;
        this.b = str;
    }

    public static Consumer lambdaFactory$(TikiUser tikiUser, String str) {
        return new DialogHelper$$Lambda$44(tikiUser, str);
    }

    @Hidden
    public void accept(Object obj) {
        DialogHelper.lambda$null$24(this.a, this.b, (Boolean) obj);
    }
}
