package com.buddy.tiki.helper;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.user.TikiUser;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$3 implements Consumer {
    private final DialogHelper f607a;
    private final Context f608b;
    private final TikiUser f609c;
    private final String f610d;
    private final FragmentManager f611e;

    private DialogHelper$$Lambda$3(DialogHelper dialogHelper, Context context, TikiUser tikiUser, String str, FragmentManager fragmentManager) {
        this.a = dialogHelper;
        this.b = context;
        this.c = tikiUser;
        this.d = str;
        this.e = fragmentManager;
    }

    public static Consumer lambdaFactory$(DialogHelper dialogHelper, Context context, TikiUser tikiUser, String str, FragmentManager fragmentManager) {
        return new DialogHelper$$Lambda$3(dialogHelper, context, tikiUser, str, fragmentManager);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.lambda$showComplainDialog$5(this.b, this.c, this.d, this.e, (OperInfo) obj);
    }
}
