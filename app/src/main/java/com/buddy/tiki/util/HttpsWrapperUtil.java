package com.buddy.tiki.util;

import android.content.Context;
import android.support.annotation.Nullable;
import com.buddy.tiki.log.TikiLog;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient.Builder;

public class HttpsWrapperUtil {
    private static final TikiLog f2404a = TikiLog.getInstance(HttpsWrapperUtil.class.getSimpleName());

    private HttpsWrapperUtil() {
    }

    @Nullable
    public static Builder wrapHttps(Context context, Builder builder) {
        Exception e;
        X509TrustManager mTrustManager = null;
        SSLSocketFactory mSSLSocketFactory = null;
        try {
            mTrustManager = m1548a(context.getAssets().open("17.api.tikiapp.im.crt"));
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2", "AndroidOpenSSL");
            sslContext.init(null, new TrustManager[]{mTrustManager}, new SecureRandom());
            mSSLSocketFactory = sslContext.getSocketFactory();
        } catch (IOException e2) {
            e = e2;
            f2404a.m264e("load https cert fail", e);
            return mSSLSocketFactory != null ? null : null;
        } catch (GeneralSecurityException e3) {
            e = e3;
            f2404a.m264e("load https cert fail", e);
            if (mSSLSocketFactory != null) {
            }
        }
        if (mSSLSocketFactory != null && mTrustManager != null) {
            return builder.sslSocketFactory(mSSLSocketFactory, mTrustManager);
        }
    }

    @Nullable
    public static Builder wrapHttpsWithCharles(Context context, Builder builder) {
        try {
            InputStream inputStream = context.getAssets().open("17.api.tikiapp.im.crt");
            InputStream inputStream2 = context.getAssets().open("charles-ssl-proxying-certificate.cer");
            return builder.sslSocketFactory(m1547a(inputStream, inputStream2));
        } catch (IOException e) {
            f2404a.m264e("load https cert fail", e);
            return null;
        }
    }

    private static SSLSocketFactory m1547a(InputStream... certificates) {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int length = certificates.length;
            int i = 0;
            int index = 0;
            while (i < length) {
                InputStream certificate = certificates[i];
                int index2 = index + 1;
                keyStore.setCertificateEntry(Integer.toString(index), certificateFactory.generateCertificate(certificate));
                if (certificate != null) {
                    try {
                        certificate.close();
                    } catch (IOException e) {
                    }
                }
                i++;
                index = index2;
            }
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            sSLSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sSLSocketFactory;
    }

    private static X509TrustManager m1548a(InputStream in) throws GeneralSecurityException {
        Collection<? extends Certificate> certificates = CertificateFactory.getInstance("X.509").generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }
        char[] password = "password".toCharArray();
        KeyStore keyStore = m1546a(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            int index2 = index + 1;
            keyStore.setCertificateEntry(Integer.toString(index), certificate);
            index = index2;
        }
        KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()).init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            return (X509TrustManager) trustManagers[0];
        }
        throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
    }

    private static KeyStore m1546a(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
