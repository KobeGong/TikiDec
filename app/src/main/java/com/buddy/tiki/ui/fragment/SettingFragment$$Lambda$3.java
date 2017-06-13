package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SettingFragment$$Lambda$3 implements Consumer {
    private final SettingFragment f2207a;

    private SettingFragment$$Lambda$3(SettingFragment settingFragment) {
        this.a = settingFragment;
    }

    public static Consumer lambdaFactory$(SettingFragment settingFragment) {
        return new SettingFragment$$Lambda$3(settingFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1409e(obj);
    }
}
