package com.buddy.tiki.view.superrecyclerview.swipe;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.superrecyclerview.swipe.SwipeItemManagerInterface.Mode;
import com.buddy.tiki.view.superrecyclerview.swipe.SwipeLayout.OnLayout;
import com.buddy.tiki.view.superrecyclerview.swipe.SwipeLayout.SwipeListener;
import java.util.List;

public abstract class BaseSwipeAdapter<VH extends BaseSwipeableViewHolder> extends Adapter<VH> implements SwipeItemManagerInterface {
    protected SwipeItemManagerImpl f3288a = new SwipeItemManagerImpl(this);

    public static class BaseSwipeableViewHolder extends ViewHolder {
        public SwipeLayout f3284a = null;
        public OnLayout f3285b = null;
        public SwipeListener f3286c = null;
        public int f3287d = -1;

        public BaseSwipeableViewHolder(View itemView) {
            super(itemView);
            this.f3284a = (SwipeLayout) itemView.findViewById(C0376R.id.recyclerview_swipe);
        }
    }

    public void onBindViewHolder(VH holder, int position) {
        this.f3288a.updateConvertView(holder, position);
    }

    public void openItem(int position) {
        this.f3288a.openItem(position);
    }

    public void closeItem(int position) {
        this.f3288a.closeItem(position);
    }

    public void closeAllExcept(SwipeLayout layout) {
        this.f3288a.closeAllExcept(layout);
    }

    public List<Integer> getOpenItems() {
        return this.f3288a.getOpenItems();
    }

    public List<SwipeLayout> getOpenLayouts() {
        return this.f3288a.getOpenLayouts();
    }

    public void removeShownLayouts(SwipeLayout layout) {
        this.f3288a.removeShownLayouts(layout);
    }

    public boolean isOpen(int position) {
        return this.f3288a.isOpen(position);
    }

    public Mode getMode() {
        return this.f3288a.getMode();
    }

    public void setMode(Mode mode) {
        this.f3288a.setMode(mode);
    }
}
