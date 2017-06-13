package com.buddy.tiki.model.user;

import android.support.annotation.Nullable;
import com.buddy.tiki.model.resource.Gift;
import io.realm.RealmObject;
import io.realm.UserChatMessageRealmProxyInterface;

public class UserChatMessage extends RealmObject implements UserChatMessageRealmProxyInterface {
    public static final int ACT_NO_RESPONSE = 5;
    public static final int ACT_TYPE_CANCEL = 1;
    public static final int ACT_TYPE_REJECT = 4;
    public static final int ACT_TYPE_SELF_CANCEL = 3;
    public static final int ACT_TYPE_SELF_REJECT = 2;
    public static final int ACT_VIDEO_CHAT = 6;
    public static final int MSG_TYPE_GIFT_RECEIVE = 8;
    public static final int MSG_TYPE_GIFT_SEND = 9;
    public static final int MSG_TYPE_HINT_RECEIVE = 1;
    public static final int MSG_TYPE_HINT_SEND = 2;
    public static final int MSG_TYPE_TEXT_RECEIVE = 6;
    public static final int MSG_TYPE_TEXT_SEND = 7;
    public static final int MSG_TYPE_TIME = 5;
    public static final int MSG_TYPE_VIDEO_RECEIVE = 3;
    public static final int MSG_TYPE_VIDEO_SEND = 4;
    public static final int MSG_VIDEO_STATE_FAIL = 3;
    public static final int MSG_VIDEO_STATE_SUCCESS = 2;
    public static final int MSG_VIDEO_STATE_UPLOADING = 1;
    private int actionType;
    private int coin;
    private int diamondNum;
    private long during;
    private String giftCover;
    private String giftId;
    private String giftMusic;
    private String giftName;
    private String giftSource;
    private boolean isRead;
    private String msgId;
    private String msgText;
    private int msgType;
    private boolean needPay;
    private boolean sendFailed;
    private boolean showTime;
    private int timeLen;
    private long timestamp;
    private String uid;
    private int uploadProgress;
    private int uploadState;
    private String url;
    private String urlMark;
    private boolean videoFail;
    private String videoId;
    private String videoPath;
    private String videoThumb;

    public int realmGet$actionType() {
        return this.actionType;
    }

    public int realmGet$coin() {
        return this.coin;
    }

    public int realmGet$diamondNum() {
        return this.diamondNum;
    }

    public long realmGet$during() {
        return this.during;
    }

    public String realmGet$giftCover() {
        return this.giftCover;
    }

    public String realmGet$giftId() {
        return this.giftId;
    }

    public String realmGet$giftMusic() {
        return this.giftMusic;
    }

    public String realmGet$giftName() {
        return this.giftName;
    }

    public String realmGet$giftSource() {
        return this.giftSource;
    }

    public boolean realmGet$isRead() {
        return this.isRead;
    }

    public String realmGet$msgId() {
        return this.msgId;
    }

    public String realmGet$msgText() {
        return this.msgText;
    }

    public int realmGet$msgType() {
        return this.msgType;
    }

    public boolean realmGet$needPay() {
        return this.needPay;
    }

    public boolean realmGet$sendFailed() {
        return this.sendFailed;
    }

    public int realmGet$timeLen() {
        return this.timeLen;
    }

    public long realmGet$timestamp() {
        return this.timestamp;
    }

    public String realmGet$uid() {
        return this.uid;
    }

    public int realmGet$uploadProgress() {
        return this.uploadProgress;
    }

    public int realmGet$uploadState() {
        return this.uploadState;
    }

    public String realmGet$url() {
        return this.url;
    }

    public String realmGet$urlMark() {
        return this.urlMark;
    }

    public boolean realmGet$videoFail() {
        return this.videoFail;
    }

    public String realmGet$videoId() {
        return this.videoId;
    }

    public String realmGet$videoPath() {
        return this.videoPath;
    }

    public String realmGet$videoThumb() {
        return this.videoThumb;
    }

    public void realmSet$actionType(int i) {
        this.actionType = i;
    }

    public void realmSet$coin(int i) {
        this.coin = i;
    }

    public void realmSet$diamondNum(int i) {
        this.diamondNum = i;
    }

    public void realmSet$during(long j) {
        this.during = j;
    }

    public void realmSet$giftCover(String str) {
        this.giftCover = str;
    }

    public void realmSet$giftId(String str) {
        this.giftId = str;
    }

    public void realmSet$giftMusic(String str) {
        this.giftMusic = str;
    }

    public void realmSet$giftName(String str) {
        this.giftName = str;
    }

    public void realmSet$giftSource(String str) {
        this.giftSource = str;
    }

    public void realmSet$isRead(boolean z) {
        this.isRead = z;
    }

    public void realmSet$msgId(String str) {
        this.msgId = str;
    }

    public void realmSet$msgText(String str) {
        this.msgText = str;
    }

    public void realmSet$msgType(int i) {
        this.msgType = i;
    }

    public void realmSet$needPay(boolean z) {
        this.needPay = z;
    }

    public void realmSet$sendFailed(boolean z) {
        this.sendFailed = z;
    }

    public void realmSet$timeLen(int i) {
        this.timeLen = i;
    }

    public void realmSet$timestamp(long j) {
        this.timestamp = j;
    }

    public void realmSet$uid(String str) {
        this.uid = str;
    }

    public void realmSet$uploadProgress(int i) {
        this.uploadProgress = i;
    }

