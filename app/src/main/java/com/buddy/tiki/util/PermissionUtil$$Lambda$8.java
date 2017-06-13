package com.buddy.tiki.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PermissionUtil$$Lambda$8 implements OnClickListener {
    private final Runnable f2441a;

    private PermissionUtil$$Lambda$8(Runnable runnable) {
        this.a = runnable;
    }

    public static OnClickListener lambdaFactory$(Runnable runnable) {
        return new PermissionUtil$$Lambda$8(runnable);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        PermissionUtil.m1579a(this.a, dialogInterface, i);
    }
}
