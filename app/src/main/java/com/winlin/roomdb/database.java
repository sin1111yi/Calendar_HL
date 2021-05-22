package com.winlin.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//数据库，关联
@Database(entities = {entity.class},version = 1, exportSchema = false)
public abstract class database extends RoomDatabase {


    //用户只需要操作dao，就可以对表进行增删改查，必须暴露dao
    public abstract dao getdao();
    //单例模式
    private static database INSTANCE;

    public static synchronized database getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder
                    (context.getApplicationContext(),
                            database.class, "room_base").build();
        }
        return INSTANCE;

    }
}