    public void realmSet$uploadState(int i) {
        this.uploadState = i;
    }

    public void realmSet$url(String str) {
        this.url = str;
    }

    public void realmSet$urlMark(String str) {
        this.urlMark = str;
    }

    public void realmSet$videoFail(boolean z) {
        this.videoFail = z;
    }

    public void realmSet$videoId(String str) {
        this.videoId = str;
    }

    public void realmSet$videoPath(String str) {
        this.videoPath = str;
    }

    public void realmSet$videoThumb(String str) {
        this.videoThumb = str;
    }

    public UserChatMessage() {
        realmSet$msgType(0);
        realmSet$timestamp(0);
        realmSet$needPay(false);
        realmSet$during(0);
        realmSet$videoFail(false);
        realmSet$isRead(false);
        realmSet$coin(0);
        realmSet$uploadState(0);
        realmSet$uploadProgress(0);
        realmSet$diamondNum(0);
        realmSet$timeLen(0);
    }

    public boolean isSendFailed() {
        return realmGet$sendFailed();
    }

    public void setSendFailed(boolean sendFailed) {
        realmSet$sendFailed(sendFailed);
    }

    public String getGiftId() {
        return realmGet$giftId();
    }

    public void setGiftId(String giftId) {
        realmSet$giftId(giftId);
    }

    public String getGiftSource() {
        return realmGet$giftSource();
    }

    public void setGiftSource(String giftSource) {
        realmSet$giftSource(giftSource);
    }

    public String getGiftMusic() {
        return realmGet$giftMusic();
    }

    public void setGiftMusic(String giftMusic) {
        realmSet$giftMusic(giftMusic);
    }

    public String getGiftCover() {
        return realmGet$giftCover();
    }

    public void setGiftCover(String giftCover) {
        realmSet$giftCover(giftCover);
    }

    public String getGiftName() {
        return realmGet$giftName();
    }

    public void setGiftName(String giftName) {
        realmSet$giftName(giftName);
    }

    public String getUrl() {
        return realmGet$url();
    }

    public void setUrl(String url) {
        realmSet$url(url);
    }

    public String getUrlMark() {
        return realmGet$urlMark();
    }

    public void setUrlMark(String urlMark) {
        realmSet$urlMark(urlMark);
    }

    public int getDiamondNum() {
        return realmGet$diamondNum();
    }

    public void setDiamondNum(int diamondNum) {
        realmSet$diamondNum(diamondNum);
    }

    public int getTimeLen() {
        return realmGet$timeLen();
    }

    public void setTimeLen(int timeLen) {
        realmSet$timeLen(timeLen);
    }

    public String getVideoPath() {
        return realmGet$videoPath();
    }

    public void setVideoPath(String videoPath) {
        realmSet$videoPath(videoPath);
    }

    public int getUploadProgress() {
        return realmGet$uploadProgress();
    }

    public void setUploadProgress(int uploadProgress) {
        realmSet$uploadProgress(uploadProgress);
    }

    public int getUploadState() {
        return realmGet$uploadState();
    }

    public void setUploadState(int uploadState) {
        realmSet$uploadState(uploadState);
    }

    public boolean isShowTime() {
        return this.showTime;
    }

    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }

    public int getCoin() {
        return realmGet$coin();
    }

    public void setCoin(int coin) {
        realmSet$coin(coin);
    }

    public int getMsgType() {
        return realmGet$msgType();
    }

    public void setMsgType(int msgType) {
        realmSet$msgType(msgType);
    }

    public String getMsgText() {
        return realmGet$msgText();
    }

    public void setMsgText(String msgText) {
        realmSet$msgText(msgText);
    }

    public int getActionType() {
        return realmGet$actionType();
    }

    public void setActionType(int actionType) {
        realmSet$actionType(actionType);
    }

    public String getVideoThumb() {
        return realmGet$videoThumb();
    }

    public void setVideoThumb(String videoThumb) {
        realmSet$videoThumb(videoThumb);
    }

    public long getTimestamp() {
        return realmGet$timestamp();
    }

    public void setTimestamp(long timestamp) {
        realmSet$timestamp(timestamp);
    }

    public String getVideoId() {
        return realmGet$videoId();
    }

    public void setVideoId(String videoId) {
        realmSet$videoId(videoId);
    }

    public boolean isNeedPay() {
        return realmGet$needPay();
    }

    public void setNeedPay(boolean needPay) {
        realmSet$needPay(needPay);
    }

    public long getDuring() {
        return realmGet$during();
    }

    public void setDuring(long during) {
        realmSet$during(during);
    }

    public boolean isVideoFail() {
        return realmGet$videoFail();
    }

    public void setVideoFail(boolean videoFail) {
        realmSet$videoFail(videoFail);
    }

    public boolean isRead() {
        return realmGet$isRead();
    }

    public void setRead(boolean read) {
        realmSet$isRead(read);
    }

    public String getUid() {
        return realmGet$uid();
    }

    public void setUid(String uid) {
        realmSet$uid(uid);
    }

    public String getMsgId() {
        return realmGet$msgId();
    }

    public void setMsgId(String msgId) {
        realmSet$msgId(msgId);
    }

    public void setGift(@Nullable Gift gift) {
        if (gift != null) {
            setGiftCover(gift.getCover());
            setGiftId(gift.getId());
            setGiftMusic(gift.getMusic());
            setGiftName(gift.getName());
            setGiftSource(gift.getSource());
        }
    }
}
