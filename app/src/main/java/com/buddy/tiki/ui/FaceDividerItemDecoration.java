package com.buddy.tiki.ui;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class FaceDividerItemDecoration extends ItemDecoration {
    private final int f1108a;
    private final int f1109b;
    private final boolean f1110c;

    public FaceDividerItemDecoration(int hSpacing, int vSpacing, boolean includeEdge) {
        this.f1108a = hSpacing;
        this.f1109b = vSpacing;
        this.f1110c = includeEdge;
    }

    public void onDraw(Canvas c, RecyclerView parent, State state) {
        super.onDraw(c, parent, state);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        int childCount = parent.getAdapter().getItemCount();
        int itemPosition = ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
            m425a(outRect, itemPosition, itemPosition % spanCount, spanCount);
        } else if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            m425a(outRect, itemPosition, ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex(), ((StaggeredGridLayoutManager) parent.getLayoutManager()).getSpanCount());
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            outRect.left = this.f1108a;
            outRect.right = this.f1108a;
            if (this.f1110c) {
                if (itemPosition == 0) {
                    outRect.top = this.f1109b;
                }
                outRect.bottom = this.f1109b;
            } else if (itemPosition > 0) {
                outRect.top = this.f1109b;
            }
        }
    }

    private void m425a(Rect outRect, int position, int column, int spanCount) {
        if (this.f1110c) {
            outRect.left = (this.f1108a * (spanCount - column)) / spanCount;
            outRect.right = (this.f1108a * (column + 1)) / spanCount;
            if (position < spanCount) {
                outRect.top = this.f1109b;
            }
            outRect.bottom = this.f1109b;
            return;
        }
        outRect.left = (this.f1108a * column) / spanCount;
        outRect.right = (this.f1108a * ((spanCount - 1) - column)) / spanCount;
        if (position >= spanCount) {
            outRect.top = this.f1109b;
        }
    }
}
