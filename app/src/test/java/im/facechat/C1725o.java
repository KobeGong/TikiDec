package im.facechat;

import android.content.Context;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import com.sina.weibo.sdk.utils.AidTask;
import im.facechat.common.p043c.C1646c;
import im.facechat.common.p045b.C1639b;
import im.facechat.common.protocol.FacechatCapturer;
import io.netty.handler.codec.http2.Http2CodecUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraEnumerationAndroid$CaptureFormat;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.CameraVideoCapturer.CameraSwitchHandler;
import org.webrtc.DataChannel;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.MediaCodecVideoDecoder;
import org.webrtc.MediaCodecVideoEncoder;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaConstraints.KeyValuePair;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnection.BundlePolicy;
import org.webrtc.PeerConnection.ContinualGatheringPolicy;
import org.webrtc.PeerConnection.IceConnectionState;
import org.webrtc.PeerConnection.IceGatheringState;
import org.webrtc.PeerConnection.IceServer;
import org.webrtc.PeerConnection.IceTransportsType;
import org.webrtc.PeerConnection.KeyType;
import org.webrtc.PeerConnection.Observer;
import org.webrtc.PeerConnection.RTCConfiguration;
import org.webrtc.PeerConnection.SignalingState;
import org.webrtc.PeerConnection.TcpCandidatePolicy;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.PeerConnectionFactory$Options;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.StatsObserver;
import org.webrtc.StatsReport;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoRenderer.Callbacks;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;
import org.webrtc.voiceengine.WebRtcAudioManager;
import tv.danmaku.ijk.media.player.BuildConfig;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* compiled from: PeerConnectionClient */
public class C1725o<F extends FacechatCapturer> {
    private static final Class<?> f11459a = C1725o.class;
    private static final C1725o f11460b = new C1725o();
    private static final IceServer f11461c = new IceServer(BuildConfig.VERSION_NAME);
    private C1619b f11462A;
    private boolean f11463B;
    private SessionDescription f11464C;
    private MediaStream f11465D;
    private int f11466E;
    private CameraVideoCapturer f11467F;
    private boolean f11468G;
    private VideoTrack f11469H;
    private VideoTrack f11470I;
    private AudioTrack f11471J;
    private RTCConfiguration f11472K;
    private List<IceServer> f11473L = new ArrayList();
    private VideoRenderer f11474M;
    private int f11475N = 1;
    private Rtc$OnConstructCapturer<F> f11476O;
    private final C1720a f11477d = new C1720a();
    private final C1724d f11478e = new C1724d();
    private final ScheduledExecutorService f11479f = Executors.newSingleThreadScheduledExecutor();
    private PeerConnectionFactory$Options f11480g = null;
    private PeerConnectionFactory f11481h;
    private PeerConnection f11482i;
    private VideoSource f11483j;
    private AudioSource f11484k;
    private boolean f11485l;
    private boolean f11486m;
    private String f11487n;
    private boolean f11488o;
    private boolean f11489p;
    private Timer f11490q;
    private Callbacks f11491r;
    private Callbacks f11492s;
    private MediaConstraints f11493t;
    private MediaConstraints f11494u;
    private MediaConstraints f11495v;
    private ParcelFileDescriptor f11496w;
    private MediaConstraints f11497x;
    private C1721c f11498y;
    private LinkedList<IceCandidate> f11499z;

    /* compiled from: PeerConnectionClient */
    public interface C1619b {
        void mo4030a();

        void mo4031a(String str, boolean z);

        void mo4032a(IceCandidate iceCandidate);

        void mo4033a(SessionDescription sessionDescription);

        void mo4034a(IceCandidate[] iceCandidateArr);

        void mo4035a(StatsReport[] statsReportArr);

        void mo4036b();

        void mo4037c();

        void mo4038d();

        void mo4039e();
    }

    /* compiled from: PeerConnectionClient */
    class C17063 implements StatsObserver {
        final /* synthetic */ C1725o f11411a;

        C17063(C1725o c1725o) {
            this.f11411a = c1725o;
        }

        public void onComplete(StatsReport[] statsReportArr) {
            this.f11411a.f11462A.mo4035a(statsReportArr);
        }
    }

    /* compiled from: PeerConnectionClient */
    class C17084 extends TimerTask {
        final /* synthetic */ C1725o f11413a;

        /* compiled from: PeerConnectionClient */
        class C17071 implements Runnable {
            final /* synthetic */ C17084 f11412a;

            C17071(C17084 c17084) {
                this.f11412a = c17084;
            }

            public void run() {
                this.f11412a.f11413a.m8167j();
            }
        }

        C17084(C1725o c1725o) {
            this.f11413a = c1725o;
        }

        public void run() {
            this.f11413a.f11479f.execute(new C17071(this));
        }
    }

    /* compiled from: PeerConnectionClient */
    class C17095 implements Runnable {
        final /* synthetic */ C1725o f11414a;

        C17095(C1725o c1725o) {
            this.f11414a = c1725o;
        }

        public void run() {
            C1639b.m7916a(C1725o.f11459a, "try to create offer " + (this.f11414a.f11482i == null) + " isError " + this.f11414a.f11489p);
            if (this.f11414a.f11482i != null && !this.f11414a.f11489p) {
                C1639b.m7916a(C1725o.f11459a, "PC Create OFFER");
                this.f11414a.f11463B = true;
                this.f11414a.f11482i.createOffer(this.f11414a.f11478e, this.f11414a.f11497x);
            }
        }
    }

    /* compiled from: PeerConnectionClient */
    class C17106 implements Runnable {
        final /* synthetic */ C1725o f11415a;

        C17106(C1725o c1725o) {
            this.f11415a = c1725o;
        }

        public void run() {
            if (this.f11415a.f11482i != null && !this.f11415a.f11489p) {
                C1639b.m7916a(C1725o.f11459a, "PC create ANSWER");
                this.f11415a.f11463B = false;
                this.f11415a.f11482i.createAnswer(this.f11415a.f11478e, this.f11415a.f11497x);
            }
        }
    }

