package com.lifeisfaceemptiness.handlelife.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function 数据库的管理类，创建数据库的表，更新数据查询数据都在这里
 * ，后期可以根据不同的类型数据类型进行分离
 */
public class SQLiteManager extends SQLiteOpenHelper {

    // 数据库的表
    private static final String[] TABLE_NAME = {
            "SPECIAL_DAY", "ACCOUNT_BOOK", "EVENT_REMIND", "ALARM_CLOCK"
    };

    // 当前操作的表
    private static int currentTable = 1;

    public SQLiteManager(Context context) {
        super(context, TABLE_NAME[currentTable], null, 1);
    }

    public int getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(int currentTable) {
        this.currentTable = currentTable;
    }


    // 创建数据库中的表
    private static final String CREATE_SPECIAL_DAY = "CREATE TABLE SPECIAL_DAY(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name varchar(64) NOT NULL," +
            "time varchar(64) NOT NULL)"; // 特殊的日期
    private static final String CREATE_ACCOUNT_BOOK = "CREATE TABLE CREATE_ACCOUNT_BOOK(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "type varchar(64) NOT NULL," +
            "price varchar(64) NOT NULL," +
            "description varchar(64) NOT NULL)"; // 记账
    private static final String CREATE_EVENT_REMIND = "CREATE TABLE CREATE_EVENT_REMIND(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name varchar(64) NOT NULL," +
            "time varchar(64) NOT NULL)"; // 事件提醒
    // todo，闹钟？
    private static final String CREATE_ALARM_CLOCK = "CREATE TABLE CREATE_ALARM_CLOCK(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name varchar(64) NOT NULL," +
            "time varchar(64) NOT NULL)"; // 闹钟


    /**
     * 根据当前数据库的中的 currentTable 执行相应表的创建
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        switch (currentTable) {
            case 1:
                db.execSQL(CREATE_SPECIAL_DAY);
                break;
            case 2:
                db.execSQL(CREATE_ACCOUNT_BOOK);
                break;
            case 3:
                db.execSQL(CREATE_EVENT_REMIND);
                break;
            case 4:
                db.execSQL(CREATE_ALARM_CLOCK);
                break;
            default:
                break;
        }
    }

    /**
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
