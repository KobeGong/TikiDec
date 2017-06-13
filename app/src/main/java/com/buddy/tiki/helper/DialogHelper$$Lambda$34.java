package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import com.buddy.tiki.util.IntentUtil;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$34 implements OnClickListener {
    private final AppCompatActivity f600a;

    private DialogHelper$$Lambda$34(AppCompatActivity appCompatActivity) {
        this.a = appCompatActivity;
    }

    public static OnClickListener lambdaFactory$(AppCompatActivity appCompatActivity) {
        return new DialogHelper$$Lambda$34(appCompatActivity);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        IntentUtil.startInstalledAppDetailsActivity(this.a);
    }
}
