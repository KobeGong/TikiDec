package com.buddy.tiki.protocol.web;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.base.HttpResult;
import com.buddy.tiki.model.trace.InboxMessage;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MessageApi {
    @FormUrlEncoded
    @POST("/top/i_message_query/get_messages")
    Observable<HttpResult<List<InboxMessage>>> messageRequest(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_message_action/send_text_message")
    Observable<HttpResult<Void>> sendTextMessage(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_message_query/get_video_url")
    Observable<HttpResult<String>> videoMessageRequest(@FieldMap ArrayMap<String, Object> arrayMap);
}
