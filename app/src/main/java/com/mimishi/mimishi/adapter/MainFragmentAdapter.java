package com.mimishi.mimishi.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.base.BaseAdapter;
import com.mimishi.mimishi.base.BaseViewHolder;
import com.mimishi.mimishi.model.ResourcesMain;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by chen on 17-2-5.
 */
public class MainFragmentAdapter extends BaseAdapter<ResourcesMain.ItemList>{

    public static final String INTENT_URL = "intent_url";

    public MainFragmentAdapter(Context context) {
        super(context);
        hideFooter();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new NormalViewHolder(inflater.inflate(R.layout.item_maim, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        NormalViewHolder viewHolder = (NormalViewHolder) holder;
//        viewHolder.tvItemName.setText(mDataSet.get(position).item_name);
        /*viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(INTENT_URL, mDataSet.get(position).item_url);
                mContext.startActivity(intent);
            }
        });*/

        viewHolder.jcVideoPlayer.setUp("http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f22794e792.f20.mp4",
                "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640",
                "一行代码实现视频播放");
        Log.i("item is succeed", mDataSet.get(position).item_name);


    }

    private class NormalViewHolder extends BaseViewHolder{
        private TextView tvItemName;
        private CardView cardView;
        private JCVideoPlayer jcVideoPlayer;
        NormalViewHolder(View itemView) {
            super(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            cardView = (CardView) itemView.findViewById(R.id.item_card_view);
            jcVideoPlayer = (JCVideoPlayer) itemView.findViewById(R.id.video_controller);
        }
    }


}
