package com.buddy.tiki.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.util.NotificationUtil;
import io.reactivex.schedulers.Schedulers;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class IMBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            switch (bundle.getInt("action")) {
                case IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED /*10001*/:
                    String taskid = bundle.getString("taskid");
                    String messageid = bundle.getString("messageid");
                    byte[] payload = bundle.getByteArray("payload");
                    if (payload != null) {
                        NotificationUtil.showNotification(context, new String(payload));
                        return;
                    }
                    return;
                case IMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START /*10002*/:
                    if (!TextUtils.isEmpty(TopConfig.f409b) && TextUtils.isEmpty(TopConfig.f408a) && !TextUtils.isEmpty(bundle.getString("clientid"))) {
                        DataLayer.getInstance().getUserManager().uploadDeviceTokenAction(bundle.getString("clientid")).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribe(IMBroadcastReceiver$$Lambda$1.lambdaFactory$(), IMBroadcastReceiver$$Lambda$2.lambdaFactory$());
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    static /* synthetic */ void m209a(Boolean aBoolean) throws Exception {
    }

    static /* synthetic */ void m210a(Throwable throwable) throws Exception {
    }
}
