package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.user.TikiUser;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ContactsAdapter$$Lambda$1 implements Consumer {
    private final ContactsAdapter f1752a;
    private final TikiUser f1753b;
    private final FriendHolder f1754c;

    private ContactsAdapter$$Lambda$1(ContactsAdapter contactsAdapter, TikiUser tikiUser, FriendHolder friendHolder) {
        this.a = contactsAdapter;
        this.b = tikiUser;
        this.c = friendHolder;
    }

    public static Consumer lambdaFactory$(ContactsAdapter contactsAdapter, TikiUser tikiUser, FriendHolder friendHolder) {
        return new ContactsAdapter$$Lambda$1(contactsAdapter, tikiUser, friendHolder);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1019a(this.b, this.c, obj);
    }
}
