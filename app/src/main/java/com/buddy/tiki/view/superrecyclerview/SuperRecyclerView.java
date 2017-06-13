package com.buddy.tiki.view.superrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener;
import com.buddy.tiki.view.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener.DismissCallbacks;
import org.bytedeco.javacpp.postproc;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class SuperRecyclerView extends FrameLayout {
    private int f3256A;
    private int[] f3257B;
    protected int f3258a = 10;
    protected RecyclerView f3259b;
    protected ViewStub f3260c;
    protected ViewStub f3261d;
    protected ViewStub f3262e;
    protected View f3263f;
    protected View f3264g;
    protected View f3265h;
    protected TextView f3266i;
    protected boolean f3267j;
    protected int f3268k;
    protected int f3269l;
    protected int f3270m;
    protected int f3271n;
    protected int f3272o;
    protected int f3273p;
    protected int f3274q;
    protected int f3275r;
    protected LAYOUT_MANAGER_TYPE f3276s;
    protected OnScrollListener f3277t;
    protected OnScrollListener f3278u;
    protected OnMoreListener f3279v;
    protected boolean f3280w;
    protected GcSwipeRefreshLayout f3281x;
    protected int f3282y;
    private OnScrollListener f3283z;

    class C05351 extends OnScrollListener {
        final /* synthetic */ SuperRecyclerView f3250a;

        C05351(SuperRecyclerView this$0) {
            this.f3250a = this$0;
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            this.f3250a.m2047b();
            if (this.f3250a.f3278u != null) {
                this.f3250a.f3278u.onScrolled(recyclerView, dx, dy);
            }
            if (this.f3250a.f3283z != null) {
                this.f3250a.f3283z.onScrolled(recyclerView, dx, dy);
            }
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (this.f3250a.f3278u != null) {
                this.f3250a.f3278u.onScrollStateChanged(recyclerView, newState);
            }
            if (this.f3250a.f3283z != null) {
                this.f3250a.f3283z.onScrollStateChanged(recyclerView, newState);
            }
        }
    }

    class C05362 extends AdapterDataObserver {
        final /* synthetic */ SuperRecyclerView f3251a;

        C05362(SuperRecyclerView this$0) {
            this.f3251a = this$0;
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            m2040a();
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            m2040a();
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            m2040a();
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            m2040a();
        }

        public void onChanged() {
            super.onChanged();
            m2040a();
        }

        private void m2040a() {
            this.f3251a.f3260c.setVisibility(8);
            this.f3251a.f3261d.setVisibility(8);
            this.f3251a.f3280w = false;
            this.f3251a.f3281x.setRefreshing(false);
            if (this.f3251a.f3259b.getAdapter().getItemCount() == 0 && this.f3251a.f3274q != 0) {
                this.f3251a.f3262e.setVisibility(0);
            } else if (this.f3251a.f3274q != 0) {
                this.f3251a.f3262e.setVisibility(8);
            }
        }
    }

    static /* synthetic */ class C05384 {
        static final /* synthetic */ int[] f3254a = new int[LAYOUT_MANAGER_TYPE.values().length];

        static {
            try {
                f3254a[LAYOUT_MANAGER_TYPE.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3254a[LAYOUT_MANAGER_TYPE.GRID.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3254a[LAYOUT_MANAGER_TYPE.STAGGERED_GRID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    public SuperRecyclerView(Context context) {
        super(context);
        m2043a();
    }

    public SuperRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m2048a(attrs);
        m2043a();
    }

    public SuperRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        m2048a(attrs);
        m2043a();
    }

    public SwipeRefreshLayout getSwipeToRefresh() {
        return this.f3281x;
    }

    public RecyclerView getRecyclerView() {
        return this.f3259b;
    }

    protected void m2048a(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0376R.styleable.superrecyclerview);
        try {
            this.f3282y = a.getResourceId(10, C0376R.layout.superrecyclerview_layout_progress_recyclerview);
            this.f3267j = a.getBoolean(3, false);
            this.f3268k = (int) a.getDimension(4, -1.0f);
            this.f3269l = (int) a.getDimension(5, 0.0f);
            this.f3270m = (int) a.getDimension(6, 0.0f);
            this.f3271n = (int) a.getDimension(7, 0.0f);
            this.f3272o = (int) a.getDimension(8, 0.0f);
            this.f3273p = a.getInt(9, -1);
            this.f3274q = a.getResourceId(0, 0);
            this.f3275r = a.getResourceId(1, C0376R.layout.superrecyclerview_layout_more_progress);
            this.f3256A = a.getResourceId(2, C0376R.layout.superrecyclerview_layout_progress);
        } finally {
            a.recycle();
        }
    }

    private void m2043a() {
        if (!isInEditMode()) {
            View v = LayoutInflater.from(getContext()).inflate(this.f3282y, this);
            this.f3281x = (GcSwipeRefreshLayout) v.findViewById(C0376R.id.ptr_layout);
            this.f3281x.setColorSchemeResources(C0376R.color.colorPrimary);
            this.f3281x.setEnabled(false);
            this.f3260c = (ViewStub) v.findViewById(16908301);
            this.f3260c.setLayoutResource(this.f3256A);
            this.f3263f = this.f3260c.inflate();
            this.f3261d = (ViewStub) v.findViewById(C0376R.id.more_progress);
            this.f3261d.setLayoutResource(this.f3275r);
            if (this.f3275r != 0) {
                this.f3264g = this.f3261d.inflate();
            }
            this.f3261d.setVisibility(8);
            this.f3262e = (ViewStub) v.findViewById(C0376R.id.empty);
            this.f3262e.setLayoutResource(this.f3274q);
            if (this.f3274q != 0) {
                this.f3265h = this.f3262e.inflate();
                this.f3266i = (TextView) this.f3265h.findViewById(C0376R.id.none_item_tips);
                this.f3262e.setVisibility(8);
            }
            m2049a(v);
        }
    }

    protected void m2049a(View view) {
        View recyclerView = view.findViewById(16908298);
        if (recyclerView instanceof RecyclerView) {
            this.f3259b = (RecyclerView) recyclerView;
            if (this.f3259b != null) {
                this.f3259b.setClipToPadding(this.f3267j);
                this.f3277t = new C05351(this);
                this.f3259b.addOnScrollListener(this.f3277t);
                if (((float) this.f3268k) != -1.0f) {
                    this.f3259b.setPadding(this.f3268k, this.f3268k, this.f3268k, this.f3268k);
                } else {
                    this.f3259b.setPadding(this.f3271n, this.f3269l, this.f3272o, this.f3270m);
                }
                if (this.f3273p != -1) {
                    this.f3259b.setScrollBarStyle(this.f3273p);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("SuperRecyclerView works with a RecyclerView!");
    }

    private void m2047b() {
        LayoutManager layoutManager = this.f3259b.getLayoutManager();
        int lastVisibleItemPosition = m2041a(layoutManager);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if ((totalItemCount - lastVisibleItemPosition <= this.f3258a || (totalItemCount - lastVisibleItemPosition == 0 && totalItemCount > visibleItemCount)) && !this.f3280w) {
            this.f3280w = true;
            if (this.f3279v != null) {
                this.f3261d.setVisibility(0);
                this.f3279v.onMoreAsked(this.f3259b.getAdapter().getItemCount(), this.f3258a, lastVisibleItemPosition);
            }
        }
    }

    private int m2041a(LayoutManager layoutManager) {
        if (this.f3276s == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                this.f3276s = LAYOUT_MANAGER_TYPE.LINEAR;
            } else if (layoutManager instanceof GridLayoutManager) {
                this.f3276s = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                this.f3276s = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }
        switch (C05384.f3254a[this.f3276s.ordinal()]) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                return ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (this.f3257B == null) {
                    this.f3257B = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(this.f3257B);
                return m2042a(this.f3257B);
            default:
                return -1;
        }
    }

    private int m2042a(int[] lastPositions) {
        int max = postproc.PP_CPU_CAPS_MMX;
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private void m2044a(Adapter adapter, boolean compatibleWithPrevious, boolean removeAndRecycleExistingViews) {
        int i = 8;
        if (compatibleWithPrevious) {
            this.f3259b.swapAdapter(adapter, removeAndRecycleExistingViews);
        } else {
            this.f3259b.setAdapter(adapter);
        }
        this.f3260c.setVisibility(8);
        this.f3259b.setVisibility(0);
        this.f3281x.setRefreshing(false);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(new C05362(this));
        }
        if (this.f3274q != 0) {
            ViewStub viewStub = this.f3262e;
            if (adapter == null || adapter.getItemCount() <= 0 || this.f3274q == 0) {
                i = 0;
            }
            viewStub.setVisibility(i);
        }
    }

    public void setLayoutManager(LayoutManager manager) {
        this.f3259b.setLayoutManager(manager);
    }

    public void swapAdapter(Adapter adapter, boolean removeAndRecycleExistingViews) {
        m2044a(adapter, true, removeAndRecycleExistingViews);
    }

    public void setupSwipeToDismiss(final DismissCallbacks listener) {
        SwipeDismissRecyclerViewTouchListener touchListener = new SwipeDismissRecyclerViewTouchListener(this.f3259b, new DismissCallbacks(this) {
            final /* synthetic */ SuperRecyclerView f3253b;

            public boolean canDismiss(int position) {
                return listener.canDismiss(position);
            }

            public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
                listener.onDismiss(recyclerView, reverseSortedPositions);
            }
        });
        this.f3283z = touchListener.makeScrollListener();
        this.f3259b.setOnTouchListener(touchListener);
    }

    public void clear() {
        this.f3259b.setAdapter(null);
    }

    public void showProgress() {
        hideRecycler();
        if (this.f3274q != 0) {
            this.f3262e.setVisibility(4);
        }
        this.f3260c.setVisibility(0);
    }

    public void showRecycler() {
        hideProgress();
        this.f3259b.setVisibility(0);
    }

    public void showMoreProgress() {
        this.f3261d.setVisibility(0);
    }

    public void hideMoreProgress() {
        this.f3261d.setVisibility(8);
    }

    public void setRefreshListener(OnRefreshListener listener) {
        this.f3281x.setEnabled(true);
        this.f3281x.setOnRefreshListener(listener);
    }

    public void setRefreshingColorResources(@ColorRes int colRes1, @ColorRes int colRes2, @ColorRes int colRes3, @ColorRes int colRes4) {
        this.f3281x.setColorSchemeResources(colRes1, colRes2, colRes3, colRes4);
    }

    public void setRefreshingColor(int col1, int col2, int col3, int col4) {
        this.f3281x.setColorSchemeColors(col1, col2, col3, col4);
    }

    public void hideProgress() {
        this.f3260c.setVisibility(8);
    }

    public void hideRecycler() {
        this.f3259b.setVisibility(8);
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.f3278u = listener;
    }

    public void addOnItemTouchListener(OnItemTouchListener listener) {
        this.f3259b.addOnItemTouchListener(listener);
    }

    public void removeOnItemTouchListener(OnItemTouchListener listener) {
        this.f3259b.removeOnItemTouchListener(listener);
    }

    public Adapter getAdapter() {
        return this.f3259b.getAdapter();
    }

    public void setAdapter(Adapter adapter) {
        m2044a(adapter, false, true);
    }

    public void setupMoreListener(OnMoreListener onMoreListener, int max) {
        this.f3279v = onMoreListener;
        this.f3258a = max;
    }

    public void setOnMoreListener(OnMoreListener onMoreListener) {
        this.f3279v = onMoreListener;
    }

    public void setNumberBeforeMoreIsCalled(int max) {
        this.f3258a = max;
    }

    public boolean isLoadingMore() {
        return this.f3280w;
    }

    public void setLoadingMore(boolean isLoadingMore) {
        this.f3280w = isLoadingMore;
    }

    public void removeMoreListener() {
        this.f3279v = null;
    }

    public void setEmptyText(String text) {
        if (this.f3266i != null) {
            this.f3266i.setText(text);
        }
    }

    public void setEmptyText(int resId) {
        if (this.f3266i != null) {
            this.f3266i.setText(resId);
        }
    }

    public void setEmptyTextColor(int color) {
        if (this.f3266i != null) {
            this.f3266i.setTextColor(color);
        }
    }

    public void setOnTouchListener(OnTouchListener listener) {
        this.f3259b.setOnTouchListener(listener);
    }

    public void addItemDecoration(ItemDecoration itemDecoration) {
        this.f3259b.addItemDecoration(itemDecoration);
    }

    public void addItemDecoration(ItemDecoration itemDecoration, int index) {
        this.f3259b.addItemDecoration(itemDecoration, index);
    }

    public void removeItemDecoration(ItemDecoration itemDecoration) {
        this.f3259b.removeItemDecoration(itemDecoration);
    }

    public View getProgressView() {
        return this.f3263f;
    }

    public View getMoreProgressView() {
        return this.f3264g;
    }

    public View getEmptyView() {
        return this.f3265h;
    }

    public void smoothScrollBy(int dx, int dy) {
        this.f3259b.smoothScrollBy(dx, dy);
    }
}
