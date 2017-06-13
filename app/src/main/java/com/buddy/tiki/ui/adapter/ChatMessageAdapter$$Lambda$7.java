package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.user.UserChatMessage;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageAdapter$$Lambda$7 implements Consumer {
    private final ChatMessageAdapter f1692a;
    private final UserChatMessage f1693b;

    private ChatMessageAdapter$$Lambda$7(ChatMessageAdapter chatMessageAdapter, UserChatMessage userChatMessage) {
        this.a = chatMessageAdapter;
        this.b = userChatMessage;
    }

    public static Consumer lambdaFactory$(ChatMessageAdapter chatMessageAdapter, UserChatMessage userChatMessage) {
        return new ChatMessageAdapter$$Lambda$7(chatMessageAdapter, userChatMessage);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1004a(this.b, obj);
    }
}
