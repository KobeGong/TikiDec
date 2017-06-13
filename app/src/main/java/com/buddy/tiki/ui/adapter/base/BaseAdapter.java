package com.buddy.tiki.ui.adapter.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseAdapter<VH extends ViewHolder, T> extends Adapter<VH> {
    protected final List<T> f1634a;
    protected Context f1635b;
    protected LayoutInflater f1636c;
    protected OnItemClick<T> f1637d;
    protected OnItemLongClick<T> f1638e;

    public interface OnItemClick<T> {
        void click(View view, T t, int i);
    }

    public interface OnItemLongClick<T> {
        boolean longClick(View view, T t, int i);
    }

    @LayoutRes
    protected abstract int mo2165a();

    public abstract void addData(@NonNull T t);

    public abstract void addDataList(@NonNull List<T> list);

    protected abstract VH mo2168b(View view);

    public BaseAdapter(@NonNull Context context) {
        this.f1635b = context;
        this.f1636c = LayoutInflater.from(context);
        this.f1634a = new ArrayList();
    }

    public BaseAdapter(@NonNull Context context, @Nullable OnItemClick<T> listener) {
        this(context);
        this.f1637d = listener;
    }

    public BaseAdapter(@NonNull Context context, @Nullable OnItemLongClick<T> listener) {
        this(context);
        this.f1638e = listener;
    }

    @NonNull
    public List<T> getDataList() {
        return this.f1634a;
    }

    @MainThread
    public void clearDataList() {
        this.f1634a.clear();
        notifyDataSetChanged();
    }

    public void addDataArray(@NonNull T[] array) {
        addDataList(Arrays.asList(array));
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return mo2168b(this.f1636c.inflate(mo2165a(), parent, false));
    }

    public int getItemCount() {
        return this.f1634a == null ? 0 : this.f1634a.size();
    }
}
