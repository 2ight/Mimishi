package com.mimishi.mimishi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.adapter.MainFragmentAdapter;
import com.mimishi.mimishi.base.BaseFragment;

/**
 * Created by chen on 17-2-5.
 */

public class MainFragment extends BaseFragment{

    private RecyclerView mRecyclerView;
    private MainFragmentAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mRecyclerView.setAdapter(mAdapter);

    }
}
