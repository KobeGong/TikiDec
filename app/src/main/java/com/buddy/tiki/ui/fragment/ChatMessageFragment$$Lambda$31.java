package com.buddy.tiki.ui.fragment;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$31 implements Transaction {
    private final ChatMessageFragment f2073a;

    private ChatMessageFragment$$Lambda$31(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Transaction lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$31(chatMessageFragment);
    }

    @Hidden
    public void execute(Realm realm) {
        this.a.m1277b(realm);
    }
}
