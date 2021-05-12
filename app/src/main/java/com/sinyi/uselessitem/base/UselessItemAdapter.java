package com.sinyi.uselessitem.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handlelife.BuildConfig;
import com.example.handlelife.R;

import com.sinyi.uselessitem.base.UselessItem;
import com.sinyi.uselessitem.base.UselessItem.itemDataType;

import java.util.ArrayList;
import java.util.List;

public class UselessItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<UselessItem> uselessItemList = new ArrayList<>();

    public UselessItemAdapter(Context context, List<UselessItem> uselessItems) {
        mContext = context;
        uselessItemList = uselessItems;
    }

    @Override
    public int getCount() {
        return uselessItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return uselessItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UselessItemViewHolder uselessItemViewHolder;
        View v;
        UselessItem uselessItem=uselessItemList.get(position);

        if (convertView == null) {
            v = View.inflate(mContext, R.layout.useless_item_layout, null);
            uselessItemViewHolder = new UselessItemViewHolder();
            if (BuildConfig.DEBUG) {
                throw new AssertionError("Assertion failed");
            }
            uselessItemViewHolder.textViewTitle = v.findViewById(R.id.useless_item_title);
            uselessItemViewHolder.textViewUpdateTime = v.findViewById(R.id.useless_item_update_time);
            uselessItemViewHolder.textViewSimpleContent = v.findViewById(R.id.useless_item_simple_content);
            uselessItemViewHolder.imageViewTypeImg = v.findViewById(R.id.useless_item_type_img);
            v.setTag(uselessItemViewHolder);
        }else{
            v=convertView;
            uselessItemViewHolder=(UselessItemViewHolder)v.getTag();
        }

        uselessItemViewHolder.textViewTitle.setText(uselessItem.getItemTitle());
        uselessItemViewHolder.textViewUpdateTime.setText(uselessItem.getItemUpdateTime());
        uselessItemViewHolder.textViewSimpleContent.setText(uselessItem.getItemSimpleContent());
        switch(uselessItem.getItemDataTag())
        {
            case isUnknownType:
                uselessItemViewHolder.imageViewTypeImg.setImageResource(R.drawable.ali_ellipsis);
                break;
            case isCustomNode:
                uselessItemViewHolder.imageViewTypeImg.setImageResource(R.drawable.ali_suggest);
                break;
            case isSpecialDate:
                uselessItemViewHolder.imageViewTypeImg.setImageResource(R.drawable.ali_calendar);
                break;
            case isAccountBook:
                uselessItemViewHolder.imageViewTypeImg.setImageResource(R.drawable.ali_consumption);
                break;
            case isEventReminder:
                uselessItemViewHolder.imageViewTypeImg.setImageResource(R.drawable.ali_notification);
                break;
            case isAlarmClock:
                uselessItemViewHolder.imageViewTypeImg.setImageResource(R.drawable.ali_on_time_shipment);
                break;
        }

        return v;
    }

    private static class UselessItemViewHolder {
        TextView textViewTitle;
        TextView textViewUpdateTime;
        TextView textViewSimpleContent;
        ImageView imageViewTypeImg;
    }
}
