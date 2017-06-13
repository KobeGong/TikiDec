package com.buddy.tiki.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.model.user.TikiUser;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$57 implements OnClickListener {
    private final DialogHelper f637a;
    private final String[] f638b;
    private final TikiUser f639c;
    private final Context f640d;
    private final String f641e;

    private DialogHelper$$Lambda$57(DialogHelper dialogHelper, String[] strArr, TikiUser tikiUser, Context context, String str) {
        this.a = dialogHelper;
        this.b = strArr;
        this.c = tikiUser;
        this.d = context;
        this.e = str;
    }

    public static OnClickListener lambdaFactory$(DialogHelper dialogHelper, String[] strArr, TikiUser tikiUser, Context context, String str) {
        return new DialogHelper$$Lambda$57(dialogHelper, strArr, tikiUser, context, str);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.lambda$null$4(this.b, this.c, this.d, this.e, dialogInterface, i);
    }
}
