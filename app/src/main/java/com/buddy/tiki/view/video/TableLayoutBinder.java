package com.buddy.tiki.view.video;

import android.content.Context;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import com.buddy.tiki.C0376R;

public class TableLayoutBinder {
    public ViewGroup f3440a;
    public TableLayout f3441b;
    private Context f3442c;

    private static class ViewHolder {
        public TextView f3438a;
        public TextView f3439b;

        private ViewHolder() {
        }

        public void setName(String name) {
            if (this.f3438a != null) {
                this.f3438a.setText(name);
            }
        }

        public void setValue(String value) {
            if (this.f3439b != null) {
                this.f3439b.setText(value);
            }
        }
    }

    public TableLayoutBinder(Context context) {
        this(context, (int) C0376R.layout.table_media_info);
    }

    public TableLayoutBinder(Context context, int layoutResourceId) {
        this.f3442c = context;
        this.f3440a = (ViewGroup) LayoutInflater.from(this.f3442c).inflate(layoutResourceId, null);
        this.f3441b = (TableLayout) this.f3440a.findViewById(C0376R.id.table);
    }

    public TableLayoutBinder(Context context, TableLayout tableLayout) {
        this.f3442c = context;
        this.f3440a = tableLayout;
        this.f3441b = tableLayout;
    }

    public View appendRow1(String name, String value) {
        return appendRow(C0376R.layout.table_media_info_row1, name, value);
    }

    public View appendRow1(int nameId, String value) {
        return appendRow1(this.f3442c.getString(nameId), value);
    }

    public View appendRow2(String name, String value) {
        return appendRow(C0376R.layout.table_media_info_row2, name, value);
    }

    public View appendRow2(int nameId, String value) {
        return appendRow2(this.f3442c.getString(nameId), value);
    }

    public View appendSection(String name) {
        return appendRow(C0376R.layout.table_media_info_section, name, null);
    }

    public View appendSection(int nameId) {
        return appendSection(this.f3442c.getString(nameId));
    }

    public View appendRow(int layoutId, String name, String value) {
        ViewGroup rowView = (ViewGroup) LayoutInflater.from(this.f3442c).inflate(layoutId, this.f3441b, false);
        setNameValueText(rowView, name, value);
        this.f3441b.addView(rowView);
        return rowView;
    }

    public ViewHolder obtainViewHolder(View rowView) {
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        if (viewHolder != null) {
            return viewHolder;
        }
        viewHolder = new ViewHolder();
        viewHolder.f3438a = (TextView) rowView.findViewById(C0376R.id.name);
        viewHolder.f3439b = (TextView) rowView.findViewById(C0376R.id.value);
        rowView.setTag(viewHolder);
        return viewHolder;
    }

    public void setNameValueText(View rowView, String name, String value) {
        ViewHolder viewHolder = obtainViewHolder(rowView);
        viewHolder.setName(name);
        viewHolder.setValue(value);
    }

    public void setValueText(View rowView, String value) {
        obtainViewHolder(rowView).setValue(value);
    }

    public ViewGroup buildLayout() {
        return this.f3440a;
    }

    public Builder buildAlertDialogBuilder() {
        Builder dlgBuilder = new Builder(this.f3442c);
        dlgBuilder.setView(buildLayout());
        return dlgBuilder;
    }
}
