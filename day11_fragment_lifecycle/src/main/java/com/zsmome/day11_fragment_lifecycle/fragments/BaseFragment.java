package com.zsmome.day11_fragment_lifecycle.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsmome.day11_fragment_lifecycle.R;

/**
 * Created by Administrator on 2017/4/15.
 */

public class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        return view;
    }
    //1.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    //2.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //4
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    //5
    @Override
    public void onStart() {
        super.onStart();
    }
    //6
    @Override
    public void onResume() {
        super.onResume();
    }
    //7
    @Override
    public void onPause() {
        super.onPause();
    }

    //销毁
    //1
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    //2
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //3
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
