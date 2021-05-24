package com.lifeisfaceemptiness.handlelife.notedepend;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteItem {

    private long noteId;
    private String noteTitle;
    private String noteContent;
    private String noteCreateTime;
    private String noteUpdateTime;
    private String defaultTitle;

    public NoteItem() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.noteCreateTime = format.format(new Date());
    }

    public NoteItem(String title) {
        this.noteTitle = title;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.noteCreateTime = format.format(new Date());
        this.noteUpdateTime = this.noteCreateTime;
    }

    public NoteItem(long id, String title, String content, String ctime, String utime) {
        this.noteId=id;
        this.noteTitle=title;
        this.noteContent=content;
        this.noteCreateTime=ctime;
        this.noteUpdateTime=utime;
    }

    public void setNoteId(long id) {
        this.noteId = id;
    }

    public long getNoteId() {
        return this.noteId;
    }

    public void setNoteTitle(String title) {
        this.noteTitle = title;
    }

    public String getNoteTitle() {
        return this.noteTitle;
    }

    public void setNoteContent(String content) {
        this.noteContent = content;
    }

    public String getNoteContent() {
        return this.noteContent;
    }

    public void setNoteCreateTime(String createTime) {
        this.noteCreateTime = createTime;
    }

    public String getNoteCreateTime() {
        return this.noteCreateTime;
    }

    public void setNoteUpdateTime(String updateTime) {
        this.noteUpdateTime = updateTime;
    }

    public String getNoteUpdateTime() {
        return this.noteUpdateTime;
    }

    private void setDefaultTitle() {
        this.defaultTitle = this.noteContent.substring(0, 10);
    }

}
