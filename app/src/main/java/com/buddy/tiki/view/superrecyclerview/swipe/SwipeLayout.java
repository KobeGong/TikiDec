package com.buddy.tiki.view.superrecyclerview.swipe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import com.buddy.tiki.C0376R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class SwipeLayout extends FrameLayout {
    private ViewDragHelper f3335a;
    private int f3336b;
    private DragEdge f3337c;
    private ShowMode f3338d;
    private float f3339e;
    private float f3340f;
    private List<SwipeListener> f3341g;
    private List<SwipeDenier> f3342h;
    private Map<View, ArrayList<OnRevealListener>> f3343i;
    private Map<View, Boolean> f3344j;
    private DoubleClickListener f3345k;
    private boolean f3346l;
    private int f3347m;
    private Callback f3348n;
    private List<OnLayout> f3349o;
    private boolean f3350p;
    private float f3351q;
    private float f3352r;
    private GestureDetector f3353s;

    public interface SwipeListener {
        void onClose(SwipeLayout swipeLayout);

        void onHandRelease(SwipeLayout swipeLayout, float f, float f2);

        void onOpen(SwipeLayout swipeLayout);

        void onStartClose(SwipeLayout swipeLayout);

        void onStartOpen(SwipeLayout swipeLayout);

        void onUpdate(SwipeLayout swipeLayout, int i, int i2);
    }

    public interface OnLayout {
        void onLayout(SwipeLayout swipeLayout);
    }

    class C05431 extends Callback {
        final /* synthetic */ SwipeLayout f3329a;

        C05431(SwipeLayout this$0) {
            this.f3329a = this$0;
        }

        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == this.f3329a.getSurfaceView()) {
                switch (C05442.f3330a[this.f3329a.f3337c.ordinal()]) {
                    case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                        return this.f3329a.getPaddingLeft();
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                        if (left < this.f3329a.getPaddingLeft()) {
                            return this.f3329a.getPaddingLeft();
                        }
                        if (left > this.f3329a.getPaddingLeft() + this.f3329a.f3336b) {
                            return this.f3329a.getPaddingLeft() + this.f3329a.f3336b;
                        }
                        return left;
                    case swscale.SWS_CS_FCC /*4*/:
                        if (left > this.f3329a.getPaddingLeft()) {
                            return this.f3329a.getPaddingLeft();
                        }
                        if (left < this.f3329a.getPaddingLeft() - this.f3329a.f3336b) {
                            return this.f3329a.getPaddingLeft() - this.f3329a.f3336b;
                        }
                        return left;
                    default:
                        return left;
                }
            } else if (child != this.f3329a.getBottomView()) {
                return left;
            } else {
                switch (C05442.f3330a[this.f3329a.f3337c.ordinal()]) {
                    case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                        return this.f3329a.getPaddingLeft();
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                        if (this.f3329a.f3338d != ShowMode.PullOut || left <= this.f3329a.getPaddingLeft()) {
                            return left;
                        }
                        return this.f3329a.getPaddingLeft();
                    case swscale.SWS_CS_FCC /*4*/:
                        if (this.f3329a.f3338d != ShowMode.PullOut || left >= this.f3329a.getMeasuredWidth() - this.f3329a.f3336b) {
                            return left;
                        }
                        return this.f3329a.getMeasuredWidth() - this.f3329a.f3336b;
                    default:
                        return left;
                }
            }
        }

        public int clampViewPositionVertical(View child, int top, int dy) {
            if (child == this.f3329a.getSurfaceView()) {
                switch (C05442.f3330a[this.f3329a.f3337c.ordinal()]) {
                    case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                        if (top < this.f3329a.getPaddingTop()) {
                            return this.f3329a.getPaddingTop();
                        }
                        if (top > this.f3329a.getPaddingTop() + this.f3329a.f3336b) {
                            return this.f3329a.getPaddingTop() + this.f3329a.f3336b;
                        }
                        return top;
                    case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                        if (top < this.f3329a.getPaddingTop() - this.f3329a.f3336b) {
                            return this.f3329a.getPaddingTop() - this.f3329a.f3336b;
                        }
                        if (top > this.f3329a.getPaddingTop()) {
                            return this.f3329a.getPaddingTop();
                        }
                        return top;
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                    case swscale.SWS_CS_FCC /*4*/:
                        return this.f3329a.getPaddingTop();
                    default:
                        return top;
                }
            }
            switch (C05442.f3330a[this.f3329a.f3337c.ordinal()]) {
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    if (this.f3329a.f3338d == ShowMode.PullOut) {
                        if (top > this.f3329a.getPaddingTop()) {
                            return this.f3329a.getPaddingTop();
                        }
                        return top;
                    } else if (this.f3329a.getSurfaceView().getTop() + dy < this.f3329a.getPaddingTop()) {
                        return this.f3329a.getPaddingTop();
                    } else {
                        if (this.f3329a.getSurfaceView().getTop() + dy > this.f3329a.getPaddingTop() + this.f3329a.f3336b) {
                            return this.f3329a.getPaddingTop() + this.f3329a.f3336b;
                        }
                        return top;
                    }
                case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                    if (this.f3329a.f3338d == ShowMode.PullOut) {
                        if (top < this.f3329a.getMeasuredHeight() - this.f3329a.f3336b) {
                            return this.f3329a.getMeasuredHeight() - this.f3329a.f3336b;
                        }
                        return top;
                    } else if (this.f3329a.getSurfaceView().getTop() + dy >= this.f3329a.getPaddingTop()) {
                        return this.f3329a.getPaddingTop();
                    } else {
                        if (this.f3329a.getSurfaceView().getTop() + dy <= this.f3329a.getPaddingTop() - this.f3329a.f3336b) {
                            return this.f3329a.getPaddingTop() - this.f3329a.f3336b;
                        }
                        return top;
                    }
                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                case swscale.SWS_CS_FCC /*4*/:
                    return this.f3329a.getPaddingTop();
                default:
                    return top;
            }
        }

        public boolean tryCaptureView(View child, int pointerId) {
            return child == this.f3329a.getSurfaceView() || child == this.f3329a.getBottomView();
        }

        public int getViewHorizontalDragRange(View child) {
            return this.f3329a.f3336b;
        }

        public int getViewVerticalDragRange(View child) {
            return this.f3329a.f3336b;
        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            for (SwipeListener l : this.f3329a.f3341g) {
                l.onHandRelease(this.f3329a, xvel, yvel);
            }
            if (releasedChild == this.f3329a.getSurfaceView()) {
                this.f3329a.m2067a(xvel, yvel);
            } else if (releasedChild == this.f3329a.getBottomView()) {
                if (this.f3329a.getShowMode() == ShowMode.PullOut) {
                    this.f3329a.m2073b(xvel, yvel);
                } else if (this.f3329a.getShowMode() == ShowMode.LayDown) {
                    this.f3329a.m2077c(xvel, yvel);
                }
            }
            this.f3329a.invalidate();
        }

        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            int evLeft = this.f3329a.getSurfaceView().getLeft();
            int evRight = this.f3329a.getSurfaceView().getRight();
            int evTop = this.f3329a.getSurfaceView().getTop();
            int evBottom = this.f3329a.getSurfaceView().getBottom();
            if (changedView == this.f3329a.getSurfaceView()) {
                if (this.f3329a.f3338d == ShowMode.PullOut) {
                    if (this.f3329a.f3337c == DragEdge.Left || this.f3329a.f3337c == DragEdge.Right) {
                        this.f3329a.getBottomView().offsetLeftAndRight(dx);
                    } else {
                        this.f3329a.getBottomView().offsetTopAndBottom(dy);
                    }
                }
            } else if (changedView == this.f3329a.getBottomView()) {
                if (this.f3329a.f3338d == ShowMode.PullOut) {
                    this.f3329a.getSurfaceView().offsetLeftAndRight(dx);
                    this.f3329a.getSurfaceView().offsetTopAndBottom(dy);
                } else {
                    Rect rect = this.f3329a.m2061a(this.f3329a.f3337c);
                    this.f3329a.getBottomView().layout(rect.left, rect.top, rect.right, rect.bottom);
                    int newLeft = this.f3329a.getSurfaceView().getLeft() + dx;
                    int newTop = this.f3329a.getSurfaceView().getTop() + dy;
                    if (this.f3329a.f3337c == DragEdge.Left && newLeft < this.f3329a.getPaddingLeft()) {
                        newLeft = this.f3329a.getPaddingLeft();
                    } else if (this.f3329a.f3337c == DragEdge.Right && newLeft > this.f3329a.getPaddingLeft()) {
                        newLeft = this.f3329a.getPaddingLeft();
                    } else if (this.f3329a.f3337c == DragEdge.Top && newTop < this.f3329a.getPaddingTop()) {
                        newTop = this.f3329a.getPaddingTop();
                    } else if (this.f3329a.f3337c == DragEdge.Bottom && newTop > this.f3329a.getPaddingTop()) {
                        newTop = this.f3329a.getPaddingTop();
                    }
                    this.f3329a.getSurfaceView().layout(newLeft, newTop, this.f3329a.getMeasuredWidth() + newLeft, this.f3329a.getMeasuredHeight() + newTop);
                }
            }
            this.f3329a.m2088b(evLeft, evTop, evRight, evBottom);
            this.f3329a.m2084a(evLeft, evTop, dx, dy);
            this.f3329a.invalidate();
        }
    }

    static /* synthetic */ class C05442 {
        static final /* synthetic */ int[] f3330a = new int[DragEdge.values().length];

        static {
            try {
                f3330a[DragEdge.Top.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3330a[DragEdge.Bottom.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3330a[DragEdge.Left.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3330a[DragEdge.Right.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public interface DoubleClickListener {
        void onDoubleClick(SwipeLayout swipeLayout, boolean z);
    }

    public enum DragEdge {
        Left,
        Right,
        Top,
        Bottom
    }

    public interface OnRevealListener {
        void onReveal(View view, DragEdge dragEdge, float f, int i);
    }

    public enum ShowMode {
        LayDown,
        PullOut
    }

    public enum Status {
        Middle,
        Open,
        Close
    }

    public interface SwipeDenier {
        boolean shouldDenySwipe(MotionEvent motionEvent);
    }

    class SwipeDetector extends SimpleOnGestureListener {
        final /* synthetic */ SwipeLayout f3334a;

        SwipeDetector(SwipeLayout this$0) {
            this.f3334a = this$0;
        }

        public boolean onDown(MotionEvent e) {
            return true;
        }

        public boolean onSingleTapUp(MotionEvent e) {
            if (this.f3334a.f3345k == null) {
                this.f3334a.m2068a(e);
            }
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (this.f3334a.f3345k != null) {
                this.f3334a.m2068a(e);
            }
            return true;
        }

        public void onLongPress(MotionEvent e) {
            this.f3334a.performLongClick();
        }

        public boolean onDoubleTap(MotionEvent e) {
            if (this.f3334a.f3345k != null) {
                View target;
                View bottom = this.f3334a.getBottomView();
                View surface = this.f3334a.getSurfaceView();
                if (e.getX() <= ((float) bottom.getLeft()) || e.getX() >= ((float) bottom.getRight()) || e.getY() <= ((float) bottom.getTop()) || e.getY() >= ((float) bottom.getBottom())) {
                    target = surface;
                } else {
                    target = bottom;
                }
                this.f3334a.f3345k.onDoubleClick(this.f3334a, target == surface);
            }
            return true;
        }
    }

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f3336b = 0;
        this.f3341g = new ArrayList();
        this.f3342h = new ArrayList();
        this.f3343i = new HashMap();
        this.f3344j = new HashMap();
        this.f3346l = true;
        this.f3347m = 0;
        this.f3348n = new C05431(this);
        this.f3350p = false;
        this.f3351q = -1.0f;
        this.f3352r = -1.0f;
        this.f3353s = new GestureDetector(getContext(), new SwipeDetector(this));
        this.f3335a = ViewDragHelper.create(this, this.f3348n);
        TypedArray a = context.obtainStyledAttributes(attrs, C0376R.styleable.SwipeLayout);
        int ordinal = a.getInt(0, DragEdge.Right.ordinal());
        this.f3339e = a.getDimension(1, 0.0f);
        this.f3340f = a.getDimension(2, 0.0f);
        this.f3337c = DragEdge.values()[ordinal];
        this.f3338d = ShowMode.values()[a.getInt(3, ShowMode.PullOut.ordinal())];
        a.recycle();
    }

    public void addSwipeListener(SwipeListener l) {
        this.f3341g.add(l);
    }

    public void removeSwipeListener(SwipeListener l) {
        this.f3341g.remove(l);
    }

    public void addSwipeDenier(SwipeDenier denier) {
        this.f3342h.add(denier);
    }

    public void removeSwipeDenier(SwipeDenier denier) {
        this.f3342h.remove(denier);
    }

    public void removeAllSwipeDeniers() {
        this.f3342h.clear();
    }

    public void addRevealListener(int childId, OnRevealListener l) {
        View child = findViewById(childId);
        if (child == null) {
            throw new IllegalArgumentException("Child does not belong to SwipeListener.");
        }
        if (!this.f3344j.containsKey(child)) {
            this.f3344j.put(child, Boolean.valueOf(false));
        }
        if (this.f3343i.get(child) == null) {
            this.f3343i.put(child, new ArrayList());
        }
        ((ArrayList) this.f3343i.get(child)).add(l);
    }

    public void addRevealListener(int[] childIds, OnRevealListener l) {
        for (int i : childIds) {
            addRevealListener(i, l);
        }
    }

    public void removeRevealListener(int childId, OnRevealListener l) {
        View child = findViewById(childId);
        if (child != null) {
            this.f3344j.remove(child);
            if (this.f3343i.containsKey(child)) {
                ((ArrayList) this.f3343i.get(child)).remove(l);
            }
        }
    }

    public void removeAllRevealListeners(int childId) {
        View child = findViewById(childId);
        if (child != null) {
            this.f3343i.remove(child);
            this.f3344j.remove(child);
        }
    }

    protected boolean m2086a(View child, Rect relativePosition, DragEdge edge, int surfaceLeft, int surfaceTop, int surfaceRight, int surfaceBottom) {
        if (((Boolean) this.f3344j.get(child)).booleanValue()) {
            return false;
        }
        int childLeft = relativePosition.left;
        int childRight = relativePosition.right;
        int childTop = relativePosition.top;
        int childBottom = relativePosition.bottom;
        if (getShowMode() == ShowMode.LayDown) {
            if ((edge != DragEdge.Right || surfaceRight > childLeft) && ((edge != DragEdge.Left || surfaceLeft < childRight) && ((edge != DragEdge.Top || surfaceTop < childBottom) && (edge != DragEdge.Bottom || surfaceBottom > childTop)))) {
                return false;
            }
            return true;
        } else if (getShowMode() != ShowMode.PullOut) {
            return false;
        } else {
            if ((edge != DragEdge.Right || childRight > getWidth()) && ((edge != DragEdge.Left || childLeft < getPaddingLeft()) && ((edge != DragEdge.Top || childTop < getPaddingTop()) && (edge != DragEdge.Bottom || childBottom > getHeight())))) {
                return false;
            }
            return true;
        }
    }

    protected boolean m2089b(View child, Rect relativePosition, DragEdge availableEdge, int surfaceLeft, int surfaceTop, int surfaceRight, int surfaceBottom) {
        int childLeft = relativePosition.left;
        int childRight = relativePosition.right;
        int childTop = relativePosition.top;
        int childBottom = relativePosition.bottom;
        if (getShowMode() != ShowMode.LayDown) {
            if (getShowMode() == ShowMode.PullOut) {
                switch (C05442.f3330a[availableEdge.ordinal()]) {
                    case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                        if (childTop < getPaddingTop() && childBottom >= getPaddingTop()) {
                            return true;
                        }
                    case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                        if (childTop < getHeight() && childTop >= getPaddingTop()) {
                            return true;
                        }
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                        if (childRight >= getPaddingLeft() && childLeft < getPaddingLeft()) {
                            return true;
                        }
                    case swscale.SWS_CS_FCC /*4*/:
                        if (childLeft <= getWidth() && childRight > getWidth()) {
                            return true;
                        }
                    default:
                        break;
                }
            }
        }
        switch (C05442.f3330a[availableEdge.ordinal()]) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                if (surfaceTop >= childTop && surfaceTop < childBottom) {
                    return true;
                }
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                if (surfaceBottom > childTop && surfaceBottom <= childBottom) {
                    return true;
                }
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                if (surfaceLeft < childRight && surfaceLeft >= childLeft) {
                    return true;
                }
            case swscale.SWS_CS_FCC /*4*/:
                if (surfaceRight > childLeft && surfaceRight <= childRight) {
                    return true;
                }
        }
        return false;
    }

    protected Rect m2082a(View child) {
        View t = child;
        Rect r = new Rect(t.getLeft(), t.getTop(), 0, 0);
        while (t.getParent() != null && t != getRootView()) {
            t = (View) t.getParent();
            if (t == this) {
                break;
            }
            r.left += t.getLeft();
            r.top += t.getTop();
        }
        r.right = r.left + child.getMeasuredWidth();
        r.bottom = r.top + child.getMeasuredHeight();
        return r;
    }

    protected void m2084a(int surfaceLeft, int surfaceTop, int dx, int dy) {
        DragEdge edge = getDragEdge();
        boolean open = true;
        if (edge == DragEdge.Left) {
            if (dx < 0) {
                open = false;
            }
        } else if (edge == DragEdge.Right) {
            if (dx > 0) {
                open = false;
            }
        } else if (edge == DragEdge.Top) {
            if (dy < 0) {
                open = false;
            }
        } else if (edge == DragEdge.Bottom && dy > 0) {
            open = false;
        }
        m2085a(surfaceLeft, surfaceTop, open);
    }

    protected void m2085a(int surfaceLeft, int surfaceTop, boolean open) {
        m2076c();
        Status status = getOpenStatus();
        if (!this.f3341g.isEmpty()) {
            this.f3347m++;
            for (SwipeListener l : this.f3341g) {
                if (this.f3347m == 1) {
                    if (open) {
                        l.onStartOpen(this);
                    } else {
                        l.onStartClose(this);
                    }
                }
                l.onUpdate(this, surfaceLeft - getPaddingLeft(), surfaceTop - getPaddingTop());
            }
            if (status == Status.Close) {
                for (SwipeListener l2 : this.f3341g) {
                    l2.onClose(this);
                }
                this.f3347m = 0;
            }
            if (status == Status.Open) {
                getBottomView().setEnabled(true);
                for (SwipeListener l22 : this.f3341g) {
                    l22.onOpen(this);
                }
                this.f3347m = 0;
            }
        }
    }

    private void m2076c() {
        Status status = getOpenStatus();
        ViewGroup bottom = getBottomView();
        if (status == Status.Close) {
            if (bottom.getVisibility() != 4) {
                bottom.setVisibility(4);
            }
        } else if (bottom.getVisibility() != 0) {
            bottom.setVisibility(0);
        }
    }

    protected void m2088b(int surfaceLeft, int surfaceTop, int surfaceRight, int surfaceBottom) {
        if (!this.f3343i.isEmpty()) {
            for (Entry<View, ArrayList<OnRevealListener>> entry : this.f3343i.entrySet()) {
                Iterator it;
                View child = (View) entry.getKey();
                Rect rect = m2082a(child);
                if (m2089b(child, rect, this.f3337c, surfaceLeft, surfaceTop, surfaceRight, surfaceBottom)) {
                    this.f3344j.put(child, Boolean.valueOf(false));
                    int distance = 0;
                    float fraction = 0.0f;
                    if (getShowMode() != ShowMode.LayDown) {
                        if (getShowMode() == ShowMode.PullOut) {
                            switch (C05442.f3330a[this.f3337c.ordinal()]) {
                                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                                    distance = rect.bottom - getPaddingTop();
                                    fraction = ((float) distance) / ((float) child.getHeight());
                                    break;
                                case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                                    distance = rect.top - getHeight();
                                    fraction = ((float) distance) / ((float) child.getHeight());
                                    break;
                                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                                    distance = rect.right - getPaddingLeft();
                                    fraction = ((float) distance) / ((float) child.getWidth());
                                    break;
                                case swscale.SWS_CS_FCC /*4*/:
                                    distance = rect.left - getWidth();
                                    fraction = ((float) distance) / ((float) child.getWidth());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    switch (C05442.f3330a[this.f3337c.ordinal()]) {
                        case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                            distance = rect.top - surfaceTop;
                            fraction = ((float) distance) / ((float) child.getHeight());
                            break;
                        case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                            distance = rect.bottom - surfaceBottom;
                            fraction = ((float) distance) / ((float) child.getHeight());
                            break;
                        case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                            distance = rect.left - surfaceLeft;
                            fraction = ((float) distance) / ((float) child.getWidth());
                            break;
                        case swscale.SWS_CS_FCC /*4*/:
                            distance = rect.right - surfaceRight;
                            fraction = ((float) distance) / ((float) child.getWidth());
                            break;
                    }
                    it = ((ArrayList) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        ((OnRevealListener) it.next()).onReveal(child, this.f3337c, Math.abs(fraction), distance);
                        if (Math.abs(fraction) == 1.0f) {
                            this.f3344j.put(child, Boolean.valueOf(true));
                        }
                    }
                }
                if (m2086a(child, rect, this.f3337c, surfaceLeft, surfaceTop, surfaceRight, surfaceBottom)) {
                    this.f3344j.put(child, Boolean.valueOf(true));
                    it = ((ArrayList) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        OnRevealListener l = (OnRevealListener) it.next();
                        if (this.f3337c == DragEdge.Left || this.f3337c == DragEdge.Right) {
                            l.onReveal(child, this.f3337c, 1.0f, child.getWidth());
                        } else {
                            l.onReveal(child, this.f3337c, 1.0f, child.getHeight());
                        }
                    }
                }
            }
        }
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.f3335a.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void addOnLayoutListener(OnLayout l) {
        if (this.f3349o == null) {
            this.f3349o = new ArrayList();
        }
        this.f3349o.add(l);
    }

    public void removeOnLayoutListener(OnLayout l) {
        if (this.f3349o != null) {
            this.f3349o.remove(l);
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() != 2) {
            throw new IllegalStateException("You need 2  views in SwipeLayout");
        } else if ((getChildAt(0) instanceof ViewGroup) && (getChildAt(1) instanceof ViewGroup)) {
            if (this.f3338d == ShowMode.PullOut) {
                m2083a();
            } else if (this.f3338d == ShowMode.LayDown) {
                m2087b();
            }
            m2076c();
            if (this.f3349o != null) {
                for (int i = 0; i < this.f3349o.size(); i++) {
                    ((OnLayout) this.f3349o.get(i)).onLayout(this);
                }
            }
        } else {
            throw new IllegalArgumentException("The 2 children in SwipeLayout must be an instance of ViewGroup");
        }
    }

    void m2083a() {
        Rect rect = m2064a(false);
        getSurfaceView().layout(rect.left, rect.top, rect.right, rect.bottom);
        rect = m2062a(ShowMode.PullOut, rect);
        getBottomView().layout(rect.left, rect.top, rect.right, rect.bottom);
        bringChildToFront(getSurfaceView());
    }

    void m2087b() {
        Rect rect = m2064a(false);
        getSurfaceView().layout(rect.left, rect.top, rect.right, rect.bottom);
        rect = m2062a(ShowMode.LayDown, rect);
        getBottomView().layout(rect.left, rect.top, rect.right, rect.bottom);
        bringChildToFront(getSurfaceView());
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.f3337c == DragEdge.Left || this.f3337c == DragEdge.Right) {
            this.f3336b = getBottomView().getMeasuredWidth() - m2060a(this.f3339e);
        } else {
            this.f3336b = getBottomView().getMeasuredHeight() - m2060a(this.f3340f);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean z = true;
        if (!isEnabled() || !m2080d()) {
            return true;
        }
        if (!isSwipeEnabled()) {
            return false;
        }
        for (SwipeDenier denier : this.f3342h) {
            if (denier != null && denier.shouldDenySwipe(ev)) {
                return false;
            }
        }
        switch (MotionEventCompat.getActionMasked(ev)) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                Status status = getOpenStatus();
                if (status != Status.Close) {
                    if (status == Status.Open) {
                        if (m2065a(getBottomView(), ev) == null) {
                            z = false;
                        }
                        this.f3350p = z;
                        break;
                    }
                }
                if (m2065a(getSurfaceView(), ev) == null) {
                    z = false;
                }
                this.f3350p = z;
                break;
                break;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                this.f3350p = false;
                break;
        }
        if (this.f3350p) {
            return false;
        }
        return this.f3335a.shouldInterceptTouchEvent(ev);
    }

    private View m2065a(ViewGroup v, MotionEvent event) {
        if (v == null) {
            return null;
        }
        if (v.onTouchEvent(event)) {
            return v;
        }
        for (int i = v.getChildCount() - 1; i >= 0; i--) {
            View child = v.getChildAt(i);
            if (child instanceof ViewGroup) {
                View grandChild = m2065a((ViewGroup) child, event);
                if (grandChild != null) {
                    return grandChild;
                }
            } else if (m2071a(v.getChildAt(i), event)) {
                return v.getChildAt(i);
            }
        }
        return null;
    }

    private boolean m2071a(View v, MotionEvent event) {
        if (v == null) {
            return false;
        }
        int[] loc = new int[2];
        v.getLocationOnScreen(loc);
        int left = loc[0];
        int top = loc[1];
        if (event.getRawX() <= ((float) left) || event.getRawX() >= ((float) (v.getWidth() + left)) || event.getRawY() <= ((float) top) || event.getRawY() >= ((float) (v.getHeight() + top))) {
            return false;
        }
        return v.onTouchEvent(event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!m2080d() || !isEnabled()) {
            return true;
        }
        if (!isSwipeEnabled()) {
            return super.onTouchEvent(event);
        }
        int action = MotionEventCompat.getActionMasked(event);
        ViewParent parent = getParent();
        this.f3353s.onTouchEvent(event);
        Status status = getOpenStatus();
        ViewGroup touching = null;
        if (status == Status.Close) {
            touching = getSurfaceView();
        } else if (status == Status.Open) {
            touching = getBottomView();
        }
        switch (action) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                this.f3335a.processTouchEvent(event);
                parent.requestDisallowInterceptTouchEvent(true);
                this.f3351q = event.getRawX();
                this.f3352r = event.getRawY();
                if (touching != null) {
                    touching.setPressed(true);
                }
                return true;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                this.f3351q = -1.0f;
                this.f3352r = -1.0f;
                if (touching != null) {
                    touching.setPressed(false);
                    break;
                }
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                if (this.f3351q != -1.0f && this.f3352r != -1.0f) {
                    boolean suitable;
                    float distanceX = event.getRawX() - this.f3351q;
                    float distanceY = event.getRawY() - this.f3352r;
                    float angle = (float) Math.toDegrees(Math.atan((double) Math.abs(distanceY / distanceX)));
                    boolean doNothing = false;
                    if (this.f3337c == DragEdge.Right) {
                        suitable = (status == Status.Open && distanceX > 0.0f) || (status == Status.Close && distanceX < 0.0f);
                        suitable = suitable || status == Status.Middle;
                        if (angle > 30.0f || !suitable) {
                            doNothing = true;
                        }
                    }
                    if (this.f3337c == DragEdge.Left) {
                        suitable = (status == Status.Open && distanceX < 0.0f) || (status == Status.Close && distanceX > 0.0f);
                        suitable = suitable || status == Status.Middle;
                        if (angle > 30.0f || !suitable) {
                            doNothing = true;
                        }
                    }
                    if (this.f3337c == DragEdge.Top) {
                        suitable = (status == Status.Open && distanceY < 0.0f) || (status == Status.Close && distanceY > 0.0f);
                        suitable = suitable || status == Status.Middle;
                        if (angle < 60.0f || !suitable) {
                            doNothing = true;
                        }
                    }
                    if (this.f3337c == DragEdge.Bottom) {
                        suitable = (status == Status.Open && distanceY > 0.0f) || (status == Status.Close && distanceY < 0.0f);
                        suitable = suitable || status == Status.Middle;
                        if (angle < 60.0f || !suitable) {
                            doNothing = true;
                        }
                    }
                    if (!doNothing) {
                        if (touching != null) {
                            touching.setPressed(false);
                        }
                        parent.requestDisallowInterceptTouchEvent(true);
                        this.f3335a.processTouchEvent(event);
                        break;
                    }
                    parent.requestDisallowInterceptTouchEvent(false);
                    return false;
                }
                event.setAction(0);
                this.f3335a.processTouchEvent(event);
                parent.requestDisallowInterceptTouchEvent(true);
                this.f3351q = event.getRawX();
                this.f3352r = event.getRawY();
                return true;
                break;
        }
        parent.requestDisallowInterceptTouchEvent(true);
        this.f3335a.processTouchEvent(event);
        return true;
    }

    private boolean m2080d() {
        AdapterView adapterView = getAdapterView();
        if (adapterView == null) {
            return true;
        }
        Adapter adapter = adapterView.getAdapter();
        if (adapter == null) {
            return true;
        }
        int p = adapterView.getPositionForView(this);
        if (adapter instanceof BaseAdapter) {
            return ((BaseAdapter) adapter).isEnabled(p);
        }
        if (adapter instanceof ListAdapter) {
            return ((ListAdapter) adapter).isEnabled(p);
        }
        return true;
    }

    public boolean isSwipeEnabled() {
        return this.f3346l;
    }

    public void setSwipeEnabled(boolean enabled) {
        this.f3346l = enabled;
    }

    private AdapterView getAdapterView() {
        for (ViewParent t = getParent(); t != null; t = t.getParent()) {
            if (t instanceof AdapterView) {
                return (AdapterView) t;
            }
        }
        return null;
    }

    private void m2068a(MotionEvent e) {
        ViewParent t = getParent();
        while (t != null) {
            if (t instanceof AdapterView) {
                AdapterView view = (AdapterView) t;
                int p = view.getPositionForView(this);
                if (p != -1 && view.performItemClick(view.getChildAt(p - view.getFirstVisiblePosition()), p, view.getAdapter().getItemId(p))) {
                    return;
                }
            } else if ((t instanceof View) && ((View) t).performClick()) {
                return;
            }
            t = t.getParent();
        }
    }

    public DragEdge getDragEdge() {
        return this.f3337c;
    }

    public void setDragEdge(DragEdge dragEdge) {
        this.f3337c = dragEdge;
        requestLayout();
    }

    public int getDragDistance() {
        return this.f3336b;
    }

    public void setDragDistance(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("Drag distance can not be < 0");
        }
        this.f3336b = m2060a((float) max);
        requestLayout();
    }

    public ShowMode getShowMode() {
        return this.f3338d;
    }

    public void setShowMode(ShowMode mode) {
        this.f3338d = mode;
        requestLayout();
    }

    public ViewGroup getSurfaceView() {
        return (ViewGroup) getChildAt(1);
    }

    public ViewGroup getBottomView() {
        return (ViewGroup) getChildAt(0);
    }

    public Status getOpenStatus() {
        int surfaceLeft = getSurfaceView().getLeft();
        int surfaceTop = getSurfaceView().getTop();
        if (surfaceLeft == getPaddingLeft() && surfaceTop == getPaddingTop()) {
            return Status.Close;
        }
        if (surfaceLeft == getPaddingLeft() - this.f3336b || surfaceLeft == getPaddingLeft() + this.f3336b || surfaceTop == getPaddingTop() - this.f3336b || surfaceTop == getPaddingTop() + this.f3336b) {
            return Status.Open;
        }
        return Status.Middle;
    }

    private void m2067a(float xvel, float yvel) {
        if (xvel == 0.0f && getOpenStatus() == Status.Middle) {
            close();
        }
        if (this.f3337c == DragEdge.Left || this.f3337c == DragEdge.Right) {
            if (xvel > 0.0f) {
                if (this.f3337c == DragEdge.Left) {
                    open();
                } else {
                    close();
                }
            }
            if (xvel >= 0.0f) {
                return;
            }
            if (this.f3337c == DragEdge.Left) {
                close();
                return;
            } else {
                open();
                return;
            }
        }
        if (yvel > 0.0f) {
            if (this.f3337c == DragEdge.Top) {
                open();
            } else {
                close();
            }
        }
        if (yvel >= 0.0f) {
            return;
        }
        if (this.f3337c == DragEdge.Top) {
            close();
        } else {
            open();
        }
    }

    private void m2073b(float xvel, float yvel) {
        if (xvel == 0.0f && getOpenStatus() == Status.Middle) {
            close();
        }
        if (this.f3337c == DragEdge.Left || this.f3337c == DragEdge.Right) {
            if (xvel > 0.0f) {
                if (this.f3337c == DragEdge.Left) {
                    open();
                } else {
                    close();
                }
            }
            if (xvel >= 0.0f) {
                return;
            }
            if (this.f3337c == DragEdge.Left) {
                close();
                return;
            } else {
                open();
                return;
            }
        }
        if (yvel > 0.0f) {
            if (this.f3337c == DragEdge.Top) {
                open();
            } else {
                close();
            }
        }
        if (yvel >= 0.0f) {
            return;
        }
        if (this.f3337c == DragEdge.Top) {
            close();
        } else {
            open();
        }
    }

    private void m2077c(float xvel, float yvel) {
        if (xvel == 0.0f && getOpenStatus() == Status.Middle) {
            close();
        }
        int l = getPaddingLeft();
        int t = getPaddingTop();
        if (xvel < 0.0f && this.f3337c == DragEdge.Right) {
            l -= this.f3336b;
        }
        if (xvel > 0.0f && this.f3337c == DragEdge.Left) {
            l += this.f3336b;
        }
        if (yvel > 0.0f && this.f3337c == DragEdge.Top) {
            t += this.f3336b;
        }
        if (yvel < 0.0f && this.f3337c == DragEdge.Bottom) {
            t -= this.f3336b;
        }
        this.f3335a.smoothSlideViewTo(getSurfaceView(), l, t);
        invalidate();
    }

    public void open() {
        open(true, true);
    }

    public void open(boolean smooth) {
        open(smooth, true);
    }

    public void open(boolean smooth, boolean notify) {
        ViewGroup surface = getSurfaceView();
        ViewGroup bottom = getBottomView();
        Rect rect = m2064a(true);
        if (smooth) {
            this.f3335a.smoothSlideViewTo(getSurfaceView(), rect.left, rect.top);
        } else {
            int dx = rect.left - surface.getLeft();
            int dy = rect.top - surface.getTop();
            surface.layout(rect.left, rect.top, rect.right, rect.bottom);
            if (getShowMode() == ShowMode.PullOut) {
                Rect bRect = m2062a(ShowMode.PullOut, rect);
                bottom.layout(bRect.left, bRect.top, bRect.right, bRect.bottom);
            }
            if (notify) {
                m2088b(rect.left, rect.top, rect.right, rect.bottom);
                m2084a(rect.left, rect.top, dx, dy);
            } else {
                m2076c();
            }
        }
        invalidate();
    }

    public void close() {
        close(true, true);
    }

    public void close(boolean smooth) {
        close(smooth, true);
    }

    public void close(boolean smooth, boolean notify) {
        ViewGroup surface = getSurfaceView();
        if (smooth) {
            this.f3335a.smoothSlideViewTo(getSurfaceView(), getPaddingLeft(), getPaddingTop());
        } else {
            Rect rect = m2064a(false);
            int dx = rect.left - surface.getLeft();
            int dy = rect.top - surface.getTop();
            surface.layout(rect.left, rect.top, rect.right, rect.bottom);
            if (notify) {
                m2088b(rect.left, rect.top, rect.right, rect.bottom);
                m2084a(rect.left, rect.top, dx, dy);
            } else {
                m2076c();
            }
        }
        invalidate();
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean smooth) {
        if (getOpenStatus() == Status.Open) {
            close(smooth);
        } else if (getOpenStatus() == Status.Close) {
            open(smooth);
        }
    }

    private Rect m2064a(boolean open) {
        int l = getPaddingLeft();
        int t = getPaddingTop();
        if (open) {
            if (this.f3337c == DragEdge.Left) {
                l = getPaddingLeft() + this.f3336b;
            } else if (this.f3337c == DragEdge.Right) {
                l = getPaddingLeft() - this.f3336b;
            } else {
                t = this.f3337c == DragEdge.Top ? getPaddingTop() + this.f3336b : getPaddingTop() - this.f3336b;
            }
        }
        return new Rect(l, t, getMeasuredWidth() + l, getMeasuredHeight() + t);
    }

    private Rect m2062a(ShowMode mode, Rect surfaceArea) {
        Rect rect = surfaceArea;
        int bl = rect.left;
        int bt = rect.top;
        int br = rect.right;
        int bb = rect.bottom;
        if (mode == ShowMode.PullOut) {
            if (this.f3337c == DragEdge.Left) {
                bl = rect.left - this.f3336b;
            } else if (this.f3337c == DragEdge.Right) {
                bl = rect.right;
            } else {
                bt = this.f3337c == DragEdge.Top ? rect.top - this.f3336b : rect.bottom;
            }
            if (this.f3337c == DragEdge.Left || this.f3337c == DragEdge.Right) {
                bb = rect.bottom;
                br = bl + getBottomView().getMeasuredWidth();
            } else {
                bb = bt + getBottomView().getMeasuredHeight();
                br = rect.right;
            }
        } else if (mode == ShowMode.LayDown) {
            if (this.f3337c == DragEdge.Left) {
                br = bl + this.f3336b;
            } else if (this.f3337c == DragEdge.Right) {
                bl = br - this.f3336b;
            } else if (this.f3337c == DragEdge.Top) {
                bb = bt + this.f3336b;
            } else {
                bt = bb - this.f3336b;
            }
        }
        return new Rect(bl, bt, br, bb);
    }

    private Rect m2061a(DragEdge dragEdge) {
        int br;
        int bb;
        int bl = getPaddingLeft();
        int bt = getPaddingTop();
        if (dragEdge == DragEdge.Right) {
            bl = getMeasuredWidth() - this.f3336b;
        } else if (dragEdge == DragEdge.Bottom) {
            bt = getMeasuredHeight() - this.f3336b;
        }
        if (dragEdge == DragEdge.Left || dragEdge == DragEdge.Right) {
            br = bl + this.f3336b;
            bb = bt + getMeasuredHeight();
        } else {
            br = bl + getMeasuredWidth();
            bb = bt + this.f3336b;
        }
        return new Rect(bl, bt, br, bb);
    }

    public void setOnDoubleClickListener(DoubleClickListener doubleClickListener) {
        this.f3345k = doubleClickListener;
    }

    private int m2060a(float dp) {
        return (int) ((getContext().getResources().getDisplayMetrics().density * dp) + 0.5f);
    }
}
