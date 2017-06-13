package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class AvatarEditActivity$$Lambda$3 implements Consumer {
    private final AvatarEditActivity f1119a;

    private AvatarEditActivity$$Lambda$3(AvatarEditActivity avatarEditActivity) {
        this.a = avatarEditActivity;
    }

    public static Consumer lambdaFactory$(AvatarEditActivity avatarEditActivity) {
        return new AvatarEditActivity$$Lambda$3(avatarEditActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m463a(obj);
    }
}
