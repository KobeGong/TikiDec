package com.buddy.tiki.util;

import com.buddy.tiki.view.VideoBreathIcon;
import com.buddy.tiki.view.match.SimpleMatchListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class VideoBreathManager extends SimpleMatchListener {
    private static VideoBreathManager f2482a;
    private int f2483b = 0;
    private List<WeakReference<VideoBreathIcon>> f2484c = new ArrayList();

    private VideoBreathManager() {
    }

    public static synchronized VideoBreathManager getInstance() {
        VideoBreathManager videoBreathManager;
        synchronized (VideoBreathManager.class) {
            if (f2482a == null) {
                f2482a = new VideoBreathManager();
            }
            videoBreathManager = f2482a;
        }
        return videoBreathManager;
    }

    public synchronized void add(VideoBreathIcon icon) {
        if (icon != null) {
            this.f2484c.add(new WeakReference(icon));
        }
    }

    public synchronized void remove(VideoBreathIcon icon) {
        if (icon != null) {
            for (int i = 0; i < this.f2484c.size(); i++) {
                WeakReference<VideoBreathIcon> iconRef = (WeakReference) this.f2484c.get(i);
                if (iconRef != null && iconRef.get() == icon) {
                    this.f2484c.remove(i);
                    break;
                }
            }
        }
    }

    public synchronized void onInit() {
        this.f2483b = 0;
        for (WeakReference<VideoBreathIcon> item : this.f2484c) {
            if (!(item == null || item.get() == null)) {
                ((VideoBreathIcon) item.get()).setStatus(0);
            }
        }
    }

    public synchronized void onMatchStart() {
        this.f2483b = 1;
        for (WeakReference<VideoBreathIcon> item : this.f2484c) {
            if (!(item == null || item.get() == null)) {
                ((VideoBreathIcon) item.get()).setStatus(1);
            }
        }
    }

    public synchronized void onConnected() {
        this.f2483b = 4;
        for (WeakReference<VideoBreathIcon> item : this.f2484c) {
            if (!(item == null || item.get() == null)) {
                ((VideoBreathIcon) item.get()).setStatus(4);
            }
        }
    }

    public synchronized int getStatus() {
        return this.f2483b;
    }
}
