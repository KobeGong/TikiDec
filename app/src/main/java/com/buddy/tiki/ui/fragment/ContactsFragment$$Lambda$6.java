package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ContactsFragment$$Lambda$6 implements Consumer {
    private final ContactsFragment f2129a;

    private ContactsFragment$$Lambda$6(ContactsFragment contactsFragment) {
        this.a = contactsFragment;
    }

    public static Consumer lambdaFactory$(ContactsFragment contactsFragment) {
        return new ContactsFragment$$Lambda$6(contactsFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1304a(obj);
    }
}
