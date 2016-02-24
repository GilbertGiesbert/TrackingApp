package com.mwx.android.trackingapp.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.mwx.android.trackingapp.IntentExtras;
import com.mwx.android.trackingapp.ItemListAdapter;
import com.mwx.android.trackingapp.R;
import com.mwx.android.trackingapp.model.Item;
import com.mwx.android.trackingapp.model.ItemDao;


public class ItemListActivity extends TrackingActivity {

    private ItemListAdapter itemAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TITLE = "Item list screen";
        LAYOUT_ID = R.layout.activity_item_list;

        super.onCreate(savedInstanceState);

        initList();
    }


    private void initList() {


        itemAdapter = new ItemListAdapter(this, new ItemDao().getAllItems());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleListClick(position);
            }
        });
    }

    private void handleListClick(int position){

        Item i = itemAdapter.getItem(position);

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("choose item")
                .setLabel(i.getName())
                .build());

        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(IntentExtras.ITEM_ID, i.getId());
        startActivity(intent);
    }
}