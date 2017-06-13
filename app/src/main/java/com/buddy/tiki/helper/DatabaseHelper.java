package com.buddy.tiki.helper;

import android.content.Context;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.netty.handler.codec.rtsp.RtspHeaders.Values;
import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmConfiguration.Builder;
import io.realm.RealmSchema;

public class DatabaseHelper {
    private RealmConfiguration f547a;

    private static class SingletonHolder {
        private static final DatabaseHelper f546a = new DatabaseHelper();
    }

    private DatabaseHelper() {
    }

    public static DatabaseHelper getInstance() {
        return SingletonHolder.f546a;
    }

    public void init(Context context) {
        Realm.init(context);
        this.f547a = new Builder().name("Tiki").schemaVersion(14).migration(DatabaseHelper$$Lambda$1.lambdaFactory$()).build();
        Realm.setDefaultConfiguration(this.f547a);
    }

    static /* synthetic */ void m118a(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 1) {
            schema.get("TikiUser").addField("tid", Long.TYPE, new FieldAttribute[0]);
            oldVersion++;
        }
        if (oldVersion == 2) {
            schema.get("TikiAdministrator").addField("tid", Long.TYPE, new FieldAttribute[0]);
            oldVersion++;
        }
        if (oldVersion == 3) {
            schema.create("UserChatMessage").addField("msgType", Integer.TYPE, new FieldAttribute[0]).addField("timestamp", Long.TYPE, new FieldAttribute[0]).addField("msgText", String.class, new FieldAttribute[0]).addField("actionType", Integer.TYPE, new FieldAttribute[0]).addField("videoId", String.class, new FieldAttribute[0]).addField("needPay", Boolean.TYPE, new FieldAttribute[0]).addField("during", Long.TYPE, new FieldAttribute[0]).addField("videoFail", Boolean.TYPE, new FieldAttribute[0]).addField("isRead", Boolean.TYPE, new FieldAttribute[0]).addField("videoThumb", String.class, new FieldAttribute[0]).addField(Oauth2AccessToken.KEY_UID, String.class, new FieldAttribute[0]).addField("coin", Integer.TYPE, new FieldAttribute[0]).addField("msgId", String.class, new FieldAttribute[]{FieldAttribute.PRIMARY_KEY});
            oldVersion++;
        }
        if (oldVersion == 4) {
            schema.create("UserChatSession").addRealmListField("messages", schema.get("UserChatMessage")).addField("sessionId", String.class, new FieldAttribute[]{FieldAttribute.PRIMARY_KEY});
            oldVersion++;
        }
        if (oldVersion == 5) {
            schema.get("TikiAdministrator").addField("vipSend", Boolean.TYPE, new FieldAttribute[0]);
            oldVersion++;
        }
        if (oldVersion == 6) {
            schema.get("UserChatMessage").addField("uploadState", Integer.TYPE, new FieldAttribute[0]).addField("uploadProgress", Integer.TYPE, new FieldAttribute[0]).addField("diamondNum", Integer.TYPE, new FieldAttribute[0]).addField("timeLen", Integer.TYPE, new FieldAttribute[0]).addField("videoPath", String.class, new FieldAttribute[0]);
            oldVersion++;
        }
        if (oldVersion == 7) {
            schema.get("UserChatMessage").addField(Values.URL, String.class, new FieldAttribute[0]).addField("urlMark", String.class, new FieldAttribute[0]);
            schema.get("TikiUser").addField("oper", Boolean.TYPE, new FieldAttribute[0]);
            oldVersion++;
        }
        if (oldVersion == 8) {
            schema.get("TikiUser").transform(DatabaseHelper$$Lambda$2.lambdaFactory$());
            oldVersion++;
        }
        if (oldVersion == 9) {
            schema.get("UserChatSession").addField("timestamp", Long.TYPE, new FieldAttribute[0]);
            oldVersion++;
        }
        if (oldVersion == 10) {
            schema.get("UserChatSession").addField("unread", Integer.TYPE, new FieldAttribute[0]);
            oldVersion++;
        }
        if (oldVersion == 11) {
            schema.get("UserChatMessage").addField("giftId", String.class, new FieldAttribute[0]).addField("giftSource", String.class, new FieldAttribute[0]).addField("giftMusic", String.class, new FieldAttribute[0]).addField("giftCover", String.class, new FieldAttribute[0]).addField("giftName", String.class, new FieldAttribute[0]);
            oldVersion++;
        }
        if (oldVersion == 12) {
            schema.get("UserChatMessage").addField("sendFailed", Boolean.TYPE, new FieldAttribute[0]);
            oldVersion++;
        }
        if (oldVersion == 13) {
            schema.get("TikiAdministrator").addField("uber", Boolean.TYPE, new FieldAttribute[0]);
            oldVersion++;
        }
    }

    public RealmConfiguration getRealmConfiguration() {
        return this.f547a;
    }
}
