package com.zsmome.day17_history.sqlite;

import com.zsmome.day17_history.model.History;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public interface HistoryDAO {
    /**
     * 插入数据
     */
    void insert(History  history);
    /**
     * 搜索内容
     */
    List<History> searchContent(String content);
    /**
     * 判断内容存不存
     */
    boolean exists(String content);
    /**
     * 更新数据
     */
    void update(String content);
    /**
     * 删除数据
     */
    void delete(String content);
}
