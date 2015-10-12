package com.example.bkzhou.nerdlauncher;

import android.support.v4.app.Fragment;

/**
 * Created by bkzhou on 15-10-9.
 */
public class NerdLauncherActivity extends  SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NerdLauncherFragment();
    }
}
