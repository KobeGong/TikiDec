package com.buddy.tiki.wertc;

import android.app.Activity;
import android.os.Bundle;

public class PreferenceSettingActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(16908290, new PreferenceSettingFragment()).commit();
    }
}
