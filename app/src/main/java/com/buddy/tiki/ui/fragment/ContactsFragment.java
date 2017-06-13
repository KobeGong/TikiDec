package com.buddy.tiki.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.ui.adapter.ContactsAdapter;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.PinYinUtil;
import com.buddy.tiki.util.PinyinComparator;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactsFragment extends BaseFragment {
    private static final TikiLog f2130a = TikiLog.getInstance("ContactsFragment");
    @BindView(2131820925)
    AppCompatTextView addFriends;
    private ContactsAdapter f2131c;
    private RealmResults<TikiUser> f2132d;
    private List<TikiUser> f2133e;
    private boolean f2134f = false;
    @BindView(2131820691)
    LinearLayout finish;
    @BindView(2131820692)
    AppCompatTextView friendNum;
    @BindView(2131820959)
    RecyclerView friendsView;
    private String f2135g;
    private String[] f2136h;
    private boolean f2137i = false;
    @BindView(2131820694)
    SearchView mSearchText;
    @BindView(2131820688)
    Toolbar mToolbar;
    @BindView(2131820696)
    AppCompatImageView selectAll;
    @BindView(2131820960)
    LinearLayout selectAllLayout;
    @BindView(2131820958)
    AppCompatTextView title;

    protected int mo2192a() {
        return C0376R.layout.fragment_contacts;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1297b();
        m1298c();
        m1300e();
    }

    private void m1297b() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(m1203h());
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.friendsView.setLayoutManager(linearLayoutManager);
        ItemAnimator itemAnimator = this.friendsView.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
    }

    private void m1298c() {
        if (getArguments() != null) {
            this.f2135g = getArguments().getString("PARAM_KEY_UID");
            this.f2136h = getArguments().getStringArray("PARAM_KEY_SELECTED_FRIENDS");
            this.f2137i = getArguments().getBoolean("PARAM_KEY_VIDEO_TO_CONTACTS");
        }
        Realm realm = Realm.getDefaultInstance();
        this.f2132d = realm.where(TikiUser.class).equalTo("relation", Integer.valueOf(4)).findAll();
        realm.close();
        this.f2133e = m1295a(this.f2132d);
        Collections.sort(this.f2133e, new PinyinComparator());
        m1299d();
        this.f2131c = new ContactsAdapter(m1203h(), this.f2133e, this.f2135g, this.f2137i);
        if (this.f2137i) {
            if (this.f2136h != null) {
                this.f2131c.setSelectUsers(new HashSet(Arrays.asList(this.f2136h)));
                setSelectUserCount(this.f2136h.length);
            }
            this.title.setText(getString(C0376R.string.record_send_friends_title));
            this.selectAllLayout.setVisibility(0);
            this.finish.setVisibility(0);
            this.addFriends.setVisibility(8);
        } else {
            this.title.setText(getString(C0376R.string.contacts));
            this.selectAllLayout.setVisibility(8);
            this.finish.setVisibility(8);
            this.addFriends.setVisibility(0);
        }
        this.friendsView.setAdapter(this.f2131c);
    }

    private List<TikiUser> m1295a(RealmResults<TikiUser> users) {
        List<TikiUser> ret = null;
        if (users != null) {
            ret = new ArrayList();
            Iterator it = users.iterator();
            while (it.hasNext()) {
                TikiUser user = (TikiUser) it.next();
                if (user != null && user.isValid()) {
                    String pinyin = PinYinUtil.getInstance().transferCNToPinyin(m1203h(), user.getMark());
                    String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                    if (Fpinyin.matches("[A-Z]")) {
                        user.setFirstPinYin(Fpinyin);
                    } else {
                        user.setFirstPinYin("{");
                        Fpinyin = "{";
                    }
                    user.setPinYin(Fpinyin + pinyin);
                    if (user.getFirstPinYin() != null) {
                        ret.add(user);
                    }
                }
            }
        }
        return ret;
    }

    private void m1299d() {
        if (this.f2133e != null) {
            int size = this.f2133e.size();
            String previousPinyin = BuildConfig.VERSION_NAME;
            for (int i = 0; i < size; i++) {
                TikiUser user = (TikiUser) this.f2133e.get(i);
                String pinyin = user.getFirstPinYin();
                if (!pinyin.equals(previousPinyin)) {
                    previousPinyin = pinyin;
                    user.setShowPinyin(true);
                }
            }
        }
    }

    public void setSelectUserCount(int count) {
        this.friendNum.setText(BuildConfig.VERSION_NAME + count);
        if (this.f2133e.size() == count) {
            this.selectAll.setBackgroundResource(C0376R.mipmap.icon_gouxuan);
            this.f2134f = true;
            return;
        }
        this.selectAll.setBackgroundResource(C0376R.mipmap.icon_kexuan_black);
        this.f2134f = false;
    }

    private void m1300e() {
        RxSearchView.queryTextChanges(this.mSearchText).debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ContactsFragment$$Lambda$1.lambdaFactory$(this), ContactsFragment$$Lambda$2.lambdaFactory$());
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(ContactsFragment$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.finish).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ContactsFragment$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.selectAll).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ContactsFragment$$Lambda$5.lambdaFactory$(this));
        RxView.clicks(this.addFriends).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ContactsFragment$$Lambda$6.lambdaFactory$(this));
    }

    /* synthetic */ void m1303a(CharSequence charSequence) throws Exception {
        String keywords = charSequence.toString().trim();
        if (TextUtils.isEmpty(keywords)) {
            this.f2133e = m1295a(this.f2132d);
        } else {
            Realm realm = Realm.getDefaultInstance();
            RealmResults filterUsers = realm.where(TikiUser.class).equalTo("relation", Integer.valueOf(4)).contains("mark", keywords).findAll();
            realm.close();
            this.f2133e = m1295a(filterUsers);
        }
        Collections.sort(this.f2133e, new PinyinComparator());
        m1299d();
        this.f2131c.setData(this.f2133e);
    }

    static /* synthetic */ void m1296a(Throwable throwable) throws Exception {
    }

    /* synthetic */ void m1307d(Object aVoid) throws Exception {
        m1204i();
    }

    /* synthetic */ void m1306c(Object aVoid) throws Exception {
        if (this.f2137i) {
            m1203h().getIntent().putExtra("PARAM_KEY_VIDEO_TO_ALL", this.f2134f);
            m1203h().getIntent().putExtra("PARAM_KEY_VIDEO_RECORD_SELECT_USERS", (String[]) this.f2131c.getSelectUser().toArray(new String[this.f2131c.getSelectUser().size()]));
            m1203h().setResult(FragmentTransaction.TRANSIT_FRAGMENT_OPEN, m1203h().getIntent());
        }
        m1204i();
    }

    /* synthetic */ void m1305b(Object aVoid) throws Exception {
        if (this.f2134f) {
            this.selectAll.setBackgroundResource(C0376R.mipmap.icon_kexuan_black);
            this.f2134f = false;
            this.f2131c.selectAll(false);
            return;
        }
        this.selectAll.setBackgroundResource(C0376R.mipmap.icon_gouxuan);
        this.f2134f = true;
        this.f2131c.selectAll(true);
    }

    /* synthetic */ void m1304a(Object aVoid) throws Exception {
        m1199a(new SearchTikiFragment(), true);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
