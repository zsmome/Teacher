package com.zsmome.day10_mul_layout_listview_edit.models;

/**
 * Created by Administrator on 2017/4/9.
 */

public class Msg {
    private int imgRes;
    private String content;
    private String time;
    private String who;

    public Msg(int imgRes, String content, String time, String who) {
        this.imgRes = imgRes;
        this.content = content;
        this.time = time;
        this.who = who;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
