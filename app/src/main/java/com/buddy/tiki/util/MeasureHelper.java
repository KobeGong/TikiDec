package com.buddy.tiki.util;

import android.view.View;
import android.view.View.MeasureSpec;
import java.lang.ref.WeakReference;
import org.bytedeco.javacpp.postproc;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public final class MeasureHelper {
    private WeakReference<View> f2424a;
    private int f2425b;
    private int f2426c;
    private int f2427d;
    private int f2428e;
    private int f2429f;
    private int f2430g;
    private int f2431h;
    private int f2432i = 0;

    public MeasureHelper(View view) {
        this.f2424a = new WeakReference(view);
    }

    public View getView() {
        if (this.f2424a == null) {
            return null;
        }
        return (View) this.f2424a.get();
    }

    public void setVideoSize(int videoWidth, int videoHeight) {
        this.f2425b = videoWidth;
        this.f2426c = videoHeight;
    }

    public void setVideoSampleAspectRatio(int videoSarNum, int videoSarDen) {
        this.f2427d = videoSarNum;
        this.f2428e = videoSarDen;
    }

    public void setVideoRotation(int videoRotationDegree) {
        this.f2429f = videoRotationDegree;
    }

    public void doMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.f2429f == 90 || this.f2429f == 270) {
            int tempSpec = widthMeasureSpec;
            widthMeasureSpec = heightMeasureSpec;
            heightMeasureSpec = tempSpec;
        }
        int width = View.getDefaultSize(this.f2425b, widthMeasureSpec);
        int height = View.getDefaultSize(this.f2426c, heightMeasureSpec);
        if (this.f2432i != 3) {
            if (this.f2425b > 0 && this.f2426c > 0) {
                int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
                int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
                int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
                int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
                if (widthSpecMode == postproc.PP_CPU_CAPS_MMX && heightSpecMode == postproc.PP_CPU_CAPS_MMX) {
                    float displayAspectRatio;
                    float specAspectRatio = ((float) widthSpecSize) / ((float) heightSpecSize);
                    switch (this.f2432i) {
                        case swscale.SWS_CS_FCC /*4*/:
                            displayAspectRatio = 1.7777778f;
                            if (this.f2429f == 90 || this.f2429f == 270) {
                                displayAspectRatio = 1.0f / 1.7777778f;
                                break;
                            }
                        case swscale.SWS_CS_SMPTE170M /*5*/:
                            displayAspectRatio = 1.3333334f;
                            if (this.f2429f == 90 || this.f2429f == 270) {
                                displayAspectRatio = 1.0f / 1.3333334f;
                                break;
                            }
                        default:
                            displayAspectRatio = ((float) this.f2425b) / ((float) this.f2426c);
                            if (this.f2427d > 0 && this.f2428e > 0) {
                                displayAspectRatio = (((float) this.f2427d) * displayAspectRatio) / ((float) this.f2428e);
                                break;
                            }
                    }
                    boolean shouldBeWider = displayAspectRatio > specAspectRatio;
                    switch (this.f2432i) {
                        case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                        case swscale.SWS_CS_FCC /*4*/:
                        case swscale.SWS_CS_SMPTE170M /*5*/:
                            if (!shouldBeWider) {
                                height = heightSpecSize;
                                width = (int) (((float) height) * displayAspectRatio);
                                break;
                            }
                            width = widthSpecSize;
                            height = (int) (((float) width) / displayAspectRatio);
                            break;
                        case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                            if (!shouldBeWider) {
                                width = widthSpecSize;
                                height = (int) (((float) width) / displayAspectRatio);
                                break;
                            }
                            height = heightSpecSize;
                            width = (int) (((float) height) * displayAspectRatio);
                            break;
                        default:
                            if (!shouldBeWider) {
                                height = Math.min(this.f2426c, heightSpecSize);
                                width = (int) (((float) height) * displayAspectRatio);
                                break;
                            }
                            width = Math.min(this.f2425b, widthSpecSize);
                            height = (int) (((float) width) / displayAspectRatio);
                            break;
                    }
                } else if (widthSpecMode == postproc.PP_CPU_CAPS_3DNOW && heightSpecMode == postproc.PP_CPU_CAPS_3DNOW) {
                    width = widthSpecSize;
                    height = heightSpecSize;
                    if (this.f2425b * height < this.f2426c * width) {
                        width = (this.f2425b * height) / this.f2426c;
                    } else if (this.f2425b * height > this.f2426c * width) {
                        height = (this.f2426c * width) / this.f2425b;
                    }
                } else if (widthSpecMode == postproc.PP_CPU_CAPS_3DNOW) {
                    width = widthSpecSize;
                    height = (this.f2426c * width) / this.f2425b;
                    if (heightSpecMode == postproc.PP_CPU_CAPS_MMX && height > heightSpecSize) {
                        height = heightSpecSize;
                    }
                } else if (heightSpecMode == postproc.PP_CPU_CAPS_3DNOW) {
                    height = heightSpecSize;
                    width = (this.f2425b * height) / this.f2426c;
                    if (widthSpecMode == postproc.PP_CPU_CAPS_MMX && width > widthSpecSize) {
                        width = widthSpecSize;
                    }
                } else {
                    width = this.f2425b;
                    height = this.f2426c;
                    if (heightSpecMode == postproc.PP_CPU_CAPS_MMX && height > heightSpecSize) {
                        height = heightSpecSize;
                        width = (this.f2425b * height) / this.f2426c;
                    }
                    if (widthSpecMode == postproc.PP_CPU_CAPS_MMX && width > widthSpecSize) {
                        width = widthSpecSize;
                        height = (this.f2426c * width) / this.f2425b;
                    }
                }
            }
        } else {
            width = widthMeasureSpec;
            height = heightMeasureSpec;
        }
        this.f2430g = width;
        this.f2431h = height;
    }

    public int getMeasuredWidth() {
        return this.f2430g;
    }

    public int getMeasuredHeight() {
        return this.f2431h;
    }

    public void setAspectRatio(int aspectRatio) {
        this.f2432i = aspectRatio;
    }
}
