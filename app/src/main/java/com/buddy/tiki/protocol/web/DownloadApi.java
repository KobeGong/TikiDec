package com.buddy.tiki.protocol.web;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownloadApi {
    @Streaming
    @GET
    Observable<ResponseBody> downloadRes(@Url String str);
}
