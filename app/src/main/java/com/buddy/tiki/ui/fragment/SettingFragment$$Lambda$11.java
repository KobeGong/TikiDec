package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SettingFragment$$Lambda$11 implements Consumer {
    private final SettingFragment f2203a;

    private SettingFragment$$Lambda$11(SettingFragment settingFragment) {
        this.a = settingFragment;
    }

    public static Consumer lambdaFactory$(SettingFragment settingFragment) {
        return new SettingFragment$$Lambda$11(settingFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1403a((OperInfo) obj);
    }
}
