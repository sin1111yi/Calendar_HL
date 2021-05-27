package com.lifeisfaceemptiness.handlelife.base.db.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function 特殊日期的数据操作，提供 CRUD
 */
public class SpecialDayDatabase extends SQLiteOpenHelper {


    // 创建数据库中的表
    public static final String CREATE_SPECIAL_DAY = "CREATE TABLE " + "SPECIAL_DAY"
            + "("
            + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name" + " TEXT NOT NULL,"
            + "time" + " TEXT NOT NULL)"; // 特殊的日期

    public SpecialDayDatabase(Context context) {
        super(context, CREATE_SPECIAL_DAY, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SPECIAL_DAY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
