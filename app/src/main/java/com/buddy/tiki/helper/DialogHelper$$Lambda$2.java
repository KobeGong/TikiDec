package com.buddy.tiki.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.model.user.TikiUser;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$2 implements OnClickListener {
    private final DialogHelper f593a;
    private final TikiUser f594b;
    private final Context f595c;

    private DialogHelper$$Lambda$2(DialogHelper dialogHelper, TikiUser tikiUser, Context context) {
        this.a = dialogHelper;
        this.b = tikiUser;
        this.c = context;
    }

    public static OnClickListener lambdaFactory$(DialogHelper dialogHelper, TikiUser tikiUser, Context context) {
        return new DialogHelper$$Lambda$2(dialogHelper, tikiUser, context);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.lambda$showChatMessageMenuDialog$1(this.b, this.c, dialogInterface, i);
    }
}
