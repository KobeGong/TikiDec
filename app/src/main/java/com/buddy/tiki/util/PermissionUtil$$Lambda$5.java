package com.buddy.tiki.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.util.phonetype.MeizuUtils;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PermissionUtil$$Lambda$5 implements OnClickListener {
    private final Context f2438a;

    private PermissionUtil$$Lambda$5(Context context) {
        this.a = context;
    }

    public static OnClickListener lambdaFactory$(Context context) {
        return new PermissionUtil$$Lambda$5(context);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        MeizuUtils.applyPermission(this.a);
    }
}
