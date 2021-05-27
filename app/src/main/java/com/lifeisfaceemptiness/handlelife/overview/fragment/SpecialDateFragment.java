package com.lifeisfaceemptiness.handlelife.overview.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.lifeisfaceemptiness.handlelife.R;
import com.lifeisfaceemptiness.handlelife.base.db.SpecialDayCRUD;
import com.lifeisfaceemptiness.handlelife.base.db.model.SpecialDay;
import com.lifeisfaceemptiness.handlelife.base.db.utils.SpecialDayDatabase;
import com.lifeisfaceemptiness.handlelife.create.message.SpecialDayMessage;
import com.lifeisfaceemptiness.handlelife.overview.adpter.SpecialDayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

//通过这个Fragment中的CalendarView获取到日期后，如果数据库中有SpecialDate，则显示距离最近的SpecialDate剩余时间
public class SpecialDateFragment extends Fragment implements AdapterView.OnItemClickListener {

    View rootView;

    Context mContext;
    CalendarView calendarView;
    private ListView mListView;
    private List<SpecialDay> mSpecialDayList = new ArrayList<>();
    private SpecialDayAdapter mAdapter;
    private static final String TAG = "SpecialDateFragment";
    private Button mButton;

    //TODO: 现在这个Fragment中CalendarView被选中的日期背景色为黑色，应该为深灰色
    public SpecialDateFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mContext = getActivity();
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_sd_show, container, false);
        }
        EventBus.getDefault().register(this);
        calendarView = rootView.findViewById(R.id.sd_calendar);
        mListView = rootView.findViewById(R.id.lv_special_day);
        // 初始化适配器
        mAdapter = new SpecialDayAdapter(mSpecialDayList, mContext);
        refreshListView();
        // 设置适配器
        mListView.setAdapter(mAdapter);

        mButton = rootView.findViewById(R.id.btn_delete_sd);
        // 删除
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpecialDayDatabase specialDayDatabase = new SpecialDayDatabase(mContext);
                SQLiteDatabase sqLiteDatabase = specialDayDatabase.getWritableDatabase();
                sqLiteDatabase.delete("SPECIAL_DAY", null, null);
                sqLiteDatabase.execSQL("update sqlite_sequence set seq=0 where name='SPECIAL_DAY'");
                refreshListView();
            }
        });
        // 单个 item 的点击事件
        mListView.setOnItemClickListener(this);
        return rootView;
    }


    /**
     * 监听得到的响应在这里处理，进行数据的存储
     *
     * @param message 事件
     */
    @Subscribe
    public void onEvent(SpecialDayMessage message) {
        // 合法性校验，如果选择日期中有一个数据为空，就不会进行数据插入
        Log.d(TAG, "SpecialDateFragment");
        Log.d(TAG, message.toString());
        if (message != null && (message.getSpecialDay().getDate().length() != 0 &&
                message.getSpecialDay().getTitle().length() != 0)) {
            SpecialDay specialDay = message.getSpecialDay();
            SpecialDayCRUD crud = new SpecialDayCRUD(mContext);
            crud.open();
            crud.insert(specialDay);
            crud.close();
            refreshListView();
        }
    }

    public void refreshListView() {
        SpecialDayCRUD crud = new SpecialDayCRUD(mContext);
        crud.open();
        // 如果存在数据，首先应该清除，避免重复
        if (mSpecialDayList.size() > 0) {
            mSpecialDayList.clear();
        }
        // 添加所有的数据
        mSpecialDayList.addAll(crud.queryData());
        crud.close();
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //todo：携带当前item的数据到编辑界面
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}