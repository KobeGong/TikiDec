package com.buddy.tiki.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.trace.InboxMessage;
import com.buddy.tiki.ui.adapter.SystemMessageAdapter;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.view.superrecyclerview.SuperRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import java.util.List;

public class SystemMessageFragment extends BaseFragment {
    private SystemMessageAdapter f2223a;
    private long f2224c = -1;
    @BindView(2131820770)
    SuperRecyclerView mMessageList;
    @BindView(2131820688)
    Toolbar mToolbar;

    protected int mo2192a() {
        return C0376R.layout.fragment_system_message;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1413b();
        m1415d();
        m1414c();
    }

    private void m1413b() {
        this.mMessageList.setEmptyText((int) C0376R.string.empty_system_message);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(m1203h());
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.mMessageList.setLayoutManager(linearLayoutManager);
        ItemAnimator itemAnimator = this.mMessageList.getRecyclerView().getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
        this.f2223a = new SystemMessageAdapter(m1203h());
        this.mMessageList.setAdapter(this.f2223a);
    }

    private void m1414c() {
        getDataLayer().getMessageManager().messageRequest(this.f2224c).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).doOnNext(SystemMessageFragment$$Lambda$1.lambdaFactory$(this)).subscribe(SystemMessageFragment$$Lambda$2.lambdaFactory$(this), SystemMessageFragment$$Lambda$3.lambdaFactory$());
    }

    /* synthetic */ void m1421b(List inboxMessages) throws Exception {
        this.mMessageList.hideMoreProgress();
    }

    /* synthetic */ void m1420a(List inboxMessages) throws Exception {
        if (inboxMessages != null && inboxMessages.size() > 0) {
            this.f2223a.addDataList(inboxMessages);
            this.f2224c = ((InboxMessage) inboxMessages.get(inboxMessages.size() - 1)).getCtime();
        }
    }

    static /* synthetic */ void m1412a(Throwable throwable) throws Exception {
    }

    private void m1415d() {
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(SystemMessageFragment$$Lambda$4.lambdaFactory$(this));
        this.mMessageList.setupMoreListener(SystemMessageFragment$$Lambda$5.lambdaFactory$(this), 1);
    }

    /* synthetic */ void m1419a(Object aVoid) throws Exception {
        m1204i();
    }

    /* synthetic */ void m1417a(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        m1414c();
    }
}