    /* compiled from: PeerConnectionClient */
    class C17139 implements Runnable {
        final /* synthetic */ C1725o f11420a;

        C17139(C1725o c1725o) {
            this.f11420a = c1725o;
        }

        public void run() {
            if (this.f11420a.f11483j != null && !this.f11420a.f11488o) {
                C1639b.m7916a(C1725o.f11459a, "Stop video source.");
                this.f11420a.f11483j.stop();
                this.f11420a.f11488o = true;
            }
        }
    }

    /* compiled from: PeerConnectionClient */
    private class C1720a implements Observer {
        final /* synthetic */ C1725o f11431a;
        private IceConnectionState f11432b;
        private AtomicBoolean f11433c;
        private Runnable f11434d;

        /* compiled from: PeerConnectionClient */
        class C17141 implements Runnable {
            final /* synthetic */ C1720a f11421a;

            C17141(C1720a c1720a) {
                this.f11421a = c1720a;
            }

            public void run() {
                if (!this.f11421a.f11433c.get()) {
                    if (this.f11421a.f11433c.get() || this.f11421a.f11432b != IceConnectionState.DISCONNECTED) {
                        if (!this.f11421a.f11433c.get() && this.f11421a.f11432b == IceConnectionState.CONNECTED && this.f11421a.f11431a.f11462A != null) {
                            this.f11421a.f11431a.f11462A.mo4037c();
                        }
                    } else if (this.f11421a.f11431a.f11462A != null) {
                        this.f11421a.f11431a.f11462A.mo4038d();
                    }
                }
            }
        }

        /* compiled from: PeerConnectionClient */
        class C17196 implements Runnable {
            final /* synthetic */ C1720a f11430a;

            C17196(C1720a c1720a) {
                this.f11430a = c1720a;
            }

            public void run() {
                this.f11430a.f11431a.f11470I = null;
            }
        }

        private C1720a(C1725o c1725o) {
            this.f11431a = c1725o;
            this.f11433c = new AtomicBoolean(false);
            this.f11434d = new C17141(this);
        }

        public void onIceCandidatesRemoved(final IceCandidate[] iceCandidateArr) {
            this.f11431a.f11479f.execute(new Runnable(this) {
                final /* synthetic */ C1720a f11423b;

                public void run() {
                    this.f11423b.f11431a.f11462A.mo4034a(iceCandidateArr);
                }
            });
        }

        public void onIceCandidate(final IceCandidate iceCandidate) {
            this.f11431a.f11479f.execute(new Runnable(this) {
                final /* synthetic */ C1720a f11425b;

                public void run() {
                    this.f11425b.f11431a.f11462A.mo4032a(iceCandidate);
                }
            });
        }

        public void onSignalingChange(SignalingState signalingState) {
            C1639b.m7916a(C1725o.f11459a, "SignalingState: " + signalingState);
        }

        public void onIceConnectionChange(final IceConnectionState iceConnectionState) {
            this.f11431a.f11479f.execute(new Runnable(this) {
                final /* synthetic */ C1720a f11427b;

                public void run() {
                    this.f11427b.f11433c.set(true);
                    this.f11427b.f11432b = iceConnectionState;
                    C1639b.m7916a(C1725o.f11459a, "IceConnectionState: " + iceConnectionState);
                    if (iceConnectionState == IceConnectionState.CONNECTED) {
                        this.f11427b.f11431a.f11462A.mo4030a();
                    } else if (iceConnectionState == IceConnectionState.DISCONNECTED) {
                        this.f11427b.f11431a.f11479f.schedule(this.f11427b.f11434d, 10000, TimeUnit.MILLISECONDS);
                        this.f11427b.f11431a.f11462A.mo4036b();
                    } else if (iceConnectionState == IceConnectionState.FAILED) {
                        this.f11427b.f11431a.f11462A.mo4038d();
                    }
                }
            });
        }

        public void onIceGatheringChange(IceGatheringState iceGatheringState) {
            C1639b.m7916a(C1725o.f11459a, "IceGatheringState: " + iceGatheringState);
        }

        public void onIceConnectionReceivingChange(boolean z) {
            C1639b.m7916a(C1725o.f11459a, "IceConnectionReceiving changed to " + z);
        }

        public void onAddStream(final MediaStream mediaStream) {
            C1639b.m7916a(C1725o.f11459a, "onAddStream: " + this.f11431a.f11465D);
            this.f11431a.f11479f.execute(new Runnable(this) {
                final /* synthetic */ C1720a f11429b;

                public void run() {
                    if (this.f11429b.f11431a.f11482i != null && !this.f11429b.f11431a.f11489p) {
                        if (mediaStream.audioTracks.size() > 1 || mediaStream.videoTracks.size() > 1) {
                            this.f11429b.f11431a.m8144a("Weird-looking stream: " + mediaStream, true);
                        } else if (mediaStream.videoTracks.size() == 1) {
                            this.f11429b.f11431a.f11470I = (VideoTrack) mediaStream.videoTracks.get(0);
                            this.f11429b.f11431a.f11470I.setEnabled(this.f11429b.f11431a.f11468G);
                            this.f11429b.f11431a.f11470I.addRenderer(new VideoRenderer(this.f11429b.f11431a.f11492s));
                        }
                    }
                }
            });
        }

        public void onRemoveStream(MediaStream mediaStream) {
            C1639b.m7916a(C1725o.f11459a, "onRemoveStream: " + mediaStream);
            this.f11431a.f11479f.execute(new C17196(this));
        }

        public void onDataChannel(DataChannel dataChannel) {
            this.f11431a.m8144a("tiki doesn't use data channels, but got: " + dataChannel.label() + " anyway!", true);
        }

        public void onRenegotiationNeeded() {
        }
    }

