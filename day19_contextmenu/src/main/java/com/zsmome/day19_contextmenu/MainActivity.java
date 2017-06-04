package com.zsmome.day19_contextmenu;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zsmome.day19_contextmenu.adapters.CommonAdapter;
import com.zsmome.day19_contextmenu.adapters.ViewHolder;
import com.zsmome.day19_contextmenu.model.Collect;
import com.zsmome.day19_contextmenu.model.Fruit;
import com.zsmome.day19_contextmenu.sqlite.CollectDAOImpl;
import com.zsmome.day19_contextmenu.sqlite.FruitDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*声明控件*/
    private ListView mFruitLv;
    /*图标资源*/
    private List<Fruit> mFruitList;

    private CommonAdapter<Fruit> mFruitCommonAdapter;
    //水果数据库操作类
    private FruitDAOImpl mFruitDAO = new FruitDAOImpl(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        initListView();
        registerForContextMenu(mFruitLv);
    }

    /**
     * 初始化控件
     */
    private void initListView() {
        /*获取数据库数据*/
        mFruitList = mFruitDAO.getFruitList();
        /*初始化通用适配器*/
        mFruitCommonAdapter = new CommonAdapter<Fruit>(this, mFruitList, R.layout.list_view_fruit) {
            @Override
            public void setViews(ViewHolder viewHolder, Fruit data) {
                viewHolder.setImageViewResource(R.id.fruit_icon_iv, data.getIcon());
                viewHolder.setTextViewText(R.id.fruit_content_tv, data.getContent());
                viewHolder.setTextViewText(R.id.fruit_date_tv, data.getDate());
            }
        };
        /*设置适配器*/
        mFruitLv.setAdapter(mFruitCommonAdapter);
    }

    /**
     * 绑定控件
     */
    private void bindViews() {
        mFruitLv = (ListView) findViewById(R.id.fruit_lv);
    }
    /**
     * 菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_body, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.collect:
                ListView collectLv = new ListView(this);
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setView(collectLv)
                        .create();
                alertDialog
                        .show();
                alertDialog.getWindow().setLayout(AbsListView.LayoutParams.MATCH_PARENT, 600);
                List<Collect> collectList = new CollectDAOImpl(this).getCollectList();
                /*收藏的集合*/
                List<Fruit> fruitList = new ArrayList<>();
                for(Collect collect : collectList) {
                    fruitList.add(mFruitDAO.getFruit(collect.get_id()));
                }
                CommonAdapter<Fruit> ada = new CommonAdapter<Fruit>(this, fruitList, R.layout.list_view_fruit) {
                    @Override
                    public void setViews(ViewHolder viewHolder, Fruit data) {
                        viewHolder.setImageViewResource(R.id.fruit_icon_iv, data.getIcon());
                        viewHolder.setTextViewText(R.id.fruit_content_tv, data.getContent());
                        viewHolder.setTextViewText(R.id.fruit_date_tv, data.getDate());
                    }
                };
                collectLv.setAdapter(ada);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 上下文菜单
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        new MenuInflater(this).inflate(R.menu.menu_list_view_fruit, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        /*获取索引*/
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = menuInfo.position;
        final Fruit fruit = mFruitList.get(position);
        switch (item.getItemId()) {
            case R.id.collect:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                /*增加收藏*/
                new CollectDAOImpl(this).insert(new Collect(fruit.get_id()));
                break;
            case R.id.delete:
                /*确认是否删除*/
                new AlertDialog.Builder(this)
                        .setTitle("确认删除？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteData(position, fruit);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create()
                        .show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * 删除数据
     * @param position
     */
    private void deleteData(int position, Fruit fruit) {
        //删除数据库数据
        mFruitDAO.delete(fruit.getContent());
        /*删除收藏中的*/
        new CollectDAOImpl(this).delete(new Collect(fruit.get_id()));
        //删除内存数据
        mFruitList.remove(position);
        mFruitCommonAdapter.refresh(mFruitList);
    }
}
