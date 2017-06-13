package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;

final /* synthetic */ class ResourceManager$$Lambda$3 implements Consumer {
    private final ResourceManager f936a;
    private final String f937b;

    private ResourceManager$$Lambda$3(ResourceManager resourceManager, String str) {
        this.a = resourceManager;
        this.b = str;
    }

    public static Consumer lambdaFactory$(ResourceManager resourceManager, String str) {
        return new ResourceManager$$Lambda$3(resourceManager, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m303a(this.b, (List) obj);
    }
}
