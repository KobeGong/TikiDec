package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$48 implements OnCancelListener {
    private final Bitmap f622a;

    private DialogHelper$$Lambda$48(Bitmap bitmap) {
        this.a = bitmap;
    }

    public static OnCancelListener lambdaFactory$(Bitmap bitmap) {
        return new DialogHelper$$Lambda$48(bitmap);
    }

    @Hidden
    public void onCancel(DialogInterface dialogInterface) {
        DialogHelper.lambda$null$7(this.a, dialogInterface);
    }
}
