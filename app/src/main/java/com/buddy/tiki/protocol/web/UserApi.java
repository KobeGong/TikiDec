package com.buddy.tiki.protocol.web;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.base.HttpResult;
import com.buddy.tiki.model.promo.PromoResult;
import com.buddy.tiki.model.user.SessionInfo;
import com.buddy.tiki.model.user.User;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {
    @FormUrlEncoded
    @POST("/top/i_user_action/facebook_oauth")
    Observable<HttpResult<SessionInfo>> facebookOauthAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/facebook_register")
    Observable<HttpResult<SessionInfo>> facebookRegisterAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_query/get_gift_send_remains")
    Observable<HttpResult<long[]>> getGiftSendRemains(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/logout")
    Observable<HttpResult<Boolean>> logoutAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/match")
    Observable<HttpResult<Boolean>> matchAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/oauth")
    Observable<HttpResult<String>> oauthAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/qq_oauth")
    Observable<HttpResult<SessionInfo>> qqOauthAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/qq_register")
    Observable<HttpResult<SessionInfo>> qqRegisterAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_search_query/search_by_tikiId")
    Observable<HttpResult<User>> searchTikiQuery(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/send_authcode")
    Observable<HttpResult<Void>> sendAuthCodeAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/set_avatar_nick_gender")
    Observable<HttpResult<Void>> setAvatarNickGenderAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/signin")
    Observable<HttpResult<SessionInfo>> signInAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/signup")
    Observable<HttpResult<SessionInfo>> signUpAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/sina_oauth")
    Observable<HttpResult<SessionInfo>> sinaOauthAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/sina_register")
    Observable<HttpResult<SessionInfo>> sinaRegisterAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_promo_action/submit_promo")
    Observable<HttpResult<PromoResult>> submitPromo(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/unmatch")
    Observable<HttpResult<Boolean>> unMatchAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/upload_device_token")
    Observable<HttpResult<Boolean>> uploadDeviceTokenAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/upload_river_token")
    Observable<HttpResult<Boolean>> uploadRiverTokenAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_query/getDetail")
    Observable<HttpResult<User>> userRequest(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/wechat_oauth")
    Observable<HttpResult<SessionInfo>> wechatOauthAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_user_action/wechat_register")
    Observable<HttpResult<SessionInfo>> wechatRegisterAction(@FieldMap ArrayMap<String, Object> arrayMap);
}
