package com.buddy.tiki.model.weibo;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class WbToken$$Parcelable implements Parcelable, ParcelWrapper<WbToken> {
    public static final Creator<WbToken$$Parcelable> CREATOR = new C04071();
    private WbToken wbToken$$0;

    /* compiled from: WbToken$$Parcelable */
    static class C04071 implements Creator<WbToken$$Parcelable> {
        C04071() {
        }

        public WbToken$$Parcelable createFromParcel(Parcel parcel$$2) {
            return new WbToken$$Parcelable(WbToken$$Parcelable.read(parcel$$2, new IdentityCollection()));
        }

        public WbToken$$Parcelable[] newArray(int size) {
            return new WbToken$$Parcelable[size];
        }
    }

    public WbToken$$Parcelable(WbToken wbToken$$2) {
        this.wbToken$$0 = wbToken$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.wbToken$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(WbToken wbToken$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0.getKey(wbToken$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        parcel$$1.writeInt(identityMap$$0.put(wbToken$$1));
        parcel$$1.writeString(wbToken$$1.uid);
        parcel$$1.writeString(wbToken$$1.accessToken);
        parcel$$1.writeLong(wbToken$$1.expiresTime);
    }

    public int describeContents() {
        return 0;
    }

    public WbToken getParcel() {
        return this.wbToken$$0;
    }

    public static WbToken read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            int reservation$$0 = identityMap$$1.reserve();
            WbToken wbToken$$4 = new WbToken();
            identityMap$$1.put(reservation$$0, wbToken$$4);
            wbToken$$4.uid = parcel$$3.readString();
            wbToken$$4.accessToken = parcel$$3.readString();
            wbToken$$4.expiresTime = parcel$$3.readLong();
            WbToken wbToken$$3 = wbToken$$4;
            identityMap$$1.put(identity$$1, wbToken$$3);
            return wbToken$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (WbToken) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
