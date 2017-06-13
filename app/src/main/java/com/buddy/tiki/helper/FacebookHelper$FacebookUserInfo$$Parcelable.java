package com.buddy.tiki.helper;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.buddy.tiki.helper.FacebookHelper.FacebookUserInfo;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class FacebookHelper$FacebookUserInfo$$Parcelable implements Parcelable, ParcelWrapper<FacebookUserInfo> {
    public static final Creator<FacebookHelper$FacebookUserInfo$$Parcelable> CREATOR = new C03851();
    private FacebookUserInfo facebookUserInfo$$0;

    /* compiled from: FacebookHelper$FacebookUserInfo$$Parcelable */
    static class C03851 implements Creator<FacebookHelper$FacebookUserInfo$$Parcelable> {
        C03851() {
        }

        public FacebookHelper$FacebookUserInfo$$Parcelable createFromParcel(Parcel parcel$$2) {
            return new FacebookHelper$FacebookUserInfo$$Parcelable(FacebookHelper$FacebookUserInfo$$Parcelable.read(parcel$$2, new IdentityCollection()));
        }

        public FacebookHelper$FacebookUserInfo$$Parcelable[] newArray(int size) {
            return new FacebookHelper$FacebookUserInfo$$Parcelable[size];
        }
    }

    public FacebookHelper$FacebookUserInfo$$Parcelable(FacebookUserInfo facebookUserInfo$$2) {
        this.facebookUserInfo$$0 = facebookUserInfo$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.facebookUserInfo$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(FacebookUserInfo facebookUserInfo$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0.getKey(facebookUserInfo$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        parcel$$1.writeInt(identityMap$$0.put(facebookUserInfo$$1));
        parcel$$1.writeString(facebookUserInfo$$1.firstName);
        parcel$$1.writeString(facebookUserInfo$$1.lastName);
        parcel$$1.writeInt(facebookUserInfo$$1.gender);
        parcel$$1.writeString(facebookUserInfo$$1.updateTime);
        parcel$$1.writeString(facebookUserInfo$$1.id);
        parcel$$1.writeString(facebookUserInfo$$1.avatar);
        parcel$$1.writeString(facebookUserInfo$$1.userName);
        parcel$$1.writeString(facebookUserInfo$$1.email);
    }

    public int describeContents() {
        return 0;
    }

    public FacebookUserInfo getParcel() {
        return this.facebookUserInfo$$0;
    }

    public static FacebookUserInfo read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            int reservation$$0 = identityMap$$1.reserve();
            FacebookUserInfo facebookUserInfo$$4 = new FacebookUserInfo();
            identityMap$$1.put(reservation$$0, facebookUserInfo$$4);
            facebookUserInfo$$4.firstName = parcel$$3.readString();
            facebookUserInfo$$4.lastName = parcel$$3.readString();
            facebookUserInfo$$4.gender = parcel$$3.readInt();
            facebookUserInfo$$4.updateTime = parcel$$3.readString();
            facebookUserInfo$$4.id = parcel$$3.readString();
            facebookUserInfo$$4.avatar = parcel$$3.readString();
            facebookUserInfo$$4.userName = parcel$$3.readString();
            facebookUserInfo$$4.email = parcel$$3.readString();
            FacebookUserInfo facebookUserInfo$$3 = facebookUserInfo$$4;
            identityMap$$1.put(identity$$1, facebookUserInfo$$3);
            return facebookUserInfo$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (FacebookUserInfo) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
