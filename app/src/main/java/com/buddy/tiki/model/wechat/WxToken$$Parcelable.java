package com.buddy.tiki.model.wechat;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class WxToken$$Parcelable implements Parcelable, ParcelWrapper<WxToken> {
    public static final Creator<WxToken$$Parcelable> CREATOR = new C04061();
    private WxToken wxToken$$0;

    /* compiled from: WxToken$$Parcelable */
    static class C04061 implements Creator<WxToken$$Parcelable> {
        C04061() {
        }

        public WxToken$$Parcelable createFromParcel(Parcel parcel$$2) {
            return new WxToken$$Parcelable(WxToken$$Parcelable.read(parcel$$2, new IdentityCollection()));
        }

        public WxToken$$Parcelable[] newArray(int size) {
            return new WxToken$$Parcelable[size];
        }
    }

    public WxToken$$Parcelable(WxToken wxToken$$2) {
        this.wxToken$$0 = wxToken$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.wxToken$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(WxToken wxToken$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0.getKey(wxToken$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        parcel$$1.writeInt(identityMap$$0.put(wxToken$$1));
        parcel$$1.writeString(wxToken$$1.access_token);
        parcel$$1.writeInt(wxToken$$1.errcode);
        parcel$$1.writeString(wxToken$$1.refresh_token);
        parcel$$1.writeString(wxToken$$1.unionid);
        parcel$$1.writeString(wxToken$$1.openid);
        parcel$$1.writeString(wxToken$$1.scope);
        parcel$$1.writeString(wxToken$$1.errmsg);
        parcel$$1.writeInt(wxToken$$1.expires_in);
    }

    public int describeContents() {
        return 0;
    }

    public WxToken getParcel() {
        return this.wxToken$$0;
    }

    public static WxToken read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            int reservation$$0 = identityMap$$1.reserve();
            WxToken wxToken$$4 = new WxToken();
            identityMap$$1.put(reservation$$0, wxToken$$4);
            wxToken$$4.access_token = parcel$$3.readString();
            wxToken$$4.errcode = parcel$$3.readInt();
            wxToken$$4.refresh_token = parcel$$3.readString();
            wxToken$$4.unionid = parcel$$3.readString();
            wxToken$$4.openid = parcel$$3.readString();
            wxToken$$4.scope = parcel$$3.readString();
            wxToken$$4.errmsg = parcel$$3.readString();
            wxToken$$4.expires_in = parcel$$3.readInt();
            WxToken wxToken$$3 = wxToken$$4;
            identityMap$$1.put(identity$$1, wxToken$$3);
            return wxToken$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (WxToken) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
