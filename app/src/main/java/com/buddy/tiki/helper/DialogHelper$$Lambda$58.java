package com.buddy.tiki.helper;

import com.buddy.tiki.C0376R;
import com.buddy.tiki.util.ToastUtil;
import io.reactivex.functions.Action;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$58 implements Action {
    private static final DialogHelper$$Lambda$58 f642a = new DialogHelper$$Lambda$58();

    private DialogHelper$$Lambda$58() {
    }

    public static Action lambdaFactory$() {
        return a;
    }

    @Hidden
    public void run() {
        ToastUtil.getInstance().show((int) C0376R.string.complain_success);
    }
}
