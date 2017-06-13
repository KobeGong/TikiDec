package com.buddy.tiki.helper;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.app.VersionInfo;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VersionHelper$$Lambda$1 implements OnClickListener {
    private final VersionHelper f759a;
    private final VersionInfo f760b;

    private VersionHelper$$Lambda$1(VersionHelper versionHelper, VersionInfo versionInfo) {
        this.a = versionHelper;
        this.b = versionInfo;
    }

    public static OnClickListener lambdaFactory$(VersionHelper versionHelper, VersionInfo versionInfo) {
        return new VersionHelper$$Lambda$1(versionHelper, versionInfo);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m184a(this.b, view);
    }
}
