package com.lifeisfaceemptiness.handlelife.base.db.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function 事件提醒的数据操作，提供 CRUD
 */
public class EventRemindDatabase extends SQLiteOpenHelper {


    // 创建数据库中的表
    public static final String CREATE_EVENT_REMIND = "CREATE TABLE " + "EVENT_REMIND"
            + "("
            + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name" + " TEXT NOT NULL,"
            + "time" + " TEXT NOT NULL)"; // 特殊的日期

    public EventRemindDatabase(Context context) {
        super(context, CREATE_EVENT_REMIND, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENT_REMIND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
