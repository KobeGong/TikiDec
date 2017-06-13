package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$24 implements OnClickListener {
    private final BaseFragment f581a;
    private final String f582b;

    private DialogHelper$$Lambda$24(BaseFragment baseFragment, String str) {
        this.a = baseFragment;
        this.b = str;
    }

    public static OnClickListener lambdaFactory$(BaseFragment baseFragment, String str) {
        return new DialogHelper$$Lambda$24(baseFragment, str);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showImageChooser$48(this.a, this.b, dialogInterface, i);
    }
}
