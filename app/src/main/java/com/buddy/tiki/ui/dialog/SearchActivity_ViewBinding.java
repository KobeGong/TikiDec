package com.buddy.tiki.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class SearchActivity_ViewBinding implements Unbinder {
    private SearchActivity f1982b;

    @UiThread
    public SearchActivity_ViewBinding(SearchActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public SearchActivity_ViewBinding(SearchActivity target, View source) {
        this.f1982b = target;
        target.mSearchText = (SearchView) Utils.findRequiredViewAsType(source, C0376R.id.search_text, "field 'mSearchText'", SearchView.class);
        target.mSearchList = (RecyclerView) Utils.findRequiredViewAsType(source, C0376R.id.data_list, "field 'mSearchList'", RecyclerView.class);
        target.mNoneTips = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.none_item_tips, "field 'mNoneTips'", AppCompatTextView.class);
        target.mBackBtn = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.back_btn, "field 'mBackBtn'", AppCompatImageView.class);
    }

    @CallSuper
    public void unbind() {
        SearchActivity target = this.f1982b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1982b = null;
        target.mSearchText = null;
        target.mSearchList = null;
        target.mNoneTips = null;
        target.mBackBtn = null;
    }
}
