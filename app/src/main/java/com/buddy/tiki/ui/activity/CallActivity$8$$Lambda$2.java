package com.buddy.tiki.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.geekint.flying.bitmap.tool.BitmapTool;
import io.reactivex.Observable;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.concurrent.Callable;

final /* synthetic */ class CallActivity$8$$Lambda$2 implements Callable {
    private final Bitmap f1238a;

    private CallActivity$8$$Lambda$2(Bitmap bitmap) {
        this.a = bitmap;
    }

    public static Callable lambdaFactory$(Bitmap bitmap) {
        return new CallActivity$8$$Lambda$2(bitmap);
    }

    @Hidden
    public Object call() {
        return Observable.just(BitmapTool.bitmap2Bytes(this.a, CompressFormat.WEBP, 50));
    }
}
