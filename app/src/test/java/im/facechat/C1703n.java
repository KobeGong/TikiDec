package im.facechat;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import im.facechat.common.p043c.C1646c;
import im.facechat.common.p043c.C1647d;
import im.facechat.common.p043c.C1648f;
import im.facechat.common.p045b.C1639b;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient$Builder;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.tls.OkHostnameVerifier;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NetworkHelper */
class C1703n {
    private static final Class<?> f11380a = C1703n.class;
    private final OkHttpClient f11381b;
    private final HostnameVerifier f11382c;
    private SSLSocketFactory f11383d;
    private X509TrustManager f11384e;
    private long f11385f;

    /* compiled from: NetworkHelper */
    class C17001 implements HostnameVerifier {
        final /* synthetic */ C1703n f11377a;

        C17001(C1703n c1703n) {
            this.f11377a = c1703n;
        }

        public boolean verify(String str, SSLSession sSLSession) {
            return str.equalsIgnoreCase("16.api.facechat.im") || OkHostnameVerifier.INSTANCE.verify(str, sSLSession);
        }
    }

    /* compiled from: NetworkHelper */
    class C17012 implements Callback {
        final /* synthetic */ C1703n f11378a;

        C17012(C1703n c1703n) {
            this.f11378a = c1703n;
        }

