package com.buddy.tiki.view;

import android.content.Context;
import android.support.v4.view.PointerIconCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.view.match.MatchingView;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainViewLayout extends RelativeLayout {
    private static final TikiLog f2712a = TikiLog.getInstance(MainViewLayout.class.getSimpleName());
    private final int f2713b;
    private final int f2714c;
    private final int f2715d;
    private final int f2716e;
    private int f2717f;
    private int f2718g;
    private MotionEvent f2719h;
    private MotionEvent f2720i;
    private long f2721j;
    private boolean f2722k;
    private boolean f2723l;
    private long f2724m;
    private int f2725n;
    private int f2726o;
    private int f2727p;
    private int f2728q;
    private int f2729r;
    private int f2730s;
    private int f2731t;
    private VelocityTracker f2732u;
    private int f2733v;
    private DragListener f2734w;

    public interface DragListener {
        void onDragX(float f);

        void onDragY(float f);

        void onFlingX(float f);

        void onFlingY(float f);
    }

    public MainViewLayout(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f2713b = 1;
        this.f2714c = 2;
        this.f2715d = 4;
        this.f2716e = 3;
        this.f2722k = false;
        this.f2723l = false;
        this.f2724m = 200;
        this.f2732u = VelocityTracker.obtain();
        this.f2733v = 1;
        this.f2718g = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f2725n = DisplayUtil.getStatusBarHeight();
        if (!isInEditMode()) {
            this.f2726o = MatchingView.getIndicatorHeight(context, DisplayUtil.getDisplayWidth(), DisplayUtil.getDisplayHeight());
            this.f2727p = (this.f2726o * 4) / 5;
            this.f2728q = DisplayUtil.getDisplayHeight() - this.f2725n;
            this.f2729r = DisplayUtil.getDisplayWidth();
            this.f2730s = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
            this.f2731t = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        }
    }

    public MainViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainViewLayout(Context context) {
        this(context, null);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        return super.onInterceptTouchEvent(event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        long now;
        double distance;
        switch (event.getActionMasked()) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                this.f2719h = MotionEvent.obtain(event);
                this.f2721j = System.currentTimeMillis();
                this.f2732u.clear();
                break;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                if (this.f2719h != null) {
                    now = System.currentTimeMillis();
                    distance = Math.hypot((double) (event.getRawX() - this.f2719h.getRawX()), (double) (event.getRawY() - this.f2719h.getRawY()));
                    this.f2732u.computeCurrentVelocity(PointerIconCompat.TYPE_DEFAULT);
                    float vy = this.f2732u.getYVelocity();
                    float vx = this.f2732u.getXVelocity();
                    if (now - this.f2721j >= this.f2724m || distance >= ((double) this.f2718g)) {
                        if (Math.abs(vy) > Math.abs(vx) || this.f2717f == 2) {
                            m1725d(vy);
                        } else if (Math.abs(vx) >= Math.abs(vy) || this.f2717f == 3 || this.f2717f == 4) {
                            m1724c(vx);
                        }
                    }
                }
                this.f2722k = false;
                m1722a(this.f2720i);
                this.f2720i = null;
                m1722a(this.f2719h);
                this.f2719h = null;
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                this.f2732u.addMovement(event);
                if (!(this.f2722k || this.f2719h == null)) {
                    now = System.currentTimeMillis();
                    distance = Math.hypot((double) (event.getRawX() - this.f2719h.getRawX()), (double) (event.getRawY() - this.f2719h.getRawY()));
                    if (now - this.f2721j >= this.f2724m && distance >= ((double) this.f2718g)) {
                        float dx = event.getRawX() - this.f2719h.getRawX();
                        float dy = event.getRawY() - this.f2719h.getRawY();
                        if (Math.abs(dy) > Math.abs(dx)) {
                            if (dy > 0.0f) {
                                this.f2717f = 2;
                            } else {
                                this.f2717f = 1;
                            }
                        } else if (dx > 0.0f) {
                            this.f2717f = 3;
                        } else {
                            this.f2717f = 4;
                        }
                        this.f2722k = true;
                    }
                }
                if (this.f2722k && this.f2720i != null) {
                    switch (this.f2717f) {
                        case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                            break;
                        case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                            m1721a(event.getRawY() - this.f2720i.getRawY());
                            break;
                        case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                        case swscale.SWS_CS_FCC /*4*/:
                            m1723b(event.getRawX() - this.f2720i.getRawX());
                            break;
                        default:
                            break;
                    }
                }
                break;
        }
        m1722a(this.f2720i);
        this.f2720i = MotionEvent.obtain(event);
        return true;
    }

    private void m1722a(MotionEvent e) {
        if (e != null) {
            e.recycle();
        }
    }

    public void setStatus(int status) {
        this.f2733v = status;
    }

    private void m1721a(float dy) {
        if (this.f2734w != null) {
            this.f2734w.onDragY(dy);
        }
    }

    private void m1723b(float dx) {
        if (this.f2734w == null) {
        }
    }

    private void m1724c(float vx) {
        if (this.f2734w == null) {
        }
    }

    private void m1725d(float vy) {
        if (this.f2734w != null) {
            this.f2734w.onFlingY(vy);
        }
    }

    public void setDragListener(DragListener dragListener) {
        this.f2734w = dragListener;
    }
}
