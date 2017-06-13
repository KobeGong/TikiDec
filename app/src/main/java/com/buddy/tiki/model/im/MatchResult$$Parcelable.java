package com.buddy.tiki.model.im;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class MatchResult$$Parcelable implements Parcelable, ParcelWrapper<MatchResult> {
    public static final Creator<MatchResult$$Parcelable> CREATOR = new C04021();
    private MatchResult matchResult$$0;

    /* compiled from: MatchResult$$Parcelable */
    static class C04021 implements Creator<MatchResult$$Parcelable> {
        C04021() {
        }

        public MatchResult$$Parcelable createFromParcel(Parcel parcel$$2) {
            return new MatchResult$$Parcelable(MatchResult$$Parcelable.read(parcel$$2, new IdentityCollection()));
        }

        public MatchResult$$Parcelable[] newArray(int size) {
            return new MatchResult$$Parcelable[size];
        }
    }

    public MatchResult$$Parcelable(MatchResult matchResult$$2) {
        this.matchResult$$0 = matchResult$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.matchResult$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(MatchResult matchResult$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0.getKey(matchResult$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        parcel$$1.writeInt(identityMap$$0.put(matchResult$$1));
        parcel$$1.writeString(matchResult$$1.passport);
        parcel$$1.writeInt(matchResult$$1.onlines);
    }

    public int describeContents() {
        return 0;
    }

    public MatchResult getParcel() {
        return this.matchResult$$0;
    }

    public static MatchResult read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            int reservation$$0 = identityMap$$1.reserve();
            MatchResult matchResult$$4 = new MatchResult();
            identityMap$$1.put(reservation$$0, matchResult$$4);
            matchResult$$4.passport = parcel$$3.readString();
            matchResult$$4.onlines = parcel$$3.readInt();
            MatchResult matchResult$$3 = matchResult$$4;
            identityMap$$1.put(identity$$1, matchResult$$3);
            return matchResult$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (MatchResult) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
