package com.zsmome.day19_contextmenu.sqlite;

import com.zsmome.day19_contextmenu.model.Fruit;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public interface FruitDAO {
    /**
     * 插入水果
     */
    void insert(Fruit fruit);
    /*删除水果*/
    void delete(String content);
    /**
     * 获取水果信息
     */
    List<Fruit> getFruitList();
    /**
     * 判断表是否存在
     */
    boolean isExists();
    /**
     * 根据id获取水果信息
     */
    Fruit getFruit(int id);
}
