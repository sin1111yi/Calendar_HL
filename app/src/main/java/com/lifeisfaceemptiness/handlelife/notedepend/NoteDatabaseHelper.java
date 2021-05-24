package com.lifeisfaceemptiness.handlelife.notedepend;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class NoteDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "NoteDatabaseHelper";
    private Context mContext;

    public static final String table_name = "Note";
    public static final String note_id = "Id";
    public static final String note_title = "Title";
    public static final String note_content = "Content";
    public static final String note_create_time = "Create_Time";
    public static final String note_update_time = "Update_Time";

    public static final String CREATE_BOOK =
            "create table " + table_name + " ("
                    + note_id + " integer primary key autoincrement, "
                    + note_title + " text, "
                    + note_content + " text, "
                    + note_create_time + " text, "
                    + note_update_time + " text)";


    public NoteDatabaseHelper(@Nullable Context context,
                              @Nullable String name,
                              @Nullable SQLiteDatabase.CursorFactory factory,
                              int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    public NoteDatabaseHelper(@Nullable Context context,
                              @Nullable String name,
                              @Nullable SQLiteDatabase.CursorFactory factory,
                              int version,
                              @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public NoteDatabaseHelper(@Nullable Context context,
                              @Nullable String name,
                              int version,
                              @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Log.d(TAG, "Create Note Database!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
