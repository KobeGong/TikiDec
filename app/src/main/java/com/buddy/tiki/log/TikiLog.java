package com.buddy.tiki.log;

import java.util.HashMap;
import java.util.Map;

public class TikiLog {
    private static final Map<String, TikiLog> f882a = new HashMap();
    private String f883b;

    private TikiLog(String tag) {
        this.f883b = tag;
    }

    public static TikiLog getInstance(String tag) {
        TikiLog log = (TikiLog) f882a.get(tag);
        if (log != null) {
            return log;
        }
        log = new TikiLog(tag);
        f882a.put(tag, log);
        return log;
    }

    public void m267v(String msg) {
    }

    public void m268v(String msg, Throwable tr) {
    }

    public void m261d(String msg) {
    }

    public void m262d(String msg, Throwable tr) {
    }

    public void m265i(String msg) {
    }

    public void m266i(String msg, Throwable tr) {
    }

    public void m269w(String msg) {
    }

    public void m270w(String msg, Throwable tr) {
    }

    public void m263e(String msg) {
    }

    public void m264e(String msg, Throwable tr) {
    }
}
