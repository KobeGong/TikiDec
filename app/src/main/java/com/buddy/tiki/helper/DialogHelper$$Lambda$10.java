package com.buddy.tiki.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.widget.AppCompatEditText;
import com.buddy.tiki.model.user.TikiUser;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$10 implements OnClickListener {
    private final DialogHelper f548a;
    private final AppCompatEditText f549b;
    private final TikiUser f550c;
    private final Context f551d;
    private final String f552e;

    private DialogHelper$$Lambda$10(DialogHelper dialogHelper, AppCompatEditText appCompatEditText, TikiUser tikiUser, Context context, String str) {
        this.a = dialogHelper;
        this.b = appCompatEditText;
        this.c = tikiUser;
        this.d = context;
        this.e = str;
    }

    public static OnClickListener lambdaFactory$(DialogHelper dialogHelper, AppCompatEditText appCompatEditText, TikiUser tikiUser, Context context, String str) {
        return new DialogHelper$$Lambda$10(dialogHelper, appCompatEditText, tikiUser, context, str);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.lambda$showEditNickDialog$26(this.b, this.c, this.d, this.e, dialogInterface, i);
    }
}
