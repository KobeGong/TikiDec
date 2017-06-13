package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

public class WrapContentDraweeView extends SimpleDraweeView {
    private int f2923a = -1;
    private final ControllerListener<ImageInfo> f2924b = new C05011(this);

    class C05011 extends BaseControllerListener<ImageInfo> {
        final /* synthetic */ WrapContentDraweeView f2922a;

        C05011(WrapContentDraweeView this$0) {
            this.f2922a = this$0;
        }

        public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            this.f2922a.m1817a(imageInfo);
        }

        public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
            this.f2922a.m1817a(imageInfo);
        }
    }

    public WrapContentDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public WrapContentDraweeView(Context context) {
        super(context);
    }

    public WrapContentDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapContentDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public WrapContentDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setImageURI(Uri uri, Object callerContext) {
        setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) getControllerBuilder()).setControllerListener(this.f2924b)).setCallerContext(callerContext)).setUri(uri).setOldController(getController())).build());
    }

    public void setGivenHeight(int givenHeight) {
        this.f2923a = givenHeight;
    }

    void m1817a(@Nullable ImageInfo imageInfo) {
        if (imageInfo != null) {
            LayoutParams layoutParams = getLayoutParams();
            if (this.f2923a != -1) {
                layoutParams.height = this.f2923a;
                layoutParams.width = (this.f2923a * imageInfo.getWidth()) / imageInfo.getHeight();
            } else {
                layoutParams.width = imageInfo.getWidth();
                layoutParams.height = imageInfo.getHeight();
            }
            setLayoutParams(layoutParams);
            setAspectRatio(((float) layoutParams.width) / ((float) layoutParams.height));
        }
    }
}
