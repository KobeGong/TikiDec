package com.buddy.tiki.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.InputDeviceCompat;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.helper.FacebookHelper.FacebookUserInfo;
import com.buddy.tiki.helper.WeiboServiceHelper;
import com.buddy.tiki.helper.WeiboServiceHelper.UserCallback;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.promo.PromoResult;
import com.buddy.tiki.model.wechat.WxToken;
import com.buddy.tiki.model.wechat.WxUser;
import com.buddy.tiki.model.weibo.WbToken;
import com.buddy.tiki.protocol.ui.BackHandleInterface;
import com.buddy.tiki.ui.activity.AvatarEditActivity;
import com.buddy.tiki.ui.activity.CallActivity;
import com.buddy.tiki.ui.dialog.LoadingDialog;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.ByteUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.PhotoUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.weibo.openapi.models.User;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.DefaultExecutorSupplier;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import com.facebook.imagepipeline.request.ImageRequest;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import org.parceler.Parcels;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class UpdateProfileFragment extends BaseFragment implements BackHandleInterface {
    private static final TikiLog f2259a = TikiLog.getInstance("UpdateProfileFragment");
    private WbToken f2260c;
    private User f2261d;
    private WxToken f2262e;
    private WxUser f2263f;
    private Bundle f2264g;
    private int f2265h;
    private String f2266i;
    private String f2267j;
    private FacebookUserInfo f2268k;
    private String f2269l;
    @BindView(2131820794)
    RelativeLayout mAvatar;
    @BindView(2131820995)
    SimpleDraweeView mAvatarImage;
    @BindView(2131821000)
    AppCompatButton mDoneButton;
    @BindView(2131820999)
    AppCompatTextView mGenderChooseTips;
    @BindView(2131820998)
    AppCompatRadioButton mGenderFemale;
    @BindView(2131820996)
    RadioGroup mGenderGroup;
    @BindView(2131820997)
    AppCompatRadioButton mGenderMale;
    @BindView(2131820898)
    AppCompatEditText mNickInput;
    @BindView(2131820975)
    AppCompatEditText mPromotionCodeInput;

    class C04691 implements Observer<Boolean> {
        final /* synthetic */ UpdateProfileFragment f2251a;

        C04691(UpdateProfileFragment this$0) {
            this.f2251a = this$0;
        }

        public void onSubscribe(Disposable d) {
            LoadingDialog.startLoading(this.f2251a.getContext(), (int) C0376R.string.updating_profile);
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            LoadingDialog.stopLoading();
        }

        public void onNext(Boolean aBoolean) {
            LoadingDialog.stopLoading();
            if (aBoolean.booleanValue()) {
                this.f2251a.m1459o();
            }
        }
    }

    class C04702 implements Observer<Boolean> {
        final /* synthetic */ UpdateProfileFragment f2252a;

        C04702(UpdateProfileFragment this$0) {
            this.f2252a = this$0;
        }

        public void onSubscribe(Disposable d) {
            LoadingDialog.startLoading(this.f2252a.getContext(), (int) C0376R.string.updating_profile);
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            LoadingDialog.stopLoading();
            UpdateProfileFragment.f2259a.m264e("onError: ", e);
        }

        public void onNext(Boolean aBoolean) {
            LoadingDialog.stopLoading();
            if (aBoolean.booleanValue()) {
                this.f2252a.m1459o();
            }
        }
    }

    class C04713 implements Observer<Boolean> {
        final /* synthetic */ UpdateProfileFragment f2253a;

        C04713(UpdateProfileFragment this$0) {
            this.f2253a = this$0;
        }

        public void onSubscribe(Disposable d) {
            LoadingDialog.startLoading(this.f2253a.m1203h(), (int) C0376R.string.updating_profile);
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            LoadingDialog.stopLoading();
            UpdateProfileFragment.f2259a.m264e("weibo update error ", e);
        }

        public void onNext(Boolean aBoolean) {
            LoadingDialog.stopLoading();
            if (aBoolean.booleanValue()) {
                this.f2253a.m1459o();
            }
        }
    }

    class C04724 extends DisposableObserver<Boolean> {
        final /* synthetic */ UpdateProfileFragment f2254a;

        C04724(UpdateProfileFragment this$0) {
            this.f2254a = this$0;
        }

        protected void onStart() {
            LoadingDialog.startLoading(this.f2254a.m1203h(), (int) C0376R.string.updating_profile);
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            LoadingDialog.stopLoading();
        }

        public void onNext(Boolean aBoolean) {
            LoadingDialog.stopLoading();
            PreferenceUtil.setFacebookLogin();
            if (aBoolean.booleanValue()) {
                this.f2254a.m1459o();
            }
        }
    }

    class C04735 implements UserCallback<User> {
        final /* synthetic */ UpdateProfileFragment f2255a;

        C04735(UpdateProfileFragment this$0) {
            this.f2255a = this$0;
        }

        public void onSuccess(User result) {
            if (result != null) {
                this.f2255a.f2261d = result;
                String str = this.f2255a.f2261d.f3487n;
                boolean z = true;
                switch (str.hashCode()) {
                    case avutil.AV_PIX_FMT_YUVA444P10LE /*102*/:
                        if (str.equals("f")) {
                            z = true;
                            break;
                        }
                        break;
                    case avutil.AV_PIX_FMT_VDPAU /*109*/:
                        if (str.equals("m")) {
                            z = false;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                        this.f2255a.mGenderMale.setChecked(true);
                        break;
                    case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                        this.f2255a.mGenderFemale.setChecked(true);
                        break;
                }
                this.f2255a.mNickInput.setText(this.f2255a.f2261d.f3477d);
                this.f2255a.f2266i = this.f2255a.f2261d.f3464B;
                FrescoUtil.setImageURI(this.f2255a.mAvatarImage, this.f2255a.f2261d.f3464B);
            }
        }

        public void onFail(String error) {
        }
    }

    class C04746 implements DataSubscriber<CloseableReference<PooledByteBuffer>> {
        final /* synthetic */ UpdateProfileFragment f2258a;

        C04746(UpdateProfileFragment this$0) {
            this.f2258a = this$0;
        }

        public void onNewResult(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
            if (dataSource.isFinished()) {
                CloseableReference ref = (CloseableReference) dataSource.getResult();
                if (ref != null) {
                    try {
                        this.f2258a.getDataLayer().getTikiResManager().uploadAvatar(ByteUtil.readFully(new PooledByteBufferInputStream((PooledByteBuffer) ref.get()))).compose(this.f2258a.bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribeOn(Schedulers.io()).subscribe(UpdateProfileFragment$6$$Lambda$1.lambdaFactory$(this), UpdateProfileFragment$6$$Lambda$2.lambdaFactory$());
                    } finally {
                        CloseableReference.closeSafely(ref);
                    }
                }
            }
        }

        /* synthetic */ void m1423a(String avatar) throws Exception {
            if (!TextUtils.isEmpty(avatar)) {
                this.f2258a.f2268k.avatar = avatar;
                this.f2258a.f2266i = avatar;
            }
            LoadingDialog.stopLoading();
        }

        public void onFailure(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
        }

        public void onCancellation(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
        }

        public void onProgressUpdate(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
        }
    }

    protected int mo2192a() {
        return C0376R.layout.fragment_update_profile;
    }

    protected void mo2193a(Bundle savedInstanceState) {
        m1450e();
        m1440c();
        m1445d();
        m1453g();
    }

    private void m1440c() {
        this.mGenderMale.setButtonDrawable((int) C0376R.drawable.widget_radio_button_drawable);
        this.mGenderFemale.setButtonDrawable((int) C0376R.drawable.widget_radio_button_drawable);
    }

    private void m1445d() {
        Observable.combineLatest(RxTextView.afterTextChangeEvents(this.mNickInput).compose(bindToLifecycle()).map(UpdateProfileFragment$$Lambda$1.lambdaFactory$()), RxRadioGroup.checkedChanges(this.mGenderGroup).compose(bindToLifecycle()).map(UpdateProfileFragment$$Lambda$2.lambdaFactory$()).doOnNext(UpdateProfileFragment$$Lambda$3.lambdaFactory$(this)), UpdateProfileFragment$$Lambda$4.lambdaFactory$()).compose(bindToLifecycle()).subscribe(RxView.enabled(this.mDoneButton));
        RxView.clicks(this.mDoneButton).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(UpdateProfileFragment$$Lambda$5.lambdaFactory$(this));
        RxView.clicks(this.mAvatar).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(UpdateProfileFragment$$Lambda$6.lambdaFactory$(this));
    }

    static /* synthetic */ Boolean m1425a(TextViewAfterTextChangeEvent event) throws Exception {
        return Boolean.valueOf(event.editable().length() > 0);
    }

    static /* synthetic */ Boolean m1427a(Integer integer) throws Exception {
        return Boolean.valueOf(integer.intValue() != -1);
    }

    /* synthetic */ void m1464a(Boolean aBoolean) throws Exception {
        this.mGenderChooseTips.setVisibility(aBoolean.booleanValue() ? 0 : 8);
    }

    /* synthetic */ void m1467b(Object aVoid) throws Exception {
        m1452f();
    }

    /* synthetic */ void m1465a(Object aVoid) throws Exception {
        this.f2267j = System.currentTimeMillis() + ".jpg";
        DialogHelper.INSTANCE.showImageChooser((BaseFragment) this, this.f2267j);
    }

    private void m1450e() {
        this.f2264g = getArguments();
        if (this.f2264g != null) {
            this.f2265h = this.f2264g.getInt("PARAM_KEY_USER_TYPE", 1);
            if (this.f2265h == 4) {
                this.f2262e = (WxToken) Parcels.unwrap(this.f2264g.getParcelable("PARAM_KEY_WXTOKEN"));
            } else if (this.f2265h == 8) {
                this.f2260c = (WbToken) Parcels.unwrap(this.f2264g.getParcelable("PARAM_KEY_WBTOKEN"));
            } else if (this.f2265h == 16) {
                this.f2268k = (FacebookUserInfo) Parcels.unwrap(this.f2264g.getParcelable("PARAM_KEY_FB_USER"));
                this.f2269l = this.f2264g.getString("PARAM_KEY_FBTOKEN");
            }
        }
    }

    private void m1452f() {
        int gender;
        String promotion = this.mPromotionCodeInput.getText().toString().trim();
        if (this.mGenderMale.isChecked()) {
            gender = 1;
        } else if (this.mGenderFemale.isChecked()) {
            gender = 2;
        } else {
            ToastUtil.getInstance().show(ChatApp.getInstance(), (int) C0376R.string.unselect_gender_error, 1);
            return;
        }
        String nick = this.mNickInput.getText().toString().trim();
        if (nick.length() <= 0) {
            this.mNickInput.requestFocusFromTouch();
            return;
        }
        switch (this.f2265h) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                m1431a(nick, gender, promotion);
                return;
            case swscale.SWS_CS_FCC /*4*/:
                m1436b(nick, gender, promotion);
                return;
            case swscale.SWS_X /*8*/:
                m1442c(nick, gender, promotion);
                return;
            case swscale.SWS_SRC_V_CHR_DROP_SHIFT /*16*/:
                m1447d(nick, gender, promotion);
                return;
            default:
                return;
        }
    }

    private void m1431a(String nick, int gender, String promo) {
        if (TextUtils.isEmpty(this.f2266i)) {
            this.f2266i = BuildConfig.VERSION_NAME;
        }
        getDataLayer().getUserManager().signUpAciton(this.f2264g.getString("PARAM_KEY_PHONE"), this.f2264g.getInt("PARAM_KEY_COUNTRYCODE"), this.f2264g.getString("PARAM_KEY_AUTHCODE"), this.f2266i, nick, gender).compose(SchedulersCompat.applyIoSchedulers()).compose(bindToLifecycle()).doOnNext(UpdateProfileFragment$$Lambda$7.lambdaFactory$(this, promo)).subscribe(new C04691(this));
    }

    /* synthetic */ void m1470d(String promo, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue() && !TextUtils.isEmpty(promo)) {
            getDataLayer().getUserManager().submitPromo(promo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(UpdateProfileFragment$$Lambda$20.lambdaFactory$(), UpdateProfileFragment$$Lambda$21.lambdaFactory$());
        }
    }

    static /* synthetic */ void m1446d(PromoResult promoResult) throws Exception {
    }

    private void m1436b(String nick, int gender, String promo) {
        getDataLayer().getUserManager().wechatRegisterAction(this.f2262e.getAccess_token(), this.f2262e.getOpenid(), this.f2262e.getUnionid(), this.f2263f.getHeadimgurl(), this.f2266i, nick, gender, this.f2263f.getProvince(), this.f2263f.getCity()).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).doOnNext(UpdateProfileFragment$$Lambda$8.lambdaFactory$(this, promo)).subscribe(new C04702(this));
    }

    /* synthetic */ void m1469c(String promo, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue() && !TextUtils.isEmpty(promo)) {
            getDataLayer().getUserManager().submitPromo(promo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(UpdateProfileFragment$$Lambda$18.lambdaFactory$(), UpdateProfileFragment$$Lambda$19.lambdaFactory$());
        }
    }

    static /* synthetic */ void m1441c(PromoResult promoResult) throws Exception {
    }

    private void m1442c(String nick, int gender, String promo) {
        if (this.f2261d == null) {
            ToastUtil.getInstance().show(ChatApp.getInstance(), (int) C0376R.string.failed_fetch_weibo_data, 1);
            m1203h().finish();
            return;
        }
        String genderString = "n";
        switch (gender) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                genderString = "m";
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                genderString = "f";
                break;
        }
        getDataLayer().getUserManager().sinaRegisterAction(this.f2260c.getAccessToken(), Long.valueOf(this.f2260c.getUid()).longValue(), this.f2266i, this.f2261d.f3481h, nick, genderString, String.valueOf(this.f2261d.f3478e), String.valueOf(this.f2261d.f3479f)).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).doOnNext(UpdateProfileFragment$$Lambda$9.lambdaFactory$(this, promo)).subscribe(new C04713(this));
    }

    /* synthetic */ void m1468b(String promo, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue() && !TextUtils.isEmpty(promo)) {
            getDataLayer().getUserManager().submitPromo(promo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(UpdateProfileFragment$$Lambda$16.lambdaFactory$(), UpdateProfileFragment$$Lambda$17.lambdaFactory$());
        }
    }

    static /* synthetic */ void m1435b(PromoResult promoResult) throws Exception {
    }

    private void m1447d(String nick, int gender, String promotion) {
        getDataLayer().getUserManager().facebookRegisterAction(this.f2269l, this.f2268k.id, this.f2268k.avatar, nick, gender).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).doOnNext(UpdateProfileFragment$$Lambda$10.lambdaFactory$(this, promotion)).subscribe(new C04724(this));
    }

    /* synthetic */ void m1466a(String promotion, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue() && !TextUtils.isEmpty(promotion)) {
            getDataLayer().getUserManager().submitPromo(promotion).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(UpdateProfileFragment$$Lambda$14.lambdaFactory$(), UpdateProfileFragment$$Lambda$15.lambdaFactory$());
        }
    }

    static /* synthetic */ void m1429a(PromoResult promoResult) throws Exception {
    }

    private void m1453g() {
        switch (this.f2265h) {
            case swscale.SWS_CS_FCC /*4*/:
                m1454j();
                return;
            case swscale.SWS_X /*8*/:
                m1455k();
                return;
            case swscale.SWS_SRC_V_CHR_DROP_SHIFT /*16*/:
                m1456l();
                return;
            default:
                return;
        }
    }

    private void m1454j() {
        getDataLayer().getWechatManager().userInfoRequest(this.f2262e.getAccess_token(), this.f2262e.getOpenid()).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).filter(UpdateProfileFragment$$Lambda$11.lambdaFactory$()).subscribe(UpdateProfileFragment$$Lambda$12.lambdaFactory$(this), UpdateProfileFragment$$Lambda$13.lambdaFactory$());
    }

    static /* synthetic */ boolean m1438b(WxUser wxUser) throws Exception {
        return wxUser != null;
    }

    /* synthetic */ void m1463a(WxUser wxUser) throws Exception {
        if (wxUser != null) {
            this.f2263f = wxUser;
            switch (this.f2263f.getSex()) {
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    this.mGenderMale.setChecked(true);
                    break;
                case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                    this.mGenderFemale.setChecked(true);
                    break;
            }
            this.mNickInput.setText(this.f2263f.getNickname());
            this.f2266i = this.f2263f.getHeadimgurl();
            FrescoUtil.setImageURI(this.mAvatarImage, this.f2263f.getHeadimgurl());
        }
    }

    static /* synthetic */ void m1432a(Throwable throwable) throws Exception {
    }

    private void m1455k() {
        WeiboServiceHelper.getInstance().getWeiboUser(m1203h(), new C04735(this));
    }

    private void m1456l() {
        this.mNickInput.setText(this.f2268k.userName);
        if (2 == this.f2268k.gender) {
            this.mGenderFemale.setChecked(true);
        } else {
            this.mGenderMale.setChecked(true);
        }
        this.f2266i = this.f2268k.avatar;
        FrescoUtil.setImageURI(this.mAvatarImage, this.f2268k.avatar);
        m1460p();
    }

    private void m1457m() {
        if (!TextUtils.isEmpty(this.f2266i)) {
            Bundle fragmentArgs = new Bundle();
            fragmentArgs.putString("PARAM_KEY_IMAGE_PATH", this.f2266i);
            m1201a(AvatarEditActivity.class, InputDeviceCompat.SOURCE_TOUCHSCREEN, fragmentArgs);
        }
    }

    private void m1458n() {
        if (this.mAvatarImage != null) {
            GenericDraweeHierarchy hierarchy = (GenericDraweeHierarchy) this.mAvatarImage.getHierarchy();
            if (hierarchy == null) {
                return;
            }
            if (TextUtils.isEmpty(this.f2266i)) {
                hierarchy.setBackgroundImage(new ColorDrawable(getResources().getColor(C0376R.color.avatarDefaultBackground)));
                this.mAvatarImage.setImageURI("res://" + getContext().getPackageName() + "/" + C0376R.mipmap.tiki_small_notification);
                return;
            }
            hierarchy.setBackgroundImage(new ColorDrawable(getResources().getColor(C0376R.color.transparent)));
            FrescoUtil.setImageURI(this.mAvatarImage, this.f2266i);
        }
    }

    private void m1459o() {
        m1200a(CallActivity.class);
        m1203h().finish();
    }

    private void m1460p() {
        if (!TextUtils.isEmpty(this.f2268k.avatar)) {
            LoadingDialog.startLoading(m1203h(), (int) C0376R.string.fetching_profile);
            Fresco.getImagePipeline().fetchEncodedImage(ImageRequest.fromUri(this.f2268k.avatar), this).subscribe(new C04746(this), new DefaultExecutorSupplier(2).forBackgroundTasks());
        }
    }

    public boolean handleBack() {
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == swscale.SWS_PRINT_INFO) {
            if (resultCode == -1) {
                this.f2266i = PhotoUtil.getPickedGalleryPath(getContext(), data);
                m1457m();
            }
        } else if (requestCode == FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
            if (resultCode == -1) {
                this.f2266i = PhotoUtil.getPickedCameraPath(this.f2267j);
                m1457m();
            }
        } else if (requestCode != InputDeviceCompat.SOURCE_TOUCHSCREEN) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode != 0 && resultCode == -1 && data != null && data.getExtras() != null) {
            this.f2266i = data.getExtras().getString("PARAM_KEY_IMAGE_PATH");
            m1458n();
        }
    }
}
