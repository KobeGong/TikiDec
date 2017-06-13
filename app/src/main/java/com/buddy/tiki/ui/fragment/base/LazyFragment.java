package com.buddy.tiki.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.trello.rxlifecycle2.components.support.RxFragment;
import io.realm.Realm;

public abstract class LazyFragment extends RxFragment {
    private static final String f2311b = LazyFragment.class.getSimpleName();
    protected Realm f2312a;
    private boolean f2313c;
    private Unbinder f2314d;
    private boolean f2315e = true;
    private boolean f2316f = true;
    private boolean f2317g = true;

    @LayoutRes
    protected abstract int m1507a();

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(m1507a(), container, false);
        this.f2314d = ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onResume() {
        super.onResume();
        if (this.f2315e) {
            this.f2315e = false;
        } else if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (this.f2316f) {
                this.f2316f = false;
                initPrepare();
                return;
            }
            onUserVisible();
        } else if (this.f2317g) {
            this.f2317g = false;
            onFirstUserInvisible();
        } else {
            onUserInvisible();
        }
    }

    public synchronized void initPrepare() {
        if (this.f2313c) {
            onFirstUserVisible();
        } else {
            this.f2313c = true;
        }
    }

    public void onFirstUserVisible() {
    }

    public void onUserVisible() {
    }

    public void onFirstUserInvisible() {
    }

    public void onUserInvisible() {
    }

    public void onStart() {
        super.onStart();
        this.f2312a = Realm.getDefaultInstance();
    }

    public void onStop() {
        super.onStop();
        this.f2312a.close();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f2314d.unbind();
    }
}
