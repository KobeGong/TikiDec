package com.buddy.tiki.model.im;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.buddy.tiki.model.user.User$$Parcelable;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class CallReceiveMessage$$Parcelable implements Parcelable, ParcelWrapper<CallReceiveMessage> {
    public static final Creator<CallReceiveMessage$$Parcelable> CREATOR = new C04011();
    private CallReceiveMessage callReceiveMessage$$0;

    /* compiled from: CallReceiveMessage$$Parcelable */
    static class C04011 implements Creator<CallReceiveMessage$$Parcelable> {
        C04011() {
        }

        public CallReceiveMessage$$Parcelable createFromParcel(Parcel parcel$$2) {
            return new CallReceiveMessage$$Parcelable(CallReceiveMessage$$Parcelable.read(parcel$$2, new IdentityCollection()));
        }

        public CallReceiveMessage$$Parcelable[] newArray(int size) {
            return new CallReceiveMessage$$Parcelable[size];
        }
    }

    public CallReceiveMessage$$Parcelable(CallReceiveMessage callReceiveMessage$$2) {
        this.callReceiveMessage$$0 = callReceiveMessage$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.callReceiveMessage$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(CallReceiveMessage callReceiveMessage$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int i = 1;
        int identity$$0 = identityMap$$0.getKey(callReceiveMessage$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        int i2;
        parcel$$1.writeInt(identityMap$$0.put(callReceiveMessage$$1));
        parcel$$1.writeString(callReceiveMessage$$1.callId);
        parcel$$1.writeString(callReceiveMessage$$1.turnUrl);
        parcel$$1.writeLong(callReceiveMessage$$1.expires);
        parcel$$1.writeInt(callReceiveMessage$$1.restful ? 1 : 0);
        if (callReceiveMessage$$1.ack) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel$$1.writeInt(i2);
        parcel$$1.writeString(callReceiveMessage$$1.turnUser);
        parcel$$1.writeString(callReceiveMessage$$1.turnSecKey);
        parcel$$1.writeInt(callReceiveMessage$$1.type);
        parcel$$1.writeString(callReceiveMessage$$1.roomId);
        parcel$$1.writeInt(callReceiveMessage$$1.quality);
        User$$Parcelable.write(callReceiveMessage$$1.friend, parcel$$1, flags$$0, identityMap$$0);
        parcel$$1.writeLong(callReceiveMessage$$1.ctime);
        if (!callReceiveMessage$$1.loadoff) {
            i = 0;
        }
        parcel$$1.writeInt(i);
        parcel$$1.writeLong(callReceiveMessage$$1.noIces);
    }

    public int describeContents() {
        return 0;
    }

    public CallReceiveMessage getParcel() {
        return this.callReceiveMessage$$0;
    }

    public static CallReceiveMessage read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        boolean z = true;
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            boolean z2;
            int reservation$$0 = identityMap$$1.reserve();
            CallReceiveMessage callReceiveMessage$$4 = new CallReceiveMessage();
            identityMap$$1.put(reservation$$0, callReceiveMessage$$4);
            callReceiveMessage$$4.callId = parcel$$3.readString();
            callReceiveMessage$$4.turnUrl = parcel$$3.readString();
            callReceiveMessage$$4.expires = parcel$$3.readLong();
            if (parcel$$3.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            callReceiveMessage$$4.restful = z2;
            if (parcel$$3.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            callReceiveMessage$$4.ack = z2;
            callReceiveMessage$$4.turnUser = parcel$$3.readString();
            callReceiveMessage$$4.turnSecKey = parcel$$3.readString();
            callReceiveMessage$$4.type = parcel$$3.readInt();
            callReceiveMessage$$4.roomId = parcel$$3.readString();
            callReceiveMessage$$4.quality = parcel$$3.readInt();
            callReceiveMessage$$4.friend = User$$Parcelable.read(parcel$$3, identityMap$$1);
            callReceiveMessage$$4.ctime = parcel$$3.readLong();
            if (parcel$$3.readInt() != 1) {
                z = false;
            }
            callReceiveMessage$$4.loadoff = z;
            callReceiveMessage$$4.noIces = parcel$$3.readLong();
            CallReceiveMessage callReceiveMessage$$3 = callReceiveMessage$$4;
            identityMap$$1.put(identity$$1, callReceiveMessage$$3);
            return callReceiveMessage$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (CallReceiveMessage) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
