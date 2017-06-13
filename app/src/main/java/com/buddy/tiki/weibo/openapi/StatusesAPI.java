package com.buddy.tiki.weibo.openapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.SparseArray;
import com.igexin.download.Downloads;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import io.netty.handler.codec.rtsp.RtspHeaders.Values;

public class StatusesAPI extends AbsOpenAPI {
    private static final SparseArray<String> f3461d = new SparseArray();

    static {
        f3461d.put(0, "https://api.weibo.com/2/statuses/friends_timeline.json");
        f3461d.put(1, "https://api.weibo.com/2/statuses/mentions.json");
        f3461d.put(3, "https://api.weibo.com/2/statuses/repost.json");
        f3461d.put(2, "https://api.weibo.com/2/statuses/update.json");
        f3461d.put(4, "https://api.weibo.com/2/statuses/upload.json");
        f3461d.put(5, "https://api.weibo.com/2/statuses/upload_url_text.json");
    }

    public StatusesAPI(Context context, String appKey, Oauth2AccessToken accessToken) {
        super(context, appKey, accessToken);
    }

    public void friendsTimeline(long since_id, long max_id, int count, int page, boolean base_app, int featureType, boolean trim_user, RequestListener listener) {
        m2165a((String) f3461d.get(0), m2167a(since_id, max_id, count, page, base_app, trim_user, featureType), "GET", listener);
    }

    public void mentions(long since_id, long max_id, int count, int page, int authorType, int sourceType, int filterType, boolean trim_user, RequestListener listener) {
        m2165a((String) f3461d.get(1), m2166a(since_id, max_id, count, page, authorType, sourceType, filterType, trim_user), "GET", listener);
    }

    public void update(String content, String lat, String lon, RequestListener listener) {
        m2165a((String) f3461d.get(2), m2168a(content, lat, lon), "POST", listener);
    }

    public void upload(String content, Bitmap bitmap, String lat, String lon, RequestListener listener) {
        WeiboParameters params = m2168a(content, lat, lon);
        params.put("pic", bitmap);
        m2165a((String) f3461d.get(4), params, "POST", listener);
    }

    public void uploadUrlText(String status, String imageUrl, String pic_id, String lat, String lon, RequestListener listener) {
        WeiboParameters params = m2168a(status, lat, lon);
        params.put(Values.URL, imageUrl);
        params.put("pic_id", pic_id);
        m2165a((String) f3461d.get(5), params, "POST", listener);
    }

    public String friendsTimelineSync(long since_id, long max_id, int count, int page, boolean base_app, int featureType, boolean trim_user) {
        return m2164a((String) f3461d.get(0), m2167a(since_id, max_id, count, page, base_app, trim_user, featureType), "GET");
    }

    public String mentionsSync(long since_id, long max_id, int count, int page, int authorType, int sourceType, int filterType, boolean trim_user) {
        return m2164a((String) f3461d.get(1), m2166a(since_id, max_id, count, page, authorType, sourceType, filterType, trim_user), "GET");
    }

    public String updateSync(String content, String lat, String lon) {
        return m2164a((String) f3461d.get(2), m2168a(content, lat, lon), "POST");
    }

    public String uploadSync(String content, Bitmap bitmap, String lat, String lon) {
        WeiboParameters params = m2168a(content, lat, lon);
        params.put("pic", bitmap);
        return m2164a((String) f3461d.get(4), params, "POST");
    }

    public String uploadUrlTextSync(String status, String imageUrl, String pic_id, String lat, String lon) {
        WeiboParameters params = m2168a(status, lat, lon);
        params.put(Values.URL, imageUrl);
        params.put("pic_id", pic_id);
        return m2164a((String) f3461d.get(5), params, "POST");
    }

    private WeiboParameters m2167a(long since_id, long max_id, int count, int page, boolean base_app, boolean trim_user, int featureType) {
        int i;
        int i2 = 1;
        WeiboParameters params = new WeiboParameters(this.c);
        params.put("since_id", since_id);
        params.put("max_id", max_id);
        params.put("count", count);
        params.put("page", page);
        String str = "base_app";
        if (base_app) {
            i = 1;
        } else {
            i = 0;
        }
        params.put(str, i);
        String str2 = "trim_user";
        if (!trim_user) {
            i2 = 0;
        }
        params.put(str2, i2);
        params.put("feature", featureType);
        return params;
    }

    private WeiboParameters m2168a(String content, String lat, String lon) {
        WeiboParameters params = new WeiboParameters(this.c);
        params.put(Downloads.COLUMN_STATUS, content);
        if (!TextUtils.isEmpty(lon)) {
            params.put("long", lon);
        }
        if (!TextUtils.isEmpty(lat)) {
            params.put("lat", lat);
        }
        return params;
    }

    private WeiboParameters m2166a(long since_id, long max_id, int count, int page, int authorType, int sourceType, int filterType, boolean trim_user) {
        WeiboParameters params = new WeiboParameters(this.c);
        params.put("since_id", since_id);
        params.put("max_id", max_id);
        params.put("count", count);
        params.put("page", page);
        params.put("filter_by_author", authorType);
        params.put("filter_by_source", sourceType);
        params.put("filter_by_type", filterType);
        params.put("trim_user", trim_user ? 1 : 0);
        return params;
    }
}
