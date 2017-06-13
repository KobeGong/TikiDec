package com.buddy.tiki.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.util.phonetype.MiuiUtils;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PermissionUtil$$Lambda$7 implements OnClickListener {
    private final Context f2440a;

    private PermissionUtil$$Lambda$7(Context context) {
        this.a = context;
    }

    public static OnClickListener lambdaFactory$(Context context) {
        return new PermissionUtil$$Lambda$7(context);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        MiuiUtils.applyMiuiPermission(this.a);
    }
}
