package com.zsmome.day09_homework_news_simpleadapter.model;

/**
 * 每项新闻的数据
 * Created by Administrator on 2017/4/8.
 */

public class NewsItem {
    private int image;
    private String title;
    private String content;

    public NewsItem(int image, String title, String content) {
        this.image = image;
        this.title = title;
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
