package com.buddy.tiki.ui.fragment;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.helper.LocationHelper;
import com.buddy.tiki.ui.dialog.base.BaseDialogFragment;
import com.buddy.tiki.util.PreferenceUtil;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PermissionRequestFragment extends BaseDialogFragment {
    private static final String[] f2175c = new String[]{"android.permission.RECORD_AUDIO", "android.permission.CAMERA", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.SYSTEM_ALERT_WINDOW"};
    private boolean f2176d = false;
    private boolean f2177e = false;
    private boolean f2178f = false;
    @BindView(2131820952)
    AppCompatImageView mCamera;
    @BindView(2131820951)
    LinearLayout mCameraGroup;
    @BindView(2131820953)
    AppCompatTextView mCameraState;
    @BindView(2131820955)
    AppCompatImageView mLocation;
    @BindView(2131820954)
    LinearLayout mLocationGroup;
    @BindView(2131820956)
    AppCompatTextView mLocationState;
    @BindView(2131820949)
    AppCompatImageView mMicro;
    @BindView(2131820948)
    LinearLayout mMicroGroup;
    @BindView(2131820950)
    AppCompatTextView mMicroState;
    @BindView(2131820957)
    AppCompatButton mRequestAll;

    protected int mo2172a() {
        return C0376R.layout.fragment_check_permission;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, C0376R.style.LargeDialogFragmentStyle);
    }

    protected void mo2173a(Bundle savedInstanceState) {
        m1352b();
        m1353c();
    }

    private void m1352b() {
        if (VERSION.SDK_INT < 23) {
            this.mRequestAll.setText(C0376R.string.welcome_expericience);
        }
    }

    private void m1353c() {
        setCancelable(false);
        if (VERSION.SDK_INT >= 23) {
            RxView.clicks(this.mRequestAll).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY)).compose(new RxPermissions(getActivity()).ensureEach(f2175c)).doOnNext(PermissionRequestFragment$$Lambda$1.lambdaFactory$(this)).subscribe(PermissionRequestFragment$$Lambda$2.lambdaFactory$(this));
        } else {
            m1354d();
        }
    }

    /* synthetic */ void m1359b(Permission permission) throws Exception {
        String str = permission.a;
        boolean z = true;
        switch (str.hashCode()) {
            case -63024214:
                if (str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    z = true;
                    break;
                }
                break;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    z = false;
                    break;
                }
                break;
            case 1831139720:
                if (str.equals("android.permission.RECORD_AUDIO")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                this.f2176d = permission.b;
                if (permission.b) {
                    this.mCamera.setImageResource(C0376R.drawable.icon_check);
                    this.mCameraState.setText(C0376R.string.permission_camera_granted);
                    this.mCameraGroup.setEnabled(false);
                    return;
                }
                return;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                this.f2177e = permission.b;
                if (permission.b) {
                    this.mMicro.setImageResource(C0376R.drawable.icon_check);
                    this.mMicroState.setText(C0376R.string.permission_micro_granted);
                    this.mMicroGroup.setEnabled(false);
                    return;
                }
                return;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                this.f2178f = permission.b;
                if (permission.b) {
                    this.mLocation.setImageResource(C0376R.drawable.icon_check);
                    this.mLocationState.setText(C0376R.string.permission_location_granted);
                    this.mLocationGroup.setEnabled(false);
                    LocationHelper.INSTANCE.requestLocation(getContext());
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* synthetic */ void m1357a(Permission permission1) throws Exception {
        if (this.f2176d && (this.f2177e & this.f2178f) != 0) {
            m1354d();
        }
    }

    private void m1354d() {
        this.mRequestAll.setText(C0376R.string.welcome_expericience);
        RxView.clicks(this.mRequestAll).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY)).subscribe(PermissionRequestFragment$$Lambda$3.lambdaFactory$(this));
    }

    /* synthetic */ void m1358a(Object aVoid) throws Exception {
        dismiss();
        PreferenceUtil.setShowPermission();
    }
}
