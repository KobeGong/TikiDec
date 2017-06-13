package com.buddy.tiki.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.user.FlwApplyResponse;
import com.buddy.tiki.ui.adapter.ApplyListAdapter;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.view.superrecyclerview.SuperRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;

public class ApplyListFragment extends BaseFragment {
    private static final TikiLog f2013a = TikiLog.getInstance("ApplyListFragment");
    private ApplyListAdapter f2014c;
    private long f2015d = -1;
    @BindView(2131820925)
    AppCompatTextView mAddFriend;
    @BindView(2131820928)
    SuperRecyclerView mApplyList;
    @BindView(2131820926)
    AppCompatTextView mApplyTips;
    @BindView(2131820927)
    View mDividerLine;
    @BindView(2131820688)
    Toolbar mToolbar;

    class C04581 extends AdapterDataObserver {
        final /* synthetic */ ApplyListFragment f2008a;

        C04581(ApplyListFragment this$0) {
            this.f2008a = this$0;
        }

        public void onChanged() {
            super.onChanged();
        }
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_apply_list;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        PreferenceUtil.setApplyNumber(0);
        m1206b();
        m1208c();
        fetchApplyList();
    }

    private void m1206b() {
        this.mApplyList.setEmptyText((int) C0376R.string.empty_apply_list);
        this.mApplyList.setLayoutManager(new LinearLayoutManager(m1203h()));
        ItemAnimator itemAnimator = this.mApplyList.getRecyclerView().getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
        this.f2014c = new ApplyListAdapter(m1203h());
        this.mApplyList.setAdapter(this.f2014c);
        this.mApplyList.setupMoreListener(ApplyListFragment$$Lambda$1.lambdaFactory$(this), 1);
    }

    /* synthetic */ void m1210a(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        fetchApplyList();
    }

    private void m1208c() {
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(ApplyListFragment$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.mAddFriend).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ApplyListFragment$$Lambda$3.lambdaFactory$(this));
        this.f2014c.registerAdapterDataObserver(new C04581(this));
    }

    /* synthetic */ void m1214b(Object aVoid) throws Exception {
        m1204i();
    }

    /* synthetic */ void m1213a(Object aVoid) throws Exception {
        m1199a(new SearchTikiFragment(), true);
        this.f2015d = -1;
    }

    public void fetchApplyList() {
        getDataLayer().getFollowManager().applysQuery(this.f2015d).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).doOnNext(ApplyListFragment$$Lambda$4.lambdaFactory$(this)).filter(ApplyListFragment$$Lambda$5.lambdaFactory$()).subscribe(ApplyListFragment$$Lambda$6.lambdaFactory$(this), ApplyListFragment$$Lambda$7.lambdaFactory$());
    }

    /* synthetic */ void m1215c(FlwApplyResponse flwApplyResponse) throws Exception {
        this.mApplyList.hideMoreProgress();
    }

    static /* synthetic */ boolean m1207b(FlwApplyResponse flwApplyResponse) throws Exception {
        return flwApplyResponse != null;
    }

    /* synthetic */ void m1212a(FlwApplyResponse flwApplyResponse) throws Exception {
        if (flwApplyResponse.getApplys() != null && flwApplyResponse.getApplys().length > 0) {
            this.f2014c.addDataArray(flwApplyResponse.getApplys());
            this.mApplyTips.setVisibility(0);
            this.mDividerLine.setVisibility(0);
        } else if (this.f2015d == -1) {
            this.mDividerLine.setVisibility(8);
            this.mApplyTips.setVisibility(8);
        }
        this.f2015d = flwApplyResponse.getMaxTimestamp();
    }

    static /* synthetic */ void m1205a(Throwable throwable) throws Exception {
    }
}
