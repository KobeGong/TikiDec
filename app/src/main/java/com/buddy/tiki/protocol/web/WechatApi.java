package com.buddy.tiki.protocol.web;

import com.buddy.tiki.model.wechat.WxRefreshToken;
import com.buddy.tiki.model.wechat.WxToken;
import com.buddy.tiki.model.wechat.WxUser;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WechatApi {
    @GET("/sns/oauth2/access_token")
    Observable<WxToken> accessTokenRequest(@Query("appid") String str, @Query("secret") String str2, @Query("code") String str3, @Query("grant_type") String str4);

    @GET("/sns/oauth2/refresh_token")
    Observable<WxRefreshToken> refreshTokenRequest(@Query("appid") String str, @Query("grant_type") String str2, @Query("refresh_token") String str3);

    @GET("/sns/userinfo")
    Observable<WxUser> userInfoRequest(@Query("access_token") String str, @Query("openid") String str2);
}
