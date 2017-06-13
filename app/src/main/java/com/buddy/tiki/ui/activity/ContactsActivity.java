package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.fragment.ContactsFragment;

public class ContactsActivity extends BaseActivity {
    private static final TikiLog f1401a = TikiLog.getInstance("ContactsActivity");
    private ContactsFragment f1402b;

    @LayoutRes
    protected int mo2115a() {
        return C0376R.layout.activity_blank;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        this.f1402b = new ContactsFragment();
        this.f1402b.setArguments(getArguments());
        addFragment(this.f1402b);
    }

    @IdRes
    protected int mo2117b() {
        return C0376R.id.fragment_container;
    }
}
