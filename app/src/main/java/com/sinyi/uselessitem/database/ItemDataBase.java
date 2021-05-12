package com.sinyi.uselessitem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDataBase extends SQLiteOpenHelper {

    private static final String ITEM_ID = "id";
    private static final String ITEM_TABLE_NAME = "items";
    private static final String ITEM_CONTENT = "content";
    private static final String ITEM_CREATE_TIME = "create_time";
    private static final String ITEM_UPDATE_TIME = "update_time";
    private static final String ITEM_DATA_TYPE = "data_type";

    public ItemDataBase(Context context) {
        super(context, ITEM_TABLE_NAME, null, 1);
    }

    private static final String CREATE_USELESS_DATABASE =
            "create table UselessDatabase (" +
                    ITEM_ID + " integer primary key autoincrement, " +
                    ITEM_TABLE_NAME + " text, " +
                    ITEM_CONTENT + " text, " +
                    ITEM_CREATE_TIME + " text, " +
                    ITEM_UPDATE_TIME + " text, " +
                    ITEM_DATA_TYPE + " integer default 0 )";

    @Override
    //TODO: complete the database

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_USELESS_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}