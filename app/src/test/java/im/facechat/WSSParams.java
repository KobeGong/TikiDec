package im.facechat;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

@Deprecated
class WSSParams extends FCBaseParams implements Parcelable {
    public static final Creator<WSSParams> CREATOR = new 1();
    private String f9536a;
    private String f9537b;
    private String f9538c;
    private String f9539d;

    protected WSSParams(Parcel parcel) {
        super(parcel);
        this.f9536a = parcel.readString();
        this.f9537b = parcel.readString();
        this.f9538c = parcel.readString();
        this.f9539d = parcel.readString();
        this.appId = parcel.readString();
        this.aid = parcel.readString();
        this.imei = parcel.readString();
        this.cpu = parcel.readString();
        this.sr = parcel.readString();
        this.rs = parcel.readString();
        this.gpu = parcel.readString();
        this.db = parcel.readString();
        this.dm = parcel.readString();
    }

    public int describeContents() {
        return 1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f9536a);
        parcel.writeString(this.f9537b);
        parcel.writeString(this.f9538c);
        parcel.writeString(this.f9539d);
        parcel.writeString(this.appId);
        parcel.writeString(this.aid);
        parcel.writeString(this.imei);
        parcel.writeString(this.cpu);
        parcel.writeString(this.sr);
        parcel.writeString(this.rs);
        parcel.writeString(this.gpu);
        parcel.writeString(this.db);
        parcel.writeString(this.dm);
    }
}
