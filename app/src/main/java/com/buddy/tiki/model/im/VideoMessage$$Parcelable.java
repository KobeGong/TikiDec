package com.buddy.tiki.model.im;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.buddy.tiki.model.msg.ChatMsg$$PackageHelper;
import com.buddy.tiki.model.user.User$$Parcelable;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class VideoMessage$$Parcelable implements Parcelable, ParcelWrapper<VideoMessage> {
    public static final Creator<VideoMessage$$Parcelable> CREATOR = new C04031();
    private VideoMessage videoMessage$$0;

    /* compiled from: VideoMessage$$Parcelable */
    static class C04031 implements Creator<VideoMessage$$Parcelable> {
        C04031() {
        }

        public VideoMessage$$Parcelable createFromParcel(Parcel parcel$$2) {
            return new VideoMessage$$Parcelable(VideoMessage$$Parcelable.read(parcel$$2, new IdentityCollection()));
        }

        public VideoMessage$$Parcelable[] newArray(int size) {
            return new VideoMessage$$Parcelable[size];
        }
    }

    public VideoMessage$$Parcelable(VideoMessage videoMessage$$2) {
        this.videoMessage$$0 = videoMessage$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.videoMessage$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(VideoMessage videoMessage$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0.getKey(videoMessage$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        parcel$$1.writeInt(identityMap$$0.put(videoMessage$$1));
        parcel$$1.writeString(videoMessage$$1.cover);
        parcel$$1.writeInt(videoMessage$$1.tikiPrice);
        parcel$$1.writeInt(videoMessage$$1.timelen);
        User$$Parcelable.write(videoMessage$$1.from, parcel$$1, flags$$0, identityMap$$0);
        parcel$$1.writeString(videoMessage$$1.videoId);
        parcel$$1.writeInt(ChatMsg$$PackageHelper.accessChatMsg$FG$ack(videoMessage$$1) ? 1 : 0);
        parcel$$1.writeLong(ChatMsg$$PackageHelper.accessChatMsg$FG$ctime(videoMessage$$1));
        parcel$$1.writeString(ChatMsg$$PackageHelper.accessChatMsg$FG$id(videoMessage$$1));
        parcel$$1.writeInt(ChatMsg$$PackageHelper.accessChatMsg$FG$type(videoMessage$$1));
    }

    public int describeContents() {
        return 0;
    }

    public VideoMessage getParcel() {
        return this.videoMessage$$0;
    }

    public static VideoMessage read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        boolean z = true;
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            int reservation$$0 = identityMap$$1.reserve();
            VideoMessage videoMessage$$4 = new VideoMessage();
            identityMap$$1.put(reservation$$0, videoMessage$$4);
            videoMessage$$4.cover = parcel$$3.readString();
            videoMessage$$4.tikiPrice = parcel$$3.readInt();
            videoMessage$$4.timelen = parcel$$3.readInt();
            videoMessage$$4.from = User$$Parcelable.read(parcel$$3, identityMap$$1);
            videoMessage$$4.videoId = parcel$$3.readString();
            if (parcel$$3.readInt() != 1) {
                z = false;
            }
            ChatMsg$$PackageHelper.accessChatMsg$FS$ack(videoMessage$$4, z);
            ChatMsg$$PackageHelper.accessChatMsg$FS$ctime(videoMessage$$4, parcel$$3.readLong());
            ChatMsg$$PackageHelper.accessChatMsg$FS$id(videoMessage$$4, parcel$$3.readString());
            ChatMsg$$PackageHelper.accessChatMsg$FS$type(videoMessage$$4, parcel$$3.readInt());
            VideoMessage videoMessage$$3 = videoMessage$$4;
            identityMap$$1.put(identity$$1, videoMessage$$3);
            return videoMessage$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (VideoMessage) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
