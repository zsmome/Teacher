package com.zsmome.screen2;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zsmome.screen2.adapters.VPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    //声明控件
    private ViewPager mWelcomeVp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        bindViews();
    }

    /**
     * 绑定控件
     */
    private void bindViews() {
        mWelcomeVp = (ViewPager) findViewById(R.id.welcome_vp);
        //资源数组
        int[] layoutId = {R.layout.welcome_1, R.layout.welcome_end};
        List<View> list = new ArrayList<>();
        for(int i=0; i<layoutId.length; i++) {
            View view = LayoutInflater.from(this).inflate(layoutId[i], null);
            list.add(view);
        }
        //进入应用
        View view = list.get(layoutId.length-1);
        ((Button) view.findViewById(R.id.en_app_bt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        VPagerAdapter vpAda = new VPagerAdapter(list);
        mWelcomeVp.setAdapter(vpAda);
    }
}
