package com.buddy.tiki.view.superrecyclerview;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class ItemTouchListenerAdapter extends SimpleOnGestureListener implements OnItemTouchListener {
    private RecyclerViewOnItemClickListener f3215a;
    private RecyclerView f3216b;
    private GestureDetector f3217c;

    public interface RecyclerViewOnItemClickListener {
        void onItemClick(RecyclerView recyclerView, View view, int i);

        void onItemLongClick(RecyclerView recyclerView, View view, int i);
    }

    public ItemTouchListenerAdapter(RecyclerView recyclerView, RecyclerViewOnItemClickListener listener) {
        if (recyclerView == null || listener == null) {
            throw new IllegalArgumentException("RecyclerView and Listener arguments can not be null");
        }
        this.f3216b = recyclerView;
        this.f3215a = listener;
        this.f3217c = new GestureDetector(recyclerView.getContext(), this);
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.f3217c.onTouchEvent(motionEvent);
        return false;
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }

    public void onShowPress(MotionEvent e) {
        View view = m2029a(e);
        if (view != null) {
            view.setPressed(true);
        }
    }

    public boolean onSingleTapUp(MotionEvent e) {
        View view = m2029a(e);
        if (view == null) {
            return false;
        }
        view.setPressed(false);
        this.f3215a.onItemClick(this.f3216b, view, this.f3216b.getChildPosition(view));
        return true;
    }

    public void onLongPress(MotionEvent e) {
        View view = m2029a(e);
        if (view != null) {
            this.f3215a.onItemLongClick(this.f3216b, view, this.f3216b.getChildPosition(view));
            view.setPressed(false);
        }
    }

    @Nullable
    private View m2029a(MotionEvent e) {
        return this.f3216b.findChildViewUnder(e.getX(), e.getY());
    }

    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
