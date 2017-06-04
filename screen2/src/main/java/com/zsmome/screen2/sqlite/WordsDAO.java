package com.zsmome.screen2.sqlite;

import com.zsmome.screen2.models.WordsTable;

/**
 * words_tb表数据操作接口
 * Created by xray on 17/5/14.
 */

public interface WordsDAO {

    /**
     * 插入记录
     * @param wordsTable
     */
    void insert(WordsTable wordsTable);

    /**
     * 表中有没有数据
     * @param table_name
     * @return
     */
    boolean tableRowCount(String table_name);
    /**
     * 随机获取一个单词
     */
    WordsTable getWords();
}
