package com.buddy.tiki.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$5 implements Consumer {
    private final Context f644a;
    private final Bitmap f645b;
    private final User f646c;
    private final FragmentManager f647d;

    private DialogHelper$$Lambda$5(Context context, Bitmap bitmap, User user, FragmentManager fragmentManager) {
        this.a = context;
        this.b = bitmap;
        this.c = user;
        this.d = fragmentManager;
    }

    public static Consumer lambdaFactory$(Context context, Bitmap bitmap, User user, FragmentManager fragmentManager) {
        return new DialogHelper$$Lambda$5(context, bitmap, user, fragmentManager);
    }

    @Hidden
    public void accept(Object obj) {
        DialogHelper.lambda$showComplainDialog$16(this.a, this.b, this.c, this.d, (OperInfo) obj);
    }
}
