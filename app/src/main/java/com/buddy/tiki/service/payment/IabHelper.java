package com.buddy.tiki.service.payment;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.recyclerview.BuildConfig;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.buddy.tiki.log.TikiLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class IabHelper {
    private static final TikiLog f1072b = TikiLog.getInstance("IabHelper");
    OnIabPurchaseFinishedListener f1073a;
    private final Object f1074c = new Object();
    private boolean f1075d = true;
    private String f1076e = "IabHelper";
    private boolean f1077f = false;
    private boolean f1078g = false;
    private boolean f1079h = false;
    private boolean f1080i = false;
    private boolean f1081j = false;
    private boolean f1082k = false;
    private String f1083l = BuildConfig.VERSION_NAME;
    private Context f1084m;
    private IInAppBillingService f1085n;
    private ServiceConnection f1086o;
    private int f1087p;
    private String f1088q;

    public static class IabAsyncInProgressException extends Exception {
        public IabAsyncInProgressException(String message) {
            super(message);
        }
    }

    public interface OnConsumeFinishedListener {
        void onConsumeFinished(Purchase purchase, IabResult iabResult);
    }

    public interface OnConsumeMultiFinishedListener {
        void onConsumeMultiFinished(List<Purchase> list, List<IabResult> list2);
    }

    public interface OnIabPurchaseFinishedListener {
        void onIabPurchaseFinished(IabResult iabResult, Purchase purchase);
    }

    public interface OnIabSetupFinishedListener {
        void onIabSetupFinished(IabResult iabResult);
    }

    public IabHelper(Context ctx) {
        this.f1084m = ctx.getApplicationContext();
        m418c("IAB helper created.");
    }

    public static String getResponseDesc(int code) {
        String[] iab_msgs = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] iabhelper_msgs = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (code <= NotificationManagerCompat.IMPORTANCE_UNSPECIFIED) {
            int index = -1000 - code;
            if (index < 0 || index >= iabhelper_msgs.length) {
                return String.valueOf(code) + ":Unknown IAB Helper Error";
            }
            return iabhelper_msgs[index];
        } else if (code < 0 || code >= iab_msgs.length) {
            return String.valueOf(code) + ":Unknown";
        } else {
            return iab_msgs[code];
        }
    }

    public void enableDebugLogging(boolean enable, String tag) {
        m407a();
        this.f1075d = enable;
        this.f1076e = tag;
    }

    public void enableDebugLogging(boolean enable) {
        m407a();
        this.f1075d = enable;
    }

    public void startSetup(final OnIabSetupFinishedListener listener) {
        m407a();
        if (this.f1077f) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        m418c("Starting in-app billing setup.");
        this.f1086o = new ServiceConnection(this) {
            final /* synthetic */ IabHelper f1062b;

            public void onServiceDisconnected(ComponentName name) {
                this.f1062b.m418c("Billing service disconnected.");
                this.f1062b.f1085n = null;
            }

            public void onServiceConnected(ComponentName name, IBinder service) {
                if (!this.f1062b.f1078g) {
                    this.f1062b.m418c("Billing service connected.");
                    this.f1062b.f1085n = Stub.asInterface(service);
                    String packageName = this.f1062b.f1084m.getPackageName();
                    try {
                        this.f1062b.m418c("Checking for in-app billing 3 support.");
                        int response = this.f1062b.f1085n.isBillingSupported(3, packageName, "inapp");
                        if (response != 0) {
                            if (listener != null) {
                                listener.onIabSetupFinished(new IabResult(response, "Error checking for billing v3 support."));
                            }
                            this.f1062b.f1080i = false;
                            this.f1062b.f1081j = false;
                            return;
                        }
                        this.f1062b.m418c("In-app billing version 3 supported for " + packageName);
                        this.f1062b.f1077f = true;
                        if (listener != null) {
                            listener.onIabSetupFinished(new IabResult(0, "Setup successful."));
                        }
                    } catch (RemoteException e) {
                        if (listener != null) {
                            listener.onIabSetupFinished(new IabResult(-1001, "RemoteException while setting up in-app billing."));
                        }
                        e.printStackTrace();
                    }
                }
            }
        };
        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        List<ResolveInfo> intentServices = this.f1084m.getPackageManager().queryIntentServices(serviceIntent, 0);
        if (intentServices != null && !intentServices.isEmpty()) {
            this.f1084m.bindService(serviceIntent, this.f1086o, 1);
        } else if (listener != null) {
            listener.onIabSetupFinished(new IabResult(3, "Billing service unavailable on device."));
        }
    }

    public void dispose() throws IabAsyncInProgressException {
        synchronized (this.f1074c) {
            if (this.f1082k) {
                throw new IabAsyncInProgressException("Can't dispose because an async operation (" + this.f1083l + ") is in progress.");
            }
        }
        m418c("Disposing.");
        this.f1077f = false;
        if (this.f1086o != null) {
            m418c("Unbinding from service.");
            if (this.f1084m != null) {
                this.f1084m.unbindService(this.f1086o);
            }
        }
        this.f1078g = true;
        this.f1084m = null;
        this.f1086o = null;
        this.f1085n = null;
        this.f1073a = null;
    }

    public void disposeWhenFinished() {
        synchronized (this.f1074c) {
            if (this.f1082k) {
                m418c("Will dispose after async operation finishes.");
                this.f1079h = true;
            } else {
                try {
                    dispose();
                } catch (IabAsyncInProgressException e) {
                }
            }
        }
    }

    private void m407a() {
        if (this.f1078g) {
            throw new IllegalStateException("IabHelper was disposed of, so it cannot be used.");
        }
    }

    public boolean subscriptionsSupported() {
        m407a();
        return this.f1080i;
    }

    public void launchPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener) throws IabAsyncInProgressException {
        launchPurchaseFlow(act, sku, requestCode, listener, BuildConfig.VERSION_NAME);
    }

    public void launchPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) throws IabAsyncInProgressException {
        launchPurchaseFlow(act, sku, "inapp", null, requestCode, listener, extraData);
    }

    public void launchPurchaseFlow(Activity act, String sku, String itemType, List<String> oldSkus, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) throws IabAsyncInProgressException {
        IabResult result;
        m407a();
        m409a("launchPurchaseFlow");
        m415b("launchPurchaseFlow");
        try {
            Bundle buyIntentBundle;
            m418c("Constructing buy intent for " + sku + ", item type: " + itemType);
            if (oldSkus == null || oldSkus.isEmpty()) {
                buyIntentBundle = this.f1085n.getBuyIntent(3, this.f1084m.getPackageName(), sku, itemType, extraData);
            } else if (this.f1081j) {
                buyIntentBundle = this.f1085n.getBuyIntentToReplaceSkus(5, this.f1084m.getPackageName(), oldSkus, sku, itemType, extraData);
            } else {
                IabResult r = new IabResult(-1011, "Subscription updates are not available.");
                m414b();
                if (listener != null) {
                    listener.onIabPurchaseFinished(r, null);
                    return;
                }
                return;
            }
            int response = m405a(buyIntentBundle);
            if (response != 0) {
                m421d("Unable to buy item, Error response: " + getResponseDesc(response));
                m414b();
                result = new IabResult(response, "Unable to buy item");
                if (listener != null) {
                    listener.onIabPurchaseFinished(result, null);
                    return;
                }
                return;
            }
            PendingIntent pendingIntent = (PendingIntent) buyIntentBundle.getParcelable("BUY_INTENT");
            m418c("Launching buy intent for " + sku + ". Request code: " + requestCode);
            this.f1087p = requestCode;
            this.f1073a = listener;
            this.f1088q = itemType;
            act.startIntentSenderForResult(pendingIntent.getIntentSender(), requestCode, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
        } catch (SendIntentException e) {
            m421d("SendIntentException while launching purchase flow for sku " + sku);
            e.printStackTrace();
            m414b();
            result = new IabResult(IMediaPlayer.MEDIA_ERROR_IO, "Failed to send intent.");
            if (listener != null) {
                listener.onIabPurchaseFinished(result, null);
            }
        } catch (RemoteException e2) {
            m421d("RemoteException while launching purchase flow for sku " + sku);
            e2.printStackTrace();
            m414b();
            result = new IabResult(-1001, "Remote exception while starting purchase flow");
            if (listener != null) {
                listener.onIabPurchaseFinished(result, null);
            }
        }
    }

    public boolean handleActivityResult(int requestCode, int resultCode, Intent data) {
        JSONException e;
        if (requestCode != this.f1087p) {
            return false;
        }
        m407a();
        m409a("handleActivityResult");
        m414b();
        IabResult result;
        if (data == null) {
            m421d("Null data in IAB activity result.");
            result = new IabResult(-1002, "Null data in IAB result");
            if (this.f1073a != null) {
                this.f1073a.onIabPurchaseFinished(result, null);
            }
            return true;
        }
        int responseCode = m404a(data);
        String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
        String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");
        if (resultCode == -1 && responseCode == 0) {
            m418c("Successful resultcode from purchase activity.");
            m418c("Purchase data: " + purchaseData);
            m418c("Data signature: " + dataSignature);
            m418c("Extras: " + data.getExtras());
            m418c("Expected item type: " + this.f1088q);
            if (purchaseData == null || dataSignature == null) {
                m421d("BUG: either purchaseData or dataSignature is null.");
                m418c("Extras: " + data.getExtras().toString());
                result = new IabResult(-1008, "IAB returned null purchaseData or dataSignature");
                if (this.f1073a != null) {
                    this.f1073a.onIabPurchaseFinished(result, null);
                }
                return true;
            }
            try {
                Purchase purchase = new Purchase(this.f1088q, purchaseData, dataSignature);
                try {
                    m418c("Purchase signature successfully verified.");
                    if (this.f1073a != null) {
                        this.f1073a.onIabPurchaseFinished(new IabResult(0, "Success"), purchase);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    Purchase purchase2 = purchase;
                    m421d("Failed to parse purchase data.");
                    e.printStackTrace();
                    result = new IabResult(-1002, "Failed to parse purchase data.");
                    if (this.f1073a != null) {
                        this.f1073a.onIabPurchaseFinished(result, null);
                    }
                    return true;
                }
            } catch (JSONException e3) {
                e = e3;
                m421d("Failed to parse purchase data.");
                e.printStackTrace();
                result = new IabResult(-1002, "Failed to parse purchase data.");
                if (this.f1073a != null) {
                    this.f1073a.onIabPurchaseFinished(result, null);
                }
                return true;
            }
        } else if (resultCode == -1) {
            m418c("Result code was OK but in-app billing response was not OK: " + getResponseDesc(responseCode));
            if (this.f1073a != null) {
                this.f1073a.onIabPurchaseFinished(new IabResult(responseCode, "Problem purchashing item."), null);
            }
        } else if (resultCode == 0) {
            m418c("Purchase canceled - Response: " + getResponseDesc(responseCode));
            result = new IabResult(-1005, "User canceled.");
            if (this.f1073a != null) {
                this.f1073a.onIabPurchaseFinished(result, null);
            }
        } else {
            m421d("Purchase failed. Result code: " + Integer.toString(resultCode) + ". Response: " + getResponseDesc(responseCode));
            result = new IabResult(-1006, "Unknown purchase response.");
            if (this.f1073a != null) {
                this.f1073a.onIabPurchaseFinished(result, null);
            }
        }
        return true;
    }

    void m422a(Purchase itemInfo) throws IabException {
        m407a();
        m409a("consume");
        if (itemInfo.getItemType().equals("inapp")) {
            try {
                String token = itemInfo.getToken();
                String sku = itemInfo.getSku();
                if (token == null || token.equals(BuildConfig.VERSION_NAME)) {
                    m421d("Can't consume " + sku + ". No token.");
                    throw new IabException((int) IMediaPlayer.MEDIA_ERROR_MALFORMED, "PurchaseInfo is missing token for sku: " + sku + " " + itemInfo);
                }
                m418c("Consuming sku: " + sku + ", token: " + token);
                int response = this.f1085n.consumePurchase(3, this.f1084m.getPackageName(), token);
                if (response == 0) {
                    m418c("Successfully consumed sku: " + sku);
                    return;
                } else {
                    m418c("Error consuming consuming sku " + sku + ". " + getResponseDesc(response));
                    throw new IabException(response, "Error consuming sku " + sku);
                }
            } catch (RemoteException e) {
                throw new IabException(-1001, "Remote exception while consuming. PurchaseInfo: " + itemInfo, e);
            }
        }
        throw new IabException((int) IMediaPlayer.MEDIA_ERROR_UNSUPPORTED, "Items of type '" + itemInfo.getItemType() + "' can't be consumed.");
    }

    public void consumeAsync(Purchase purchase, OnConsumeFinishedListener listener) throws IabAsyncInProgressException {
        m407a();
        m409a("consume");
        List<Purchase> purchases = new ArrayList();
        purchases.add(purchase);
        m410a(purchases, listener, null);
    }

    public void consumeAsync(List<Purchase> purchases, OnConsumeMultiFinishedListener listener) throws IabAsyncInProgressException {
        m407a();
        m409a("consume");
        m410a(purchases, null, listener);
    }

    private void m409a(String operation) {
        if (!this.f1077f) {
            m421d("Illegal state for operation (" + operation + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + operation);
        }
    }

    private int m405a(Bundle b) {
        Object o = b.get("RESPONSE_CODE");
        if (o == null) {
            m418c("Bundle with null response code, assuming OK (known issue)");
            return 0;
        } else if (o instanceof Integer) {
            return ((Integer) o).intValue();
        } else {
            if (o instanceof Long) {
                return (int) ((Long) o).longValue();
            }
            m421d("Unexpected type for bundle response code.");
            m421d(o.getClass().getName());
            throw new RuntimeException("Unexpected type for bundle response code: " + o.getClass().getName());
        }
    }

    private int m404a(Intent i) {
        Object o = i.getExtras().get("RESPONSE_CODE");
        if (o == null) {
            m421d("Intent with no response code, assuming OK (known issue)");
            return 0;
        } else if (o instanceof Integer) {
            return ((Integer) o).intValue();
        } else {
            if (o instanceof Long) {
                return (int) ((Long) o).longValue();
            }
            m421d("Unexpected type for intent response code.");
            m421d(o.getClass().getName());
            throw new RuntimeException("Unexpected type for intent response code: " + o.getClass().getName());
        }
    }

    private void m415b(String operation) throws IabAsyncInProgressException {
        synchronized (this.f1074c) {
            if (this.f1082k) {
                throw new IabAsyncInProgressException("Can't start async operation (" + operation + ") because another async operation (" + this.f1083l + ") is in progress.");
            }
            this.f1083l = operation;
            this.f1082k = true;
            m418c("Starting async operation: " + operation);
        }
    }

    private void m414b() {
        synchronized (this.f1074c) {
            m418c("Ending async operation: " + this.f1083l);
            this.f1083l = BuildConfig.VERSION_NAME;
            this.f1082k = false;
            if (this.f1079h) {
                try {
                    dispose();
                } catch (IabAsyncInProgressException e) {
                }
            }
        }
    }

    private void m410a(List<Purchase> purchases, OnConsumeFinishedListener singleListener, OnConsumeMultiFinishedListener multiListener) throws IabAsyncInProgressException {
        final Handler handler = new Handler();
        m415b("consume");
        final List<Purchase> list = purchases;
        final OnConsumeFinishedListener onConsumeFinishedListener = singleListener;
        final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener = multiListener;
        new Thread(new Runnable(this) {
            final /* synthetic */ IabHelper f1071e;

            public void run() {
                final List<IabResult> results = new ArrayList();
                for (Purchase purchase : list) {
                    try {
                        this.f1071e.m422a(purchase);
                        results.add(new IabResult(0, "Successful consume of sku " + purchase.getSku()));
                    } catch (IabException ex) {
                        results.add(ex.getResult());
                    }
                }
                this.f1071e.m414b();
                if (!(this.f1071e.f1078g || onConsumeFinishedListener == null)) {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ C04182 f1064b;

                        public void run() {
                            onConsumeFinishedListener.onConsumeFinished((Purchase) list.get(0), (IabResult) results.get(0));
                        }
                    });
                }
                if (!this.f1071e.f1078g && onConsumeMultiFinishedListener != null) {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ C04182 f1066b;

                        public void run() {
                            onConsumeMultiFinishedListener.onConsumeMultiFinished(list, results);
                        }
                    });
                }
            }
        }).start();
    }

    private void m418c(String msg) {
        f1072b.m261d(msg);
    }

    private void m421d(String msg) {
        f1072b.m263e("In-app billing error: " + msg);
    }
}
