package com.buddy.tiki.ui.dialog.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.buddy.tiki.service.base.DataLayer;
import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment;
import io.realm.Realm;

public abstract class BaseDialogFragment extends RxAppCompatDialogFragment {
    protected Realm f1844a;
    protected View f1845b;
    private DataLayer f1846c = DataLayer.getInstance();
    private Unbinder f1847d;

    @LayoutRes
    protected abstract int mo2172a();

    protected abstract void mo2173a(Bundle bundle);

    public DataLayer getDataLayer() {
        return this.f1846c;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.f1845b = inflater.inflate(mo2172a(), container, false);
        this.f1847d = ButterKnife.bind((Object) this, this.f1845b);
        return this.f1845b;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        m1057b(savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        mo2173a(savedInstanceState);
    }

    protected void m1057b(Bundle savedInstanceState) {
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f1847d.unbind();
    }

    public void onStop() {
        super.onStop();
        this.f1844a.close();
    }

    public void onStart() {
        super.onStart();
        this.f1844a = Realm.getDefaultInstance();
    }
}
