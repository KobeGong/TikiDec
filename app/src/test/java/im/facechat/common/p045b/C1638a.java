package im.facechat.common.p045b;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import tv.danmaku.ijk.media.player.BuildConfig;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: FacechatDefaultLoggingDelegate */
public class C1638a implements C1637c {
    static final C1638a f11214a = new C1638a();
    private String f11215b = IjkMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
    private int f11216c = 7;

    private C1638a() {
    }

    public static C1638a m7903a() {
        return f11214a;
    }

    private static String m7905a(String str, Throwable th) {
        return str + '\n' + C1638a.m7906a(th);
    }

    private static String m7906a(Throwable th) {
        if (th == null) {
            return BuildConfig.VERSION_NAME;
        }
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public void m7909a(int i) {
        this.f11216c = i;
    }

    public boolean mo4056b(int i) {
        return this.f11216c <= i;
    }

    public void mo4053a(String str, String str2) {
        m7907a(3, str, str2);
    }

    public void mo4055b(String str, String str2) {
        m7907a(5, str, str2);
    }

    public void mo4057c(String str, String str2) {
        m7907a(6, str, str2);
    }

    public void mo4054a(String str, String str2, Throwable th) {
        m7908a(6, str, str2, th);
    }

    private void m7907a(int i, String str, String str2) {
        Log.println(i, m7904a(str), str2);
    }

    private void m7908a(int i, String str, String str2, Throwable th) {
        Log.println(i, m7904a(str), C1638a.m7905a(str2, th));
    }

    private String m7904a(String str) {
        if (this.f11215b != null) {
            return this.f11215b + ":" + str;
        }
        return str;
    }
}
