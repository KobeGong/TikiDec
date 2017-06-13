package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class PermissionRequestFragment_ViewBinding implements Unbinder {
    private PermissionRequestFragment f2179b;

    @UiThread
    public PermissionRequestFragment_ViewBinding(PermissionRequestFragment target, View source) {
        this.f2179b = target;
        target.mMicroGroup = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.permission_micro_group, "field 'mMicroGroup'", LinearLayout.class);
        target.mCameraGroup = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.permission_camera_group, "field 'mCameraGroup'", LinearLayout.class);
        target.mLocationGroup = (LinearLayout) Utils.findRequiredViewAsType(source, C0376R.id.permission_location_group, "field 'mLocationGroup'", LinearLayout.class);
        target.mMicro = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.permission_micro, "field 'mMicro'", AppCompatImageView.class);
        target.mMicroState = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.permission_micro_state, "field 'mMicroState'", AppCompatTextView.class);
        target.mCamera = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.permission_camera, "field 'mCamera'", AppCompatImageView.class);
        target.mCameraState = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.permission_camera_state, "field 'mCameraState'", AppCompatTextView.class);
        target.mLocation = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.permission_location, "field 'mLocation'", AppCompatImageView.class);
        target.mLocationState = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.permission_location_state, "field 'mLocationState'", AppCompatTextView.class);
        target.mRequestAll = (AppCompatButton) Utils.findRequiredViewAsType(source, C0376R.id.permission_all, "field 'mRequestAll'", AppCompatButton.class);
    }

    @CallSuper
    public void unbind() {
        PermissionRequestFragment target = this.f2179b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2179b = null;
        target.mMicroGroup = null;
        target.mCameraGroup = null;
        target.mLocationGroup = null;
        target.mMicro = null;
        target.mMicroState = null;
        target.mCamera = null;
        target.mCameraState = null;
        target.mLocation = null;
        target.mLocationState = null;
        target.mRequestAll = null;
    }
}
