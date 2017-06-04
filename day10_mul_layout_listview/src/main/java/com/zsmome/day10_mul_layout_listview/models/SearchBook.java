package com.zsmome.day10_mul_layout_listview.models;

/**
 * Created by Administrator on 2017/4/9.
 */

public class SearchBook {
    private int searchImg;
    private String content;

    public SearchBook(int searchImg, String content) {
        this.searchImg = searchImg;
        this.content = content;
    }

    public int getSearchImg() {
        return searchImg;
    }

    public void setSearchImg(int searchImg) {
        this.searchImg = searchImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
