package com.buddy.tiki.helper;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;

final /* synthetic */ class UploadHelper$$Lambda$2 implements Consumer {
    private final UploadHelper f709a;
    private final String[] f710b;
    private final int f711c;
    private final int f712d;
    private final String[] f713e;
    private final boolean f714f;

    private UploadHelper$$Lambda$2(UploadHelper uploadHelper, String[] strArr, int i, int i2, String[] strArr2, boolean z) {
        this.a = uploadHelper;
        this.b = strArr;
        this.c = i;
        this.d = i2;
        this.e = strArr2;
        this.f = z;
    }

    public static Consumer lambdaFactory$(UploadHelper uploadHelper, String[] strArr, int i, int i2, String[] strArr2, boolean z) {
        return new UploadHelper$$Lambda$2(uploadHelper, strArr, i, i2, strArr2, z);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m175a(this.b, this.c, this.d, this.e, this.f, (List) obj);
    }
}
