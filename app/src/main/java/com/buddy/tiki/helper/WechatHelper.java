package com.buddy.tiki.helper;

import com.buddy.tiki.ChatApp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WechatHelper {
    private IWXAPI f796a;

    private static class SingletonHolder {
        private static final WechatHelper f795a = new WechatHelper();
    }

    private WechatHelper() {
    }

    public static WechatHelper getInstance() {
        return SingletonHolder.f795a;
    }

    public IWXAPI getIwxapi() {
        if (this.f796a == null) {
            this.f796a = WXAPIFactory.createWXAPI(ChatApp.getInstance(), "wxbc381cbe55588e97");
            this.f796a.registerApp("wxbc381cbe55588e97");
        }
        return this.f796a;
    }
}
