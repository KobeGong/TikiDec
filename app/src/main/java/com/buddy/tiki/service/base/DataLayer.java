package com.buddy.tiki.service.base;

import com.buddy.tiki.service.AppManager;
import com.buddy.tiki.service.ChatManager;
import com.buddy.tiki.service.DownloadApiManager;
import com.buddy.tiki.service.FeedbackManager;
import com.buddy.tiki.service.FollowManager;
import com.buddy.tiki.service.MessageManager;
import com.buddy.tiki.service.PaymentManager;
import com.buddy.tiki.service.ResourceManager;
import com.buddy.tiki.service.TikiResManager;
import com.buddy.tiki.service.UserManager;
import com.buddy.tiki.service.WechatManager;

public class DataLayer {
    private volatile WechatManager f1024a;
    private volatile UserManager f1025b;
    private volatile AppManager f1026c;
    private volatile ResourceManager f1027d;
    private volatile DownloadApiManager f1028e;
    private volatile FollowManager f1029f;
    private volatile ChatManager f1030g;
    private volatile TikiResManager f1031h;
    private volatile MessageManager f1032i;
    private volatile FeedbackManager f1033j;
    private volatile PaymentManager f1034k;

    private static class SingletonHolder {
        private static final DataLayer f1023a = new DataLayer();
    }

    private DataLayer() {
        this.f1024a = null;
        this.f1025b = null;
        this.f1026c = null;
        this.f1027d = null;
        this.f1028e = null;
        this.f1029f = null;
        this.f1030g = null;
        this.f1031h = null;
        this.f1032i = null;
        this.f1033j = null;
        this.f1034k = null;
    }

    public static DataLayer getInstance() {
        return SingletonHolder.f1023a;
    }

    public void cleanup() {
        if (this.f1025b != null) {
            this.f1025b.m277d();
        }
        if (this.f1026c != null) {
            this.f1026c.m277d();
        }
        if (this.f1027d != null) {
            this.f1027d.m277d();
        }
        if (this.f1029f != null) {
            this.f1029f.m277d();
        }
        if (this.f1030g != null) {
            this.f1030g.m277d();
        }
        if (this.f1031h != null) {
            this.f1031h.m277d();
        }
        if (this.f1032i != null) {
            this.f1032i.m277d();
        }
        if (this.f1033j != null) {
            this.f1029f.m277d();
        }
        if (this.f1034k != null) {
            this.f1034k.m277d();
        }
    }

    public WechatManager getWechatManager() {
        if (this.f1024a == null) {
            synchronized (DataLayer.class) {
                if (this.f1024a == null) {
                    this.f1024a = new WechatManager();
                }
            }
        }
        return this.f1024a;
    }

    public UserManager getUserManager() {
        if (this.f1025b == null) {
            synchronized (DataLayer.class) {
                if (this.f1025b == null) {
                    this.f1025b = new UserManager();
                }
            }
        }
        return this.f1025b;
    }

    public AppManager getAppManager() {
        if (this.f1026c == null) {
            synchronized (DataLayer.class) {
                if (this.f1026c == null) {
                    this.f1026c = new AppManager();
                }
            }
        }
        return this.f1026c;
    }

    public ResourceManager getResourceManager() {
        if (this.f1027d == null) {
            synchronized (DataLayer.class) {
                if (this.f1027d == null) {
                    this.f1027d = new ResourceManager();
                }
            }
        }
        return this.f1027d;
    }

    public DownloadApiManager getDownloadApiManager() {
        if (this.f1028e == null) {
            synchronized (DataLayer.class) {
                if (this.f1028e == null) {
                    this.f1028e = new DownloadApiManager();
                }
            }
        }
        return this.f1028e;
    }

    public FollowManager getFollowManager() {
        if (this.f1029f == null) {
            synchronized (DataLayer.class) {
                if (this.f1029f == null) {
                    this.f1029f = new FollowManager();
                }
            }
        }
        return this.f1029f;
    }

    public ChatManager getChatManager() {
        if (this.f1030g == null) {
            synchronized (DataLayer.class) {
                if (this.f1030g == null) {
                    this.f1030g = new ChatManager();
                }
            }
        }
        return this.f1030g;
    }

    public TikiResManager getTikiResManager() {
        if (this.f1031h == null) {
            synchronized (DataLayer.class) {
                if (this.f1031h == null) {
                    this.f1031h = new TikiResManager();
                }
            }
        }
        return this.f1031h;
    }

    public MessageManager getMessageManager() {
        if (this.f1032i == null) {
            synchronized (DataLayer.class) {
                if (this.f1032i == null) {
                    this.f1032i = new MessageManager();
                }
            }
        }
        return this.f1032i;
    }

    public FeedbackManager getFeedbackManager() {
        if (this.f1033j == null) {
            synchronized (DataLayer.class) {
                if (this.f1033j == null) {
                    this.f1033j = new FeedbackManager();
                }
            }
        }
        return this.f1033j;
    }

    public PaymentManager getPaymentManager() {
        if (this.f1034k == null) {
            synchronized (DataLayer.class) {
                if (this.f1034k == null) {
                    this.f1034k = new PaymentManager();
                }
            }
        }
        return this.f1034k;
    }
}
