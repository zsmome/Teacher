package com.zsmome.day19_contextmenu.model;

/**
 * Created by Administrator on 2017/5/20.
 */

public class Fruit {
    private int _id;
    private int icon;
    private String content;
    private String date;

    public Fruit() {
    }

    public Fruit(int _id, int icon, String content, String date) {
        this._id = _id;
        this.icon = icon;
        this.content = content;
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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
}
