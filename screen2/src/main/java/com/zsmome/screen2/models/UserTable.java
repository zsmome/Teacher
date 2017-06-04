package com.zsmome.screen2.models;

import java.util.Date;

/**
 * 用户表
 * 存储到数据库
 *
 */
public class UserTable {
    private String username;
    private String password;
    //创建日期
    private Long date;
    //提醒剩余次数
    private int hintRemainCount;
    //错过的单词数
    private int loseWordsCount;

    public UserTable() {
    }

    public UserTable(String username, String password, Long date, int hintRemainCount, int loseWordsCount) {
        this.username = username;
        this.password = password;
        this.date = date;
        this.hintRemainCount = hintRemainCount;
        this.loseWordsCount = loseWordsCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public int getHintRemainCount() {
        return hintRemainCount;
    }

    public void setHintRemainCount(int hintRemainCount) {
        this.hintRemainCount = hintRemainCount;
    }

    public int getLoseWordsCount() {
        return loseWordsCount;
    }

    public void setLoseWordsCount(int loseWordsCount) {
        this.loseWordsCount = loseWordsCount;
    }
}
