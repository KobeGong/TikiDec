package com.buddy.tiki.ui.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageAdapter$$Lambda$15 implements OnCancelListener {
    private static final ChatMessageAdapter$$Lambda$15 f1675a = new ChatMessageAdapter$$Lambda$15();

    private ChatMessageAdapter$$Lambda$15() {
    }

    public static OnCancelListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onCancel(DialogInterface dialogInterface) {
        ChatMessageAdapter.m998b(dialogInterface);
    }
}
