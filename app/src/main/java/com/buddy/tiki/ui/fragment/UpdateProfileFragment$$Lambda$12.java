package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.wechat.WxUser;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$12 implements Consumer {
    private final UpdateProfileFragment f2229a;

    private UpdateProfileFragment$$Lambda$12(UpdateProfileFragment updateProfileFragment) {
        this.a = updateProfileFragment;
    }

    public static Consumer lambdaFactory$(UpdateProfileFragment updateProfileFragment) {
        return new UpdateProfileFragment$$Lambda$12(updateProfileFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1463a((WxUser) obj);
    }
}
