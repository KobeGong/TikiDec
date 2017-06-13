package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$23 implements OnClickListener {
    private final BaseActivity f579a;
    private final String f580b;

    private DialogHelper$$Lambda$23(BaseActivity baseActivity, String str) {
        this.a = baseActivity;
        this.b = str;
    }

    public static OnClickListener lambdaFactory$(BaseActivity baseActivity, String str) {
        return new DialogHelper$$Lambda$23(baseActivity, str);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showImageChooser$47(this.a, this.b, dialogInterface, i);
    }
}
