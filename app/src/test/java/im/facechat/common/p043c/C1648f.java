package im.facechat.common.p043c;

import android.content.Context;
import android.support.annotation.Nullable;
import im.facechat.common.p045b.C1639b;
import io.netty.handler.codec.dns.DnsRecord;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import tv.danmaku.ijk.media.player.BuildConfig;

/* compiled from: MD5Helper */
public class C1648f {
    private static final Class<?> f11231a = C1648f.class;

    public static String m7954a(String str) {
        return C1648f.m7955a(str.getBytes());
    }

    public static String m7955a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & DnsRecord.CLASS_ANY);
                if (toHexString.length() < 2) {
                    toHexString = "0" + toHexString;
                }
                stringBuilder.append(toHexString);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    @Nullable
    public static String m7953a(Context context) {
        String b;
        Throwable e;
        try {
            try {
                try {
                    try {
                        b = C1648f.m7956b(MessageDigest.getInstance("MD5").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray()))).getEncoded()));
                    } catch (NoSuchAlgorithmException e2) {
                        e = e2;
                        C1639b.m7917a(f11231a, "get messageDigest fail", e);
                        b = null;
                        return b;
                    } catch (CertificateException e3) {
                        e = e3;
                        C1639b.m7917a(f11231a, "get messageDigest fail", e);
                        b = null;
                        return b;
                    }
                    return b;
                } catch (Throwable e4) {
                    C1639b.m7917a(f11231a, "X509Certificate generate fail", e4);
                    return null;
                }
            } catch (Throwable e42) {
                C1639b.m7917a(f11231a, "CertificateFactory get instance fail", e42);
                return null;
            }
        } catch (Throwable e422) {
            C1639b.m7917a(f11231a, "read fingerprint fail", e422);
            return null;
        }
    }

    private static String m7956b(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String toHexString = Integer.toHexString(bArr[i]);
            int length = toHexString.length();
            if (length == 1) {
                toHexString = "0" + toHexString;
            }
            if (length > 2) {
                toHexString = toHexString.substring(length - 2, length);
            }
            stringBuilder.append(toHexString.toUpperCase());
            if (i < bArr.length - 1) {
                stringBuilder.append(':');
            }
        }
        return stringBuilder.toString();
    }
}
