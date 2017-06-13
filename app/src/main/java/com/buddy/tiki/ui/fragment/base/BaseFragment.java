package com.buddy.tiki.ui.fragment.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

public abstract class BaseFragment extends RxFragment {
    private BaseActivity f2009a;
    protected View f2010b;
    private DataLayer f2011c = DataLayer.getInstance();
    private Unbinder f2012d;

    @LayoutRes
    protected abstract int mo2192a();

    protected abstract void mo2193a(Bundle bundle);

    public DataLayer getDataLayer() {
        return this.f2011c;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.f2010b = inflater.inflate(mo2192a(), container, false);
        return this.f2010b;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.f2012d = ButterKnife.bind((Object) this, view);
        mo2193a(savedInstanceState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            this.f2009a = (BaseActivity) activity;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f2012d.unbind();
    }

    protected BaseActivity m1203h() {
        return this.f2009a;
    }

    protected void m1199a(@NonNull BaseFragment fragment, boolean usedAnim) {
        this.f2009a.addFragment(fragment, usedAnim);
    }

    protected void m1204i() {
        this.f2009a.removeFragment();
    }

    protected void m1198a(@NonNull View view) {
        this.f2009a.showKeyBoard(view);
    }

    protected void m1200a(@NonNull Class cls) {
        m1202a(cls, null);
    }

    protected void m1202a(@NonNull Class cls, @Nullable Bundle args) {
        this.f2009a.launchActivity(cls, args);
    }

    protected void m1201a(@NonNull Class cls, int requestCode, @Nullable Bundle args) {
        Intent intent = new Intent(getContext(), cls);
        if (args != null) {
            intent.putExtra("PARAMS_KEY", args);
        }
        startActivityForResult(intent, requestCode);
    }
}
