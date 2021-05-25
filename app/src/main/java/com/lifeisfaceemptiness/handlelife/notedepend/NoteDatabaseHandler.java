package com.lifeisfaceemptiness.handlelife.notedepend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.lifeisfaceemptiness.handlelife.note.Note;
import com.lifeisfaceemptiness.handlelife.note.NoteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NoteDatabaseHandler {
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase db;

    private static final String[] columns =
            {
                    NoteDatabaseHelper.note_id,
                    NoteDatabaseHelper.note_title,
                    NoteDatabaseHelper.note_content,
                    NoteDatabaseHelper.note_create_time,
                    NoteDatabaseHelper.note_update_time
            };

    public NoteDatabaseHandler(Context context) {
        dbHelper = new NoteDatabaseHelper(context, "NoteItems.db", null, 1);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addNoteItem(NoteItem item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDatabaseHelper.note_title, item.getNoteTitle());
        contentValues.put(NoteDatabaseHelper.note_content, item.getNoteContent());
        contentValues.put(NoteDatabaseHelper.note_create_time, item.getNoteCreateTime());
        contentValues.put(NoteDatabaseHelper.note_update_time, item.getNoteUpdateTime());
        long insertId = db.insert(NoteDatabaseHelper.table_name, null, contentValues);
        item.setNoteId(insertId);
    }

    public NoteItem getNoteItemById(long id) {
        NoteItem item;
        Cursor cursor = db.query(NoteDatabaseHelper.table_name, columns,
                NoteDatabaseHelper.note_id + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (cursor != null) {
            item = new NoteItem(Integer.parseInt(cursor.getString(1)), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
        } else {
            //希望通过这一步骤在cursor为null时返回一个空的item
            item = new NoteItem(0x3f3f3f3f, "null", "null", "null", "null");
        }
        Objects.requireNonNull(cursor).close();
        return item;
    }

    public List<NoteItem> getAllNotes() {
        Cursor cursor = db.query(NoteDatabaseHelper.table_name, columns,
                null, null, null, null, null);

        List<NoteItem> items = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                NoteItem item = new NoteItem();
                item.setNoteId(cursor.getLong(cursor.getColumnIndex(NoteDatabaseHelper.note_id)));
                item.setNoteTitle(cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.note_title)));
                item.setNoteContent(cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.note_content)));
                item.setNoteCreateTime(cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.note_create_time)));
                item.setNoteUpdateTime(cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.note_update_time)));
                items.add(item);
            }
        }
        cursor.close();
        return items;
    }

    public int updateNoteItem(NoteItem item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDatabaseHelper.note_id, item.getNoteId());
        contentValues.put(NoteDatabaseHelper.note_title, item.getNoteTitle());
        contentValues.put(NoteDatabaseHelper.note_content, item.getNoteContent());
        contentValues.put(NoteDatabaseHelper.note_create_time, item.getNoteCreateTime());
        contentValues.put(NoteDatabaseHelper.note_update_time, item.getNoteUpdateTime());
        return db.update(NoteDatabaseHelper.table_name, contentValues,
                NoteDatabaseHelper.note_id + "=?", new String[]{String.valueOf(item.getNoteId())});
    }

    public void removeNoteItem(NoteItem item) {
        db.delete(NoteDatabaseHelper.table_name, NoteDatabaseHelper.note_id + "=" + item.getNoteId(), null);
    }

}
