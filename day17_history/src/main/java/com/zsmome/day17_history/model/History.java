package com.zsmome.day17_history.model;

/**
 * 历史记录类
 */
public class History {
    private String content;
    private String date;
    private int count;

    public History() {
    }

    public History(String content, String date, int count) {
        this.content = content;
        this.date = date;
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
