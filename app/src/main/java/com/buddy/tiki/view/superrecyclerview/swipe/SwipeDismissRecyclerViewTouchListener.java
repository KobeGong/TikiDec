package com.buddy.tiki.view.superrecyclerview.swipe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class SwipeDismissRecyclerViewTouchListener implements OnTouchListener {
    private int f3301a;
    private int f3302b;
    private int f3303c;
    private long f3304d;
    private RecyclerView f3305e;
    private DismissCallbacks f3306f;
    private int f3307g = 1;
    private List<PendingDismissData> f3308h = new ArrayList();
    private int f3309i = 0;
    private float f3310j;
    private float f3311k;
    private boolean f3312l;
    private int f3313m;
    private VelocityTracker f3314n;
    private int f3315o;
    private View f3316p;
    private boolean f3317q;

    public interface DismissCallbacks {
        boolean canDismiss(int i);

        void onDismiss(RecyclerView recyclerView, int[] iArr);
    }

    class C05391 extends OnScrollListener {
        final /* synthetic */ SwipeDismissRecyclerViewTouchListener f3290a;

        C05391(SwipeDismissRecyclerViewTouchListener this$0) {
            this.f3290a = this$0;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            boolean z = true;
            SwipeDismissRecyclerViewTouchListener swipeDismissRecyclerViewTouchListener = this.f3290a;
            if (newState == 1) {
                z = false;
            }
            swipeDismissRecyclerViewTouchListener.setEnabled(z);
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }
    }

    static class PendingDismissData implements Comparable<PendingDismissData> {
        public int f3299a;
        public View f3300b;

        public PendingDismissData(int position, View view) {
            this.f3299a = position;
            this.f3300b = view;
        }

        public int compareTo(PendingDismissData other) {
            return other.f3299a - this.f3299a;
        }
    }

    static /* synthetic */ int m2050a(SwipeDismissRecyclerViewTouchListener x0) {
        int i = x0.f3309i - 1;
        x0.f3309i = i;
        return i;
    }

    public SwipeDismissRecyclerViewTouchListener(RecyclerView recyclerView, DismissCallbacks callbacks) {
        ViewConfiguration vc = ViewConfiguration.get(recyclerView.getContext());
        this.f3301a = vc.getScaledTouchSlop();
        this.f3302b = vc.getScaledMinimumFlingVelocity() * 16;
        this.f3303c = vc.getScaledMaximumFlingVelocity();
        this.f3304d = (long) recyclerView.getContext().getResources().getInteger(17694720);
        this.f3305e = recyclerView;
        this.f3306f = callbacks;
    }

    public void setEnabled(boolean enabled) {
        this.f3317q = !enabled;
    }

    public OnScrollListener makeScrollListener() {
        return new C05391(this);
    }

    @SuppressLint({"AndroidLintClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.f3307g < 2) {
            this.f3307g = this.f3305e.getWidth();
        }
        float deltaX;
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                if (this.f3317q) {
                    return false;
                }
                Rect rect = new Rect();
                int childCount = this.f3305e.getChildCount();
                int[] listViewCoords = new int[2];
                this.f3305e.getLocationOnScreen(listViewCoords);
                int x = ((int) motionEvent.getRawX()) - listViewCoords[0];
                int y = ((int) motionEvent.getRawY()) - listViewCoords[1];
                for (int i = 0; i < childCount; i++) {
                    View child = this.f3305e.getChildAt(i);
                    child.getHitRect(rect);
                    if (rect.contains(x, y)) {
                        this.f3316p = child;
                        if (this.f3316p != null) {
                            this.f3310j = motionEvent.getRawX();
                            this.f3311k = motionEvent.getRawY();
                            this.f3315o = this.f3305e.getChildPosition(this.f3316p);
                            if (this.f3306f.canDismiss(this.f3315o)) {
                                this.f3316p = null;
                            } else {
                                this.f3314n = VelocityTracker.obtain();
                                this.f3314n.addMovement(motionEvent);
                            }
                        }
                        return false;
                    }
                }
                if (this.f3316p != null) {
                    this.f3310j = motionEvent.getRawX();
                    this.f3311k = motionEvent.getRawY();
                    this.f3315o = this.f3305e.getChildPosition(this.f3316p);
                    if (this.f3306f.canDismiss(this.f3315o)) {
                        this.f3316p = null;
                    } else {
                        this.f3314n = VelocityTracker.obtain();
                        this.f3314n.addMovement(motionEvent);
                    }
                }
                return false;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                if (this.f3314n != null) {
                    deltaX = motionEvent.getRawX() - this.f3310j;
                    this.f3314n.addMovement(motionEvent);
                    this.f3314n.computeCurrentVelocity(PointerIconCompat.TYPE_DEFAULT);
                    float velocityX = this.f3314n.getXVelocity();
                    float absVelocityX = Math.abs(velocityX);
                    float absVelocityY = Math.abs(this.f3314n.getYVelocity());
                    boolean dismiss = false;
                    boolean dismissRight = false;
                    if (Math.abs(deltaX) > ((float) this.f3307g) / 2.0f && this.f3312l) {
                        dismiss = true;
                        dismissRight = deltaX > 0.0f;
                    } else if (((float) this.f3302b) <= absVelocityX && absVelocityX <= ((float) this.f3303c) && absVelocityY < absVelocityX && this.f3312l) {
                        dismiss = ((velocityX > 0.0f ? 1 : (velocityX == 0.0f ? 0 : -1)) < 0 ? 1 : null) == ((deltaX > 0.0f ? 1 : (deltaX == 0.0f ? 0 : -1)) < 0 ? 1 : null);
                        dismissRight = this.f3314n.getXVelocity() > 0.0f;
                    }
                    if (!dismiss || this.f3315o == -1) {
                        this.f3316p.animate().translationX(0.0f).alpha(1.0f).setDuration(this.f3304d).setListener(null);
                    } else {
                        float f;
                        final View downView = this.f3316p;
                        final int downPosition = this.f3315o;
                        this.f3309i++;
                        ViewPropertyAnimator animate = this.f3316p.animate();
                        if (dismissRight) {
                            f = (float) this.f3307g;
                        } else {
                            f = (float) (-this.f3307g);
                        }
                        animate.translationX(f).alpha(0.0f).setDuration(this.f3304d).setListener(new AnimatorListenerAdapter(this) {
                            final /* synthetic */ SwipeDismissRecyclerViewTouchListener f3293c;

                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                this.f3293c.m2052a(downView, downPosition);
                            }
                        });
                    }
                    this.f3314n.recycle();
                    this.f3314n = null;
                    this.f3310j = 0.0f;
                    this.f3311k = 0.0f;
                    this.f3316p = null;
                    this.f3315o = -1;
                    this.f3312l = false;
                    break;
                }
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                if (!(this.f3314n == null || this.f3317q)) {
                    this.f3314n.addMovement(motionEvent);
                    deltaX = motionEvent.getRawX() - this.f3310j;
                    float deltaY = motionEvent.getRawY() - this.f3311k;
                    if (Math.abs(deltaX) > ((float) this.f3301a) && Math.abs(deltaY) < Math.abs(deltaX) / 2.0f) {
                        int i2;
                        this.f3312l = true;
                        if (deltaX > 0.0f) {
                            i2 = this.f3301a;
                        } else {
                            i2 = -this.f3301a;
                        }
                        this.f3313m = i2;
                        this.f3305e.requestDisallowInterceptTouchEvent(true);
                        MotionEvent cancelEvent = MotionEvent.obtain(motionEvent);
                        cancelEvent.setAction((MotionEventCompat.getActionIndex(motionEvent) << 8) | 3);
                        this.f3305e.onTouchEvent(cancelEvent);
                        cancelEvent.recycle();
                    }
                    if (this.f3312l) {
                        this.f3316p.setTranslationX(deltaX - ((float) this.f3313m));
                        this.f3316p.setAlpha(Math.max(0.0f, Math.min(1.0f, 1.0f - ((2.0f * Math.abs(deltaX)) / ((float) this.f3307g)))));
                        return true;
                    }
                }
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                if (this.f3314n != null) {
                    if (this.f3316p != null && this.f3312l) {
                        this.f3316p.animate().translationX(0.0f).alpha(1.0f).setDuration(this.f3304d).setListener(null);
                    }
                    this.f3314n.recycle();
                    this.f3314n = null;
                    this.f3310j = 0.0f;
                    this.f3311k = 0.0f;
                    this.f3316p = null;
                    this.f3315o = -1;
                    this.f3312l = false;
                    break;
                }
                break;
        }
        return false;
    }

    private void m2052a(final View dismissView, int dismissPosition) {
        final LayoutParams lp = dismissView.getLayoutParams();
        final int originalHeight = dismissView.getHeight();
        ValueAnimator animator = ValueAnimator.ofInt(new int[]{originalHeight, 1}).setDuration(this.f3304d);
        animator.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ SwipeDismissRecyclerViewTouchListener f3295b;

            public void onAnimationEnd(Animator animation) {
                SwipeDismissRecyclerViewTouchListener.m2050a(this.f3295b);
                if (this.f3295b.f3309i == 0) {
                    Collections.sort(this.f3295b.f3308h);
                    int[] dismissPositions = new int[this.f3295b.f3308h.size()];
                    for (int i = this.f3295b.f3308h.size() - 1; i >= 0; i--) {
                        dismissPositions[i] = ((PendingDismissData) this.f3295b.f3308h.get(i)).f3299a;
                    }
                    this.f3295b.f3306f.onDismiss(this.f3295b.f3305e, dismissPositions);
                    this.f3295b.f3315o = -1;
                    for (PendingDismissData pendingDismiss : this.f3295b.f3308h) {
                        pendingDismiss.f3300b.setAlpha(1.0f);
                        pendingDismiss.f3300b.setTranslationX(0.0f);
                        LayoutParams lp = pendingDismiss.f3300b.getLayoutParams();
                        lp.height = originalHeight;
                        pendingDismiss.f3300b.setLayoutParams(lp);
                    }
                    long time = SystemClock.uptimeMillis();
                    this.f3295b.f3305e.dispatchTouchEvent(MotionEvent.obtain(time, time, 3, 0.0f, 0.0f, 0));
                    this.f3295b.f3308h.clear();
                }
            }
        });
        animator.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ SwipeDismissRecyclerViewTouchListener f3298c;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lp.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                dismissView.setLayoutParams(lp);
            }
        });
        this.f3308h.add(new PendingDismissData(dismissPosition, dismissView));
        animator.start();
    }
}
