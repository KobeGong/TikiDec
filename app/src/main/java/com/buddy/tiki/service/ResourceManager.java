package com.buddy.tiki.service;

import android.os.SystemClock;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.protocol.web.ResourceApi;
import com.buddy.tiki.service.base.BaseManager;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunc;
import com.buddy.tiki.service.base.HttpRequestBody;
import com.buddy.tiki.util.PreferenceUtil;
import io.reactivex.Observable;
import java.util.List;

public class ResourceManager extends BaseManager {
    private ResourceApi f939d;

    protected void mo2110b() {
        this.f939d = (ResourceApi) this.b.getServiceInstance(ResourceApi.class);
    }

    public Observable<List<Gift>> sysGiftsRequest(int page) {
        String type = "1000" + page;
        if (m274a(type)) {
            return Observable.just((List) ((Pair) this.a.get(type)).second);
        }
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("page", Integer.valueOf(page));
        return this.f939d.sysGiftsRequest(HttpRequestBody.getInstance().generateRequestParams(params, "4UU90OPYfKb9xPxXS57T")).map(new HttpResultFunc()).doOnNext(ResourceManager$$Lambda$1.lambdaFactory$(this, type)).onErrorReturn(ResourceManager$$Lambda$2.lambdaFactory$());
    }

    /* synthetic */ void m305b(String type, List gifts) throws Exception {
        this.a.put(type, new Pair(Long.valueOf(SystemClock.elapsedRealtime()), gifts));
        PreferenceUtil.saveGiftListCache(gifts);
    }

    public Observable<List<FaceUnity>> sysFaceunityRequest(int page) {
        String type = "2000" + page;
        if (m274a(type)) {
            return Observable.just((List) ((Pair) this.a.get(type)).second);
        }
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("page", Integer.valueOf(page));
        params.put("v", Integer.valueOf(1));
        return this.f939d.sysFaceunityRequest(HttpRequestBody.getInstance().generateRequestParams(params, "0gb90OPYfKb9xPxX09LL")).map(new HttpResultFunc()).doOnNext(ResourceManager$$Lambda$3.lambdaFactory$(this, type)).onErrorReturn(ResourceManager$$Lambda$4.lambdaFactory$());
    }

    /* synthetic */ void m303a(String type, List faceUnities) throws Exception {
        this.a.put(type, new Pair(Long.valueOf(SystemClock.elapsedRealtime()), faceUnities));
        PreferenceUtil.saveFaceuAvatarCache(faceUnities);
    }
}
