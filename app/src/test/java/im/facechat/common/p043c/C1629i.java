package im.facechat.common.p043c;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;
import tv.danmaku.ijk.media.player.BuildConfig;

/* compiled from: StateMachine */
public class C1629i {
    private String f11173a;
    private C1656c f11174b;
    private HandlerThread f11175c;

    /* compiled from: StateMachine */
    public static class C1651a {
        private C1629i f11232a;
        private long f11233b;
        private int f11234c;
        private String f11235d;
        private C1620e f11236e;
        private C1620e f11237f;
        private C1620e f11238g;

        C1651a(C1629i c1629i, Message message, String str, C1620e c1620e, C1620e c1620e2, C1620e c1620e3) {
            m7959a(c1629i, message, str, c1620e, c1620e2, c1620e3);
        }

        public void m7959a(C1629i c1629i, Message message, String str, C1620e c1620e, C1620e c1620e2, C1620e c1620e3) {
            this.f11232a = c1629i;
            this.f11233b = System.currentTimeMillis();
            this.f11234c = message != null ? message.what : 0;
            this.f11235d = str;
            this.f11236e = c1620e;
            this.f11237f = c1620e2;
            this.f11238g = c1620e3;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("time=");
            Calendar.getInstance().setTimeInMillis(this.f11233b);
            stringBuilder.append(String.format("%tm-%td %tH:%tM:%tS.%tL", new Object[]{r0, r0, r0, r0, r0, r0}));
            stringBuilder.append(" processed=");
            stringBuilder.append(this.f11236e == null ? "<null>" : this.f11236e.mo4040c());
            stringBuilder.append(" org=");
            stringBuilder.append(this.f11237f == null ? "<null>" : this.f11237f.mo4040c());
            stringBuilder.append(" dest=");
            stringBuilder.append(this.f11238g == null ? "<null>" : this.f11238g.mo4040c());
            stringBuilder.append(" what=");
            Object b = this.f11232a != null ? this.f11232a.m7811b(this.f11234c) : BuildConfig.VERSION_NAME;
            if (TextUtils.isEmpty(b)) {
                stringBuilder.append(this.f11234c);
                stringBuilder.append("(0x");
                stringBuilder.append(Integer.toHexString(this.f11234c));
                stringBuilder.append(")");
            } else {
                stringBuilder.append(b);
            }
            if (!TextUtils.isEmpty(this.f11235d)) {
                stringBuilder.append(" ");
                stringBuilder.append(this.f11235d);
            }
            return stringBuilder.toString();
        }
    }

    /* compiled from: StateMachine */
    private static class C1652b {
        private Vector<C1651a> f11239a;
        private int f11240b;
        private int f11241c;
        private int f11242d;
        private boolean f11243e;

        private C1652b() {
            this.f11239a = new Vector();
            this.f11240b = 20;
            this.f11241c = 0;
            this.f11242d = 0;
            this.f11243e = false;
        }

        synchronized boolean m7962a() {
            return this.f11243e;
        }

        synchronized int m7963b() {
            return this.f11239a.size();
        }

        synchronized int m7964c() {
            return this.f11242d;
        }

        synchronized void m7965d() {
            this.f11239a.clear();
        }

        synchronized C1651a m7960a(int i) {
            C1651a c1651a;
            int i2 = this.f11241c + i;
            if (i2 >= this.f11240b) {
                i2 -= this.f11240b;
            }
            if (i2 >= m7963b()) {
                c1651a = null;
            } else {
                c1651a = (C1651a) this.f11239a.get(i2);
            }
            return c1651a;
        }

        synchronized void m7961a(C1629i c1629i, Message message, String str, C1620e c1620e, C1620e c1620e2, C1620e c1620e3) {
            this.f11242d++;
            if (this.f11239a.size() < this.f11240b) {
                this.f11239a.add(new C1651a(c1629i, message, str, c1620e, c1620e2, c1620e3));
            } else {
                C1651a c1651a = (C1651a) this.f11239a.get(this.f11241c);
                this.f11241c++;
                if (this.f11241c >= this.f11240b) {
                    this.f11241c = 0;
                }
                c1651a.m7959a(c1629i, message, str, c1620e, c1620e2, c1620e3);
            }
        }
    }

