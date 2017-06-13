package com.buddy.tiki.weibo.openapi.models;

import android.support.v7.recyclerview.BuildConfig;
import com.igexin.download.Downloads;
import io.netty.handler.codec.rtsp.RtspHeaders.Values;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public String f3463A;
    public String f3464B;
    public String f3465C;
    public boolean f3466D;
    public int f3467E;
    public int f3468F;
    public String f3469G;
    public String f3470H;
    public String f3471I;
    public String f3472J;
    public String f3473K;
    public String f3474a;
    public String f3475b;
    public String f3476c;
    public String f3477d;
    public int f3478e;
    public int f3479f;
    public String f3480g;
    public String f3481h;
    public String f3482i;
    public String f3483j;
    public String f3484k;
    public String f3485l;
    public String f3486m;
    public String f3487n;
    public int f3488o;
    public int f3489p;
    public int f3490q;
    public int f3491r;
    public String f3492s;
    public boolean f3493t;
    public boolean f3494u;
    public boolean f3495v;
    public boolean f3496w;
    public int f3497x;
    public String f3498y;
    public boolean f3499z;

    public static User parse(String jsonString) {
        try {
            return parse(new JSONObject(jsonString));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User parse(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        User user = new User();
        user.f3474a = jsonObject.optString("id", BuildConfig.VERSION_NAME);
        user.f3475b = jsonObject.optString("idstr", BuildConfig.VERSION_NAME);
        user.f3476c = jsonObject.optString("screen_name", BuildConfig.VERSION_NAME);
        user.f3477d = jsonObject.optString("name", BuildConfig.VERSION_NAME);
        user.f3478e = jsonObject.optInt("province", -1);
        user.f3479f = jsonObject.optInt("city", -1);
        user.f3480g = jsonObject.optString("location", BuildConfig.VERSION_NAME);
        user.f3481h = jsonObject.optString(Downloads.COLUMN_DESCRIPTION, BuildConfig.VERSION_NAME);
        user.f3482i = jsonObject.optString(Values.URL, BuildConfig.VERSION_NAME);
        user.f3483j = jsonObject.optString("profile_image_url", BuildConfig.VERSION_NAME);
        user.f3484k = jsonObject.optString("profile_url", BuildConfig.VERSION_NAME);
        user.f3485l = jsonObject.optString("domain", BuildConfig.VERSION_NAME);
        user.f3486m = jsonObject.optString("weihao", BuildConfig.VERSION_NAME);
        user.f3487n = jsonObject.optString("gender", BuildConfig.VERSION_NAME);
        user.f3488o = jsonObject.optInt("followers_count", 0);
        user.f3489p = jsonObject.optInt("friends_count", 0);
        user.f3490q = jsonObject.optInt("statuses_count", 0);
        user.f3491r = jsonObject.optInt("favourites_count", 0);
        user.f3492s = jsonObject.optString("created_at", BuildConfig.VERSION_NAME);
        user.f3493t = jsonObject.optBoolean("following", false);
        user.f3494u = jsonObject.optBoolean("allow_all_act_msg", false);
        user.f3495v = jsonObject.optBoolean("geo_enabled", false);
        user.f3496w = jsonObject.optBoolean("verified", false);
        user.f3497x = jsonObject.optInt("verified_type", -1);
        user.f3498y = jsonObject.optString("remark", BuildConfig.VERSION_NAME);
        user.f3499z = jsonObject.optBoolean("allow_all_comment", true);
        user.f3463A = jsonObject.optString("avatar_large", BuildConfig.VERSION_NAME);
        user.f3464B = jsonObject.optString("avatar_hd", BuildConfig.VERSION_NAME);
        user.f3465C = jsonObject.optString("verified_reason", BuildConfig.VERSION_NAME);
        user.f3466D = jsonObject.optBoolean("follow_me", false);
        user.f3467E = jsonObject.optInt("online_status", 0);
        user.f3468F = jsonObject.optInt("bi_followers_count", 0);
        user.f3469G = jsonObject.optString("lang", BuildConfig.VERSION_NAME);
        user.f3470H = jsonObject.optString("star", BuildConfig.VERSION_NAME);
        user.f3471I = jsonObject.optString("mbtype", BuildConfig.VERSION_NAME);
        user.f3472J = jsonObject.optString("mbrank", BuildConfig.VERSION_NAME);
        user.f3473K = jsonObject.optString("block_word", BuildConfig.VERSION_NAME);
        return user;
    }
}
