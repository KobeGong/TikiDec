package com.buddy.tiki.helper;

import android.content.Context;
import com.buddy.tiki.model.user.TikiUser;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$38 implements Consumer {
    private final TikiUser f604a;
    private final Context f605b;

    private DialogHelper$$Lambda$38(TikiUser tikiUser, Context context) {
        this.a = tikiUser;
        this.b = context;
    }

    public static Consumer lambdaFactory$(TikiUser tikiUser, Context context) {
        return new DialogHelper$$Lambda$38(tikiUser, context);
    }

    @Hidden
    public void accept(Object obj) {
        DialogHelper.lambda$null$36(this.a, this.b, (Boolean) obj);
    }
}
