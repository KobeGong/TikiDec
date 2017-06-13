package com.buddy.tiki.view;

import android.content.Context;
import android.util.AttributeSet;
import com.buddy.tiki.util.VideoBreathManager;

public class VideoBreathIcon extends LoopTransitionImage {
    private int f2898a;

    public VideoBreathIcon(Context context) {
        this(context, null);
    }

    public VideoBreathIcon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoBreathIcon(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f2898a = 0;
        setStatus(VideoBreathManager.getInstance().getStatus());
        VideoBreathManager.getInstance().add(this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setStatus(int r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.f2898a;	 Catch:{ all -> 0x000e }
        if (r0 != r4) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);
        return;
    L_0x0007:
        switch(r4) {
            case 0: goto L_0x0011;
            case 1: goto L_0x0018;
            case 2: goto L_0x000a;
            case 3: goto L_0x000a;
            case 4: goto L_0x004b;
            default: goto L_0x000a;
        };
    L_0x000a:
        r3.postInvalidate();	 Catch:{ all -> 0x000e }
        goto L_0x0005;
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x0011:
        r3.clearColors();	 Catch:{ all -> 0x000e }
        r0 = 0;
        r3.f2898a = r0;	 Catch:{ all -> 0x000e }
        goto L_0x000a;
    L_0x0018:
        r0 = 4;
        r0 = new int[r0];	 Catch:{ all -> 0x000e }
        r1 = 0;
        r2 = "#000000";
        r2 = android.graphics.Color.parseColor(r2);	 Catch:{ all -> 0x000e }
        r0[r1] = r2;	 Catch:{ all -> 0x000e }
        r1 = 1;
        r2 = "#FF1F84";
        r2 = android.graphics.Color.parseColor(r2);	 Catch:{ all -> 0x000e }
        r0[r1] = r2;	 Catch:{ all -> 0x000e }
        r1 = 2;
        r2 = "#AC0CFF";
        r2 = android.graphics.Color.parseColor(r2);	 Catch:{ all -> 0x000e }
        r0[r1] = r2;	 Catch:{ all -> 0x000e }
        r1 = 3;
        r2 = "#1F58FF";
        r2 = android.graphics.Color.parseColor(r2);	 Catch:{ all -> 0x000e }
        r0[r1] = r2;	 Catch:{ all -> 0x000e }
        r3.setColors(r0);	 Catch:{ all -> 0x000e }
        r0 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        r3.setInterval(r0);	 Catch:{ all -> 0x000e }
        r0 = 1;
        r3.f2898a = r0;	 Catch:{ all -> 0x000e }
        goto L_0x000a;
    L_0x004b:
        r0 = 2;
        r0 = new int[r0];	 Catch:{ all -> 0x000e }
        r1 = 0;
        r2 = "#000000";
        r2 = android.graphics.Color.parseColor(r2);	 Catch:{ all -> 0x000e }
        r0[r1] = r2;	 Catch:{ all -> 0x000e }
        r1 = 1;
        r2 = "#FF3C3C";
        r2 = android.graphics.Color.parseColor(r2);	 Catch:{ all -> 0x000e }
        r0[r1] = r2;	 Catch:{ all -> 0x000e }
        r3.setColors(r0);	 Catch:{ all -> 0x000e }
        r0 = 600; // 0x258 float:8.41E-43 double:2.964E-321;
        r3.setInterval(r0);	 Catch:{ all -> 0x000e }
        r0 = 4;
        r3.f2898a = r0;	 Catch:{ all -> 0x000e }
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.view.VideoBreathIcon.setStatus(int):void");
    }
}
