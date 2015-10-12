package com.example.bkzhou.nerdlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by bkzhou on 15-10-9.
 */
public class NerdLauncherFragment extends ListFragment {
    private static final String TAG = "NerdLauncherFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent startupIntent = new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(startupIntent,0);
        Log.d(TAG, activities.size() + "");
        Collections.sort(activities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo resolveInfo, ResolveInfo t1) {
                PackageManager packageManager = getActivity().getPackageManager();

                return String.CASE_INSENSITIVE_ORDER.compare(
                        resolveInfo.loadLabel(packageManager).toString(),
                        t1.loadLabel(packageManager).toString());
            }
        });
        ArrayAdapter<ResolveInfo> adapter =  new ArrayAdapter<ResolveInfo>(getActivity(),android.R.layout.simple_list_item_1,activities){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                PackageManager packageManager = getActivity().getPackageManager();
                View v = super.getView(position,convertView,parent);
                TextView tv = (TextView) v;
                ResolveInfo ri = getItem(position);
                tv.setText(ri.loadLabel(packageManager));
                return v;
            }
        };
        setListAdapter(adapter);
    }
}
