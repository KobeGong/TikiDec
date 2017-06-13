package com.buddy.tiki.helper;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.constant.IReturnCode;
import com.buddy.tiki.service.TikiManager;
import com.buddy.tiki.util.ToastUtil;
import org.bytedeco.javacpp.avcodec.AVCodecContext;

public class ErrorCodeHelper {
    private final Handler f681a;

    private static class MsgRunnable implements Runnable {
        private String f678a;
        private int f679b;

        public MsgRunnable(String msg) {
            this.f678a = msg;
        }

        public MsgRunnable(int msgRes) {
            this.f679b = msgRes;
        }

        public void run() {
            if (this.f679b == 0) {
                ToastUtil.getInstance().show(this.f678a);
            } else {
                ToastUtil.getInstance().show(this.f679b);
            }
        }
    }

    private static class SingletonHolder {
        private static final ErrorCodeHelper f680a = new ErrorCodeHelper();
    }

    private ErrorCodeHelper() {
        this.f681a = new Handler(Looper.getMainLooper());
    }

    public static ErrorCodeHelper getInstance() {
        return SingletonHolder.f680a;
    }

    public void handleError(int errorCode, @StringRes int errorMsg) {
        this.f681a.post(new MsgRunnable(errorMsg));
    }

    public void handleError(int errorCode, String errorMsg) {
        switch (errorCode) {
            case AVCodecContext.FF_PROFILE_UNKNOWN /*-99*/:
                this.f681a.post(new MsgRunnable((int) C0376R.string.unknown_net_error));
                return;
            case IReturnCode.SESSION_TIMEOUT /*-94*/:
                TikiManager.getInstance().logout(false);
                this.f681a.post(new MsgRunnable((int) C0376R.string.kickout_msg));
                return;
            default:
                if (TextUtils.isEmpty(errorMsg)) {
                    this.f681a.post(new MsgRunnable((int) C0376R.string.unknown_net_error));
                    return;
                } else {
                    this.f681a.post(new MsgRunnable(errorMsg));
                    return;
                }
        }
    }
}
