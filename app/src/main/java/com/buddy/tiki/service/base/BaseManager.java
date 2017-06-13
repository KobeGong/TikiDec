package com.buddy.tiki.service.base;

import android.os.SystemClock;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import com.buddy.tiki.helper.ErrorCodeHelper;
import com.buddy.tiki.model.base.HttpResult;
import com.buddy.tiki.model.exception.NetException;
import io.reactivex.CompletableSource;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.BehaviorSubject;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseManager {
    protected final ConcurrentHashMap<String, Pair<Long, Object>> f903a;
    protected HttpApi f904b = HttpApi.getInstance();
    protected ArrayMap<Class, BehaviorSubject> f905c;

    public static class HttpResultFunc<T> implements Function<HttpResult<T>, T> {
        public T apply(HttpResult<T> httpResult) throws Exception {
            if (httpResult.getCode() == 0) {
                return httpResult.getResult();
            }
            ErrorCodeHelper.getInstance().handleError(httpResult.getCode(), httpResult.getMsg());
            throw new NetException(httpResult.getCode(), httpResult.getMsg());
        }
    }

    public static class HttpResultFunction<T> implements Function<HttpResult<T>, CompletableSource> {
        public CompletableSource apply(HttpResult<T> httpResult) throws Exception {
            if (httpResult.getCode() == 0) {
                return BaseManager$HttpResultFunction$$Lambda$1.lambdaFactory$();
            }
            ErrorCodeHelper.getInstance().handleError(httpResult.getCode(), httpResult.getMsg());
            throw new NetException(httpResult.getCode(), httpResult.getMsg());
        }
    }

    protected abstract void mo2110b();

    public BaseManager() {
        mo2110b();
        this.f903a = new ConcurrentHashMap();
        this.f905c = new ArrayMap();
        mo2109a();
    }

    protected void mo2109a() {
    }

    protected long mo2112c() {
        return 5000;
    }

    protected boolean m274a(String type) {
        long time = SystemClock.elapsedRealtime();
        Pair<Long, Object> pair = (Pair) this.f903a.get(type);
        if (pair == null || time - ((Long) pair.first).longValue() > mo2112c()) {
            return false;
        }
        return true;
    }

    protected void m277d() {
        if (this.f903a != null) {
            this.f903a.clear();
        }
    }

    protected <T> void m273a(@NonNull BehaviorSubject<T> subject, @NonNull DisposableObserver<T> observer) {
        if (!observer.isDisposed()) {
            subject.subscribeWith(observer);
        }
    }

    @Nullable
    @CheckResult
    protected <T> BehaviorSubject<T> m271a(Class<T> cls) {
        return (BehaviorSubject) this.f905c.get(cls);
    }

    public <T> void addSubscription(@NonNull DisposableObserver<T> observer, Class<T> cls) {
        BehaviorSubject<T> subject = m271a((Class) cls);
        if (subject != null) {
            m273a(subject, observer);
        }
    }
}
