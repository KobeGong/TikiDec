package com.buddy.tiki.helper;

import android.text.TextUtils;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.service.base.ACache;
import com.buddy.tiki.service.base.DataLayer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class DownloadHelper {
    private static final TikiLog f677a = TikiLog.getInstance("DownloadHelper");

    private static class SingleHolder {
        private static final DownloadHelper f676a = new DownloadHelper();
    }

    public static DownloadHelper getInstance() {
        return SingleHolder.f676a;
    }

    public void downloadWebPResource() {
        DataLayer.getInstance().getResourceManager().sysGiftsRequest(1).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).flatMap(DownloadHelper$$Lambda$2.lambdaFactory$()).filter(DownloadHelper$$Lambda$3.lambdaFactory$()).doOnNext(DownloadHelper$$Lambda$1.lambdaFactory$()).filter(DownloadHelper$$Lambda$4.lambdaFactory$()).flatMap(DownloadHelper$$Lambda$5.lambdaFactory$()).subscribe(DownloadHelper$$Lambda$6.lambdaFactory$(), DownloadHelper$$Lambda$7.lambdaFactory$());
        DataLayer.getInstance().getAppManager().getOperInfoCache().subscribeOn(Schedulers.io()).flatMap(DownloadHelper$$Lambda$8.lambdaFactory$()).filter(DownloadHelper$$Lambda$9.lambdaFactory$()).flatMap(DownloadHelper$$Lambda$10.lambdaFactory$()).filter(DownloadHelper$$Lambda$11.lambdaFactory$()).flatMap(DownloadHelper$$Lambda$12.lambdaFactory$()).subscribe(DownloadHelper$$Lambda$13.lambdaFactory$(), DownloadHelper$$Lambda$14.lambdaFactory$());
    }

    static /* synthetic */ void m137d(Gift gift) throws Exception {
        if (!TextUtils.isEmpty(gift.getMusic()) && ACache.get(ChatApp.getInstance()).getAsString(gift.getId()) == null) {
            f677a.m263e(gift.getId() + "  " + ACache.get(ChatApp.getInstance()).getAsString(gift.getId()));
            DataLayer.getInstance().getDownloadApiManager().downloadFile(gift.getMusic(), gift.getId(), "Music").subscribe(DownloadHelper$$Lambda$17.lambdaFactory$(), DownloadHelper$$Lambda$18.lambdaFactory$());
        }
    }

    static /* synthetic */ void m131b(String aVoid) throws Exception {
    }

    static /* synthetic */ boolean m136c(Gift gift) throws Exception {
        return (gift == null || TextUtils.isEmpty(gift.getSource())) ? false : true;
    }

    static /* synthetic */ boolean m134b(Gift gift) throws Exception {
        return !Fresco.getImagePipeline().isInDiskCacheSync(ImageRequest.fromUri(gift.getSource()));
    }

    static /* synthetic */ void m127a(Object aVoid) throws Exception {
    }

    static /* synthetic */ ObservableSource m123a(OperInfo operInfo) throws Exception {
        return !operInfo.isShowFaceunityDownload() ? DataLayer.getInstance().getResourceManager().sysFaceunityRequest(1) : Observable.empty();
    }

    static /* synthetic */ boolean m130a(List faceUnities) throws Exception {
        return faceUnities != null && faceUnities.size() > 0;
    }

    static /* synthetic */ boolean m133b(FaceUnity faceUnity) throws Exception {
        return faceUnity != null && faceUnity.isAutoLoading();
    }

    static /* synthetic */ void m128a(String path) throws Exception {
    }
}
