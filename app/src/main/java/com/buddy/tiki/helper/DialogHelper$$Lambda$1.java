package com.buddy.tiki.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.model.user.TikiUser;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$1 implements OnClickListener {
    private final DialogHelper f572a;
    private final TikiUser f573b;
    private final Context f574c;

    private DialogHelper$$Lambda$1(DialogHelper dialogHelper, TikiUser tikiUser, Context context) {
        this.a = dialogHelper;
        this.b = tikiUser;
        this.c = context;
    }

    public static OnClickListener lambdaFactory$(DialogHelper dialogHelper, TikiUser tikiUser, Context context) {
        return new DialogHelper$$Lambda$1(dialogHelper, tikiUser, context);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.lambda$showFriendDialog$0(this.b, this.c, dialogInterface, i);
    }
}
