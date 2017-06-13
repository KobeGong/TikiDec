package com.buddy.tiki.wertc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.recyclerview.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.buddy.tiki.C0376R;
import io.netty.handler.codec.rtsp.RtspHeaders.Values;
import java.util.HashMap;
import java.util.Map;
import org.bytedeco.javacpp.swscale;
import org.webrtc.StatsReport;
import org.webrtc.StatsReport.Value;

public class HudFragment extends Fragment {
    private final CpuMonitor f3620a = new CpuMonitor();
    private View f3621b;
    private TextView f3622c;
    private TextView f3623d;
    private TextView f3624e;
    private TextView f3625f;
    private TextView f3626g;
    private ImageButton f3627h;
    private boolean f3628i;
    private boolean f3629j;
    private volatile boolean f3630k;

    class C05611 implements OnClickListener {
        final /* synthetic */ HudFragment f3619a;

        C05611(HudFragment this$0) {
            this.f3619a = this$0;
        }

        public void onClick(View view) {
            if (this.f3619a.f3629j) {
                this.f3619a.m2239a(this.f3619a.f3623d.getVisibility() == 0 ? 4 : 0);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.f3621b = inflater.inflate(C0376R.layout.fragment_hud, container, false);
        this.f3622c = (TextView) this.f3621b.findViewById(C0376R.id.encoder_stat_call);
        this.f3623d = (TextView) this.f3621b.findViewById(C0376R.id.hud_stat_bwe);
        this.f3624e = (TextView) this.f3621b.findViewById(C0376R.id.hud_stat_connection);
        this.f3625f = (TextView) this.f3621b.findViewById(C0376R.id.hud_stat_video_send);
        this.f3626g = (TextView) this.f3621b.findViewById(C0376R.id.hud_stat_video_recv);
        this.f3627h = (ImageButton) this.f3621b.findViewById(C0376R.id.button_toggle_debug);
        this.f3627h.setOnClickListener(new C05611(this));
        return this.f3621b;
    }

    public void onStart() {
        int visibility;
        super.onStart();
        if (this.f3629j) {
            visibility = 0;
        } else {
            visibility = 4;
        }
        this.f3622c.setVisibility(visibility);
        this.f3627h.setVisibility(visibility);
        m2239a(4);
        this.f3630k = true;
    }

    public void onStop() {
        this.f3630k = false;
        super.onStop();
    }

    private void m2239a(int visibility) {
        this.f3623d.setVisibility(visibility);
        this.f3624e.setVisibility(visibility);
        this.f3625f.setVisibility(visibility);
        this.f3626g.setVisibility(visibility);
        this.f3623d.setTextSize(3, 5.0f);
        this.f3624e.setTextSize(3, 5.0f);
        this.f3625f.setTextSize(3, 5.0f);
        this.f3626g.setTextSize(3, 5.0f);
    }

    private Map<String, String> m2238a(StatsReport report) {
        Map<String, String> reportMap = new HashMap();
        for (Value value : report.values) {
            reportMap.put(value.name, value.value);
        }
        return reportMap;
    }

    public void updateEncoderStatistics(StatsReport[] reports) {
        if (this.f3630k && this.f3629j) {
            StringBuilder encoderStat = new StringBuilder(swscale.SWS_GAUSS);
            StringBuilder bweStat = new StringBuilder();
            StringBuilder connectionStat = new StringBuilder();
            StringBuilder videoSendStat = new StringBuilder();
            StringBuilder videoRecvStat = new StringBuilder();
            String fps = null;
            String targetBitrate = null;
            String actualBitrate = null;
            for (StatsReport report : reports) {
                Map<String, String> reportMap;
                if (report.type.equals(Values.SSRC)) {
                    if (report.id.contains(Values.SSRC) && report.id.contains("send")) {
                        reportMap = m2238a(report);
                        String trackId = (String) reportMap.get("googTrackId");
                        if (trackId != null && trackId.contains("ARDAMSv0")) {
                            fps = (String) reportMap.get("googFrameRateSent");
                            videoSendStat.append(report.id).append("\n");
                            for (Value value : report.values) {
                                videoSendStat.append(value.name.replace("goog", BuildConfig.VERSION_NAME)).append("=").append(value.value).append("\n");
                            }
                        }
                    }
                }
                if (report.type.equals(Values.SSRC)) {
                    if (report.id.contains(Values.SSRC) && report.id.contains("recv")) {
                        if (((String) m2238a(report).get("googFrameWidthReceived")) != null) {
                            videoRecvStat.append(report.id).append("\n");
                            for (Value value2 : report.values) {
                                videoRecvStat.append(value2.name.replace("goog", BuildConfig.VERSION_NAME)).append("=").append(value2.value).append("\n");
                            }
                        }
                    }
                }
                if (report.id.equals("bweforvideo")) {
                    reportMap = m2238a(report);
                    targetBitrate = (String) reportMap.get("googTargetEncBitrate");
                    actualBitrate = (String) reportMap.get("googActualEncBitrate");
                    bweStat.append(report.id).append("\n");
                    for (Value value22 : report.values) {
                        bweStat.append(value22.name.replace("goog", BuildConfig.VERSION_NAME).replace("Available", BuildConfig.VERSION_NAME)).append("=").append(value22.value).append("\n");
                    }
                } else if (report.type.equals("googCandidatePair")) {
                    String activeConnection = (String) m2238a(report).get("googActiveConnection");
                    if (activeConnection != null && activeConnection.equals("true")) {
                        connectionStat.append(report.id).append("\n");
                        for (Value value222 : report.values) {
                            connectionStat.append(value222.name.replace("goog", BuildConfig.VERSION_NAME)).append("=").append(value222.value).append("\n");
                        }
                    }
                }
            }
            this.f3623d.setText(bweStat.toString());
            this.f3624e.setText(connectionStat.toString());
            this.f3625f.setText(videoSendStat.toString());
            this.f3626g.setText(videoRecvStat.toString());
            if (this.f3628i) {
                if (fps != null) {
                    encoderStat.append("Fps:  ").append(fps).append("\n");
                }
                if (targetBitrate != null) {
                    encoderStat.append("Target BR: ").append(targetBitrate).append("\n");
                }
                if (actualBitrate != null) {
                    encoderStat.append("Actual BR: ").append(actualBitrate).append("\n");
                }
            }
            if (this.f3620a.sampleCpuUtilization()) {
                encoderStat.append("CPU%: ").append(this.f3620a.getCpuCurrent()).append("/").append(this.f3620a.getCpuAvg3()).append("/").append(this.f3620a.getCpuAvgAll());
            }
            this.f3622c.setText(encoderStat.toString());
        }
    }
}