    /* compiled from: StateMachine */
    private static class C1656c extends Handler {
        private static final Object f11250a = new Object();
        private boolean f11251b;
        private boolean f11252c;
        private Message f11253d;
        private C1652b f11254e;
        private boolean f11255f;
        private C1655c[] f11256g;
        private int f11257h;
        private C1655c[] f11258i;
        private int f11259j;
        private C1653a f11260k;
        private C1654b f11261l;
        private C1629i f11262m;
        private HashMap<C1621h, C1655c> f11263n;
        private C1621h f11264o;
        private C1621h f11265p;
        private boolean f11266q;
        private ArrayList<Message> f11267r;

        /* compiled from: StateMachine */
        private class C1653a extends C1621h {
            final /* synthetic */ C1656c f11244a;

            private C1653a(C1656c c1656c) {
                this.f11244a = c1656c;
            }

            public boolean mo4042a(Message message) {
                this.f11244a.f11262m.m7821d(message);
                return true;
            }
        }

        /* compiled from: StateMachine */
        private class C1654b extends C1621h {
            final /* synthetic */ C1656c f11245a;

            private C1654b(C1656c c1656c) {
                this.f11245a = c1656c;
            }

            public boolean mo4042a(Message message) {
                return false;
            }
        }

        /* compiled from: StateMachine */
        private class C1655c {
            C1621h f11246a;
            C1655c f11247b;
            boolean f11248c;
            final /* synthetic */ C1656c f11249d;

            private C1655c(C1656c c1656c) {
                this.f11249d = c1656c;
            }

            public String toString() {
                String str;
                StringBuilder append = new StringBuilder().append("state=").append(this.f11246a.mo4040c()).append(",active=").append(this.f11248c).append(",parent=");
                if (this.f11247b == null) {
                    str = "null";
                } else {
                    str = this.f11247b.f11246a.mo4040c();
                }
                return append.append(str).toString();
            }
        }

        private C1656c(Looper looper, C1629i c1629i) {
            super(looper);
            this.f11251b = false;
            this.f11252c = false;
            this.f11254e = new C1652b();
            this.f11257h = -1;
            this.f11260k = new C1653a();
            this.f11261l = new C1654b();
            this.f11263n = new HashMap();
            this.f11266q = false;
            this.f11267r = new ArrayList();
            this.f11262m = c1629i;
            m7971a(this.f11260k, null);
            m7971a(this.f11261l, null);
        }

        public final void handleMessage(Message message) {
            if (!this.f11251b) {
                if (!(this.f11262m == null || message.what == -2 || message.what == -1)) {
                    this.f11262m.m7804a(message);
                }
                if (this.f11252c) {
                    this.f11262m.m7809a("handleMessage: E msg.what=" + message.what);
                }
                this.f11253d = message;
                C1621h c1621h = null;
                if (this.f11255f) {
                    c1621h = m7969a(message);
                } else if (!this.f11255f && this.f11253d.what == -2 && this.f11253d.obj == f11250a) {
                    this.f11255f = true;
                    m7974a(0);
                } else {
                    throw new RuntimeException("StateMachine.handleMessage: The start method not called, received msg: " + message);
                }
                m7976a(c1621h, message);
                if (this.f11252c && this.f11262m != null) {
                    this.f11262m.m7809a("handleMessage: X");
                }
                if (this.f11262m != null && message.what != -2 && message.what != -1) {
                    this.f11262m.m7813b(message);
                }
            }
        }

