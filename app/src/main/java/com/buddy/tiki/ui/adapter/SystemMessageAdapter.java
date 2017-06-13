package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.trace.InboxMessage;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import com.buddy.tiki.ui.adapter.base.BaseAdapter;
import com.buddy.tiki.util.DateUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class SystemMessageAdapter extends BaseAdapter<MessageHolder, InboxMessage> {

    static class HttpSpan extends ClickableSpan {
        private final WeakReference<Context> f1834a;
        private final String f1835b;
        private final boolean f1836c;

        public HttpSpan(String url, Context context, boolean clickAble) {
            this.f1835b = url;
            this.f1834a = new WeakReference(context);
            this.f1836c = clickAble;
        }

        public void onClick(View widget) {
            if (this.f1834a.get() != null && this.f1836c) {
                WebBrowserActivity.launchWeb((Context) this.f1834a.get(), this.f1835b);
            }
        }

        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            if (this.f1834a.get() != null) {
                ds.setColor(((Context) this.f1834a.get()).getResources().getColor(C0376R.color.brightSkyBlue));
            }
        }
    }

    static class MessageHolder extends ViewHolder {
        AppCompatTextView f1837a;
        SimpleDraweeView f1838b;
        AppCompatTextView f1839c;

        public MessageHolder(View itemView) {
            super(itemView);
            this.f1837a = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_time);
            this.f1838b = (SimpleDraweeView) itemView.findViewById(C0376R.id.msg_avatar);
            this.f1839c = (AppCompatTextView) itemView.findViewById(C0376R.id.msg_text);
        }
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m1053a(view);
    }

    public SystemMessageAdapter(@NonNull Context context) {
        super(context);
    }

    public void addDataList(@NonNull List<InboxMessage> list) {
        int preSize = this.a.size();
        this.a.addAll(list);
        notifyItemRangeInserted(preSize, list.size());
    }

    public void addData(@NonNull InboxMessage data) {
    }

    protected int mo2165a() {
        return C0376R.layout.item_system_message;
    }

    protected MessageHolder m1053a(View view) {
        return new MessageHolder(view);
    }

    public void onBindViewHolder(MessageHolder holder, int position) {
        InboxMessage inboxMessage = (InboxMessage) this.a.get(position);
        if (inboxMessage != null) {
            if (position == 0 || !(this.a.get(position - 1) == null || DateUtil.isMessageTimeClose(((InboxMessage) this.a.get(position - 1)).getCtime(), inboxMessage.getCtime()))) {
                holder.f1837a.setVisibility(0);
                holder.f1837a.setText(DateUtil.format(new Date(inboxMessage.getCtime()), (DateFormat) DateUtil.f2354d.get()));
            } else {
                holder.f1837a.setVisibility(8);
            }
            holder.f1838b.setImageURI("res://" + this.b.getPackageName() + "/" + C0376R.mipmap.ic_launch);
            if (TextUtils.isEmpty(inboxMessage.getUrl()) || TextUtils.isEmpty(inboxMessage.getUrlMark())) {
                holder.f1839c.setText(inboxMessage.getContent());
                return;
            }
            String finalText = inboxMessage.getContent() + " " + inboxMessage.getUrlMark();
            SpannableString spannableString = new SpannableString(finalText);
            spannableString.setSpan(new HttpSpan(inboxMessage.getUrl(), this.b, true), finalText.indexOf(inboxMessage.getUrlMark()), finalText.length(), 33);
            holder.f1839c.setText(spannableString);
            holder.f1839c.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
