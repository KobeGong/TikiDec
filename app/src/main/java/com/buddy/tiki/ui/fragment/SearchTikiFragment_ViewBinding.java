package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.superrecyclerview.SuperRecyclerView;

public class SearchTikiFragment_ViewBinding implements Unbinder {
    private SearchTikiFragment f2201b;

    @UiThread
    public SearchTikiFragment_ViewBinding(SearchTikiFragment target, View source) {
        this.f2201b = target;
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mSearchResult = (SuperRecyclerView) Utils.findRequiredViewAsType(source, C0376R.id.data_list, "field 'mSearchResult'", SuperRecyclerView.class);
        target.mSearchView = (AppCompatEditText) Utils.findRequiredViewAsType(source, C0376R.id.search_view, "field 'mSearchView'", AppCompatEditText.class);
    }

    @CallSuper
    public void unbind() {
        SearchTikiFragment target = this.f2201b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2201b = null;
        target.mToolbar = null;
        target.mSearchResult = null;
        target.mSearchView = null;
    }
}