        private void m7976a(C1621h c1621h, Message message) {
            C1620e c1620e = this.f11256g[this.f11257h].f11246a;
            boolean z = this.f11262m.m7823e(this.f11253d) && message.obj != f11250a;
            if (this.f11254e.m7962a()) {
                if (this.f11265p != null) {
                    this.f11254e.m7961a(this.f11262m, this.f11253d, this.f11262m.m7825f(this.f11253d), c1621h, c1620e, this.f11265p);
                }
            } else if (z) {
                this.f11254e.m7961a(this.f11262m, this.f11253d, this.f11262m.m7825f(this.f11253d), c1621h, c1620e, this.f11265p);
            }
            C1621h c1621h2 = this.f11265p;
            if (c1621h2 != null) {
                while (true) {
                    if (this.f11252c) {
                        this.f11262m.m7809a("handleMessage: new destination call exit/enter");
                    }
                    C1655c a = m7970a(c1621h2);
                    this.f11266q = true;
                    m7977a(a);
                    m7974a(m7986d());
                    m7985c();
                    if (c1621h2 == this.f11265p) {
                        break;
                    }
                    c1621h2 = this.f11265p;
                }
                this.f11265p = null;
            }
            if (c1621h2 == null) {
                return;
            }
            if (c1621h2 == this.f11261l) {
                this.f11262m.m7819d();
                m7973a();
            } else if (c1621h2 == this.f11260k) {
                this.f11262m.m7817c();
            }
        }

        private final void m7973a() {
            if (this.f11262m.f11175c != null) {
                getLooper().quit();
                this.f11262m.f11175c = null;
            }
            this.f11262m.f11174b = null;
            this.f11262m = null;
            this.f11253d = null;
            this.f11254e.m7965d();
            this.f11256g = null;
            this.f11258i = null;
            this.f11263n.clear();
            this.f11264o = null;
            this.f11265p = null;
            this.f11267r.clear();
            this.f11251b = true;
        }

        private final void m7980b() {
            if (this.f11252c) {
                this.f11262m.m7809a("completeConstruction: E");
            }
            int i = 0;
            for (C1655c c1655c : this.f11263n.values()) {
                int i2 = 0;
                C1655c c1655c2;
                while (c1655c2 != null) {
                    c1655c2 = c1655c2.f11247b;
                    i2++;
                }
                if (i >= i2) {
                    i2 = i;
                }
                i = i2;
            }
            if (this.f11252c) {
                this.f11262m.m7809a("completeConstruction: maxDepth=" + i);
            }
            this.f11256g = new C1655c[i];
            this.f11258i = new C1655c[i];
            m7988e();
            sendMessageAtFrontOfQueue(obtainMessage(-2, f11250a));
            if (this.f11252c) {
                this.f11262m.m7809a("completeConstruction: X");
            }
        }

        private final C1621h m7969a(Message message) {
            C1655c c1655c = this.f11256g[this.f11257h];
            if (this.f11252c) {
                this.f11262m.m7809a("processMsg: " + c1655c.f11246a.mo4040c());
            }
            if (m7982b(message)) {
                m7975a(this.f11261l);
            } else {
                while (!c1655c.f11246a.mo4042a(message)) {
                    c1655c = c1655c.f11247b;
                    if (c1655c == null) {
                        this.f11262m.m7818c(message);
                        break;
                    } else if (this.f11252c) {
                        this.f11262m.m7809a("processMsg: " + c1655c.f11246a.mo4040c());
                    }
                }
            }
            if (c1655c != null) {
                return c1655c.f11246a;
            }
            return null;
        }

        private final void m7977a(C1655c c1655c) {
            while (this.f11257h >= 0 && this.f11256g[this.f11257h] != c1655c) {
                C1621h c1621h = this.f11256g[this.f11257h].f11246a;
                if (this.f11252c) {
                    this.f11262m.m7809a("invokeExitMethods: " + c1621h.mo4040c());
                }
                c1621h.mo4043b();
                this.f11256g[this.f11257h].f11248c = false;
                this.f11257h--;
            }
        }

        private final void m7974a(int i) {
            for (int i2 = i; i2 <= this.f11257h; i2++) {
                if (i == this.f11257h) {
                    this.f11266q = false;
                }
                if (this.f11252c) {
                    this.f11262m.m7809a("invokeEnterMethods: " + this.f11256g[i2].f11246a.mo4040c());
                }
                this.f11256g[i2].f11246a.mo4041a();
                this.f11256g[i2].f11248c = true;
            }
            this.f11266q = false;
        }

