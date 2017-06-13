package com.buddy.tiki.weibo.openapi;

import android.content.Context;
import android.util.SparseArray;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

public class UsersAPI extends AbsOpenAPI {
    private static final SparseArray<String> f3462d = new SparseArray();

    static {
        f3462d.put(0, "https://api.weibo.com/2/users/show.json");
        f3462d.put(1, "https://api.weibo.com/2/users/domain_show.json");
        f3462d.put(2, "https://api.weibo.com/2/users/counts.json");
    }

    public UsersAPI(Context context, String appKey, Oauth2AccessToken accessToken) {
        super(context, appKey, accessToken);
    }

    public void show(long uid, RequestListener listener) {
        WeiboParameters params = new WeiboParameters(this.c);
        params.put(Oauth2AccessToken.KEY_UID, uid);
        m2165a((String) f3462d.get(0), params, "GET", listener);
    }

    public void show(String screen_name, RequestListener listener) {
        WeiboParameters params = new WeiboParameters(this.c);
        params.put("screen_name", screen_name);
        m2165a((String) f3462d.get(0), params, "GET", listener);
    }

    public void domainShow(String domain, RequestListener listener) {
        WeiboParameters params = new WeiboParameters(this.c);
        params.put("domain", domain);
        m2165a((String) f3462d.get(1), params, "GET", listener);
    }

    public void counts(long[] uids, RequestListener listener) {
        m2165a((String) f3462d.get(2), m2169a(uids), "GET", listener);
    }

    public String showSync(long uid) {
        WeiboParameters params = new WeiboParameters(this.c);
        params.put(Oauth2AccessToken.KEY_UID, uid);
        return m2164a((String) f3462d.get(0), params, "GET");
    }

    public String showSync(String screen_name) {
        WeiboParameters params = new WeiboParameters(this.c);
        params.put("screen_name", screen_name);
        return m2164a((String) f3462d.get(0), params, "GET");
    }

    public String domainShowSync(String domain) {
        WeiboParameters params = new WeiboParameters(this.c);
        params.put("domain", domain);
        return m2164a((String) f3462d.get(1), params, "GET");
    }

    public String countsSync(long[] uids) {
        return m2164a((String) f3462d.get(2), m2169a(uids), "GET");
    }

    private WeiboParameters m2169a(long[] uids) {
        WeiboParameters params = new WeiboParameters(this.c);
        StringBuilder strb = new StringBuilder();
        for (long cid : uids) {
            strb.append(cid).append(",");
        }
        strb.deleteCharAt(strb.length() - 1);
        params.put("uids", strb.toString());
        return params;
    }
}
