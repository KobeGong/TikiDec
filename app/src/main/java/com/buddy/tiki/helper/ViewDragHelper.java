package com.buddy.tiki.helper;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.widget.ScrollerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import java.util.Arrays;
import org.bytedeco.javacpp.postproc;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class ViewDragHelper {
    private static final Interpolator f772a = new C03921();
    private final Callback f773b;
    private final ViewGroup f774c;
    private int f775d;
    private int f776e;
    private int f777f = -1;
    private float[] f778g;
    private float[] f779h;
    private float[] f780i;
    private float[] f781j;
    private int[] f782k;
    private int[] f783l;
    private int[] f784m;
    private int f785n;
    private VelocityTracker f786o;
    private float f787p;
    private float f788q;
    private int f789r;
    private int f790s;
    private ScrollerCompat f791t;
    private View f792u;
    private final Runnable f793v = new C03932(this);
    private boolean f794w;

    static class C03921 implements Interpolator {
        C03921() {
        }

        public float getInterpolation(float t) {
            t -= 1.0f;
            return ((((t * t) * t) * t) * t) + 1.0f;
        }
    }

    class C03932 implements Runnable {
        final /* synthetic */ ViewDragHelper f771a;

        C03932(ViewDragHelper this$0) {
            this.f771a = this$0;
        }

        public void run() {
            this.f771a.m203a(0);
        }
    }

    public static abstract class Callback {
        public abstract boolean tryCaptureView(View view, int i);

        public void onViewDragStateChanged(int state) {
        }

        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
        }

        public void onViewCaptured(View capturedChild, int activePointerId) {
        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {
        }

        public void onEdgeTouched(int edgeFlags, int pointerId) {
        }

        public boolean onEdgeLock(int edgeFlags) {
            return false;
        }

        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
        }

        public int getOrderedChildIndex(int index) {
            return index;
        }

        public int getViewHorizontalDragRange(View child) {
            return 0;
        }

        public int getViewVerticalDragRange(View child) {
            return 0;
        }

        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return 0;
        }

        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }
    }

    private ViewDragHelper(Context context, ViewGroup forParent, Callback cb) {
        if (forParent == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (cb == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.f774c = forParent;
            this.f773b = cb;
            ViewConfiguration vc = ViewConfiguration.get(context);
            this.f789r = (int) ((20.0f * context.getResources().getDisplayMetrics().density) + 0.5f);
            this.f776e = vc.getScaledTouchSlop();
            this.f787p = (float) vc.getScaledMaximumFlingVelocity();
            this.f788q = (float) vc.getScaledMinimumFlingVelocity();
            this.f791t = ScrollerCompat.create(context, f772a);
        }
    }

    public static ViewDragHelper create(ViewGroup forParent, Callback cb) {
        return new ViewDragHelper(forParent.getContext(), forParent, cb);
    }

    public static ViewDragHelper create(ViewGroup forParent, float sensitivity, Callback cb) {
        ViewDragHelper helper = create(forParent, cb);
        helper.f776e = (int) (((float) helper.f776e) * (1.0f / sensitivity));
        return helper;
    }

    public void setSensitivity(Context context, float sensitivity) {
        this.f776e = (int) (((float) ViewConfiguration.get(context).getScaledTouchSlop()) * (1.0f / Math.max(0.0f, Math.min(1.0f, sensitivity))));
    }

    public void setMaxVelocity(float maxVel) {
        this.f787p = maxVel;
    }

    public float getMinVelocity() {
        return this.f788q;
    }

    public void setMinVelocity(float minVel) {
        this.f788q = minVel;
    }

    public int getViewDragState() {
        return this.f775d;
    }

    public void setEdgeTrackingEnabled(int edgeFlags) {
        this.f790s = edgeFlags;
    }

    public int getEdgeSize() {
        return this.f789r;
    }

    public void setEdgeSize(int size) {
        this.f789r = size;
    }

    public void captureChildView(View childView, int activePointerId) {
        if (childView.getParent() != this.f774c) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f774c + ")");
        }
        this.f792u = childView;
        this.f777f = activePointerId;
        this.f773b.onViewCaptured(childView, activePointerId);
        m203a(1);
    }

    public View getCapturedView() {
        return this.f792u;
    }

    public int getActivePointerId() {
        return this.f777f;
    }

    public int getTouchSlop() {
        return this.f776e;
    }

    public void cancel() {
        this.f777f = -1;
        m190a();
        if (this.f786o != null) {
            this.f786o.recycle();
            this.f786o = null;
        }
    }

    public void abort() {
        cancel();
        if (this.f775d == 2) {
            int oldX = this.f791t.getCurrX();
            int oldY = this.f791t.getCurrY();
            this.f791t.abortAnimation();
            int newX = this.f791t.getCurrX();
            int newY = this.f791t.getCurrY();
            this.f773b.onViewPositionChanged(this.f792u, newX, newY, newX - oldX, newY - oldY);
        }
        m203a(0);
    }

    public boolean smoothSlideViewTo(View child, int finalLeft, int finalTop) {
        this.f792u = child;
        this.f777f = -1;
        return m195a(finalLeft, finalTop, 0, 0);
    }

    public boolean settleCapturedViewAt(int finalLeft, int finalTop) {
        if (this.f794w) {
            return m195a(finalLeft, finalTop, (int) VelocityTrackerCompat.getXVelocity(this.f786o, this.f777f), (int) VelocityTrackerCompat.getYVelocity(this.f786o, this.f777f));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean m195a(int finalLeft, int finalTop, int xvel, int yvel) {
        int startLeft = this.f792u.getLeft();
        int startTop = this.f792u.getTop();
        int dx = finalLeft - startLeft;
        int dy = finalTop - startTop;
        if (dx == 0 && dy == 0) {
            this.f791t.abortAnimation();
            m203a(0);
            return false;
        }
        this.f791t.startScroll(startLeft, startTop, dx, dy, m189a(this.f792u, dx, dy, xvel, yvel));
        m203a(2);
        return true;
    }

    private int m189a(View child, int dx, int dy, int xvel, int yvel) {
        xvel = m197b(xvel, (int) this.f788q, (int) this.f787p);
        yvel = m197b(yvel, (int) this.f788q, (int) this.f787p);
        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);
        int absXVel = Math.abs(xvel);
        int absYVel = Math.abs(yvel);
        int addedVel = absXVel + absYVel;
        int addedDistance = absDx + absDy;
        return (int) ((((float) m188a(dx, xvel, this.f773b.getViewHorizontalDragRange(child))) * (xvel != 0 ? ((float) absXVel) / ((float) addedVel) : ((float) absDx) / ((float) addedDistance))) + (((float) m188a(dy, yvel, this.f773b.getViewVerticalDragRange(child))) * (yvel != 0 ? ((float) absYVel) / ((float) addedVel) : ((float) absDy) / ((float) addedDistance))));
    }

    private int m188a(int delta, int velocity, int motionRange) {
        if (delta == 0) {
            return 0;
        }
        int duration;
        int width = this.f774c.getWidth();
        int halfWidth = width / 2;
        float distance = ((float) halfWidth) + (((float) halfWidth) * m185a(Math.min(1.0f, ((float) Math.abs(delta)) / ((float) width))));
        velocity = Math.abs(velocity);
        if (velocity > 0) {
            duration = Math.round(1000.0f * Math.abs(distance / ((float) velocity))) * 4;
        } else {
            duration = (int) (((((float) Math.abs(delta)) / ((float) motionRange)) + 1.0f) * 256.0f);
        }
        return Math.min(duration, 600);
    }

    private int m197b(int value, int absMin, int absMax) {
        int absValue = Math.abs(value);
        if (absValue < absMin) {
            return 0;
        }
        if (absValue <= absMax) {
            return value;
        }
        if (value <= 0) {
            return -absMax;
        }
        return absMax;
    }

    private float m186a(float value, float absMin, float absMax) {
        float absValue = Math.abs(value);
        if (absValue < absMin) {
            return 0.0f;
        }
        if (absValue <= absMax) {
            return value;
        }
        if (value <= 0.0f) {
            return -absMax;
        }
        return absMax;
    }

    private float m185a(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    public void flingCapturedView(int minLeft, int minTop, int maxLeft, int maxTop) {
        if (this.f794w) {
            this.f791t.fling(this.f792u.getLeft(), this.f792u.getTop(), (int) VelocityTrackerCompat.getXVelocity(this.f786o, this.f777f), (int) VelocityTrackerCompat.getYVelocity(this.f786o, this.f777f), minLeft, maxLeft, minTop, maxTop);
            m203a(2);
            return;
        }
        throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
    }

    public boolean continueSettling(boolean deferCallbacks) {
        if (this.f775d == 2) {
            boolean keepGoing = this.f791t.computeScrollOffset();
            int x = this.f791t.getCurrX();
            int y = this.f791t.getCurrY();
            int dx = x - this.f792u.getLeft();
            int dy = y - this.f792u.getTop();
            if (dx != 0) {
                this.f792u.offsetLeftAndRight(dx);
            }
            if (dy != 0) {
                this.f792u.offsetTopAndBottom(dy);
            }
            if (!(dx == 0 && dy == 0)) {
                this.f773b.onViewPositionChanged(this.f792u, x, y, dx, dy);
            }
            if (keepGoing && x == this.f791t.getFinalX() && y == this.f791t.getFinalY()) {
                this.f791t.abortAnimation();
                keepGoing = this.f791t.isFinished();
            }
            if (!keepGoing) {
                if (deferCallbacks) {
                    this.f774c.post(this.f793v);
                } else {
                    m203a(0);
                }
            }
        }
        return this.f775d == 2;
    }

    private void m191a(float xvel, float yvel) {
        this.f794w = true;
        this.f773b.onViewReleased(this.f792u, xvel, yvel);
        this.f794w = false;
        if (this.f775d == 1) {
            m203a(0);
        }
    }

    private void m190a() {
        if (this.f778g != null) {
            Arrays.fill(this.f778g, 0.0f);
            Arrays.fill(this.f779h, 0.0f);
            Arrays.fill(this.f780i, 0.0f);
            Arrays.fill(this.f781j, 0.0f);
            Arrays.fill(this.f782k, 0);
            Arrays.fill(this.f783l, 0);
            Arrays.fill(this.f784m, 0);
            this.f785n = 0;
        }
    }

    private void m200b(int pointerId) {
        if (this.f778g != null) {
            this.f778g[pointerId] = 0.0f;
            this.f779h[pointerId] = 0.0f;
            this.f780i[pointerId] = 0.0f;
            this.f781j[pointerId] = 0.0f;
            this.f782k[pointerId] = 0;
            this.f783l[pointerId] = 0;
            this.f784m[pointerId] = 0;
            this.f785n &= (1 << pointerId) ^ -1;
        }
    }

    private void m202c(int pointerId) {
        if (this.f778g == null || this.f778g.length <= pointerId) {
            float[] imx = new float[(pointerId + 1)];
            float[] imy = new float[(pointerId + 1)];
            float[] lmx = new float[(pointerId + 1)];
            float[] lmy = new float[(pointerId + 1)];
            int[] iit = new int[(pointerId + 1)];
            int[] edip = new int[(pointerId + 1)];
            int[] edl = new int[(pointerId + 1)];
            if (this.f778g != null) {
                System.arraycopy(this.f778g, 0, imx, 0, this.f778g.length);
                System.arraycopy(this.f779h, 0, imy, 0, this.f779h.length);
                System.arraycopy(this.f780i, 0, lmx, 0, this.f780i.length);
                System.arraycopy(this.f781j, 0, lmy, 0, this.f781j.length);
                System.arraycopy(this.f782k, 0, iit, 0, this.f782k.length);
                System.arraycopy(this.f783l, 0, edip, 0, this.f783l.length);
                System.arraycopy(this.f784m, 0, edl, 0, this.f784m.length);
            }
            this.f778g = imx;
            this.f779h = imy;
            this.f780i = lmx;
            this.f781j = lmy;
            this.f782k = iit;
            this.f783l = edip;
            this.f784m = edl;
        }
    }

    private void m192a(float x, float y, int pointerId) {
        m202c(pointerId);
        float[] fArr = this.f778g;
        this.f780i[pointerId] = x;
        fArr[pointerId] = x;
        fArr = this.f779h;
        this.f781j[pointerId] = y;
        fArr[pointerId] = y;
        this.f782k[pointerId] = m187a((int) x, (int) y);
        this.f785n |= 1 << pointerId;
    }

    private void m193a(MotionEvent ev) {
        int pointerCount = MotionEventCompat.getPointerCount(ev);
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = MotionEventCompat.getPointerId(ev, i);
            float x = MotionEventCompat.getX(ev, i);
            float y = MotionEventCompat.getY(ev, i);
            this.f780i[pointerId] = x;
            this.f781j[pointerId] = y;
        }
    }

    public boolean isPointerDown(int pointerId) {
        return (this.f785n & (1 << pointerId)) != 0;
    }

    void m203a(int state) {
        if (this.f775d != state) {
            this.f775d = state;
            this.f773b.onViewDragStateChanged(state);
            if (state == 0) {
                this.f792u = null;
            }
        }
    }

    boolean m204a(View toCapture, int pointerId) {
        if (toCapture == this.f792u && this.f777f == pointerId) {
            return true;
        }
        if (toCapture == null || !this.f773b.tryCaptureView(toCapture, pointerId)) {
            return false;
        }
        this.f777f = pointerId;
        captureChildView(toCapture, pointerId);
        return true;
    }

    public boolean shouldInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        int actionIndex = MotionEventCompat.getActionIndex(ev);
        if (action == 0) {
            cancel();
        }
        if (this.f786o == null) {
            this.f786o = VelocityTracker.obtain();
        }
        this.f786o.addMovement(ev);
        float x;
        float y;
        int pointerId;
        View toCapture;
        int edgesTouched;
        switch (action) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                x = ev.getX();
                y = ev.getY();
                pointerId = MotionEventCompat.getPointerId(ev, 0);
                m192a(x, y, pointerId);
                toCapture = findTopChildUnder((int) x, (int) y);
                if (toCapture == this.f792u && this.f775d == 2) {
                    m204a(toCapture, pointerId);
                }
                edgesTouched = this.f782k[pointerId];
                if ((this.f790s & edgesTouched) != 0) {
                    this.f773b.onEdgeTouched(this.f790s & edgesTouched, pointerId);
                    break;
                }
                break;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                cancel();
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                int pointerCount = MotionEventCompat.getPointerCount(ev);
                int i = 0;
                while (i < pointerCount) {
                    pointerId = MotionEventCompat.getPointerId(ev, i);
                    x = MotionEventCompat.getX(ev, i);
                    y = MotionEventCompat.getY(ev, i);
                    float dx = x - this.f778g[pointerId];
                    float dy = y - this.f779h[pointerId];
                    m199b(dx, dy, pointerId);
                    if (this.f775d != 1) {
                        toCapture = findTopChildUnder((int) x, (int) y);
                        if (toCapture == null || !m196a(toCapture, dx, dy) || !m204a(toCapture, pointerId)) {
                            i++;
                        }
                    }
                    m193a(ev);
                    break;
                }
                m193a(ev);
                break;
            case swscale.SWS_CS_SMPTE170M /*5*/:
                pointerId = MotionEventCompat.getPointerId(ev, actionIndex);
                x = MotionEventCompat.getX(ev, actionIndex);
                y = MotionEventCompat.getY(ev, actionIndex);
                m192a(x, y, pointerId);
                if (this.f775d != 0) {
                    if (this.f775d == 2) {
                        toCapture = findTopChildUnder((int) x, (int) y);
                        if (toCapture == this.f792u) {
                            m204a(toCapture, pointerId);
                            break;
                        }
                    }
                }
                edgesTouched = this.f782k[pointerId];
                if ((this.f790s & edgesTouched) != 0) {
                    this.f773b.onEdgeTouched(this.f790s & edgesTouched, pointerId);
                    break;
                }
                break;
            case postproc.PP_QUALITY_MAX /*6*/:
                m200b(MotionEventCompat.getPointerId(ev, actionIndex));
                break;
        }
        if (this.f775d == 1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processTouchEvent(android.view.MotionEvent r22) {
        /*
        r21 = this;
        r3 = android.support.v4.view.MotionEventCompat.getActionMasked(r22);
        r4 = android.support.v4.view.MotionEventCompat.getActionIndex(r22);
        if (r3 != 0) goto L_0x000d;
    L_0x000a:
        r21.cancel();
    L_0x000d:
        r0 = r21;
        r0 = r0.f786o;
        r19 = r0;
        if (r19 != 0) goto L_0x001f;
    L_0x0015:
        r19 = android.view.VelocityTracker.obtain();
        r0 = r19;
        r1 = r21;
        r1.f786o = r0;
    L_0x001f:
        r0 = r21;
        r0 = r0.f786o;
        r19 = r0;
        r0 = r19;
        r1 = r22;
        r0.addMovement(r1);
        switch(r3) {
            case 0: goto L_0x0030;
            case 1: goto L_0x0287;
            case 2: goto L_0x011a;
            case 3: goto L_0x029d;
            case 4: goto L_0x002f;
            case 5: goto L_0x008e;
            case 6: goto L_0x01fe;
            default: goto L_0x002f;
        };
    L_0x002f:
        return;
    L_0x0030:
        r17 = r22.getX();
        r18 = r22.getY();
        r19 = 0;
        r0 = r22;
        r1 = r19;
        r15 = android.support.v4.view.MotionEventCompat.getPointerId(r0, r1);
        r0 = r17;
        r0 = (int) r0;
        r19 = r0;
        r0 = r18;
        r0 = (int) r0;
        r20 = r0;
        r0 = r21;
        r1 = r19;
        r2 = r20;
        r16 = r0.findTopChildUnder(r1, r2);
        r0 = r21;
        r1 = r17;
        r2 = r18;
        r0.m192a(r1, r2, r15);
        r0 = r21;
        r1 = r16;
        r0.m204a(r1, r15);
        r0 = r21;
        r0 = r0.f782k;
        r19 = r0;
        r7 = r19[r15];
        r0 = r21;
        r0 = r0.f790s;
        r19 = r0;
        r19 = r19 & r7;
        if (r19 == 0) goto L_0x002f;
    L_0x0078:
        r0 = r21;
        r0 = r0.f773b;
        r19 = r0;
        r0 = r21;
        r0 = r0.f790s;
        r20 = r0;
        r20 = r20 & r7;
        r0 = r19;
        r1 = r20;
        r0.onEdgeTouched(r1, r15);
        goto L_0x002f;
    L_0x008e:
        r0 = r22;
        r15 = android.support.v4.view.MotionEventCompat.getPointerId(r0, r4);
        r0 = r22;
        r17 = android.support.v4.view.MotionEventCompat.getX(r0, r4);
        r0 = r22;
        r18 = android.support.v4.view.MotionEventCompat.getY(r0, r4);
        r0 = r21;
        r1 = r17;
        r2 = r18;
        r0.m192a(r1, r2, r15);
        r0 = r21;
        r0 = r0.f775d;
        r19 = r0;
        if (r19 != 0) goto L_0x00f5;
    L_0x00b1:
        r0 = r17;
        r0 = (int) r0;
        r19 = r0;
        r0 = r18;
        r0 = (int) r0;
        r20 = r0;
        r0 = r21;
        r1 = r19;
        r2 = r20;
        r16 = r0.findTopChildUnder(r1, r2);
        r0 = r21;
        r1 = r16;
        r0.m204a(r1, r15);
        r0 = r21;
        r0 = r0.f782k;
        r19 = r0;
        r7 = r19[r15];
        r0 = r21;
        r0 = r0.f790s;
        r19 = r0;
        r19 = r19 & r7;
        if (r19 == 0) goto L_0x002f;
    L_0x00de:
        r0 = r21;
        r0 = r0.f773b;
        r19 = r0;
        r0 = r21;
        r0 = r0.f790s;
        r20 = r0;
        r20 = r20 & r7;
        r0 = r19;
        r1 = r20;
        r0.onEdgeTouched(r1, r15);
        goto L_0x002f;
    L_0x00f5:
        r0 = r17;
        r0 = (int) r0;
        r19 = r0;
        r0 = r18;
        r0 = (int) r0;
        r20 = r0;
        r0 = r21;
        r1 = r19;
        r2 = r20;
        r19 = r0.isCapturedViewUnder(r1, r2);
        if (r19 == 0) goto L_0x002f;
    L_0x010b:
        r0 = r21;
        r0 = r0.f792u;
        r19 = r0;
        r0 = r21;
        r1 = r19;
        r0.m204a(r1, r15);
        goto L_0x002f;
    L_0x011a:
        r0 = r21;
        r0 = r0.f775d;
        r19 = r0;
        r20 = 1;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x018e;
    L_0x0128:
        r0 = r21;
        r0 = r0.f777f;
        r19 = r0;
        r0 = r22;
        r1 = r19;
        r12 = android.support.v4.view.MotionEventCompat.findPointerIndex(r0, r1);
        r0 = r22;
        r17 = android.support.v4.view.MotionEventCompat.getX(r0, r12);
        r0 = r22;
        r18 = android.support.v4.view.MotionEventCompat.getY(r0, r12);
        r0 = r21;
        r0 = r0.f780i;
        r19 = r0;
        r0 = r21;
        r0 = r0.f777f;
        r20 = r0;
        r19 = r19[r20];
        r19 = r17 - r19;
        r0 = r19;
        r10 = (int) r0;
        r0 = r21;
        r0 = r0.f781j;
        r19 = r0;
        r0 = r21;
        r0 = r0.f777f;
        r20 = r0;
        r19 = r19[r20];
        r19 = r18 - r19;
        r0 = r19;
        r11 = (int) r0;
        r0 = r21;
        r0 = r0.f792u;
        r19 = r0;
        r19 = r19.getLeft();
        r19 = r19 + r10;
        r0 = r21;
        r0 = r0.f792u;
        r20 = r0;
        r20 = r20.getTop();
        r20 = r20 + r11;
        r0 = r21;
        r1 = r19;
        r2 = r20;
        r0.m201b(r1, r2, r10, r11);
        r21.m193a(r22);
        goto L_0x002f;
    L_0x018e:
        r14 = android.support.v4.view.MotionEventCompat.getPointerCount(r22);
        r8 = 0;
    L_0x0193:
        if (r8 >= r14) goto L_0x01ce;
    L_0x0195:
        r0 = r22;
        r15 = android.support.v4.view.MotionEventCompat.getPointerId(r0, r8);
        r0 = r22;
        r17 = android.support.v4.view.MotionEventCompat.getX(r0, r8);
        r0 = r22;
        r18 = android.support.v4.view.MotionEventCompat.getY(r0, r8);
        r0 = r21;
        r0 = r0.f778g;
        r19 = r0;
        r19 = r19[r15];
        r5 = r17 - r19;
        r0 = r21;
        r0 = r0.f779h;
        r19 = r0;
        r19 = r19[r15];
        r6 = r18 - r19;
        r0 = r21;
        r0.m199b(r5, r6, r15);
        r0 = r21;
        r0 = r0.f775d;
        r19 = r0;
        r20 = 1;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x01d3;
    L_0x01ce:
        r21.m193a(r22);
        goto L_0x002f;
    L_0x01d3:
        r0 = r17;
        r0 = (int) r0;
        r19 = r0;
        r0 = r18;
        r0 = (int) r0;
        r20 = r0;
        r0 = r21;
        r1 = r19;
        r2 = r20;
        r16 = r0.findTopChildUnder(r1, r2);
        r0 = r21;
        r1 = r16;
        r19 = r0.m196a(r1, r5, r6);
        if (r19 == 0) goto L_0x01fb;
    L_0x01f1:
        r0 = r21;
        r1 = r16;
        r19 = r0.m204a(r1, r15);
        if (r19 != 0) goto L_0x01ce;
    L_0x01fb:
        r8 = r8 + 1;
        goto L_0x0193;
    L_0x01fe:
        r0 = r22;
        r15 = android.support.v4.view.MotionEventCompat.getPointerId(r0, r4);
        r0 = r21;
        r0 = r0.f775d;
        r19 = r0;
        r20 = 1;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0280;
    L_0x0212:
        r0 = r21;
        r0 = r0.f777f;
        r19 = r0;
        r0 = r19;
        if (r15 != r0) goto L_0x0280;
    L_0x021c:
        r13 = -1;
        r14 = android.support.v4.view.MotionEventCompat.getPointerCount(r22);
        r8 = 0;
    L_0x0222:
        if (r8 >= r14) goto L_0x0277;
    L_0x0224:
        r0 = r22;
        r9 = android.support.v4.view.MotionEventCompat.getPointerId(r0, r8);
        r0 = r21;
        r0 = r0.f777f;
        r19 = r0;
        r0 = r19;
        if (r9 != r0) goto L_0x0237;
    L_0x0234:
        r8 = r8 + 1;
        goto L_0x0222;
    L_0x0237:
        r0 = r22;
        r17 = android.support.v4.view.MotionEventCompat.getX(r0, r8);
        r0 = r22;
        r18 = android.support.v4.view.MotionEventCompat.getY(r0, r8);
        r0 = r17;
        r0 = (int) r0;
        r19 = r0;
        r0 = r18;
        r0 = (int) r0;
        r20 = r0;
        r0 = r21;
        r1 = r19;
        r2 = r20;
        r19 = r0.findTopChildUnder(r1, r2);
        r0 = r21;
        r0 = r0.f792u;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0234;
    L_0x0263:
        r0 = r21;
        r0 = r0.f792u;
        r19 = r0;
        r0 = r21;
        r1 = r19;
        r19 = r0.m204a(r1, r9);
        if (r19 == 0) goto L_0x0234;
    L_0x0273:
        r0 = r21;
        r13 = r0.f777f;
    L_0x0277:
        r19 = -1;
        r0 = r19;
        if (r13 != r0) goto L_0x0280;
    L_0x027d:
        r21.m198b();
    L_0x0280:
        r0 = r21;
        r0.m200b(r15);
        goto L_0x002f;
    L_0x0287:
        r0 = r21;
        r0 = r0.f775d;
        r19 = r0;
        r20 = 1;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0298;
    L_0x0295:
        r21.m198b();
    L_0x0298:
        r21.cancel();
        goto L_0x002f;
    L_0x029d:
        r0 = r21;
        r0 = r0.f775d;
        r19 = r0;
        r20 = 1;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x02b8;
    L_0x02ab:
        r19 = 0;
        r20 = 0;
        r0 = r21;
        r1 = r19;
        r2 = r20;
        r0.m191a(r1, r2);
    L_0x02b8:
        r21.cancel();
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.helper.ViewDragHelper.processTouchEvent(android.view.MotionEvent):void");
    }

    private void m199b(float dx, float dy, int pointerId) {
        int dragsStarted = 0;
        if (m194a(dx, dy, pointerId, 1)) {
            dragsStarted = 0 | 1;
        }
        if (m194a(dy, dx, pointerId, 4)) {
            dragsStarted |= 4;
        }
        if (m194a(dx, dy, pointerId, 2)) {
            dragsStarted |= 2;
        }
        if (m194a(dy, dx, pointerId, 8)) {
            dragsStarted |= 8;
        }
        if (dragsStarted != 0) {
            int[] iArr = this.f783l;
            iArr[pointerId] = iArr[pointerId] | dragsStarted;
            this.f773b.onEdgeDragStarted(dragsStarted, pointerId);
        }
    }

    private boolean m194a(float delta, float odelta, int pointerId, int edge) {
        float absDelta = Math.abs(delta);
        float absODelta = Math.abs(odelta);
        if ((this.f782k[pointerId] & edge) != edge || (this.f790s & edge) == 0 || (this.f784m[pointerId] & edge) == edge || (this.f783l[pointerId] & edge) == edge) {
            return false;
        }
        if (absDelta <= ((float) this.f776e) && absODelta <= ((float) this.f776e)) {
            return false;
        }
        if (absDelta < 0.5f * absODelta && this.f773b.onEdgeLock(edge)) {
            int[] iArr = this.f784m;
            iArr[pointerId] = iArr[pointerId] | edge;
            return false;
        } else if ((this.f783l[pointerId] & edge) != 0 || absDelta <= ((float) this.f776e)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean m196a(View child, float dx, float dy) {
        if (child == null) {
            return false;
        }
        boolean checkHorizontal;
        boolean checkVertical;
        if (this.f773b.getViewHorizontalDragRange(child) > 0) {
            checkHorizontal = true;
        } else {
            checkHorizontal = false;
        }
        if (this.f773b.getViewVerticalDragRange(child) > 0) {
            checkVertical = true;
        } else {
            checkVertical = false;
        }
        if (checkHorizontal && checkVertical) {
            if ((dx * dx) + (dy * dy) <= ((float) (this.f776e * this.f776e))) {
                return false;
            }
            return true;
        } else if (checkHorizontal) {
            if (Math.abs(dx) <= ((float) this.f776e)) {
                return false;
            }
            return true;
        } else if (!checkVertical) {
            return false;
        } else {
            if (Math.abs(dy) <= ((float) this.f776e)) {
                return false;
            }
            return true;
        }
    }

    public boolean checkTouchSlop(int directions) {
        int count = this.f778g.length;
        for (int i = 0; i < count; i++) {
            if (checkTouchSlop(directions, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTouchSlop(int directions, int pointerId) {
        if (!isPointerDown(pointerId)) {
            return false;
        }
        boolean checkHorizontal;
        boolean checkVertical;
        if ((directions & 1) == 1) {
            checkHorizontal = true;
        } else {
            checkHorizontal = false;
        }
        if ((directions & 2) == 2) {
            checkVertical = true;
        } else {
            checkVertical = false;
        }
        float dx = this.f780i[pointerId] - this.f778g[pointerId];
        float dy = this.f781j[pointerId] - this.f779h[pointerId];
        if (checkHorizontal && checkVertical) {
            if ((dx * dx) + (dy * dy) <= ((float) (this.f776e * this.f776e))) {
                return false;
            }
            return true;
        } else if (checkHorizontal) {
            if (Math.abs(dx) <= ((float) this.f776e)) {
                return false;
            }
            return true;
        } else if (!checkVertical) {
            return false;
        } else {
            if (Math.abs(dy) <= ((float) this.f776e)) {
                return false;
            }
            return true;
        }
    }

    public boolean isEdgeTouched(int edges) {
        int count = this.f782k.length;
        for (int i = 0; i < count; i++) {
            if (isEdgeTouched(edges, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEdgeTouched(int edges, int pointerId) {
        return isPointerDown(pointerId) && (this.f782k[pointerId] & edges) != 0;
    }

    private void m198b() {
        this.f786o.computeCurrentVelocity(PointerIconCompat.TYPE_DEFAULT, this.f787p);
        m191a(m186a(VelocityTrackerCompat.getXVelocity(this.f786o, this.f777f), this.f788q, this.f787p), m186a(VelocityTrackerCompat.getYVelocity(this.f786o, this.f777f), this.f788q, this.f787p));
    }

    private void m201b(int left, int top, int dx, int dy) {
        int clampedX = left;
        int clampedY = top;
        int oldLeft = this.f792u.getLeft();
        int oldTop = this.f792u.getTop();
        if (dx != 0) {
            clampedX = this.f773b.clampViewPositionHorizontal(this.f792u, left, dx);
            this.f792u.offsetLeftAndRight(clampedX - oldLeft);
        }
        if (dy != 0) {
            clampedY = this.f773b.clampViewPositionVertical(this.f792u, top, dy);
            this.f792u.offsetTopAndBottom(clampedY - oldTop);
        }
        if (dx != 0 || dy != 0) {
            this.f773b.onViewPositionChanged(this.f792u, clampedX, clampedY, clampedX - oldLeft, clampedY - oldTop);
        }
    }

    public boolean isCapturedViewUnder(int x, int y) {
        return isViewUnder(this.f792u, x, y);
    }

    public boolean isViewUnder(View view, int x, int y) {
        if (view != null && x >= view.getLeft() && x < view.getRight() && y >= view.getTop() && y < view.getBottom()) {
            return true;
        }
        return false;
    }

    public View findTopChildUnder(int x, int y) {
        for (int i = this.f774c.getChildCount() - 1; i >= 0; i--) {
            View child = this.f774c.getChildAt(this.f773b.getOrderedChildIndex(i));
            if (x >= child.getLeft() && x < child.getRight() && y >= child.getTop() && y < child.getBottom()) {
                return child;
            }
        }
        return null;
    }

    private int m187a(int x, int y) {
        int result = 0;
        if (x < this.f774c.getLeft() + this.f789r) {
            result = 1;
        }
        if (y < this.f774c.getTop() + this.f789r) {
            result = 4;
        }
        if (x > this.f774c.getRight() - this.f789r) {
            result = 2;
        }
        if (y > this.f774c.getBottom() - this.f789r) {
            return 8;
        }
        return result;
    }
}
