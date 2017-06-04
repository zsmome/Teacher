package com.zsmome.screen2.sqlite;

import com.zsmome.screen2.models.UserTable;

/**
 * Created by Administrator on 2017/5/15.
 */

public interface UserDAO {
    /**
     * 插入记录
     *
     * @param userTable
     */
    void insert(UserTable userTable);

    /**
     * 获取用户表数据
     */
    UserTable getUser(String username);

    /**
     * 更新谁的提醒次数
     */
    void updateHintRemainCount(String username, int count);
    /**
     * 更新错过的单词数
     */
    void updateLoseWordsCount(String username);
}
