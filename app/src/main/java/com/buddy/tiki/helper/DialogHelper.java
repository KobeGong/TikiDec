package com.buddy.tiki.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.VersionInfo;
import com.buddy.tiki.model.event.Notice;
import com.buddy.tiki.model.im.VideoMessage;
import com.buddy.tiki.model.promo.PromoResult;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.service.base.ACache;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.CallFriendActivity;
import com.buddy.tiki.ui.activity.base.ActivityManager;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.dialog.ConfirmDialog;
import com.buddy.tiki.ui.dialog.FacePageDialog;
import com.buddy.tiki.ui.dialog.KeyboardDialog;
import com.buddy.tiki.ui.dialog.MultiChoiceDialog.Builder;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.Priority;
import com.jakewharton.rxbinding2.view.RxView;
import io.realm.Realm;
import java.util.concurrent.TimeUnit;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public enum DialogHelper {
    INSTANCE;
    
    private static final TikiLog f656a = null;
    KeyboardDialog keyboardDialog;
    private int mBlockDialogShowing;
    private volatile FacePageDialog mFacePageDialog;

    static {
        f656a = TikiLog.getInstance("DialogHelper");
    }

    public void startVideoChat(@NonNull Context context, @NonNull TikiUser data) {
        String nick = data.getNick();
        if (data.isValid()) {
            Intent intent = new Intent();
            Bundle args = new Bundle();
            args.putBoolean("PARAM_KEY_IS_CALLER", true);
            args.putString("PARAM_KEY_UID", data.getUid());
            args.putString("PARAM_KEY_NICK", data.getNick());
            intent.setClass(ActivityManager.getInstance().currentActivity(), CallFriendActivity.class);
            intent.putExtra("PARAMS_KEY", args);
            ActivityManager.getInstance().currentActivity().startActivityForResult(intent, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            return;
        }
        m120a(context, nick);
    }

    public void showFriendDialog(@NonNull Context context, @NonNull TikiUser user) {
        String nickName = BuildConfig.VERSION_NAME;
        if (user.isValid() && user.getUnread() > 0) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            if (user.isValid()) {
                user.setUnread(0);
            }
            realm.commitTransaction();
            realm.close();
        }
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("FriendDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        View headerView = LayoutInflater.from(context).inflate(C0376R.layout.widget_friend_header, null, false);
        AppCompatTextView nick = (AppCompatTextView) headerView.findViewById(C0376R.id.nick);
        AppCompatTextView tikiNum = (AppCompatTextView) headerView.findViewById(C0376R.id.tiki_num);
        Drawable drawable = null;
        switch (user.getGender()) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                drawable = context.getResources().getDrawable(C0376R.mipmap.icon_male);
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                drawable = context.getResources().getDrawable(C0376R.mipmap.icon_female);
                break;
        }
        if (drawable != null) {
            drawable.setBounds(0, 0, DisplayUtil.dip2px(16.0f), DisplayUtil.dip2px(16.0f));
            nick.setCompoundDrawablesRelative(null, null, drawable, null);
        }
        nick.setText(user.getMark());
        tikiNum.setText(context.getString(C0376R.string.tiki_num_param, new Object[]{Long.valueOf(user.getTid())}));
        new Builder(context).setCustomTitle(headerView, new LayoutParams(-1, DisplayUtil.dip2px(120.0f))).setAllBoldExceptLast(true).setItems(C0376R.array.msgDialogActions, new int[]{0, 0, 0, 0}, DialogHelper$$Lambda$1.lambdaFactory$(this, user, context)).show(fragmentManager, "FriendDialog");
    }

    public void showChatMessageMenuDialog(@NonNull Context context, @NonNull TikiUser user) {
        String nickName = BuildConfig.VERSION_NAME;
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("MessageDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        View headerView = LayoutInflater.from(context).inflate(C0376R.layout.widget_msg_dialog_header, null, false);
        ((AppCompatTextView) headerView.findViewById(C0376R.id.nick)).setText(user.getMark());
        new Builder(context).setCustomTitle(headerView, new LayoutParams(-1, DisplayUtil.dip2px(60.0f))).setAllBoldExceptLast(true).setItems(C0376R.array.msgDialogActions, new int[]{0, 0, 0, 0}, DialogHelper$$Lambda$2.lambdaFactory$(this, user, context)).show(fragmentManager, "MessageDialog");
    }

    public void showComplainDialog(@NonNull Context context, @NonNull TikiUser user) {
        String nick = user.getNick();
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("ComplainDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        DataLayer.getInstance().getAppManager().getOperInfoCache().compose(SchedulersCompat.applyIoSchedulers()).subscribe(DialogHelper$$Lambda$3.lambdaFactory$(this, context, user, nick, fragmentManager), DialogHelper$$Lambda$4.lambdaFactory$());
    }

    public void showComplainDialog(@NonNull Context context, @NonNull User user, Bitmap pic) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("ComplainDialog2");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        DataLayer.getInstance().getAppManager().getOperInfoCache().compose(SchedulersCompat.applyIoSchedulers()).subscribe(DialogHelper$$Lambda$5.lambdaFactory$(context, pic, user, fragmentManager), DialogHelper$$Lambda$6.lambdaFactory$());
    }

    public void showComplainDialog(@NonNull Context context, @NonNull VideoMessage videoMessage) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("ComplainDialog3");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        DataLayer.getInstance().getAppManager().getOperInfoCache().compose(SchedulersCompat.applyIoSchedulers()).subscribe(DialogHelper$$Lambda$7.lambdaFactory$(context, videoMessage, fragmentManager), DialogHelper$$Lambda$8.lambdaFactory$());
    }

    public void showEditNickDialog(@NonNull Context context, @NonNull TikiUser user) {
        String nick = user.getNick();
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("EditNickDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        AppCompatEditText editText = new AppCompatEditText(context);
        editText.setMinimumWidth(context.getResources().getDimensionPixelSize(C0376R.dimen.dialog_width_with_padding));
        editText.setTextSize(2, 18.0f);
        editText.setTextColor(context.getResources().getColor(C0376R.color.colorMinorText));
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.mark_friend).setView(editText, new LayoutParams(-1, -1)).setNegativeButton((int) C0376R.string.cancel, DialogHelper$$Lambda$9.lambdaFactory$()).setPositiveButton((int) C0376R.string.ok, DialogHelper$$Lambda$10.lambdaFactory$(this, editText, user, context, nick)).show(fragmentManager, "EditNickDialog");
    }

    public void showLogoutDialog(@NonNull Context context) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("LogoutDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.logout).setMessage((int) C0376R.string.logout_tips).setNegativeButton((int) C0376R.string.cancel, DialogHelper$$Lambda$11.lambdaFactory$()).setPositiveButton((int) C0376R.string.ok, DialogHelper$$Lambda$12.lambdaFactory$()).show(fragmentManager, "LogoutDialog");
    }

    public void showConfirmDeleteDialog(@NonNull Context context, @NonNull TikiUser user) {
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.delete_friend).setMessage((int) C0376R.string.delete_confirm_message).setNegativeButton((int) C0376R.string.cancel, DialogHelper$$Lambda$13.lambdaFactory$()).setPositiveButton((int) C0376R.string.ok, DialogHelper$$Lambda$14.lambdaFactory$(this, user, context, user.getNick())).show(((BaseActivity) context).getSupportFragmentManager(), "showConfirmDeleteDialog");
    }

    public void showConfirmDeleteDialogInMessage(@NonNull Context context, @NonNull TikiUser user) {
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.delete_friend).setMessage((int) C0376R.string.delete_confirm_message).setNegativeButton((int) C0376R.string.cancel, DialogHelper$$Lambda$15.lambdaFactory$()).setPositiveButton((int) C0376R.string.ok, DialogHelper$$Lambda$16.lambdaFactory$(this, user, context, user.getNick())).show(((BaseActivity) context).getSupportFragmentManager(), "showConfirmDeleteDialog");
    }

    public void showModifyGender(@NonNull Context context, @NonNull User user) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("ModifyGenderDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        View view = LayoutInflater.from(context).inflate(C0376R.layout.widget_modify_gender_message, null);
        AppCompatRadioButton mMaleButton = (AppCompatRadioButton) view.findViewById(C0376R.id.male_check);
        AppCompatRadioButton mFemaleButton = (AppCompatRadioButton) view.findViewById(C0376R.id.female_check);
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.modify_gender).setView(view, new LayoutParams(-1, -2)).setPositiveButton((int) C0376R.string.ok, DialogHelper$$Lambda$17.lambdaFactory$(mMaleButton, mFemaleButton, context, user)).setNegativeButton((int) C0376R.string.temp_not_change, DialogHelper$$Lambda$18.lambdaFactory$()).show(((BaseActivity) context).getSupportFragmentManager(), "ModifyGenderDialog");
        switch (user.getGender()) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                mMaleButton.setChecked(true);
                return;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                mFemaleButton.setChecked(true);
                return;
            default:
                mFemaleButton.setChecked(false);
                mMaleButton.setChecked(false);
                return;
        }
    }

    public void showLackBalanceDialog(@NonNull Context context, String url) {
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.lack_balance_title).setMessage((int) C0376R.string.lack_balance_msg).setPositiveButton((int) C0376R.string.recharge, DialogHelper$$Lambda$19.lambdaFactory$(context, url)).setNegativeButton((int) C0376R.string.cancel, DialogHelper$$Lambda$20.lambdaFactory$()).show(((BaseActivity) context).getSupportFragmentManager(), "LackBalanceDialog");
    }

    public void showPromotionDialog(@NonNull Context context, PromoResult promoResult) {
        new ConfirmDialog.Builder(context).setTitle(promoResult.getTitle()).setMessage(promoResult.getText()).setPositiveButton((int) C0376R.string.I_know, DialogHelper$$Lambda$21.lambdaFactory$()).show(((BaseActivity) context).getSupportFragmentManager(), "PromotionDialog");
    }

    public void showActivityPage(@NonNull Context context, @NonNull Notice notice, OnCancelListener listener) {
        if (ACache.get(context).getAsString(notice.getNoticeId()) != null) {
            listener.onCancel(null);
            return;
        }
        AlertDialog dialog = new AlertDialog.Builder(context, C0376R.style.Theme.AppCompat.Light.Dialog.Alert.Custom).setView((int) C0376R.layout.dialog_activity_page).setOnCancelListener(listener).show();
        SimpleDraweeView banner = (SimpleDraweeView) dialog.findViewById(C0376R.id.banner_img);
        AppCompatTextView title = (AppCompatTextView) dialog.findViewById(C0376R.id.banner_title);
        AppCompatTextView content = (AppCompatTextView) dialog.findViewById(C0376R.id.banner_content);
        AppCompatTextView detailButton = (AppCompatTextView) dialog.findViewById(C0376R.id.banner_detail);
        if (banner != null) {
            FrescoUtil.setImageURI(banner, Uri.parse(notice.getBanner()), Priority.HIGH, false);
        }
        if (title != null) {
            title.setText(notice.getTitle());
        }
        if (content != null) {
            content.setText(notice.getDesc());
        }
        if (!(detailButton == null || TextUtils.isEmpty(notice.getUrl()))) {
            detailButton.setVisibility(0);
            detailButton.setOnClickListener(DialogHelper$$Lambda$22.lambdaFactory$(context, notice));
        }
        ACache.get(context).put(notice.getNoticeId(), "Tiki");
    }

    public void showUpdateDialog(@NonNull Context context, VersionInfo versionInfo, OnClickListener listener) {
        AlertDialog dialog = new AlertDialog.Builder(context, C0376R.style.Theme.AppCompat.Light.Dialog.Alert.Custom).setView((int) C0376R.layout.dialog_update).setCancelable(!versionInfo.isForce()).show();
        AppCompatTextView content = (AppCompatTextView) dialog.findViewById(C0376R.id.update_content);
        AppCompatButton updateButton = (AppCompatButton) dialog.findViewById(C0376R.id.update_btn);
        if (content != null) {
            content.setText(versionInfo.getUpdateDesc());
        }
        if (updateButton != null) {
            updateButton.setOnClickListener(listener);
        }
    }

    public void showStopMatchDialog(@NonNull Context context, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener negativeListener, OnCancelListener cancelListener) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("StopMatchDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.hangup_msg_title).setMessage((int) C0376R.string.hangup_tips).setPositiveButton((int) C0376R.string.ok, confirmListener).setNegativeButton((int) C0376R.string.cancel, negativeListener).setOnCancelListener(cancelListener).show(fragmentManager, "StopMatchDialog");
    }

    public void showImageChooser(@NonNull BaseActivity baseActivity, String photoName) {
        new Builder(baseActivity).setTitle((int) C0376R.string.set_avatar).setItems(C0376R.array.avatarChooser, DialogHelper$$Lambda$23.lambdaFactory$(baseActivity, photoName)).setAllBoldExceptLast(true).show(baseActivity.getSupportFragmentManager(), "ImageChooseDialog");
    }

    public void showImageChooser(@NonNull BaseFragment fragment, String photoName) {
        new Builder(ActivityManager.getInstance().currentActivity()).setTitle((int) C0376R.string.set_avatar).setItems(C0376R.array.avatarChooser, DialogHelper$$Lambda$24.lambdaFactory$(fragment, photoName)).setAllBoldExceptLast(true).show(fragment.getFragmentManager(), "ImageChooseDialog");
    }

    public void showPassportEditDialog(@NonNull Context context, String currentPassport, Runnable confirmTask, Runnable cancelTask) {
        AlertDialog dialog = new AlertDialog.Builder(context, C0376R.style.PassportEditDialogStyle).setView((int) C0376R.layout.dialog_passport_edit).setCancelable(true).create();
        if (dialog != null) {
            dialog.show();
            LinearLayout rootLayout = (LinearLayout) dialog.findViewById(C0376R.id.root_layout);
            BaseActivity activity = (BaseActivity) ActivityManager.getInstance().currentActivity();
            if (!(rootLayout == null || activity == null)) {
                ViewGroup.LayoutParams layoutParams = rootLayout.getLayoutParams();
                layoutParams.width = DisplayUtil.getRawWidth(activity);
                layoutParams.height = DisplayUtil.getRawHeight(activity);
                rootLayout.setLayoutParams(layoutParams);
            }
            AppCompatImageView cancel = (AppCompatImageView) dialog.findViewById(C0376R.id.cancel);
            if (cancel != null) {
                RxView.clicks(cancel).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(DialogHelper$$Lambda$25.lambdaFactory$(dialog, cancelTask), DialogHelper$$Lambda$26.lambdaFactory$());
            }
            InputFilter[] filters = new InputFilter[]{new LengthFilter(20)};
            AppCompatEditText passport = (AppCompatEditText) dialog.findViewById(C0376R.id.passport);
            if (passport != null) {
                passport.setText(currentPassport);
                passport.setFilters(filters);
            }
            AppCompatTextView ok = (AppCompatTextView) dialog.findViewById(C0376R.id.ok);
            if (ok != null) {
                RxView.clicks(ok).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(DialogHelper$$Lambda$27.lambdaFactory$(this, passport, dialog, confirmTask, context), DialogHelper$$Lambda$28.lambdaFactory$());
            }
        }
    }

    public void showQuitPassportDialog(@NonNull Context context, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener negativeListener, OnCancelListener cancelListener) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("StopMatchDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.quit_passport_chat).setPositiveButton((int) C0376R.string.quit, confirmListener).setNegativeButton((int) C0376R.string.cancel, negativeListener).setOnCancelListener(cancelListener).show(fragmentManager, "QuitPassportDialog");
    }

    public void showReUploadVideoDialog(@NonNull Context context, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener negativeListener, OnCancelListener cancelListener) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("ReUploadVideoDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.video_reupload).setPositiveButton((int) C0376R.string.ok, confirmListener).setNegativeButton((int) C0376R.string.cancel, negativeListener).setOnCancelListener(cancelListener).show(fragmentManager, "ReUploadVideoDialog");
    }

    public void showReSendGiftDialog(@NonNull Context context, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener negativeListener, OnCancelListener cancelListener) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("ReSendGiftDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.gift_resend).setPositiveButton((int) C0376R.string.ok, confirmListener).setNegativeButton((int) C0376R.string.cancel, negativeListener).setOnCancelListener(cancelListener).show(fragmentManager, "ReSendGiftDialog");
    }

    public void showReSendTextDialog(@NonNull Context context, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener negativeListener, OnCancelListener cancelListener) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("ReSendTextDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.text_resend).setPositiveButton((int) C0376R.string.ok, confirmListener).setNegativeButton((int) C0376R.string.cancel, negativeListener).setOnCancelListener(cancelListener).show(fragmentManager, "ReSendTextDialog");
    }

    public void showForbiddenModifyGenderDialog(@NonNull Context context) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("ForbiddenDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.forbidden_modify_gender).setMessage((int) C0376R.string.unmodifiable_gender_message).setPositiveButton((int) C0376R.string.I_know, DialogHelper$$Lambda$29.lambdaFactory$()).show(fragmentManager, "ForbiddenDialog");
    }

    public void showFreeTimeOutDialog(@NonNull Context context, @NonNull String title, @NonNull String message) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("FreeTimeOutDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle(title).setMessage(message).setPositiveButton((int) C0376R.string.I_know, DialogHelper$$Lambda$30.lambdaFactory$()).show(fragmentManager, "FreeTimeOutDialog");
    }

    public void showTooManyFriendsDialog(@NonNull Context context) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("TooManyFriendsDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.too_many_friends).setMessage((int) C0376R.string.can_select_all).setPositiveButton((int) C0376R.string.I_know, DialogHelper$$Lambda$31.lambdaFactory$()).show(fragmentManager, "TooManyFriendsDialog");
    }

    public void showExplainDataUsageDialog(@NonNull Context context) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("DataUsageDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.explain_data_usage_title).setMessage((int) C0376R.string.explain_data_usage_msg).setPositiveButton((int) C0376R.string.I_know, DialogHelper$$Lambda$32.lambdaFactory$()).show(fragmentManager, "DataUsageDialog");
    }

    public void showLackInFilterDialog(@NonNull Context context, DialogInterface.OnClickListener positiveListener, OnCancelListener cancelListener, DialogInterface.OnClickListener negativeListener) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("LackDialog");
        if (fragment != null) {
            this.mBlockDialogShowing--;
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.lack_diamond_title).setMessage((int) C0376R.string.lack_diamond_msg).setPositiveButton((int) C0376R.string.go_for_recharging, positiveListener).setNegativeButton((int) C0376R.string.cancel, negativeListener).setOnCancelListener(cancelListener).setOnDismissListener(DialogHelper$$Lambda$33.lambdaFactory$(this)).show(fragmentManager, "LackDialog");
        this.mBlockDialogShowing++;
    }

    public void showVideMessageDialog(@NonNull Context context, @NonNull VideoMessage videoMessage, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener negativeListener, OnCancelListener cancelListener) {
        FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("VideMessageDialog");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        new ConfirmDialog.Builder(context).setTitle("\u6536\u5230\u89c6\u9891\u6d88\u606f").setMessage("\u6765\u81ea\uff1a" + videoMessage.getFrom().getNick()).setPositiveButton((int) C0376R.string.ok, confirmListener).setNegativeButton((int) C0376R.string.cancel, negativeListener).setOnCancelListener(cancelListener).show(fragmentManager, "VideMessageDialog");
    }

    public void showFaceuDialog(@NonNull BaseActivity activity, OnDismissListener cancelListener) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        synchronized (this) {
            if (this.mFacePageDialog == null) {
                this.mFacePageDialog = new FacePageDialog();
            }
        }
        this.mFacePageDialog.setOnDismissListener(cancelListener);
        if (!this.mFacePageDialog.isAdded()) {
            this.mFacePageDialog.show(transaction, "FacePageDialog");
        }
    }

    public void showKeyboardDialog(@NonNull BaseActivity activity, OnCancelListener cancelListener) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        if (this.keyboardDialog == null) {
            this.keyboardDialog = new KeyboardDialog();
        }
        this.keyboardDialog.show(transaction, "FacePageDialog");
    }

    public void showPermissionMissDialog(AppCompatActivity context) {
        new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.error).setMessage((int) C0376R.string.permission_denied).setCancelable(false).setAutoDismiss(true).setPositiveButton((int) C0376R.string.goto_granted, DialogHelper$$Lambda$34.lambdaFactory$(context)).show(context.getSupportFragmentManager(), "DeniedPermissionDialog");
    }

    public void showAlertWindowPermission(Context context, DialogInterface.OnClickListener confirmListener, final DialogInterface.OnClickListener cancelListener) {
        if ((context instanceof BaseActivity) && PreferenceUtil.isShowFloatWindowPermission()) {
            FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag("AlertWindowPermissionDialog");
            if (fragment != null) {
                fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
            }
            new ConfirmDialog.Builder(context).setTitle((int) C0376R.string.error).setMessage((int) C0376R.string.permission_alert_window_denied).setCancelable(true).setAutoDismiss(true).setPositiveButton((int) C0376R.string.goto_granted, confirmListener).setNegativeButton((int) C0376R.string.cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DialogHelper f655b;

                public void onClick(DialogInterface dialog, int which) {
                    PreferenceUtil.setShowFloatWindowPermission(false);
                    if (cancelListener != null) {
                        cancelListener.onClick(dialog, which);
                    }
                }
            }).show(fragmentManager, "AlertWindowPermissionDialog");
        }
    }

    public void reset() {
        this.mFacePageDialog = null;
        resetIndex();
    }

    public boolean isBlockDialogShowing() {
        return this.mBlockDialogShowing > 0;
    }

    public void resetIndex() {
        this.mBlockDialogShowing = 0;
    }

    private boolean m121a(String passport) {
        if (TextUtils.isEmpty(passport)) {
            return true;
        }
        return passport.matches("^[[a-zA-Z0-9][\\u4e00-\\u9fa5]]+$");
    }

    private void m120a(@NonNull Context context, @NonNull String nick) {
        ToastUtil.getInstance().show(context.getString(C0376R.string.remote_delete_relation, new Object[]{nick}));
    }
}
