package com.buddy.tiki.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.recyclerview.BuildConfig;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.log.TikiLog;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.GraphJSONObjectCallback;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import io.netty.handler.codec.rtsp.RtspHeaders.Values;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

public class FacebookHelper {
    private static final TikiLog f686a = TikiLog.getInstance("FacebookHelper");
    private CallbackManager f687b;

    @Parcel
    public static class FacebookUserInfo {
        public String avatar;
        public String email;
        public String firstName;
        public int gender;
        public String id;
        public String lastName;
        public String updateTime;
        public String userName;
    }

    public interface FacebookUserInfoCallback {
        void onFailed(String str);

        void onSuccess(FacebookUserInfo facebookUserInfo);
    }

    public void initFBLoginButton(View clickView, LoginButton lbFacebook, Fragment fragment, FacebookCallback<LoginResult> facebookCallback, Runnable addtionalClickEvent) {
        initFBLoginButton(lbFacebook, fragment, facebookCallback);
        if (clickView != null) {
            clickView.setOnClickListener(FacebookHelper$$Lambda$1.lambdaFactory$(addtionalClickEvent, lbFacebook));
        }
    }

    static /* synthetic */ void m140a(Runnable addtionalClickEvent, LoginButton lbFacebook, View v) {
        if (addtionalClickEvent != null) {
            addtionalClickEvent.run();
        }
        lbFacebook.callOnClick();
    }

    public void initFBLoginButton(LoginButton lbFacebook, Fragment fragment, FacebookCallback<LoginResult> facebookCallback) {
        lbFacebook.setReadPermissions("public_profile", NotificationCompatApi24.CATEGORY_EMAIL, "user_status");
        if (fragment != null) {
            lbFacebook.setFragment(fragment);
        }
        this.f687b = Factory.create();
        lbFacebook.registerCallback(this.f687b, facebookCallback);
    }

    public void getFacebookUserInfo(AccessToken accessToken, final FacebookUserInfoCallback callback) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphJSONObjectCallback(this) {
            final /* synthetic */ FacebookHelper f685b;

            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response == null) {
                    callback.onFailed(ChatApp.getInstance().getResources().getString(C0376R.string.fail_fetch_fb_user_info));
                    return;
                }
                JSONObject userObject = response.getJSONObject();
                if (userObject == null) {
                    callback.onFailed(ChatApp.getInstance().getResources().getString(C0376R.string.fail_fetch_fb_user_info));
                    return;
                }
                FacebookUserInfo userInfo = new FacebookUserInfo();
                userInfo.id = FacebookHelper.m141b(userObject, "id");
                userInfo.firstName = FacebookHelper.m141b(userObject, "firstName");
                userInfo.lastName = FacebookHelper.m141b(userObject, "last_name");
                userInfo.userName = FacebookHelper.m141b(userObject, "name");
                userInfo.updateTime = FacebookHelper.m141b(userObject, "updated_time");
                userInfo.email = FacebookHelper.m141b(userObject, NotificationCompatApi24.CATEGORY_EMAIL);
                if ("female".equals(FacebookHelper.m141b(userObject, "gender"))) {
                    userInfo.gender = 2;
                } else {
                    userInfo.gender = 1;
                }
                try {
                    JSONObject jsonObject = new JSONObject(FacebookHelper.m141b(userObject, "picture"));
                    JSONObject jSONObject;
                    try {
                        userInfo.avatar = jsonObject.optJSONObject("data").optString(Values.URL);
                        jSONObject = jsonObject;
                    } catch (JSONException e) {
                        jSONObject = jsonObject;
                    }
                } catch (JSONException e2) {
                }
                callback.onSuccess(userInfo);
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,first_name,last_name,gender,locale,timezone,updated_time,verified,picture");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    public CallbackManager getFacebookCallbackManager() {
        return this.f687b;
    }

    public static void logout() {
        LoginManager.getInstance().logOut();
    }

    private static String m141b(JSONObject graphResponse, String flag) {
        String value = BuildConfig.VERSION_NAME;
        try {
            value = graphResponse.getString(flag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        f686a.m261d("\u83b7\u53d6\u7528\u6237\u4fe1\u606f flag=" + flag + "   \u7ed3\u679c\u662f" + value);
        return value;
    }
}
