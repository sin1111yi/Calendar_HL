package com.lifeisfaceemptiness.handlelife.base.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lifeisfaceemptiness.handlelife.base.db.model.EventRemind;
import com.lifeisfaceemptiness.handlelife.base.db.utils.EventRemindDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function
 */
public class EventRemindCRUD {
    SQLiteOpenHelper mOpenHelper;

    SQLiteDatabase mSQLiteDatabase;

    public EventRemindCRUD(Context context) {
        mOpenHelper = new EventRemindDatabase(context);
    }

    public void open() {
        mSQLiteDatabase = mOpenHelper.getWritableDatabase();
    }

    public void close() {
        mOpenHelper.close();
    }

    /**
     * 插入
     *
     * @param eventRemind
     * @return
     */
    public EventRemind insert(EventRemind eventRemind) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", eventRemind.getName());
        contentValues.put("time", eventRemind.getTime());
        long insertId = mSQLiteDatabase.insert("EVENT_REMIND", null, contentValues);
        eventRemind.setId(insertId);
        return eventRemind;
    }


    /**
     * @return 查询数据得到的结果
     */
    public List<EventRemind> queryData() {
        // 1、获得 Cursor 对象
        Cursor cursor = mSQLiteDatabase.query("EVENT_REMIND", null, null, null, null, null, null);
        List<EventRemind> list = new ArrayList<>();
        // 2、通过 Cursor 获得数据库中表中所有的数据
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                EventRemind eventRemind = new EventRemind();
                eventRemind.setId(cursor.getColumnIndex("id"));
                eventRemind.setName(cursor.getString(cursor.getColumnIndex("name")));
                eventRemind.setTime(cursor.getString(cursor.getColumnIndex("time")));
                list.add(eventRemind);
            }
        }
        return list;
    }

    /**
     * 数据库元素的删除
     */
    public void deleteData(EventRemind eventRemind) {
        mSQLiteDatabase.delete("EVENT_REMIND", "id=" + eventRemind.getId(), null);
    }
}
