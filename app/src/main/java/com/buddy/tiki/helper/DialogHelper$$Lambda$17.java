package com.buddy.tiki.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.widget.AppCompatRadioButton;
import com.buddy.tiki.model.user.User;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$17 implements OnClickListener {
    private final AppCompatRadioButton f565a;
    private final AppCompatRadioButton f566b;
    private final Context f567c;
    private final User f568d;

    private DialogHelper$$Lambda$17(AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, Context context, User user) {
        this.a = appCompatRadioButton;
        this.b = appCompatRadioButton2;
        this.c = context;
        this.d = user;
    }

    public static OnClickListener lambdaFactory$(AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, Context context, User user) {
        return new DialogHelper$$Lambda$17(appCompatRadioButton, appCompatRadioButton2, context, user);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showModifyGender$41(this.a, this.b, this.c, this.d, dialogInterface, i);
    }
}
