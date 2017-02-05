package com.mimishi.mimishi.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 17-2-5.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected final int NORMAL_ITEM = 1;
    protected final int FOOTER_ITEM = 0;

    private Context mContext;
    private List<T> mDataSet = new ArrayList<>();
    private boolean isShowFooter = true;


    public BaseAdapter(Context context){
        mContext = context;
    }

    public void clearItems(){
        mDataSet.clear();
    }

    public void refreshItems(List<T>list){
        clearItems();
        mDataSet.addAll(list);
        notifyDataSetChanged();
    }

    public void addItems(List<T> list){
        mDataSet.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 >= getItemCount() && isShowFooter) {
            return FOOTER_ITEM;
        }
        return NORMAL_ITEM;
    }
    @Override
    public int getItemCount() {
        if (isShowFooter && mDataSet.size() != 0){
            return mDataSet.size() + 1;
        }
        return mDataSet.size();
    }

    public void showFooter(){
        isShowFooter = true;
    }
    public void hideShowFooter(){
        isShowFooter = false;
    }


}