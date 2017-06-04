package com.zsmome.day10_mul_layout_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.zsmome.day10_mul_layout_listview.models.Book;
import com.zsmome.day10_mul_layout_listview.models.MulLayoutAdapter;
import com.zsmome.day10_mul_layout_listview.models.SearchBook;

import java.util.ArrayList;
import java.util.List;

import static com.zsmome.day10_mul_layout_listview.models.MulLayoutAdapter.TYPE_BOOK;

public class MainActivity extends AppCompatActivity {
    private ListView mBookLv;
    private List<Object> mData;
    private MulLayoutAdapter myAdapter;
    public static final int TYPE_BOOK = 0;
    public static final int TYPE_SEARCHBOOK = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
    }

    private void initData() {
        //数据准备：
        mData = new ArrayList<Object>();
        for(int i = 0;i < 20;i++){
            switch ((int)(Math.random() * 2)){
                case TYPE_BOOK:
                    mData.add(new Book(R.mipmap.a1,"郭霖"));
                    break;
                case TYPE_SEARCHBOOK:
                    mData.add(new SearchBook(R.mipmap.a2,"百度"));
                    break;
            }
        }
    }

    private void initViews() {
        mBookLv = (ListView) findViewById(R.id.bookLv);

        myAdapter = new MulLayoutAdapter(MainActivity.this,mData);
        mBookLv.setAdapter(myAdapter);
    }
}
