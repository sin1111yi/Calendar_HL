package com.lifeisfaceemptiness.handlelife.overview.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lifeisfaceemptiness.handlelife.R;
import com.lifeisfaceemptiness.handlelife.base.db.model.EventRemind;

import java.util.List;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function
 */
public class EventRemindAdapter extends BaseAdapter {

    private List<EventRemind> mEventRemindList;
    private Context mContext;
    private TextView mSdName;
    private TextView mSdTime;

    public EventRemindAdapter(List<EventRemind> specialDayList, Context context) {
        mEventRemindList = specialDayList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mEventRemindList.size();
    }

    @Override
    public Object getItem(int position) {
        return mEventRemindList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * TODO:没有实现 ListView 的优化
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.fragment_event_remind, null);
        mSdName = view.findViewById(R.id.tv_event_remind_name);
        mSdTime = view.findViewById(R.id.tv_event_remind_time);
        mSdName.setText(mEventRemindList.get(position).getName());
        mSdTime.setText(mEventRemindList.get(position).getTime());
        view.setTag(mEventRemindList.get(position).getId());
        return view;
    }
}
