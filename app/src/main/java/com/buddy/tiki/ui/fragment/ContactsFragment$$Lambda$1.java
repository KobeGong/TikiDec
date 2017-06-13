package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ContactsFragment$$Lambda$1 implements Consumer {
    private final ContactsFragment f2124a;

    private ContactsFragment$$Lambda$1(ContactsFragment contactsFragment) {
        this.a = contactsFragment;
    }

    public static Consumer lambdaFactory$(ContactsFragment contactsFragment) {
        return new ContactsFragment$$Lambda$1(contactsFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1303a((CharSequence) obj);
    }
}
