package com.buddy.tiki.helper;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.im.VideoMessage;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$7 implements Consumer {
    private final Context f649a;
    private final VideoMessage f650b;
    private final FragmentManager f651c;

    private DialogHelper$$Lambda$7(Context context, VideoMessage videoMessage, FragmentManager fragmentManager) {
        this.a = context;
        this.b = videoMessage;
        this.c = fragmentManager;
    }

    public static Consumer lambdaFactory$(Context context, VideoMessage videoMessage, FragmentManager fragmentManager) {
        return new DialogHelper$$Lambda$7(context, videoMessage, fragmentManager);
    }

    @Hidden
    public void accept(Object obj) {
        DialogHelper.lambda$showComplainDialog$20(this.a, this.b, this.c, (OperInfo) obj);
    }
}
