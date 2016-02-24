package com.mwx.android.trackingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.mwx.android.trackingapp.Constants;
import com.mwx.android.trackingapp.IntentExtras;
import com.mwx.android.trackingapp.R;
import com.mwx.android.trackingapp.model.Item;
import com.mwx.android.trackingapp.model.ItemDao;

public class ItemSoldActivity extends TrackingActivity {

    private int discount = 50;

    private long itemId = Constants.NO_VALUE;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TITLE = "Item sold screen";
        LAYOUT_ID = R.layout.activity_item_sold;

        super.onCreate(savedInstanceState);


        if(item == null)
            initItem();

        if(item == null) {
            go2ItemListActivity();
            return;
        }

        initTextView();
        initButton();

    }


    private void initItem() {

        if(itemId == Constants.NO_VALUE){
            itemId = getIntent().getLongExtra(IntentExtras.ITEM_ID, Constants.NO_VALUE);
        }

        if(itemId != Constants.NO_VALUE){
            item = new ItemDao().getItem(itemId);
        }
    }

    private void go2ItemListActivity() {
        Intent intent = new Intent(this, ItemListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private void initTextView() {

        String congrats = getResources().getString(R.string.itemSold_congrats);
        congrats = String.format(congrats, item.getName(), item.getPrice());
        TextView tv_congrats = (TextView) findViewById(R.id.tv_congrats);
        tv_congrats.setText(congrats);

        String offer = getResources().getString(R.string.itemSold_offer);
        offer = String.format(offer, discount);
        TextView tv_offer = (TextView) findViewById(R.id.tv_offer);
        tv_offer.setText(offer);

    }

    private void initButton() {

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("Clicked on back to item list")
                        .build());

                go2ItemListActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        go2ItemListActivity();
    }
}