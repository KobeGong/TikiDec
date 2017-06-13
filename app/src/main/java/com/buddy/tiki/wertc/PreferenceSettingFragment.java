package com.buddy.tiki.wertc;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.buddy.tiki.C0376R;

public class PreferenceSettingFragment extends PreferenceFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(C0376R.xml.preferences);
    }
}
