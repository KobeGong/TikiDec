package com.buddy.tiki.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import butterknife.BindView;
import butterknife.OnClick;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.ui.dialog.LoadingDialog;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import org.parceler.Parcels;

public class ModifyNickFragment extends BaseFragment {
    private User f2162a;
    @BindView(2131820898)
    AppCompatEditText mNickInput;
    @BindView(2131820974)
    AppCompatTextView mSaveButton;
    @BindView(2131820688)
    Toolbar mToolbar;

    public static ModifyNickFragment newInstance(@NonNull User user) {
        ModifyNickFragment fragment = new ModifyNickFragment();
        Bundle args = new Bundle();
        args.putParcelable("PARAM_KEY_USER", Parcels.wrap(user));
        fragment.setArguments(args);
        return fragment;
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_modify_nick;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1334c();
        m1335d();
        m1336e();
    }

    private void m1334c() {
        Bundle args = getArguments();
        if (args != null) {
            this.f2162a = (User) Parcels.unwrap(args.getParcelable("PARAM_KEY_USER"));
        }
    }

    private void m1335d() {
        if (this.f2162a != null) {
            String nick = this.f2162a.getNick();
            this.mNickInput.setText(nick);
            this.mNickInput.setSelection(TextUtils.isEmpty(nick) ? 0 : nick.length());
        }
    }

    private void m1336e() {
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(ModifyNickFragment$$Lambda$1.lambdaFactory$(this));
        RxTextView.afterTextChangeEvents(this.mNickInput).compose(bindToLifecycle()).map(ModifyNickFragment$$Lambda$2.lambdaFactory$()).map(ModifyNickFragment$$Lambda$3.lambdaFactory$()).subscribe(RxView.enabled(this.mSaveButton));
    }

    /* synthetic */ void m1340a(Object aVoid) throws Exception {
        m1204i();
    }

    static /* synthetic */ Boolean m1331a(Integer integer) throws Exception {
        return Boolean.valueOf(integer.intValue() > 0);
    }

    @OnClick({2131820974})
    public void onSave() {
        Observable.just(this.mNickInput.getEditableText().toString().trim()).flatMapCompletable(ModifyNickFragment$$Lambda$4.lambdaFactory$(this)).compose(bindToLifecycle()).compose(SchedulersCompat.applyIOCompletableSchedulers()).subscribe(ModifyNickFragment$$Lambda$5.lambdaFactory$(this), ModifyNickFragment$$Lambda$6.lambdaFactory$());
    }

    /* synthetic */ CompletableSource m1338a(String s) throws Exception {
        return getDataLayer().getUserManager().setAvatarNickGenderAction(this.f2162a.getAvatar(), s, this.f2162a.getGender());
    }

    /* synthetic */ void m1341b() throws Exception {
        LoadingDialog.stopLoading();
        ToastUtil.getInstance().show((int) C0376R.string.modify_success);
        m1204i();
    }
}
