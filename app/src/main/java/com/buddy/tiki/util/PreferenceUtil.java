package com.buddy.tiki.util;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.model.resource.Gift;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import java.lang.reflect.Type;
import java.util.List;

public class PreferenceUtil {

    static class C04861 extends TypeToken<List<FaceUnity>> {
        C04861() {
        }
    }

    static class C04872 extends TypeToken<List<Gift>> {
        C04872() {
        }
    }

    private static SharedPreferences m1607a() {
        return ChatApp.getInstance().getSharedPreferences("setting_file", 0);
    }

    public static SharedPreferences getUserSharedPreference() {
        return ChatApp.getInstance().getSharedPreferences("user_file_" + TopConfig.f408a, 0);
    }

    private static SharedPreferences m1608b() {
        return ChatApp.getInstance().getSharedPreferences("tiki_cache_file", 0);
    }

    public static boolean isShowPermission() {
        return m1607a().getBoolean("PERMISSION_SHOW", false);
    }

    public static void setShowPermission() {
        m1607a().edit().putBoolean("PERMISSION_SHOW", true).apply();
    }

    public static Oauth2AccessToken getWeiboAccessToken() {
        String uid = m1609c();
        String accessToken = m1610d();
        String refreshToken = m1611e();
        long time = m1612f();
        Oauth2AccessToken token = new Oauth2AccessToken();
        token.setUid(uid);
        token.setToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setExpiresTime(time);
        return token;
    }

    public static void saveWeiboAccessToken(Oauth2AccessToken accessToken) {
        m1607a().edit().putString("WEIBO_TOKEN_UID", accessToken.getUid()).putString("WEIBO_TOKEN_ACCESS_TOKEN", accessToken.getToken()).putString("WEIBO_TOKEN_REFRESH_TOKEN", accessToken.getRefreshToken()).putLong("WEIBO_TOKEN_EXPIRES_TIME", accessToken.getExpiresTime()).apply();
    }

    private static String m1609c() {
        return m1607a().getString("WEIBO_TOKEN_UID", BuildConfig.VERSION_NAME);
    }

    private static String m1610d() {
        return m1607a().getString("WEIBO_TOKEN_ACCESS_TOKEN", BuildConfig.VERSION_NAME);
    }

    private static String m1611e() {
        return m1607a().getString("WEIBO_TOKEN_REFRESH_TOKEN", BuildConfig.VERSION_NAME);
    }

    private static long m1612f() {
        return m1607a().getLong("WEIBO_TOKEN_EXPIRES_TIME", 0);
    }

    public static long getSyncTimepoint() {
        return getUserSharedPreference().getLong("SYNC_FRIEND_TIMEPOINT", 0);
    }

    public static void setSyncTimepoint(long timepoint) {
        getUserSharedPreference().edit().putLong("SYNC_FRIEND_TIMEPOINT", timepoint).apply();
    }

    public static long getUpgradeShowTime() {
        return m1607a().getLong("UPGRADE_SHOWTIME", 0);
    }

    public static void setUpgradeShowtime() {
        m1607a().edit().putLong("UPGRADE_SHOWTIME", System.currentTimeMillis()).apply();
    }

    public static void clearUserPreference() {
        getUserSharedPreference().edit().clear().commit();
    }

    public static void clearAllPreference() {
        getUserSharedPreference().edit().clear().commit();
        m1607a().edit().clear().commit();
    }

    public static void setActiveFlag() {
        m1607a().edit().putBoolean("ACTIVE_FLAG", true).apply();
    }

    public static boolean getActiveStatus() {
        return m1607a().getBoolean("ACTIVE_FLAG", false);
    }

    public static boolean getPushTurnStatus() {
        return m1607a().getBoolean("PUSH_FLAG", true);
    }

    public static void setPushTurnFlag(boolean turnFlag) {
        m1607a().edit().putBoolean("PUSH_FLAG", turnFlag).apply();
    }

    public static void setShowIntroduceVideo() {
        m1607a().edit().putBoolean("SHOW_INTRODUCE_VIDEO", true).apply();
    }

    public static boolean getShowIntroduceFlag() {
        return m1607a().getBoolean("SHOW_INTRODUCE_VIDEO", false);
    }

    public static String getTikiUidToken() {
        return m1607a().getString("TIKI_UID_TOKEN", BuildConfig.VERSION_NAME);
    }

    public static void setTikiUidToken(String uid) {
        m1607a().edit().putString("TIKI_UID_TOKEN", uid).apply();
    }

    public static String getTikiSessionToken() {
        return m1607a().getString("TIKI_SESSION_TOKEN", BuildConfig.VERSION_NAME);
    }

    public static void setTikiSessionToken(String session) {
        m1607a().edit().putString("TIKI_SESSION_TOKEN", session).apply();
    }

    public static void resetTikiSyncPoint() {
        m1607a().edit().putBoolean("TIKI_RESET_POINT", true).apply();
        getUserSharedPreference().edit().putLong("SYNC_FRIEND_TIMEPOINT", 0).apply();
    }

    public static void putResetFlag() {
        m1607a().edit().putBoolean("TIKI_RESET_POINT", true).apply();
    }

    public static boolean getResetTikiSyncPoint() {
        return m1607a().getBoolean("TIKI_RESET_POINT", false);
    }

    public static boolean isGestureHint() {
        return getUserSharedPreference().getBoolean("GESTURE_HINT", false);
    }

