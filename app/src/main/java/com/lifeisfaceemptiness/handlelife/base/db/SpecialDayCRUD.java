package com.lifeisfaceemptiness.handlelife.base.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lifeisfaceemptiness.handlelife.base.db.model.SpecialDay;
import com.lifeisfaceemptiness.handlelife.base.db.utils.SpecialDayDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function
 */
public class SpecialDayCRUD {
    SQLiteOpenHelper mOpenHelper;

    SQLiteDatabase mSQLiteDatabase;

    public SpecialDayCRUD(Context context) {
        mOpenHelper = new SpecialDayDatabase(context);
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
     * @param specialDay
     * @return
     */
    public SpecialDay insert(SpecialDay specialDay) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", specialDay.getTitle());
        contentValues.put("time", specialDay.getDate());
        long insertId = mSQLiteDatabase.insert("SPECIAL_DAY", null, contentValues);
        specialDay.setId(insertId);
        return specialDay;
    }


    /**
     * @return 查询数据得到的结果
     */
    public List<SpecialDay> queryData() {
        // 1、获得 Cursor 对象
        Cursor cursor = mSQLiteDatabase.query("SPECIAL_DAY", null, null, null, null, null, null);
        List<SpecialDay> list = new ArrayList<>();
        // 2、通过 Cursor 获得数据库中表中所有的数据
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                SpecialDay specialDay = new SpecialDay();
                specialDay.setId(cursor.getColumnIndex("id"));
                specialDay.setTitle(cursor.getString(cursor.getColumnIndex("name")));
                specialDay.setDate(cursor.getString(cursor.getColumnIndex("time")));
                list.add(specialDay);
            }
        }
        return list;
    }

    /**
     * 数据库元素的删除
     */
    public void deleteData(SpecialDay specialDay) {
        mSQLiteDatabase.delete("SPECIAL_DAY", "id=" + specialDay.getId(), null);
    }
}
