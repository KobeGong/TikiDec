package com.buddy.tiki.view.match;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.drawable.LoopTransitionDrawable;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.util.DisplayUtil;
import java.util.Random;
import org.bytedeco.javacpp.postproc;

public class SimpleMatchingView extends RelativeLayout {
    private static final TikiLog f3191a = TikiLog.getInstance(SimpleMatchingView.class.getSimpleName());
    private LoopTransitionDrawable f3192b;
    private AnimationDrawable f3193c;
    private Random f3194d;
    private boolean f3195e;

    public SimpleMatchingView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f3194d = new Random();
        this.f3195e = false;
        inflate(context, C0376R.layout.view_simple_matching, this);
        measure(MeasureSpec.makeMeasureSpec(DisplayUtil.getDisplayWidth(), postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec(DisplayUtil.getDisplayHeight(), postproc.PP_CPU_CAPS_3DNOW));
        this.f3192b = new LoopTransitionDrawable((long) this.f3194d.nextInt(2000));
        this.f3192b.setColors(new int[]{Color.parseColor("#FFDC1E"), Color.parseColor("#19FFE1"), Color.parseColor("#1973FF"), Color.parseColor("#F519FF"), Color.parseColor("#FF7D19")});
        this.f3192b.setInterval(PointerIconCompat.TYPE_DEFAULT);
        this.f3192b.setReverseDrawOrder(false);
        this.f3192b.setShapeOnly(false);
        setBackground(this.f3192b);
        this.f3193c = (AnimationDrawable) ((AppCompatImageView) findViewById(C0376R.id.tiki_loading)).getDrawable();
    }

    public SimpleMatchingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleMatchingView(Context context) {
        this(context, null);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        m2012a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m2013b();
    }

    private synchronized void m2012a() {
        if (this.f3193c != null) {
            this.f3193c.start();
        }
        this.f3195e = true;
    }

    private synchronized void m2013b() {
        if (this.f3193c != null) {
            this.f3193c.stop();
        }
        this.f3195e = false;
    }
}
