package com.buddy.tiki.service.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.helper.MD5Helper;
import com.buddy.tiki.util.PreferenceUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HttpRequestBody {
    private static final MediaType f1059a = MediaType.parse("text/plain");
    private final Gson f1060b;

    private static class SingletonHolder {
        private static final HttpRequestBody f1058a = new HttpRequestBody();
    }

    private HttpRequestBody() {
        this.f1060b = new GsonBuilder().create();
    }

    public static HttpRequestBody getInstance() {
        return SingletonHolder.f1058a;
    }

    public synchronized ArrayMap<String, Object> generateRequestParams(@Nullable ArrayMap<String, Object> params, @NonNull String reqKey) {
        ArrayMap<String, Object> requestParams;
        requestParams = new ArrayMap();
        long time = System.currentTimeMillis();
        if (TextUtils.isEmpty(TopConfig.f408a)) {
            String uid = PreferenceUtil.getTikiUidToken();
            if (!TextUtils.isEmpty(uid)) {
                TopConfig.f408a = uid;
                requestParams.put("u", uid);
            }
        } else {
            requestParams.put("u", TopConfig.f408a);
        }
        if (TextUtils.isEmpty(TopConfig.f409b)) {
            String session = PreferenceUtil.getTikiSessionToken();
            if (!TextUtils.isEmpty(session)) {
                TopConfig.f409b = session;
                requestParams.put("s", session);
            }
        } else {
            requestParams.put("s", TopConfig.f409b);
        }
        if (!TextUtils.isEmpty(TopConfig.f410c)) {
            requestParams.put("di", TopConfig.f410c);
        }
        if (!TextUtils.isEmpty(TopConfig.f413f)) {
            requestParams.put("domain", TopConfig.f413f);
        }
        requestParams.put("dt", String.valueOf(TopConfig.f412e));
        if (params != null) {
            requestParams.put("params", this.f1060b.toJson((Object) params, (Type) ArrayMap.class));
        }
        requestParams.put("st", String.valueOf(time));
        requestParams.put("sk", TextUtils.isEmpty(TopConfig.f408a) ? MD5Helper.getMD5(reqKey + time) : MD5Helper.getMD5(TopConfig.f408a + reqKey + time));
        return requestParams;
    }

    public synchronized ArrayMap<String, RequestBody> generateRequestBody(@Nullable ArrayMap<String, Object> params, @NonNull String reqKey) {
        ArrayMap<String, RequestBody> requestBody;
        requestBody = new ArrayMap();
        long time = System.currentTimeMillis();
        if (TextUtils.isEmpty(TopConfig.f408a)) {
            String uid = PreferenceUtil.getTikiUidToken();
            if (!TextUtils.isEmpty(uid)) {
                TopConfig.f408a = uid;
                requestBody.put("u", RequestBody.create(f1059a, TopConfig.f408a));
            }
        } else {
            requestBody.put("u", RequestBody.create(f1059a, TopConfig.f408a));
        }
        if (TextUtils.isEmpty(TopConfig.f409b)) {
            String session = PreferenceUtil.getTikiSessionToken();
            if (!TextUtils.isEmpty(session)) {
                TopConfig.f409b = session;
                requestBody.put("s", RequestBody.create(f1059a, TopConfig.f409b));
            }
        } else {
            requestBody.put("s", RequestBody.create(f1059a, TopConfig.f409b));
        }
        if (!TextUtils.isEmpty(TopConfig.f410c)) {
            requestBody.put("di", RequestBody.create(f1059a, TopConfig.f410c));
        }
        if (!TextUtils.isEmpty(TopConfig.f413f)) {
            requestBody.put("domain", RequestBody.create(f1059a, TopConfig.f413f));
        }
        requestBody.put("dt", RequestBody.create(f1059a, String.valueOf(TopConfig.f412e)));
        if (params != null) {
            requestBody.put("params", RequestBody.create(f1059a, this.f1060b.toJson((Object) params, (Type) ArrayMap.class)));
        }
        requestBody.put("st", RequestBody.create(f1059a, String.valueOf(time)));
        requestBody.put("sk", RequestBody.create(f1059a, TextUtils.isEmpty(TopConfig.f408a) ? MD5Helper.getMD5(reqKey + time) : MD5Helper.getMD5(TopConfig.f408a + reqKey + time)));
        return requestBody;
    }
}
