package com.zsmome.day12_no4_fragmentpager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsmome.day12_no4_fragmentpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlueFragment extends Fragment {


    public BlueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("blue", "...............");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blue, container, false);
    }

}
