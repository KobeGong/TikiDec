package com.buddy.tiki.ui.dialog;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.app.VersionInfo;
import com.buddy.tiki.ui.dialog.base.BaseDialogFragment;
import org.parceler.Parcels;

public class UpdateDialog extends BaseDialogFragment {
    private VersionInfo f1998c;
    private OnClickListener f1999d;
    @BindView(2131820924)
    AppCompatButton mUpdateBtn;
    @BindView(2131820923)
    AppCompatTextView mUpdateContent;

    protected int mo2172a() {
        return C0376R.layout.dialog_update;
    }

    protected void mo2173a(Bundle savedInstanceState) {
        m1191b();
        m1192c();
        m1193d();
    }

    private void m1191b() {
        if (getArguments() != null) {
            this.f1998c = (VersionInfo) Parcels.unwrap(getArguments().getParcelable("PARAM_KEY_VERSION"));
        }
    }

    private void m1192c() {
        if (this.f1998c != null) {
            this.mUpdateContent.setText(this.f1998c.getUpdateDesc());
        }
    }

    private void m1193d() {
        if (this.f1999d != null) {
            this.mUpdateBtn.setOnClickListener(this.f1999d);
        }
    }

    public void setOnUpdateListener(OnClickListener l) {
        this.f1999d = l;
    }
}
