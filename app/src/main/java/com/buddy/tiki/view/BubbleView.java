package com.buddy.tiki.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class BubbleView extends RelativeLayout {
    private List<Drawable> f2564a = new ArrayList();
    private int f2565b = m1665a(16);
    private int f2566c = m1665a(16);
    private int f2567d = 8;
    private int f2568e = 2;
    private int f2569f = 4000;
    private int f2570g = IMediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK;
    private int f2571h = 60;
    private float f2572i = 1.0f;
    private float f2573j = 1.0f;
    private int f2574k = IMediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK;

    public class BesselEvaluator implements TypeEvaluator<float[]> {
        final /* synthetic */ BubbleView f2561a;
        private float[] f2562b = new float[2];
        private float[] f2563c = new float[2];

        public BesselEvaluator(BubbleView this$0, float[] point1, float[] point2) {
            this.f2561a = this$0;
            this.f2562b = point1;
            this.f2563c = point2;
        }

        public float[] evaluate(float fraction, float[] point0, float[] point3) {
            return new float[]{(((((point0[0] * (1.0f - fraction)) * (1.0f - fraction)) * (1.0f - fraction)) + ((((this.f2562b[0] * 3.0f) * fraction) * (1.0f - fraction)) * (1.0f - fraction))) + ((((this.f2563c[0] * 3.0f) * (1.0f - fraction)) * fraction) * fraction)) + (((point3[0] * fraction) * fraction) * fraction), (((((point0[1] * (1.0f - fraction)) * (1.0f - fraction)) * (1.0f - fraction)) + ((((this.f2562b[1] * 3.0f) * fraction) * (1.0f - fraction)) * (1.0f - fraction))) + ((((this.f2563c[1] * 3.0f) * (1.0f - fraction)) * fraction) * fraction)) + (((point3[1] * fraction) * fraction) * fraction)};
        }
    }

    public BubbleView(Context context) {
        super(context);
    }

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BubbleView setDrawableList(List<Drawable> drawableList) {
        this.f2564a = drawableList;
        return this;
    }

    public BubbleView setRiseDuration(int riseDuration) {
        this.f2569f = riseDuration;
        return this;
    }

    public BubbleView setBottomPadding(int px) {
        this.f2570g = px;
        return this;
    }

    public BubbleView setOriginsOffset(int px) {
        this.f2571h = px;
        return this;
    }

    public BubbleView setScaleAnimation(float maxScale, float minScale) {
        this.f2572i = maxScale;
        this.f2573j = minScale;
        return this;
    }

    public BubbleView setAnimationDelay(int delay) {
        this.f2574k = delay;
        return this;
    }

    public void setMaxHeartNum(int maxHeartNum) {
        this.f2567d = maxHeartNum;
    }

    public void setMinHeartNum(int minHeartNum) {
        this.f2568e = minHeartNum;
    }

    public BubbleView setItemViewWH(int viewWidth, int viewHeight) {
        this.f2566c = viewHeight;
        this.f2565b = viewWidth;
        return this;
    }

    public BubbleView setGiftBoxImaeg(Drawable drawable, int positionX, int positionY) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawable);
        addView(imageView, new LayoutParams(imageView.getWidth(), imageView.getHeight()));
        imageView.setX((float) positionX);
        imageView.setY((float) positionY);
        return this;
    }

    public void startAnimation(int rankWidth, int rankHeight) {
        Observable.timer((long) this.f2574k, TimeUnit.MILLISECONDS).repeat((long) (((int) (Math.random() * ((double) (this.f2567d - this.f2568e)))) + this.f2568e)).observeOn(AndroidSchedulers.mainThread()).subscribe(BubbleView$$Lambda$1.lambdaFactory$(this, rankWidth, rankHeight));
    }

    /* synthetic */ void m1671c(int rankWidth, int rankHeight, Long aLong) throws Exception {
        m1667a(rankWidth, rankHeight);
    }

    public void startAnimation(int rankWidth, int rankHeight, int count) {
        Observable.timer((long) this.f2574k, TimeUnit.MILLISECONDS).repeat((long) count).observeOn(AndroidSchedulers.mainThread()).subscribe(BubbleView$$Lambda$2.lambdaFactory$(this, rankWidth, rankHeight));
    }

    /* synthetic */ void m1670b(int rankWidth, int rankHeight, Long aLong) throws Exception {
        m1667a(rankWidth, rankHeight);
    }

    public void startAnimation(int rankWidth, int rankHeight, int delay, int count) {
        Observable.timer((long) delay, TimeUnit.MILLISECONDS).repeat((long) count).observeOn(AndroidSchedulers.mainThread()).subscribe(BubbleView$$Lambda$3.lambdaFactory$(this, rankWidth, rankHeight));
    }

    /* synthetic */ void m1669a(int rankWidth, int rankHeight, Long aLong) throws Exception {
        m1667a(rankWidth, rankHeight);
    }

    private void m1667a(int rankWidth, int rankHeight) {
        rankHeight -= this.f2570g;
        switch ((int) (Math.random() * 3.0d)) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                rankWidth -= this.f2571h;
                break;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                rankWidth += this.f2571h;
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                rankHeight -= this.f2571h;
                break;
        }
        LayoutParams layoutParams = new LayoutParams(this.f2565b, this.f2566c);
        int heartDrawableIndex = (int) (((double) this.f2564a.size()) * Math.random());
        ImageView tempImageView = new ImageView(getContext());
        tempImageView.setImageDrawable((Drawable) this.f2564a.get(heartDrawableIndex));
        addView(tempImageView, layoutParams);
        ObjectAnimator riseAlphaAnimator = ObjectAnimator.ofFloat(tempImageView, "alpha", new float[]{1.0f, 0.0f});
        riseAlphaAnimator.setDuration((long) this.f2569f);
        ObjectAnimator riseScaleXAnimator = ObjectAnimator.ofFloat(tempImageView, "scaleX", new float[]{this.f2573j, this.f2572i});
        riseScaleXAnimator.setDuration((long) this.f2569f);
        ObjectAnimator riseScaleYAnimator = ObjectAnimator.ofFloat(tempImageView, "scaleY", new float[]{this.f2573j, this.f2572i});
        riseScaleYAnimator.setDuration((long) this.f2569f);
        ValueAnimator valueAnimator = m1666a(tempImageView, rankWidth, rankHeight);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator).with(riseAlphaAnimator).with(riseScaleXAnimator).with(riseScaleYAnimator);
        animatorSet.start();
    }

    private ValueAnimator m1666a(ImageView imageView, int rankWidth, int rankHeight) {
        float[] point0 = new float[]{((float) rankWidth) / 2.0f, (float) rankHeight};
        float[] point3 = new float[]{(float) (Math.random() * ((double) rankWidth)), 0.0f};
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new BesselEvaluator(this, new float[]{((float) (((double) rankWidth) * 0.1d)) + ((float) ((Math.random() * ((double) rankWidth)) * 0.8d)), (float) (((double) rankHeight) - ((Math.random() * ((double) rankHeight)) * 0.5d))}, new float[]{(float) (Math.random() * ((double) rankWidth)), (float) (Math.random() * ((double) (((float) rankHeight) - new float[]{((float) (((double) rankWidth) * 0.1d)) + ((float) ((Math.random() * ((double) rankWidth)) * 0.8d)), (float) (((double) rankHeight) - ((Math.random() * ((double) rankHeight)) * 0.5d))}[1])))}), new Object[]{point0, point3});
        valueAnimator.setDuration((long) this.f2569f);
        valueAnimator.addUpdateListener(BubbleView$$Lambda$4.lambdaFactory$(imageView));
        final ImageView imageView2 = imageView;
        valueAnimator.addListener(new AnimatorListener(this) {
            final /* synthetic */ BubbleView f2560b;

            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                this.f2560b.removeView(imageView2);
                imageView2.setImageDrawable(null);
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        return valueAnimator;
    }

    static /* synthetic */ void m1668a(ImageView imageView, ValueAnimator animation) {
        float[] currentPosition = new float[2];
        currentPosition = (float[]) animation.getAnimatedValue();
        imageView.setTranslationX(currentPosition[0]);
        imageView.setTranslationY(currentPosition[1]);
    }

    private int m1665a(int dp) {
        return (int) ((((float) dp) * getResources().getDisplayMetrics().density) + 0.5f);
    }
}
