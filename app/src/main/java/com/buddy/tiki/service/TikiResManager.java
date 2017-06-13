package com.buddy.tiki.service;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.buddy.tiki.helper.UploadHelper;
import com.buddy.tiki.helper.UploadHelper.UploadCallback;
import com.buddy.tiki.protocol.web.TikiResApi;
import com.buddy.tiki.request.base.ProgressRequestBody;
import com.buddy.tiki.service.base.BaseManager;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunc;
import com.buddy.tiki.service.base.HttpRequestBody;
import com.buddy.tiki.util.FileUtil;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TikiResManager extends BaseManager {
    private static final MediaType f955d = MediaType.parse("application/octet-stream");
    private TikiResApi f956e;
    private Handler f957f;

    protected void mo2110b() {
        this.f956e = (TikiResApi) this.b.getUpServiceInstance(TikiResApi.class);
        this.f957f = new Handler(Looper.getMainLooper());
    }

    public Observable<String> uploadTempPic(@NonNull byte[] data) {
        return uploadTempPic(data, null, 0, 0);
    }

    public Observable<String> uploadTempPic(@NonNull byte[] data, @Nullable String[] ids, int min, int max) {
        ArrayMap<String, Object> params = new ArrayMap();
        RequestBody requestBody = ids != null ? m310a(RequestBody.create(f955d, data), ids, min, max) : RequestBody.create(f955d, data);
        ArrayMap<String, RequestBody> arrayMap = HttpRequestBody.getInstance().generateRequestBody(params, "CSU90OPYfKb9xPxXS5CG");
        arrayMap.put("data\"; filename=\"nofilename", requestBody);
        return this.f956e.uploadTempPic(arrayMap).map(new HttpResultFunc());
    }

    public Observable<String> uploadAvatar(@NonNull byte[] data) {
        ArrayMap<String, Object> params = new ArrayMap();
        RequestBody requestBody = RequestBody.create(f955d, data);
        ArrayMap<String, RequestBody> arrayMap = HttpRequestBody.getInstance().generateRequestBody(params, "XXUU90OPYfKb9xPxXS5CG");
        arrayMap.put("data\"; filename=\"nofilename", requestBody);
        return this.f956e.uploadAvatar(arrayMap).map(new HttpResultFunc());
    }

    public Observable<String> uploadVideo(@NonNull String filePath, @Nullable String[] ids, int min, int max) {
        byte[] bytes = FileUtil.readBytes(filePath);
        if (bytes == null) {
            return Observable.error(new NullPointerException("empty video"));
        }
        return uploadVideo(bytes, ids, min, max);
    }

    public Observable<String> uploadVideo(@NonNull byte[] data, @Nullable String[] ids, int min, int max) {
        ArrayMap<String, Object> params = new ArrayMap();
        RequestBody requestBody = ids != null ? m310a(RequestBody.create(f955d, data), ids, min, max) : RequestBody.create(f955d, data);
        ArrayMap<String, RequestBody> arrayMap = HttpRequestBody.getInstance().generateRequestBody(params, "YUXUU90OPYfKb9xPxXS5COR");
        arrayMap.put("data\"; filename=\"nofilename", requestBody);
        return this.f956e.uploadVideo(arrayMap).map(new HttpResultFunc());
    }

    private RequestBody m310a(@NonNull RequestBody requestBody, @NonNull String[] ids, int min, int max) {
        ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody);
        progressRequestBody.setListener(TikiResManager$$Lambda$1.lambdaFactory$(this, ids, max, min));
        return progressRequestBody;
    }

    /* synthetic */ void m312a(@NonNull String[] ids, int max, int min, long bytesWritten, long contentLength, long networkSpeed) {
        this.f957f.post(TikiResManager$$Lambda$2.lambdaFactory$(ids, bytesWritten, contentLength, max, min, networkSpeed));
    }

    static /* synthetic */ void m311a(@NonNull String[] ids, long bytesWritten, long contentLength, int max, int min, long networkSpeed) {
        for (String id : ids) {
            UploadCallback callback = UploadHelper.getInstance().getCallback(id);
            if (callback != null) {
                callback.uploadProgress(id, bytesWritten, contentLength, (((((float) bytesWritten) * 1.0f) / ((float) contentLength)) * ((float) (max - min))) + ((float) min), networkSpeed);
            }
        }
    }
}
