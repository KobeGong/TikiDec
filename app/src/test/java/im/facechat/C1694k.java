package im.facechat;

import android.text.TextUtils;
import com.sina.weibo.sdk.component.ShareRequestParam;
import im.facechat.C1678h.C1664a;
import im.facechat.common.p043c.C1643b;
import im.facechat.common.p044a.C1635a;
import im.facechat.common.p044a.C1635a.C1634a;
import im.facechat.common.p045b.C1639b;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.rtsp.RtspDecoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;
import java.net.URI;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;
import org.json.JSONObject;
import p042u.aly.dd;
import tv.danmaku.ijk.media.player.BuildConfig;

/* compiled from: FacechatWebSocketClient */
class C1694k implements C1634a {
    private static final Class<?> f11354a = C1694k.class;
    private final URI f11355b;
    private final C1664a f11356c;
    private C1676b f11357d;
    private C1699m f11358e;
    private NioEventLoopGroup f11359f;
    private Channel f11360g;
    private volatile boolean f11361h = true;
    private C1693d f11362i = C1693d.NEW;
    private long f11363j;
    private Timer f11364k;
    private ScheduledFuture f11365l;
    private Random f11366m;

    /* compiled from: FacechatWebSocketClient */
    interface C1676b {
        void mo4064a(int i);

        void mo4065a(JSONObject jSONObject, int i);
    }

    /* compiled from: FacechatWebSocketClient */
    class C16831 implements Runnable {
        final /* synthetic */ C1694k f11330a;

        C16831(C1694k c1694k) {
            this.f11330a = c1694k;
        }

        public void run() {
            try {
                this.f11330a.m8079h();
            } catch (Throwable e) {
                C1639b.m7917a(C1694k.f11354a, "doConnect fail", e);
            }
        }
    }

    /* compiled from: FacechatWebSocketClient */
    class C16883 implements ChannelFutureListener {
        final /* synthetic */ C1694k f11339a;

        /* compiled from: FacechatWebSocketClient */
        class C16871 implements Runnable {
            final /* synthetic */ C16883 f11338a;

            C16871(C16883 c16883) {
                this.f11338a = c16883;
            }

            public void run() {
                this.f11338a.f11339a.m8092a();
            }
        }

        C16883(C1694k c1694k) {
            this.f11339a = c1694k;
        }

        public /* synthetic */ void operationComplete(Future future) throws Exception {
            m8062a((ChannelFuture) future);
        }