    public static void setGestureHint(boolean gestureHint) {
        getUserSharedPreference().edit().putBoolean("GESTURE_HINT", gestureHint).apply();
    }

    public static int getApplyNumber() {
        return getUserSharedPreference().getInt("TIKI_APPLY_NUM", 0);
    }

    public static void setApplyNumber(int number) {
        getUserSharedPreference().edit().putInt("TIKI_APPLY_NUM", number).apply();
    }

    public static int getSysMsgNumber() {
        return getUserSharedPreference().getInt("TIKI_SYS_MSG_NUM", 0);
    }

    public static void setSysMsgNumber(int num) {
        getUserSharedPreference().edit().putInt("TIKI_SYS_MSG_NUM", num).apply();
    }

    public static String getLastChannelKey() {
        return m1607a().getString("TIKI_LAST_CHANNEL", BuildConfig.VERSION_NAME);
    }

    public static void setLastChannelKey(String channel) {
        m1607a().edit().putString("TIKI_LAST_CHANNEL", channel).apply();
    }

    public static boolean getFacebookLogin() {
        return getUserSharedPreference().getBoolean("TIKI_FACEBOOK_LOGIN", false);
    }

    public static void setFacebookLogin() {
        getUserSharedPreference().edit().putBoolean("TIKI_FACEBOOK_LOGIN", true).apply();
    }

    public static int getRecordTip() {
        return m1607a().getInt("TIKI_RECORD_TIP", 0);
    }

    public static void setRecordTip(int num) {
        m1607a().edit().putInt("TIKI_RECORD_TIP", num).apply();
    }

    public static int getMaskTip() {
        return m1607a().getInt("TIKI_MASK_TIP", 0);
    }

    public static void setMaskTip(int num) {
        m1607a().edit().putInt("TIKI_MASK_TIP", num).apply();
    }

    public static int getVideoChatTip() {
        return m1607a().getInt("VIDEO_CHAT_TIP", 0);
    }

    public static void setVideoChatTip(int num) {
        m1607a().edit().putInt("VIDEO_CHAT_TIP", num).apply();
    }

    public static boolean getExploreButtonTips() {
        return getUserSharedPreference().getBoolean("EXPLORE_BUTTON_TIP", false);
    }

    public static void setExploreButtonTips() {
        getUserSharedPreference().edit().putBoolean("EXPLORE_BUTTON_TIP", true).apply();
    }

    public static void saveConfigInfoCache(@NonNull ConfigInfo configInfo) {
        m1608b().edit().putString("CONFIG_INFO_CACHE", new GsonBuilder().create().toJson((Object) configInfo, (Type) ConfigInfo.class)).apply();
    }

    @Nullable
    public static ConfigInfo getConfigInfoCache() {
        String info = m1608b().getString("CONFIG_INFO_CACHE", BuildConfig.VERSION_NAME);
        if (TextUtils.isEmpty(info)) {
            return null;
        }
        return (ConfigInfo) new GsonBuilder().create().fromJson(info, ConfigInfo.class);
    }

    public static void saveFaceuAvatarCache(@NonNull List<FaceUnity> list) {
        m1608b().edit().putString("FACEU_AVATAR_CACHE", new GsonBuilder().create().toJson((Object) list)).apply();
    }

    @Nullable
    public static List<FaceUnity> getFaceuAvatarCache() {
        String faceu = m1608b().getString("FACEU_AVATAR_CACHE", BuildConfig.VERSION_NAME);
        if (TextUtils.isEmpty(faceu)) {
            return null;
        }
        return (List) new GsonBuilder().create().fromJson(faceu, new C04861().getType());
    }

    public static void saveOperInfoCache(OperInfo operInfo) {
        if (!TextUtils.isEmpty(TopConfig.f408a)) {
            getUserSharedPreference().edit().putString("OPER_INFO_CACHE", new GsonBuilder().create().toJson((Object) operInfo)).apply();
        }
    }

    @Nullable
    public static OperInfo getOperInfoCache() {
        if (TextUtils.isEmpty(TopConfig.f408a)) {
            return null;
        }
        String info = getUserSharedPreference().getString("OPER_INFO_CACHE", BuildConfig.VERSION_NAME);
        if (TextUtils.isEmpty(info)) {
            return null;
        }
        return (OperInfo) new GsonBuilder().create().fromJson(info, OperInfo.class);
    }

    public static void saveGiftListCache(@NonNull List<Gift> list) {
        m1608b().edit().putString("GIFT_LIST_CACHE", new GsonBuilder().create().toJson((Object) list)).apply();
    }

    @Nullable
    public static List<Gift> getGiftListCache() {
        String cache = m1608b().getString("GIFT_LIST_CACHE", BuildConfig.VERSION_NAME);
        if (TextUtils.isEmpty(cache)) {
            return null;
        }
        return (List) new GsonBuilder().create().fromJson(cache, new C04872().getType());
    }

    public static void saveWorkingStatus(boolean working) {
        getUserSharedPreference().edit().putBoolean("WORKING_STATUS", working).apply();
    }

    public static boolean getWorkingStatus() {
        return getUserSharedPreference().getBoolean("WORKING_STATUS", false);
    }

    public static boolean isShowFloatWindowPermission() {
        return m1607a().getBoolean("SHOW_FLOAT_WINDOW_PERMISSION", true);
    }

    public static void setShowFloatWindowPermission(boolean showFloatWindowPermission) {
        m1607a().edit().putBoolean("SHOW_FLOAT_WINDOW_PERMISSION", showFloatWindowPermission).apply();
    }
}
