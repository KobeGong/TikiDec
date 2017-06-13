package com.buddy.tiki.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.BuildConfig;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.weibo.openapi.StatusesAPI;
import com.buddy.tiki.weibo.openapi.UsersAPI;
import com.buddy.tiki.weibo.openapi.models.User;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

public class WeiboServiceHelper {
    private AuthInfo f804a;
    private SsoHandler f805b;
    private Oauth2AccessToken f806c;

    private class AuthListener implements WeiboAuthListener {
        final /* synthetic */ WeiboServiceHelper f801a;
        private WeiboAuthCallback f802b;

        public AuthListener(WeiboServiceHelper weiboServiceHelper, WeiboAuthCallback callback) {
            this.f801a = weiboServiceHelper;
            this.f802b = callback;
        }

        public void onComplete(Bundle bundle) {
            this.f801a.f806c = Oauth2AccessToken.parseAccessToken(bundle);
            if (this.f801a.f806c.isSessionValid()) {
                PreferenceUtil.saveWeiboAccessToken(this.f801a.f806c);
                this.f802b.onSuccess(this.f801a.f806c.getUid(), this.f801a.f806c.getToken(), this.f801a.f806c.getExpiresTime());
                return;
            }
            this.f802b.onFail("Session is not valid");
        }

        public void onWeiboException(WeiboException e) {
            this.f802b.onFail(e == null ? BuildConfig.VERSION_NAME : e.getMessage());
        }

        public void onCancel() {
        }
    }

    public interface PublishCallback<T> {
        void onFail(String str);

        void onSuccess(T t);
    }

    private static class SingletonHolder {
        private static final WeiboServiceHelper f803a = new WeiboServiceHelper();
    }

    public interface UserCallback<T> {
        void onFail(String str);

        void onSuccess(T t);
    }

    public interface WeiboAuthCallback {
        void onFail(String str);

        void onSuccess(String str, String str2, long j);
    }

    private WeiboServiceHelper() {
    }

    public static WeiboServiceHelper getInstance() {
        return SingletonHolder.f803a;
    }

    public SsoHandler loginWeibo(Activity activity, WeiboAuthCallback callback) {
        this.f804a = new AuthInfo(activity, "3853249382", "http://", "all");
        this.f805b = new SsoHandler(activity, this.f804a);
        this.f805b.authorize(new AuthListener(this, callback));
        return this.f805b;
    }

    public SsoHandler getSsoHandler() {
        return this.f805b;
    }

    public void reset() {
        this.f805b = null;
        this.f804a = null;
    }

    public void getWeiboUser(@NonNull Context context, final UserCallback<User> callback) {
        new UsersAPI(context, "c2e5300cb28a2d141244e04881bff521", this.f806c).show(Long.parseLong(this.f806c.getUid()), new RequestListener(this) {
            final /* synthetic */ WeiboServiceHelper f798b;

            public void onComplete(String s) {
                if (callback != null) {
                    callback.onSuccess(User.parse(s));
                }
            }

            public void onWeiboException(WeiboException e) {
                callback.onFail(e == null ? BuildConfig.VERSION_NAME : e.getMessage());
            }
        });
    }

    public void publish(String content, Bitmap bitmap, final PublishCallback<Boolean> callback) {
        new StatusesAPI(ChatApp.getInstance(), "c2e5300cb28a2d141244e04881bff521", PreferenceUtil.getWeiboAccessToken()).upload(content, bitmap, null, null, new RequestListener(this) {
            final /* synthetic */ WeiboServiceHelper f800b;

            public void onComplete(String s) {
                if (callback != null) {
                    callback.onSuccess(Boolean.valueOf(true));
                }
            }

            public void onWeiboException(WeiboException e) {
                if (callback != null) {
                    callback.onFail(e == null ? BuildConfig.VERSION_NAME : e.getMessage());
                }
            }
        });
    }

    public boolean isValidAccessToken() {
        Oauth2AccessToken accessToken = PreferenceUtil.getWeiboAccessToken();
        return accessToken != null && System.currentTimeMillis() < accessToken.getExpiresTime();
    }
}
