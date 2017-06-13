package com.buddy.tiki.service.payment;

import org.json.JSONException;
import org.json.JSONObject;

public class Purchase {
    private String f1091a;
    private String f1092b;
    private String f1093c;
    private String f1094d;
    private long f1095e;
    private int f1096f;
    private String f1097g;
    private String f1098h;
    private String f1099i;
    private String f1100j;
    private boolean f1101k;

    public Purchase(String itemType, String jsonPurchaseInfo, String signature) throws JSONException {
        this.f1091a = itemType;
        this.f1099i = jsonPurchaseInfo;
        JSONObject o = new JSONObject(this.f1099i);
        this.f1092b = o.optString("orderId");
        this.f1093c = o.optString("packageName");
        this.f1094d = o.optString("productId");
        this.f1095e = o.optLong("purchaseTime");
        this.f1096f = o.optInt("purchaseState");
        this.f1097g = o.optString("developerPayload");
        this.f1098h = o.optString("token", o.optString("purchaseToken"));
        this.f1101k = o.optBoolean("autoRenewing");
        this.f1100j = signature;
    }

    public String getItemType() {
        return this.f1091a;
    }

    public String getOrderId() {
        return this.f1092b;
    }

    public String getPackageName() {
        return this.f1093c;
    }

    public String getSku() {
        return this.f1094d;
    }

    public long getPurchaseTime() {
        return this.f1095e;
    }

    public int getPurchaseState() {
        return this.f1096f;
    }

    public String getDeveloperPayload() {
        return this.f1097g;
    }

    public String getToken() {
        return this.f1098h;
    }

    public String getOriginalJson() {
        return this.f1099i;
    }

    public String getSignature() {
        return this.f1100j;
    }

    public boolean isAutoRenewing() {
        return this.f1101k;
    }

    public String toString() {
        return "PurchaseInfo(type:" + this.f1091a + "):" + this.f1099i;
    }
}
