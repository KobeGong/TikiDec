package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.model.im.VideoMessage;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$47 implements OnClickListener {
    private final String[] f620a;
    private final VideoMessage f621b;

    private DialogHelper$$Lambda$47(String[] strArr, VideoMessage videoMessage) {
        this.a = strArr;
        this.b = videoMessage;
    }

    public static OnClickListener lambdaFactory$(String[] strArr, VideoMessage videoMessage) {
        return new DialogHelper$$Lambda$47(strArr, videoMessage);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$null$19(this.a, this.b, dialogInterface, i);
    }
}
