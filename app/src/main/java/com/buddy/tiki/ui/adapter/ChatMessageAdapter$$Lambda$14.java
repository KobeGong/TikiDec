package com.buddy.tiki.ui.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageAdapter$$Lambda$14 implements OnClickListener {
    private static final ChatMessageAdapter$$Lambda$14 f1674a = new ChatMessageAdapter$$Lambda$14();

    private ChatMessageAdapter$$Lambda$14() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        ChatMessageAdapter.m999b(dialogInterface, i);
    }
}
