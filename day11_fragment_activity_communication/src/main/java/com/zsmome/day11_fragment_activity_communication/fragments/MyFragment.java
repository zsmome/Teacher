package com.zsmome.day11_fragment_activity_communication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zsmome.day11_fragment_activity_communication.MainActivity;
import com.zsmome.day11_fragment_activity_communication.R;

/**
 * Created by Administrator on 2017/4/15.
 */

public class MyFragment extends Fragment {
    private Button mBt;
    private static int count = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment, container, false);
        initViews(view);
        return view;
    }

    /**
     * 初始化组件
     */
    private void initViews(View view) {
        mBt = (Button) view.findViewById(R.id.fragment_bt);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得这个Fragment所在的Activity
                MainActivity activity = (MainActivity) getActivity();
                activity.plus();
            }
        });
    }

    /**
     *
     */
    public void plus() {
        count ++;
        mBt.setText("Fragment -- "+count);
    }
}
