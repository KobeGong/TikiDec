package im.facechat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Base64;
import im.facechat.common.p043c.C1646c;
import im.facechat.common.p043c.C1659j;
import im.facechat.common.p045b.C1639b;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Map;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.BuildConfig;

/* compiled from: FileUtil */
class C1697l {
    private static final Class<?> f11370a = C1697l.class;

    /* compiled from: FileUtil */
    interface C1616a {
        void mo4027a(@Nullable C1729s c1729s);
    }

    private C1697l() {
    }

    @Nullable
    static TrustManagerFactory m8097a() {
        Throwable e;
        try {
            Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(C1646c.m7950a().m7952b().getAssets().open("wss.crt"));
            KeyStore instance = KeyStore.getInstance("PKCS12");
            instance.load(null, null);
            instance.setCertificateEntry("trust", generateCertificate);
            KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()).init(instance, null);
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            return instance2;
        } catch (IOException e2) {
            e = e2;
        } catch (KeyStoreException e3) {
            e = e3;
        } catch (NoSuchAlgorithmException e4) {
            e = e4;
        } catch (CertificateException e5) {
            e = e5;
        } catch (UnrecoverableKeyException e6) {
            e = e6;
        }
        C1639b.m7917a(f11370a, "load trustManager fail ", e);
        return null;
    }

    static void m8102a(String str, String str2, String str3, long j, String str4) {
        C1728q.m8209a(str);
        C1728q.m8211b(str2);
        C1728q.m8213c(str3);
        C1728q.m8208a(j);
        C1697l.m8100a(str4);
        if (ActivityCompat.checkSelfPermission(C1646c.m7950a().m7952b(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            C1697l.m8101a(str, str3);
        }
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    private static void m8101a(final String str, final String str2) {
        C1659j.m7994a().m7995a(new Runnable() {
            public void run() {
                File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (externalStoragePublicDirectory.exists()) {
                    File file = new File(externalStoragePublicDirectory, ".river");
                    File file2 = new File(file, ".river.data");
                    if (!file.exists()) {
                        boolean mkdirs = file.mkdirs();
                        C1639b.m7916a(C1697l.f11370a, "mkdir onResult is " + mkdirs);
                        if (mkdirs) {
                            try {
                                C1639b.m7916a(C1697l.f11370a, "mkFile onResult is " + file2.createNewFile());
                            } catch (Throwable e) {
                                C1639b.m7917a(C1697l.f11370a, "create file fail, reason : ", e);
                            }
                        }
                    }
                    if (!file2.exists()) {
                        try {
                            C1639b.m7916a(C1697l.f11370a, "mkFile onResult is " + file2.createNewFile());
                        } catch (Throwable e2) {
                            C1639b.m7917a(C1697l.f11370a, "create file fail, reason : ", e2);
                        }
                    }
                    if (file.exists() && file2.exists()) {
                        Map arrayMap = new ArrayMap();
                        arrayMap.put("RIVER_ID_KEY", str);
                        arrayMap.put("RIVER_SIGN_KEY", str2);
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                            fileOutputStream.write(Base64.encode(new JSONObject(arrayMap).toString().getBytes(), 0));
                            fileOutputStream.close();
                        } catch (Throwable e22) {
                            C1639b.m7917a(C1697l.f11370a, "open file outputStream fail", e22);
                        }
                    }
                }
            }
        });
    }

    static void m8098a(@NonNull Context context, @NonNull C1616a c1616a) {
        C1729s c1729s = new C1729s();
        SharedPreferences sharedPreferences = context.getSharedPreferences("river_xml", 0);
        Object string = sharedPreferences.getString("RIVER_ID_KEY", BuildConfig.VERSION_NAME);
        Object string2 = sharedPreferences.getString("RIVER_SIGN_KEY", BuildConfig.VERSION_NAME);
        Object string3 = sharedPreferences.getString("RIVER_TOKEN_KEY", BuildConfig.VERSION_NAME);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3) || ActivityCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            C1697l.m8099a(c1616a);
            return;
        }
        c1729s.m8221a(string);
        c1729s.m8225c(string2);
        c1729s.m8223b(string3);
        c1616a.mo4027a(c1729s);
    }

    @RequiresPermission("android.permission.READ_EXTERNAL_STORAGE")
    private static void m8099a(final C1616a c1616a) {
        C1659j.m7994a().m7995a(new Runnable() {
            public void run() {
                Throwable e;
                File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (externalStoragePublicDirectory.exists()) {
                    File file = new File(externalStoragePublicDirectory, ".river");
                    externalStoragePublicDirectory = new File(file, ".river.data");
                    if (file.exists() && externalStoragePublicDirectory.exists()) {
                        try {
                            InputStream fileInputStream = new FileInputStream(externalStoragePublicDirectory);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            byteArrayOutputStream.close();
                            fileInputStream.close();
                            Object str = new String(Base64.decode(byteArrayOutputStream.toByteArray(), 0));
                            if (!TextUtils.isEmpty(str)) {
                                JSONObject jSONObject = new JSONObject(str);
                                C1729s c1729s = new C1729s();
                                Object optString = jSONObject.optString("RIVER_ID_KEY");
                                Object optString2 = jSONObject.optString("RIVER_SIGN_KEY");
                                if (!(TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2))) {
                                    c1729s.m8221a(optString);
                                    c1729s.m8225c(optString2);
                                    c1616a.mo4027a(c1729s);
                                    return;
                                }
                            }
                        } catch (IOException e2) {
                            e = e2;
                            C1639b.m7917a(C1697l.f11370a, "read file fail", e);
                            c1616a.mo4027a(null);
                        } catch (JSONException e3) {
                            e = e3;
                            C1639b.m7917a(C1697l.f11370a, "read file fail", e);
                            c1616a.mo4027a(null);
                        }
                    }
                }
                c1616a.mo4027a(null);
            }
        });
    }

    public static void m8100a(String str) {
        C1728q.m8215d(str);
    }
}
