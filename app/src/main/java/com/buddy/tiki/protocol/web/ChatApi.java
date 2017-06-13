package com.buddy.tiki.protocol.web;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.base.HttpResult;
import com.buddy.tiki.model.im.CallReceiveMessage;
import com.buddy.tiki.model.im.MatchResult;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ChatApi {
    @FormUrlEncoded
    @POST("/top/i_chat_action/accept_call")
    Observable<HttpResult<CallReceiveMessage>> acceptCallAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/close_call")
    Observable<HttpResult<Boolean>> closeCallAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/match")
    Observable<HttpResult<MatchResult>> matchAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/reject_call")
    Observable<HttpResult<Boolean>> rejectCallAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/report_chat_session")
    Observable<HttpResult<Boolean>> reportChatSession(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/reportMatchCall")
    Observable<HttpResult<Boolean>> reportMatchCallAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/request_call")
    Observable<HttpResult<String>> requestCallAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/rush")
    Observable<HttpResult<Boolean>> rushAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/send_video_message")
    Observable<HttpResult<String>> sendVideoMessage(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/set_uber_working")
    Observable<HttpResult<Boolean>> setUberWorking(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_chat_action/unmatch")
    Observable<HttpResult<Boolean>> unMatchAction(@FieldMap ArrayMap<String, Object> arrayMap);
}
