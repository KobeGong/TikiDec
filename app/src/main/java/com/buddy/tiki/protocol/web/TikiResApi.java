package com.buddy.tiki.protocol.web;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.base.HttpResult;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;

public interface TikiResApi {
    @Streaming
    @Multipart
    @POST("/top/i_tikires_action/upload_avatar")
    Observable<HttpResult<String>> uploadAvatar(@PartMap ArrayMap<String, RequestBody> arrayMap);

    @Streaming
    @Multipart
    @POST("/top/i_tikires_action/upload_temppic")
    Observable<HttpResult<String>> uploadTempPic(@PartMap ArrayMap<String, RequestBody> arrayMap);

    @Streaming
    @Multipart
    @POST("/top/i_tikires_action/upload_video_message")
    Observable<HttpResult<String>> uploadVideo(@PartMap ArrayMap<String, RequestBody> arrayMap);
}
