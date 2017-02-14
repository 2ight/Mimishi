package com.mimishi.mimishi.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.base.BaseAdapter;
import com.mimishi.mimishi.base.BaseViewHolder;
import com.mimishi.mimishi.model.ResourcesVideo;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by chen on 17-2-5.
 */
public class MainFragmentAdapter extends BaseAdapter<ResourcesVideo.ItemList>{

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

        viewHolder.jcVideoPlayer.setUp(mDataSet.get(position).item_video, mDataSet.get(position).item_thumbnail, mDataSet.get(position).item_title);
        Log.i("item is succeed", mDataSet.get(position).item_title);

    }

    private class NormalViewHolder extends BaseViewHolder{
        private CardView cardView;
        private JCVideoPlayer jcVideoPlayer;
        NormalViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.item_card_view);
            jcVideoPlayer = (JCVideoPlayer) itemView.findViewById(R.id.video_controller);
        }
    }


}