        private final void m7985c() {
            for (int size = this.f11267r.size() - 1; size >= 0; size--) {
                Message message = (Message) this.f11267r.get(size);
                if (this.f11252c) {
                    this.f11262m.m7809a("moveDeferredMessageAtFrontOfQueue; what=" + message.what);
                }
                sendMessageAtFrontOfQueue(message);
            }
            this.f11267r.clear();
        }

        private final int m7986d() {
            int i = this.f11257h + 1;
            int i2 = i;
            for (int i3 = this.f11259j - 1; i3 >= 0; i3--) {
                if (this.f11252c) {
                    this.f11262m.m7809a("moveTempStackToStateStack: i=" + i3 + ",j=" + i2);
                }
                this.f11256g[i2] = this.f11258i[i3];
                i2++;
            }
            this.f11257h = i2 - 1;
            if (this.f11252c) {
                this.f11262m.m7809a("moveTempStackToStateStack: X mStateStackTop=" + this.f11257h + ",startingIndex=" + i + ",Top=" + this.f11256g[this.f11257h].f11246a.mo4040c());
            }
            return i;
        }

        private final C1655c m7970a(C1621h c1621h) {
            this.f11259j = 0;
            C1655c c1655c = (C1655c) this.f11263n.get(c1621h);
            do {
                C1655c[] c1655cArr = this.f11258i;
                int i = this.f11259j;
                this.f11259j = i + 1;
                c1655cArr[i] = c1655c;
                c1655c = c1655c.f11247b;
                if (c1655c == null) {
                    break;
                }
            } while (!c1655c.f11248c);
            if (this.f11252c) {
                this.f11262m.m7809a("setupTempStateStackWithStatesToEnter: X mTempStateStackCount=" + this.f11259j + ",curStateInfo: " + c1655c);
            }
            return c1655c;
        }

        private final void m7988e() {
            if (this.f11252c) {
                this.f11262m.m7809a("setupInitialStateStack: E mInitialState=" + this.f11264o.mo4040c());
            }
            C1655c c1655c = (C1655c) this.f11263n.get(this.f11264o);
            this.f11259j = 0;
            while (c1655c != null) {
                this.f11258i[this.f11259j] = c1655c;
                c1655c = c1655c.f11247b;
                this.f11259j++;
            }
            this.f11257h = -1;
            m7986d();
        }

        private final C1620e m7990f() {
            return this.f11256g[this.f11257h].f11246a;
        }

        private final C1655c m7971a(C1621h c1621h, C1621h c1621h2) {
            C1655c c1655c;
            C1655c a;
            if (this.f11252c) {
                String str;
                C1629i c1629i = this.f11262m;
                StringBuilder append = new StringBuilder().append("addStateInternal: E state=").append(c1621h.mo4040c()).append(",parent=");
                if (c1621h2 == null) {
                    str = BuildConfig.VERSION_NAME;
                } else {
                    str = c1621h2.mo4040c();
                }
                c1629i.m7809a(append.append(str).toString());
            }
            if (c1621h2 != null) {
                c1655c = (C1655c) this.f11263n.get(c1621h2);
                a = c1655c == null ? m7971a(c1621h2, null) : c1655c;
            } else {
                a = null;
            }
            c1655c = (C1655c) this.f11263n.get(c1621h);
            if (c1655c == null) {
                c1655c = new C1655c();
                this.f11263n.put(c1621h, c1655c);
            }
            if (c1655c.f11247b == null || c1655c.f11247b == a) {
                c1655c.f11246a = c1621h;
                c1655c.f11247b = a;
                c1655c.f11248c = false;
                if (this.f11252c) {
                    this.f11262m.m7809a("addStateInternal: X stateInfo: " + c1655c);
                }
                return c1655c;
            }
            throw new RuntimeException("state already added");
        }

        private final void m7981b(C1621h c1621h) {
            if (this.f11252c) {
                this.f11262m.m7809a("setInitialState: initialState=" + c1621h.mo4040c());
            }
            this.f11264o = c1621h;
        }

        private final void m7975a(C1620e c1620e) {
            if (this.f11266q) {
                Log.wtf(this.f11262m.f11173a, "transitionTo called while transition already in progress to " + this.f11265p + ", new target state=" + c1620e);
            }
            this.f11265p = (C1621h) c1620e;
            if (this.f11252c) {
                this.f11262m.m7809a("transitionTo: destState=" + this.f11265p.mo4040c());
            }
        }

