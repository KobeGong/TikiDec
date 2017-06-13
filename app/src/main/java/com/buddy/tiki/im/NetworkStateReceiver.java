package com.buddy.tiki.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.util.NetworkUtil;
import com.buddy.tiki.util.SchedulersCompat;

public class NetworkStateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && NetworkUtil.getConnectivityStatus(context) != 0 && !IMRtcClient.getInstance().isActive()) {
            DataLayer.getInstance().getAppManager().getConfigCache().compose(SchedulersCompat.applyIoSchedulers()).filter(NetworkStateReceiver$$Lambda$1.lambdaFactory$()).doOnNext(NetworkStateReceiver$$Lambda$2.lambdaFactory$()).subscribe(NetworkStateReceiver$$Lambda$3.lambdaFactory$(), NetworkStateReceiver$$Lambda$4.lambdaFactory$());
        }
    }

    static /* synthetic */ boolean m244c(ConfigInfo configInfo) throws Exception {
        return configInfo != null;
    }

    static /* synthetic */ void m241a(ConfigInfo configInfo) throws Exception {
    }

    static /* synthetic */ void m242a(Throwable throwable) throws Exception {
    }
}
