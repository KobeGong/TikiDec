package com.buddy.tiki.ui.activity.base;

import android.app.Activity;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.service.TikiManager;
import com.buddy.tiki.util.CPUUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class ActivityManager {
    public Stack<Activity> f1622a;
    private TikiLog f1623b;

    private static class SingletonHolder {
        private static final ActivityManager f1621a = new ActivityManager();
    }

    private ActivityManager() {
        this.f1622a = new Stack();
        this.f1623b = TikiLog.getInstance(ActivityManager.class.getSimpleName());
    }

    public static ActivityManager getInstance() {
        return SingletonHolder.f1621a;
    }

    public void popActivity() {
        if (((Activity) this.f1622a.lastElement()) != null) {
        }
        if (this.f1622a.size() == 0) {
            CPUUtil.release();
        }
    }

    public void popActivity(Activity activity) {
        if (activity != null) {
            this.f1622a.remove(activity);
            this.f1623b.m261d("[popActivity] activity=" + activity.getClass().getName() + ", mActivityStack size=" + this.f1622a.size());
        }
        if (this.f1622a != null && this.f1622a.size() == 0) {
            TikiManager.getInstance().cleanUpAfterQuit();
        }
    }

    public Activity currentActivity() {
        if (this.f1622a.isEmpty()) {
            return null;
        }
        return (Activity) this.f1622a.lastElement();
    }

    public void pushActivity(Activity activity) {
        if (activity != null) {
            this.f1622a.add(activity);
            this.f1623b.m261d("[pushActivity] activity=" + activity.getClass().getName() + ", mActivityStack size=" + this.f1622a.size());
        }
    }

    public void popAndFinishAllActivityExceptOne(Activity activity) {
        while (this.f1622a.size() > 0) {
            Activity finishActivity = (Activity) this.f1622a.pop();
            if (!(finishActivity == null || finishActivity == activity)) {
                finishActivity.finish();
            }
        }
        pushActivity(activity);
    }

    public void popAndFinishAllActivity() {
        while (this.f1622a.size() > 0) {
            Activity finishActivity = (Activity) this.f1622a.pop();
            if (finishActivity != null) {
                finishActivity.finish();
            }
        }
    }

    public void popAndFinishWithClass(HashSet<Class> classNames) {
        int length = this.f1622a.size();
        List<Activity> list = new ArrayList();
        for (int i = length - 1; i >= 0; i--) {
            Activity finishActivity = (Activity) this.f1622a.elementAt(i);
            if (classNames.contains(finishActivity.getClass())) {
                list.add(finishActivity);
            }
        }
        for (Activity activity : list) {
            activity.finish();
        }
    }

    public void realPopAndFinishWithClass(HashSet<Class> classNames) {
        int length = this.f1622a.size();
        List<Activity> list = new ArrayList();
        for (int i = length - 1; i >= 0; i--) {
            Activity finishActivity = (Activity) this.f1622a.elementAt(i);
            if (classNames.contains(finishActivity.getClass())) {
                list.add(finishActivity);
            }
        }
        for (Activity activity : list) {
            popActivity(activity);
            activity.finish();
        }
    }

    public void popAndFinishAboveTargetClass(Class className) {
        int length = this.f1622a.size();
        List<Activity> list = new ArrayList();
        for (int i = length - 1; i >= 0; i--) {
            Activity finishActivity = (Activity) this.f1622a.elementAt(i);
            list.add(finishActivity);
            if (className.equals(finishActivity.getClass())) {
                break;
            }
        }
        for (Activity activity : list) {
            activity.finish();
        }
    }

    public void popAndFinishAboveAndWithoutClass(Class className) {
        int length = this.f1622a.size();
        List<Activity> list = new ArrayList();
        for (int i = length - 1; i >= 0; i--) {
            Activity finishActivity = (Activity) this.f1622a.elementAt(i);
            if (finishActivity != null) {
                if (className.equals(finishActivity.getClass())) {
                    break;
                }
                list.add(finishActivity);
            }
        }
        for (Activity activity : list) {
            activity.finish();
        }
    }

    public boolean isExistTargetClass(Class className) {
        int length = this.f1622a.size();
        for (int i = 0; i < length; i++) {
            if (className.equals(((Activity) this.f1622a.elementAt(i)).getClass())) {
                return true;
            }
        }
        return false;
    }

    public void popAndFinishWithNotClass(HashSet<Class> classNames) {
        int length = this.f1622a.size();
        List<Activity> list = new ArrayList();
        for (int i = length - 1; i >= 0; i--) {
            Activity finishActivity = (Activity) this.f1622a.elementAt(i);
            if (!classNames.contains(finishActivity.getClass())) {
                list.add(finishActivity);
            }
        }
        for (Activity activity : list) {
            activity.finish();
        }
    }

    public int sizeOfActivity() {
        if (this.f1622a == null) {
            return 0;
        }
        return this.f1622a.size();
    }

    public Activity getTopFirstActivity() {
        if (this.f1622a.size() > 1) {
            return (Activity) this.f1622a.elementAt(this.f1622a.size() - 1);
        }
        return null;
    }

    public Activity getTopSecondActivity() {
        if (this.f1622a.size() > 1) {
            return (Activity) this.f1622a.elementAt(this.f1622a.size() - 2);
        }
        return null;
    }

    public void onActivityCreate(Activity activity) {
        getInstance().pushActivity(activity);
    }

    public void onActivityDestroy(Activity activity) {
        getInstance().popActivity(activity);
    }
}
