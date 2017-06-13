package com.buddy.tiki.util;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.BuildConfig;
import com.buddy.tiki.view.WrapContentDraweeView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.CacheChoice;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

public class FrescoUtil {
    public static void setImageURI(@NonNull Context context, @NonNull SimpleDraweeView image, @NonNull Uri uri, Postprocessor postprocessor) {
        if (postprocessor == null) {
            image.setImageURI(uri);
            return;
        }
        image.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(uri).setPostprocessor(new BlurProcessor(context)).build())).setOldController(image.getController())).build());
    }

    public static void setImageURI(@Nullable SimpleDraweeView imageView, @Nullable String uri) {
        setImageURI(imageView, uri == null ? null : Uri.parse(uri));
    }

    public static void setImageURI(@Nullable SimpleDraweeView imageView, @Nullable String uri, boolean progressive) {
        setImageURI(imageView, uri == null ? null : Uri.parse(uri), Priority.MEDIUM, progressive);
    }

    public static void setImageURI(@Nullable SimpleDraweeView imageView, @Nullable Uri uri) {
        setImageURI(imageView, uri, Priority.MEDIUM, false);
    }

    public static void setImageURI(@Nullable SimpleDraweeView imageView, @Nullable Uri uri, @Nullable Priority priority, boolean progressive) {
        if (imageView != null) {
            if (uri == null) {
                imageView.setImageURI(BuildConfig.VERSION_NAME);
            } else if (imageView instanceof WrapContentDraweeView) {
                imageView.setImageURI(uri);
            } else {
                ImageRequestBuilder progressiveRenderingEnabled = ImageRequestBuilder.fromRequest(ImageRequest.fromUri(uri)).setProgressiveRenderingEnabled(progressive);
                if (priority == null) {
                    priority = Priority.MEDIUM;
                }
                imageView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(progressiveRenderingEnabled.setRequestPriority(priority).setCacheChoice(CacheChoice.SMALL).build())).setOldController(imageView.getController())).build());
            }
        }
    }
}
