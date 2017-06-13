package com.buddy.tiki.view.video;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.recyclerview.BuildConfig;
import android.util.SparseArray;
import android.view.View;
import android.widget.TableLayout;
import com.buddy.tiki.C0376R;
import java.util.Locale;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.MediaPlayerProxy;

public class InfoHudViewHolder {
    private TableLayoutBinder f3418a;
    private SparseArray<View> f3419b = new SparseArray();
    private IMediaPlayer f3420c;
    private long f3421d = 0;
    private long f3422e = 0;
    private Handler f3423f = new C05551(this);

    class C05551 extends Handler {
        final /* synthetic */ InfoHudViewHolder f3417a;

        C05551(InfoHudViewHolder this$0) {
            this.f3417a = this$0;
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    InfoHudViewHolder holder = this.f3417a;
                    IjkMediaPlayer mp = null;
                    if (this.f3417a.f3420c != null) {
                        if (this.f3417a.f3420c instanceof IjkMediaPlayer) {
                            mp = (IjkMediaPlayer) this.f3417a.f3420c;
                        } else if (this.f3417a.f3420c instanceof MediaPlayerProxy) {
                            IMediaPlayer internal = ((MediaPlayerProxy) this.f3417a.f3420c).getInternalMediaPlayer();
                            if (internal != null && (internal instanceof IjkMediaPlayer)) {
                                mp = (IjkMediaPlayer) internal;
                            }
                        }
                        if (mp != null) {
                            switch (mp.getVideoDecoder()) {
                                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                                    this.f3417a.m2151a(C0376R.string.vdec, "avcodec");
                                    break;
                                case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                                    this.f3417a.m2151a(C0376R.string.vdec, "MediaCodec");
                                    break;
                                default:
                                    this.f3417a.m2151a(C0376R.string.vdec, BuildConfig.VERSION_NAME);
                                    break;
                            }
                            float fpsOutput = mp.getVideoOutputFramesPerSecond();
                            float fpsDecode = mp.getVideoDecodeFramesPerSecond();
                            this.f3417a.m2151a(C0376R.string.fps, String.format(Locale.US, "%.2f / %.2f", new Object[]{Float.valueOf(fpsDecode), Float.valueOf(fpsOutput)}));
                            long videoCachedDuration = mp.getVideoCachedDuration();
                            long audioCachedDuration = mp.getAudioCachedDuration();
                            long videoCachedBytes = mp.getVideoCachedBytes();
                            long audioCachedBytes = mp.getAudioCachedBytes();
                            long tcpSpeed = mp.getTcpSpeed();
                            long bitRate = mp.getBitRate();
                            long seekLoadDuration = mp.getSeekLoadDuration();
                            this.f3417a.m2151a(C0376R.string.v_cache, String.format(Locale.US, "%s, %s", new Object[]{InfoHudViewHolder.m2157c(videoCachedDuration), InfoHudViewHolder.m2159d(videoCachedBytes)}));
                            this.f3417a.m2151a(C0376R.string.a_cache, String.format(Locale.US, "%s, %s", new Object[]{InfoHudViewHolder.m2157c(audioCachedDuration), InfoHudViewHolder.m2159d(audioCachedBytes)}));
                            this.f3417a.m2151a(C0376R.string.load_cost, String.format(Locale.US, "%d ms", new Object[]{Long.valueOf(this.f3417a.f3421d)}));
                            this.f3417a.m2151a(C0376R.string.seek_cost, String.format(Locale.US, "%d ms", new Object[]{Long.valueOf(this.f3417a.f3422e)}));
                            this.f3417a.m2151a(C0376R.string.seek_load_cost, String.format(Locale.US, "%d ms", new Object[]{Long.valueOf(seekLoadDuration)}));
                            this.f3417a.m2151a(C0376R.string.tcp_speed, String.format(Locale.US, "%s", new Object[]{InfoHudViewHolder.m2155b(tcpSpeed, 1000)}));
                            this.f3417a.m2151a(C0376R.string.bit_rate, String.format(Locale.US, "%.2f kbs", new Object[]{Float.valueOf(((float) bitRate) / 1000.0f)}));
                            this.f3417a.f3423f.removeMessages(1);
                            this.f3417a.f3423f.sendEmptyMessageDelayed(1, 500);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public InfoHudViewHolder(Context context, TableLayout tableLayout) {
        this.f3418a = new TableLayoutBinder(context, tableLayout);
    }

    private static String m2157c(long duration) {
        if (duration >= 1000) {
            return String.format(Locale.US, "%.2f sec", new Object[]{Float.valueOf(((float) duration) / 1000.0f)});
        }
        return String.format(Locale.US, "%d msec", new Object[]{Long.valueOf(duration)});
    }

    private static String m2155b(long bytes, long elapsed_milli) {
        if (elapsed_milli <= 0) {
            return "0 B/s";
        }
        if (bytes <= 0) {
            return "0 B/s";
        }
        float bytes_per_sec = (((float) bytes) * 1000.0f) / ((float) elapsed_milli);
        if (bytes_per_sec >= 1000000.0f) {
            return String.format(Locale.US, "%.2f MB/s", new Object[]{Float.valueOf((bytes_per_sec / 1000.0f) / 1000.0f)});
        } else if (bytes_per_sec >= 1000.0f) {
            return String.format(Locale.US, "%.1f KB/s", new Object[]{Float.valueOf(bytes_per_sec / 1000.0f)});
        } else {
            return String.format(Locale.US, "%d B/s", new Object[]{Long.valueOf((long) bytes_per_sec)});
        }
    }

    private static String m2159d(long bytes) {
        if (bytes >= 100000) {
            return String.format(Locale.US, "%.2f MB", new Object[]{Float.valueOf((((float) bytes) / 1000.0f) / 1000.0f)});
        } else if (bytes >= 100) {
            return String.format(Locale.US, "%.1f KB", new Object[]{Float.valueOf(((float) bytes) / 1000.0f)});
        } else {
            return String.format(Locale.US, "%d B", new Object[]{Long.valueOf(bytes)});
        }
    }

    private void m2151a(int id, String value) {
        View rowView = (View) this.f3419b.get(id);
        if (rowView == null) {
            this.f3419b.put(id, this.f3418a.appendRow2(id, value));
            return;
        }
        this.f3418a.setValueText(rowView, value);
    }

    public void setMediaPlayer(IMediaPlayer mp) {
        this.f3420c = mp;
        if (this.f3420c != null) {
            this.f3423f.sendEmptyMessageDelayed(1, 500);
        } else {
            this.f3423f.removeMessages(1);
        }
    }

    public void updateLoadCost(long time) {
        this.f3421d = time;
    }

    public void updateSeekCost(long time) {
        this.f3422e = time;
    }
}
