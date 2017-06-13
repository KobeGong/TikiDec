package com.buddy.tiki.helper;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$$Lambda$8 implements Transaction {
    private final String[] f731a;

    private UploadHelper$$Lambda$8(String[] strArr) {
        this.a = strArr;
    }

    public static Transaction lambdaFactory$(String[] strArr) {
        return new UploadHelper$$Lambda$8(strArr);
    }

    @Hidden
    public void execute(Realm realm) {
        UploadHelper.m173a(this.a, realm);
    }
}
