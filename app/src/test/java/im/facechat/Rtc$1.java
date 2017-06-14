package im.facechat;

import android.content.Context;
import android.support.annotation.Nullable;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import im.facechat.common.p045b.C1639b;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

class Rtc$1 implements Callback {
    final /* synthetic */ Context f11152a;
    final /* synthetic */ FCBaseParams f11153b;
    final /* synthetic */ boolean f11154c;

    Rtc$1(Context context, FCBaseParams fCBaseParams, boolean z) {
        this.a = context;
        this.b = fCBaseParams;
        this.c = z;
    }

    public void onFailure(Call call, IOException iOException) {
        C1639b.m7917a(Rtc.a(), "request fail", iOException);
        if (Rtc.b() != null) {
            Rtc.b().onError(400, "\u65e0\u6cd5\u8fde\u63a5\u5230\u670d\u52a1\u5668");
        }
        FacechatBroadcastManager.m7738a(FacechatBroadcastManager.ACTION_FAIL, 400, "\u65e0\u6cd5\u8fde\u63a5\u5230\u670d\u52a1\u5668");
    }

    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            String string = response.body().string();
            C1639b.m7916a(Rtc.a(), "onResponse:" + string);
            m7747a(string);
            return;
        }
        C1639b.m7921c(Rtc.a(), "request fail " + response.isSuccessful());
        if (Rtc.b() != null) {
            Rtc.b().onError(400, "\u65e0\u6cd5\u8fde\u63a5\u5230\u670d\u52a1\u5668");
        }
        FacechatBroadcastManager.m7738a(FacechatBroadcastManager.ACTION_FAIL, 400, "\u65e0\u6cd5\u8fde\u63a5\u5230\u670d\u52a1\u5668");
    }

    private void m7747a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(SelectCountryActivity.EXTRA_COUNTRY_CODE, -99) != 0) {
                FacechatBroadcastManager.m7738a(FacechatBroadcastManager.ACTION_FAIL, 400, "\u65e0\u6cd5\u8fde\u63a5\u5230\u670d\u52a1\u5668");
                if (Rtc.b() != null) {
                    Rtc.b().onError(400, "\u65e0\u6cd5\u8fde\u63a5\u5230\u670d\u52a1\u5668");
                    return;
                }
                return;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject != null) {
                long optLong = optJSONObject.optLong("ltt");
                String optString = optJSONObject.optString("identify");
                String optString2 = optJSONObject.optString("stunurl");
                JSONArray optJSONArray = optJSONObject.optJSONArray("wssuris");
                String optString3 = optJSONObject.optString("riverToken");
                String optString4 = optJSONObject.optString("riverId");
                String optString5 = optJSONObject.optString("riverSign");
                List arrayList = new ArrayList();
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        arrayList.add(optJSONArray.optString(i));
                    }
                }
                C1631a c1631a = new C1631a();
                c1631a.m7870a(optString);
                c1631a.m7873b(optString2);
                c1631a.m7871a(arrayList);
                c1631a.m7875c(optString3);
                c1631a.m7877d(optString4);
                c1631a.m7879e(optString5);
                C1697l.m8102a(optString4, optString3, optString5, optLong, optJSONObject.toString());
                FacechatBroadcastManager.m7740a(FacechatBroadcastManager.ACTION_TOKEN, optString3);
                m7746a(c1631a);
            }
        } catch (Throwable e) {
            C1639b.m7917a(Rtc.a(), "getAppConfig error", e);
        }
    }

    private void m7746a(@Nullable C1631a c1631a) {
        if (c1631a != null) {
            C1682j.m8059a(this.a, c1631a, this.b);
            if (!this.c) {
                Rtc.e().getAndSet(true);
                if (Rtc.d() != null) {
                    Rtc.d().m7820d(100);
                }
            } else if (Rtc.c() == null && c1631a.m7872b() != null && c1631a.m7872b().size() > 0) {
                String a = m7745a(new C1729s(c1631a.m7876d(), c1631a.m7874c(), c1631a.m7878e()), (String) c1631a.m7872b().get(new Random().nextInt(c1631a.m7872b().size())), c1631a.m7869a());
                C1639b.m7916a(Rtc.a(), "url is : " + a);
                Rtc.a(new C1678h(URI.create(a), new C1699m(), new Rtc$c(null), Rtc.d()));
                Rtc.e().getAndSet(true);
                if (Rtc.d() != null) {
                    Rtc.d().m7820d(100);
                }
            }
        }
    }

    private String m7745a(C1729s c1729s, String str, String str2) {
        if (c1729s == null) {
            return ParamUtil.m7742a(this.b, str, str2, this.a.getPackageName());
        }
        return ParamUtil.m7743a(this.b, str, str2, this.a.getPackageName(), c1729s.m8220a(), c1729s.m8222b(), c1729s.m8224c());
    }
}
