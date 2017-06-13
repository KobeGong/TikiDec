package com.buddy.tiki.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.base.Constants;
import com.buddy.tiki.helper.MediaHelper;
import com.buddy.tiki.view.IncomingCallView;
import com.buddy.tiki.view.IncomingCallView1;
import java.util.ArrayList;
import java.util.List;

public class IncomingCallManager {
    private static IncomingCallManager f2405a = new IncomingCallManager();
    private ArrayMap<String, View> f2406b = new ArrayMap();
    private List<String> f2407c = new ArrayList();

    private IncomingCallManager() {
    }

    public static IncomingCallManager getInstance() {
        return f2405a;
    }

    public synchronized void show(Context context, String callId, String sound, String title, String content, String callerUid, String callerNick) {
        if (context != null) {
            if (!TextUtils.isEmpty(callId)) {
                if (!this.f2406b.containsKey(callId)) {
                    IncomingCallView1 view = new IncomingCallView1(context, callId, sound, title, content, callerUid, callerNick);
                    WindowManager windowManager = (WindowManager) ChatApp.getInstance().getSystemService("window");
                    String packageName = context.getPackageName();
                    if (windowManager != null) {
                        LayoutParams params = new LayoutParams();
                        params.alpha = 0.9f;
                        params.width = -1;
                        params.height = -2;
                        params.gravity = 49;
                        params.x = 0;
                        params.y = 0;
                        params.dimAmount = 0.0f;
                        params.flags = 262178;
                        params.format = -3;
                        if (VERSION.SDK_INT >= 19) {
                            params.type = 2005;
                        } else {
                            params.type = 2002;
                        }
                        params.packageName = packageName;
                        windowManager.addView(view, params);
                        this.f2406b.put(callId, view);
                        if (this.f2406b.size() > 0) {
                            MediaHelper.INSTANCE.playMusic(ChatApp.getInstance(), Constants.f406a, true);
                        }
                    }
                }
            }
        }
    }

    public synchronized void show(Context context, String callId, String callerNick, String callerUid) {
        if (context != null) {
            if (!TextUtils.isEmpty(callId)) {
                if (!(this.f2406b.containsKey(callId) || this.f2407c.contains(callId))) {
                    IncomingCallView view = new IncomingCallView(context, callId, callerNick, callerUid);
                    WindowManager windowManager = (WindowManager) ChatApp.getInstance().getSystemService("window");
                    String packageName = context.getPackageName();
                    if (windowManager != null) {
                        LayoutParams params = new LayoutParams();
                        params.width = -1;
                        params.height = -2;
                        params.gravity = 49;
                        params.x = 0;
                        params.y = 0;
                        params.dimAmount = 0.0f;
                        params.flags = 262178;
                        params.format = -3;
                        if (VERSION.SDK_INT >= 19) {
                            params.type = 2005;
                        } else {
                            params.type = 2002;
                        }
                        params.packageName = packageName;
                        windowManager.addView(view, params);
                        this.f2406b.put(callId, view);
                    }
                }
            }
        }
    }

    public synchronized void dismiss(String callId) {
        if (this.f2406b.containsKey(callId)) {
            WindowManager windowManager = (WindowManager) ChatApp.getInstance().getSystemService("window");
            if (windowManager != null) {
                windowManager.removeView((View) this.f2406b.get(callId));
                this.f2406b.remove(callId);
                if (!this.f2407c.contains(callId)) {
                    this.f2407c.add(callId);
                }
            }
            if (this.f2406b.size() == 0) {
                MediaHelper.INSTANCE.stopMusic();
            }
        }
    }

    public synchronized void dismissAll() {
        if (this.f2406b != null && this.f2406b.size() > 0) {
            WindowManager windowManager = (WindowManager) ChatApp.getInstance().getSystemService("window");
            if (windowManager != null) {
                for (int i = this.f2406b.size() - 1; i >= 0; i--) {
                    windowManager.removeView((View) this.f2406b.valueAt(i));
                    String key = (String) this.f2406b.keyAt(i);
                    if (!this.f2407c.contains(key)) {
                        this.f2407c.add(key);
                    }
                    this.f2406b.remove(key);
                }
                MediaHelper.INSTANCE.stopMusic();
            }
        }
    }
}
