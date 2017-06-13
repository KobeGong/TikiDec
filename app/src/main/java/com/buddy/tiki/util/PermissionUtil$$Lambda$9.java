package com.buddy.tiki.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PermissionUtil$$Lambda$9 implements OnClickListener {
    private final Context f2442a;

    private PermissionUtil$$Lambda$9(Context context) {
        this.a = context;
    }

    public static OnClickListener lambdaFactory$(Context context) {
        return new PermissionUtil$$Lambda$9(context);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        PermissionUtil.m1577a(this.a, dialogInterface, i);
    }
}
