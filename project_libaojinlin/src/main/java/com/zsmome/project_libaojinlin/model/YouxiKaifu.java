package com.zsmome.project_libaojinlin.model;

/**
 * Created by Administrator on 2017/4/26.
 */

/**
 * 开服类
 */
public class YouxiKaifu {
    private String id;
    private String imageUrl;
    private String title;
    private String date;
    private String serverNo;
    private String operator;

    public YouxiKaifu(String id, String imageUrl, String title, String date, String serverNo, String operator) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.date = date;
        this.serverNo = serverNo;
        this.operator = operator;
    }

    public String getId() {
        return id;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getServerNo() {
        return serverNo;
    }

    public String getOperator() {
        return operator;
    }
}
