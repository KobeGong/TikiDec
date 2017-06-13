package com.buddy.tiki.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.event.UserEvent.WxTokenEvent;
import com.buddy.tiki.helper.WechatHelper;
import com.buddy.tiki.model.wechat.WxToken;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.dialog.LoadingDialog;
import com.buddy.tiki.util.ToastUtil;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.bytedeco.javacpp.avcodec.AVCodecContext;
import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private static final String f3751a = WXEntryActivity.class.getSimpleName();
    private IWXAPI f3752b;
    private WxToken f3753d;
    private int f3754e;

    class C05671 implements Observer<Boolean> {
        final /* synthetic */ WXEntryActivity f3750a;

        C05671(WXEntryActivity this$0) {
            this.f3750a = this$0;
        }

        public void onSubscribe(Disposable d) {
            LoadingDialog.startLoading(this.f3750a);
        }

        public void onComplete() {
        }

        public void onError(Throwable e) {
            LoadingDialog.stopLoading();
            this.f3750a.setResult(0);
            this.f3750a.finish();
        }

        public void onNext(Boolean aBoolean) {
            LoadingDialog.stopLoading();
            this.f3750a.m2311a(aBoolean);
        }
    }

    protected int mo2115a() {
        return C0376R.layout.activity_wx_entry;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m2312c();
        m2314e();
        m2313d();
    }

    protected int mo2117b() {
        return 0;
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        if (baseResp.errCode != 0) {
            if (this.f3754e == 1) {
                switch (baseResp.errCode) {
                    case -4:
                        ToastUtil.getInstance().show(ChatApp.getInstance(), (int) C0376R.string.authorization_reject, 1);
                        break;
                    case AVCodecContext.FF_COMPLIANCE_EXPERIMENTAL /*-2*/:
                        ToastUtil.getInstance().show(ChatApp.getInstance(), (int) C0376R.string.authorization_cancel, 1);
                        break;
                }
            }
            setResult(0);
            finish();
        } else if (this.f3754e == 1) {
            m2310a(baseResp);
        } else if (this.f3754e == 0) {
            ToastUtil.getInstance().show((int) C0376R.string.share_success);
            finish();
        } else {
            finish();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.f3752b.handleIntent(intent, this);
    }

    private void m2312c() {
        if (getArguments() != null) {
            this.f3754e = getArguments().getInt("PARAM_KEY_WX_OP_TYPE", 0);
        } else {
            this.f3754e = 0;
        }
    }

    private void m2313d() {
        if (this.f3754e == 1) {
            Req req = new Req();
            req.scope = "snsapi_userinfo";
            req.state = "tiki_wx_login";
            this.f3752b.sendReq(req);
        }
    }

    private void m2314e() {
        this.f3752b = WechatHelper.getInstance().getIwxapi();
    }

    private void m2310a(BaseResp resp) {
        getDataLayer().getWechatManager().accessTokenRequest("wxbc381cbe55588e97", "2750563ba17a11f384effb232e23c5f6", ((Resp) resp).code).subscribeOn(Schedulers.io()).compose(bindUntilEvent(ActivityEvent.DESTROY)).map(WXEntryActivity$$Lambda$1.lambdaFactory$(this)).flatMap(WXEntryActivity$$Lambda$2.lambdaFactory$(this)).observeOn(AndroidSchedulers.mainThread()).subscribe(new C05671(this));
    }

    /* synthetic */ WxToken m2319b(WxToken wxToken) throws Exception {
        if (TextUtils.isEmpty(wxToken.getErrmsg())) {
            this.f3753d = wxToken;
            return wxToken;
        }
        throw new IllegalStateException(wxToken.getErrmsg());
    }

    /* synthetic */ ObservableSource m2316a(WxToken token) throws Exception {
        return getDataLayer().getUserManager().wechatOauthAction(token.getAccess_token(), token.getOpenid(), token.getUnionid());
    }

    private void m2311a(Boolean isNewUser) {
        EventBus.getDefault().post(new WxTokenEvent(isNewUser.booleanValue(), this.f3753d));
        finish();
    }
}
