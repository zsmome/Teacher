package com.zsmome.day14_group_listview.models;

/**
 * 水果类
 * Created by Administrator on 2017/4/23.
 */

public class Fruit {
    private int image;
    private String desc;

    public Fruit(int image, String desc) {
        this.image = image;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
