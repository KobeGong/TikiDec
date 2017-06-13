package com.buddy.tiki.helper;

import com.buddy.tiki.model.user.TikiUser;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$41 implements Consumer {
    private final TikiUser f613a;

    private DialogHelper$$Lambda$41(TikiUser tikiUser) {
        this.a = tikiUser;
    }

    public static Consumer lambdaFactory$(TikiUser tikiUser) {
        return new DialogHelper$$Lambda$41(tikiUser);
    }

    @Hidden
    public void accept(Object obj) {
        DialogHelper.lambda$null$31(this.a, (Boolean) obj);
    }
}
