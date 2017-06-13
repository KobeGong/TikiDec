package com.buddy.tiki.service;

import android.os.SystemClock;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.protocol.web.AppApi;
import com.buddy.tiki.service.base.BaseManager;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunc;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunction;
import com.buddy.tiki.service.base.HttpRequestBody;
import com.buddy.tiki.util.PreferenceUtil;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class AppManager extends BaseManager {
    private AppApi f906d;
    private BehaviorSubject<ConfigInfo> f907e;
    private BehaviorSubject<OperInfo> f908f;

    protected void mo2109a() {
        this.f907e = BehaviorSubject.create();
        this.f908f = BehaviorSubject.create();
        this.c.put(ConfigInfo.class, this.f907e);
        this.c.put(OperInfo.class, this.f908f);
    }

    protected void mo2110b() {
        this.f906d = (AppApi) this.b.getServiceInstance(AppApi.class);
    }

    public Observable<ConfigInfo> configInfoRequest() {
        if (m274a("0")) {
            return Observable.just((ConfigInfo) ((Pair) this.a.get("0")).second);
        }
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("newSDK", Boolean.valueOf(true));
        return this.f906d.configInfoRequest(HttpRequestBody.getInstance().generateRequestParams(params, "4UU90OPYfKb9xPxXS58T")).map(new HttpResultFunc()).doOnNext(AppManager$$Lambda$1.lambdaFactory$(this)).onErrorReturn(AppManager$$Lambda$2.lambdaFactory$());
    }

    /* synthetic */ void m281a(ConfigInfo configInfo) throws Exception {
        this.a.put("0", new Pair(Long.valueOf(SystemClock.elapsedRealtime()), configInfo));
        this.f907e.onNext(configInfo);
        PreferenceUtil.saveConfigInfoCache(configInfo);
    }

    public Observable<OperInfo> operInfoRequest() {
        if (m274a("1")) {
            return Observable.just((OperInfo) ((Pair) this.a.get("1")).second);
        }
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("newSDK", Boolean.valueOf(true));
        return this.f906d.operInfoRequest(HttpRequestBody.getInstance().generateRequestParams(params, "5UO90OPYfKb9xPxU654l")).map(new HttpResultFunc()).doOnNext(AppManager$$Lambda$3.lambdaFactory$(this)).onErrorReturn(AppManager$$Lambda$4.lambdaFactory$());
    }

    /* synthetic */ void m282a(OperInfo operInfo) throws Exception {
        this.a.put("1", new Pair(Long.valueOf(SystemClock.elapsedRealtime()), operInfo));
        this.f908f.onNext(operInfo);
        PreferenceUtil.saveOperInfoCache(operInfo);
    }

    public Completable activeAction() {
        return this.f906d.activeAction(HttpRequestBody.getInstance().generateRequestParams(null, "5UO90OPYfKb9xPxU654l")).flatMapCompletable(new HttpResultFunction());
    }

    public Observable<OperInfo> getOperInfoCache() {
        Pair pair = (Pair) this.a.get("1");
        if (pair == null) {
            return operInfoRequest();
        }
        return Observable.just((OperInfo) pair.second);
    }

    public Observable<ConfigInfo> getConfigCache() {
        Pair pair = (Pair) this.a.get("0");
        if (pair == null) {
            return configInfoRequest();
        }
        return Observable.just((ConfigInfo) pair.second);
    }

    public Observable<Long> getServerTime() {
        return this.f906d.getServerTime(HttpRequestBody.getInstance().generateRequestParams(null, "xUU90OPYfKb9xPxXS58T")).map(new HttpResultFunc());
    }
}
