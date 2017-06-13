package com.buddy.tiki.ui.activity;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$9 implements Transaction {
    private final VideoRecordResultActivity f1567a;
    private final String[] f1568b;

    private VideoRecordResultActivity$$Lambda$9(VideoRecordResultActivity videoRecordResultActivity, String[] strArr) {
        this.a = videoRecordResultActivity;
        this.b = strArr;
    }

    public static Transaction lambdaFactory$(VideoRecordResultActivity videoRecordResultActivity, String[] strArr) {
        return new VideoRecordResultActivity$$Lambda$9(videoRecordResultActivity, strArr);
    }

    @Hidden
    public void execute(Realm realm) {
        this.a.m923a(this.b, realm);
    }
}
