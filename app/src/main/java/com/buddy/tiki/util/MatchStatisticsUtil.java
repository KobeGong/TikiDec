package com.buddy.tiki.util;

import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.im.VideoStatics;
import com.buddy.tiki.service.base.ACache;
import com.buddy.tiki.service.base.DataLayer;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class MatchStatisticsUtil {
    private static final TikiLog f2423a = TikiLog.getInstance(MatchStatisticsUtil.class.getSimpleName());

    public static synchronized void add(String touid, String roomId, long startTime, int callType, String passport) {
        synchronized (MatchStatisticsUtil.class) {
            if (!(TextUtils.isEmpty(touid) || TextUtils.isEmpty(roomId) || startTime <= 0)) {
                VideoStatics videoStatics = new VideoStatics();
                videoStatics.setTouid(touid);
                videoStatics.setRoomId(roomId);
                videoStatics.setStartTime(startTime);
                videoStatics.setEndTime(startTime);
                videoStatics.setCallType(callType);
                videoStatics.setUpspeed(0);
                videoStatics.setDownspeed(0);
                videoStatics.setRemindBandwidth(0);
                videoStatics.setSendloss(0.0f);
                videoStatics.setReceiveloss(0.0f);
                videoStatics.setElectric(0.0f);
                videoStatics.setCpu(0.0f);
                videoStatics.setLinkType(3);
                videoStatics.setReportStatus(0);
                videoStatics.setPassport(passport);
                videoStatics.setFinish(false);
                m1560a(videoStatics, true);
            }
        }
    }

    public static synchronized void update(VideoStatics videoStatics) {
        synchronized (MatchStatisticsUtil.class) {
            m1567b(videoStatics);
        }
    }

    public static synchronized void updateEndTime(String touid, String roomId, long endTime) {
        synchronized (MatchStatisticsUtil.class) {
            if (!TextUtils.isEmpty(touid) && !TextUtils.isEmpty(roomId) && endTime > 0) {
                JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                            if (item == null) {
                                continue;
                            } else if (touid.equals(item.getTouid()) && roomId.equals(item.getRoomId())) {
                                try {
                                    if (item.getStartTime() < endTime) {
                                        item.setEndTime(endTime);
                                        item.setFinish(false);
                                        jsonArray.put(i, JSON.toJSONString(item));
                                        ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
                                        break;
                                    }
                                } catch (JSONException e) {
                                }
                            }
                        } catch (JSONException e2) {
                        } catch (com.alibaba.fastjson.JSONException e3) {
                        } catch (ClassCastException e4) {
                        }
                    }
                }
            }
        }
    }

    private static void m1560a(VideoStatics videoStatics, boolean forceReplace) {
        if (videoStatics != null && !TextUtils.isEmpty(videoStatics.getTouid()) && !TextUtils.isEmpty(videoStatics.getRoomId())) {
            JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
            if (jsonArray == null) {
                jsonArray = new JSONArray();
                videoStatics.setReportStatus(0);
                jsonArray.put(JSON.toJSONString(videoStatics));
                ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
                return;
            }
            int i = 0;
            while (i < jsonArray.length()) {
                try {
                    VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                    if (item != null && videoStatics.getTouid().equals(item.getTouid()) && videoStatics.getRoomId().equals(item.getRoomId()) && forceReplace) {
                        try {
                            jsonArray.put(i, JSON.toJSONString(videoStatics));
                            break;
                        } catch (JSONException e) {
                        }
                    }
                } catch (JSONException e2) {
                } catch (com.alibaba.fastjson.JSONException e3) {
                } catch (ClassCastException e4) {
                }
                i++;
            }
            if (i == jsonArray.length()) {
                videoStatics.setReportStatus(0);
                jsonArray.put(JSON.toJSONString(videoStatics));
            }
            ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
        }
    }

    private static void m1567b(VideoStatics videoStatics) {
        f2423a.m261d("updateInternal");
        if (videoStatics != null && !TextUtils.isEmpty(videoStatics.getTouid()) && !TextUtils.isEmpty(videoStatics.getRoomId())) {
            JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                        if (item != null && videoStatics.getTouid().equals(item.getTouid()) && videoStatics.getRoomId().equals(item.getRoomId())) {
                            try {
                                f2423a.m261d("updateInternal: found: finish:" + item.isFinish() + " status:" + item.getReportStatus());
                                if (item.getReportStatus() == 0 && !item.isFinish()) {
                                    item.setEndTime(videoStatics.getEndTime());
                                    item.setUpspeed(videoStatics.getUpspeed());
                                    item.setDownspeed(videoStatics.getDownspeed());
                                    item.setRemindBandwidth(videoStatics.getRemindBandwidth());
                                    item.setCallType(videoStatics.getCallType());
                                    item.setLinkType(videoStatics.getLinkType());
                                    item.setSendloss(videoStatics.getSendloss());
                                    item.setReceiveloss(videoStatics.getReceiveloss());
                                    item.setElectric(videoStatics.getElectric());
                                    item.setCpu(videoStatics.getCpu());
                                    jsonArray.put(i, JSON.toJSONString(item));
                                    ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
                                    return;
                                }
                            } catch (JSONException e) {
                                f2423a.m264e("error occur when update", e);
                                return;
                            }
                        }
                    } catch (JSONException e2) {
                    } catch (com.alibaba.fastjson.JSONException e3) {
                    } catch (ClassCastException e4) {
                    }
                }
                f2423a.m261d("updateInternal: not found");
            }
        }
    }

    public static synchronized void remove(String touid, String roomId) {
        synchronized (MatchStatisticsUtil.class) {
            m1561a(touid, roomId);
        }
    }

    private static void m1561a(String touid, String roomId) {
        if (!TextUtils.isEmpty(touid) && !TextUtils.isEmpty(roomId)) {
            JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
            if (jsonArray != null && jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                        if (item != null && touid.equals(item.getTouid()) && roomId.equals(item.getRoomId())) {
                            if (VERSION.SDK_INT >= 19) {
                                jsonArray.remove(i);
                            } else {
                                jsonArray = m1556a(i, jsonArray);
                            }
                        }
                    } catch (JSONException e) {
                    } catch (com.alibaba.fastjson.JSONException e2) {
                    } catch (ClassCastException e3) {
                    }
                }
                ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
            }
        }
    }

    private static JSONArray m1556a(int index, JSONArray from) {
        if (index < 0 || from == null || index >= from.length()) {
            return from;
        }
        List<VideoStatics> objs = m1555a(from);
        if (objs == null || index >= objs.size()) {
            return null;
        }
        objs.remove(index);
        JSONArray ja = new JSONArray();
        for (VideoStatics obj : objs) {
            ja.put(JSON.toJSONString(obj));
        }
        return ja;
    }

    private static List<VideoStatics> m1555a(JSONArray ja) {
        if (ja == null) {
            return null;
        }
        int len = ja.length();
        List<VideoStatics> result = new ArrayList(len);
        for (int i = 0; i < len; i++) {
            VideoStatics obj = (VideoStatics) JSON.parseObject(ja.optString(i, null), VideoStatics.class);
            if (obj != null) {
                result.add(obj);
            }
        }
        return result;
    }

    public static synchronized void resetStatus() {
        synchronized (MatchStatisticsUtil.class) {
            m1557a();
        }
    }

    private static void m1557a() {
        JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                    if (item != null) {
                        try {
                            item.setReportStatus(0);
                            jsonArray.put(i, JSON.toJSONString(item));
                        } catch (JSONException e) {
                        }
                    }
                } catch (JSONException e2) {
                } catch (com.alibaba.fastjson.JSONException e3) {
                } catch (ClassCastException e4) {
                }
            }
            ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
        }
    }

    public static synchronized void removeReported() {
        synchronized (MatchStatisticsUtil.class) {
            m1566b();
        }
    }

    private static void m1566b() {
        JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
        if (jsonArray != null && jsonArray.length() != 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                    if (item != null && item.getReportStatus() == 2) {
                        if (VERSION.SDK_INT >= 19) {
                            jsonArray.remove(i);
                        } else {
                            jsonArray = m1556a(i, jsonArray);
                        }
                    }
                } catch (JSONException e) {
                } catch (com.alibaba.fastjson.JSONException e2) {
                } catch (ClassCastException e3) {
                }
            }
            ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
        }
    }

    public static synchronized List<VideoStatics> getAll() {
        List<VideoStatics> c;
        synchronized (MatchStatisticsUtil.class) {
            c = m1570c();
        }
        return c;
    }

    private static List<VideoStatics> m1570c() {
        JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }
        List<VideoStatics> result = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                if (item != null) {
                    result.add(item);
                }
            } catch (JSONException e) {
            } catch (com.alibaba.fastjson.JSONException e2) {
            } catch (ClassCastException e3) {
            }
        }
        return result;
    }

    public static synchronized void clearAll() {
        synchronized (MatchStatisticsUtil.class) {
            ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", new JSONArray());
        }
    }

    public static synchronized void finish(String touid, String roomId, long endTime, float cpu, float electric, boolean meHangup) {
        synchronized (MatchStatisticsUtil.class) {
            if (!TextUtils.isEmpty(touid) && !TextUtils.isEmpty(roomId)) {
                JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                            if (item == null) {
                                continue;
                            } else if (touid.equals(item.getTouid()) && roomId.equals(item.getRoomId())) {
                                try {
                                    if (item.getReportStatus() == 0 && !item.isFinish()) {
                                        item.setEndTime(endTime);
                                        item.setFinish(true);
                                        item.setElectric(electric);
                                        item.setCpu(cpu);
                                        item.setMeHangup(meHangup);
                                        jsonArray.put(i, JSON.toJSONString(item));
                                        ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
                                        break;
                                    }
                                } catch (JSONException e) {
                                }
                            }
                        } catch (JSONException e2) {
                        } catch (com.alibaba.fastjson.JSONException e3) {
                        } catch (ClassCastException e4) {
                        }
                    }
                }
            }
        }
    }

    public static synchronized void unfinish(String touid, String roomId) {
        synchronized (MatchStatisticsUtil.class) {
            if (!TextUtils.isEmpty(touid) && !TextUtils.isEmpty(roomId)) {
                JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                            if (item == null) {
                                continue;
                            } else if (touid.equals(item.getTouid()) && roomId.equals(item.getRoomId())) {
                                try {
                                    if (item.getReportStatus() == 0 && item.isFinish()) {
                                        item.setFinish(false);
                                        jsonArray.put(i, JSON.toJSONString(item));
                                        ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
                                        break;
                                    }
                                } catch (JSONException e) {
                                }
                            }
                        } catch (JSONException e2) {
                        } catch (com.alibaba.fastjson.JSONException e3) {
                        } catch (ClassCastException e4) {
                        }
                    }
                }
            }
        }
    }

    public static synchronized VideoStatics getFirst() {
        VideoStatics d;
        synchronized (MatchStatisticsUtil.class) {
            d = m1572d();
        }
        return d;
    }

    private static VideoStatics m1572d() {
        JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }
        try {
            return (VideoStatics) JSON.parseObject(jsonArray.getString(0), VideoStatics.class);
        } catch (JSONException e) {
            return null;
        } catch (com.alibaba.fastjson.JSONException e2) {
            return null;
        } catch (ClassCastException e3) {
            return null;
        }
    }

    public static synchronized VideoStatics get(String touid, String roomId) {
        VideoStatics b;
        synchronized (MatchStatisticsUtil.class) {
            b = m1565b(touid, roomId);
        }
        return b;
    }

    private static VideoStatics m1565b(String touid, String roomId) {
        if (!(TextUtils.isEmpty(touid) || TextUtils.isEmpty(roomId))) {
            JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
            if (jsonArray == null || jsonArray.length() == 0) {
                return null;
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                    if (item != null && touid.equals(item.getTouid()) && roomId.equals(item.getRoomId())) {
                        return item;
                    }
                } catch (JSONException e) {
                } catch (com.alibaba.fastjson.JSONException e2) {
                } catch (ClassCastException e3) {
                }
            }
        }
        return null;
    }

    public static synchronized void reportAll() {
        synchronized (MatchStatisticsUtil.class) {
            List<VideoStatics> items = m1570c();
            if (items != null && items.size() > 0) {
                f2423a.m261d("reportAll");
                Observable.fromIterable(items).subscribeOn(Schedulers.io()).map(MatchStatisticsUtil$$Lambda$1.lambdaFactory$()).subscribe(MatchStatisticsUtil$$Lambda$2.lambdaFactory$(), MatchStatisticsUtil$$Lambda$3.lambdaFactory$());
            }
        }
    }

    static /* synthetic */ void m1564a(Void aVoid) throws Exception {
    }

    public static synchronized void report(String touid, String roomId) {
        synchronized (MatchStatisticsUtil.class) {
            VideoStatics item = get(touid, roomId);
            if (item != null) {
                m1574e(item);
            }
        }
    }

    private static synchronized void m1562a(String touid, String roomId, int status) {
        synchronized (MatchStatisticsUtil.class) {
            m1568b(touid, roomId, status);
        }
    }

    private static void m1568b(String touid, String roomId, int status) {
        if ((status == 0 || status == 1 || status == 2) && !TextUtils.isEmpty(touid) && !TextUtils.isEmpty(roomId)) {
            JSONArray jsonArray = ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").getAsJSONArray("MATCH_STATISTICS");
            if (jsonArray != null && jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        VideoStatics item = (VideoStatics) JSON.parseObject(jsonArray.getString(i), VideoStatics.class);
                        if (item != null && touid.equals(item.getTouid()) && roomId.equals(item.getRoomId())) {
                            item.setReportStatus(status);
                            try {
                                jsonArray.put(i, JSON.toJSONString(item));
                            } catch (JSONException e) {
                            }
                        }
                    } catch (JSONException e2) {
                    } catch (com.alibaba.fastjson.JSONException e3) {
                    } catch (ClassCastException e4) {
                    }
                }
                ACache.get(ChatApp.getInstance(), "MATCH_STATISTICS").put("MATCH_STATISTICS", jsonArray);
            }
        }
    }

    private static int m1569c(String touid, String roomId) {
        VideoStatics item = m1565b(touid, roomId);
        if (item != null) {
            return item.getReportStatus();
        }
        return -1;
    }

    private static boolean m1571c(VideoStatics videoStatics) {
        if (videoStatics == null || TextUtils.isEmpty(videoStatics.getTouid()) || TextUtils.isEmpty(videoStatics.getRoomId()) || videoStatics.getEndTime() - videoStatics.getStartTime() < 0 || ((videoStatics.getCallType() != 2 && videoStatics.getCallType() != 1) || videoStatics.getUpspeed() < 0 || videoStatics.getDownspeed() < 0 || videoStatics.getRemindBandwidth() < 0 || ((videoStatics.getLinkType() != 3 && videoStatics.getLinkType() != 1 && videoStatics.getLinkType() != 2) || videoStatics.getSendloss() < 0.0f || videoStatics.getReceiveloss() < 0.0f || videoStatics.getElectric() < 0.0f || videoStatics.getCpu() < 0.0f))) {
            return false;
        }
        if (videoStatics.getReportStatus() != 0) {
            return false;
        }
        return true;
    }

    private static Observable<Boolean> m1573d(VideoStatics videoStatics) {
        if (!m1571c(videoStatics)) {
            return null;
        }
        int sampleLen = (int) ((videoStatics.getEndTime() - videoStatics.getStartTime()) / 1000);
        long upSpeed = videoStatics.getUpspeed();
        long downSpeed = videoStatics.getDownspeed();
        if (sampleLen > 0) {
            upSpeed /= (long) sampleLen;
            downSpeed /= (long) sampleLen;
        }
        f2423a.m261d("report:uid:" + videoStatics.getTouid() + " roomId:" + videoStatics.getRoomId());
        return DataLayer.getInstance().getChatManager().reportMatchCallAction(videoStatics.getRoomId(), videoStatics.getTouid(), (int) ((videoStatics.getEndTime() - videoStatics.getStartTime()) / 1000), upSpeed, downSpeed, videoStatics.getRemindBandwidth(), videoStatics.getCallType(), videoStatics.getLinkType(), videoStatics.getSendloss(), videoStatics.getReceiveloss(), videoStatics.getElectric(), videoStatics.getCpu(), videoStatics.getPassport(), videoStatics.isMeHangup()).subscribeOn(Schedulers.io());
    }

    private static Void m1574e(VideoStatics videoStatics) {
        if (videoStatics != null) {
            Observable<Boolean> observable = m1573d(videoStatics);
            if (observable != null) {
                observable.subscribe(MatchStatisticsUtil$$Lambda$4.lambdaFactory$(videoStatics), MatchStatisticsUtil$$Lambda$5.lambdaFactory$(videoStatics));
            }
        }
        return null;
    }

    static /* synthetic */ void m1558a(VideoStatics videoStatics, Boolean aBoolean) throws Exception {
        if (aBoolean.booleanValue()) {
            m1562a(videoStatics.getTouid(), videoStatics.getRoomId(), 2);
            remove(videoStatics.getTouid(), videoStatics.getRoomId());
            return;
        }
        m1568b(videoStatics.getTouid(), videoStatics.getRoomId(), 0);
    }

    static /* synthetic */ void m1559a(VideoStatics videoStatics, Throwable throwable) throws Exception {
        m1562a(videoStatics.getTouid(), videoStatics.getRoomId(), 0);
        f2423a.m264e("reportMatch", throwable);
    }

    public static synchronized int getStatus(String touid, String roomId) {
        int c;
        synchronized (MatchStatisticsUtil.class) {
            c = m1569c(touid, roomId);
        }
        return c;
    }

    public static void printAll() {
        f2423a.m261d("VS ALL:");
        List<VideoStatics> result = getAll();
        if (result != null) {
            for (VideoStatics v : result) {
                f2423a.m261d("  VS:" + JSON.toJSONString(v));
            }
        }
    }
}
