package com.buddy.tiki.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.buddy.tiki.C0376R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PageGridView extends RecyclerView {
    int f2747a;
    int f2748b;
    long f2749c;
    int f2750d;
    boolean f2751e;
    int f2752f;
    int f2753g;
    int f2754h;
    private int f2755i;
    private int f2756j;
    private int f2757k;
    private int f2758l;
    private int f2759m;
    private boolean f2760n;
    private PageIndicator f2761o;
    private boolean f2762p;
    private List<OnPageChangeListener> f2763q;
    private OnItemClickListener f2764r;
    private Drawable f2765s;
    private Semaphore f2766t;

    public interface PagingAdapter<T> {
        List<T> getData();

        T getEmpty();
    }

    public static class DividerGridItemDecoration extends ItemDecoration {
        private static final int[] f2743a = new int[]{16843284};
        private final int f2744b;
        private Drawable f2745c;

        public DividerGridItemDecoration(Drawable divider, int pageSize) {
            this.f2745c = divider;
            this.f2744b = pageSize;
        }

        public void onDraw(Canvas c, RecyclerView parent, State state) {
            m1736a(c, parent);
            m1738b(c, parent);
        }

        private int m1735a(RecyclerView parent) {
            LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                return ((GridLayoutManager) layoutManager).getSpanCount();
            }
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            }
            return -1;
        }

        private void m1736a(Canvas c, RecyclerView parent) {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                int top = child.getBottom() + params.bottomMargin;
                this.f2745c.setBounds(child.getLeft() - params.leftMargin, top, (child.getRight() + params.rightMargin) + this.f2745c.getIntrinsicWidth(), top + this.f2745c.getIntrinsicHeight());
                this.f2745c.draw(c);
            }
        }

        private void m1738b(Canvas c, RecyclerView parent) {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                int left = child.getRight() + params.rightMargin;
                int right = left + this.f2745c.getIntrinsicWidth();
                this.f2745c.setBounds(left, child.getTop() - params.topMargin, right, child.getBottom() + params.bottomMargin);
                this.f2745c.draw(c);
            }
        }

        private boolean m1737a(RecyclerView parent, int pos, int spanCount, int childCount) {
            if ((parent.getLayoutManager() instanceof GridLayoutManager) && (pos + 1) % spanCount == 0) {
                return true;
            }
            return false;
        }

        private boolean m1739b(RecyclerView parent, int pos, int spanCount, int childCount) {
            if (!(parent.getLayoutManager() instanceof GridLayoutManager) || this.f2744b - (pos % this.f2744b) > spanCount) {
                return false;
            }
            return true;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int spanCount = m1735a(parent);
            int childCount = parent.getAdapter().getItemCount();
            int itemPosition = ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
            boolean isLastRow = m1737a(parent, itemPosition, spanCount, childCount);
            boolean isLastColumn = m1739b(parent, itemPosition, spanCount, childCount);
            if (isLastRow && isLastColumn) {
                outRect.set(this.f2745c.getIntrinsicWidth(), 0, this.f2745c.getIntrinsicWidth(), 0);
            } else if (isLastRow) {
                outRect.set(this.f2745c.getIntrinsicWidth(), 0, 0, 0);
            } else if (isLastColumn) {
                outRect.set(this.f2745c.getIntrinsicWidth(), this.f2745c.getIntrinsicHeight(), this.f2745c.getIntrinsicWidth(), this.f2745c.getIntrinsicHeight());
            } else {
                outRect.set(this.f2745c.getIntrinsicWidth(), this.f2745c.getIntrinsicHeight(), 0, this.f2745c.getIntrinsicHeight());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PageGridView pageGridView, int i);
    }

    public interface OnPageChangeListener {
        void onPageChanged(int i);
    }

    public interface PageIndicator {
        void initIndicatorItems(int i);

        void onPageSelected(int i);

        void onPageUnSelected(int i);
    }

    public class PagingScrollListener extends OnScrollListener {
        final /* synthetic */ PageGridView f2746a;

        public PagingScrollListener(PageGridView this$0) {
            this.f2746a = this$0;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == 0) {
                int percent = this.f2746a.f2750d / this.f2746a.getWidth();
                if (this.f2746a.f2750d % this.f2746a.getWidth() > this.f2746a.getWidth() / 2) {
                    percent++;
                }
                this.f2746a.f2752f = this.f2746a.getWidth() * percent;
                this.f2746a.f2751e = true;
                this.f2746a.f2753g = percent;
                if (this.f2746a.f2761o != null) {
                    this.f2746a.f2761o.onPageUnSelected(this.f2746a.f2754h);
                    this.f2746a.f2761o.onPageSelected(this.f2746a.f2753g);
                }
                if (this.f2746a.f2763q != null) {
                    for (OnPageChangeListener listener : this.f2746a.f2763q) {
                        listener.onPageChanged(this.f2746a.f2753g);
                    }
                }
                recyclerView.smoothScrollBy(this.f2746a.f2752f - this.f2746a.f2750d, 0);
            } else if (newState == 2) {
                this.f2746a.f2751e = false;
                this.f2746a.f2754h = this.f2746a.f2753g;
            }
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            PageGridView pageGridView = this.f2746a;
            pageGridView.f2750d += dx;
        }
    }

    public PageGridView(Context context) {
        this(context, null);
    }

    public PageGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageGridView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2750d = 0;
        this.f2751e = false;
        this.f2752f = 0;
        this.f2753g = 0;
        this.f2754h = 0;
        this.f2755i = 0;
        this.f2756j = 0;
        this.f2757k = 0;
        this.f2758l = 0;
        this.f2759m = -1;
        this.f2760n = false;
        this.f2762p = false;
        this.f2766t = new Semaphore(1);
        TypedArray array = context.obtainStyledAttributes(attrs, C0376R.styleable.PageGridView);
        this.f2755i = array.getInteger(0, 0);
        this.f2756j = array.getInteger(1, 0);
        this.f2765s = array.getDrawable(2);
        if (this.f2755i < 0 || this.f2756j < 0) {
            throw new RuntimeException("\u884c\u6570\u6216\u5217\u6570\u4e0d\u80fd\u4e3a\u8d1f\u6570");
        } else if (this.f2755i == 0 && this.f2756j == 0) {
            throw new RuntimeException("\u884c\u6570\u548c\u5217\u6570\u4e0d\u80fd\u90fd\u4e3a0");
        } else {
            LayoutManager layoutManager;
            if (this.f2755i > 0) {
                if (this.f2756j > 0) {
                    this.f2760n = true;
                    addOnScrollListener(new PagingScrollListener(this));
                }
                layoutManager = new GridLayoutManager(getContext(), this.f2755i, 0, false);
            } else {
                layoutManager = new GridLayoutManager(getContext(), this.f2756j, 1, false);
            }
            array.recycle();
            layoutManager.setAutoMeasureEnabled(true);
            setLayoutManager(layoutManager);
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.f2759m = getWidth();
    }

    public void setAdapter(Adapter adapter) {
        if (this.f2760n) {
            if (adapter instanceof PagingAdapter) {
                PagingAdapter pagingAdapter = (PagingAdapter) adapter;
                List data = pagingAdapter.getData();
                List formatData = new ArrayList();
                this.f2758l = this.f2755i * this.f2756j;
                this.f2757k = data.size() / this.f2758l;
                if (data.size() % this.f2758l != 0) {
                    this.f2757k++;
                }
                for (int p = 0; p < this.f2757k; p++) {
                    for (int c = 0; c < this.f2756j; c++) {
                        for (int r = 0; r < this.f2755i; r++) {
                            int index = ((this.f2756j * r) + c) + (this.f2758l * p);
                            if (index > data.size() - 1) {
                                formatData.add(pagingAdapter.getEmpty());
                            } else {
                                formatData.add(data.get(index));
                            }
                        }
                    }
                }
                data.clear();
                data.addAll(formatData);
            } else {
                throw new RuntimeException("adapter must be PagingAdapter");
            }
        }
        super.setAdapter(adapter);
        if (this.f2761o != null && this.f2762p) {
            this.f2761o.initIndicatorItems(this.f2757k);
            this.f2761o.onPageSelected(0);
            this.f2762p = false;
        }
        if (this.f2763q != null) {
            for (OnPageChangeListener listener : this.f2763q) {
                listener.onPageChanged(0);
            }
        }
        if (this.f2765s != null) {
            addItemDecoration(new DividerGridItemDecoration(this.f2765s, this.f2758l));
        }
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.f2764r != null) {
            switch (ev.getAction()) {
                case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                    try {
                        this.f2766t.acquire();
                        this.f2747a = (int) ev.getX();
                        this.f2748b = (int) ev.getY();
                        this.f2749c = System.currentTimeMillis();
                        this.f2766t.release();
                        break;
                    } catch (InterruptedException e) {
                        break;
                    }
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    try {
                        this.f2766t.acquire();
                        int my = (int) Math.abs(ev.getY() - ((float) this.f2748b));
                        long time = System.currentTimeMillis() - this.f2749c;
                        if (((int) Math.abs(ev.getX() - ((float) this.f2747a))) <= 15 && my <= 15 && time < 200) {
                            int position = m1740a((int) ev.getRawX(), (int) ev.getRawY());
                            if (position != -1) {
                                this.f2764r.onItemClick(this, position);
                            }
                        }
                        this.f2766t.release();
                        break;
                    } catch (InterruptedException e2) {
                        break;
                    }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private int m1740a(int x, int y) {
        int position = -1;
        y -= getResources().getDimensionPixelOffset(C0376R.dimen.indicator_height);
        Rect rect = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).getGlobalVisibleRect(rect);
            if (rect.contains(x, y)) {
                position = i;
                break;
            }
        }
        if (this.f2755i > 0) {
            return position + getChildLayoutPosition(getLayoutManager().getChildAt(0));
        }
        return position;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.f2764r = onItemClickListener;
    }

    public void setPageIndicator(PageIndicator pageIndicator) {
        this.f2761o = pageIndicator;
        this.f2762p = true;
        if (getAdapter() != null && this.f2760n) {
            pageIndicator.initIndicatorItems(this.f2757k);
            pageIndicator.onPageSelected(this.f2753g);
            this.f2762p = false;
        }
    }

    public void addOnPageChangeListener(OnPageChangeListener listener) {
        if (this.f2763q == null) {
            this.f2763q = new ArrayList();
        }
        this.f2763q.add(listener);
    }

    public void removeOnPageChangeListener(OnPageChangeListener listener) {
        if (this.f2763q != null) {
            this.f2763q.remove(listener);
        }
    }

    public int getPageSize() {
        return this.f2757k;
    }
}
