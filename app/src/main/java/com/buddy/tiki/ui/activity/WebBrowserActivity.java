package com.buddy.tiki.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.OnClick;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.base.BusinessDomain;
import com.buddy.tiki.helper.SecurityHelper;
import com.buddy.tiki.helper.WeiboServiceHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.constant.ChannelKeys;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.service.payment.IabHelper;
import com.buddy.tiki.service.payment.IabHelper.IabAsyncInProgressException;
import com.buddy.tiki.service.payment.IabHelper.OnConsumeFinishedListener;
import com.buddy.tiki.service.payment.IabHelper.OnIabPurchaseFinishedListener;
import com.buddy.tiki.service.payment.IabResult;
import com.buddy.tiki.service.payment.Purchase;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.dialog.ConfirmDialog.Builder;
import com.buddy.tiki.ui.dialog.ShareDialog;
import com.buddy.tiki.util.RippleUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.jakewharton.rxbinding2.view.RxView;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class WebBrowserActivity extends BaseActivity {
    private static final TikiLog f1607a = TikiLog.getInstance("WebBrowserActivity");
    private ProgressDialog f1608b;
    private String f1609d;
    private byte[] f1610e;
    private LinkedList<String> f1611f;
    private IabHelper f1612g;
    private final OnConsumeFinishedListener f1613h = new C04471(this);
    private final OnIabPurchaseFinishedListener f1614i = new C04482(this);
    @BindView(2131820707)
    AppCompatTextView mBackButton;
    @BindView(2131820708)
    AppCompatTextView mCloseButton;
    @BindView(2131820710)
    AppCompatTextView mMoreAction;
    @BindView(2131820712)
    ProgressBar mProgressBar;
    @BindView(2131820709)
    AppCompatTextView mWebTitle;
    @BindView(2131820711)
    WebView mWebView;

    class C04471 implements OnConsumeFinishedListener {
        final /* synthetic */ WebBrowserActivity f1591a;

        C04471(WebBrowserActivity this$0) {
            this.f1591a = this$0;
        }

        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (this.f1591a.f1612g != null) {
                if (result.isSuccess()) {
                    this.f1591a.getDataLayer().getPaymentManager().googleRecharge(purchase.getOriginalJson(), purchase.getSignature()).compose(this.f1591a.bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(WebBrowserActivity$1$$Lambda$1.lambdaFactory$(this), WebBrowserActivity$1$$Lambda$2.lambdaFactory$(this));
                    return;
                }
                this.f1591a.m940a(this.f1591a.getString(C0376R.string.purchase_failed), result.getMessage());
                this.f1591a.m941a(false);
            }
        }

        /* synthetic */ void m932a(Boolean aBoolean) throws Exception {
            this.f1591a.m941a(false);
            if (aBoolean.booleanValue()) {
                ToastUtil.getInstance().show(this.f1591a, (int) C0376R.string.recharge_success);
                this.f1591a.mWebView.reload();
                return;
            }
            this.f1591a.m939a(this.f1591a.getString(C0376R.string.authenticity_verification_failed));
        }

        /* synthetic */ void m933a(Throwable throwable) throws Exception {
            this.f1591a.m941a(false);
        }
    }

    class C04482 implements OnIabPurchaseFinishedListener {
        final /* synthetic */ WebBrowserActivity f1592a;

        C04482(WebBrowserActivity this$0) {
            this.f1592a = this$0;
        }

        public void onIabPurchaseFinished(IabResult result, Purchase info) {
            if (this.f1592a.f1612g != null) {
                if (result.isFailure()) {
                    this.f1592a.m940a(this.f1592a.getString(C0376R.string.purchase_failed), result.getMessage());
                    this.f1592a.m941a(false);
                } else if (this.f1592a.m942a(info)) {
                    try {
                        this.f1592a.f1612g.consumeAsync(info, this.f1592a.f1613h);
                    } catch (IabAsyncInProgressException e) {
                        WebBrowserActivity.f1607a.m264e("consume fail", e);
                        this.f1592a.m941a(false);
                    }
                } else {
                    this.f1592a.m940a(this.f1592a.getString(C0376R.string.authenticity_verification_failed), "\u672c\u5730\u9a8c\u8bc1\u5931\u8d25");
                    this.f1592a.m941a(false);
                }
            }
        }
    }

    class C04493 extends WebChromeClient {
        final /* synthetic */ WebBrowserActivity f1593a;

        C04493(WebBrowserActivity this$0) {
            this.f1593a = this$0;
        }

        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            this.f1593a.mWebTitle.setText(title);
            this.f1593a.f1611f.addLast(title);
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }

        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                this.f1593a.mProgressBar.setVisibility(8);
            } else {
                this.f1593a.mProgressBar.setVisibility(0);
                this.f1593a.mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    class C04504 extends WebViewClient {
        final /* synthetic */ WebBrowserActivity f1594a;

        C04504(WebBrowserActivity this$0) {
            this.f1594a = this$0;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("http:") || url.startsWith("https:")) {
                return false;
            }
            if (url.startsWith("alipays") && url.lastIndexOf("package") != -1) {
                try {
                    this.f1594a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                } catch (Exception e) {
                    WebBrowserActivity.f1607a.m264e("open alipay ", e);
                }
            }
            return true;
        }

        public void onPageFinished(WebView view, String url) {
            if (view.canGoBack()) {
                this.f1594a.mBackButton.setText(C0376R.string.back);
            }
        }

        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }

    public class TikiOpen {
        final /* synthetic */ WebBrowserActivity f1606a;

        public TikiOpen(WebBrowserActivity this$0) {
            this.f1606a = this$0;
        }

        @JavascriptInterface
        public boolean isGoogleAvailable() {
            return this.f1606a.m956j();
        }

        /* synthetic */ void lambda$showAndroidPay$0(String payload) {
            this.f1606a.m946b(payload);
        }

        @JavascriptInterface
        public void showAndroidPay(String payload) {
            this.f1606a.runOnUiThread(WebBrowserActivity$TikiOpen$$Lambda$1.lambdaFactory$(this, payload));
        }

        /* synthetic */ void lambda$setRightButtonHidden$1(boolean show) {
            this.f1606a.mMoreAction.setVisibility(!show ? 0 : 8);
        }

        @JavascriptInterface
        public void setRightButtonHidden(boolean show) {
            this.f1606a.runOnUiThread(WebBrowserActivity$TikiOpen$$Lambda$2.lambdaFactory$(this, show));
        }

        /* synthetic */ void lambda$setRightButtonText$2(String text) {
            this.f1606a.mMoreAction.setText(text);
        }

        @JavascriptInterface
        public void setRightButtonText(String text) {
            this.f1606a.runOnUiThread(WebBrowserActivity$TikiOpen$$Lambda$3.lambdaFactory$(this, text));
        }

        @JavascriptInterface
        public void oauth(String method) {
            this.f1606a.getDataLayer().getUserManager().oauthAction("com.buddy.facechat").compose(this.f1606a.bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(WebBrowserActivity$TikiOpen$$Lambda$4.lambdaFactory$(this, method), WebBrowserActivity$TikiOpen$$Lambda$5.lambdaFactory$());
        }

        /* synthetic */ void lambda$oauth$3(String method, String s) throws Exception {
            this.f1606a.mWebView.loadUrl("javascript:" + method + "('" + s + "')");
        }

        @JavascriptInterface
        public void shareSocialNetWithTitleUrl(String title, String shareurl) {
            ShareDialog.newInstance(title, shareurl).show(this.f1606a.getSupportFragmentManager(), "ShareDialog");
        }

        @JavascriptInterface
        public void callUser(String uid) {
            if (!TextUtils.isEmpty(uid)) {
                Realm realm = Realm.getDefaultInstance();
                TikiUser tikiUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, uid).findFirst();
                if (tikiUser != null && tikiUser.isLoaded() && tikiUser.isValid()) {
                    startFriendCall(tikiUser.getUid(), tikiUser.getNick());
                } else {
                    this.f1606a.getDataLayer().getUserManager().userRequest(uid).compose(this.f1606a.bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(WebBrowserActivity$TikiOpen$$Lambda$6.lambdaFactory$(this), WebBrowserActivity$TikiOpen$$Lambda$7.lambdaFactory$());
                }
                realm.close();
            }
        }

        static /* synthetic */ void lambda$callUser$5(Throwable throwable) throws Exception {
        }

        private void startFriendCall(String uid, String nick) {
            Intent intent = new Intent();
            Bundle args = new Bundle();
            args.putBoolean("PARAM_KEY_IS_CALLER", true);
            args.putString("PARAM_KEY_UID", uid);
            args.putString("PARAM_KEY_NICK", nick);
            intent.setClass(this.f1606a, CallFriendActivity.class);
            intent.putExtra("PARAMS_KEY", args);
            this.f1606a.startActivity(intent);
        }

        private void startFriendCall(User user) {
            Intent intent = new Intent();
            Bundle args = new Bundle();
            args.putBoolean("PARAM_KEY_IS_CALLER", true);
            args.putString("PARAM_KEY_UID", user.getUid());
            args.putString("PARAM_KEY_NICK", user.getNick());
            intent.setClass(this.f1606a, CallFriendActivity.class);
            intent.putExtra("PARAMS_KEY", args);
            this.f1606a.startActivity(intent);
        }

        @JavascriptInterface
        public void setAdvanceOptionCommentCost(String options, String filterDisplayStr, String cost) {
            Intent intent = new Intent();
            intent.putExtra("PARAM_KEY_MATCH_OPTIONS_RESULT", options);
            intent.putExtra("PARAM_KEY_MATCH_OPTIONS_DISPLAY", filterDisplayStr);
            intent.putExtra("PARAM_KEY_MATCH_OPTIONS_COST", cost);
            this.f1606a.setResult(-1, intent);
            this.f1606a.finish();
        }
    }

    public static void launchWeb(@NonNull Context context, String url) {
        Intent intent = new Intent(context, WebBrowserActivity.class);
        Bundle args = new Bundle();
        args.putString("PARAM_KEY_WEB_URL", url);
        intent.putExtra("PARAMS_KEY", args);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(C0376R.anim.activity_bottom_in, C0376R.anim.activity_still);
        }
    }

    public static void launchWebForResult(@NonNull Activity activity, String url, int requetCode) {
        Intent intent = new Intent(activity, WebBrowserActivity.class);
        Bundle args = new Bundle();
        args.putString("PARAM_KEY_WEB_URL", url);
        intent.putExtra("PARAMS_KEY", args);
        activity.startActivityForResult(intent, requetCode);
        activity.overridePendingTransition(C0376R.anim.activity_bottom_in, C0376R.anim.activity_still);
    }

    protected int mo2115a() {
        return C0376R.layout.activity_browser;
    }

    protected void onActivityCreate(Bundle savedInstanceState) {
        m949d();
        m951e();
        m952f();
    }

    protected int mo2117b() {
        return 0;
    }

    private void m949d() {
        Bundle args = getArguments();
        if (args != null) {
            this.f1609d = args.getString("PARAM_KEY_WEB_URL", BusinessDomain.f403a);
        }
        this.f1611f = new LinkedList();
        if (m956j()) {
            this.f1612g = new IabHelper(this);
            this.f1612g.enableDebugLogging(false);
            this.f1612g.startSetup(WebBrowserActivity$$Lambda$1.lambdaFactory$(this));
        }
    }

    /* synthetic */ void m959a(IabResult result) {
        if (!result.isSuccess()) {
            this.f1612g = null;
            m940a(getString(C0376R.string.unavailable_google_service), BuildConfig.VERSION_NAME);
        } else if (this.f1612g != null) {
        }
    }

    private void m951e() {
        Drawable drawable = getResources().getDrawable(C0376R.mipmap.icon_back_black);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.mBackButton.setCompoundDrawablesRelative(drawable, null, null, null);
        }
        RippleUtil.setRippleBackground(this, this.mBackButton, this.mCloseButton, this.mMoreAction);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void m952f() {
        RxView.clicks(this.mMoreAction).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(WebBrowserActivity$$Lambda$2.lambdaFactory$(this));
        RxView.clicks(this.mBackButton).compose(bindToLifecycle()).subscribe(WebBrowserActivity$$Lambda$3.lambdaFactory$(this));
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setSaveFormData(false);
        settings.setSupportZoom(false);
        settings.setUserAgentString("tiki/1.2.11 language/" + Locale.getDefault().getLanguage());
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        this.mWebView.addJavascriptInterface(new TikiOpen(this), "TikiOpen");
        WebChromeClient webChromeClient = new C04493(this);
        WebViewClient webViewClient = new C04504(this);
        this.mWebView.setWebChromeClient(webChromeClient);
        this.mWebView.setWebViewClient(webViewClient);
        this.mWebView.loadUrl(this.f1609d);
    }

    /* synthetic */ void m962b(Object aVoid) throws Exception {
        m953g();
    }

    /* synthetic */ void m960a(Object aVoid) throws Exception {
        m954h();
    }

    @OnClick({2131820708})
    public void closeWeb() {
        finish();
    }

    private void m953g() {
        this.mWebView.loadUrl("javascript:TikiBridgeTopRightButtonClicked()");
    }

    private void m954h() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            if (this.f1611f.size() > 0) {
                this.f1611f.removeLast();
            }
            if (this.f1611f.size() > 0) {
                this.mWebTitle.setText((CharSequence) this.f1611f.getLast());
                return;
            }
            return;
        }
        finish();
    }

    public void onBackPressed() {
        m954h();
    }

    protected void onDestroy() {
        m955i();
        super.onDestroy();
        if (this.f1612g != null) {
            this.f1612g.disposeWhenFinished();
            this.f1612g = null;
        }
    }

    private void m955i() {
        this.f1611f.clear();
        if (this.mWebView != null) {
            this.mWebView.removeJavascriptInterface("TikiOpen");
            this.mWebView.removeAllViews();
            ((ViewGroup) this.mWebView.getParent()).removeView(this.mWebView);
            this.mWebView.setTag(null);
            this.mWebView.clearHistory();
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.f1612g == null || !this.f1612g.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            if (WeiboServiceHelper.getInstance().getSsoHandler() != null) {
                WeiboServiceHelper.getInstance().getSsoHandler().authorizeCallBack(requestCode, resultCode, data);
            }
        }
    }

    private void m941a(boolean show) {
        if (show) {
            if (this.f1608b == null) {
                this.f1608b = new ProgressDialog(this);
                this.f1608b.setIndeterminate(true);
                this.f1608b.setMessage(getResources().getString(C0376R.string.waiting));
            }
            this.f1608b.show();
        } else if (this.f1608b != null) {
            this.f1608b.dismiss();
        }
    }

    private void m940a(String message, String extras) {
        f1607a.m263e("**** purchase Error: " + extras);
        m939a(message);
    }

    private void m939a(String message) {
        new Builder(this).setMessage(message).setTitle((int) C0376R.string.error).setPositiveButton((int) C0376R.string.ok, WebBrowserActivity$$Lambda$4.lambdaFactory$()).show(getSupportFragmentManager(), "Alert");
    }

    static /* synthetic */ void m935a(DialogInterface dialog, int which) {
    }

    private boolean m956j() {
        return ChannelKeys.GOOGLE_MARKET.compareTo(BusinessDomain.f405c) == 0;
    }

    private boolean m942a(Purchase p) {
        return this.f1610e != null && Arrays.equals(this.f1610e, SecurityHelper.encrypt(this, p.getDeveloperPayload().getBytes(), ChannelKeys.GOOGLE_MARKET));
    }

    private void m946b(String productId) {
        if (TextUtils.isEmpty(productId)) {
            m940a(getString(C0376R.string.invalid_product_id), BuildConfig.VERSION_NAME);
        } else if (this.f1612g == null) {
            m940a(getString(C0376R.string.unavailable_google_service), BuildConfig.VERSION_NAME);
        } else {
            m941a(true);
            String payload = SecurityHelper.nextPayload();
            this.f1610e = SecurityHelper.encrypt(this, payload.getBytes(), ChannelKeys.GOOGLE_MARKET);
            try {
                this.f1612g.launchPurchaseFlow(this, productId + "_android", 1919, this.f1614i, payload);
            } catch (IabAsyncInProgressException e) {
                f1607a.m263e("Error launching purchase flow. Another async operation in progress.");
                m941a(false);
            }
        }
    }
}
