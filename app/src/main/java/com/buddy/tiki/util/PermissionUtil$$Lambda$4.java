package com.buddy.tiki.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PermissionUtil$$Lambda$4 implements OnClickListener {
    private final Runnable f2437a;

    private PermissionUtil$$Lambda$4(Runnable runnable) {
        this.a = runnable;
    }

    public static OnClickListener lambdaFactory$(Runnable runnable) {
        return new PermissionUtil$$Lambda$4(runnable);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        PermissionUtil.m1587c(this.a, dialogInterface, i);
    }
}
