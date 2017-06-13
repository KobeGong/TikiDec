package com.buddy.tiki.service;

import com.buddy.tiki.protocol.web.DownloadApi;
import com.buddy.tiki.service.base.BaseManager;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DownloadApiManager extends BaseManager {
    private DownloadApi f913d;

    protected void mo2110b() {
        this.f913d = (DownloadApi) this.b.getServiceInstance(DownloadApi.class);
    }

    public Observable<String> downloadFile(String url, String id, String type) {
        return this.f913d.downloadRes(url).subscribeOn(Schedulers.io()).flatMap(DownloadApiManager$$Lambda$1.lambdaFactory$(type, id));
    }
}
