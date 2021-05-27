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
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.lifeisfaceemptiness.handlelife.R;
import com.lifeisfaceemptiness.handlelife.base.db.EventRemindCRUD;
import com.lifeisfaceemptiness.handlelife.base.db.model.EventRemind;
import com.lifeisfaceemptiness.handlelife.base.db.utils.EventRemindDatabase;
import com.lifeisfaceemptiness.handlelife.create.message.EventRemindMessage;
import com.lifeisfaceemptiness.handlelife.overview.adpter.EventRemindAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class EventReminderFragment extends Fragment implements AdapterView.OnItemClickListener {

    View rootView;
    private ListView mListView;
    private Button mButton;
    private List<EventRemind> mEventRemindList = new ArrayList<>();
    private Context mContext;
    private static final String TAG = "EventReminderFragment";
    private EventRemindAdapter mAdapter;


    public EventReminderFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_er_show, container, false);
        }
        EventBus.getDefault().register(this);
        mListView = rootView.findViewById(R.id.lv_event_remind);

        mButton = rootView.findViewById(R.id.btn_delete_er);
        mAdapter = new EventRemindAdapter(mEventRemindList,mContext);
        refreshListView();
        mListView.setAdapter(mAdapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventRemindDatabase eventRemindDatabase = new EventRemindDatabase(mContext);
                SQLiteDatabase sqLiteDatabase = eventRemindDatabase.getWritableDatabase();
                sqLiteDatabase.delete("EVENT_REMIND", null, null);
                sqLiteDatabase.execSQL("update sqlite_sequence set seq=0 where name='EVENT_REMIND'");
                refreshListView();
            }
        });

        return rootView;
    }

    /**
     * 监听得到的响应在这里处理，进行数据的存储
     *
     * @param message 事件
     */
    @Subscribe
    public void onEvent(EventRemindMessage message) {
        // 合法性校验，如果选择日期中有一个数据为空，就不会进行数据插入
        Log.d(TAG, "EventReminderFragment");
        Log.d(TAG, message.toString());
        if (message != null && (message.getEventRemind().getName().length() != 0 &&
                message.getEventRemind().getTime().length() != 0)) {
            EventRemind eventRemind = message.getEventRemind();
            EventRemindCRUD crud = new EventRemindCRUD(mContext);
            crud.open();
            crud.insert(eventRemind);
            crud.close();
            refreshListView();
        }
    }

    public void refreshListView() {
        EventRemindCRUD crud = new EventRemindCRUD(mContext);
        crud.open();
        // 如果存在数据，首先应该清除，避免重复
        if (mEventRemindList.size() > 0) {
            mEventRemindList.clear();
        }
        // 添加所有的数据
        mEventRemindList.addAll(crud.queryData());
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