package im.facechat.common.p043c;

import im.facechat.common.p043c.C1643b.C1641a;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

/* compiled from: FacechatInterceptor */
public class C1647d implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().addHeader("User-Agent", new C1641a().m7933b()).build());
    }
}
