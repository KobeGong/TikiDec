package com.buddy.tiki.service;

import android.support.v4.util.ArrayMap;
import android.support.v7.recyclerview.BuildConfig;
import com.buddy.tiki.model.address.Address;
import com.buddy.tiki.model.im.CallReceiveMessage;
import com.buddy.tiki.model.im.MatchResult;
import com.buddy.tiki.protocol.web.ChatApi;
import com.buddy.tiki.service.base.BaseManager;
import com.buddy.tiki.service.base.BaseManager.HttpResultFunc;
import com.buddy.tiki.service.base.HttpRequestBody;
import io.reactivex.Observable;

public class ChatManager extends BaseManager {
    private ChatApi f910d;

    protected void mo2110b() {
        this.f910d = (ChatApi) this.b.getServiceInstance(ChatApi.class);
    }

    public Observable<CallReceiveMessage> acceptCallAction(String callId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("callId", callId);
        return this.f910d.acceptCallAction(HttpRequestBody.getInstance().generateRequestParams(params, "Vh1dKj459b4zAQcqVpfW")).map(new HttpResultFunc());
    }

    public Observable<Boolean> closeCallAction(String callId) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("callId", callId);
        return this.f910d.closeCallAction(HttpRequestBody.getInstance().generateRequestParams(params, "Vh1dKj459b4zAQcqVpfW")).map(new HttpResultFunc());
    }

    public Observable<Boolean> rejectCallAction(String touid) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("callId", touid);
        return this.f910d.rejectCallAction(HttpRequestBody.getInstance().generateRequestParams(params, "Vh1dKj459b4zAQcqVpfW")).map(new HttpResultFunc());
    }

    public Observable<String> requestCallAction(String touid) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("touid", touid);
        return this.f910d.requestCallAction(HttpRequestBody.getInstance().generateRequestParams(params, "mwwwmzQMCXvM65ILa03M")).map(new HttpResultFunc());
    }

    public Observable<Boolean> reportMatchCallAction(String roomId, String touid, int samplelen, long upspeed, long downspeed, long remindBandwidth, int callType, int linkType, float sendloss, float receiveloss, float electric, float cpu, String passport, boolean meHangup) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("roomId", roomId);
        params.put("touid", touid);
        params.put("samplelen", Integer.valueOf(samplelen));
        params.put("upspeed", Long.valueOf(upspeed));
        params.put("downspeed", Long.valueOf(downspeed));
        params.put("remindBandwidth", Long.valueOf(remindBandwidth));
        params.put("callType", Integer.valueOf(callType));
        params.put("linkType", Integer.valueOf(linkType));
        params.put("sendloss", Float.valueOf(sendloss));
        params.put("receiveloss", Float.valueOf(receiveloss));
        params.put("electric", Float.valueOf(electric));
        params.put("cpu", Float.valueOf(cpu));
        params.put("passport", passport);
        params.put("meHangup", Boolean.valueOf(meHangup));
        return this.f910d.reportMatchCallAction(HttpRequestBody.getInstance().generateRequestParams(params, "Vh1dKj459b4zAQcqVpfW")).map(new HttpResultFunc());
    }

    public Observable<MatchResult> matchAction(Address address, String options) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("passport", BuildConfig.VERSION_NAME);
        params.put("addr", address);
        params.put("options", options);
        params.put("newSDK", Boolean.valueOf(true));
        params.put("blured", Boolean.valueOf(true));
        return this.f910d.matchAction(HttpRequestBody.getInstance().generateRequestParams(params, "CSDYuS4WFInCCSYsfRcUc")).map(new HttpResultFunc());
    }

    public Observable<Boolean> unMatchAction() {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("passport", BuildConfig.VERSION_NAME);
        return this.f910d.unMatchAction(HttpRequestBody.getInstance().generateRequestParams(params, "6XYuS4WFInCCSYsf89Q")).map(new HttpResultFunc());
    }

    public Observable<String> sendVideoMessage(String videourl, String cover, String[] tos, int diamonds, int timelen, boolean isAllFriend) {
        ArrayMap<String, Object> params = new ArrayMap();
        StringBuilder sb = new StringBuilder();
        if (isAllFriend) {
            sb.append("friends");
        } else {
            sb.append(tos[0]);
            for (int i = 1; i < tos.length; i++) {
                sb.append(',');
                sb.append(tos[i]);
            }
        }
        params.put("videourl", videourl);
        params.put("cover", cover);
        params.put("tos", sb.toString());
        params.put("diamonds", Integer.valueOf(diamonds));
        params.put("timelen", Integer.valueOf(timelen));
        return this.f910d.sendVideoMessage(HttpRequestBody.getInstance().generateRequestParams(params, "CSDYuS4WFInCCSYsfRcUc")).map(new HttpResultFunc());
    }

    public Observable<Boolean> reportChatSession(String session, String touid, String roomId, int callType, boolean finished, int clockTcoin) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("session", session);
        params.put("touid", touid);
        params.put("callType", Integer.valueOf(callType));
        params.put("roomId", roomId);
        params.put("finished", Boolean.valueOf(finished));
        params.put("clockTcoin", Integer.valueOf(clockTcoin));
        return this.f910d.reportChatSession(HttpRequestBody.getInstance().generateRequestParams(params, "CSDYuS4WFInCCSYsfRcUc")).map(new HttpResultFunc());
    }

    public Observable<Boolean> rushAction(Address addr) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("addr", addr);
        return this.f910d.rushAction(HttpRequestBody.getInstance().generateRequestParams(params, "CSDYuS4WFInCCSYsfRcUc")).map(new HttpResultFunc());
    }

    public Observable<Boolean> setUberWorking(boolean working) {
        ArrayMap<String, Object> params = new ArrayMap();
        params.put("working", Boolean.valueOf(working));
        return this.f910d.setUberWorking(HttpRequestBody.getInstance().generateRequestParams(params, "xSDYuS4WFInC3SYsfRc6c")).map(new HttpResultFunc()).doOnNext(ChatManager$$Lambda$1.lambdaFactory$(working));
    }
}
