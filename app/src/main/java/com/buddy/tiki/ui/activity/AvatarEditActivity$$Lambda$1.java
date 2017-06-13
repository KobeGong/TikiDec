package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class AvatarEditActivity$$Lambda$1 implements Consumer {
    private final AvatarEditActivity f1117a;

    private AvatarEditActivity$$Lambda$1(AvatarEditActivity avatarEditActivity) {
        this.a = avatarEditActivity;
    }

    public static Consumer lambdaFactory$(AvatarEditActivity avatarEditActivity) {
        return new AvatarEditActivity$$Lambda$1(avatarEditActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m466b(obj);
    }
}
