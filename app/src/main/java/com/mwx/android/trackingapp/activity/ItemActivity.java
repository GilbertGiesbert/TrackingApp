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

public class ItemActivity extends TrackingActivity {

    private long itemId = Constants.NO_VALUE;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TITLE = "Item screen";
        LAYOUT_ID = R.layout.activity_item;

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
        startActivity(intent);
    }


    private void initTextView() {

        String text = getResources().getString(R.string.item_promptToBuy);
        text = String.format(text, item.getName(), item.getPrice());

        TextView tv = (TextView) findViewById(R.id.tv_congrats);
        tv.setText(text);

    }


    private void initButton() {

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("Click on buy button")
                        .setLabel(item.getName())
                        .build());

                Intent intent = new Intent(ItemActivity.this, ItemSoldActivity.class);
                intent.putExtra(IntentExtras.ITEM_ID, item.getId());
                startActivity(intent);
            }
        });
    }
}