        private final void m7992g() {
            if (this.f11252c) {
                this.f11262m.m7809a("quit:");
            }
            sendMessage(obtainMessage(-1, f11250a));
        }

        private final boolean m7982b(Message message) {
            return message.what == -1 && message.obj == f11250a;
        }
    }

    protected C1629i(String str) {
        this.f11175c = new HandlerThread(str);
        this.f11175c.start();
        m7800a(str, this.f11175c.getLooper());
    }

    private void m7800a(String str, Looper looper) {
        this.f11173a = str;
        this.f11174b = new C1656c(looper, this);
    }

    protected void m7804a(Message message) {
    }

    protected void m7813b(Message message) {
    }

    public final void m7807a(C1621h c1621h, C1621h c1621h2) {
        this.f11174b.m7971a(c1621h, c1621h2);
    }

    public final void m7806a(C1621h c1621h) {
        this.f11174b.m7971a(c1621h, null);
    }

    public final void m7814b(C1621h c1621h) {
        this.f11174b.m7981b(c1621h);
    }

    public final C1620e m7810b() {
        C1656c c1656c = this.f11174b;
        if (c1656c == null) {
            return null;
        }
        return c1656c.m7990f();
    }

    public final void m7805a(C1620e c1620e) {
        this.f11174b.m7975a(c1620e);
    }

    protected void m7818c(Message message) {
        if (this.f11174b.f11252c) {
            m7815b(" - unhandledMessage: msg.what=" + message.what);
        }
    }

    protected void m7821d(Message message) {
    }

    protected void m7817c() {
    }

    protected void m7819d() {
    }

    public final String m7822e() {
        return this.f11173a;
    }

    public final int m7824f() {
        C1656c c1656c = this.f11174b;
        if (c1656c == null) {
            return 0;
        }
        return c1656c.f11254e.m7963b();
    }

    public final int m7826g() {
        C1656c c1656c = this.f11174b;
        if (c1656c == null) {
            return 0;
        }
        return c1656c.f11254e.m7964c();
    }

    public final C1651a m7803a(int i) {
        C1656c c1656c = this.f11174b;
        if (c1656c == null) {
            return null;
        }
        return c1656c.f11254e.m7960a(i);
    }

    protected boolean m7823e(Message message) {
        return true;
    }

    protected String m7825f(Message message) {
        return BuildConfig.VERSION_NAME;
    }

    protected String m7811b(int i) {
        return null;
    }

    public final Message m7816c(int i) {
        return Message.obtain(this.f11174b, i);
    }

    public final Message m7802a(int i, Object obj) {
        return Message.obtain(this.f11174b, i, obj);
    }

    public void m7820d(int i) {
        C1656c c1656c = this.f11174b;
        if (c1656c != null) {
            c1656c.sendMessage(m7816c(i));
        }
    }

    public void m7812b(int i, Object obj) {
        C1656c c1656c = this.f11174b;
        if (c1656c != null) {
            c1656c.sendMessage(m7802a(i, obj));
        }
    }

    public final void m7827h() {
        C1656c c1656c = this.f11174b;
        if (c1656c != null) {
            c1656c.m7992g();
        }
    }

    public void m7828i() {
        C1656c c1656c = this.f11174b;
        if (c1656c != null) {
            c1656c.m7980b();
        }
    }

    public void m7808a(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println(m7822e() + ":");
        printWriter.println(" total records=" + m7826g());
        for (int i = 0; i < m7824f(); i++) {
            printWriter.println(" rec[" + i + "]: " + m7803a(i).toString());
            printWriter.flush();
        }
        printWriter.println("curState=" + m7810b().mo4040c());
    }

    public String toString() {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        m7808a(null, printWriter, null);
        printWriter.flush();
        printWriter.close();
        return stringWriter.toString();
    }

    protected void m7809a(String str) {
        Log.d(this.f11173a, str);
    }

    protected void m7815b(String str) {
        Log.e(this.f11173a, str);
    }
}
