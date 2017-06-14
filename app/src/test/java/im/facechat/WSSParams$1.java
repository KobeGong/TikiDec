package im.facechat;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class WSSParams$1 implements Creator<WSSParams> {
    WSSParams$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m7867a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m7868a(i);
    }

    public WSSParams m7867a(Parcel parcel) {
        return new WSSParams(parcel);
    }

    public WSSParams[] m7868a(int i) {
        return new WSSParams[i];
    }
}
