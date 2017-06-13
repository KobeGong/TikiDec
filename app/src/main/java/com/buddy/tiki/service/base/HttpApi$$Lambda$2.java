package com.buddy.tiki.service.base;

import java.lang.invoke.LambdaForm.Hidden;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

final /* synthetic */ class HttpApi$$Lambda$2 implements HostnameVerifier {
    private static final HttpApi$$Lambda$2 f1050a = new HttpApi$$Lambda$2();

    private HttpApi$$Lambda$2() {
    }

    public static HostnameVerifier lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean verify(String str, SSLSession sSLSession) {
        return HttpApi.m402a(str, sSLSession);
    }
}