    /* compiled from: PeerConnectionClient */
    public static class C1721c {
        public final boolean f11435a;
        public final boolean f11436b;
        public final boolean f11437c;
        public final boolean f11438d;
        public final int f11439e;
        public final int f11440f;
        public final int f11441g;
        public final int f11442h;
        public final String f11443i;
        public final boolean f11444j;
        public final boolean f11445k;
        public final int f11446l;
        public final String f11447m;
        public final boolean f11448n;
        public final boolean f11449o;
        public final boolean f11450p;
        public final boolean f11451q;
        public final boolean f11452r;
        public final boolean f11453s;
        public final boolean f11454t;

        public C1721c(boolean z, boolean z2, boolean z3, boolean z4, int i, int i2, int i3, int i4, String str, boolean z5, boolean z6, int i5, String str2, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13) {
            this.f11435a = z;
            this.f11438d = z4;
            this.f11436b = z2;
            this.f11437c = z3;
            this.f11439e = i;
            this.f11440f = i2;
            this.f11441g = i3;
            this.f11442h = i4;
            this.f11443i = str;
            this.f11444j = z5;
            this.f11445k = z6;
            this.f11446l = i5;
            this.f11447m = str2;
            this.f11448n = z7;
            this.f11449o = z8;
            this.f11450p = z9;
            this.f11451q = z10;
            this.f11452r = z11;
            this.f11453s = z12;
            this.f11454t = z13;
        }
    }

    /* compiled from: PeerConnectionClient */
    private class C1724d implements SdpObserver {
        final /* synthetic */ C1725o f11458a;

        /* compiled from: PeerConnectionClient */
        class C17232 implements Runnable {
            final /* synthetic */ C1724d f11457a;

            C17232(C1724d c1724d) {
                this.f11457a = c1724d;
            }

            public void run() {
                if (this.f11457a.f11458a.f11482i != null && !this.f11457a.f11458a.f11489p) {
                    if (this.f11457a.f11458a.f11463B) {
                        if (this.f11457a.f11458a.f11482i.getRemoteDescription() == null) {
                            C1639b.m7916a(C1725o.f11459a, "Local SDP set succesfully");
                            this.f11457a.f11458a.f11462A.mo4033a(this.f11457a.f11458a.f11464C);
                            return;
                        }
                        C1639b.m7916a(C1725o.f11459a, "Remote SDP set succesfully");
                        this.f11457a.f11458a.m8171l();
                    } else if (this.f11457a.f11458a.f11482i.getLocalDescription() != null) {
                        C1639b.m7916a(C1725o.f11459a, "Local SDP set succesfully");
                        this.f11457a.f11458a.f11462A.mo4033a(this.f11457a.f11458a.f11464C);
                        this.f11457a.f11458a.m8171l();
                    } else {
                        C1639b.m7916a(C1725o.f11459a, "Remote SDP set succesfully");
                    }
                }
            }
        }

        private C1724d(C1725o c1725o) {
            this.f11458a = c1725o;
        }

        public void onCreateSuccess(SessionDescription sessionDescription) {
            if (this.f11458a.f11464C != null) {
                this.f11458a.m8144a("Multiple SDP create.", true);
                return;
            }
            String str = sessionDescription.description;
            if (this.f11458a.f11486m) {
                str = C1725o.m8148b(str, "ISAC", true);
            }
            if (this.f11458a.f11485l) {
                str = C1725o.m8148b(str, this.f11458a.f11487n, false);
            }
            final SessionDescription sessionDescription2 = new SessionDescription(sessionDescription.type, str);
            this.f11458a.f11464C = sessionDescription2;
            this.f11458a.f11479f.execute(new Runnable(this) {
                final /* synthetic */ C1724d f11456b;

                public void run() {
                    if (this.f11456b.f11458a.f11482i != null && !this.f11456b.f11458a.f11489p) {
                        C1639b.m7916a(C1725o.f11459a, "Set local SDP from " + sessionDescription2.type);
                        this.f11456b.f11458a.f11482i.setLocalDescription(this.f11456b.f11458a.f11478e, sessionDescription2);
                    }
                }
            });
        }

        public void onSetSuccess() {
            this.f11458a.f11479f.execute(new C17232(this));
        }

        public void onCreateFailure(String str) {
            this.f11458a.m8144a("createSDP error: " + str, true);
        }

        public void onSetFailure(String str) {
            this.f11458a.m8144a("setSDP error: " + str, true);
        }
    }

    private C1725o() {
    }

    public static C1725o m8130a() {
        return f11460b;
    }

    private static String m8149b(String str, boolean z, String str2, int i) {
        int i2;
        int i3 = 0;
        String[] split = str2.split("\r\n");
        int i4 = -1;
        String str3 = null;
        Pattern compile = Pattern.compile("^a=rtpmap:(\\d+) " + str + "(/\\d+)+[\r]?$");
        for (i2 = 0; i2 < split.length; i2++) {
            Matcher matcher = compile.matcher(split[i2]);
            if (matcher.matches()) {
                str3 = matcher.group(1);
                i4 = i2;
                break;
            }
        }
        if (str3 == null) {
            C1639b.m7919b(f11459a, "No rtpmap for " + str + " codec");
            return str2;
        }
        StringBuilder stringBuilder;
        String str4;
        C1639b.m7916a(f11459a, "Found " + str + " rtpmap " + str3 + " at " + split[i4]);
        compile = Pattern.compile("^a=fmtp:" + str3 + " \\w+=\\d+.*[\r]?$");
        for (i2 = 0; i2 < split.length; i2++) {
            if (compile.matcher(split[i2]).matches()) {
                C1639b.m7916a(f11459a, "Found " + str + " " + split[i2]);
                if (z) {
                    split[i2] = split[i2] + "; x-google-start-bitrate=" + i;
                } else {
                    split[i2] = split[i2] + "; maxaveragebitrate=" + (i * IjkMediaCodecInfo.RANK_MAX);
                }
                C1639b.m7916a(f11459a, "Update remote SDP line: " + split[i2]);
                i2 = 1;
                stringBuilder = new StringBuilder();
                while (i3 < split.length) {
                    stringBuilder.append(split[i3]).append("\r\n");
                    if (i2 == 0 && i3 == i4) {
                        if (z) {
                            str4 = "a=fmtp:" + str3 + " " + "maxaveragebitrate" + "=" + (i * IjkMediaCodecInfo.RANK_MAX);
                        } else {
                            str4 = "a=fmtp:" + str3 + " " + "x-google-start-bitrate" + "=" + i;
                        }
                        C1639b.m7916a(f11459a, "Add remote SDP line: " + str4);
                        stringBuilder.append(str4).append("\r\n");
                    }
                    i3++;
                }
                return stringBuilder.toString();
            }
        }
        i2 = 0;
        stringBuilder = new StringBuilder();
        while (i3 < split.length) {
            stringBuilder.append(split[i3]).append("\r\n");
            if (z) {
                str4 = "a=fmtp:" + str3 + " " + "maxaveragebitrate" + "=" + (i * IjkMediaCodecInfo.RANK_MAX);
            } else {
                str4 = "a=fmtp:" + str3 + " " + "x-google-start-bitrate" + "=" + i;
            }
            C1639b.m7916a(f11459a, "Add remote SDP line: " + str4);
            stringBuilder.append(str4).append("\r\n");
            i3++;
        }
        return stringBuilder.toString();
    }

