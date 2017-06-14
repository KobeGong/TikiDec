package com.buddy.tiki.wertc;

import android.content.Context;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.recyclerview.BuildConfig;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.im.IMRtcClient;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.constant.MsgDataType;
import com.buddy.tiki.util.ToastUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bytedeco.javacpp.avcodec;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
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
import org.webrtc.PeerConnectionFactory.Options;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.StatsReport;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoRenderer.Callbacks;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;
import org.webrtc.voiceengine.WebRtcAudioManager;

public class PeerConnectionClient {
    public static int f3703a = 4;
    private static final TikiLog Logger = TikiLog.getInstance("PeerConnectionClient");
    private static final PeerConnectionClient f3705d = new PeerConnectionClient();
    private static final IceServer defaultIceServer = new IceServer("stun:rtc.tikiapp.im:3478");
    private LinkedList<IceCandidate> iceCandidates;
    private PeerConnectionEvents f3708B;
    private boolean haveOffer;
    private SessionDescription f3710D;
    private MediaStream f3711E;
    private int f3712F;
    private CameraVideoCapturer videoCapturer;
    private boolean f3714H;
    private VideoTrack localVideoTrack;
    private VideoTrack remoteVideoTrack;
    private AudioTrack audioTrack;
    private RTCConfiguration rtcConfiguration;
    private List<IceServer> iceServers;
    private IMRtcClient imClient;
    private VideoRenderer f3721O;
    private int f3722P = 1;
    private int f3723Q;
    private int f3724R;
    private CallBack<PeerConnectionClient> f3725S;
    Options f3726b = null;
    private final PCObserver f3727f = new PCObserver();
    private final SDPObserver f3728g = new SDPObserver();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private PeerConnectionFactory peerConnectionFactory;
    private PeerConnection peerConnection;
    private VideoSource videoSource;
    private AudioSource audioSource;
    private boolean videoCallEnable;
    private boolean f3735n;
    private String f3736o;
    private boolean f3737p;
    private boolean f3738q;
    private Timer f3739r;
    private Callbacks localRenderer;
    private Callbacks removeRenderer;
    private MediaConstraints f3742u;
    private MediaConstraints videoConstraints;
    private MediaConstraints f3744w;
    private ParcelFileDescriptor f3745x;
    private MediaConstraints f3746y;
    private PeerConnectionParameters f3747z;

    public interface CallBack<T> {
        void call(T t);
    }

    class IceServerList extends ArrayList<IceServer> {
        IceServerList() {
            add(PeerConnectionClient.defaultIceServer);
        }
    }

    class C05632 implements Comparator<CaptureFormat> {
        final /* synthetic */ PeerConnectionClient f3662a;

        C05632(PeerConnectionClient this$0) {
            this.f3662a = this$0;
        }

        private int m2243a(CaptureFormat captureFormat) {
            if (captureFormat.width > 640 || captureFormat.height > 480) {
                return ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            return ((captureFormat.width - 640) * (captureFormat.width - 640)) + ((captureFormat.height - 480) * (captureFormat.height - 480));
        }

        public int compare(CaptureFormat o1, CaptureFormat o2) {
            return m2243a(o1) - m2243a(o2);
        }
    }

    class C05643 implements Comparator<CaptureFormat> {
        final /* synthetic */ PeerConnectionClient f3663a;

        C05643(PeerConnectionClient this$0) {
            this.f3663a = this$0;
        }

        private int m2244a(CaptureFormat captureFormat) {
            return ((captureFormat.width - 240) * (captureFormat.width - 240)) + ((captureFormat.height - 160) * (captureFormat.height - 160));
        }

        public int compare(CaptureFormat o1, CaptureFormat o2) {
            return m2244a(o1) - m2244a(o2);
        }
    }

    class C05654 implements Comparator<CaptureFormat> {
        final /* synthetic */ PeerConnectionClient f3664a;

        C05654(PeerConnectionClient this$0) {
            this.f3664a = this$0;
        }

        private int m2245a(CaptureFormat captureFormat) {
            if (captureFormat.width < 480 || captureFormat.height < 360) {
                return ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            return ((captureFormat.width - 480) * (captureFormat.width - 480)) + ((captureFormat.height - 360) * (captureFormat.height - 360));
        }

        public int compare(CaptureFormat o1, CaptureFormat o2) {
            return m2245a(o1) - m2245a(o2);
        }
    }

    class C05665 extends TimerTask {
        final /* synthetic */ PeerConnectionClient f3666a;

        C05665(PeerConnectionClient this$0) {
            this.f3666a = this$0;
        }

        public void run() {
            this.f3666a.executorService.execute(PeerConnectionClient$5$$Lambda$1.lambdaFactory$(this.f3666a));
        }
    }

    private class PCObserver implements Observer {
        final /* synthetic */ PeerConnectionClient f3677a;
        private IceConnectionState f3678b;

        private PCObserver(PeerConnectionClient peerConnectionClient) {
            this.f3677a = peerConnectionClient;
        }

        /* synthetic */ void m2252a(IceCandidate[] iceCandidates) {
            this.f3677a.f3708B.onIceCandidatesRemoved(iceCandidates);
        }

        public void onIceCandidatesRemoved(IceCandidate[] iceCandidates) {
            this.f3677a.executorService.execute(PeerConnectionClient$PCObserver$$Lambda$1.lambdaFactory$(this, iceCandidates));
        }

        /* synthetic */ void m2249a(IceCandidate candidate) {
            this.f3677a.f3708B.onIceCandidate(candidate);
        }

        public void onIceCandidate(IceCandidate candidate) {
            this.f3677a.executorService.execute(PeerConnectionClient$PCObserver$$Lambda$2.lambdaFactory$(this, candidate));
        }

        public void onSignalingChange(SignalingState newState) {
            PeerConnectionClient.Logger.m261d("SignalingState: " + newState);
        }

        public void onIceConnectionChange(IceConnectionState newState) {
            this.f3677a.executorService.execute(PeerConnectionClient$PCObserver$$Lambda$3.lambdaFactory$(this, newState));
        }

