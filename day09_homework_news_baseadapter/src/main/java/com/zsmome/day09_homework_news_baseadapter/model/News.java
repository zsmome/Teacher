package com.zsmome.day09_homework_news_baseadapter.model;

/**
 * 每一项的新闻
 * Created by Administrator on 2017/4/8.
 */

public class News {
    private String imageUrl;
    private String title;
    private String content;

    public News(String imageUrl, String title, String content) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
