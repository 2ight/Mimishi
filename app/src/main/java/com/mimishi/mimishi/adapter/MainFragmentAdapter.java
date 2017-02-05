package com.mimishi.mimishi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.base.BaseAdapter;
import com.mimishi.mimishi.base.BaseViewHolder;
import com.mimishi.mimishi.model.ResourceMain;

/**
 * Created by chen on 17-2-5.
 */
public class MainFragmentAdapter extends BaseAdapter<ResourceMain>{

    public MainFragmentAdapter(Context context) {
        super(context);
        //在这里要测试执行的顺序是否正确
        hideFooter();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new NormalViewHolder(inflater.inflate(R.layout.item_maim, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        NormalViewHolder viewHolder = (NormalViewHolder) holder;
        viewHolder.tvItemName.setText(mDataSet.get(position).name);
        
    }

    class NormalViewHolder extends BaseViewHolder{
        private TextView tvItemName;
        public NormalViewHolder(View itemView) {
            super(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
        }
    }


}
