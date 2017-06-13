package com.buddy.tiki.view.superrecyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.buddy.tiki.C0376R;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public abstract class DragDropTouchListener implements OnItemTouchListener {
    private final int f3199a;
    private RecyclerView f3200b;
    private Activity f3201c;
    private Drawable f3202d;
    private DisplayMetrics f3203e;
    private int f3204f;
    private int f3205g;
    private View f3206h;
    private int f3207i;
    private int f3208j;
    private int f3209k;
    private boolean f3210l;
    private boolean f3211m;

    protected abstract void m2027a(RecyclerView recyclerView, int i);

    protected abstract void m2028a(RecyclerView recyclerView, int i, int i2);

    public DragDropTouchListener(RecyclerView recyclerView, Activity activity) {
        this.f3204f = -1;
        this.f3205g = -1;
        this.f3207i = -1;
        this.f3208j = -1;
        this.f3211m = true;
        this.f3200b = recyclerView;
        this.f3201c = activity;
        this.f3203e = recyclerView.getResources().getDisplayMetrics();
        this.f3199a = (int) (50.0f / this.f3203e.density);
        this.f3202d = recyclerView.getResources().getDrawable(C0376R.drawable.drag_frame);
    }

    public DragDropTouchListener(RecyclerView recyclerView, Activity activity, Drawable dragHighlight) {
        this(recyclerView, activity);
        this.f3202d = dragHighlight;
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent event) {
        if (!this.f3211m) {
            return false;
        }
        switch (event.getAction() & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                return m2020a(event);
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                return m2025c(event);
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                if (this.f3210l && m2022b(event)) {
                    return true;
                }
                return false;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                return m2026d(event);
            default:
                return false;
        }
    }

    public void onTouchEvent(RecyclerView view, MotionEvent event) {
        if (this.f3210l) {
            switch (event.getAction() & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK) {
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    m2025c(event);
                    return;
                case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                    m2022b(event);
                    return;
                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                    m2026d(event);
                    return;
                default:
                    return;
            }
        }
    }

    public void startDrag() {
        View viewUnder = this.f3200b.findChildViewUnder((float) this.f3205g, (float) this.f3204f);
        if (viewUnder != null) {
            this.f3210l = true;
            this.f3208j = this.f3200b.getChildPosition(viewUnder);
            int[] viewRawCoords = m2023b(viewUnder);
            this.f3206h = m2017a(viewUnder);
            this.f3206h.setX((float) viewRawCoords[0]);
            this.f3206h.setY((float) viewRawCoords[1]);
            this.f3207i = viewRawCoords[1];
            this.f3201c.addContentView(this.f3206h, new LayoutParams(-2, -2));
            this.f3206h.bringToFront();
            viewUnder.setVisibility(4);
        }
    }

    private boolean m2020a(MotionEvent event) {
        this.f3209k = event.getPointerId(0);
        this.f3204f = (int) event.getY();
        this.f3205g = (int) event.getX();
        return false;
    }

    private boolean m2022b(MotionEvent event) {
        if (this.f3209k == -1) {
            return false;
        }
        this.f3206h.setY((float) (this.f3207i + (((int) event.getY(event.findPointerIndex(this.f3209k))) - this.f3204f)));
        m2018a();
        m2024c();
        return true;
    }

    private void m2018a() {
        int pos = this.f3208j;
        int abovePos = pos - 1;
        int belowPos = pos + 1;
        View aboveView = m2014a(abovePos);
        View belowView = m2014a(belowPos);
        int mobileViewY = (int) this.f3206h.getY();
        if (aboveView != null && aboveView.getTop() > -1 && mobileViewY < aboveView.getTop()) {
            m2019a(aboveView, pos, abovePos);
        }
        if (belowView != null && belowView.getTop() > -1 && mobileViewY > belowView.getTop()) {
            m2019a(belowView, pos, belowPos);
        }
    }

    private void m2019a(View switchView, int originalViewPos, int switchViewPos) {
        View originalView = m2014a(originalViewPos);
        int delta = originalView.getTop() - switchView.getTop();
        m2028a(this.f3200b, originalViewPos, switchViewPos);
        switchView.setVisibility(4);
        originalView.setVisibility(0);
        originalView.setTranslationY((float) (-delta));
        originalView.animate().translationYBy((float) delta).setDuration(150);
        this.f3208j = switchViewPos;
    }

    private boolean m2025c(MotionEvent event) {
        if (this.f3210l) {
            m2027a(this.f3200b, this.f3208j);
        }
        m2021b();
        return false;
    }

    private boolean m2026d(MotionEvent event) {
        m2021b();
        return false;
    }

    private void m2021b() {
        final View view = m2014a(this.f3208j);
        if (!(view == null || this.f3206h == null)) {
            this.f3206h.animate().y((float) m2023b(view)[1]).setDuration(150).setListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ DragDropTouchListener f3198b;

                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(0);
                    if (this.f3198b.f3206h != null) {
                        ((ViewGroup) this.f3198b.f3206h.getParent()).removeView(this.f3198b.f3206h);
                        this.f3198b.f3206h = null;
                    }
                }
            });
        }
        this.f3210l = false;
        this.f3207i = -1;
        this.f3208j = -1;
    }

    private View m2014a(int position) {
        ViewHolder viewHolder = this.f3200b.findViewHolderForPosition(position);
        return viewHolder == null ? null : viewHolder.itemView;
    }

    private boolean m2024c() {
        int height = this.f3200b.getHeight();
        int hoverViewTop = (int) this.f3206h.getY();
        int hoverHeight = this.f3206h.getHeight();
        if (hoverViewTop <= 0) {
            this.f3200b.scrollBy(0, -this.f3199a);
            return true;
        } else if (hoverViewTop + hoverHeight < height) {
            return false;
        } else {
            this.f3200b.scrollBy(0, this.f3199a);
            return true;
        }
    }

    private ImageView m2017a(View v) {
        if (v instanceof FrameLayout) {
            Drawable foreground = ((FrameLayout) v).getForeground();
            if (foreground != null) {
                foreground.setVisible(false, false);
            }
        } else if (v.getBackground() != null) {
            v.getBackground().setVisible(false, false);
        }
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        if (this.f3202d != null) {
            this.f3202d.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
            this.f3202d.draw(canvas);
        }
        ImageView imageView = new ImageView(this.f3200b.getContext());
        imageView.setImageBitmap(bitmap);
        return imageView;
    }

    private int[] m2023b(View locateView) {
        int topOffset = this.f3203e.heightPixels - this.f3201c.findViewById(16908290).getMeasuredHeight();
        loc = new int[2];
        locateView.getLocationOnScreen(loc);
        loc[1] = loc[1] - topOffset;
        return loc;
    }

    public void setEnabled(boolean enabled) {
        this.f3211m = enabled;
    }
}
