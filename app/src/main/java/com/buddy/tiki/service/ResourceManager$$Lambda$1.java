package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;

final /* synthetic */ class ResourceManager$$Lambda$1 implements Consumer {
    private final ResourceManager f933a;
    private final String f934b;

    private ResourceManager$$Lambda$1(ResourceManager resourceManager, String str) {
        this.a = resourceManager;
        this.b = str;
    }

    public static Consumer lambdaFactory$(ResourceManager resourceManager, String str) {
        return new ResourceManager$$Lambda$1(resourceManager, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m305b(this.b, (List) obj);
    }
}
