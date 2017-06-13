package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.user.TikiUser;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class AddSendFriendsAdapter$$Lambda$1 implements Consumer {
    private final AddSendFriendsAdapter f1627a;
    private final TikiUser f1628b;
    private final FriendHolder f1629c;

    private AddSendFriendsAdapter$$Lambda$1(AddSendFriendsAdapter addSendFriendsAdapter, TikiUser tikiUser, FriendHolder friendHolder) {
        this.a = addSendFriendsAdapter;
        this.b = tikiUser;
        this.c = friendHolder;
    }

    public static Consumer lambdaFactory$(AddSendFriendsAdapter addSendFriendsAdapter, TikiUser tikiUser, FriendHolder friendHolder) {
        return new AddSendFriendsAdapter$$Lambda$1(addSendFriendsAdapter, tikiUser, friendHolder);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m974a(this.b, this.c, obj);
    }
}
