package com.buddy.tiki.helper;

import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.service.base.DataLayer;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$16 implements Function {
    private final FaceUnity f664a;

    private DownloadHelper$$Lambda$16(FaceUnity faceUnity) {
        this.a = faceUnity;
    }

    public static Function lambdaFactory$(FaceUnity faceUnity) {
        return new DownloadHelper$$Lambda$16(faceUnity);
    }

    @Hidden
    public Object apply(Object obj) {
        return DataLayer.getInstance().getDownloadApiManager().downloadFile(this.a.getIosSrc(), this.a.getId(), "Avatar");
    }
}
