package com.lifeisfaceemptiness.handlelife.overview.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lifeisfaceemptiness.handlelife.R;
import com.lifeisfaceemptiness.handlelife.base.db.model.SpecialDay;

import java.util.List;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function
 */
public class SpecialDayAdapter extends BaseAdapter {

    private List<SpecialDay> mSpecialDayList;
    private Context mContext;
    private TextView mSdName;
    private TextView mSdTime;

    public SpecialDayAdapter(List<SpecialDay> specialDayList, Context context) {
        mSpecialDayList = specialDayList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mSpecialDayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSpecialDayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * TODO:没有实现 ListView 的优化
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.fragment_special_day, null);
        mSdName = view.findViewById(R.id.tv_sd_name);
        mSdTime = view.findViewById(R.id.tv_sp_time);
        mSdName.setText(mSpecialDayList.get(position).getTitle());
        mSdTime.setText(mSpecialDayList.get(position).getDate());
        view.setTag(mSpecialDayList.get(position).getId());
        return view;
    }
}
