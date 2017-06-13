package com.buddy.tiki.helper;

import android.content.Context;
import android.widget.Toast;
import com.buddy.tiki.C0376R;
import io.reactivex.functions.Action;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$55 implements Action {
    private final Context f635a;

    private DialogHelper$$Lambda$55(Context context) {
        this.a = context;
    }

    public static Action lambdaFactory$(Context context) {
        return new DialogHelper$$Lambda$55(context);
    }

    @Hidden
    public void run() {
        Toast.makeText(this.a, C0376R.string.complain_success, 0).show();
    }
}
