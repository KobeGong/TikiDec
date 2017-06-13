package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class AvatarEditActivity$$Lambda$6 implements Consumer {
    private final AvatarEditActivity f1122a;

    private AvatarEditActivity$$Lambda$6(AvatarEditActivity avatarEditActivity) {
        this.a = avatarEditActivity;
    }

    public static Consumer lambdaFactory$(AvatarEditActivity avatarEditActivity) {
        return new AvatarEditActivity$$Lambda$6(avatarEditActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m464a((String) obj);
    }
}
