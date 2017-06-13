package com.buddy.tiki.wxapi;

import com.buddy.tiki.model.wechat.WxToken;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WXEntryActivity$$Lambda$1 implements Function {
    private final WXEntryActivity f3748a;

    private WXEntryActivity$$Lambda$1(WXEntryActivity wXEntryActivity) {
        this.a = wXEntryActivity;
    }

    public static Function lambdaFactory$(WXEntryActivity wXEntryActivity) {
        return new WXEntryActivity$$Lambda$1(wXEntryActivity);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m2319b((WxToken) obj);
    }
}
