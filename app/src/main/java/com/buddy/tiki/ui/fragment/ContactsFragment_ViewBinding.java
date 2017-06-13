package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class ContactsFragment_ViewBinding implements Unbinder {
    private ContactsFragment f2138b;

    @UiThread
    public ContactsFragment_ViewBinding(ContactsFragment target, View source) {
        this.f2138b = target;
        target.friendsView = (RecyclerView) Utils.findRequiredViewAsType(source, C0376R.id.child_add_friends_view, "field 'friendsView'", RecyclerView.class);
        target.mSearchText = (SearchView) Utils.findRequiredViewAsType(source, C0376R.id.search_text, "field 'mSearchText'", SearchView.class);
        target.finish = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.add_friend_finish, "field 'finish'", LinearLayout.class);
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.friendNum = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.add_friend_num, "field 'friendNum'", AppCompatTextView.class);
        target.addFriends = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.add_friend, "field 'addFriends'", AppCompatTextView.class);
        target.selectAll = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.select_all, "field 'selectAll'", AppCompatImageView.class);
        target.selectAllLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.select_all_layout, "field 'selectAllLayout'", LinearLayout.class);
        target.title = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.contacts_title, "field 'title'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        ContactsFragment target = this.f2138b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2138b = null;
        target.friendsView = null;
        target.mSearchText = null;
        target.finish = null;
        target.mToolbar = null;
        target.friendNum = null;
        target.addFriends = null;
        target.selectAll = null;
        target.selectAllLayout = null;
        target.title = null;
    }
}
