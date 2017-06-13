package com.buddy.tiki.helper;

import com.buddy.tiki.ChatApp;
import com.buddy.tiki.model.resource.Gift;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$5 implements Function {
    private static final DownloadHelper$$Lambda$5 f671a = new DownloadHelper$$Lambda$5();

    private DownloadHelper$$Lambda$5() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return Fresco.getImagePipeline().prefetchToDiskCache(ImageRequestBuilder.fromRequest(ImageRequest.fromUri(((Gift) obj).getSource())).setRequestPriority(Priority.LOW).build(), ChatApp.getInstance(), Priority.LOW);
    }
}
