package com.buddy.tiki.model.user;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class TikiUser$$Parcelable implements Parcelable, ParcelWrapper<TikiUser> {
    public static final Creator<TikiUser$$Parcelable> CREATOR = new C04041();
    private TikiUser tikiUser$$0;

    /* compiled from: TikiUser$$Parcelable */
    static class C04041 implements Creator<TikiUser$$Parcelable> {
        C04041() {
        }

        public TikiUser$$Parcelable createFromParcel(Parcel parcel$$2) {
            return new TikiUser$$Parcelable(TikiUser$$Parcelable.read(parcel$$2, new IdentityCollection()));
        }

        public TikiUser$$Parcelable[] newArray(int size) {
            return new TikiUser$$Parcelable[size];
        }
    }

    public TikiUser$$Parcelable(TikiUser tikiUser$$2) {
        this.tikiUser$$0 = tikiUser$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.tikiUser$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(TikiUser tikiUser$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int i = 1;
        int identity$$0 = identityMap$$0.getKey(tikiUser$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        int i2;
        parcel$$1.writeInt(identityMap$$0.put(tikiUser$$1));
        parcel$$1.writeInt(tikiUser$$1.getGender());
        parcel$$1.writeInt(tikiUser$$1.getUnread());
        parcel$$1.writeString(tikiUser$$1.getLastMessage());
        parcel$$1.writeString(tikiUser$$1.getFirstPinYin());
        parcel$$1.writeLong(tikiUser$$1.getLastMessageTime());
        parcel$$1.writeString(tikiUser$$1.getAvatar());
        parcel$$1.writeLong(tikiUser$$1.getTid());
        parcel$$1.writeInt(tikiUser$$1.getRelation());
        parcel$$1.writeString(tikiUser$$1.getPinYin());
        parcel$$1.writeString(tikiUser$$1.getNick());
        parcel$$1.writeString(tikiUser$$1.getUid());
        if (tikiUser$$1.isShowPinyin()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel$$1.writeInt(i2);
        if (!tikiUser$$1.isOper()) {
            i = 0;
        }
        parcel$$1.writeInt(i);
        parcel$$1.writeString(tikiUser$$1.getMark());
    }

    public int describeContents() {
        return 0;
    }

    public TikiUser getParcel() {
        return this.tikiUser$$0;
    }

    public static TikiUser read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        boolean z = true;
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            boolean z2;
            int reservation$$0 = identityMap$$1.reserve();
            TikiUser tikiUser$$4 = new TikiUser();
            identityMap$$1.put(reservation$$0, tikiUser$$4);
            tikiUser$$4.setGender(parcel$$3.readInt());
            tikiUser$$4.setUnread(parcel$$3.readInt());
            tikiUser$$4.setLastMessage(parcel$$3.readString());
            tikiUser$$4.setFirstPinYin(parcel$$3.readString());
            tikiUser$$4.setLastMessageTime(parcel$$3.readLong());
            tikiUser$$4.setAvatar(parcel$$3.readString());
            tikiUser$$4.setTid(parcel$$3.readLong());
            tikiUser$$4.setRelation(parcel$$3.readInt());
            tikiUser$$4.setPinYin(parcel$$3.readString());
            tikiUser$$4.setNick(parcel$$3.readString());
            tikiUser$$4.setUid(parcel$$3.readString());
            if (parcel$$3.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            tikiUser$$4.setShowPinyin(z2);
            if (parcel$$3.readInt() != 1) {
                z = false;
            }
            tikiUser$$4.setOper(z);
            tikiUser$$4.setMark(parcel$$3.readString());
            TikiUser tikiUser$$3 = tikiUser$$4;
            identityMap$$1.put(identity$$1, tikiUser$$3);
            return tikiUser$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (TikiUser) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
