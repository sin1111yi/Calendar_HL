package com.lifeisfaceemptiness.handlelife.note;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function 笔记消息传递的数据实体
 */
public class NoteMessage {

    public NoteMessage(String content, int isSave) {
        this.content = content;
        this.isSave = isSave;
    }

    private String content;
    private int isSave;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsSave() {
        return isSave;
    }

    public void setIsSave(int isSave) {
        this.isSave = isSave;
    }

    @Override
    public String toString() {
        return "NoteMessage{" +
                "content='" + content + '\'' +
                ", isSave=" + isSave +
                '}';
    }
}
