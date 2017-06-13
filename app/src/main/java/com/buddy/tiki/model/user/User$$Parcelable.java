package com.buddy.tiki.model.user;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class User$$Parcelable implements Parcelable, ParcelWrapper<User> {
    public static final Creator<User$$Parcelable> CREATOR = new C04051();
    private User user$$0;

    /* compiled from: User$$Parcelable */
    static class C04051 implements Creator<User$$Parcelable> {
        C04051() {
        }

        public User$$Parcelable createFromParcel(Parcel parcel$$2) {
            return new User$$Parcelable(User$$Parcelable.read(parcel$$2, new IdentityCollection()));
        }

        public User$$Parcelable[] newArray(int size) {
            return new User$$Parcelable[size];
        }
    }

    public User$$Parcelable(User user$$2) {
        this.user$$0 = user$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.user$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(User user$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int i = 1;
        int identity$$0 = identityMap$$0.getKey(user$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        int i2;
        parcel$$1.writeInt(identityMap$$0.put(user$$1));
        parcel$$1.writeLong(user$$1.diamonds);
        parcel$$1.writeString(user$$1.blockUrl);
        parcel$$1.writeString(user$$1.areaflag);
        parcel$$1.writeInt(user$$1.gender);
        parcel$$1.writeInt(user$$1.uber ? 1 : 0);
        parcel$$1.writeLong(user$$1.tid);
        parcel$$1.writeInt(user$$1.relation);
        parcel$$1.writeString(user$$1.nick);
        parcel$$1.writeString(user$$1.uid);
        if (user$$1.resetGender) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel$$1.writeInt(i2);
        parcel$$1.writeLong(user$$1.tikis);
        if (user$$1.blocked) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel$$1.writeInt(i2);
        parcel$$1.writeString(user$$1.areaName);
        parcel$$1.writeLong(user$$1.trice);
        parcel$$1.writeString(user$$1.inviteUrl);
        if (user$$1.vipSend) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel$$1.writeInt(i2);
        if (user$$1.working) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel$$1.writeInt(i2);
        parcel$$1.writeString(user$$1.blockText);
        parcel$$1.writeString(user$$1.area);
        parcel$$1.writeInt(user$$1.applys);
        parcel$$1.writeString(user$$1.weiboUrl);
        parcel$$1.writeString(user$$1.avatar);
        parcel$$1.writeInt(user$$1.friends);
        if (user$$1.needReport) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel$$1.writeInt(i2);
        parcel$$1.writeInt(user$$1.messages);
        if (!user$$1.oper) {
            i = 0;
        }
        parcel$$1.writeInt(i);
        parcel$$1.writeString(user$$1.mark);
    }

    public int describeContents() {
        return 0;
    }

    public User getParcel() {
        return this.user$$0;
    }

    public static User read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        boolean z = true;
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            boolean z2;
            int reservation$$0 = identityMap$$1.reserve();
            User user$$4 = new User();
            identityMap$$1.put(reservation$$0, user$$4);
            user$$4.diamonds = parcel$$3.readLong();
            user$$4.blockUrl = parcel$$3.readString();
            user$$4.areaflag = parcel$$3.readString();
            user$$4.gender = parcel$$3.readInt();
            if (parcel$$3.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            user$$4.uber = z2;
            user$$4.tid = parcel$$3.readLong();
            user$$4.relation = parcel$$3.readInt();
            user$$4.nick = parcel$$3.readString();
            user$$4.uid = parcel$$3.readString();
            if (parcel$$3.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            user$$4.resetGender = z2;
            user$$4.tikis = parcel$$3.readLong();
            if (parcel$$3.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            user$$4.blocked = z2;
            user$$4.areaName = parcel$$3.readString();
            user$$4.trice = parcel$$3.readLong();
            user$$4.inviteUrl = parcel$$3.readString();
            if (parcel$$3.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            user$$4.vipSend = z2;
            if (parcel$$3.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            user$$4.working = z2;
            user$$4.blockText = parcel$$3.readString();
            user$$4.area = parcel$$3.readString();
            user$$4.applys = parcel$$3.readInt();
            user$$4.weiboUrl = parcel$$3.readString();
            user$$4.avatar = parcel$$3.readString();
            user$$4.friends = parcel$$3.readInt();
            if (parcel$$3.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            user$$4.needReport = z2;
            user$$4.messages = parcel$$3.readInt();
            if (parcel$$3.readInt() != 1) {
                z = false;
            }
            user$$4.oper = z;
            user$$4.mark = parcel$$3.readString();
            User user$$3 = user$$4;
            identityMap$$1.put(identity$$1, user$$3);
            return user$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (User) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
