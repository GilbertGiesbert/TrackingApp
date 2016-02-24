package com.mwx.android.trackingapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mwx.android.trackingapp.R;
import com.mwx.android.trackingapp.app.TrackingApp;

/**
 * Created by joern on 23.02.2016.
 */
public class TrackingActivity extends AppCompatActivity {

    protected String TITLE;
    protected int LAYOUT_ID;

    protected Tracker tracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_ID);
        setTitle(TITLE);

        initTracker();
    }


    @Override
    protected void onResume() {
        super.onResume();  // Always call the superclass method first

        tracker.setScreenName(TITLE);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_common, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.m_crash:
                int crash = 1/0;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initTracker() {
        TrackingApp app = (TrackingApp) getApplication();
        tracker = app.getDefaultTracker();
    }
}