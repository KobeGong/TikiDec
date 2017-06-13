package com.buddy.tiki.helper;

import com.buddy.tiki.helper.UploadHelper.C03901;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$7 implements Transaction {
    private final String[] f748a;
    private final String f749b;

    private UploadHelper$1$$Lambda$7(String[] strArr, String str) {
        this.a = strArr;
        this.b = str;
    }

    public static Transaction lambdaFactory$(String[] strArr, String str) {
        return new UploadHelper$1$$Lambda$7(strArr, str);
    }

    @Hidden
    public void execute(Realm realm) {
        C03901.m154a(this.a, this.b, realm);
    }
}
