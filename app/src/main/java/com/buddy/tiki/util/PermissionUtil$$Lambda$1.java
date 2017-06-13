package com.buddy.tiki.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.util.phonetype.QikuUtils;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PermissionUtil$$Lambda$1 implements OnClickListener {
    private final Context f2434a;

    private PermissionUtil$$Lambda$1(Context context) {
        this.a = context;
    }

    public static OnClickListener lambdaFactory$(Context context) {
        return new PermissionUtil$$Lambda$1(context);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        QikuUtils.applyPermission(this.a);
    }
}
