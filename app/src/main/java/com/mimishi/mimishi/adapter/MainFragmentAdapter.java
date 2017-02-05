package com.mimishi.mimishi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mimishi.mimishi.base.BaseAdapter;

/**
 * Created by chen on 17-2-5.
 */
public class MainFragmentAdapter extends BaseAdapter<>{

    public MainFragmentAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
