package com.buddy.tiki.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ui.dialog.base.BaseDialogFragment;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.view.CenterAppCompatTextView;

public class MultiChoiceDialog extends BaseDialogFragment {
    private String f1960c;
    private CharSequence[] f1961d;
    private int[] f1962e;
    private int f1963f;
    private boolean f1964g;
    private boolean f1965h;
    private View f1966i;
    private LayoutParams f1967j;
    private LayoutInflater f1968k;
    private OnClickListener f1969l;
    private OnCancelListener f1970m;
    @BindView(2131820886)
    ListView mDialogContent;
    @BindView(2131820884)
    AppCompatTextView mDialogTitle;
    @BindView(2131820917)
    FrameLayout mTitleContainer;
    @BindView(2131820918)
    View mTitleDivider;
    private boolean f1971n = true;

    public static class Builder {
        MultiChoiceDialog f1955a = new MultiChoiceDialog();
        Context f1956b;

        public Builder(Context context) {
            this.f1956b = context;
        }

        public Builder setTitle(String title) {
            this.f1955a.f1960c = title;
            return this;
        }

        public Builder setTitle(@StringRes int title) {
            this.f1955a.f1960c = this.f1956b.getString(title);
            return this;
        }

        public Builder setItems(@ArrayRes int array, OnClickListener listener) {
            return setItems(array, null, 0, listener);
        }

        public Builder setItems(@ArrayRes int array, int[] drawables, OnClickListener listener) {
            return setItems(array, drawables, (int) GravityCompat.START, listener);
        }

        public Builder setItems(@ArrayRes int array, int[] drawables, int gravity, OnClickListener listener) {
            this.f1955a.f1961d = this.f1956b.getResources().getTextArray(array);
            this.f1955a.f1962e = drawables;
            this.f1955a.f1963f = gravity;
            this.f1955a.f1969l = listener;
            return this;
        }

        public Builder setItems(@NonNull String[] array, int[] drawables, int gravity, OnClickListener listener) {
            this.f1955a.f1961d = array;
            this.f1955a.f1962e = drawables;
            this.f1955a.f1963f = gravity;
            this.f1955a.f1969l = listener;
            return this;
        }

        public Builder setAllBold(boolean allBold) {
            this.f1955a.f1964g = allBold;
            this.f1955a.f1965h = false;
            return this;
        }

        public Builder setAllBoldExceptLast(boolean allBoldExceptLast) {
            this.f1955a.f1965h = allBoldExceptLast;
            this.f1955a.f1964g = false;
            return this;
        }

        public Builder setCustomTitle(View customTitle, @Nullable LayoutParams layoutParams) {
            this.f1955a.f1966i = customTitle;
            MultiChoiceDialog multiChoiceDialog = this.f1955a;
            if (layoutParams == null) {
                layoutParams = new LayoutParams(-1, -2);
            }
            multiChoiceDialog.f1967j = layoutParams;
            return this;
        }

        public Builder setCustomTitle(@LayoutRes int customTitle, @Nullable LayoutParams layoutParams) {
            this.f1955a.f1966i = LayoutInflater.from(this.f1956b).inflate(customTitle, null);
            MultiChoiceDialog multiChoiceDialog = this.f1955a;
            if (layoutParams == null) {
                layoutParams = new LayoutParams(-1, -2);
            }
            multiChoiceDialog.f1967j = layoutParams;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener listener) {
            this.f1955a.f1970m = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.f1955a.f1971n = cancelable;
            return this;
        }

        public MultiChoiceDialog create() {
            return this.f1955a;
        }

        public void show(@NonNull FragmentManager fragmentManager, String tag) {
            fragmentManager.beginTransaction().add(this.f1955a, tag).addToBackStack(null).commitAllowingStateLoss();
        }
    }

    private class ContentAdapter extends BaseAdapter {
        final /* synthetic */ MultiChoiceDialog f1959a;

        class ViewHolder {
            CenterAppCompatTextView f1957a;
            final /* synthetic */ ContentAdapter f1958b;

            ViewHolder(ContentAdapter this$1) {
                this.f1958b = this$1;
            }
        }

        private ContentAdapter(MultiChoiceDialog multiChoiceDialog) {
            this.f1959a = multiChoiceDialog;
        }

        public int getCount() {
            return this.f1959a.f1961d == null ? 0 : this.f1959a.f1961d.length;
        }

        public Object getItem(int position) {
            return this.f1959a.f1961d[position];
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = this.f1959a.f1968k.inflate(C0376R.layout.item_multi_choice, parent, false);
                holder = new ViewHolder(this);
                holder.f1957a = (CenterAppCompatTextView) convertView.findViewById(C0376R.id.list_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.f1957a.setText(this.f1959a.f1961d[position].toString());
            if (this.f1959a.f1964g) {
                holder.f1957a.getPaint().setFakeBoldText(true);
            } else if (this.f1959a.f1965h) {
                holder.f1957a.getPaint().setFakeBoldText(position != getCount() + -1);
            } else {
                holder.f1957a.getPaint().setFakeBoldText(false);
            }
            if (this.f1959a.f1962e != null && this.f1959a.f1962e.length > position && this.f1959a.f1962e[position] != 0) {
                holder.f1957a.setGravity(16);
                Drawable drawable = AppCompatResources.getDrawable(this.f1959a.getContext(), this.f1959a.f1962e[position]);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    switch (this.f1959a.f1963f) {
                        case GravityCompat.START /*8388611*/:
                            holder.f1957a.setCompoundDrawablesRelative(drawable, null, null, null);
                            break;
                        case GravityCompat.END /*8388613*/:
                            holder.f1957a.setCompoundDrawablesRelative(null, null, drawable, null);
                            break;
                    }
                }
            }
            holder.f1957a.setGravity(17);
            return convertView;
        }
    }

    protected int mo2172a() {
        return C0376R.layout.dialog_select;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(1);
        dialog.getWindow().setBackgroundDrawableResource(C0376R.color.transparent);
        dialog.getWindow().setLayout(DisplayUtil.dip2px(270.0f), -2);
        return dialog;
    }

    protected void mo2173a(Bundle savedInstanceState) {
        this.f1968k = LayoutInflater.from(getContext());
        m1152b();
    }

    private void m1152b() {
        if (TextUtils.isEmpty(this.f1960c)) {
            this.mDialogTitle.setVisibility(8);
        } else {
            this.mDialogTitle.setText(this.f1960c);
        }
        if (this.f1966i != null) {
            this.mTitleContainer.addView(this.f1966i, this.f1967j);
        } else {
            this.mTitleContainer.setVisibility(8);
        }
        this.mDialogContent.setAdapter(new ContentAdapter());
        this.mDialogContent.setOnItemClickListener(MultiChoiceDialog$$Lambda$1.lambdaFactory$(this));
        setCancelable(this.f1971n);
    }

    /* synthetic */ void m1161a(AdapterView parent, View view, int position, long id) {
        this.f1969l.onClick(getDialog(), position);
        dismiss();
    }

    public void onCancel(DialogInterface dialog) {
        if (this.f1970m != null) {
            this.f1970m.onCancel(dialog);
        }
    }
}
