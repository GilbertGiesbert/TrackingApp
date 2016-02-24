package com.mwx.android.trackingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mwx.android.trackingapp.model.Item;

import java.util.List;

/**
 * Created by joern on 24.02.2016.
 */
public class ItemListAdapter extends ArrayAdapter<Item>{

    private static final int LAYOUT_ROW = android.R.layout.simple_list_item_1;

    private static class ViewHolder {
        TextView tv;
    }


    public ItemListAdapter(Context context, List<Item> objects) {
        super(context, LAYOUT_ROW, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(LAYOUT_ROW, parent, false);
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            vh = new ViewHolder();
            vh.tv = tv;
            convertView.setTag(vh);

        }
        else{
            vh = (ViewHolder) convertView.getTag();
        }

        Item i = getItem(position);
        vh.tv.setText(i.getName());
        return convertView;
    }
}