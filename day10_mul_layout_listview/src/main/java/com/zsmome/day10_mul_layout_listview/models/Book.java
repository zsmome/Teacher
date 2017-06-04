package com.zsmome.day10_mul_layout_listview.models;

/**
 * Created by Administrator on 2017/4/9.
 */

public class Book {
    private int bookImg;
    private String desc;

    public Book(int bookImg, String desc) {
        this.bookImg = bookImg;
        this.desc = desc;
    }

    public int getBookImg() {
        return bookImg;
    }

    public void setBookImg(int bookImg) {
        this.bookImg = bookImg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
