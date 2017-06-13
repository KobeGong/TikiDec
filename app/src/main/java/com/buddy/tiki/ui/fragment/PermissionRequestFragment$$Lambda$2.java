package com.buddy.tiki.ui.fragment;

import com.tbruyelle.rxpermissions2.Permission;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PermissionRequestFragment$$Lambda$2 implements Consumer {
    private final PermissionRequestFragment f2173a;

    private PermissionRequestFragment$$Lambda$2(PermissionRequestFragment permissionRequestFragment) {
        this.a = permissionRequestFragment;
    }

    public static Consumer lambdaFactory$(PermissionRequestFragment permissionRequestFragment) {
        return new PermissionRequestFragment$$Lambda$2(permissionRequestFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1357a((Permission) obj);
    }
}
