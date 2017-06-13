package com.buddy.tiki.weibo.openapi;

import android.content.Context;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;

public abstract class AbsOpenAPI {
    private static final String f3457d = AbsOpenAPI.class.getName();
    protected Oauth2AccessToken f3458a;
    protected Context f3459b;
    protected String f3460c;

    public AbsOpenAPI(Context context, String appKey, Oauth2AccessToken accessToken) {
        this.f3459b = context;
        this.f3460c = appKey;
        this.f3458a = accessToken;
    }

    protected void m2165a(String url, WeiboParameters params, String httpMethod, RequestListener listener) {
        if (this.f3458a == null || TextUtils.isEmpty(url) || params == null || TextUtils.isEmpty(httpMethod) || listener == null) {
            LogUtil.e(f3457d, "Argument error!");
            return;
        }
        params.put(Oauth2AccessToken.KEY_ACCESS_TOKEN, this.f3458a.getToken());
        new AsyncWeiboRunner(this.f3459b).requestAsync(url, params, httpMethod, listener);
    }

    protected String m2164a(String url, WeiboParameters params, String httpMethod) {
        if (this.f3458a == null || TextUtils.isEmpty(url) || params == null || TextUtils.isEmpty(httpMethod)) {
            LogUtil.e(f3457d, "Argument error!");
            return BuildConfig.VERSION_NAME;
        }
        params.put(Oauth2AccessToken.KEY_ACCESS_TOKEN, this.f3458a.getToken());
        return new AsyncWeiboRunner(this.f3459b).request(url, params, httpMethod);
    }
}
