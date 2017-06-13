package com.buddy.tiki.ui.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageAdapter$$Lambda$12 implements OnCancelListener {
    private static final ChatMessageAdapter$$Lambda$12 f1672a = new ChatMessageAdapter$$Lambda$12();

    private ChatMessageAdapter$$Lambda$12() {
    }

    public static OnCancelListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onCancel(DialogInterface dialogInterface) {
        ChatMessageAdapter.m991a(dialogInterface);
    }
}
