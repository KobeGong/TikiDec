package com.buddy.tiki.service;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.protocol.web.FeedbackApi;
import com.buddy.tiki.service.base.BaseManager;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunction;
import com.buddy.tiki.service.base.HttpRequestBody;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.reactivex.Completable;

public class FeedbackManager extends BaseManager {
    private FeedbackApi f914d;

    protected void mo2110b() {
        this.f914d = (FeedbackApi) this.b.getServiceInstance(FeedbackApi.class);
    }

    public Completable complainAction(String uid, String pic, String text, String reason) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put(Oauth2AccessToken.KEY_UID, uid);
        params.put("pic", pic);
        params.put("text", text);
        params.put("reason", reason);
        return this.f914d.complainAction(HttpRequestBody.getInstance().generateRequestParams(params, "OIUSTOPTWEAKr14TYkjh")).flatMapCompletable(new HttpResultFunction());
    }

    public Completable reportScreenshot(String roomId, String picture) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("roomId", roomId);
        params.put("picture", picture);
        return this.f914d.reportScreenshot(HttpRequestBody.getInstance().generateRequestParams(params, "OIUSTOPTWEAKr14TYkjh")).flatMapCompletable(new HttpResultFunction());
    }

    public Completable reportVideoMessage(String videoId, String reason) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("videoId", videoId);
        params.put("reason", reason);
        return this.f914d.reportVideoMessage(HttpRequestBody.getInstance().generateRequestParams(params, "OIUSTOPTWEAKr14TYkjh")).flatMapCompletable(new HttpResultFunction());
    }
}
