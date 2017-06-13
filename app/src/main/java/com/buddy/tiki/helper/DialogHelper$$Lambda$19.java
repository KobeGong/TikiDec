package com.buddy.tiki.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$19 implements OnClickListener {
    private final Context f570a;
    private final String f571b;

    private DialogHelper$$Lambda$19(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public static OnClickListener lambdaFactory$(Context context, String str) {
        return new DialogHelper$$Lambda$19(context, str);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        WebBrowserActivity.launchWeb(this.a, this.b);
    }
}
