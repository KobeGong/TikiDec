package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.event.UserEvent.UploadStateEvent;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.helper.UploadHelper;
import com.buddy.tiki.helper.UploadHelper.UploadCallback;
import com.buddy.tiki.model.user.TikiAdministrator;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.UserChatMessage;
import com.buddy.tiki.ui.activity.ChatMessageActivity;
import com.buddy.tiki.ui.activity.VideoMessageActivity;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.util.BlurProcessor;
import com.buddy.tiki.util.DateUtil;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.ResourceUrlUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.view.UserChatVideoReceiveMessageView;
import com.buddy.tiki.view.VideoUploadProgressView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.postproc;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacpp.swscale;
import org.greenrobot.eventbus.EventBus;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class ChatMessageAdapter extends Adapter<ViewHolder> {
    private List<UserChatMessage> f1737a;
    private TikiUser f1738b;
    private TikiAdministrator f1739c;
    private LayoutInflater f1740d;
    private Context f1741e;
    private long f1742f = 0;

    static class HttpSpan extends ClickableSpan {
        private final WeakReference<Context> f1699a;
        private final String f1700b;
        private final boolean f1701c;

        public HttpSpan(String url, Context context, boolean clickAble) {
            this.f1700b = url;
            this.f1699a = new WeakReference(context);
            this.f1701c = clickAble;
        }

        public void onClick(View widget) {
            if (this.f1699a.get() != null && this.f1701c) {
                WebBrowserActivity.launchWeb((Context) this.f1699a.get(), this.f1700b);
            }
        }

        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            if (this.f1699a.get() != null) {
                ds.setColor(((Context) this.f1699a.get()).getResources().getColor(C0376R.color.brightSkyBlue));
            }
        }
    }

    public enum ITEM_TYPE {
        ITEM_TYPE_SEND,
        ITEM_TYPE_RECIVE,
        ITEM_TYPE_SEND_VIDEO,
        ITEM_TYPE_RECIVE_VIDEO,
        ITEM_TYPE_RECIVE_TEXT,
        ITEM_TYPE_SEND_TEXT,
        ITEM_TYPE_RECIVE_GIFT,
        ITEM_TYPE_SEND_GIFT
    }

    static class ReceiveGiftMessageHolder extends ViewHolder {
        SimpleDraweeView f1703a;
        AppCompatTextView f1704b;
        AppCompatTextView f1705c;
        SimpleDraweeView f1706d;

        public ReceiveGiftMessageHolder(View itemView) {
            super(itemView);
            this.f1703a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1706d = (SimpleDraweeView) itemView.findViewById(C0376R.id.gift);
            this.f1705c = (AppCompatTextView) itemView.findViewById(C0376R.id.read_state);
            this.f1704b = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_time);
        }
    }

    static class ReceiveMessageHolder extends ViewHolder {
        SimpleDraweeView f1707a;
        AppCompatTextView f1708b;
        AppCompatTextView f1709c;

        public ReceiveMessageHolder(View itemView) {
            super(itemView);
            this.f1707a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1708b = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_bubble_text);
            this.f1709c = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_time);
        }
    }

    static class ReceiveMessageVideoHolder extends ViewHolder {
        SimpleDraweeView f1710a;
        UserChatVideoReceiveMessageView f1711b;
        AppCompatTextView f1712c;
        LinearLayout f1713d;
        AppCompatImageView f1714e;

        public ReceiveMessageVideoHolder(View itemView) {
            super(itemView);
            this.f1710a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1711b = (UserChatVideoReceiveMessageView) itemView.findViewById(C0376R.id.video_thumb);
            this.f1712c = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_time);
            this.f1713d = (LinearLayout) itemView.findViewById(C0376R.id.video_invalid);
            this.f1714e = (AppCompatImageView) itemView.findViewById(C0376R.id.video_play);
        }
    }

    static class ReceiveTextMessageHolder extends ViewHolder {
        SimpleDraweeView f1715a;
        AppCompatTextView f1716b;
        AppCompatTextView f1717c;

        public ReceiveTextMessageHolder(View itemView) {
            super(itemView);
            this.f1715a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1716b = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_bubble_text);
            this.f1717c = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_time);
        }
    }

    static class SendGiftMessageHolder extends ViewHolder {
        SimpleDraweeView f1718a;
        SimpleDraweeView f1719b;
        AppCompatTextView f1720c;
        AppCompatImageView f1721d;

        public SendGiftMessageHolder(View itemView) {
            super(itemView);
            this.f1718a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1719b = (SimpleDraweeView) itemView.findViewById(C0376R.id.gift);
            this.f1720c = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_time);
            this.f1721d = (AppCompatImageView) itemView.findViewById(C0376R.id.send_state);
        }
    }

    static class SendMessageHolder extends ViewHolder {
        SimpleDraweeView f1722a;
        AppCompatTextView f1723b;
        AppCompatTextView f1724c;

        public SendMessageHolder(View itemView) {
            super(itemView);
            this.f1722a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1723b = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_bubble_text);
            this.f1724c = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_time);
        }
    }

    static class SendMessageVideoHolder extends ViewHolder {
        SimpleDraweeView f1725a;
        SimpleDraweeView f1726b;
        AppCompatTextView f1727c;
        VideoUploadProgressView f1728d;
        LinearLayout f1729e;
        AppCompatImageView f1730f;
        AppCompatTextView f1731g;
        LinearLayout f1732h;

        public SendMessageVideoHolder(View itemView) {
            super(itemView);
            this.f1725a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1726b = (SimpleDraweeView) itemView.findViewById(C0376R.id.video_thumb);
            this.f1727c = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_time);
            this.f1728d = (VideoUploadProgressView) itemView.findViewById(C0376R.id.video_uploading);
            this.f1729e = (LinearLayout) itemView.findViewById(C0376R.id.video_upload_fail);
            this.f1730f = (AppCompatImageView) itemView.findViewById(C0376R.id.video_play);
            this.f1731g = (AppCompatTextView) itemView.findViewById(C0376R.id.read_state);
            this.f1732h = (LinearLayout) itemView.findViewById(C0376R.id.video_invalid);
        }
    }

    static class SendTextMessageHolder extends ViewHolder {
        SimpleDraweeView f1733a;
        AppCompatTextView f1734b;
        AppCompatTextView f1735c;
        AppCompatImageView f1736d;

        public SendTextMessageHolder(View itemView) {
            super(itemView);
            this.f1733a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1734b = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_bubble_text);
            this.f1735c = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_time);
            this.f1736d = (AppCompatImageView) itemView.findViewById(C0376R.id.send_state);
        }
    }

    public ChatMessageAdapter(@NonNull Context context, @Nullable List<UserChatMessage> data, @NonNull TikiUser user, @NonNull TikiAdministrator administrator) {
        this.f1740d = LayoutInflater.from(context);
        this.f1737a = data;
        this.f1741e = context;
        this.f1738b = user;
        this.f1739c = administrator;
    }

    public void setData(List<UserChatMessage> messages) {
        this.f1737a = messages;
        notifyDataSetChanged();
    }

    public void setServerTime(long serverTime) {
        this.f1742f = serverTime;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_SEND.ordinal()) {
            return new SendMessageHolder(this.f1740d.inflate(C0376R.layout.item_message_out_info, parent, false));
        }
        if (viewType == ITEM_TYPE.ITEM_TYPE_RECIVE.ordinal()) {
            return new ReceiveMessageHolder(this.f1740d.inflate(C0376R.layout.item_message_in_info, parent, false));
        }
        if (viewType == ITEM_TYPE.ITEM_TYPE_SEND_VIDEO.ordinal()) {
            return new SendMessageVideoHolder(this.f1740d.inflate(C0376R.layout.item_message_out_video, parent, false));
        }
        if (viewType == ITEM_TYPE.ITEM_TYPE_RECIVE_VIDEO.ordinal()) {
            return new ReceiveMessageVideoHolder(this.f1740d.inflate(C0376R.layout.item_message_in_video, parent, false));
        }
        if (viewType == ITEM_TYPE.ITEM_TYPE_RECIVE_TEXT.ordinal()) {
            return new ReceiveTextMessageHolder(this.f1740d.inflate(C0376R.layout.item_message_in_text, parent, false));
        }
        if (viewType == ITEM_TYPE.ITEM_TYPE_SEND_TEXT.ordinal()) {
            return new SendTextMessageHolder(this.f1740d.inflate(C0376R.layout.item_message_out_text, parent, false));
        }
        if (viewType == ITEM_TYPE.ITEM_TYPE_RECIVE_GIFT.ordinal()) {
            return new ReceiveGiftMessageHolder(this.f1740d.inflate(C0376R.layout.item_message_in_gift, parent, false));
        }
        return new SendGiftMessageHolder(this.f1740d.inflate(C0376R.layout.item_message_out_gift, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        UserChatMessage message = (UserChatMessage) this.f1737a.get(position);
        if (message != null && this.f1738b != null && this.f1739c != null) {
            RxView.clicks(holder.itemView).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(ChatMessageAdapter$$Lambda$1.lambdaFactory$(this));
            AppCompatTextView time = null;
            if (holder instanceof SendMessageHolder) {
                if (TextUtils.isEmpty(this.f1738b.getAvatar()) || !this.f1738b.getAvatar().startsWith("http://")) {
                    ((SendMessageHolder) holder).f1722a.setImageURI("res://" + this.f1741e.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
                } else {
                    FrescoUtil.setImageURI(((SendMessageHolder) holder).f1722a, ResourceUrlUtil.getNormalAvatar(this.f1739c.getAvatar(), DisplayUtil.dip2px(36.0f)));
                }
                ((SendMessageHolder) holder).f1723b.setText(message.getMsgText());
                time = ((SendMessageHolder) holder).f1724c;
            } else if (holder instanceof ReceiveMessageHolder) {
                if (TextUtils.isEmpty(this.f1738b.getAvatar()) || !this.f1738b.getAvatar().startsWith("http://")) {
                    ((ReceiveMessageHolder) holder).f1707a.setImageURI("res://" + this.f1741e.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
                } else {
                    FrescoUtil.setImageURI(((ReceiveMessageHolder) holder).f1707a, ResourceUrlUtil.getNormalAvatar(this.f1738b.getAvatar(), DisplayUtil.dip2px(36.0f)));
                }
                ((ReceiveMessageHolder) holder).f1708b.setText(message.getMsgText());
                time = ((ReceiveMessageHolder) holder).f1709c;
            } else if (holder instanceof SendMessageVideoHolder) {
                if (TextUtils.isEmpty(this.f1738b.getAvatar()) || !this.f1738b.getAvatar().startsWith("http://")) {
                    ((SendMessageVideoHolder) holder).f1725a.setImageURI("res://" + this.f1741e.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
                } else {
                    FrescoUtil.setImageURI(((SendMessageVideoHolder) holder).f1725a, ResourceUrlUtil.getNormalAvatar(this.f1739c.getAvatar(), DisplayUtil.dip2px(36.0f)));
                }
                time = ((SendMessageVideoHolder) holder).f1727c;
                if (!TextUtils.isEmpty(message.getVideoThumb())) {
                    if (message.getMsgType() == 4) {
                        FrescoUtil.setImageURI(((SendMessageVideoHolder) holder).f1726b, Uri.parse("file://" + message.getVideoThumb()));
                    } else {
                        ((SendMessageVideoHolder) holder).f1726b.setImageURI(Uri.parse(message.getVideoThumb()));
                    }
                }
                RxView.clicks(((SendMessageVideoHolder) holder).f1729e).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(ChatMessageAdapter$$Lambda$2.lambdaFactory$(this, message, holder));
                RxView.clicks(((SendMessageVideoHolder) holder).f1726b).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(ChatMessageAdapter$$Lambda$3.lambdaFactory$(this, message));
                if (message.getUploadState() == 2) {
                    m993a(holder, 0);
                } else if (message.getUploadState() == 3) {
                    m993a(holder, 1);
                } else if (message.getUploadState() == 1) {
                    long currentTimeMillis;
                    if (this.f1742f <= 0) {
                        currentTimeMillis = System.currentTimeMillis();
                    } else {
                        currentTimeMillis = this.f1742f;
                    }
                    if (currentTimeMillis - message.getTimestamp() < 86400000) {
                        m993a(holder, 2);
                        final ViewHolder viewHolder = holder;
                        UploadHelper.getInstance().registerCallback(message.getMsgId(), new UploadCallback(this) {
                            final /* synthetic */ ChatMessageAdapter f1698b;

                            public void uploadProgress(String id, long currentSize, long totalSize, float progress, long networkSpeed) {
                                int i;
                                if (progress < 0.0f) {
                                    this.f1698b.m993a(viewHolder, 1);
                                }
                                if (progress >= 100.0f || progress < 0.0f) {
                                    UploadHelper.getInstance().unRegisterCallback(id);
                                }
                                VideoUploadProgressView videoUploadProgressView = ((SendMessageVideoHolder) viewHolder).f1728d;
                                if (progress >= 0.0f) {
                                    i = (int) progress;
                                } else {
                                    i = 0;
                                }
                                videoUploadProgressView.setProgress(i);
                                if (progress == 100.0f) {
                                    this.f1698b.m993a(viewHolder, 0);
                                }
                            }
                        });
                    } else {
                        m993a(holder, 3);
                    }
                } else {
                    m993a(holder, 0);
                }
                if (message.isRead()) {
                    ((SendMessageVideoHolder) holder).f1731g.setVisibility(0);
                    ((SendMessageVideoHolder) holder).f1731g.setText(this.f1741e.getString(C0376R.string.get_coin, new Object[]{Integer.valueOf(message.getCoin())}));
                } else {
                    ((SendMessageVideoHolder) holder).f1731g.setVisibility(8);
                }
                if ((this.f1742f <= 0 ? System.currentTimeMillis() : this.f1742f) - message.getTimestamp() > 86400000) {
                    m993a(holder, 3);
                }
            } else if (holder instanceof ReceiveMessageVideoHolder) {
                if (TextUtils.isEmpty(this.f1738b.getAvatar()) || !this.f1738b.getAvatar().startsWith("http://")) {
                    ((ReceiveMessageVideoHolder) holder).f1710a.setImageURI("res://" + this.f1741e.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
                } else {
                    FrescoUtil.setImageURI(((ReceiveMessageVideoHolder) holder).f1710a, ResourceUrlUtil.getNormalAvatar(this.f1738b.getAvatar(), DisplayUtil.dip2px(36.0f)));
                }
                time = ((ReceiveMessageVideoHolder) holder).f1712c;
                if (!TextUtils.isEmpty(message.getVideoThumb())) {
                    Uri uri = Uri.parse(message.getVideoThumb());
                    if (message.isNeedPay()) {
                        FrescoUtil.setImageURI(this.f1741e, ((ReceiveMessageVideoHolder) holder).f1711b, uri, new BlurProcessor(this.f1741e));
                    } else {
                        FrescoUtil.setImageURI(((ReceiveMessageVideoHolder) holder).f1711b, uri);
                    }
                }
                if ((this.f1742f <= 0 ? System.currentTimeMillis() : this.f1742f) - message.getTimestamp() > 86400000) {
                    ((ReceiveMessageVideoHolder) holder).f1713d.setVisibility(0);
                    ((ReceiveMessageVideoHolder) holder).f1714e.setVisibility(8);
                } else {
                    ((ReceiveMessageVideoHolder) holder).f1713d.setVisibility(8);
                    ((ReceiveMessageVideoHolder) holder).f1714e.setVisibility(0);
                }
                RxView.clicks(((ReceiveMessageVideoHolder) holder).f1711b).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(ChatMessageAdapter$$Lambda$4.lambdaFactory$(this, message));
            } else if (holder instanceof ReceiveTextMessageHolder) {
                if (TextUtils.isEmpty(this.f1738b.getAvatar()) || !this.f1738b.getAvatar().startsWith("http://")) {
                    ((ReceiveTextMessageHolder) holder).f1715a.setImageURI("res://" + this.f1741e.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
                } else {
                    FrescoUtil.setImageURI(((ReceiveTextMessageHolder) holder).f1715a, ResourceUrlUtil.getNormalAvatar(this.f1738b.getAvatar(), DisplayUtil.dip2px(36.0f)));
                }
                time = ((ReceiveTextMessageHolder) holder).f1717c;
                ((ReceiveTextMessageHolder) holder).f1716b.setText(message.getMsgText());
                if (TextUtils.isEmpty(message.getUrl()) || TextUtils.isEmpty(message.getUrlMark())) {
                    ((ReceiveTextMessageHolder) holder).f1716b.setText(message.getMsgText());
                } else {
                    finalText = message.getMsgText() + " " + message.getUrlMark();
                    spannableString = new SpannableString(finalText);
                    spannableString.setSpan(new HttpSpan(message.getUrl(), this.f1741e, true), finalText.indexOf(message.getUrlMark()), finalText.length(), 33);
                    ((ReceiveTextMessageHolder) holder).f1716b.setText(spannableString);
                    ((ReceiveTextMessageHolder) holder).f1716b.setMovementMethod(LinkMovementMethod.getInstance());
                }
            } else if (holder instanceof SendTextMessageHolder) {
                if (TextUtils.isEmpty(this.f1738b.getAvatar()) || !this.f1738b.getAvatar().startsWith("http://")) {
                    ((SendTextMessageHolder) holder).f1733a.setImageURI("res://" + this.f1741e.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
                } else {
                    FrescoUtil.setImageURI(((SendTextMessageHolder) holder).f1733a, ResourceUrlUtil.getNormalAvatar(this.f1739c.getAvatar(), DisplayUtil.dip2px(36.0f)));
                }
                ((SendTextMessageHolder) holder).f1734b.setText(message.getMsgText());
                time = ((SendTextMessageHolder) holder).f1735c;
                if (message.isSendFailed()) {
                    ((SendTextMessageHolder) holder).f1736d.setVisibility(0);
                } else {
                    ((SendTextMessageHolder) holder).f1736d.setVisibility(8);
                }
                if (TextUtils.isEmpty(message.getUrl()) || TextUtils.isEmpty(message.getUrlMark())) {
                    ((SendTextMessageHolder) holder).f1734b.setText(message.getMsgText());
                } else {
                    finalText = message.getMsgText() + " " + message.getUrlMark();
                    spannableString = new SpannableString(finalText);
                    spannableString.setSpan(new HttpSpan(message.getUrl(), this.f1741e, true), finalText.indexOf(message.getUrlMark()), finalText.length(), 33);
                    ((SendTextMessageHolder) holder).f1734b.setText(spannableString);
                    ((SendTextMessageHolder) holder).f1734b.setMovementMethod(LinkMovementMethod.getInstance());
                }
                RxView.clicks(((SendTextMessageHolder) holder).f1734b).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(ChatMessageAdapter$$Lambda$5.lambdaFactory$(this, message));
            } else if (holder instanceof ReceiveGiftMessageHolder) {
                if (TextUtils.isEmpty(this.f1738b.getAvatar()) || !this.f1738b.getAvatar().startsWith("http://")) {
                    ((ReceiveGiftMessageHolder) holder).f1703a.setImageURI("res://" + this.f1741e.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
                } else {
                    FrescoUtil.setImageURI(((ReceiveGiftMessageHolder) holder).f1703a, ResourceUrlUtil.getNormalAvatar(this.f1738b.getAvatar(), DisplayUtil.dip2px(36.0f)));
                }
                time = ((ReceiveGiftMessageHolder) holder).f1704b;
                FrescoUtil.setImageURI(((ReceiveGiftMessageHolder) holder).f1706d, message.getGiftCover());
                if (message.getCoin() > 0) {
                    ((ReceiveGiftMessageHolder) holder).f1705c.setText(this.f1741e.getString(C0376R.string.get_gift_coin, new Object[]{message.getGiftName(), Integer.valueOf(message.getCoin())}));
                } else {
                    ((ReceiveGiftMessageHolder) holder).f1705c.setText(this.f1741e.getString(C0376R.string.get_gift_coin_free, new Object[]{message.getGiftName()}));
                }
                RxView.clicks(((ReceiveGiftMessageHolder) holder).f1706d).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(ChatMessageAdapter$$Lambda$6.lambdaFactory$(this, message));
            } else if (holder instanceof SendGiftMessageHolder) {
                if (TextUtils.isEmpty(this.f1738b.getAvatar()) || !this.f1738b.getAvatar().startsWith("http://")) {
                    ((SendGiftMessageHolder) holder).f1718a.setImageURI("res://" + this.f1741e.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
                } else {
                    FrescoUtil.setImageURI(((SendGiftMessageHolder) holder).f1718a, ResourceUrlUtil.getNormalAvatar(this.f1739c.getAvatar(), DisplayUtil.dip2px(36.0f)));
                }
                time = ((SendGiftMessageHolder) holder).f1720c;
                FrescoUtil.setImageURI(((SendGiftMessageHolder) holder).f1719b, message.getGiftCover());
                if (message.isSendFailed()) {
                    ((SendGiftMessageHolder) holder).f1721d.setVisibility(0);
                } else {
                    ((SendGiftMessageHolder) holder).f1721d.setVisibility(8);
                }
                RxView.clicks(((SendGiftMessageHolder) holder).f1719b).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(ChatMessageAdapter$$Lambda$7.lambdaFactory$(this, message));
            }
            if (message.isShowTime()) {
                time.setText(DateUtil.getChatMessageDate(message.getTimestamp()) + " " + DateUtil.getChatMessageTime(message.getTimestamp()));
                time.setVisibility(0);
                return;
            }
            time.setVisibility(8);
        }
    }

    /* synthetic */ void m1006a(Object aVoid) throws Exception {
        if (this.f1741e instanceof ChatMessageActivity) {
            this.f1741e.hideGiftLayout();
        }
    }

    /* synthetic */ void m1003a(UserChatMessage message, ViewHolder holder, Object aVoid) throws Exception {
        DialogHelper.INSTANCE.showReUploadVideoDialog(this.f1741e, ChatMessageAdapter$$Lambda$16.lambdaFactory$(message, holder), ChatMessageAdapter$$Lambda$17.lambdaFactory$(), ChatMessageAdapter$$Lambda$18.lambdaFactory$());
    }

    static /* synthetic */ void m996a(UserChatMessage message, ViewHolder holder, DialogInterface dialog, int which) {
        EventBus.getDefault().post(new UploadStateEvent(new String[]{message.getMsgId()}, message.getVideoThumb(), message.getVideoPath(), new String[]{message.getUid()}, message.getDiamondNum(), message.getTimeLen(), false));
        ((SendMessageVideoHolder) holder).f1730f.setVisibility(8);
        ((SendMessageVideoHolder) holder).f1728d.setVisibility(0);
        ((SendMessageVideoHolder) holder).f1729e.setVisibility(8);
    }

    static /* synthetic */ void m1002c(DialogInterface dialog, int which) {
    }

    static /* synthetic */ void m1001c(DialogInterface dialog) {
    }

    /* synthetic */ void m1011e(UserChatMessage message, Object aVoid) throws Exception {
        if (this.f1741e instanceof BaseActivity) {
            if ((this.f1742f <= 0 ? System.currentTimeMillis() : this.f1742f) - message.getTimestamp() > 86400000) {
                ToastUtil.getInstance().show(this.f1741e, (int) C0376R.string.video_message_error_expired);
            } else {
                VideoMessageActivity.launchVideoMessage((BaseActivity) this.f1741e, message);
            }
        }
    }

    /* synthetic */ void m1010d(UserChatMessage message, Object aVoid) throws Exception {
        if (this.f1741e instanceof BaseActivity) {
            if ((this.f1742f <= 0 ? System.currentTimeMillis() : this.f1742f) - message.getTimestamp() > 86400000) {
                ToastUtil.getInstance().show(this.f1741e, (int) C0376R.string.video_message_error_expired);
            } else {
                m994a(message);
            }
        }
    }

    /* synthetic */ void m1009c(UserChatMessage message, Object aVoid) throws Exception {
        if ((this.f1741e instanceof ChatMessageActivity) && message.isSendFailed()) {
            DialogHelper.INSTANCE.showReSendTextDialog(this.f1741e, ChatMessageAdapter$$Lambda$13.lambdaFactory$(message), ChatMessageAdapter$$Lambda$14.lambdaFactory$(), ChatMessageAdapter$$Lambda$15.lambdaFactory$());
        }
    }

    static /* synthetic */ void m999b(DialogInterface dialog, int which) {
    }

    static /* synthetic */ void m998b(DialogInterface dialog) {
    }

    /* synthetic */ void m1008b(UserChatMessage message, Object aVoid) throws Exception {
        if (this.f1741e instanceof ChatMessageActivity) {
            this.f1741e.showGift(message.getGiftMusic(), message.getGiftId(), message.getGiftSource());
        }
    }

    /* synthetic */ void m1004a(UserChatMessage message, Object aVoid) throws Exception {
        if (!(this.f1741e instanceof ChatMessageActivity)) {
            return;
        }
        if (message.isSendFailed()) {
            DialogHelper.INSTANCE.showReSendGiftDialog(this.f1741e, ChatMessageAdapter$$Lambda$10.lambdaFactory$(message), ChatMessageAdapter$$Lambda$11.lambdaFactory$(), ChatMessageAdapter$$Lambda$12.lambdaFactory$());
        } else {
            this.f1741e.showGift(message.getGiftMusic(), message.getGiftId(), message.getGiftSource());
        }
    }

    static /* synthetic */ void m992a(DialogInterface dialog, int which) {
    }

    static /* synthetic */ void m991a(DialogInterface dialog) {
    }

    private void m993a(ViewHolder holder, int state) {
        switch (state) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                ((SendMessageVideoHolder) holder).f1732h.setVisibility(8);
                ((SendMessageVideoHolder) holder).f1730f.setVisibility(0);
                ((SendMessageVideoHolder) holder).f1728d.setVisibility(8);
                ((SendMessageVideoHolder) holder).f1729e.setVisibility(8);
                return;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                ((SendMessageVideoHolder) holder).f1732h.setVisibility(8);
                ((SendMessageVideoHolder) holder).f1730f.setVisibility(8);
                ((SendMessageVideoHolder) holder).f1728d.setVisibility(8);
                ((SendMessageVideoHolder) holder).f1729e.setVisibility(0);
                return;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                ((SendMessageVideoHolder) holder).f1732h.setVisibility(8);
                ((SendMessageVideoHolder) holder).f1730f.setVisibility(8);
                ((SendMessageVideoHolder) holder).f1728d.setVisibility(0);
                ((SendMessageVideoHolder) holder).f1729e.setVisibility(8);
                return;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                ((SendMessageVideoHolder) holder).f1732h.setVisibility(0);
                ((SendMessageVideoHolder) holder).f1730f.setVisibility(8);
                ((SendMessageVideoHolder) holder).f1728d.setVisibility(8);
                ((SendMessageVideoHolder) holder).f1729e.setVisibility(8);
                return;
            default:
                return;
        }
    }

    private void m994a(UserChatMessage message) {
        if (message.getMsgType() == 3) {
            ((BaseActivity) this.f1741e).getDataLayer().getMessageManager().videoMessageRequest(message.getVideoId()).compose(SchedulersCompat.applyIoSchedulers()).observeOn(AndroidSchedulers.mainThread()).subscribe(ChatMessageAdapter$$Lambda$8.lambdaFactory$(this, message), ChatMessageAdapter$$Lambda$9.lambdaFactory$(this));
        }
    }

    /* synthetic */ void m1005a(UserChatMessage message, String uri) throws Exception {
        if (!TextUtils.isEmpty(uri)) {
            VideoMessageActivity.launchVideoMessage((BaseActivity) this.f1741e, message);
        }
    }

    /* synthetic */ void m1007a(Throwable throwable) throws Exception {
    }

    public int getItemViewType(int position) {
        switch (((UserChatMessage) this.f1737a.get(position)).getMsgType()) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                return ITEM_TYPE.ITEM_TYPE_RECIVE.ordinal();
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                return ITEM_TYPE.ITEM_TYPE_SEND.ordinal();
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                return ITEM_TYPE.ITEM_TYPE_RECIVE_VIDEO.ordinal();
            case swscale.SWS_CS_FCC /*4*/:
                return ITEM_TYPE.ITEM_TYPE_SEND_VIDEO.ordinal();
            case postproc.PP_QUALITY_MAX /*6*/:
                return ITEM_TYPE.ITEM_TYPE_RECIVE_TEXT.ordinal();
            case swscale.SWS_CS_SMPTE240M /*7*/:
                return ITEM_TYPE.ITEM_TYPE_SEND_TEXT.ordinal();
            case swscale.SWS_X /*8*/:
                return ITEM_TYPE.ITEM_TYPE_RECIVE_GIFT.ordinal();
            case swscale.SWS_CS_BT2020 /*9*/:
                return ITEM_TYPE.ITEM_TYPE_SEND_GIFT.ordinal();
            default:
                return super.getItemViewType(position);
        }
    }

    public int getItemCount() {
        return this.f1737a == null ? 0 : this.f1737a.size();
    }
}
