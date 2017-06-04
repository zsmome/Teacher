package com.zsmome.day19_contextmenu;

import android.app.Application;
import android.content.res.TypedArray;

import com.zsmome.day19_contextmenu.model.Fruit;
import com.zsmome.day19_contextmenu.sqlite.FruitDAOImpl;
import com.zsmome.day19_contextmenu.sqlite.MySqliteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        MySqliteOpenHelper helper = MySqliteOpenHelper.getInstance(this);
        helper.getWritableDatabase();
        FruitDAOImpl fruitDAO = new FruitDAOImpl(this);
        if(!fruitDAO.isExists()) {
            for(Fruit fruit : getFruitList()) {
                fruitDAO.insert(fruit);
            }
        }
        super.onCreate();
    }
    /**
     * 获取水果类集合
     * @return
     */
    private List<Fruit> getFruitList() {
        /*集合*/
        List<Fruit> list = new ArrayList<>();
        TypedArray iconArr = getResources().obtainTypedArray(R.array.array_fruit_icon);
        int len = iconArr.length();
        for(int i=0; i<len; i++) {
            int iconId = iconArr.getResourceId(i, 0);
            String content = "水果"+i;
            Date time = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd hh:mm");
            String date = sdf.format(time);
            list.add(new Fruit(-1, iconId, content, date));
        }
        return  list;
    }
}
