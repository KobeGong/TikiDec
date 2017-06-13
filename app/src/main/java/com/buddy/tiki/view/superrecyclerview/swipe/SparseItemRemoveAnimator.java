package com.buddy.tiki.view.superrecyclerview.swipe;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView.ViewHolder;

public class SparseItemRemoveAnimator extends DefaultItemAnimator {
    private boolean f3289a = false;

    public void setSkipNext(boolean skipNext) {
        this.f3289a = skipNext;
    }

    public boolean animateRemove(ViewHolder holder) {
        if (!this.f3289a) {
            return super.animateRemove(holder);
        }
        dispatchRemoveFinished(holder);
        this.f3289a = false;
        return false;
    }
}
