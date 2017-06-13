package com.buddy.tiki.helper;

import com.buddy.tiki.helper.UploadHelper.C03901;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$13 implements Transaction {
    private final String[] f736a;

    private UploadHelper$1$$Lambda$13(String[] strArr) {
        this.a = strArr;
    }

    public static Transaction lambdaFactory$(String[] strArr) {
        return new UploadHelper$1$$Lambda$13(strArr);
    }

    @Hidden
    public void execute(Realm realm) {
        C03901.m159b(this.a, realm);
    }
}
