package com.buddy.tiki.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import org.bytedeco.javacpp.postproc;

public class NestedLinearLayoutManager extends LinearLayoutManager {
    private int[] f2742a;

    public NestedLinearLayoutManager(Context context) {
        super(context);
        this.f2742a = new int[2];
    }

    public NestedLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.f2742a = new int[2];
    }

    public void onMeasure(Recycler recycler, State state, int widthSpec, int heightSpec) {
        int widthMode = MeasureSpec.getMode(widthSpec);
        int heightMode = MeasureSpec.getMode(heightSpec);
        int widthSize = MeasureSpec.getSize(widthSpec);
        int heightSize = MeasureSpec.getSize(heightSpec);
        int width = 0;
        int height = 0;
        for (int i = 0; i < getItemCount(); i++) {
            m1734a(recycler, i, MeasureSpec.makeMeasureSpec(i, 0), MeasureSpec.makeMeasureSpec(i, 0), this.f2742a);
            if (getOrientation() == 0) {
                width += this.f2742a[0];
                if (i == 0) {
                    height = this.f2742a[1];
                }
            } else {
                height += this.f2742a[1];
                if (i == 0) {
                    width = this.f2742a[0];
                }
            }
        }
        switch (widthMode) {
            case postproc.PP_CPU_CAPS_3DNOW /*1073741824*/:
                width = widthSize;
                break;
        }
        switch (heightMode) {
            case postproc.PP_CPU_CAPS_3DNOW /*1073741824*/:
                height = heightSize;
                break;
        }
        setMeasuredDimension(width, height);
    }

    private void m1734a(Recycler recycler, int position, int widthSpec, int heightSpec, int[] measuredDimension) {
        try {
            View view = recycler.getViewForPosition(0);
            if (view != null) {
                LayoutParams p = (LayoutParams) view.getLayoutParams();
                view.measure(ViewGroup.getChildMeasureSpec(widthSpec, getPaddingLeft() + getPaddingRight(), p.width), ViewGroup.getChildMeasureSpec(heightSpec, getPaddingTop() + getPaddingBottom(), p.height));
                measuredDimension[0] = (view.getMeasuredWidth() + p.leftMargin) + p.rightMargin;
                measuredDimension[1] = (view.getMeasuredHeight() + p.bottomMargin) + p.topMargin;
                recycler.recycleView(view);
            }
        } catch (Exception e) {
        }
    }
}
