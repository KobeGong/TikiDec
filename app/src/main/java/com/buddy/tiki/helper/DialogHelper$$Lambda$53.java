package com.buddy.tiki.helper;

import com.buddy.tiki.model.user.User;
import com.buddy.tiki.service.base.DataLayer;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$53 implements Function {
    private final User f631a;
    private final String[] f632b;
    private final int f633c;

    private DialogHelper$$Lambda$53(User user, String[] strArr, int i) {
        this.a = user;
        this.b = strArr;
        this.c = i;
    }

    public static Function lambdaFactory$(User user, String[] strArr, int i) {
        return new DialogHelper$$Lambda$53(user, strArr, i);
    }

    @Hidden
    public Object apply(Object obj) {
        return DataLayer.getInstance().getFeedbackManager().complainAction(this.a.getUid(), (String) obj, this.b[this.c], this.b[this.c]);
    }
}
