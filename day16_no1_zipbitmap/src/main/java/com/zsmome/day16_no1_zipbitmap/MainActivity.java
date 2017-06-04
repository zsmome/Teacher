package com.zsmome.day16_no1_zipbitmap;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zsmome.day16_no1_zipbitmap.utils.LoadImager;

public class MainActivity extends AppCompatActivity {
    //声明组件
    private ImageView mBackgroundIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mBackgroundIv = (ImageView) findViewById(R.id.background_iv);
        String url = "http://pic1.win4000.com/wallpaper/c/587040f33bfc6.jpg";
        new LoadImager().loadImage(url, new LoadImager.OnLoadImage() {
            @Override
            public void loadImageComplete(String url, Bitmap bitmap) {
                mBackgroundIv.setImageBitmap(bitmap);
                Toast.makeText(MainActivity.this, "大小：" + bitmap.getByteCount() / 1024 + "KB", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
