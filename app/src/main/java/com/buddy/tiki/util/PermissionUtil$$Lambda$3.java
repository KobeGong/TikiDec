package com.buddy.tiki.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.util.phonetype.HuaweiUtils;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PermissionUtil$$Lambda$3 implements OnClickListener {
    private final Context f2436a;

    private PermissionUtil$$Lambda$3(Context context) {
        this.a = context;
    }

    public static OnClickListener lambdaFactory$(Context context) {
        return new PermissionUtil$$Lambda$3(context);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        HuaweiUtils.applyPermission(this.a);
    }
}
