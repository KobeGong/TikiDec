package com.buddy.tiki.ui.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.buddy.tiki.model.user.UserChatMessage;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageAdapter$$Lambda$16 implements OnClickListener {
    private final UserChatMessage f1676a;
    private final ViewHolder f1677b;

    private ChatMessageAdapter$$Lambda$16(UserChatMessage userChatMessage, ViewHolder viewHolder) {
        this.a = userChatMessage;
        this.b = viewHolder;
    }

    public static OnClickListener lambdaFactory$(UserChatMessage userChatMessage, ViewHolder viewHolder) {
        return new ChatMessageAdapter$$Lambda$16(userChatMessage, viewHolder);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        ChatMessageAdapter.m996a(this.a, this.b, dialogInterface, i);
    }
}
