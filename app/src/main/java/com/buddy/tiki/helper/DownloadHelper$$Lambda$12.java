package com.buddy.tiki.helper;

import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.util.FileUtil;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$12 implements Function {
    private static final DownloadHelper$$Lambda$12 f660a = new DownloadHelper$$Lambda$12();

    private DownloadHelper$$Lambda$12() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return FileUtil.isAvatarInDiskAsync(((FaceUnity) obj).getId()).filter(DownloadHelper$$Lambda$15.lambdaFactory$()).flatMap(DownloadHelper$$Lambda$16.lambdaFactory$((FaceUnity) obj));
    }
}
