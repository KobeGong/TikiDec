package com.buddy.tiki.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.InputDeviceCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.event.UserEvent.ModifyGenderEvent;
import com.buddy.tiki.event.UserEvent.ModifyProfileEvent;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.ui.activity.AvatarEditActivity;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.PhotoUtil;
import com.buddy.tiki.util.ResourceUrlUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.parceler.Parcels;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class ProfileFragment extends BaseFragment {
    private User f2188a;
    private String f2189c;
    private String f2190d;
    @BindView(2131820794)
    SimpleDraweeView mAvatar;
    @BindView(2131820978)
    View mAvatarLayout;
    @BindView(2131820981)
    AppCompatTextView mGender;
    @BindView(2131820980)
    View mGenderLayout;
    @BindView(2131820775)
    AppCompatTextView mNick;
    @BindView(2131820979)
    View mNickLayout;
    @BindView(2131820977)
    ViewGroup mSpringLayout;
    @BindView(2131820983)
    AppCompatTextView mTiki;
    @BindView(2131820982)
    View mTikiLayout;
    @BindView(2131820688)
    Toolbar mToolbar;

    public static ProfileFragment newInstance(@NonNull User user) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable("PARAM_KEY_USER", Parcels.wrap(user));
        profileFragment.setArguments(args);
        return profileFragment;
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_profile;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1363c();
        m1365e();
        m1366f();
    }

    private void m1363c() {
        Bundle args = getArguments();
        if (args != null) {
            this.f2188a = (User) Parcels.unwrap(args.getParcelable("PARAM_KEY_USER"));
            if (this.f2188a == null) {
                m1364d();
                return;
            }
            return;
        }
        m1364d();
    }

    private void m1364d() {
        getDataLayer().getUserManager().userRequest(TopConfig.f408a).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(ProfileFragment$$Lambda$1.lambdaFactory$(this), ProfileFragment$$Lambda$2.lambdaFactory$());
    }

    /* synthetic */ void m1370a(User user) throws Exception {
        this.f2188a = user;
        m1365e();
    }

    static /* synthetic */ void m1362b(Throwable throwable) throws Exception {
    }

    private void m1365e() {
        if (this.f2188a != null) {
            FrescoUtil.setImageURI(this.mAvatar, ResourceUrlUtil.getNormalAvatar(this.f2188a.getAvatar(), DisplayUtil.dip2px(48.0f)));
            this.mNick.setText(this.f2188a.getNick());
            this.mTiki.setText(String.valueOf(this.f2188a.getTid()));
            switch (this.f2188a.getGender()) {
                case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                    this.mGender.setText("unbelievable");
                    break;
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    this.mGender.setText(C0376R.string.male);
                    break;
                case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                    this.mGender.setText(C0376R.string.female);
                    break;
            }
            if (!this.f2188a.isResetGender()) {
                this.mGender.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
            }
        }
    }

    private void m1366f() {
        RxToolbar.navigationClicks(this.mToolbar).compose(bindToLifecycle()).subscribe(ProfileFragment$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.mNickLayout).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(ProfileFragment$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.mGenderLayout).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(ProfileFragment$$Lambda$5.lambdaFactory$(this));
        RxView.clicks(this.mAvatarLayout).compose(bindToLifecycle()).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(ProfileFragment$$Lambda$6.lambdaFactory$(this));
    }

    /* synthetic */ void m1374d(Object aVoid) throws Exception {
        m1204i();
    }

    /* synthetic */ void m1373c(Object aVoid) throws Exception {
        m1199a(ModifyNickFragment.newInstance(this.f2188a), true);
    }

    /* synthetic */ void m1372b(Object aVoid) throws Exception {
        if (this.f2188a != null) {
            if (this.f2188a.isResetGender()) {
                DialogHelper.INSTANCE.showModifyGender(m1203h(), this.f2188a);
            } else {
                DialogHelper.INSTANCE.showForbiddenModifyGenderDialog(m1203h());
            }
        }
    }

    /* synthetic */ void m1371a(Object aVoid) throws Exception {
        this.f2190d = System.currentTimeMillis() + ".jpg";
        DialogHelper.INSTANCE.showImageChooser((BaseFragment) this, this.f2190d);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onNickChangeEvent(ModifyProfileEvent event) {
        if (event != null) {
            String nick = event.f512a;
            int gender = event.f513b;
            String avatar = event.f514c;
            if (this.f2188a != null) {
                this.f2188a.setGender(gender);
                this.f2188a.setNick(nick);
                this.mNick.setText(nick);
                this.mGender.setText(gender == 1 ? C0376R.string.male : C0376R.string.female);
                FrescoUtil.setImageURI(this.mAvatar, ResourceUrlUtil.getNormalAvatar(avatar, DisplayUtil.dip2px(48.0f)));
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGenderChangeEvent(ModifyGenderEvent event) {
        if (event != null) {
            int gender = event.f511a;
            if (this.f2188a != null) {
                this.f2188a.setGender(gender);
                this.f2188a.setResetGender(false);
                this.mGender.setText(gender == 1 ? C0376R.string.male : C0376R.string.female);
                this.mGender.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
            }
        }
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void m1367g() {
        if (!TextUtils.isEmpty(this.f2189c)) {
            Bundle fragmentArgs = new Bundle();
            fragmentArgs.putString("PARAM_KEY_IMAGE_PATH", this.f2189c);
            m1201a(AvatarEditActivity.class, InputDeviceCompat.SOURCE_TOUCHSCREEN, fragmentArgs);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == swscale.SWS_PRINT_INFO) {
            if (resultCode == -1) {
                this.f2189c = PhotoUtil.getPickedGalleryPath(getContext(), data);
                m1367g();
            }
        } else if (requestCode == FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
            if (resultCode == -1) {
                this.f2189c = PhotoUtil.getPickedCameraPath(this.f2190d);
                m1367g();
            }
        } else if (requestCode != InputDeviceCompat.SOURCE_TOUCHSCREEN) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode != 0 && resultCode == -1 && data != null && data.getExtras() != null && this.f2188a != null) {
            this.f2189c = data.getExtras().getString("PARAM_KEY_IMAGE_PATH");
            getDataLayer().getUserManager().setAvatarNickGenderAction(this.f2189c, this.f2188a.getNick(), this.f2188a.getGender()).compose(bindToLifecycle()).subscribeOn(Schedulers.io()).subscribe(ProfileFragment$$Lambda$7.lambdaFactory$(), ProfileFragment$$Lambda$8.lambdaFactory$());
        }
    }

    static /* synthetic */ void m1361b() throws Exception {
    }

    static /* synthetic */ void m1360a(Throwable throwable) throws Exception {
    }
}
