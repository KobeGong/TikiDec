package com.buddy.tiki.protocol.web;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.base.HttpResult;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PaymentApi {
    @FormUrlEncoded
    @POST("/top/i_payment_action/buy_video_message")
    Observable<HttpResult<Boolean>> buyVideoMessage(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_payment_action/google_recharge")
    Observable<HttpResult<Boolean>> googleRecharge(@FieldMap ArrayMap<String, Object> arrayMap);
}
