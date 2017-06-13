package com.buddy.tiki.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$$Lambda$13 implements OnDismissListener {
    private final VideoRecordActivity f1504a;

    private VideoRecordActivity$$Lambda$13(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public static OnDismissListener lambdaFactory$(VideoRecordActivity videoRecordActivity) {
        return new VideoRecordActivity$$Lambda$13(videoRecordActivity);
    }

    @Hidden
    public void onDismiss(DialogInterface dialogInterface) {
        this.a.m894a(dialogInterface);
    }
}
