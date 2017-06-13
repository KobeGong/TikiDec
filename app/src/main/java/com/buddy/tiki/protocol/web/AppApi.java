package com.buddy.tiki.protocol.web;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.base.HttpResult;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AppApi {
    @FormUrlEncoded
    @POST("/top/i_app_query/active")
    Observable<HttpResult<Void>> activeAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_app_query/get_sys_configinfo")
    Observable<HttpResult<ConfigInfo>> configInfoRequest(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_app_query/sync_ntp_time")
    Observable<HttpResult<Long>> getServerTime(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_app_query/get_oper_configinfo")
    Observable<HttpResult<OperInfo>> operInfoRequest(@FieldMap ArrayMap<String, Object> arrayMap);
}
