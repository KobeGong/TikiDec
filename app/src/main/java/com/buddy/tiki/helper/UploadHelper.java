package com.buddy.tiki.helper;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.buddy.tiki.model.user.UserChatMessage;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.util.FileUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UploadHelper {
    private final ConcurrentHashMap<String, UploadCallback> f757a;
    private final Handler f758b;

    private static class SingletonHolder {
        private static final UploadHelper f754a = new UploadHelper();
    }

    public static abstract class UploadCallback {
        private UploadStatus f755a = UploadStatus.INIT;

        public abstract void uploadProgress(String str, long j, long j2, float f, long j3);

        public UploadStatus getStatus() {
            return this.f755a;
        }

        public void onStart() {
        }
    }

    public enum UploadStatus {
        INIT,
        UPLOADING,
        FAIL,
        FINISH
    }

    private UploadHelper() {
        this.f757a = new ConcurrentHashMap();
        this.f758b = new Handler(Looper.getMainLooper());
    }

    public static UploadHelper getInstance() {
        return SingletonHolder.f754a;
    }

    public void registerCallback(String id, @NonNull UploadCallback callback) {
        this.f757a.put(id, callback);
    }

    public void unRegisterCallback(String id) {
        this.f757a.remove(id);
    }

    @Nullable
    public UploadCallback getCallback(String id) {
        return (UploadCallback) this.f757a.get(id);
    }

    public synchronized boolean isDownloading(@NonNull String id) {
        boolean z;
        UploadCallback uploadCallback = (UploadCallback) this.f757a.get(id);
        z = uploadCallback != null && uploadCallback.f755a == UploadStatus.UPLOADING;
        return z;
    }

    public UploadStatus getUploadStatus(@NonNull String id) {
        UploadCallback uploadCallback = (UploadCallback) this.f757a.get(id);
        return uploadCallback != null ? uploadCallback.f755a : UploadStatus.INIT;
    }

    public void uploadVideoMessage(@NonNull String picPath, @NonNull String videoPath, String tos, int diamonds, int timelen, @NonNull String id) {
        uploadVideoMessage(picPath, videoPath, new String[]{tos}, diamonds, timelen, new String[]{id}, false);
    }

    public void uploadVideoMessage(@NonNull byte[] pic, @NonNull byte[] video, String tos, int diamonds, int timelen, @NonNull String id) {
        uploadVideoMessage(pic, video, new String[]{tos}, diamonds, timelen, new String[]{id}, false);
    }

    public void uploadVideoMessage(@NonNull String picPath, @NonNull String videoPath, String[] tos, int diamonds, int timelen, @NonNull String[] ids, boolean isAllFriend) {
        Observable.defer(UploadHelper$$Lambda$1.lambdaFactory$(picPath, videoPath)).subscribeOn(Schedulers.io()).subscribe(UploadHelper$$Lambda$2.lambdaFactory$(this, tos, diamonds, timelen, ids, isAllFriend), UploadHelper$$Lambda$3.lambdaFactory$(this, ids));
    }

    static /* synthetic */ ObservableSource m165a(@NonNull String picPath, @NonNull String videoPath) throws Exception {
        byte[] pic = FileUtil.readBytes(picPath);
        byte[] video = FileUtil.readBytes(videoPath);
        if (pic == null || video == null) {
            return Observable.error(new NullPointerException("\u8bfb\u53d6\u5230\u5c01\u9762\u6216\u8005\u89c6\u9891\u6570\u636e\u4e3a\u7a7a"));
        }
        List<byte[]> list = new ArrayList(2);
        list.add(pic);
        list.add(video);
        return Observable.just(list);
    }

    /* synthetic */ void m175a(String[] tos, int diamonds, int timelen, @NonNull String[] ids, boolean isAllFriend, List bytes) throws Exception {
        uploadVideoMessage((byte[]) bytes.get(0), (byte[]) bytes.get(1), tos, diamonds, timelen, ids, isAllFriend);
    }

    /* synthetic */ void m176a(@NonNull String[] ids, Throwable throwable) throws Exception {
        LooperHandlerHelper.getInstance().getLooperHandler().post(UploadHelper$$Lambda$6.lambdaFactory$(ids));
        for (String id : ids) {
            UploadCallback callback = (UploadCallback) this.f757a.get(id);
            if (callback != null) {
                callback.f755a = UploadStatus.FAIL;
                this.f758b.post(UploadHelper$$Lambda$7.lambdaFactory$(callback, id));
            }
        }
    }

    static /* synthetic */ void m172a(@NonNull String[] ids) {
        Realm realm = Realm.getDefaultInstance();
        Transaction lambdaFactory$ = UploadHelper$$Lambda$8.lambdaFactory$(ids);
        realm.getClass();
        realm.executeTransactionAsync(lambdaFactory$, UploadHelper$$Lambda$9.lambdaFactory$(realm), UploadHelper$$Lambda$10.lambdaFactory$(realm));
    }

    static /* synthetic */ void m173a(@NonNull String[] ids, Realm innerRealm) {
        Iterator it = innerRealm.where(UserChatMessage.class).in("msgId", ids).findAll().iterator();
        while (it.hasNext()) {
            ((UserChatMessage) it.next()).setUploadState(3);
        }
    }

    public void uploadVideoMessage(@NonNull byte[] pic, @NonNull byte[] video, String[] tos, int diamonds, int timelen, @NonNull final String[] ids, boolean isAllFriend) {
        DataLayer.getInstance().getTikiResManager().uploadTempPic(pic, ids, 0, 10).subscribeOn(Schedulers.io()).flatMap(UploadHelper$$Lambda$4.lambdaFactory$(video, ids, tos, diamonds, timelen, isAllFriend)).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<String>(this) {
            final /* synthetic */ UploadHelper f753b;

            public void onComplete() {
            }

            public void onStart() {
                LooperHandlerHelper.getInstance().getLooperHandler().post(UploadHelper$1$$Lambda$1.lambdaFactory$(ids));
                for (String id : ids) {
                    UploadCallback uploadCallback = (UploadCallback) this.f753b.f757a.get(id);
                    if (uploadCallback != null) {
                        uploadCallback.f755a = UploadStatus.UPLOADING;
                        Handler b = this.f753b.f758b;
                        uploadCallback.getClass();
                        b.post(UploadHelper$1$$Lambda$2.lambdaFactory$(uploadCallback));
                    }
                }
            }

            static /* synthetic */ void m158b(@NonNull String[] ids) {
                Realm realm = Realm.getDefaultInstance();
                Transaction lambdaFactory$ = UploadHelper$1$$Lambda$13.lambdaFactory$(ids);
                realm.getClass();
                realm.executeTransactionAsync(lambdaFactory$, UploadHelper$1$$Lambda$14.lambdaFactory$(realm), UploadHelper$1$$Lambda$15.lambdaFactory$(realm));
            }

            static /* synthetic */ void m159b(@NonNull String[] ids, Realm innerRealm) {
                Iterator it = innerRealm.where(UserChatMessage.class).in("msgId", ids).findAll().iterator();
                while (it.hasNext()) {
                    ((UserChatMessage) it.next()).setUploadState(1);
                }
            }

            public void onError(Throwable e) {
                LooperHandlerHelper.getInstance().getLooperHandler().post(UploadHelper$1$$Lambda$3.lambdaFactory$(ids));
                for (String id : ids) {
                    UploadCallback uploadCallback = (UploadCallback) this.f753b.f757a.get(id);
                    if (uploadCallback != null) {
                        this.f753b.f757a.remove(id);
                        uploadCallback.f755a = UploadStatus.FAIL;
                        this.f753b.f758b.post(UploadHelper$1$$Lambda$4.lambdaFactory$(uploadCallback, id));
                    }
                }
            }

            static /* synthetic */ void m151a(@NonNull String[] ids) {
                Realm realm = Realm.getDefaultInstance();
                Transaction lambdaFactory$ = UploadHelper$1$$Lambda$10.lambdaFactory$(ids);
                realm.getClass();
                realm.executeTransactionAsync(lambdaFactory$, UploadHelper$1$$Lambda$11.lambdaFactory$(realm), UploadHelper$1$$Lambda$12.lambdaFactory$(realm));
            }

            static /* synthetic */ void m152a(@NonNull String[] ids, Realm innerRealm) {
                Iterator it = innerRealm.where(UserChatMessage.class).in("msgId", ids).findAll().iterator();
                while (it.hasNext()) {
                    ((UserChatMessage) it.next()).setUploadState(3);
                }
            }

            public void onNext(String s) {
                LooperHandlerHelper.getInstance().getLooperHandler().post(UploadHelper$1$$Lambda$5.lambdaFactory$(ids, s));
                for (String id : ids) {
                    UploadCallback uploadCallback = (UploadCallback) this.f753b.f757a.get(id);
                    if (uploadCallback != null) {
                        this.f753b.f757a.remove(id);
                        uploadCallback.f755a = UploadStatus.FINISH;
                        this.f753b.f758b.post(UploadHelper$1$$Lambda$6.lambdaFactory$(uploadCallback, id));
                    }
                }
            }

            static /* synthetic */ void m153a(@NonNull String[] ids, String s) {
                Realm realm = Realm.getDefaultInstance();
                Transaction lambdaFactory$ = UploadHelper$1$$Lambda$7.lambdaFactory$(ids, s);
                realm.getClass();
                realm.executeTransactionAsync(lambdaFactory$, UploadHelper$1$$Lambda$8.lambdaFactory$(realm), UploadHelper$1$$Lambda$9.lambdaFactory$(realm));
            }

            static /* synthetic */ void m154a(@NonNull String[] ids, String s, Realm innerRealm) {
                Iterator it = innerRealm.where(UserChatMessage.class).in("msgId", ids).findAll().iterator();
                while (it.hasNext()) {
                    UserChatMessage message = (UserChatMessage) it.next();
                    message.setUploadState(2);
                    message.setVideoId(s);
                }
            }
        });
    }
}
