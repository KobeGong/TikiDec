package com.buddy.tiki.service;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.trace.InboxMessage;
import com.buddy.tiki.protocol.web.MessageApi;
import com.buddy.tiki.service.base.BaseManager;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunc;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunction;
import com.buddy.tiki.service.base.HttpRequestBody;
import io.reactivex.Completable;
import io.reactivex.Observable;
import java.util.List;

public class MessageManager extends BaseManager {
    private MessageApi f931d;

    protected void mo2110b() {
        this.f931d = (MessageApi) this.b.getServiceInstance(MessageApi.class);
    }

    public Observable<List<InboxMessage>> messageRequest(long maxTimestamp) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("maxTimestamp", Long.valueOf(maxTimestamp));
        return this.f931d.messageRequest(HttpRequestBody.getInstance().generateRequestParams(params, "12xhkfdLLLdjsM6YDOhwB")).map(new HttpResultFunc());
    }

    public Observable<String> videoMessageRequest(String videoId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("videoId", videoId);
        return this.f931d.videoMessageRequest(HttpRequestBody.getInstance().generateRequestParams(params, "22xhkfdLLLdjsM6YDOhcB")).map(new HttpResultFunc());
    }

    public Completable sendTextMessage(String toUid, String content) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("toUid", toUid);
        params.put("content", content);
        return this.f931d.sendTextMessage(HttpRequestBody.getInstance().generateRequestParams(params, "L34hkfdlopdjsM6YDOf5g")).flatMapCompletable(new HttpResultFunction());
    }
}
