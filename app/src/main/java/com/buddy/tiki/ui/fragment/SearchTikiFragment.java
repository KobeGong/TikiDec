package com.buddy.tiki.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.TextView;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.ui.adapter.SearchAdapter;
import com.buddy.tiki.ui.dialog.LoadingDialog;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.view.superrecyclerview.SuperRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchTikiFragment extends BaseFragment {
    private SearchAdapter f2200a;
    @BindView(2131820770)
    SuperRecyclerView mSearchResult;
    @BindView(2131820693)
    AppCompatEditText mSearchView;
    @BindView(2131820688)
    Toolbar mToolbar;

    class C04681 implements Observer<User> {
        final /* synthetic */ SearchTikiFragment f2199a;

        C04681(SearchTikiFragment this$0) {
            this.f2199a = this$0;
        }

        public void onSubscribe(Disposable d) {
            LoadingDialog.startLoading(this.f2199a.m1203h(), (int) C0376R.string.searching);
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            LoadingDialog.stopLoading();
        }

        public void onNext(User user) {
            LoadingDialog.stopLoading();
            this.f2199a.f2200a.clearDataList();
            if (user != null) {
                this.f2199a.f2200a.addData(user);
            }
            this.f2199a.mSearchResult.setEmptyText((int) C0376R.string.no_such_user);
        }
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_search_by_tiki;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1387b();
        m1388c();
    }

    private void m1387b() {
        this.mSearchView.requestFocus();
        m1198a(this.mSearchView);
    }

    private void m1388c() {
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(SearchTikiFragment$$Lambda$1.lambdaFactory$(this));
        this.mSearchResult.setLayoutManager(new LinearLayoutManager(m1203h()));
        this.f2200a = new SearchAdapter(m1203h());
        this.mSearchResult.setAdapter(this.f2200a);
        this.mSearchView.setOnEditorActionListener(SearchTikiFragment$$Lambda$2.lambdaFactory$(this));
    }

    /* synthetic */ void m1391a(Object aVoid) throws Exception {
        m1204i();
    }

    /* synthetic */ boolean m1392a(TextView v, int actionId, KeyEvent event) {
        if (actionId != 3 || v.getText().length() <= 0) {
            return false;
        }
        getDataLayer().getUserManager().searchTikiQuery(v.getText().toString()).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(new C04681(this));
        return true;
    }
}
