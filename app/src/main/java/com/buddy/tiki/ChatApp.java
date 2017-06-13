package com.buddy.tiki;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import android.util.Log;
import com.buddy.tiki.base.BusinessDomain;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.helper.AnalyseHelper;
import com.buddy.tiki.helper.DatabaseHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.constant.ChannelKeys;
import com.buddy.tiki.model.constant.DomainType;
import com.buddy.tiki.model.user.TikiAdministrator;
import com.buddy.tiki.service.base.Foreground;
import com.buddy.tiki.service.base.HttpApi;
import com.buddy.tiki.util.PhoneStateUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.ProcessUtil;
import com.buddy.tiki.util.StringUtil;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.meituan.android.walle.WalleChannelReader;
import im.facechat.Rtc;
import io.realm.Realm;

public class ChatApp extends MultiDexApplication {
    private static final TikiLog f399a = TikiLog.getInstance(ChatApp.class.getSimpleName());
    private static ChatApp f400b;

    public static ChatApp getInstance() {
        return f400b;
    }

    public void onCreate() {
        super.onCreate();
        BusinessDomain.f403a = "https://17.api.tikiapp.im";
        BusinessDomain.f404b = "http://upcdn.live.biuapp.im:4002";
        if (ProcessUtil.isMainProcess(this)) {
            f400b = this;
            Rtc.preInitialize(this);
            Foreground.init(this);
            m91c();
            DatabaseHelper.getInstance().init(this);
            m89a();
            m92d();
            m93e();
            AnalyseHelper.init(this);
            m90b();
        }
    }

    private void m89a() {
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this).setMaxCacheSize(209715200).setMaxCacheSizeOnLowDiskSpace(209715200).setMaxCacheSizeOnVeryLowDiskSpace(209715200).build();
        Fresco.initialize(this, OkHttpImagePipelineConfigFactory.newBuilder(this, HttpApi.getInstance().getOkHttpClientInstance()).setMainDiskCacheConfig(diskCacheConfig).setSmallImageDiskCacheConfig(DiskCacheConfig.newBuilder(this).setMaxCacheSize(52428800).setMaxCacheSizeOnLowDiskSpace(26214400).setMaxCacheSizeOnVeryLowDiskSpace(26214400).build()).build());
    }

    private void m90b() {
        f399a.m265i("initAppsFlyer:DEBUG:false");
        if (!TextUtils.isEmpty(BusinessDomain.f405c) && ChannelKeys.GOOGLE_MARKET.toLowerCase().equals(BusinessDomain.f405c.toLowerCase())) {
            try {
                Class appsFlyerLib = Class.forName("com.appsflyer.AppsFlyerLib");
                Object appsFlyerInstance = appsFlyerLib.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                String deviceId = PhoneStateUtil.getDeviceId(this);
                appsFlyerLib.getMethod("setImeiData", new Class[]{String.class}).invoke(appsFlyerInstance, new Object[]{deviceId});
                String androidId = PhoneStateUtil.getAndroidId(this);
                appsFlyerLib.getMethod("setAndroidIdData", new Class[]{String.class}).invoke(appsFlyerInstance, new Object[]{androidId});
                f399a.m265i("DeviceID: " + deviceId + " androidId: " + androidId);
            } catch (Exception e) {
                f399a.m265i("AppsFlyer not integrated");
            }
        }
    }

    private void m91c() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp((Application) this);
    }

    private void m92d() {
        TopConfig.f410c = PhoneStateUtil.getDevieId(this);
        TopConfig.f412e = 2;
        TopConfig.f413f = DomainType.BIZ;
        TopConfig.f411d = String.valueOf("1.2.11");
        Realm realm = Realm.getDefaultInstance();
        TikiAdministrator tikiAdministrator = (TikiAdministrator) realm.where(TikiAdministrator.class).findFirst();
        if (tikiAdministrator != null && tikiAdministrator.isLoaded() && tikiAdministrator.isValid()) {
            TopConfig.f409b = tikiAdministrator.getSession();
            TopConfig.f408a = tikiAdministrator.getUid();
            PreferenceUtil.setTikiSessionToken(TopConfig.f409b);
            PreferenceUtil.setTikiUidToken(TopConfig.f408a);
            f399a.m261d("initTopConfig: uid = " + TopConfig.f408a + " session = " + TopConfig.f409b);
        }
        realm.close();
    }

    private void m93e() {
        String channel = WalleChannelReader.getChannel(this);
        if (TextUtils.isEmpty(channel)) {
            channel = ChannelKeys.LOCAL;
        }
        BusinessDomain.f405c = channel;
        Log.i(EnvironmentCompat.MEDIA_UNKNOWN, StringUtil.fuzz(channel));
    }
}
