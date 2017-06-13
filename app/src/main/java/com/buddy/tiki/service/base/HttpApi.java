package com.buddy.tiki.service.base;

import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.base.BusinessDomain;
import com.buddy.tiki.helper.ErrorCodeHelper;
import com.buddy.tiki.helper.NetHelper;
import com.buddy.tiki.util.HttpsWrapperUtil;
import com.buddy.tiki.util.UserAgentTool;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.handler.codec.rtsp.RtspHeaders.Names;
import io.netty.handler.traffic.AbstractTrafficShapingHandler;
import io.realm.RealmObject;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.tls.OkHostnameVerifier;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpApi {
    public final Retrofit f1053a;
    private final Interceptor f1054b;
    private final OkHttpClient f1055c;
    private final Retrofit f1056d;
    private final Retrofit f1057e;

    class C04131 implements ExclusionStrategy {
        final /* synthetic */ HttpApi f1051a;

        C04131(HttpApi this$0) {
            this.f1051a = this$0;
        }

        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaringClass().equals(RealmObject.class);
        }

        public boolean shouldSkipClass(Class<?> cls) {
            return false;
        }
    }

    private static class SingletonHolder {
        private static final HttpApi f1052a = new HttpApi();
    }

    static /* synthetic */ Response m401a(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetHelper.hadNetworkAvailable(ChatApp.getInstance())) {
            ErrorCodeHelper.getInstance().handleError(-99, (int) C0376R.string.unknown_net_error);
        }
        return chain.proceed(request.newBuilder().url(request.url().newBuilder().addQueryParameter("channel", BusinessDomain.f405c).build()).addHeader(Names.USER_AGENT, new UserAgentTool().toHeaderValue()).build());
    }

    private HttpApi() {
        this.f1054b = HttpApi$$Lambda$1.lambdaFactory$();
        Builder builder = new Builder().connectTimeout(30000, TimeUnit.MILLISECONDS).readTimeout(AbstractTrafficShapingHandler.DEFAULT_MAX_TIME, TimeUnit.MILLISECONDS).writeTimeout(30000, TimeUnit.MILLISECONDS);
        OkHttpClient wxHttpClient = builder.connectTimeout(30000, TimeUnit.MILLISECONDS).readTimeout(AbstractTrafficShapingHandler.DEFAULT_MAX_TIME, TimeUnit.MILLISECONDS).writeTimeout(30000, TimeUnit.MILLISECONDS).build();
        OkHttpClient upHttpClient = builder.connectTimeout(60000, TimeUnit.MILLISECONDS).readTimeout(60000, TimeUnit.MILLISECONDS).writeTimeout(60000, TimeUnit.MILLISECONDS).addInterceptor(this.f1054b).build();
        Builder httpsBuilder = HttpsWrapperUtil.wrapHttps(ChatApp.getInstance(), builder.hostnameVerifier(HttpApi$$Lambda$2.lambdaFactory$()));
        if (httpsBuilder == null) {
            throw new NullPointerException("https \u6784\u9020\u5931\u8d25");
        }
        this.f1055c = httpsBuilder.build();
        Gson gson = new GsonBuilder().setExclusionStrategies(new C04131(this)).create();
        this.f1053a = new Retrofit.Builder().client(this.f1055c).baseUrl(BusinessDomain.f403a).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        this.f1056d = new Retrofit.Builder().client(upHttpClient).baseUrl(BusinessDomain.f404b).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        this.f1057e = new Retrofit.Builder().client(wxHttpClient).baseUrl("https://api.weixin.qq.com").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    static /* synthetic */ boolean m402a(String hostname, SSLSession session) {
        return hostname.equalsIgnoreCase(BusinessDomain.f403a) || OkHostnameVerifier.INSTANCE.verify(hostname, session);
    }

    public static HttpApi getInstance() {
        return SingletonHolder.f1052a;
    }

    public <T> T getServiceInstance(Class<T> classType) {
        return this.f1053a.create(classType);
    }

    public <T> T getUpServiceInstance(Class<T> classType) {
        return this.f1056d.create(classType);
    }

    public <T> T getWxServiceInstance(Class<T> classType) {
        return this.f1057e.create(classType);
    }

    public OkHttpClient getOkHttpClientInstance() {
        return this.f1055c;
    }
}
