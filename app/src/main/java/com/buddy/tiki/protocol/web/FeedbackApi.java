package com.buddy.tiki.protocol.web;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.base.HttpResult;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FeedbackApi {
    @FormUrlEncoded
    @POST("/top/i_feedback_action/complain")
    Observable<HttpResult<Void>> complainAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_feedback_action/report_screenshot")
    Observable<HttpResult<Void>> reportScreenshot(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_feedback_action/report_video")
    Observable<HttpResult<Void>> reportVideoMessage(@FieldMap ArrayMap<String, Object> arrayMap);
}
