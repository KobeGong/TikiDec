package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PermissionRequestFragment$$Lambda$3 implements Consumer {
    private final PermissionRequestFragment f2174a;

    private PermissionRequestFragment$$Lambda$3(PermissionRequestFragment permissionRequestFragment) {
        this.a = permissionRequestFragment;
    }

    public static Consumer lambdaFactory$(PermissionRequestFragment permissionRequestFragment) {
        return new PermissionRequestFragment$$Lambda$3(permissionRequestFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1358a(obj);
    }
}
