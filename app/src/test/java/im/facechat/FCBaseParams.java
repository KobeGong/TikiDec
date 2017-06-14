package im.facechat;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import tv.danmaku.ijk.media.player.BuildConfig;

class FCBaseParams implements Parcelable {
    public static final Creator<FCBaseParams> CREATOR = new C16141();
    protected static final String dt = "2";
    protected String aid;
    protected String appId;
    protected String bi;
    protected String bmd5;
    protected String cpu;
    protected String db;
    protected String dm;
    protected String gpu;
    protected String imei;
    protected String rs;
    protected String sr;

    static class C16141 implements Creator<FCBaseParams> {
        C16141() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m7735a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m7736a(i);
        }

        public FCBaseParams m7735a(Parcel parcel) {
            return new FCBaseParams(parcel);
        }

        public FCBaseParams[] m7736a(int i) {
            return new FCBaseParams[i];
        }
    }

    protected FCBaseParams(Parcel parcel) {
        this.appId = parcel.readString();
        this.aid = parcel.readString();
        this.imei = parcel.readString();
        this.cpu = parcel.readString();
        this.sr = parcel.readString();
        this.rs = parcel.readString();
        this.gpu = parcel.readString();
        this.db = parcel.readString();
        this.dm = parcel.readString();
        this.bi = parcel.readString();
        this.bmd5 = parcel.readString();
    }

    String getAppId() {
        return this.appId;
    }

    void setAppId(String str) {
        this.appId = fixNull(str);
    }

    String getAid() {
        return this.aid;
    }

    void setAid(String str) {
        this.aid = fixNull(str);
    }

    String getImei() {
        return this.imei;
    }

    void setImei(String str) {
        this.imei = fixNull(str);
    }

    String getCpu() {
        return this.cpu;
    }

    void setCpu(String str) {
        this.cpu = fixNull(str);
    }

    String getSr() {
        return this.sr;
    }

    void setSr(String str) {
        this.sr = fixNull(str);
    }

    String getRs() {
        return this.rs;
    }

    void setRs(String str) {
        this.rs = fixNull(str);
    }

    String getGpu() {
        return BuildConfig.VERSION_NAME;
    }

    void setGpu(String str) {
        this.gpu = fixNull(str);
    }

    String getDb() {
        return this.db;
    }

    void setDb(String str) {
        this.db = fixNull(str);
    }

    String getDm() {
        return this.dm;
    }

    void setDm(String str) {
        this.dm = fixNull(str);
    }

    String getDt() {
        return dt;
    }

    String getBi() {
        return this.bi;
    }

    void setBi(String str) {
        this.bi = str;
    }

    String getBmd5() {
        return this.bmd5;
    }

    void setBmd5(String str) {
        this.bmd5 = str;
    }

    protected String fixNull(@Nullable String str) {
        return str == null ? BuildConfig.VERSION_NAME : str.replace(" ", BuildConfig.VERSION_NAME);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.appId);
        parcel.writeString(this.aid);
        parcel.writeString(this.imei);
        parcel.writeString(this.cpu);
        parcel.writeString(this.sr);
        parcel.writeString(this.rs);
        parcel.writeString(this.gpu);
        parcel.writeString(this.db);
        parcel.writeString(this.dm);
        parcel.writeString(this.bi);
        parcel.writeString(this.bmd5);
    }
}
