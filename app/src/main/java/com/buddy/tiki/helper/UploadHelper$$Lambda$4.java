package com.buddy.tiki.helper;

import com.buddy.tiki.service.base.DataLayer;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$$Lambda$4 implements Function {
    private final byte[] f717a;
    private final String[] f718b;
    private final String[] f719c;
    private final int f720d;
    private final int f721e;
    private final boolean f722f;

    private UploadHelper$$Lambda$4(byte[] bArr, String[] strArr, String[] strArr2, int i, int i2, boolean z) {
        this.a = bArr;
        this.b = strArr;
        this.c = strArr2;
        this.d = i;
        this.e = i2;
        this.f = z;
    }

    public static Function lambdaFactory$(byte[] bArr, String[] strArr, String[] strArr2, int i, int i2, boolean z) {
        return new UploadHelper$$Lambda$4(bArr, strArr, strArr2, i, i2, z);
    }

    @Hidden
    public Object apply(Object obj) {
        return DataLayer.getInstance().getTikiResManager().uploadVideo(this.a, this.b, 10, 99).flatMap(UploadHelper$$Lambda$5.lambdaFactory$((String) obj, this.c, this.d, this.e, this.f));
    }
}
