package com.buddy.tiki.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SocketService extends Service {
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }
}
