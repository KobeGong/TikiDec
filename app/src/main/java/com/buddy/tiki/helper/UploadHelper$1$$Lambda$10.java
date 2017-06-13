package com.buddy.tiki.helper;

import com.buddy.tiki.helper.UploadHelper.C03901;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$10 implements Transaction {
    private final String[] f733a;

    private UploadHelper$1$$Lambda$10(String[] strArr) {
        this.a = strArr;
    }

    public static Transaction lambdaFactory$(String[] strArr) {
        return new UploadHelper$1$$Lambda$10(strArr);
    }

    @Hidden
    public void execute(Realm realm) {
        C03901.m152a(this.a, realm);
    }
}