        public void m8062a(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                C1639b.m7916a(C1694k.f11354a, "connect success");
                this.f11339a.f11362i = C1693d.CONNECTED;
            } else if (this.f11339a.f11362i != C1693d.MANUAL_DISCONNECTED) {
                if (this.f11339a.f11365l != null) {
                    this.f11339a.f11365l.cancel(true);
                }
                this.f11339a.f11365l = channelFuture.channel().eventLoop().schedule(new C16871(this), this.f11339a.m8083j(), TimeUnit.MILLISECONDS);
            }
        }
    }

    /* compiled from: FacechatWebSocketClient */
    class C16894 extends TimerTask {
        final /* synthetic */ C1694k f11340a;

        C16894(C1694k c1694k) {
            this.f11340a = c1694k;
        }

        public void run() {
            this.f11340a.m8089n();
        }
    }

    /* compiled from: FacechatWebSocketClient */
    private class C1690a extends IdleStateHandler {
        final /* synthetic */ C1694k f11341a;

        public C1690a(C1694k c1694k, int i, int i2, int i3) {
            this.f11341a = c1694k;
            super(i, i2, i3);
        }

        public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
            super.userEventTriggered(channelHandlerContext, obj);
            C1639b.m7916a(C1694k.f11354a, "class IdleStatePingHandler :[userEventTriggered] " + obj);
            if (obj instanceof IdleStateEvent) {
                IdleStateEvent idleStateEvent = (IdleStateEvent) obj;
                if (!idleStateEvent.state().equals(IdleState.READER_IDLE) && !idleStateEvent.state().equals(IdleState.WRITER_IDLE) && idleStateEvent.state().equals(IdleState.ALL_IDLE)) {
                    C1639b.m7916a(C1694k.f11354a, "ALL_IDLE");
                    this.f11341a.m8088m();
                    return;
                }
                return;
            }
            this.f11341a.m8087l();
        }
    }

    /* compiled from: FacechatWebSocketClient */
    private class C1692c extends SimpleChannelInboundHandler<Object> {
        final /* synthetic */ C1694k f11345a;
        private final WebSocketClientHandshaker f11346b;
        private ChannelPromise f11347c;

        public C1692c(C1694k c1694k, WebSocketClientHandshaker webSocketClientHandshaker) {
            this.f11345a = c1694k;
            this.f11346b = webSocketClientHandshaker;
        }

        public ChannelFuture m8064a() {
            return this.f11347c;
        }

        public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
            this.f11347c = channelHandlerContext.newPromise();
        }

        public void channelActive(ChannelHandlerContext channelHandlerContext) {
            this.f11346b.handshake(channelHandlerContext.channel());
        }

        public void channelInactive(ChannelHandlerContext channelHandlerContext) {
            C1639b.m7916a(C1694k.f11354a, "WebSocket Client disconnected!");
            if (this.f11345a.f11357d != null) {
                this.f11345a.f11357d.mo4064a(-1);
            }
            if (!this.f11345a.f11361h) {
                return;
            }
            if (this.f11345a.f11362i == C1693d.NEW || this.f11345a.f11362i == C1693d.CONNECTED) {
                this.f11345a.f11362i = C1693d.DISCONNECTED;
            }
        }

        public void channelRead0(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
            this.f11345a.m8087l();
            this.f11345a.f11363j = System.nanoTime();
            Channel channel = channelHandlerContext.channel();
            if (!this.f11346b.isHandshakeComplete()) {
                this.f11346b.finishHandshake(channel, (FullHttpResponse) obj);
                C1639b.m7916a(C1694k.f11354a, "WebSocket Client connected!");
                this.f11347c.setSuccess();
                if (this.f11345a.f11357d != null) {
                    this.f11345a.f11357d.mo4064a(2);
                }
            } else if (obj instanceof FullHttpResponse) {
                FullHttpResponse fullHttpResponse = (FullHttpResponse) obj;
                throw new IllegalStateException("Unexpected FullHttpResponse (getStatus=" + fullHttpResponse.getStatus() + ", content=" + fullHttpResponse.content().toString(CharsetUtil.UTF_8) + ')');
            } else {
                WebSocketFrame webSocketFrame = (WebSocketFrame) obj;
                if (webSocketFrame instanceof TextWebSocketFrame) {
                    TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) webSocketFrame;
                    C1639b.m7916a(C1694k.f11354a, "WebSocket Client received message: " + textWebSocketFrame.text());
                    m8063a(textWebSocketFrame.text());
                } else if (webSocketFrame instanceof PongWebSocketFrame) {
                    C1639b.m7916a(C1694k.f11354a, "WebSocket Client received pong");
                } else if (webSocketFrame instanceof CloseWebSocketFrame) {
                    C1639b.m7916a(C1694k.f11354a, "WebSocket Client received closing");
                    channel.close();
                }
            }
        }

        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
            C1639b.m7917a(C1694k.f11354a, "exception caught", th);
            if (!this.f11347c.isDone()) {
                this.f11347c.setFailure(th);
            }
            channelHandlerContext.close();
        }

        private void m8063a(String str) {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject;
                try {
                    jSONObject = new JSONObject(str);
                } catch (Throwable e) {
                    C1639b.m7917a(C1694k.f11354a, "convert to json failed : ", e);
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    final int optInt = jSONObject.optInt("cmd");
                    switch (optInt) {
                        case 8002:
                            this.f11345a.m8077g();
                            return;
                        case 8003:
                            C1639b.m7916a(C1694k.f11354a, "server pong");
                            return;
                        case 8004:
                            C1639b.m7921c(C1694k.f11354a, "you have been kicked out");
                            this.f11345a.f11362i = C1693d.MANUAL_DISCONNECTED;
                            return;
                        default:
                            this.f11345a.f11358e.execute(new Runnable(this) {
                                final /* synthetic */ C1692c f11344c;

                                public void run() {
                                    if (this.f11344c.f11345a.f11357d != null) {
                                        this.f11344c.f11345a.f11357d.mo4065a(jSONObject, optInt);
                                    }
                                }
                            });
                            return;
                    }
                }
            }
        }
    }

    /* compiled from: FacechatWebSocketClient */
    private enum C1693d {
        NEW,
        CONNECTED,
        DISCONNECTED,
        MANUAL_DISCONNECTED,
        BACKGROUND
    }

    C1694k(URI uri, C1699m c1699m, C1676b c1676b, C1664a c1664a) {
        this.f11355b = uri;
        this.f11358e = c1699m;
        this.f11357d = c1676b;
        C1635a.m7888a().m7894a((C1634a) this);
        this.f11357d.mo4064a(0);
        this.f11366m = new Random();
        this.f11356c = c1664a;
        m8092a();
    }

    private static void m8069a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    void m8092a() {
        m8087l();
        this.f11358e.execute(new C16831(this));
    }

    void m8093a(String str) {
        if (this.f11360g != null) {
            C1639b.m7916a(f11354a, "ready to send simple message: " + str);
            this.f11360g.writeAndFlush(new TextWebSocketFrame(str));
        }
    }

    String m8090a(int i, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        C1694k.m8069a(jSONObject, "cmd", Integer.valueOf(i));
        C1694k.m8069a(jSONObject, ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA, (Object) str);
        C1694k.m8069a(jSONObject, "extras", (Object) str2);
        String jSONObject2 = jSONObject.toString();
        if (this.f11360g != null) {
            C1639b.m7916a(f11354a, "ready to send message: " + jSONObject2);
            this.f11360g.writeAndFlush(new TextWebSocketFrame(jSONObject2));
        }
        return jSONObject2;
    }

    String m8091a(int i, JSONObject jSONObject, String str) {
        return m8090a(i, jSONObject.toString(), str);
    }

    private void m8076f() {
        m8090a(8002, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME);
    }

    private void m8077g() {
        m8090a(8003, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME);
    }

    private void m8079h() throws SSLException {
        if (this.f11361h && this.f11362i != C1693d.MANUAL_DISCONNECTED && this.f11362i != C1693d.CONNECTED) {
            final int port = this.f11355b.getPort();
            final String host = this.f11355b.getHost();
            TrustManagerFactory a = C1697l.m8097a();
            if (a != null) {
                final SslContext build = SslContextBuilder.forClient().trustManager(a).build();
                this.f11359f = new NioEventLoopGroup(3);
                try {
                    final C1692c c1692c = new C1692c(this, WebSocketClientHandshakerFactory.newHandshaker(this.f11355b, WebSocketVersion.V13, null, false, new DefaultHttpHeaders()));
                    Bootstrap bootstrap = new Bootstrap();
                    ((Bootstrap) ((Bootstrap) ((Bootstrap) ((Bootstrap) ((Bootstrap) bootstrap.group(this.f11359f)).channel(NioSocketChannel.class)).handler(new ChannelInitializer<SocketChannel>(this) {
                        final /* synthetic */ C1694k f11337e;

                        /* compiled from: FacechatWebSocketClient */
                        class C16851 extends ChannelInboundHandlerAdapter {
                            final /* synthetic */ C16862 f11332a;

                            /* compiled from: FacechatWebSocketClient */
                            class C16841 implements Runnable {
                                final /* synthetic */ C16851 f11331a;

                                C16841(C16851 c16851) {
                                    this.f11331a = c16851;
                                }

                                public void run() {
                                    this.f11331a.f11332a.f11337e.m8092a();
                                }
                            }

                            C16851(C16862 c16862) {
                                this.f11332a = c16862;
                            }

                            public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
                                super.channelInactive(channelHandlerContext);
                                if (this.f11332a.f11337e.f11365l != null) {
                                    this.f11332a.f11337e.f11365l.cancel(true);
                                }
                                this.f11332a.f11337e.f11365l = channelHandlerContext.channel().eventLoop().schedule(new C16841(this), this.f11332a.f11337e.m8083j(), TimeUnit.MILLISECONDS);
                            }
                        }

                        protected /* synthetic */ void initChannel(Channel channel) throws Exception {
                            m8061a((SocketChannel) channel);
                        }

                        protected void m8061a(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addFirst(build.newHandler(socketChannel.alloc(), host, port));
                            pipeline.addFirst(new C16851(this));
                            pipeline.addLast(new HttpClientCodec(), new HttpObjectAggregator(RtspDecoder.DEFAULT_MAX_HEADER_SIZE), c1692c);
                            pipeline.addLast("ping", new C1690a(this.f11337e, 120, 120, 120));
                        }
                    })).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf(m8081i()))).option(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true))).option(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
                    this.f11360g = bootstrap.connect(this.f11355b.getHost(), port).addListener(new C16883(this)).sync().channel();
                    c1692c.m8064a().sync();
                } catch (Throwable e) {
                    if (this.f11357d != null) {
                        this.f11357d.mo4064a(-1);
                    }
                    C1639b.m7917a(f11354a, "connect fail", e);
                }
            } else if (this.f11357d != null) {
                this.f11357d.mo4065a(new JSONObject(), 400);
            }
        }
    }

    private int m8081i() {
        String a = C1643b.m7939a();
        Object obj = -1;
        switch (a.hashCode()) {
            case 1653:
                if (a.equals("2g")) {
                    obj = 2;
                    break;
                }
                break;
            case 1684:
                if (a.equals("3g")) {
                    obj = null;
                    break;
                }
                break;
            case 3649301:
                if (a.equals("wifi")) {
                    obj = 1;
                    break;
                }
                break;
            case 1959784951:
                if (a.equals("invalid")) {
                    obj = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case dd.f15932a /*0*/:
            case dd.f15933b /*1*/:
                return 15000;
            default:
                return 30000;
        }
    }

    private long m8083j() {
        return (this.f11366m.nextLong() % 1000) + 1000;
    }

    private void m8085k() {
        m8087l();
        if (this.f11360g != null) {
            this.f11360g.writeAndFlush(new CloseWebSocketFrame());
        }
        this.f11362i = C1693d.BACKGROUND;
        if (this.f11359f != null) {
            this.f11359f.shutdownGracefully();
        }
    }

    void m8094b() {
        C1639b.m7921c(f11354a, "websocket close");
        m8087l();
        if (this.f11360g != null) {
            this.f11360g.writeAndFlush(new CloseWebSocketFrame());
        }
        this.f11361h = false;
        this.f11362i = C1693d.MANUAL_DISCONNECTED;
        C1635a.m7888a().m7895b((C1634a) this);
        if (this.f11359f != null) {
            this.f11359f.shutdownGracefully();
        }
    }

    private void m8087l() {
        if (this.f11364k != null) {
            this.f11364k.purge();
            this.f11364k.cancel();
            this.f11364k = null;
        }
    }

    private void m8088m() {
        m8087l();
        this.f11364k = new Timer();
        this.f11364k.scheduleAtFixedRate(new C16894(this), 8000, 8000);
    }

    private void m8089n() {
        long nanoTime = System.nanoTime();
        if (nanoTime >= this.f11363j + 150000000000L) {
            m8092a();
        } else if (nanoTime >= this.f11363j + 120000000000L) {
            m8076f();
        }
    }

    public void mo4087c() {
        this.f11361h = true;
        C1639b.m7916a(f11354a, "class FacechatWebSocketClient :[onBecameForeground] " + this.f11362i.name());
        if (this.f11362i == null) {
            return;
        }
        if (this.f11362i == C1693d.DISCONNECTED || this.f11362i == C1693d.BACKGROUND) {
            m8092a();
        }
    }

    public void mo4088d() {
        this.f11361h = false;
        if (this.f11356c.mo4063a() != 12) {
            m8085k();
        }
    }
}