    private static String m8148b(String str, String str2, boolean z) {
        int i = 0;
        String[] split = str.split("\r\n");
        String str3 = null;
        Pattern compile = Pattern.compile("^a=rtpmap:(\\d+) " + str2 + "(/\\d+)+[\r]?$");
        String str4 = "m=video ";
        if (z) {
            str4 = "m=audio ";
        }
        int i2 = -1;
        for (int i3 = 0; i3 < split.length && (i2 == -1 || str3 == null); i3++) {
            if (split[i3].startsWith(str4)) {
                i2 = i3;
            } else {
                Matcher matcher = compile.matcher(split[i3]);
                if (matcher.matches()) {
                    str3 = matcher.group(1);
                }
            }
        }
        if (i2 == -1) {
            C1639b.m7919b(f11459a, "No " + str4 + " line, so can't prefer " + str2);
            return str;
        } else if (str3 == null) {
            C1639b.m7919b(f11459a, "No rtpmap for " + str2);
            return str;
        } else {
            C1639b.m7916a(f11459a, "Found " + str2 + " rtpmap " + str3 + ", prefer at " + split[i2]);
            String[] split2 = split[i2].split(" ");
            if (split2.length > 3) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(split2[0]).append(" ");
                stringBuilder.append(split2[1]).append(" ");
                stringBuilder.append(split2[2]).append(" ");
                stringBuilder.append(str3);
                for (int i4 = 3; i4 < split2.length; i4++) {
                    if (!split2[i4].equals(str3)) {
                        stringBuilder.append(" ").append(split2[i4]);
                    }
                }
                split[i2] = stringBuilder.toString();
                C1639b.m7916a(f11459a, "Change media description: " + split[i2]);
            } else {
                C1639b.m7921c(f11459a, "Wrong SDP media description format: " + split[i2]);
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            int length = split.length;
            while (i < length) {
                stringBuilder2.append(split[i]).append("\r\n");
                i++;
            }
            return stringBuilder2.toString();
        }
    }

    public void m8187a(Rtc$OnConstructCapturer<F> rtc$OnConstructCapturer) {
        this.f11476O = rtc$OnConstructCapturer;
    }

