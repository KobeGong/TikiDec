package com.buddy.tiki.ui.dialog;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SimpleItemAnimator;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.adapter.FriendAdapter;
import com.buddy.tiki.util.RippleUtil;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Case;
import io.realm.RealmResults;
import java.util.concurrent.TimeUnit;

public class SearchActivity extends BaseActivity {
    private FriendAdapter f1981a;
    @BindView(2131820707)
    AppCompatImageView mBackBtn;
    @BindView(2131820771)
    AppCompatTextView mNoneTips;
    @BindView(2131820770)
    RecyclerView mSearchList;
    @BindView(2131820694)
    SearchView mSearchText;

    protected int mo2117b() {
        return 0;
    }

    protected int mo2115a() {
        return C0376R.layout.activity_search;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m1164d();
        m1165e();
    }

    private void m1164d() {
        this.mSearchList.setLayoutManager(new LinearLayoutManager(this));
        ItemAnimator itemAnimator = this.mSearchList.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
        this.mSearchText.onActionViewExpanded();
        RippleUtil.setRippleBackground(this, this.mBackBtn);
    }

    private void m1165e() {
        RxSearchView.queryTextChanges(this.mSearchText).debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindUntilEvent(ActivityEvent.DESTROY)).flatMap(SearchActivity$$Lambda$1.lambdaFactory$(this)).filter(SearchActivity$$Lambda$2.lambdaFactory$()).observeOn(AndroidSchedulers.mainThread()).subscribe(SearchActivity$$Lambda$3.lambdaFactory$(this), SearchActivity$$Lambda$4.lambdaFactory$());
        RxView.clicks(this.mBackBtn).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(SearchActivity$$Lambda$5.lambdaFactory$(this));
        this.mSearchText.setOnCloseListener(SearchActivity$$Lambda$6.lambdaFactory$());
    }

    /* synthetic */ ObservableSource m1167a(CharSequence charSequence) throws Exception {
        return Observable.fromCallable(SearchActivity$$Lambda$7.lambdaFactory$(this, charSequence));
    }

    /* synthetic */ RealmResults m1172b(CharSequence charSequence) throws Exception {
        return this.c.where(TikiUser.class).contains("nick", charSequence.toString().trim(), Case.INSENSITIVE).or().contains("mark", charSequence.toString().trim(), Case.INSENSITIVE).findAll();
    }

    /* synthetic */ void m1169a(RealmResults tikiUsers) throws Exception {
        if (!tikiUsers.isValid() || tikiUsers.size() <= 0) {
            this.mSearchList.setVisibility(8);
            this.mNoneTips.setVisibility(0);
            return;
        }
        this.mSearchList.setVisibility(0);
        this.mNoneTips.setVisibility(8);
        this.f1981a = new FriendAdapter(this, tikiUsers, true, true, "lastMessageTime", "unread");
        this.mSearchList.setAdapter(this.f1981a);
    }

    static /* synthetic */ void m1162a(Throwable throwable) throws Exception {
    }

    /* synthetic */ void m1170a(Object aVoid) throws Exception {
        finish();
    }
}
