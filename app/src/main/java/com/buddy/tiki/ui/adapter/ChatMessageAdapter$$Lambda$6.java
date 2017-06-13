package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.user.UserChatMessage;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageAdapter$$Lambda$6 implements Consumer {
    private final ChatMessageAdapter f1690a;
    private final UserChatMessage f1691b;

    private ChatMessageAdapter$$Lambda$6(ChatMessageAdapter chatMessageAdapter, UserChatMessage userChatMessage) {
        this.a = chatMessageAdapter;
        this.b = userChatMessage;
    }

    public static Consumer lambdaFactory$(ChatMessageAdapter chatMessageAdapter, UserChatMessage userChatMessage) {
        return new ChatMessageAdapter$$Lambda$6(chatMessageAdapter, userChatMessage);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1008b(this.b, obj);
    }
}
