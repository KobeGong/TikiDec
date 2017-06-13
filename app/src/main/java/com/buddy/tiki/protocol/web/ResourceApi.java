package com.buddy.tiki.protocol.web;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.base.HttpResult;
import com.buddy.tiki.model.resource.Border;
import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.model.resource.Gift;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ResourceApi {
    @FormUrlEncoded
    @POST("/top/i_resource_query/get_sys_borders")
    Observable<HttpResult<List<Border>>> sysBordersRequest(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_resource_query/get_sys_faceunity")
    Observable<HttpResult<List<FaceUnity>>> sysFaceunityRequest(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_resource_query/get_sys_gifts")
    Observable<HttpResult<List<Gift>>> sysGiftsRequest(@FieldMap ArrayMap<String, Object> arrayMap);
}
