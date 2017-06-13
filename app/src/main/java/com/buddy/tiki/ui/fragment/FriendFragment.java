package com.buddy.tiki.ui.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import butterknife.BindString;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.user.FlwApplyResponse;
import com.buddy.tiki.model.user.UserChatSession;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.ApplyListActivity;
import com.buddy.tiki.ui.activity.ContactsActivity;
import com.buddy.tiki.ui.adapter.SessionAdapter;
import com.buddy.tiki.ui.dialog.SearchActivity;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.RippleUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.view.NestedLinearLayoutManager;
import com.buddy.tiki.view.RoundTextView;
import com.buddy.tiki.view.superrecyclerview.SuperNestedRecyclerView;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.concurrent.TimeUnit;

public class FriendFragment extends BaseFragment {
    private static final TikiLog f2150a = TikiLog.getInstance("FriendFragment");
    @BindView(2131820925)
    AppCompatImageView addFriend;
    private SessionAdapter f2151c;
    private FriendFragmentEvent f2152d;
    private RealmResults<UserChatSession> f2153e;
    private OnSharedPreferenceChangeListener f2154f = new C04641(this);
    @BindView(2131820965)
    RoundTextView mApplyBadge;
    @BindView(2131820963)
    RelativeLayout mApplyBtnLayout;
    @BindView(2131820964)
    AppCompatTextView mApplyButton;
    @BindString(2131296312)
    String mApplyMessage;
    @BindView(2131820966)
    SuperNestedRecyclerView mFriendList;
    @BindView(2131820693)
    FrameLayout mSearchView;
    @BindView(2131820962)
    AppCompatImageView mVideoButton;
    @BindView(2131820961)
    View mVideoLayout;

    public interface FriendFragmentEvent {
        void onFriendBackToCall();
    }

    class C04641 implements OnSharedPreferenceChangeListener {
        final /* synthetic */ FriendFragment f2149a;

        C04641(FriendFragment this$0) {
            this.f2149a = this$0;
        }

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if (s.equals("TIKI_APPLY_NUM")) {
                this.f2149a.m1319g();
            }
        }
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_friend;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1313b();
        m1315c();
        m1316d();
        m1318f();
        m1317e();
        PreferenceUtil.getUserSharedPreference().registerOnSharedPreferenceChangeListener(this.f2154f);
    }

    private void m1313b() {
        RippleUtil.setRippleBackground(m1203h(), this.mVideoLayout);
        NestedLinearLayoutManager linearLayoutManager = new NestedLinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.mFriendList.setLayoutManager(linearLayoutManager);
        ItemAnimator itemAnimator = this.mFriendList.getRecyclerView().getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
    }

    private void m1315c() {
        RxView.clicks(this.addFriend).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(FriendFragment$$Lambda$1.lambdaFactory$(this));
        RxView.clicks(this.mApplyBtnLayout).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(FriendFragment$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.mSearchView).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(FriendFragment$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.mVideoLayout).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(FriendFragment$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.mFriendList.getEmptyView().findViewById(C0376R.id.chat_with_friends)).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(FriendFragment$$Lambda$5.lambdaFactory$(this));
    }

    /* synthetic */ void m1328e(Object aVoid) throws Exception {
        m1200a(ContactsActivity.class);
    }

    /* synthetic */ void m1327d(Object aVoid) throws Exception {
        m1200a(ApplyListActivity.class);
    }

    /* synthetic */ void m1326c(Object aVoid) throws Exception {
        m1200a(SearchActivity.class);
    }

    /* synthetic */ void m1325b(Object aVoid) throws Exception {
        if (this.f2152d != null) {
            this.f2152d.onFriendBackToCall();
        }
    }

    /* synthetic */ void m1324a(Object aVoid) throws Exception {
        if (this.f2152d != null) {
            this.f2152d.onFriendBackToCall();
        }
    }

    private void m1316d() {
        DataLayer.getInstance().getAppManager().getConfigCache().compose(SchedulersCompat.applyIoSchedulers()).compose(bindToLifecycle()).subscribe(FriendFragment$$Lambda$6.lambdaFactory$(this), FriendFragment$$Lambda$7.lambdaFactory$());
    }

    /* synthetic */ void m1322a(ConfigInfo configInfo) throws Exception {
        if (!isAdded()) {
            return;
        }
        if (configInfo.isHideRndMatch()) {
            this.mVideoButton.setVisibility(8);
        } else {
            this.mVideoButton.setVisibility(0);
        }
    }

    static /* synthetic */ void m1314b(Throwable throwable) throws Exception {
    }

    private void m1317e() {
        DataLayer.getInstance().getFollowManager().applysQuery(-1).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).map(FriendFragment$$Lambda$8.lambdaFactory$()).subscribe(FriendFragment$$Lambda$9.lambdaFactory$(this), FriendFragment$$Lambda$10.lambdaFactory$());
    }

    static /* synthetic */ Integer m1310a(FlwApplyResponse flwApplyResponse) throws Exception {
        int length = (flwApplyResponse == null || flwApplyResponse.getApplys() == null) ? 0 : flwApplyResponse.getApplys().length;
        return Integer.valueOf(length);
    }

    /* synthetic */ void m1323a(Integer size) throws Exception {
        if (isAdded()) {
            PreferenceUtil.setApplyNumber(size.intValue());
            if (size.intValue() > 0) {
                this.mApplyBadge.setVisibility(0);
                this.mApplyBadge.setText(String.valueOf(size));
                return;
            }
            this.mApplyBadge.setVisibility(8);
        }
    }

    private void m1318f() {
        Realm realm = Realm.getDefaultInstance();
        this.f2153e = realm.where(UserChatSession.class).findAll().sort("timestamp", Sort.DESCENDING);
        f2150a.m263e("mUserChatSessions size = " + this.f2153e.size());
        this.f2151c = new SessionAdapter(m1203h(), this.f2153e, true, false, "timestamp", "unread");
        this.mFriendList.setAdapter(this.f2151c);
        realm.close();
    }

    public void onDestroyView() {
        this.f2151c.close();
        PreferenceUtil.getUserSharedPreference().unregisterOnSharedPreferenceChangeListener(this.f2154f);
        super.onDestroyView();
    }

    public void onRestartPage() {
        m1319g();
    }

    private void m1319g() {
        int num = PreferenceUtil.getApplyNumber();
        if (this.mApplyBadge == null) {
            return;
        }
        if (num > 0) {
            this.mApplyBadge.setVisibility(0);
            this.mApplyBadge.setText(String.valueOf(num));
            return;
        }
        this.mApplyBadge.setVisibility(8);
    }

    public void onResume() {
        super.onResume();
        m1319g();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f2152d = (FriendFragmentEvent) activity;
        } catch (Exception e) {
            f2150a.m263e("get event fail");
        }
    }
}
