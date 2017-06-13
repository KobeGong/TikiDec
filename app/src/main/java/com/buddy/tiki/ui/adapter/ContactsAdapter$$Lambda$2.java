package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.user.TikiUser;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ContactsAdapter$$Lambda$2 implements OnClickListener {
    private final ContactsAdapter f1755a;
    private final TikiUser f1756b;

    private ContactsAdapter$$Lambda$2(ContactsAdapter contactsAdapter, TikiUser tikiUser) {
        this.a = contactsAdapter;
        this.b = tikiUser;
    }

    public static OnClickListener lambdaFactory$(ContactsAdapter contactsAdapter, TikiUser tikiUser) {
        return new ContactsAdapter$$Lambda$2(contactsAdapter, tikiUser);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1018a(this.b, view);
    }
}
