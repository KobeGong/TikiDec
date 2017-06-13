package com.buddy.tiki.view.superrecyclerview.swipe;

import android.support.v7.widget.RecyclerView.Adapter;
import com.buddy.tiki.view.superrecyclerview.swipe.BaseSwipeAdapter.BaseSwipeableViewHolder;
import com.buddy.tiki.view.superrecyclerview.swipe.SwipeItemManagerInterface.Mode;
import com.buddy.tiki.view.superrecyclerview.swipe.SwipeLayout.OnLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SwipeItemManagerImpl implements SwipeItemManagerInterface {
    public final int f3322a = -1;
    protected int f3323b = -1;
    protected Set<Integer> f3324c = new HashSet();
    protected Set<SwipeLayout> f3325d = new HashSet();
    protected Adapter f3326e;
    private Mode f3327f = Mode.Single;

    private class OnLayoutListener implements OnLayout {
        final /* synthetic */ SwipeItemManagerImpl f3318a;
        private int f3319b;

        OnLayoutListener(SwipeItemManagerImpl swipeItemManagerImpl, int position) {
            this.f3318a = swipeItemManagerImpl;
            this.f3319b = position;
        }

        public void setPosition(int position) {
            this.f3319b = position;
        }

        public void onLayout(SwipeLayout v) {
            if (this.f3318a.isOpen(this.f3319b)) {
                v.open(false, false);
            } else {
                v.close(false, false);
            }
        }
    }

    private class SwipeMemory extends SimpleSwipeListener {
        final /* synthetic */ SwipeItemManagerImpl f3320a;
        private int f3321b;

        SwipeMemory(SwipeItemManagerImpl swipeItemManagerImpl, int position) {
            this.f3320a = swipeItemManagerImpl;
            this.f3321b = position;
        }

        public void onClose(SwipeLayout layout) {
            if (this.f3320a.f3327f == Mode.Multiple) {
                this.f3320a.f3324c.remove(Integer.valueOf(this.f3321b));
            } else {
                this.f3320a.f3323b = -1;
            }
        }

        public void onStartOpen(SwipeLayout layout) {
            if (this.f3320a.f3327f == Mode.Single) {
                this.f3320a.closeAllExcept(layout);
            }
        }

        public void onOpen(SwipeLayout layout) {
            if (this.f3320a.f3327f == Mode.Multiple) {
                this.f3320a.f3324c.add(Integer.valueOf(this.f3321b));
                return;
            }
            this.f3320a.closeAllExcept(layout);
            this.f3320a.f3323b = this.f3321b;
        }

        public void setPosition(int position) {
            this.f3321b = position;
        }
    }

    public SwipeItemManagerImpl(Adapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("Adapter can not be null");
        } else if (adapter instanceof SwipeItemManagerInterface) {
            this.f3326e = adapter;
        } else {
            throw new IllegalArgumentException("adapter should implement the SwipeAdapterInterface");
        }
    }

    public Mode getMode() {
        return this.f3327f;
    }

    public void setMode(Mode mode) {
        this.f3327f = mode;
        this.f3324c.clear();
        this.f3325d.clear();
        this.f3323b = -1;
    }

    private void m2059a(BaseSwipeableViewHolder targetViewHolder, int position) {
        targetViewHolder.f3285b = new OnLayoutListener(this, position);
        targetViewHolder.f3286c = new SwipeMemory(this, position);
        targetViewHolder.f3287d = position;
        targetViewHolder.f3284a.addSwipeListener(targetViewHolder.f3286c);
        targetViewHolder.f3284a.addOnLayoutListener(targetViewHolder.f3285b);
    }

    public void updateConvertView(BaseSwipeableViewHolder targetViewHolder, int position) {
        if (targetViewHolder.f3285b == null) {
            m2059a(targetViewHolder, position);
        }
        SwipeLayout swipeLayout = targetViewHolder.f3284a;
        if (swipeLayout == null) {
            throw new IllegalStateException("can not find SwipeLayout in target view");
        }
        this.f3325d.add(swipeLayout);
        ((SwipeMemory) targetViewHolder.f3286c).setPosition(position);
        ((OnLayoutListener) targetViewHolder.f3285b).setPosition(position);
        targetViewHolder.f3287d = position;
    }

    public void openItem(int position) {
        if (this.f3327f != Mode.Multiple) {
            this.f3323b = position;
        } else if (!this.f3324c.contains(Integer.valueOf(position))) {
            this.f3324c.add(Integer.valueOf(position));
        }
    }

    public void closeItem(int position) {
        if (this.f3327f == Mode.Multiple) {
            this.f3324c.remove(Integer.valueOf(position));
        } else if (this.f3323b == position) {
            this.f3323b = -1;
        }
    }

    public void closeAllExcept(SwipeLayout layout) {
        for (SwipeLayout s : this.f3325d) {
            if (s != layout) {
                s.close();
            }
        }
    }

    public void removeShownLayouts(SwipeLayout layout) {
        this.f3325d.remove(layout);
    }

    public List<Integer> getOpenItems() {
        if (this.f3327f == Mode.Multiple) {
            return new ArrayList(this.f3324c);
        }
        return Arrays.asList(new Integer[]{Integer.valueOf(this.f3323b)});
    }

    public List<SwipeLayout> getOpenLayouts() {
        return new ArrayList(this.f3325d);
    }

    public boolean isOpen(int position) {
        if (this.f3327f == Mode.Multiple) {
            return this.f3324c.contains(Integer.valueOf(position));
        }
        return this.f3323b == position;
    }
}
