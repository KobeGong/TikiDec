package com.buddy.tiki.helper;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import com.buddy.tiki.helper.VersionHelper.C03911;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VersionHelper$1$$Lambda$1 implements Function {
    private final Query f761a;

    private VersionHelper$1$$Lambda$1(Query query) {
        this.a = query;
    }

    public static Function lambdaFactory$(Query query) {
        return new VersionHelper$1$$Lambda$1(query);
    }

    @Hidden
    public Object apply(Object obj) {
        return C03911.m177a(this.a, (DownloadManager) obj);
    }
}
