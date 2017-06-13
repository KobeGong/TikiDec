package com.buddy.tiki.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {
    private final Gson f689a;

    private static class SingletonHolder {
        private static final GsonHelper f688a = new GsonHelper();
    }

    private GsonHelper() {
        this.f689a = new GsonBuilder().create();
    }

    public static GsonHelper getInstance() {
        return SingletonHolder.f688a;
    }

    public Gson getGsonInstance() {
        return this.f689a;
    }
}
