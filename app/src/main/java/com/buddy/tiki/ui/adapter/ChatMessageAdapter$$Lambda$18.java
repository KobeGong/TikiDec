package com.buddy.tiki.ui.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageAdapter$$Lambda$18 implements OnCancelListener {
    private static final ChatMessageAdapter$$Lambda$18 f1679a = new ChatMessageAdapter$$Lambda$18();

    private ChatMessageAdapter$$Lambda$18() {
    }

    public static OnCancelListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onCancel(DialogInterface dialogInterface) {
        ChatMessageAdapter.m1001c(dialogInterface);
    }
}
