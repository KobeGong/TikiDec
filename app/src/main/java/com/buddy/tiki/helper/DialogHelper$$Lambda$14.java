package com.buddy.tiki.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.model.user.TikiUser;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$14 implements OnClickListener {
    private final DialogHelper f556a;
    private final TikiUser f557b;
    private final Context f558c;
    private final String f559d;

    private DialogHelper$$Lambda$14(DialogHelper dialogHelper, TikiUser tikiUser, Context context, String str) {
        this.a = dialogHelper;
        this.b = tikiUser;
        this.c = context;
        this.d = str;
    }

    public static OnClickListener lambdaFactory$(DialogHelper dialogHelper, TikiUser tikiUser, Context context, String str) {
        return new DialogHelper$$Lambda$14(dialogHelper, tikiUser, context, str);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.lambda$showConfirmDeleteDialog$33(this.b, this.c, this.d, dialogInterface, i);
    }
}
