package com.buddy.tiki.service;

import com.buddy.tiki.model.wechat.WxRefreshToken;
import com.buddy.tiki.model.wechat.WxToken;
import com.buddy.tiki.model.wechat.WxUser;
import com.buddy.tiki.protocol.web.WechatApi;
import com.buddy.tiki.service.base.BaseManager;
import io.reactivex.Observable;

public class WechatManager extends BaseManager {
    private WechatApi f1010d;

    protected void mo2110b() {
        this.f1010d = (WechatApi) this.b.getWxServiceInstance(WechatApi.class);
    }

    public Observable<WxToken> accessTokenRequest(String appId, String secret, String code) {
        return this.f1010d.accessTokenRequest(appId, secret, code, "authorization_code");
    }

    public Observable<WxRefreshToken> refreshTokenRequest(String appId, String grantType, String refreshToken) {
        return this.f1010d.refreshTokenRequest(appId, grantType, refreshToken);
    }

    public Observable<WxUser> userInfoRequest(String accessToken, String openId) {
        return this.f1010d.userInfoRequest(accessToken, openId);
    }
}
