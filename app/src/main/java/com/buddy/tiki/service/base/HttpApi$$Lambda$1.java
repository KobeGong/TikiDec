package com.buddy.tiki.service.base;

import java.lang.invoke.LambdaForm.Hidden;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

final /* synthetic */ class HttpApi$$Lambda$1 implements Interceptor {
    private static final HttpApi$$Lambda$1 f1049a = new HttpApi$$Lambda$1();

    private HttpApi$$Lambda$1() {
    }

    public static Interceptor lambdaFactory$() {
        return a;
    }

    @Hidden
    public Response intercept(Chain chain) {
        return HttpApi.m401a(chain);
    }
}
