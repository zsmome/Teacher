package com.zsmome.day10_mul_layout_listview.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.day10_mul_layout_listview.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */

public class MulLayoutAdapter extends BaseAdapter {
    public static final int TYPE_BOOK = 0;
    public static final int TYPE_SEARCHBOOK = 1;
    private Context mContext;
    private List<Object> mData;

    public MulLayoutAdapter(Context mContext, List<Object> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(mData.get(position) instanceof Book) {
            return TYPE_BOOK;
        }
        return TYPE_SEARCHBOOK;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        BookHolder bookHolder = null;
        SearchBookHolder searchBookHolder = null;
        if(convertView == null) {
            switch (type) {
                case TYPE_BOOK:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.book_list_view, parent, false);
                    bookHolder = new BookHolder(convertView);
                    break;
                case TYPE_SEARCHBOOK:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.search_book_list_view, parent, false);
                    searchBookHolder = new SearchBookHolder(convertView);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_BOOK:
                    bookHolder = (BookHolder) convertView.getTag();
                    break;
                case TYPE_SEARCHBOOK:
                    searchBookHolder = (SearchBookHolder) convertView.getTag();
                    break;
            }
        }
        Object obj = mData.get(position);
        switch (type) {
            case TYPE_BOOK:
                Book book = (Book) obj;
                if(book != null) {
                    bookHolder.bookImg.setImageResource(book.getBookImg());
                    bookHolder.content.setText(book.getDesc());
                }
                break;
            case TYPE_SEARCHBOOK:
                SearchBook searchBook = (SearchBook) obj;
                if(searchBook != null) {
                    searchBookHolder.searchImg.setImageResource(searchBook.getSearchImg());
                    searchBookHolder.content.setText(searchBook.getContent());
                }
                break;
        }
        return convertView;
    }
    //书类
    static class BookHolder {
        private ImageView bookImg;
        private TextView content;
        public BookHolder(View view) {
            bookImg = (ImageView) view.findViewById(R.id.bookIv);
            content = (TextView) view.findViewById(R.id.bookTv);
            view.setTag(this);
        }
    }
    //搜索书
    static class SearchBookHolder {
        private ImageView searchImg;
        private TextView content;
        public SearchBookHolder(View view) {
            searchImg = (ImageView) view.findViewById(R.id.searchIv);
            content = (TextView) view.findViewById(R.id.searchTv);
            view.setTag(this);
        }
    }
}