        /* synthetic */ void m2251a(IceConnectionState newState) {
            this.f3678b = newState;
            PeerConnectionClient.Logger.m261d("IceConnectionState: " + newState);
            if (newState == IceConnectionState.CONNECTED) {
                this.f3677a.f3708B.onIceConnected();
            } else if (newState == IceConnectionState.DISCONNECTED) {
                ToastUtil.getInstance().show(ChatApp.getInstance(), (int) C0376R.string.network_unstable, 1);
                this.f3677a.f3708B.onConnectionLost();
                Observable.timer(10000, TimeUnit.MILLISECONDS, Schedulers.from(this.f3677a.executorService)).subscribe(PeerConnectionClient$PCObserver$$Lambda$6.lambdaFactory$(this));
            } else if (newState == IceConnectionState.FAILED) {
                this.f3677a.f3708B.onIceDisconnected();
            }
        }

        /* synthetic */ void m2248a(Long aLong) throws Exception {
            if (this.f3678b == IceConnectionState.DISCONNECTED) {
                this.f3677a.f3708B.onIceDisconnected();
            } else if (this.f3678b == IceConnectionState.CONNECTED) {
                this.f3677a.f3708B.onReconnected();
            }
        }

        public void onIceGatheringChange(IceGatheringState newState) {
            PeerConnectionClient.Logger.m261d("IceGatheringState: " + newState);
        }

        public void onIceConnectionReceivingChange(boolean receiving) {
            PeerConnectionClient.Logger.m261d("IceConnectionReceiving changed to " + receiving);
        }

        public void onAddStream(final MediaStream stream) {
            PeerConnectionClient.Logger.m261d("onAddStream: " + this.f3677a.f3711E);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    if (f3677a.peerConnection != null && !f3677a.f3738q) {
                        if (stream.audioTracks.size() > 1 || stream.videoTracks.size() > 1) {
                            f3677a.m2267b("Weird-looking stream: " + stream);
                        } else if (stream.videoTracks.size() == 1) {
                            f3677a.remoteVideoTrack = (VideoTrack) stream.videoTracks.get(0);
                            f3677a.remoteVideoTrack.setEnabled(f3677a.f3714H);
                            f3677a.remoteVideoTrack.addRenderer(new VideoRenderer(f3677a.removeRenderer));
                        }
                    }
                }
            });
        }

        /* synthetic */ void m2250a(MediaStream stream) {
            if (this.f3677a.peerConnection != null && !this.f3677a.f3738q) {
                if (stream.audioTracks.size() > 1 || stream.videoTracks.size() > 1) {
                    this.f3677a.m2267b("Weird-looking stream: " + stream);
                } else if (stream.videoTracks.size() == 1) {
                    this.f3677a.remoteVideoTrack = (VideoTrack) stream.videoTracks.get(0);
                    this.f3677a.remoteVideoTrack.setEnabled(this.f3677a.f3714H);
                    this.f3677a.remoteVideoTrack.addRenderer(new VideoRenderer(this.f3677a.removeRenderer));
                }
            }
        }

        public void onRemoveStream(MediaStream stream) {
            PeerConnectionClient.Logger.m261d("onRemoveStream: " + stream);
            this.f3677a.executorService.execute(PeerConnectionClient$PCObserver$$Lambda$5.lambdaFactory$(this));
        }

        /* synthetic */ void m2247a() {
            this.f3677a.remoteVideoTrack = null;
        }

        public void onDataChannel(DataChannel dc) {
            this.f3677a.m2267b("tiki doesn't use data channels, but got: " + dc.label() + " anyway!");
        }

