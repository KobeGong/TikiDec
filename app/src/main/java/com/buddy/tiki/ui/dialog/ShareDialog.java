package com.buddy.tiki.ui.dialog;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.widget.Toast;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.base.BusinessDomain;
import com.buddy.tiki.helper.WechatHelper;
import com.buddy.tiki.helper.WeiboServiceHelper;
import com.buddy.tiki.helper.WeiboServiceHelper.PublishCallback;
import com.buddy.tiki.helper.WeiboServiceHelper.WeiboAuthCallback;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.ui.dialog.base.BaseRxBottomSheetDialogFragment;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.wxapi.WXEntryActivity;
import com.geekint.flying.bitmap.tool.BitmapTool;
import com.jakewharton.rxbinding2.view.RxView;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.postproc;

public class ShareDialog extends BaseRxBottomSheetDialogFragment {
    private static final TikiLog f1990a = TikiLog.getInstance("ShareDialog");
    private AppCompatTextView f1991b;
    private AppCompatTextView f1992c;
    private AppCompatTextView f1993d;
    private AppCompatTextView f1994e;
    private AppCompatTextView f1995f;
    private String f1996g;
    private String f1997h;

    class C04561 implements WeiboAuthCallback {
        final /* synthetic */ ShareDialog f1988a;

        C04561(ShareDialog this$0) {
            this.f1988a = this$0;
        }

        public void onSuccess(String uid, String accessToken, long expiresTime) {
            this.f1988a.m1183i();
        }

        public void onFail(String error) {
        }
    }

    class C04572 implements PublishCallback<Boolean> {
        final /* synthetic */ ShareDialog f1989a;

        C04572(ShareDialog this$0) {
            this.f1989a = this$0;
        }

        public void onSuccess(Boolean result) {
            LoadingDialog.stopLoading();
            Toast.makeText(this.f1989a.getHoldingActivity(), C0376R.string.share_success, 0).show();
        }

        public void onFail(String error) {
            LoadingDialog.stopLoading();
            TikiLog b = ShareDialog.f1990a;
            if (TextUtils.isEmpty(error)) {
                error = BuildConfig.VERSION_NAME;
            }
            b.m263e(error);
        }
    }

    public static ShareDialog newInstance(String title, String url) {
        ShareDialog shareDialog = new ShareDialog();
        Bundle args = new Bundle();
        args.putString("PARAM_KEY_SHARE_TITLE", title);
        args.putString("PARAM_KEY_SHARE_URL", url);
        shareDialog.setArguments(args);
        return shareDialog;
    }

    protected void mo2183a(Bundle savedInstanceState) {
        m1177c();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(C0376R.layout.dialog_share);
        m1173a(dialog);
        m1178d();
        m1179e();
        return dialog;
    }

    protected int mo2182a() {
        return C0376R.style.ShareDialogStyle;
    }

    private void m1177c() {
        if (getArguments() != null) {
            this.f1996g = getArguments().getString("PARAM_KEY_SHARE_TITLE", "Tiki");
            this.f1997h = getArguments().getString("PARAM_KEY_SHARE_URL", BusinessDomain.f403a);
        }
    }

    private void m1173a(Dialog dialog) {
        this.f1991b = (AppCompatTextView) dialog.findViewById(C0376R.id.share_wechat);
        this.f1992c = (AppCompatTextView) dialog.findViewById(C0376R.id.share_moment);
        this.f1993d = (AppCompatTextView) dialog.findViewById(C0376R.id.share_weibo);
        this.f1994e = (AppCompatTextView) dialog.findViewById(C0376R.id.copy_link);
        this.f1995f = (AppCompatTextView) dialog.findViewById(C0376R.id.cancel_btn);
    }

    private void m1178d() {
        Drawable drawable;
        IWXAPI iwxapi = WechatHelper.getInstance().getIwxapi();
        if (iwxapi.isWXAppInstalled() && iwxapi.isWXAppSupportAPI()) {
            drawable = getResources().getDrawable(C0376R.drawable.icon_share_wechat);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            this.f1991b.setCompoundDrawablesRelative(null, drawable, null, null);
            drawable = getResources().getDrawable(C0376R.drawable.icon_share_moment);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            this.f1992c.setCompoundDrawablesRelative(null, drawable, null, null);
        } else {
            this.f1991b.setVisibility(8);
            this.f1992c.setVisibility(8);
        }
        drawable = getResources().getDrawable(C0376R.drawable.icon_share_weibo);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        this.f1993d.setCompoundDrawablesRelative(null, drawable, null, null);
        drawable = getResources().getDrawable(C0376R.drawable.icon_copy_link);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        this.f1994e.setCompoundDrawablesRelative(null, drawable, null, null);
    }

    private void m1179e() {
        RxView.clicks(this.f1991b).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ShareDialog$$Lambda$1.lambdaFactory$(this));
        RxView.clicks(this.f1992c).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ShareDialog$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.f1993d).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ShareDialog$$Lambda$3.lambdaFactory$(this));
        RxView.clicks(this.f1994e).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(ShareDialog$$Lambda$4.lambdaFactory$(this));
        RxView.clicks(this.f1995f).compose(bindToLifecycle()).subscribe(ShareDialog$$Lambda$5.lambdaFactory$(this));
    }

    /* synthetic */ void m1190e(Object aVoid) throws Exception {
        m1181g();
    }

    /* synthetic */ void m1189d(Object aVoid) throws Exception {
        m1182h();
    }

    /* synthetic */ void m1188c(Object aVoid) throws Exception {
        m1183i();
    }

    /* synthetic */ void m1187b(Object aVoid) throws Exception {
        m1180f();
    }

    /* synthetic */ void m1186a(Object aVoid) throws Exception {
        dismiss();
    }

    private void m1180f() {
        ((ClipboardManager) getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(this.f1996g, this.f1997h));
        ToastUtil.getInstance().show((int) C0376R.string.copy_link_success);
        dismiss();
    }

    private void m1181g() {
        m1175a(false);
        dismiss();
    }

    private void m1182h() {
        m1175a(true);
        dismiss();
    }

    private void m1183i() {
        if (WeiboServiceHelper.getInstance().isValidAccessToken()) {
            LoadingDialog.startLoading(getHoldingActivity());
            WeiboServiceHelper.getInstance().publish(this.f1996g + " " + this.f1997h, BitmapFactory.decodeResource(getResources(), C0376R.mipmap.ic_launch), new C04572(this));
            dismiss();
            return;
        }
        WeiboServiceHelper.getInstance().loginWeibo(getHoldingActivity(), new C04561(this));
    }

    private void m1175a(boolean isTimeline) {
        int i;
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = this.f1997h;
        WXMediaMessage mediaMessage = new WXMediaMessage(webpageObject);
        mediaMessage.title = this.f1996g;
        mediaMessage.thumbData = BitmapTool.bitmap2Bytes(BitmapFactory.decodeResource(getResources(), C0376R.mipmap.ic_launch), CompressFormat.PNG, 85);
        Req req = new Req();
        req.transaction = this.f1997h;
        req.message = mediaMessage;
        if (isTimeline) {
            i = 1;
        } else {
            i = 0;
        }
        req.scene = i;
        Intent intent = new Intent(getContext(), WXEntryActivity.class);
        intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
        new Bundle().putInt("PARAM_KEY_WX_OP_TYPE", 0);
        getHoldingActivity().startActivity(intent);
        WechatHelper.getInstance().getIwxapi().sendReq(req);
    }

    public void onDestroy() {
        super.onDestroy();
        WeiboServiceHelper.getInstance().reset();
    }
}