    public void m8186a(final Context context, C1721c c1721c, C1619b c1619b) {
        this.f11498y = c1721c;
        this.f11462A = c1619b;
        this.f11485l = c1721c.f11435a;
        this.f11481h = null;
        this.f11482i = null;
        this.f11486m = false;
        this.f11488o = false;
        this.f11489p = false;
        this.f11499z = null;
        this.f11464C = null;
        this.f11465D = null;
        this.f11467F = null;
        this.f11468G = true;
        this.f11469H = null;
        this.f11470I = null;
        this.f11490q = new Timer();
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11404b;

            public void run() {
                this.f11404b.m8137a(context);
            }
        });
    }

    public void m8189a(final EglBase.Context context, Callbacks callbacks, IceServer iceServer) {
        if (this.f11498y == null) {
            C1639b.m7921c(f11459a, "Creating peer connection without initializing factory.");
            return;
        }
        this.f11491r = callbacks;
        if (iceServer == null) {
            this.f11473L = new ArrayList<IceServer>(this) {
                final /* synthetic */ C1725o f11396a;

                {
                    this.f11396a = r2;
                    add(C1725o.f11461c);
                }
            };
        } else {
            this.f11473L.clear();
            this.f11473L.add(iceServer);
        }
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11398b;

            public void run() {
                this.f11398b.m8163h();
                this.f11398b.m8146a(context);
            }
        });
    }

    public void m8196b() {
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11399a;

            {
                this.f11399a = r1;
            }

            public void run() {
                this.f11399a.m8164i();
            }
        });
    }

    private void m8137a(Context context) {
        boolean z;
        C1639b.m7916a(f11459a, "Create peer connection factory. Use video: " + this.f11498y.f11435a);
        this.f11489p = false;
        PeerConnectionFactory.initializeFieldTrials(BuildConfig.VERSION_NAME);
        String str = (MediaCodecVideoDecoder.isH264HwSupported() && MediaCodecVideoEncoder.isH264HwSupported()) ? "H264" : "VP8";
        this.f11487n = str;
        if (this.f11485l && this.f11498y.f11443i != null) {
            if (this.f11498y.f11443i.equals("VP9")) {
                this.f11487n = "VP9";
            } else if (this.f11498y.f11443i.equals("H264")) {
                this.f11487n = "H264";
            }
        }
        C1639b.m7916a(f11459a, "Pereferred video codec: " + this.f11487n);
        if (this.f11498y.f11447m == null || !this.f11498y.f11447m.equals("ISAC")) {
            z = false;
        } else {
            z = true;
        }
        this.f11486m = z;
        if (this.f11498y.f11450p) {
            C1639b.m7916a(f11459a, "Allow OpenSL ES audio if device supports it");
            WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(false);
        } else {
            C1639b.m7916a(f11459a, "Disable OpenSL ES audio even if device supports it");
            WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(true);
        }
        if (!PeerConnectionFactory.initializeAndroidGlobals(context, true, true, this.f11498y.f11444j)) {
            this.f11462A.mo4031a("Failed to initializeAndroidGlobals", true);
        }
        if (this.f11480g != null) {
            C1639b.m7916a(f11459a, "Factory networkIgnoreMask option: " + this.f11480g.networkIgnoreMask);
        }
        this.f11481h = new PeerConnectionFactory(this.f11480g);
        C1639b.m7916a(f11459a, "Peer connection factory created.");
    }

    private void m8163h() {
        this.f11493t = new MediaConstraints();
        if (this.f11498y.f11436b) {
            this.f11493t.optional.add(new KeyValuePair("DtlsSrtpKeyAgreement", "false"));
        } else {
            this.f11493t.optional.add(new KeyValuePair("DtlsSrtpKeyAgreement", "true"));
        }
        this.f11466E = CameraEnumerationAndroid.getDeviceCount();
        if (this.f11466E <= 0) {
            C1639b.m7919b(f11459a, "No camera on device. Switch to audio only call.");
            this.f11485l = false;
        }
        if (this.f11485l) {
            this.f11494u = new MediaConstraints();
            Collection a = C1661e.m8000a(this.f11466E - 1);
            if (this.f11475N == AidTask.WHAT_LOAD_AID_SUC) {
                this.f11494u.optional.add(new KeyValuePair("maxWidth", Integer.toString(640)));
                this.f11494u.optional.add(new KeyValuePair("maxHeight", Integer.toString(480)));
            } else if (this.f11475N == 1) {
                r0 = new CameraEnumerationAndroid$CaptureFormat(640, 480, 1, 15);
                if (a != null) {
                    r0 = (CameraEnumerationAndroid$CaptureFormat) Collections.min(a, new Comparator<CameraEnumerationAndroid$CaptureFormat>(this) {
                        final /* synthetic */ C1725o f11400a;

                        {
                            this.f11400a = r1;
                        }

                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return m8120a((CameraEnumerationAndroid$CaptureFormat) obj, (CameraEnumerationAndroid$CaptureFormat) obj2);
                        }

                        private int m8119a(CameraEnumerationAndroid$CaptureFormat cameraEnumerationAndroid$CaptureFormat) {
                            if (cameraEnumerationAndroid$CaptureFormat.width > 640 || cameraEnumerationAndroid$CaptureFormat.height > 480) {
                                return Http2CodecUtil.MAX_INITIAL_WINDOW_SIZE;
                            }
                            return ((cameraEnumerationAndroid$CaptureFormat.width - 640) * (cameraEnumerationAndroid$CaptureFormat.width - 640)) + ((cameraEnumerationAndroid$CaptureFormat.height - 480) * (cameraEnumerationAndroid$CaptureFormat.height - 480));
                        }

                        public int m8120a(CameraEnumerationAndroid$CaptureFormat cameraEnumerationAndroid$CaptureFormat, CameraEnumerationAndroid$CaptureFormat cameraEnumerationAndroid$CaptureFormat2) {
                            return m8119a(cameraEnumerationAndroid$CaptureFormat) - m8119a(cameraEnumerationAndroid$CaptureFormat2);
                        }
                    });
                }
                C1639b.m7916a(f11459a, "Video Quality: HIGH");
                this.f11494u.mandatory.add(new KeyValuePair("minWidth", Integer.toString(r0.width)));
                this.f11494u.mandatory.add(new KeyValuePair("maxWidth", Integer.toString(r0.width)));
                this.f11494u.mandatory.add(new KeyValuePair("minHeight", Integer.toString(r0.height)));
                this.f11494u.mandatory.add(new KeyValuePair("maxHeight", Integer.toString(r0.height)));
            } else if (this.f11475N == 3) {
                r0 = new CameraEnumerationAndroid$CaptureFormat(240, 160, 1, 15);
                if (a != null) {
                    r0 = (CameraEnumerationAndroid$CaptureFormat) Collections.min(a, new Comparator<CameraEnumerationAndroid$CaptureFormat>(this) {
                        final /* synthetic */ C1725o f11401a;

                        {
                            this.f11401a = r1;
                        }

                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return m8122a((CameraEnumerationAndroid$CaptureFormat) obj, (CameraEnumerationAndroid$CaptureFormat) obj2);
                        }

                        private int m8121a(CameraEnumerationAndroid$CaptureFormat cameraEnumerationAndroid$CaptureFormat) {
                            return ((cameraEnumerationAndroid$CaptureFormat.width - 240) * (cameraEnumerationAndroid$CaptureFormat.width - 240)) + ((cameraEnumerationAndroid$CaptureFormat.height - 160) * (cameraEnumerationAndroid$CaptureFormat.height - 160));
                        }

                        public int m8122a(CameraEnumerationAndroid$CaptureFormat cameraEnumerationAndroid$CaptureFormat, CameraEnumerationAndroid$CaptureFormat cameraEnumerationAndroid$CaptureFormat2) {
                            return m8121a(cameraEnumerationAndroid$CaptureFormat) - m8121a(cameraEnumerationAndroid$CaptureFormat2);
                        }
                    });
                }
                C1639b.m7916a(f11459a, "Video Quality: LOW");
                this.f11494u.mandatory.add(new KeyValuePair("minWidth", Integer.toString(r0.width)));
                this.f11494u.mandatory.add(new KeyValuePair("maxWidth", Integer.toString(r0.width)));
                this.f11494u.mandatory.add(new KeyValuePair("minHeight", Integer.toString(r0.height)));
                this.f11494u.mandatory.add(new KeyValuePair("maxHeight", Integer.toString(r0.height)));
            } else {
                r0 = new CameraEnumerationAndroid$CaptureFormat(480, 360, 1, 15);
                if (a != null) {
                    r0 = (CameraEnumerationAndroid$CaptureFormat) Collections.min(a, new Comparator<CameraEnumerationAndroid$CaptureFormat>(this) {
                        final /* synthetic */ C1725o f11402a;

                        {
                            this.f11402a = r1;
                        }

                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return m8124a((CameraEnumerationAndroid$CaptureFormat) obj, (CameraEnumerationAndroid$CaptureFormat) obj2);
                        }

                        private int m8123a(CameraEnumerationAndroid$CaptureFormat cameraEnumerationAndroid$CaptureFormat) {
                            if (cameraEnumerationAndroid$CaptureFormat.width < 480 || cameraEnumerationAndroid$CaptureFormat.height < 360) {
                                return Http2CodecUtil.MAX_INITIAL_WINDOW_SIZE;
                            }
                            return ((cameraEnumerationAndroid$CaptureFormat.width - 480) * (cameraEnumerationAndroid$CaptureFormat.width - 480)) + ((cameraEnumerationAndroid$CaptureFormat.height - 360) * (cameraEnumerationAndroid$CaptureFormat.height - 360));
                        }

                        public int m8124a(CameraEnumerationAndroid$CaptureFormat cameraEnumerationAndroid$CaptureFormat, CameraEnumerationAndroid$CaptureFormat cameraEnumerationAndroid$CaptureFormat2) {
                            return m8123a(cameraEnumerationAndroid$CaptureFormat) - m8123a(cameraEnumerationAndroid$CaptureFormat2);
                        }
                    });
                }
                C1639b.m7916a(f11459a, "Video Quality: NORMAL");
                this.f11494u.mandatory.add(new KeyValuePair("minWidth", Integer.toString(r0.width)));
                this.f11494u.mandatory.add(new KeyValuePair("maxWidth", Integer.toString(r0.width)));
                this.f11494u.mandatory.add(new KeyValuePair("minHeight", Integer.toString(r0.height)));
                this.f11494u.mandatory.add(new KeyValuePair("maxHeight", Integer.toString(r0.height)));
            }
            int i = this.f11498y.f11441g;
            if (i > 0) {
                Math.min(i, 15);
            }
            this.f11494u.mandatory.add(new KeyValuePair("minFrameRate", Integer.toString(1)));
            this.f11494u.mandatory.add(new KeyValuePair("maxFrameRate", Integer.toString(15)));
        }
        this.f11495v = new MediaConstraints();
        if (this.f11498y.f11448n) {
            C1639b.m7916a(f11459a, "Disabling audio processing");
            this.f11495v.mandatory.add(new KeyValuePair("googEchoCancellation", "false"));
            this.f11495v.mandatory.add(new KeyValuePair("googAutoGainControl", "false"));
            this.f11495v.mandatory.add(new KeyValuePair("googHighpassFilter", "false"));
            this.f11495v.mandatory.add(new KeyValuePair("googNoiseSuppression", "false"));
        }
        if (this.f11498y.f11454t) {
            this.f11495v.mandatory.add(new KeyValuePair("googAutoGainControl", "true"));
        }
        this.f11497x = new MediaConstraints();
        this.f11497x.mandatory.add(new KeyValuePair("OfferToReceiveAudio", "true"));
        if (this.f11485l || this.f11498y.f11436b) {
            this.f11497x.mandatory.add(new KeyValuePair("OfferToReceiveVideo", "true"));
        } else {
            this.f11497x.mandatory.add(new KeyValuePair("OfferToReceiveVideo", "false"));
        }
    }

    public void m8192a(Callbacks callbacks) {
        if (!(this.f11491r == null || this.f11474M == null)) {
            this.f11469H.removeRenderer(this.f11474M);
        }
        this.f11491r = callbacks;
        if (this.f11491r != null) {
            this.f11474M = new VideoRenderer(this.f11491r);
            this.f11469H.addRenderer(this.f11474M);
        }
    }

    private void m8146a(EglBase.Context context) {
        if (this.f11481h == null || this.f11489p) {
            C1639b.m7921c(f11459a, "Peerconnection factory is not created");
            return;
        }
        C1639b.m7916a(f11459a, "Create peer connection.");
        C1639b.m7916a(f11459a, "PCConstraints: " + this.f11493t.toString());
        if (this.f11494u != null) {
            C1639b.m7916a(f11459a, "VideoConstraints: " + this.f11494u.toString());
        }
        this.f11499z = new LinkedList();
        if (this.f11485l) {
            C1639b.m7916a(f11459a, "EGLContext: " + context);
            this.f11481h.setVideoHwAccelerationOptions(context, context);
        }
        this.f11472K = new RTCConfiguration(this.f11473L);
        this.f11472K.iceTransportsType = IceTransportsType.ALL;
        this.f11472K.tcpCandidatePolicy = TcpCandidatePolicy.DISABLED;
        this.f11472K.continualGatheringPolicy = ContinualGatheringPolicy.GATHER_CONTINUALLY;
        this.f11472K.audioJitterBufferFastAccelerate = true;
        this.f11472K.bundlePolicy = BundlePolicy.MAXBUNDLE;
        this.f11472K.keyType = KeyType.ECDSA;
        this.f11482i = this.f11481h.createPeerConnection(this.f11472K, this.f11493t, this.f11477d);
        this.f11463B = false;
        C1639b.m7916a(f11459a, "init video source: videoCallEnabled:" + this.f11485l);
        this.f11465D = this.f11481h.createLocalMediaStream("ARDAMS");
        if (this.f11485l) {
            if (this.f11467F != null) {
                try {
                    C1639b.m7916a(f11459a, "stop already exist video capturer: videoSource:" + this.f11483j + " videoSourceStopped:" + this.f11488o);
                    this.f11467F.stopCapture();
                    this.f11467F.dispose();
                } catch (Throwable e) {
                    C1639b.m7917a(f11459a, "dispose video capturer error:", e);
                }
            }
            if (this.f11498y.f11438d) {
                C1639b.m7916a(f11459a, "use Camera2");
                m8145a(new Camera2Enumerator(C1646c.m7950a().m7952b()));
            } else {
                C1639b.m7916a(f11459a, "use Camera");
                if (this.f11476O == null) {
                    m8145a(new C1661e(this.f11498y.f11445k));
                } else {
                    String deviceName = CameraEnumerationAndroid.getDeviceName(0);
                    String nameOfFrontFacingDevice = CameraEnumerationAndroid.getNameOfFrontFacingDevice();
                    if (this.f11466E <= 1 || nameOfFrontFacingDevice == null) {
                        nameOfFrontFacingDevice = deviceName;
                    }
                    C1639b.m7916a(f11459a, "Opening camera: " + nameOfFrontFacingDevice);
                    this.f11467F = this.f11476O.newInstance(nameOfFrontFacingDevice, null, this.f11498y.f11445k);
                }
            }
            if (this.f11467F == null) {
                m8144a("Failed to open camera", false);
                return;
            }
            this.f11465D.addTrack(m8136a(this.f11467F));
        }
        this.f11465D.addTrack(m8168k());
        this.f11482i.addStream(this.f11465D);
        if (this.f11498y.f11449o) {
            try {
                this.f11496w = ParcelFileDescriptor.open(new File(Environment.DIRECTORY_DOWNLOADS + "/audio.aecdump"), 1006632960);
                this.f11481h.startAecDump(this.f11496w.getFd(), -1);
            } catch (Throwable e2) {
                C1639b.m7917a(f11459a, "Can not open aecdump file", e2);
            }
        }
        C1639b.m7916a(f11459a, "Default Peer connection created. ");
    }

    private void m8145a(CameraEnumerator cameraEnumerator) {
        int i = 0;
        String[] deviceNames = cameraEnumerator.getDeviceNames();
        C1639b.m7916a(f11459a, "Looking for front facing cameras.");
        for (String str : deviceNames) {
            if (cameraEnumerator.isFrontFacing(str)) {
                C1639b.m7916a(f11459a, "Creating front facing camera capturer.");
                this.f11467F = cameraEnumerator.createCapturer(str, null);
                if (this.f11467F != null) {
                    return;
                }
            }
        }
        C1639b.m7916a(f11459a, "Looking for other cameras.");
        int length = deviceNames.length;
        while (i < length) {
            String str2 = deviceNames[i];
            if (!cameraEnumerator.isFrontFacing(str2)) {
                C1639b.m7916a(f11459a, "Creating other camera capturer.");
                this.f11467F = cameraEnumerator.createCapturer(str2, null);
                if (this.f11467F != null) {
                    return;
                }
            }
            i++;
        }
    }

    private void m8164i() {
        if (this.f11490q != null) {
            this.f11490q.purge();
            this.f11490q.cancel();
            this.f11490q = null;
        }
        if (this.f11481h != null && this.f11498y.f11449o) {
            this.f11481h.stopAecDump();
        }
        C1639b.m7916a(f11459a, "Closing peer connection.");
        if (this.f11482i != null) {
            this.f11482i.dispose();
            this.f11482i = null;
        }
        if (this.f11484k != null) {
            this.f11484k.dispose();
            this.f11484k = null;
        }
        C1639b.m7916a(f11459a, "Closing video source.");
        if (this.f11483j != null) {
            try {
                this.f11467F.stopCapture();
            } catch (Throwable e) {
                C1639b.m7917a(f11459a, "stopCapture fail", e);
            }
            this.f11483j.dispose();
            this.f11483j = null;
        }
        C1639b.m7916a(f11459a, "Closing peer connection factory.");
        if (this.f11481h != null) {
            this.f11481h = null;
        }
        this.f11480g = null;
        C1639b.m7916a(f11459a, "Closing peer connection done.");
        this.f11462A.mo4039e();
    }

    void m8193a(final Callbacks callbacks, final IceServer iceServer) {
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11407c;

            public void run() {
                this.f11407c.f11489p = false;
                this.f11407c.m8163h();
                this.f11407c.m8153c(callbacks, iceServer);
                this.f11407c.m8195a(true, 1000);
                C1639b.m7921c(C1725o.f11459a, "recreate peerconnection ok");
            }
        });
        this.f11492s = callbacks;
    }

    public void m8197b(final Callbacks callbacks, final IceServer iceServer) {
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11410c;

            public void run() {
                this.f11410c.m8153c(callbacks, iceServer);
            }
        });
    }

    private void m8153c(Callbacks callbacks, IceServer iceServer) {
        if (this.f11490q != null) {
            this.f11490q.cancel();
            this.f11490q.purge();
            this.f11490q = null;
        }
        if (this.f11482i != null) {
            this.f11482i.removeStream(this.f11465D);
        }
        if (this.f11470I != null) {
            this.f11470I.removeRenderer(new VideoRenderer(callbacks));
            this.f11470I = null;
        }
        if (this.f11482i != null) {
            this.f11482i.close();
        }
        if (this.f11484k != null) {
            this.f11484k.dispose();
            this.f11484k = null;
        }
        List arrayList;
        if (iceServer == null) {
            arrayList = new ArrayList();
            arrayList.add(f11461c);
            this.f11472K.iceServers = arrayList;
        } else {
            arrayList = new ArrayList();
            arrayList.add(iceServer);
            this.f11472K.iceServers = arrayList;
        }
        this.f11472K.iceTransportsType = IceTransportsType.ALL;
        this.f11482i = this.f11481h.createPeerConnection(this.f11472K, this.f11493t, this.f11477d);
        this.f11482i.addStream(this.f11465D);
        this.f11464C = null;
    }

    private void m8167j() {
        if (this.f11482i != null && !this.f11489p && !this.f11482i.getStats(new C17063(this), null)) {
            C1639b.m7921c(f11459a, "getStats() returns false!");
        }
    }

    public void m8195a(boolean z, long j) {
        if (z) {
            if (this.f11490q != null) {
                this.f11490q.purge();
                this.f11490q.cancel();
            }
            this.f11490q = new Timer();
            try {
                this.f11490q.schedule(new C17084(this), 0, j);
            } catch (Throwable e) {
                C1639b.m7917a(f11459a, "Can not schedule statistics timer", e);
            }
        } else if (this.f11490q != null) {
            this.f11490q.purge();
            this.f11490q.cancel();
        }
    }

    public void m8198c() {
        this.f11479f.execute(new C17095(this));
    }

    public void m8199d() {
        this.f11479f.execute(new C17106(this));
    }

    public void m8190a(final IceCandidate iceCandidate) {
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11417b;

            public void run() {
                if (this.f11417b.f11482i != null && !this.f11417b.f11489p) {
                    if (this.f11417b.f11499z != null) {
                        this.f11417b.f11499z.add(iceCandidate);
                    } else {
                        this.f11417b.f11482i.addIceCandidate(iceCandidate);
                    }
                }
            }
        });
    }

    public void m8191a(final SessionDescription sessionDescription) {
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11419b;

            public void run() {
                if (this.f11419b.f11482i != null && !this.f11419b.f11489p) {
                    String str = sessionDescription.description;
                    if (this.f11419b.f11486m) {
                        str = C1725o.m8148b(str, "ISAC", true);
                    }
                    if (this.f11419b.f11485l) {
                        str = C1725o.m8148b(str, this.f11419b.f11487n, false);
                    }
                    if (this.f11419b.f11485l && this.f11419b.f11498y.f11442h > 0) {
                        str = C1725o.m8149b("H264", true, C1725o.m8149b("VP9", true, C1725o.m8149b("VP8", true, str, this.f11419b.f11498y.f11442h), this.f11419b.f11498y.f11442h), this.f11419b.f11498y.f11442h);
                    }
                    if (this.f11419b.f11498y.f11446l > 0) {
                        str = C1725o.m8149b("opus", false, str, this.f11419b.f11498y.f11446l);
                    }
                    C1639b.m7916a(C1725o.f11459a, "Set remote SDP.");
                    SessionDescription sessionDescription = new SessionDescription(sessionDescription.type, str);
                    C1639b.m7916a(C1725o.f11459a, "setRemoteDescription " + sessionDescription.description);
                    this.f11419b.f11482i.setRemoteDescription(this.f11419b.f11478e, sessionDescription);
                }
            }
        });
    }

    public void m8200e() {
        this.f11479f.execute(new C17139(this));
    }

    public void m8194a(@Nullable final Callbacks callbacks, @Nullable final Callbacks callbacks2) {
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11388c;

            public void run() {
                if (!(this.f11388c.f11492s == null || callbacks == null || this.f11388c.f11492s == callbacks || this.f11388c.f11470I == null)) {
                    this.f11388c.f11470I.removeRenderer(new VideoRenderer(this.f11388c.f11492s));
                    this.f11388c.f11492s = callbacks;
                    this.f11388c.f11470I.addRenderer(new VideoRenderer(this.f11388c.f11492s));
                }
                if (!(this.f11388c.f11491r == null || callbacks2 == null || this.f11388c.f11491r == callbacks2)) {
                    this.f11388c.m8192a(callbacks2);
                }
                C1639b.m7916a(C1725o.f11459a, "Restart video source prepare");
                if (this.f11388c.f11483j != null && this.f11388c.f11488o) {
                    C1639b.m7916a(C1725o.f11459a, "Restart video source.");
                    this.f11388c.f11483j.restart();
                    this.f11388c.f11488o = false;
                }
            }
        });
    }

    private void m8144a(final String str, final boolean z) {
        C1639b.m7921c(f11459a, "Peerconnection error: " + str);
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11391c;

            public void run() {
                if (!this.f11391c.f11489p) {
                    this.f11391c.f11462A.mo4031a(str, z);
                    this.f11391c.f11489p = true;
                }
            }
        });
    }

    private VideoTrack m8136a(VideoCapturer videoCapturer) {
        C1639b.m7916a(f11459a, "createVideoTrack: renderVideo:" + this.f11468G);
        this.f11483j = this.f11481h.createVideoSource(videoCapturer, this.f11494u);
        this.f11469H = this.f11481h.createVideoTrack("ARDAMSv0", this.f11483j);
        this.f11474M = new VideoRenderer(this.f11491r);
        this.f11469H.addRenderer(this.f11474M);
        this.f11469H.setEnabled(this.f11468G);
        return this.f11469H;
    }

    private AudioTrack m8168k() {
        this.f11484k = this.f11481h.createAudioSource(this.f11495v);
        this.f11471J = this.f11481h.createAudioTrack("ARDAMSa0", this.f11484k);
        this.f11471J.setEnabled(true);
        return this.f11471J;
    }

    private void m8171l() {
        if (this.f11499z != null) {
            Iterator it = this.f11499z.iterator();
            while (it.hasNext()) {
                this.f11482i.addIceCandidate((IceCandidate) it.next());
            }
            this.f11499z = null;
        }
    }

    private void m8151b(CameraSwitchHandler cameraSwitchHandler) {
        if (!this.f11485l || this.f11466E < 2 || this.f11489p || this.f11467F == null) {
            C1639b.m7921c(f11459a, "Failed to switch camera. Video: " + this.f11485l + ". Error : " + this.f11489p + ". Number of cameras: " + this.f11466E);
            return;
        }
        C1639b.m7916a(f11459a, "Switch camera");
        this.f11467F.switchCamera(cameraSwitchHandler);
    }

    public void m8188a(final CameraSwitchHandler cameraSwitchHandler) {
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11393b;

            public void run() {
                this.f11393b.m8151b(cameraSwitchHandler);
            }
        });
    }

    public void m8185a(final int i) {
        C1639b.m7916a(f11459a, "setQuality:oQ:" + this.f11475N + " nQ:" + i);
        this.f11479f.execute(new Runnable(this) {
            final /* synthetic */ C1725o f11395b;

            public void run() {
                if (i != this.f11395b.f11475N) {
                    if (i == 1 || i == 3 || i == 2) {
                        this.f11395b.f11475N = i;
                    }
                }
            }
        });
    }
}
