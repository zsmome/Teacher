package com.zsmome.screen2.models;

/**
 * 单词基本类
 * Created by Administrator on 2017/5/14.
 */

public class WordsTable {
    private String word;
    private String trans;
    private String phonetic;

    public WordsTable() {
    }

    public WordsTable(String word, String trans, String phonetic) {
        this.word = word;
        this.trans = trans;
        this.phonetic = phonetic;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    @Override
    public String toString() {
        return "WordsTable{" +
                "word='" + word + '\'' +
                ", trans='" + trans + '\'' +
                ", phonetic='" + phonetic + '\'' +
                '}';
    }
}
