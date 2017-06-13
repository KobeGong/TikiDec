package com.buddy.tiki.view.superrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
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
import android.widget.TextView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.TikiNestedScrollView;
import com.buddy.tiki.view.superrecyclerview.SuperRecyclerView.LAYOUT_MANAGER_TYPE;
import com.buddy.tiki.view.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener;
import com.buddy.tiki.view.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener.DismissCallbacks;
import org.bytedeco.javacpp.postproc;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class SuperNestedRecyclerView extends TikiNestedScrollView {
    private int[] f3223A;
    protected int f3224a = 10;
    protected RecyclerView f3225b;
    protected ViewStub f3226c;
    protected ViewStub f3227d;
    protected ViewStub f3228e;
    protected View f3229f;
    protected View f3230g;
    protected View f3231h;
    protected TextView f3232i;
    protected boolean f3233j;
    protected int f3234k;
    protected int f3235l;
    protected int f3236m;
    protected int f3237n;
    protected int f3238o;
    protected int f3239p;
    protected int f3240q;
    protected int f3241r;
    protected LAYOUT_MANAGER_TYPE f3242s;
    protected OnScrollListener f3243t;
    protected OnScrollListener f3244u;
    protected OnMoreListener f3245v;
    protected boolean f3246w;
    protected int f3247x;
    private OnScrollListener f3248y;
    private int f3249z;

    class C05311 extends OnScrollListener {
        final /* synthetic */ SuperNestedRecyclerView f3218a;

        C05311(SuperNestedRecyclerView this$0) {
            this.f3218a = this$0;
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            this.f3218a.m2037b();
            if (this.f3218a.f3244u != null) {
                this.f3218a.f3244u.onScrolled(recyclerView, dx, dy);
            }
            if (this.f3218a.f3248y != null) {
                this.f3218a.f3248y.onScrolled(recyclerView, dx, dy);
            }
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (this.f3218a.f3244u != null) {
                this.f3218a.f3244u.onScrollStateChanged(recyclerView, newState);
            }
            if (this.f3218a.f3248y != null) {
                this.f3218a.f3248y.onScrollStateChanged(recyclerView, newState);
            }
        }
    }

    class C05322 extends AdapterDataObserver {
        final /* synthetic */ SuperNestedRecyclerView f3219a;

        C05322(SuperNestedRecyclerView this$0) {
            this.f3219a = this$0;
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            m2030a();
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            m2030a();
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            m2030a();
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            m2030a();
        }

        public void onChanged() {
            super.onChanged();
            m2030a();
        }

        private void m2030a() {
            this.f3219a.f3226c.setVisibility(8);
            this.f3219a.f3227d.setVisibility(8);
            this.f3219a.f3246w = false;
            if (this.f3219a.f3225b.getAdapter().getItemCount() == 0 && this.f3219a.f3240q != 0) {
                this.f3219a.f3228e.setVisibility(0);
            } else if (this.f3219a.f3240q != 0) {
                this.f3219a.f3228e.setVisibility(8);
            }
        }
    }

    static /* synthetic */ class C05344 {
        static final /* synthetic */ int[] f3222a = new int[LAYOUT_MANAGER_TYPE.values().length];

        static {
            try {
                f3222a[LAYOUT_MANAGER_TYPE.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3222a[LAYOUT_MANAGER_TYPE.GRID.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3222a[LAYOUT_MANAGER_TYPE.STAGGERED_GRID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public SuperNestedRecyclerView(Context context) {
        super(context);
        m2033a();
    }

    public SuperNestedRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m2038a(attrs);
        m2033a();
    }

    public SuperNestedRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        m2038a(attrs);
        m2033a();
    }

    public RecyclerView getRecyclerView() {
        return this.f3225b;
    }

    protected void m2038a(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0376R.styleable.superrecyclerview);
        try {
            this.f3247x = a.getResourceId(10, C0376R.layout.superrecyclerview_layout_nestedscroll_recyclewview);
            this.f3233j = a.getBoolean(3, false);
            this.f3234k = (int) a.getDimension(4, -1.0f);
            this.f3235l = (int) a.getDimension(5, 0.0f);
            this.f3236m = (int) a.getDimension(6, 0.0f);
            this.f3237n = (int) a.getDimension(7, 0.0f);
            this.f3238o = (int) a.getDimension(8, 0.0f);
            this.f3239p = a.getInt(9, -1);
            this.f3240q = a.getResourceId(0, 0);
            this.f3241r = a.getResourceId(1, C0376R.layout.superrecyclerview_layout_more_progress);
            this.f3249z = a.getResourceId(2, C0376R.layout.superrecyclerview_layout_progress);
        } finally {
            a.recycle();
        }
    }

    private void m2033a() {
        if (!isInEditMode()) {
            setFillViewport(true);
            View v = LayoutInflater.from(getContext()).inflate(this.f3247x, this);
            this.f3226c = (ViewStub) v.findViewById(16908301);
            this.f3226c.setLayoutResource(this.f3249z);
            this.f3229f = this.f3226c.inflate();
            this.f3227d = (ViewStub) v.findViewById(C0376R.id.more_progress);
            this.f3227d.setLayoutResource(this.f3241r);
            if (this.f3241r != 0) {
                this.f3230g = this.f3227d.inflate();
            }
            this.f3227d.setVisibility(8);
            this.f3228e = (ViewStub) v.findViewById(C0376R.id.empty);
            this.f3228e.setLayoutResource(this.f3240q);
            if (this.f3240q != 0) {
                this.f3231h = this.f3228e.inflate();
                this.f3232i = (TextView) this.f3231h.findViewById(C0376R.id.none_item_tips);
                this.f3228e.setVisibility(8);
            }
            m2039a(v);
        }
    }

    protected void m2039a(View view) {
        View recyclerView = view.findViewById(16908298);
        if (recyclerView instanceof RecyclerView) {
            this.f3225b = (RecyclerView) recyclerView;
            if (this.f3225b != null) {
                this.f3225b.setClipToPadding(this.f3233j);
                this.f3225b.setNestedScrollingEnabled(true);
                this.f3243t = new C05311(this);
                this.f3225b.addOnScrollListener(this.f3243t);
                if (((float) this.f3234k) != -1.0f) {
                    this.f3225b.setPadding(this.f3234k, this.f3234k, this.f3234k, this.f3234k);
                } else {
                    this.f3225b.setPadding(this.f3237n, this.f3235l, this.f3238o, this.f3236m);
                }
                if (this.f3239p != -1) {
                    this.f3225b.setScrollBarStyle(this.f3239p);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("SuperRecyclerView works with a RecyclerView!");
    }

    private void m2037b() {
        LayoutManager layoutManager = this.f3225b.getLayoutManager();
        int lastVisibleItemPosition = m2031a(layoutManager);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if ((totalItemCount - lastVisibleItemPosition <= this.f3224a || (totalItemCount - lastVisibleItemPosition == 0 && totalItemCount > visibleItemCount)) && !this.f3246w) {
            this.f3246w = true;
            if (this.f3245v != null) {
                this.f3227d.setVisibility(0);
                this.f3245v.onMoreAsked(this.f3225b.getAdapter().getItemCount(), this.f3224a, lastVisibleItemPosition);
            }
        }
    }

    private int m2031a(LayoutManager layoutManager) {
        if (this.f3242s == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                this.f3242s = LAYOUT_MANAGER_TYPE.LINEAR;
            } else if (layoutManager instanceof GridLayoutManager) {
                this.f3242s = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                this.f3242s = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }
        switch (C05344.f3222a[this.f3242s.ordinal()]) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                return ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (this.f3223A == null) {
                    this.f3223A = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(this.f3223A);
                return m2032a(this.f3223A);
            default:
                return -1;
        }
    }

    private int m2032a(int[] lastPositions) {
        int max = postproc.PP_CPU_CAPS_MMX;
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private void m2034a(Adapter adapter, boolean compatibleWithPrevious, boolean removeAndRecycleExistingViews) {
        int i = 8;
        if (compatibleWithPrevious) {
            this.f3225b.swapAdapter(adapter, removeAndRecycleExistingViews);
        } else {
            this.f3225b.setAdapter(adapter);
        }
        this.f3226c.setVisibility(8);
        this.f3225b.setVisibility(0);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(new C05322(this));
        }
        if (this.f3240q != 0) {
            ViewStub viewStub = this.f3228e;
            if (adapter == null || adapter.getItemCount() <= 0 || this.f3240q == 0) {
                i = 0;
            }
            viewStub.setVisibility(i);
        }
    }

    public void setLayoutManager(LayoutManager manager) {
        this.f3225b.setLayoutManager(manager);
    }

    public void swapAdapter(Adapter adapter, boolean removeAndRecycleExistingViews) {
        m2034a(adapter, true, removeAndRecycleExistingViews);
    }

    public void setupSwipeToDismiss(final DismissCallbacks listener) {
        SwipeDismissRecyclerViewTouchListener touchListener = new SwipeDismissRecyclerViewTouchListener(this.f3225b, new DismissCallbacks(this) {
            final /* synthetic */ SuperNestedRecyclerView f3221b;

            public boolean canDismiss(int position) {
                return listener.canDismiss(position);
            }

            public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
                listener.onDismiss(recyclerView, reverseSortedPositions);
            }
        });
        this.f3248y = touchListener.makeScrollListener();
        this.f3225b.setOnTouchListener(touchListener);
    }

    public void clear() {
        this.f3225b.setAdapter(null);
    }

    public void showProgress() {
        hideRecycler();
        if (this.f3240q != 0) {
            this.f3228e.setVisibility(4);
        }
        this.f3226c.setVisibility(0);
    }

    public void showRecycler() {
        hideProgress();
        this.f3225b.setVisibility(0);
    }

    public void showMoreProgress() {
        this.f3227d.setVisibility(0);
    }

    public void hideMoreProgress() {
        this.f3227d.setVisibility(8);
    }

    public void hideProgress() {
        this.f3226c.setVisibility(8);
    }

    public void hideRecycler() {
        this.f3225b.setVisibility(8);
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.f3244u = listener;
    }

    public void addOnItemTouchListener(OnItemTouchListener listener) {
        this.f3225b.addOnItemTouchListener(listener);
    }

    public void removeOnItemTouchListener(OnItemTouchListener listener) {
        this.f3225b.removeOnItemTouchListener(listener);
    }

    public Adapter getAdapter() {
        return this.f3225b.getAdapter();
    }

    public void setAdapter(Adapter adapter) {
        m2034a(adapter, false, true);
    }

    public void setupMoreListener(OnMoreListener onMoreListener, int max) {
        this.f3245v = onMoreListener;
        this.f3224a = max;
    }

    public void setOnMoreListener(OnMoreListener onMoreListener) {
        this.f3245v = onMoreListener;
    }

    public void setNumberBeforeMoreIsCalled(int max) {
        this.f3224a = max;
    }

    public boolean isLoadingMore() {
        return this.f3246w;
    }

    public void setLoadingMore(boolean isLoadingMore) {
        this.f3246w = isLoadingMore;
    }

    public void removeMoreListener() {
        this.f3245v = null;
    }

    public void setEmptyText(String text) {
        if (this.f3232i != null) {
            this.f3232i.setText(text);
        }
    }

    public void setEmptyText(int resId) {
        if (this.f3232i != null) {
            this.f3232i.setText(resId);
        }
    }

    public void setEmptyTextColor(int color) {
        if (this.f3232i != null) {
            this.f3232i.setTextColor(color);
        }
    }

    public void setOnTouchListener(OnTouchListener listener) {
        this.f3225b.setOnTouchListener(listener);
    }

    public void addItemDecoration(ItemDecoration itemDecoration) {
        this.f3225b.addItemDecoration(itemDecoration);
    }

    public void addItemDecoration(ItemDecoration itemDecoration, int index) {
        this.f3225b.addItemDecoration(itemDecoration, index);
    }

    public void removeItemDecoration(ItemDecoration itemDecoration) {
        this.f3225b.removeItemDecoration(itemDecoration);
    }

    @Nullable
    public View getProgressView() {
        return this.f3229f;
    }

    @Nullable
    public View getMoreProgressView() {
        return this.f3230g;
    }

    @Nullable
    public View getEmptyView() {
        return this.f3231h;
    }
}
