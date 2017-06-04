package com.zsmome.day19_contextmenu.sqlite;

import com.zsmome.day19_contextmenu.model.Collect;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */

public interface CollectDAO {
    /**
     * 插入收藏
     */
    void insert(Collect collect);
    /**
     * 删除收藏
     */
    void delete(Collect collect);
    /**
     * 获取收藏集合
     */
    List<Collect> getCollectList();
}
