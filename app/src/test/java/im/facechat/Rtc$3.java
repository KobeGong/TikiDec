package im.facechat;

import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONObject;

class Rtc$3 implements Callback {
    final /* synthetic */ Rtc$SimpleActionCallback f11158a;

    Rtc$3(Rtc$SimpleActionCallback rtc$SimpleActionCallback) {
        this.a = rtc$SimpleActionCallback;
    }

    public void onFailure(Call call, IOException iOException) {
        if (this.a != null) {
            this.a.onResult(false, "\u65e0\u6cd5\u8fde\u63a5\u5230\u670d\u52a1\u5668");
        }
    }

    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            try {
                JSONObject jSONObject = new JSONObject(response.body().string());
                int optInt = jSONObject.optInt(SelectCountryActivity.EXTRA_COUNTRY_CODE, -99);
                String optString = jSONObject.optString("msg");
                if (optInt == 0) {
                    if (this.a != null) {
                        this.a.onResult(true, null);
                    }
                } else if (this.a != null) {
                    this.a.onResult(false, optString);
                }
            } catch (Exception e) {
                if (this.a != null) {
                    this.a.onResult(false, "Invalid json");
                }
            }
        } else if (this.a != null) {
            this.a.onResult(false, "\u65e0\u6cd5\u8fde\u63a5\u5230\u670d\u52a1\u5668");
        }
    }
}