        public void onRenegotiationNeeded() {
        }
    }

    public interface PeerConnectionEvents {
        void onConnectionLost();

        void onIceCandidate(IceCandidate iceCandidate);

        void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr);

        void onIceConnected();

        void onIceDisconnected();

        void onLocalDescription(SessionDescription sessionDescription);

        void onPeerConnectionClosed();

        void onPeerConnectionError(String str);

        void onPeerConnectionStatsReady(StatsReport[] statsReportArr);

        void onReconnected();
    }

    public static class PeerConnectionParameters {
        public final boolean f3679a;
        public final boolean f3680b;
        public final boolean f3681c;
        public final boolean f3682d;
        public final int f3683e;
        public final int f3684f;
        public final int f3685g;
        public final int f3686h;
        public final String f3687i;
        public final boolean f3688j;
        public final boolean f3689k;
        public final int f3690l;
        public final String f3691m;
        public final boolean f3692n;
        public final boolean f3693o;
        public final boolean f3694p;
        public final boolean f3695q;
        public final boolean f3696r;
        public final boolean f3697s;
        public final boolean f3698t;

        public PeerConnectionParameters(boolean videoCallEnabled, boolean loopback, boolean tracing, boolean useCamera2, int videoWidth, int videoHeight, int videoFps, int videoStartBitrate, String videoCodec, boolean videoCodecHwAcceleration, boolean captureToTexture, int audioStartBitrate, String audioCodec, boolean noAudioProcessing, boolean aecDump, boolean useOpenSLES, boolean disableBuiltInAEC, boolean disableBuiltInAGC, boolean disableBuiltInNS, boolean enableLevelControl) {
            this.f3679a = videoCallEnabled;
            this.f3682d = useCamera2;
            this.f3680b = loopback;
            this.f3681c = tracing;
            this.f3683e = videoWidth;
            this.f3684f = videoHeight;
            this.f3685g = videoFps;
            this.f3686h = videoStartBitrate;
            this.f3687i = videoCodec;
            this.f3688j = videoCodecHwAcceleration;
            this.f3689k = captureToTexture;
            this.f3690l = audioStartBitrate;
            this.f3691m = audioCodec;
            this.f3692n = noAudioProcessing;
            this.f3693o = aecDump;
            this.f3694p = useOpenSLES;
            this.f3695q = disableBuiltInAEC;
            this.f3696r = disableBuiltInAGC;
            this.f3697s = disableBuiltInNS;
            this.f3698t = enableLevelControl;
        }
    }

    private class SDPObserver implements SdpObserver {
        final /* synthetic */ PeerConnectionClient f3702a;

        private SDPObserver(PeerConnectionClient peerConnectionClient) {
            this.f3702a = peerConnectionClient;
        }

        public void onCreateSuccess(SessionDescription origSdp) {
            if (this.f3702a.f3710D != null) {
                this.f3702a.m2267b("Multiple SDP create.");
                return;
            }
            String sdpDescription = origSdp.description;
            if (this.f3702a.f3735n) {
                sdpDescription = PeerConnectionClient.m2263b(sdpDescription, "ISAC", true);
            }
            if (this.f3702a.videoCallEnable) {
                sdpDescription = PeerConnectionClient.m2263b(sdpDescription, this.f3702a.f3736o, false);
            }
            SessionDescription sdp = new SessionDescription(origSdp.type, sdpDescription);
            this.f3702a.f3710D = sdp;
            this.f3702a.executorService.execute(PeerConnectionClient$SDPObserver$$Lambda$1.lambdaFactory$(this, sdp));
        }

        /* synthetic */ void m2254a(SessionDescription sdp) {
            if (this.f3702a.peerConnection != null && !this.f3702a.f3738q) {
                PeerConnectionClient.Logger.m261d("Set local SDP from " + sdp.type);
                this.f3702a.peerConnection.setLocalDescription(this.f3702a.f3728g, sdp);
            }
        }

        public void onSetSuccess() {
            this.f3702a.executorService.execute(PeerConnectionClient$SDPObserver$$Lambda$2.lambdaFactory$(this));
        }

        /* synthetic */ void m2253a() {
            if (this.f3702a.peerConnection != null && !this.f3702a.f3738q) {
                if (this.f3702a.haveOffer) {
                    if (this.f3702a.peerConnection.getRemoteDescription() == null) {
                        PeerConnectionClient.Logger.m261d("Local SDP set succesfully");
                        this.f3702a.f3708B.onLocalDescription(this.f3702a.f3710D);
                        return;
                    }
                    PeerConnectionClient.Logger.m261d("Remote SDP set succesfully");
                    this.f3702a.m2286l();
                } else if (this.f3702a.peerConnection.getLocalDescription() != null) {
                    PeerConnectionClient.Logger.m261d("Local SDP set succesfully");
                    this.f3702a.f3708B.onLocalDescription(this.f3702a.f3710D);
                    this.f3702a.m2286l();
                } else {
                    PeerConnectionClient.Logger.m261d("Remote SDP set succesfully");
                }
            }
        }

        public void onCreateFailure(String error) {
            this.f3702a.m2267b("createSDP error: " + error);
        }

        public void onSetFailure(String error) {
            this.f3702a.m2267b("setSDP error: " + error);
        }
    }

    public static class SimplePeerConnectionEvents implements PeerConnectionEvents {
        public void onConnectionLost() {
        }

        public void onIceCandidate(IceCandidate candidate) {
        }

        public void onIceCandidatesRemoved(IceCandidate[] candidates) {
        }

        public void onIceConnected() {
        }

        public void onIceDisconnected() {
        }

        public void onLocalDescription(SessionDescription sdp) {
        }

        public void onPeerConnectionClosed() {
        }

        public void onPeerConnectionError(String description) {
        }

        public void onPeerConnectionStatsReady(StatsReport[] reports) {
        }

        public void onReconnected() {
        }
    }

    private PeerConnectionClient() {
    }

    public static PeerConnectionClient getInstance() {
        return f3705d;
    }

    private static String m2256a(String codec, boolean isVideoCodec, String sdpDescription, int bitrateKbps) {
        int i;
        String[] lines = sdpDescription.split("\r\n");
        int rtpmapLineIndex = -1;
        boolean sdpFormatUpdated = false;
        String codecRtpMap = null;
        Pattern codecPattern = Pattern.compile("^a=rtpmap:(\\d+) " + codec + "(/\\d+)+[\r]?$");
        for (i = 0; i < lines.length; i++) {
            Matcher codecMatcher = codecPattern.matcher(lines[i]);
            if (codecMatcher.matches()) {
                codecRtpMap = codecMatcher.group(1);
                rtpmapLineIndex = i;
                break;
            }
        }
        if (codecRtpMap == null) {
            Logger.m269w("No rtpmap for " + codec + " codec");
            return sdpDescription;
        }
        StringBuilder newSdpDescription;
        Logger.m261d("Found " + codec + " rtpmap " + codecRtpMap + " at " + lines[rtpmapLineIndex]);
        codecPattern = Pattern.compile("^a=fmtp:" + codecRtpMap + " \\w+=\\d+.*[\r]?$");
        for (i = 0; i < lines.length; i++) {
            String bitrateSet;
            if (codecPattern.matcher(lines[i]).matches()) {
                Logger.m261d("Found " + codec + " " + lines[i]);
                if (isVideoCodec) {
                    lines[i] = lines[i] + "; x-google-start-bitrate=" + bitrateKbps;
                } else {
                    lines[i] = lines[i] + "; maxaveragebitrate=" + (bitrateKbps * PointerIconCompat.TYPE_DEFAULT);
                }
                Logger.m261d("Update remote SDP line: " + lines[i]);
                sdpFormatUpdated = true;
                newSdpDescription = new StringBuilder();
                i = 0;
                while (i < lines.length) {
                    newSdpDescription.append(lines[i]).append("\r\n");
                    if (!sdpFormatUpdated && i == rtpmapLineIndex) {
                        if (isVideoCodec) {
                            bitrateSet = "a=fmtp:" + codecRtpMap + " " + "maxaveragebitrate" + "=" + (bitrateKbps * PointerIconCompat.TYPE_DEFAULT);
                        } else {
                            bitrateSet = "a=fmtp:" + codecRtpMap + " " + "x-google-start-bitrate" + "=" + bitrateKbps;
                        }
                        Logger.m261d("Add remote SDP line: " + bitrateSet);
                        newSdpDescription.append(bitrateSet).append("\r\n");
                    }
                    i++;
                }
                return newSdpDescription.toString();
            }
        }
        newSdpDescription = new StringBuilder();
        i = 0;
        while (i < lines.length) {
            newSdpDescription.append(lines[i]).append("\r\n");
            if (isVideoCodec) {
                bitrateSet = "a=fmtp:" + codecRtpMap + " " + "maxaveragebitrate" + "=" + (bitrateKbps * PointerIconCompat.TYPE_DEFAULT);
            } else {
                bitrateSet = "a=fmtp:" + codecRtpMap + " " + "x-google-start-bitrate" + "=" + bitrateKbps;
            }
            Logger.m261d("Add remote SDP line: " + bitrateSet);
            newSdpDescription.append(bitrateSet).append("\r\n");
            i++;
        }
        return newSdpDescription.toString();
    }

    private static String m2263b(String sdpDescription, String codec, boolean isAudio) {
        String[] lines = sdpDescription.split("\r\n");
        int mLineIndex = -1;
        String codecRtpMap = null;
        Pattern codecPattern = Pattern.compile("^a=rtpmap:(\\d+) " + codec + "(/\\d+)+[\r]?$");
        String mediaDescription = "m=video ";
        if (isAudio) {
            mediaDescription = "m=audio ";
        }
        for (int i = 0; i < lines.length && (mLineIndex == -1 || codecRtpMap == null); i++) {
            if (lines[i].startsWith(mediaDescription)) {
                mLineIndex = i;
            } else {
                Matcher codecMatcher = codecPattern.matcher(lines[i]);
                if (codecMatcher.matches()) {
                    codecRtpMap = codecMatcher.group(1);
                }
            }
        }
        if (mLineIndex == -1) {
            Logger.m269w("No " + mediaDescription + " line, so can't prefer " + codec);
            return sdpDescription;
        } else if (codecRtpMap == null) {
            Logger.m269w("No rtpmap for " + codec);
            return sdpDescription;
        } else {
            Logger.m261d("Found " + codec + " rtpmap " + codecRtpMap + ", prefer at " + lines[mLineIndex]);
            String[] origMLineParts = lines[mLineIndex].split(" ");
            if (origMLineParts.length > 3) {
                StringBuilder newMLine = new StringBuilder();
                int origPartIndex = 0 + 1;
                newMLine.append(origMLineParts[0]).append(" ");
                int origPartIndex2 = origPartIndex + 1;
                newMLine.append(origMLineParts[origPartIndex]).append(" ");
                origPartIndex = origPartIndex2 + 1;
                newMLine.append(origMLineParts[origPartIndex2]).append(" ");
                newMLine.append(codecRtpMap);
                for (origPartIndex2 = origPartIndex; origPartIndex2 < origMLineParts.length; origPartIndex2++) {
                    if (!origMLineParts[origPartIndex2].equals(codecRtpMap)) {
                        newMLine.append(" ").append(origMLineParts[origPartIndex2]);
                    }
                }
                lines[mLineIndex] = newMLine.toString();
                Logger.m261d("Change media description: " + lines[mLineIndex]);
            } else {
                Logger.m263e("Wrong SDP media description format: " + lines[mLineIndex]);
            }
            StringBuilder newSdpDescription = new StringBuilder();
            for (String line : lines) {
                newSdpDescription.append(line).append("\r\n");
            }
            return newSdpDescription.toString();
        }
    }

    public void setPeerConnectionFactoryOptions(Options options) {
        this.f3726b = options;
    }

    public void createPeerConnectionFactory(final Context context, PeerConnectionParameters peerConnectionParameters, PeerConnectionEvents events) {
        this.f3747z = peerConnectionParameters;
        this.f3708B = events;
        this.videoCallEnable = peerConnectionParameters.f3679a;
        this.peerConnectionFactory = null;
        this.peerConnection = null;
        this.f3735n = false;
        this.f3737p = false;
        this.f3738q = false;
        this.iceCandidates = null;
        this.f3710D = null;
        this.f3711E = null;
        this.videoCapturer = null;
        this.f3714H = true;
        this.localVideoTrack = null;
        this.remoteVideoTrack = null;
        this.f3739r = new Timer();
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                createPeerConnectionFactoryInternal(context);
            }
        });
    }

    public void createPeerConnection(Callbacks remoteRender) {
        this.removeRenderer = remoteRender;
        this.executorService.execute(PeerConnectionClient$$Lambda$2.lambdaFactory$(this));
    }

    /* synthetic */ void m2308e() {
        enableStatsEvents(true, PointerIconCompat.TYPE_DEFAULT);
    }

    public void createPeerConnectionInternal(final EglBase.Context renderEGLContext, Callbacks localRender, IMRtcClient client, IceServer iceServer) {
        if (this.f3747z == null) {
            Logger.m263e("Creating peer connection without initializing factory.");
            return;
        }
        this.localRenderer = localRender;
        this.imClient = client;
        if (iceServer == null) {
            this.iceServers = new IceServerList();
        } else {
            this.iceServers.clear();
            this.iceServers.add(iceServer);
        }
        this.executorService.execute(new Runnable() {
                                         @Override
                                         public void run() {
                                             m2279h();
                                             createPeerConnection(renderEGLContext);
                                         }
                                     });
    }

    public void close() {
        this.executorService.execute(PeerConnectionClient$$Lambda$4.lambdaFactory$(this));
    }

    public boolean isVideoCallEnabled() {
        return this.videoCallEnable;
    }

    private void createPeerConnectionFactoryInternal(Context context) {
        boolean z;
        Logger.m261d("Create peer connection factory. Use video: " + this.f3747z.f3679a);
        this.f3738q = false;
        PeerConnectionFactory.initializeFieldTrials(BuildConfig.VERSION_NAME);
        String str = (MediaCodecVideoDecoder.isH264HwSupported() && MediaCodecVideoEncoder.isH264HwSupported()) ? "H264" : "VP8";
        this.f3736o = str;
        if (this.videoCallEnable && this.f3747z.f3687i != null) {
            if (this.f3747z.f3687i.equals("VP9")) {
                this.f3736o = "VP9";
            } else if (this.f3747z.f3687i.equals("H264")) {
                this.f3736o = "H264";
            }
        }
        Logger.m261d("Pereferred video codec: " + this.f3736o);
        if (this.f3747z.f3691m == null || !this.f3747z.f3691m.equals("ISAC")) {
            z = false;
        } else {
            z = true;
        }
        this.f3735n = z;
        if (this.f3747z.f3694p) {
            Logger.m261d("Allow OpenSL ES audio if device supports it");
            WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(false);
        } else {
            Logger.m261d("Disable OpenSL ES audio even if device supports it");
            WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(true);
        }
        if (!PeerConnectionFactory.initializeAndroidGlobals(context, true, true, this.f3747z.f3688j)) {
            this.f3708B.onPeerConnectionError("Failed to initializeAndroidGlobals");
        }
        if (this.f3726b != null) {
            Logger.m261d("Factory networkIgnoreMask option: " + this.f3726b.networkIgnoreMask);
        }
        this.peerConnectionFactory = new PeerConnectionFactory(this.f3726b);
        Logger.m261d("Peer connection factory created.");
    }

    private void m2279h() {
        this.f3742u = new MediaConstraints();
        if (this.f3747z.f3680b) {
            this.f3742u.optional.add(new KeyValuePair("DtlsSrtpKeyAgreement", "false"));
        } else {
            this.f3742u.optional.add(new KeyValuePair("DtlsSrtpKeyAgreement", "true"));
        }
        this.f3712F = CameraEnumerationAndroid.getDeviceCount();
        if (this.f3712F <= 0) {
            Logger.m269w("No camera on device. Switch to audio only call.");
            this.videoCallEnable = false;
        }
        if (this.videoCallEnable) {
            this.videoConstraints = new MediaConstraints();
            List<CaptureFormat> supportedFormats = Camera1Enumerator.m2193a(this.f3712F - 1);
            if (this.f3722P == f3703a) {
                Logger.m261d("Video Quality: VIDEO");
                this.videoConstraints.optional.add(new KeyValuePair("maxWidth", Integer.toString(640)));
                this.videoConstraints.optional.add(new KeyValuePair("maxHeight", Integer.toString(480)));
                this.f3723Q = 640;
                this.f3724R = 480;
            } else if (this.f3722P == 1) {
                filteredFormat = new CaptureFormat(640, 480, 1, 15);
                if (supportedFormats != null) {
                    filteredFormat = (CaptureFormat) Collections.min(supportedFormats, new C05632(this));
                }
                Logger.m261d("Video Quality: HIGH");
                this.videoConstraints.optional.add(new KeyValuePair("maxWidth", Integer.toString(filteredFormat.width)));
                this.videoConstraints.optional.add(new KeyValuePair("maxHeight", Integer.toString(filteredFormat.height)));
                this.f3723Q = filteredFormat.width;
                this.f3724R = filteredFormat.height;
            } else if (this.f3722P == 3) {
                filteredFormat = new CaptureFormat(MsgDataType.CALL_CLOSE_MSG, avcodec.AV_CODEC_ID_XBM, 1, 15);
                if (supportedFormats != null) {
                    filteredFormat = (CaptureFormat) Collections.min(supportedFormats, new C05643(this));
                }
                Logger.m261d("Video Quality: LOW");
                this.videoConstraints.optional.add(new KeyValuePair("maxWidth", Integer.toString(filteredFormat.width)));
                this.videoConstraints.optional.add(new KeyValuePair("maxHeight", Integer.toString(filteredFormat.height)));
                this.f3723Q = filteredFormat.width;
                this.f3724R = filteredFormat.height;
            } else {
                filteredFormat = new CaptureFormat(480, 360, 1, 15);
                if (supportedFormats != null) {
                    filteredFormat = (CaptureFormat) Collections.min(supportedFormats, new C05654(this));
                }
                Logger.m261d("Video Quality: NORMAL");
                this.videoConstraints.optional.add(new KeyValuePair("maxWidth", Integer.toString(filteredFormat.width)));
                this.videoConstraints.optional.add(new KeyValuePair("maxHeight", Integer.toString(filteredFormat.height)));
                this.f3723Q = filteredFormat.width;
                this.f3724R = filteredFormat.height;
            }
            if (this.f3722P == f3703a) {
                this.videoConstraints.mandatory.add(new KeyValuePair("minHeight", Integer.toString(480)));
                this.videoConstraints.mandatory.add(new KeyValuePair("minWidth", Integer.toString(640)));
            } else {
                this.videoConstraints.mandatory.add(new KeyValuePair("minHeight", Integer.toString(avcodec.AV_CODEC_ID_A64_MULTI5)));
                this.videoConstraints.mandatory.add(new KeyValuePair("minWidth", Integer.toString(avcodec.AV_CODEC_ID_WEBP)));
            }
            this.videoConstraints.mandatory.add(new KeyValuePair("minFrameRate", Integer.toString(1)));
            this.videoConstraints.mandatory.add(new KeyValuePair("maxFrameRate", Integer.toString(15)));
        }
        this.f3744w = new MediaConstraints();
        if (this.f3747z.f3692n) {
            Logger.m261d("Disabling audio processing");
            this.f3744w.mandatory.add(new KeyValuePair("googEchoCancellation", "false"));
            this.f3744w.mandatory.add(new KeyValuePair("googAutoGainControl", "false"));
            this.f3744w.mandatory.add(new KeyValuePair("googHighpassFilter", "false"));
            this.f3744w.mandatory.add(new KeyValuePair("googNoiseSuppression", "false"));
        }
        if (this.f3747z.f3698t) {
            this.f3744w.mandatory.add(new KeyValuePair("googAutoGainControl", "true"));
        }
        this.f3746y = new MediaConstraints();
        this.f3746y.mandatory.add(new KeyValuePair("OfferToReceiveAudio", "true"));
        if (this.videoCallEnable || this.f3747z.f3680b) {
            this.f3746y.mandatory.add(new KeyValuePair("OfferToReceiveVideo", "true"));
        } else {
            this.f3746y.mandatory.add(new KeyValuePair("OfferToReceiveVideo", "false"));
        }
    }

    public void setLocalRender(Callbacks localRender) {
        if (!(this.localRenderer == null || this.f3721O == null || this.localVideoTrack == null)) {
            this.localVideoTrack.removeRenderer(this.f3721O);
        }
        this.localRenderer = localRender;
        if (this.localRenderer != null && this.localVideoTrack != null) {
            this.f3721O = new VideoRenderer(this.localRenderer);
            this.localVideoTrack.addRenderer(this.f3721O);
        }
    }

    private void createPeerConnection(EglBase.Context renderEGLContext) {
        if (this.peerConnectionFactory == null || this.f3738q) {
            Logger.m263e("Peerconnection factory is not created");
            return;
        }
        Logger.m261d("Create peer connection.");
        Logger.m261d("PCConstraints: " + this.f3742u.toString());
        if (this.videoConstraints != null) {
            Logger.m261d("VideoConstraints: " + this.videoConstraints.toString());
        }
        this.iceCandidates = new LinkedList<>();
        if (this.videoCallEnable) {
            Logger.m261d("EGLContext: " + renderEGLContext);
            this.peerConnectionFactory.setVideoHwAccelerationOptions(renderEGLContext, renderEGLContext);
        }
        this.rtcConfiguration = new RTCConfiguration(this.iceServers);
        this.rtcConfiguration.iceTransportsType = IceTransportsType.ALL;
        this.rtcConfiguration.tcpCandidatePolicy = TcpCandidatePolicy.DISABLED;
        this.rtcConfiguration.continualGatheringPolicy = ContinualGatheringPolicy.GATHER_CONTINUALLY;
        this.rtcConfiguration.audioJitterBufferFastAccelerate = true;
        this.rtcConfiguration.bundlePolicy = BundlePolicy.MAXBUNDLE;
        this.rtcConfiguration.keyType = KeyType.ECDSA;
        this.peerConnection = this.peerConnectionFactory.createPeerConnection(this.rtcConfiguration, this.f3742u, this.f3727f);
        this.haveOffer = false;
        Logger.m261d("init video source: videoCallEnabled:" + this.videoCallEnable);
        this.f3711E = this.peerConnectionFactory.createLocalMediaStream("ARDAMS");
        if (this.videoCallEnable) {
            if (this.videoCapturer != null) {
                try {
                    Logger.m261d("stop already exist video capturer: videoSource:" + this.videoSource + " videoSourceStopped:" + this.f3737p);
                    this.videoCapturer.stopCapture();
                    this.videoCapturer.dispose();
                } catch (Exception e) {
                    Logger.m262d("dispose video capturer error:", e);
                }
            }
            if (this.f3747z.f3682d) {
                Logger.m261d("use Camera2");
                m2262a(new Camera2Enumerator(ChatApp.getInstance()));
            } else {
                Logger.m261d("use Camera");
                String cameraDeviceName = CameraEnumerationAndroid.getDeviceName(0);
                String frontCameraDeviceName = CameraEnumerationAndroid.getNameOfFrontFacingDevice();
                if (this.f3712F > 1 && frontCameraDeviceName != null) {
                    cameraDeviceName = frontCameraDeviceName;
                }
                Logger.m261d("Opening camera: " + cameraDeviceName);
                this.videoCapturer = BiuVideoCapturer2.create(cameraDeviceName, null, this.f3747z.f3689k);
            }
            if (this.videoCapturer == null) {
                m2267b("Failed to open camera");
                return;
            }
            this.f3711E.addTrack(m2260a(this.videoCapturer));
        }
        this.f3711E.addTrack(m2284k());
        this.peerConnection.addStream(this.f3711E);
        if (this.f3747z.f3693o) {
            try {
                this.f3745x = ParcelFileDescriptor.open(new File(Environment.DIRECTORY_DOWNLOADS + "/audio.aecdump"), 1006632960);
                this.peerConnectionFactory.startAecDump(this.f3745x.getFd(), -1);
            } catch (IOException e2) {
                Logger.m264e("Can not open aecdump file", e2);
            }
        }
        Logger.m261d("Default Peer connection created.");
        if (this.f3725S != null) {
            this.f3725S.call(this);
        }
    }

    private void m2281i() {
        if (this.f3739r != null) {
            this.f3739r.cancel();
            this.f3739r.purge();
            this.f3739r = null;
        }
        if (this.peerConnectionFactory != null && this.f3747z.f3693o) {
            this.peerConnectionFactory.stopAecDump();
        }
        Logger.m261d("Closing peer connection.");
        if (this.peerConnection != null) {
            this.peerConnection.dispose();
            this.peerConnection = null;
        }
        if (this.audioSource != null) {
            this.audioSource.dispose();
            this.audioSource = null;
        }
        Logger.m261d("Closing video source.");
        if (this.videoSource != null) {
            try {
                this.videoCapturer.stopCapture();
            } catch (InterruptedException e) {
                Logger.m264e("stopCapture fail", e);
            }
            this.videoSource.dispose();
            this.videoSource = null;
        }
        Logger.m261d("Closing peer connection factory.");
        if (this.peerConnectionFactory != null) {
            this.peerConnectionFactory = null;
        }
        this.f3726b = null;
        Logger.m261d("Closing peer connection done.");
        this.f3708B.onPeerConnectionClosed();
    }

    public void recreateConnection(final Callbacks remoteRender, final IceServer iceServer) {
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                m2279h();
                m2271c(remoteRender, iceServer);
                enableStatsEvents(true, PointerIconCompat.TYPE_DEFAULT);
            }
        });
        this.removeRenderer = remoteRender;
    }

    /* synthetic */ void m2305b(Callbacks remoteRender, IceServer iceServer) {
        m2279h();
        m2271c(remoteRender, iceServer);
        enableStatsEvents(true, PointerIconCompat.TYPE_DEFAULT);
    }

    /* synthetic */ void m2301a(Callbacks remoteRenderer, IceServer iceServer) {
        m2271c(remoteRenderer, iceServer);
    }

    public void hangup(Callbacks remoteRenderer, IceServer iceServer) {
        this.executorService.execute(PeerConnectionClient$$Lambda$6.lambdaFactory$(this, remoteRenderer, iceServer));
    }

    private void m2271c(Callbacks remoteVideoRender, IceServer iceServer) {
        if (this.f3739r != null) {
            this.f3739r.cancel();
            this.f3739r.purge();
            this.f3739r = null;
        }
        this.peerConnection.removeStream(this.f3711E);
        if (this.remoteVideoTrack != null) {
            this.remoteVideoTrack.removeRenderer(new VideoRenderer(remoteVideoRender));
            this.remoteVideoTrack = null;
        }
        this.peerConnection.close();
        if (this.audioSource != null) {
            this.audioSource.dispose();
            this.audioSource = null;
        }
        List<IceServer> iceServers;
        if (iceServer == null) {
            iceServers = new ArrayList();
            iceServers.add(defaultIceServer);
            this.rtcConfiguration.iceServers = iceServers;
        } else {
            iceServers = new ArrayList();
            iceServers.add(iceServer);
            this.rtcConfiguration.iceServers = iceServers;
        }
        this.rtcConfiguration.iceTransportsType = IceTransportsType.ALL;
        this.peerConnection = this.peerConnectionFactory.createPeerConnection(this.rtcConfiguration, this.f3742u, this.f3727f);
        this.peerConnection.addStream(this.f3711E);
        this.f3710D = null;
    }

    public boolean isHDVideo() {
        if (!this.videoCallEnable) {
            return false;
        }
        int minWidth = 0;
        int minHeight = 0;
        for (KeyValuePair keyValuePair : this.videoConstraints.mandatory) {
            if (keyValuePair.getKey().equals("minWidth")) {
                try {
                    minWidth = Integer.parseInt(keyValuePair.getValue());
                } catch (NumberFormatException e) {
                    Logger.m263e("Can not parse video width from video constraints");
                }
            } else if (keyValuePair.getKey().equals("minHeight")) {
                try {
                    minHeight = Integer.parseInt(keyValuePair.getValue());
                } catch (NumberFormatException e2) {
                    Logger.m263e("Can not parse video height from video constraints");
                }
            }
        }
        if (minWidth * minHeight >= 921600) {
            return true;
        }
        return false;
    }

    public void setVideoEnabled(boolean enable) {
        this.executorService.execute(PeerConnectionClient$$Lambda$7.lambdaFactory$(this, enable));
    }

    /* synthetic */ void m2302a(boolean enable) {
        this.f3714H = enable;
        if (this.localVideoTrack != null) {
            this.localVideoTrack.setEnabled(this.f3714H);
        }
        if (this.remoteVideoTrack != null) {
            this.remoteVideoTrack.setEnabled(this.f3714H);
        }
    }

    private void m2283j() {
        if (this.peerConnection != null && !this.f3738q && !this.peerConnection.getStats(PeerConnectionClient$$Lambda$8.lambdaFactory$(this), null)) {
            Logger.m263e("getStats() returns false!");
        }
    }

    /* synthetic */ void m2303a(StatsReport[] reports) {
        this.f3708B.onPeerConnectionStatsReady(reports);
    }

    public void enableStatsEvents(boolean enable, int periodMs) {
        if (enable) {
            this.f3739r = new Timer();
            try {
                this.f3739r.schedule(new C05665(this), 0, (long) periodMs);
            } catch (Exception e) {
                Logger.m264e("Can not schedule statistics timer", e);
            }
        } else if (this.f3739r != null) {
            this.f3739r.cancel();
        }
    }

    public void createOffer() {
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (peerConnection != null && !f3738q) {
                    Logger.m261d("PC Create OFFER");
                    haveOffer = true;
                    peerConnection.createOffer(f3728g, f3746y);
                }
            }
        });
    }

    public void createAnswer() {
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (peerConnection != null && !f3738q) {
                    Logger.m261d("PC create ANSWER");
                    haveOffer = false;
                    peerConnection.createAnswer(f3728g, f3746y);
                }
            }
        });
    }

    public void addRemoteIceCandidate(IceCandidate candidate) {
        this.executorService.execute(PeerConnectionClient$$Lambda$11.lambdaFactory$(this, candidate));
    }

    /* synthetic */ void m2299a(IceCandidate candidate) {
        if (this.peerConnection != null && !this.f3738q) {
            if (this.iceCandidates != null) {
                this.iceCandidates.add(candidate);
            } else {
                this.peerConnection.addIceCandidate(candidate);
            }
        }
    }

    public void setRemoteDescription(SessionDescription sdp) {
        this.executorService.execute(PeerConnectionClient$$Lambda$12.lambdaFactory$(this, sdp));
    }

    /* synthetic */ void m2300a(SessionDescription sdp) {
        if (this.peerConnection != null && !this.f3738q) {
            String sdpDescription = sdp.description;
            if (this.f3735n) {
                sdpDescription = m2263b(sdpDescription, "ISAC", true);
            }
            if (this.videoCallEnable) {
                sdpDescription = m2263b(sdpDescription, this.f3736o, false);
            }
            if (this.videoCallEnable && this.f3747z.f3686h > 0) {
                sdpDescription = m2256a("H264", true, m2256a("VP9", true, m2256a("VP8", true, sdpDescription, this.f3747z.f3686h), this.f3747z.f3686h), this.f3747z.f3686h);
            }
            if (this.f3747z.f3690l > 0) {
                sdpDescription = m2256a("opus", false, sdpDescription, this.f3747z.f3690l);
            }
            Logger.m261d("Set remote SDP.");
            SessionDescription sdpRemote = new SessionDescription(sdp.type, sdpDescription);
            Logger.m261d("setRemoteDescription " + sdpRemote.description);
            this.peerConnection.setRemoteDescription(this.f3728g, sdpRemote);
        }
    }

    public void stopVideoSource() {
        this.executorService.execute(PeerConnectionClient$$Lambda$13.lambdaFactory$(this));
    }

    /* synthetic */ void m2304b() {
        if (this.videoSource != null && !this.f3737p) {
            Logger.m261d("Stop video source.");
            this.videoSource.stop();
            this.f3737p = true;
        }
    }

    public void startVideoSource() {
        this.executorService.execute(PeerConnectionClient$$Lambda$14.lambdaFactory$(this));
    }

    /* synthetic */ void m2293a() {
        Logger.m261d("Restart video source.1");
        if (this.videoSource != null && this.f3737p) {
            Logger.m261d("Restart video source.");
            this.videoSource.restart();
            this.f3737p = false;
        }
    }

    private void m2267b(String errorMessage) {
        Logger.m263e("Peerconnection error: " + errorMessage);
        this.executorService.execute(PeerConnectionClient$$Lambda$15.lambdaFactory$(this, errorMessage));
    }

    /* synthetic */ void m2296a(String errorMessage) {
        if (!this.f3738q) {
            this.f3708B.onPeerConnectionError(errorMessage);
            this.f3738q = true;
        }
    }

    private void m2262a(CameraEnumerator enumerator) {
        int i = 0;
        String[] deviceNames = enumerator.getDeviceNames();
        Logger.m261d("Looking for front facing cameras.");
        for (String deviceName : deviceNames) {
            String deviceName2;
            if (enumerator.isFrontFacing(deviceName2)) {
                Logger.m261d("Creating front facing camera capturer.");
                this.videoCapturer = enumerator.createCapturer(deviceName2, null);
                if (this.videoCapturer != null) {
                    return;
                }
            }
        }
        Logger.m261d("Looking for other cameras.");
        int length = deviceNames.length;
        while (i < length) {
            deviceName2 = deviceNames[i];
            if (!enumerator.isFrontFacing(deviceName2)) {
                Logger.m261d("Creating other camera capturer.");
                this.videoCapturer = enumerator.createCapturer(deviceName2, null);
                if (this.videoCapturer != null) {
                    return;
                }
            }
            i++;
        }
    }

    private VideoTrack m2260a(VideoCapturer capturer) {
        Logger.m261d("createVideoTrack: renderVideo:" + this.f3714H);
        this.videoSource = this.peerConnectionFactory.createVideoSource(capturer, this.videoConstraints);
        this.localVideoTrack = this.peerConnectionFactory.createVideoTrack("ARDAMSv0", this.videoSource);
        this.f3721O = new VideoRenderer(this.localRenderer);
        this.localVideoTrack.addRenderer(this.f3721O);
        this.localVideoTrack.setEnabled(this.f3714H);
        return this.localVideoTrack;
    }

    private AudioTrack m2284k() {
        this.audioSource = this.peerConnectionFactory.createAudioSource(this.f3744w);
        this.audioTrack = this.peerConnectionFactory.createAudioTrack("ARDAMSa0", this.audioSource);
        this.audioTrack.setEnabled(true);
        return this.audioTrack;
    }

    private void m2286l() {
        if (this.iceCandidates != null) {
            Iterator it = this.iceCandidates.iterator();
            while (it.hasNext()) {
                this.peerConnection.addIceCandidate((IceCandidate) it.next());
            }
            this.iceCandidates = null;
        }
    }

    private void m2268b(CameraSwitchHandler cameraSwitchHandler) {
        if (!this.videoCallEnable || this.f3712F < 2 || this.f3738q || this.videoCapturer == null) {
            Logger.m263e("Failed to switch camera. Video: " + this.videoCallEnable + ". Error : " + this.f3738q + ". Number of cameras: " + this.f3712F);
            return;
        }
        Logger.m261d("Switch camera");
        this.videoCapturer.switchCamera(cameraSwitchHandler);
    }

    /* synthetic */ void m2297a(CameraSwitchHandler cameraSwitchHandler) {
        m2268b(cameraSwitchHandler);
    }

    public void switchCamera(CameraSwitchHandler cameraSwitchHandler) {
        this.executorService.execute(PeerConnectionClient$$Lambda$16.lambdaFactory$(this, cameraSwitchHandler));
    }

    /* synthetic */ void m2294a(int width, int height, int framerate) {
        m2264b(width, height, framerate);
    }

    public void changeCaptureFormat(int width, int height, int framerate) {
        this.executorService.execute(PeerConnectionClient$$Lambda$17.lambdaFactory$(this, width, height, framerate));
    }

    private void m2264b(int width, int height, int framerate) {
        if (!this.videoCallEnable || this.f3738q || this.videoCapturer == null) {
            Logger.m263e("Failed to change capture format. Video: " + this.videoCallEnable + ". Error : " + this.f3738q);
        } else {
            this.videoCapturer.onOutputFormatRequest(width, height, framerate);
        }
    }

    public CameraVideoCapturer getVideoCapturer() {
        return this.videoCapturer;
    }

    public int getQuality() {
        Logger.m261d("getQuality:" + this.f3722P);
        return this.f3722P;
    }

    public void setQuality(int quality) {
        Logger.m261d("setQuality:oQ:" + this.f3722P + " nQ:" + quality);
        if (quality != this.f3722P) {
            if (quality == f3703a) {
                this.f3722P = quality;
                if (!(this.videoCapturer == null || this.videoSource == null || this.f3737p)) {
                    this.videoCapturer.changeCaptureFormat(640, 480, 15);
                    return;
                }
            }
            if (quality == 1 || quality == 3 || quality == 2) {
                this.f3722P = quality;
                if (this.videoCapturer != null && this.videoSource != null && !this.f3737p) {
                    Logger.m261d("changeCaptureFormat: quality:" + this.f3722P);
                    if (this.f3722P == 1) {
                        this.videoCapturer.changeCaptureFormat(640, 480, 15);
                    } else if (this.f3722P == 2) {
                        this.videoCapturer.changeCaptureFormat(480, 360, 15);
                    } else {
                        this.videoCapturer.changeCaptureFormat(MsgDataType.CALL_CLOSE_MSG, avcodec.AV_CODEC_ID_XBM, 15);
                    }
                }
            }
        }
    }

    public void setOnPeerConnectionCreated(CallBack<PeerConnectionClient> onCreated) {
        this.f3725S = onCreated;
    }
}
