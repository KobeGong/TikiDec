package com.buddy.tiki.view.superrecyclerview;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public abstract class GcRecyclerViewAdapter<VH extends ViewHolder> extends Adapter<VH> {
    protected View f3212a;

    protected static class HeadViewHolder extends ViewHolder {
        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    public GcRecyclerViewAdapter() {
        setHasStableIds(true);
    }

    public View getMyHeadView() {
        return this.f3212a;
    }

    public void setMyHeadView(View headView) {
        this.f3212a = headView;
    }
}
