package com.buddy.tiki.helper;

import com.buddy.tiki.service.base.DataLayer;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$$Lambda$5 implements Function {
    private final String f723a;
    private final String[] f724b;
    private final int f725c;
    private final int f726d;
    private final boolean f727e;

    private UploadHelper$$Lambda$5(String str, String[] strArr, int i, int i2, boolean z) {
        this.a = str;
        this.b = strArr;
        this.c = i;
        this.d = i2;
        this.e = z;
    }

    public static Function lambdaFactory$(String str, String[] strArr, int i, int i2, boolean z) {
        return new UploadHelper$$Lambda$5(str, strArr, i, i2, z);
    }

    @Hidden
    public Object apply(Object obj) {
        return DataLayer.getInstance().getChatManager().sendVideoMessage((String) obj, this.a, this.b, this.c, this.d, this.e);
    }
}
