package com.buddy.tiki.ui.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import com.buddy.tiki.model.user.UserChatMessage;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageAdapter$$Lambda$2 implements Consumer {
    private final ChatMessageAdapter f1681a;
    private final UserChatMessage f1682b;
    private final ViewHolder f1683c;

    private ChatMessageAdapter$$Lambda$2(ChatMessageAdapter chatMessageAdapter, UserChatMessage userChatMessage, ViewHolder viewHolder) {
        this.a = chatMessageAdapter;
        this.b = userChatMessage;
        this.c = viewHolder;
    }

    public static Consumer lambdaFactory$(ChatMessageAdapter chatMessageAdapter, UserChatMessage userChatMessage, ViewHolder viewHolder) {
        return new ChatMessageAdapter$$Lambda$2(chatMessageAdapter, userChatMessage, viewHolder);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1003a(this.b, this.c, obj);
    }
}
