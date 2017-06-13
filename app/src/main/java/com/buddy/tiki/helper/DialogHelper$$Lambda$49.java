package com.buddy.tiki.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import com.buddy.tiki.model.user.User;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$49 implements OnClickListener {
    private final String[] f623a;
    private final Bitmap f624b;
    private final User f625c;
    private final Context f626d;

    private DialogHelper$$Lambda$49(String[] strArr, Bitmap bitmap, User user, Context context) {
        this.a = strArr;
        this.b = bitmap;
        this.c = user;
        this.d = context;
    }

    public static OnClickListener lambdaFactory$(String[] strArr, Bitmap bitmap, User user, Context context) {
        return new DialogHelper$$Lambda$49(strArr, bitmap, user, context);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$null$15(this.a, this.b, this.c, this.d, dialogInterface, i);
    }
}
