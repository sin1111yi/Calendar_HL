package com.sinyi.uselessitem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDataBase extends SQLiteOpenHelper {

    public static final String ITEM_TABLE_NAME = "items";
    public static final String ITEM_CONTENT = "content";
    public static final String ITEM_ID = "id";
    public static final String ITEM_CREATE_TIME = "create time";
    public static final String ITEM_UPDATE_TIME = "update time";
    public static final String ITEM_DATA_TYPE = "data type";

    public ItemDataBase(Context context) {
        super(context, ITEM_TABLE_NAME, null, 1);
    }

    @Override
    //TODO: complete the database
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + ITEM_TABLE_NAME
                + "("
                + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ITEM_CONTENT + " TEXT NOT NULL, "
                + ITEM_CREATE_TIME + " TEXT NOT NULL,"
                + ITEM_UPDATE_TIME + " TEXT NOT　NULL,"
                + ITEM_DATA_TYPE + " INTEGER DEFAULT 1" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}