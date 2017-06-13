package com.buddy.tiki.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.widget.ImageView;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BubbleView$$Lambda$4 implements AnimatorUpdateListener {
    private final ImageView f2558a;

    private BubbleView$$Lambda$4(ImageView imageView) {
        this.a = imageView;
    }

    public static AnimatorUpdateListener lambdaFactory$(ImageView imageView) {
        return new BubbleView$$Lambda$4(imageView);
    }

    @Hidden
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        BubbleView.m1668a(this.a, valueAnimator);
    }
}
