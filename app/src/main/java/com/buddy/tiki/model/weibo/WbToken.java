package com.buddy.tiki.model.weibo;

import org.parceler.Parcel;

@Parcel
public class WbToken {
    String accessToken;
    long expiresTime;
    String uid;

    public WbToken(String uid, String accessToken, long expiresTime) {
        this.uid = uid;
        this.accessToken = accessToken;
        this.expiresTime = expiresTime;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresTime() {
        return this.expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }
}
