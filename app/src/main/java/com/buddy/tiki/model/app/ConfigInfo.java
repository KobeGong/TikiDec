package com.buddy.tiki.model.app;

import com.buddy.tiki.model.event.Notice;

public class ConfigInfo {
    private int blurTime;
    private boolean closelocalIce;
    private String exchangeStoreUrl;
    private String exploreH5Text;
    private String exploreH5Url;
    private String feedbackUrl;
    private String h5DiamondsUrl;
    private String h5TikisUrl;
    private boolean hideAuthcode;
    private boolean hideRndMatch;
    private String matchOptionsUrl;
    private MatchLimits mlimits;
    private Notice notice;
    private String patchurl;
    private int pornFirst;
    private int pornInterval;
    private String privacyUrl;
    private String rankingUrl;
    private boolean showAlipay;
    private boolean showApplePay;
    private boolean showSearch;
    private String stunUrl;
    private String tikiexp;
    private String triceUrl;
    private VersionInfo version;
    private String[] wsuris;

    public String getMatchOptionsUrl() {
        return this.matchOptionsUrl;
    }

    public void setMatchOptionsUrl(String matchOptionsUrl) {
        this.matchOptionsUrl = matchOptionsUrl;
    }

    public String getRankingUrl() {
        return this.rankingUrl;
    }

    public void setRankingUrl(String rankingUrl) {
        this.rankingUrl = rankingUrl;
    }

    public int getPornFirst() {
        return this.pornFirst;
    }

    public void setPornFirst(int pornFirst) {
        this.pornFirst = pornFirst;
    }

    public int getBlurTime() {
        return this.blurTime;
    }

    public void setBlurTime(int blurTime) {
        this.blurTime = blurTime;
    }

    public String getExploreH5Text() {
        return this.exploreH5Text;
    }

    public void setExploreH5Text(String exploreH5Text) {
        this.exploreH5Text = exploreH5Text;
    }

    public String getExploreH5Url() {
        return this.exploreH5Url;
    }

    public void setExploreH5Url(String exploreH5Url) {
        this.exploreH5Url = exploreH5Url;
    }

    public int getPornInterval() {
        return this.pornInterval;
    }

    public void setPornInterval(int pornInterval) {
        this.pornInterval = pornInterval;
    }

    public String getTikiexp() {
        return this.tikiexp;
    }

    public void setTikiexp(String tikiexp) {
        this.tikiexp = tikiexp;
    }

    public boolean isCloselocalIce() {
        return this.closelocalIce;
    }

    public void setCloselocalIce(boolean closelocalIce) {
        this.closelocalIce = closelocalIce;
    }

    public Notice getNotice() {
        return this.notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public String getH5DiamondsUrl() {
        return this.h5DiamondsUrl;
    }

    public void setH5DiamondsUrl(String h5DiamondsUrl) {
        this.h5DiamondsUrl = h5DiamondsUrl;
    }

    public String getH5TikisUrl() {
        return this.h5TikisUrl;
    }

    public void setH5TikisUrl(String h5TikisUrl) {
        this.h5TikisUrl = h5TikisUrl;
    }

    public boolean isHideAuthcode() {
        return this.hideAuthcode;
    }

    public void setHideAuthcode(boolean hideAuthcode) {
        this.hideAuthcode = hideAuthcode;
    }

    public String getPatchurl() {
        return this.patchurl;
    }

    public void setPatchurl(String patchurl) {
        this.patchurl = patchurl;
    }

    public String getPrivacyUrl() {
        return this.privacyUrl;
    }

    public void setPrivacyUrl(String privacyUrl) {
        this.privacyUrl = privacyUrl;
    }

    public boolean isShowAlipay() {
        return this.showAlipay;
    }

    public void setShowAlipay(boolean showAlipay) {
        this.showAlipay = showAlipay;
    }

    public boolean isShowApplePay() {
        return this.showApplePay;
    }

    public void setShowApplePay(boolean showApplePay) {
        this.showApplePay = showApplePay;
    }

    public String getStunUrl() {
        return this.stunUrl;
    }

    public void setStunUrl(String stunUrl) {
        this.stunUrl = stunUrl;
    }

    public VersionInfo getVersion() {
        return this.version;
    }

    public void setVersion(VersionInfo version) {
        this.version = version;
    }

    public String[] getWsuris() {
        return this.wsuris;
    }

    public void setWsuris(String[] wsuris) {
        this.wsuris = wsuris;
    }

    public MatchLimits getMlimits() {
        return this.mlimits;
    }

    public void setMlimits(MatchLimits mlimits) {
        this.mlimits = mlimits;
    }

    public String getFeedbackUrl() {
        return this.feedbackUrl;
    }

    public void setFeedbackUrl(String feedbackUrl) {
        this.feedbackUrl = feedbackUrl;
    }

    public boolean isHideRndMatch() {
        return this.hideRndMatch;
    }

    public void setHideRndMatch(boolean hideRndMatch) {
        this.hideRndMatch = hideRndMatch;
    }

    public boolean isShowSearch() {
        return this.showSearch;
    }

    public void setShowSearch(boolean showSearch) {
        this.showSearch = showSearch;
    }

    public String getTriceUrl() {
        return this.triceUrl;
    }

    public void setTriceUrl(String triceUrl) {
        this.triceUrl = triceUrl;
    }

    public String getExchangeStoreUrl() {
        return this.exchangeStoreUrl;
    }

    public void setExchangeStoreUrl(String exchangeStoreUrl) {
        this.exchangeStoreUrl = exchangeStoreUrl;
    }
}
