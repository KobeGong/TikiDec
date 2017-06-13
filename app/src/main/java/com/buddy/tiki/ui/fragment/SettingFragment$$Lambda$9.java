package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SettingFragment$$Lambda$9 implements Consumer {
    private final SettingFragment f2213a;

    private SettingFragment$$Lambda$9(SettingFragment settingFragment) {
        this.a = settingFragment;
    }

    public static Consumer lambdaFactory$(SettingFragment settingFragment) {
        return new SettingFragment$$Lambda$9(settingFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1402a((ConfigInfo) obj);
    }
}
