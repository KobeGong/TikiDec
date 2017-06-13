package com.buddy.tiki.service;

import com.buddy.tiki.util.FileUtil;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;
import okhttp3.ResponseBody;

final /* synthetic */ class DownloadApiManager$$Lambda$1 implements Function {
    private final String f911a;
    private final String f912b;

    private DownloadApiManager$$Lambda$1(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public static Function lambdaFactory$(String str, String str2) {
        return new DownloadApiManager$$Lambda$1(str, str2);
    }

    @Hidden
    public Object apply(Object obj) {
        return Observable.just(FileUtil.cacheResourceFile(this.a, this.b, (ResponseBody) obj));
    }
}
