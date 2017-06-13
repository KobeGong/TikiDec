package com.buddy.tiki.service;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.protocol.web.PaymentApi;
import com.buddy.tiki.service.base.BaseManager;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunc;
import com.buddy.tiki.service.base.HttpRequestBody;
import io.reactivex.Observable;

public class PaymentManager extends BaseManager {
    private PaymentApi f932d;

    protected void mo2110b() {
        this.f932d = (PaymentApi) this.b.getServiceInstance(PaymentApi.class);
    }

    public Observable<Boolean> buyVideoMessage(String videoId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("videoId", videoId);
        return this.f932d.buyVideoMessage(HttpRequestBody.getInstance().generateRequestParams(params, "1245TOPTWEAKr99TYkjh")).map(new HttpResultFunc());
    }

    public Observable<Boolean> googleRecharge(String receipt, String signtrue) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("receipt", receipt);
        params.put("signtrue", signtrue);
        return this.f932d.googleRecharge(HttpRequestBody.getInstance().generateRequestParams(params, "I7Y6TOPTWEAKr99TYpl1")).map(new HttpResultFunc());
    }
}
