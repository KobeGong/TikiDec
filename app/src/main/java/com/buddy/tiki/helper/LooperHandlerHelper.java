package com.buddy.tiki.helper;

import android.os.Handler;
import android.os.HandlerThread;

public class LooperHandlerHelper {
    private final Handler f693a;

    private static class SingletonHolder {
        private static final LooperHandlerHelper f692a = new LooperHandlerHelper();
    }

    private LooperHandlerHelper() {
        HandlerThread handlerThread = new HandlerThread("LoopHandlerThread");
        handlerThread.start();
        this.f693a = new Handler(handlerThread.getLooper());
    }

    public static LooperHandlerHelper getInstance() {
        return SingletonHolder.f692a;
    }

    public Handler getLooperHandler() {
        return this.f693a;
    }
}