        public void onFailure(Call call, IOException iOException) {
            C1639b.m7917a(C1703n.f11380a, "cookie request fail", iOException);
        }

        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String string = response.body().string();
                C1639b.m7916a(C1703n.f11380a, "cookies " + string);
                m8107a(string);
                return;
            }
            C1639b.m7921c(C1703n.f11380a, "cookie response fail");
        }

        private void m8107a(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt(SelectCountryActivity.EXTRA_COUNTRY_CODE, -1);
                String optString = jSONObject.optString("result");
                if (optInt == 0) {
                    C1728q.m8217e(optString);
                }
            } catch (JSONException e) {
            }
        }
    }

    /* compiled from: NetworkHelper */
    private static class C1702a {
        private static final C1703n f11379a = new C1703n();
    }

    private C1703n() {
        this.f11381b = new OkHttpClient$Builder().readTimeout(15, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS).addInterceptor(new C1647d()).retryOnConnectionFailure(true).build();
        this.f11382c = new C17001(this);
    }

    static C1703n m8109a() {
        return C1702a.f11379a;
    }

    void m8116a(Context context, FCBaseParams fCBaseParams, C1729s c1729s, Callback callback) {
        if (m8114a(context, callback)) {
            OkHttpClient build = this.f11381b.newBuilder().sslSocketFactory(this.f11383d, this.f11384e).hostnameVerifier(this.f11382c).build();
            String a = ParamUtil.m7741a(fCBaseParams, c1729s, C1728q.m8212c(), true);
            C1639b.m7921c(f11380a, "paramUrl " + a);
            RequestBody a2 = m8113a("?" + a, callback);
            if (a2 != null) {
                build.newCall(new Builder().url("https://16.api.facechat.im/client/config/get_top_configs").post(a2).build()).enqueue(callback);
            }
        }
    }

    void m8117a(@NonNull Context context, String str, String str2, int i, String str3, @NonNull Callback callback) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && m8114a(context, callback)) {
            RequestBody a = m8113a("?" + ParamUtil.m7744a(str, str2, i, str3, context.getPackageName()), callback);
            if (a != null) {
                this.f11381b.newBuilder().sslSocketFactory(this.f11383d, this.f11384e).hostnameVerifier(this.f11382c).build().newCall(new Builder().url("https://16.api.facechat.im/client/config/set_apns_token").post(a).build()).enqueue(callback);
            }
        }
    }

    void m8118a(String str, String str2, String str3) {
        if (this.f11385f + 5000000000L > System.nanoTime()) {
            C1639b.m7921c(f11380a, "too often");
            return;
        }
        this.f11385f = System.nanoTime();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            C1639b.m7921c(f11380a, "appId or id or token is empty");
        } else if (m8114a(C1646c.m7950a().m7952b(), null)) {
            long currentTimeMillis = System.currentTimeMillis();
            this.f11381b.newCall(new Builder().cacheControl(CacheControl.FORCE_NETWORK).url("https://16.api.facechat.im/client/config/cookies").post(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("appId", str).addFormDataPart("riverId", str2).addFormDataPart("riverToken", str3).addFormDataPart("cookies", C1728q.m8214d()).addFormDataPart("st", String.valueOf(currentTimeMillis)).addFormDataPart("sk", C1648f.m7954a("DSXKdtKFLbJ5akO9g2c3A9" + currentTimeMillis)).build()).build()).enqueue(new C17012(this));
        }
    }

    @CheckResult
    private synchronized boolean m8114a(Context context, @Nullable Callback callback) {
        Throwable e;
        boolean z = true;
        synchronized (this) {
            if (this.f11384e == null || this.f11383d == null) {
                this.f11384e = null;
                this.f11383d = null;
                try {
                    this.f11384e = m8111a(context.getAssets().open("api.geekint.com.crt"));
                    SSLContext instance = SSLContext.getInstance("TLSv1.2", "AndroidOpenSSL");
                    instance.init(null, new TrustManager[]{this.f11384e}, null);
                    this.f11383d = instance.getSocketFactory();
                } catch (IOException e2) {
                    e = e2;
                    C1639b.m7917a(f11380a, "load https cert fail", e);
                    if (callback != null) {
                        callback.onFailure(null, new IOException("\u8bc1\u4e66\u672a\u521d\u59cb\u5316\u6210\u529f"));
                    }
                    z = false;
                    return z;
                } catch (GeneralSecurityException e3) {
                    e = e3;
                    C1639b.m7917a(f11380a, "load https cert fail", e);
                    if (callback != null) {
                        callback.onFailure(null, new IOException("\u8bc1\u4e66\u672a\u521d\u59cb\u5316\u6210\u529f"));
                    }
                    z = false;
                    return z;
                }
                if (this.f11383d == null || this.f11384e == null) {
                    if (callback != null) {
                        callback.onFailure(null, new IOException("\u8bc1\u4e66\u672a\u521d\u59cb\u5316\u6210\u529f"));
                    }
                    z = false;
                }
            }
        }
        return z;
    }

    private RequestBody m8113a(String str, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            callback.onFailure(null, new IOException("\u65e0\u6cd5\u83b7\u53d6\u5230\u624b\u673a\u53c2\u6570"));
            return null;
        } else if (!Uri.parse(str).isOpaque()) {
            return m8112a(Uri.parse(str));
        } else {
            callback.onFailure(null, new IOException("\u65e0\u6cd5\u83b7\u53d6\u5230\u624b\u673a\u53c2\u6570"));
            return null;
        }
    }

    private RequestBody m8112a(Uri uri) {
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        FormBody.Builder builder = new FormBody.Builder();
        for (String str : queryParameterNames) {
            builder.add(str, uri.getQueryParameter(str));
        }
        return builder.build();
    }

    private X509TrustManager m8111a(InputStream inputStream) throws GeneralSecurityException {
        Collection<Certificate> generateCertificates = CertificateFactory.getInstance("X.509").generateCertificates(inputStream);
        if (generateCertificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }
        char[] toCharArray = "password".toCharArray();
        KeyStore a = m8110a(toCharArray);
        int i = 0;
        for (Certificate certificateEntry : generateCertificates) {
            int i2 = i + 1;
            a.setCertificateEntry(Integer.toString(i), certificateEntry);
            i = i2;
        }
        KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()).init(a, toCharArray);
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(a);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            return (X509TrustManager) trustManagers[0];
        }
        throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
    }

    private KeyStore m8110a(char[] cArr) throws GeneralSecurityException {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, cArr);
            return instance;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
