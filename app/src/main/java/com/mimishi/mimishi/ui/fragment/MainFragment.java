package com.mimishi.mimishi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.adapter.MainFragmentAdapter;
import com.mimishi.mimishi.base.BaseFragment;
import com.mimishi.mimishi.common.RecyclerViewItemDecoration;
import com.mimishi.mimishi.model.ResourcesVideo;
import com.mimishi.mimishi.rx.HttpMethods;
import com.mimishi.mimishi.utils.ToastUtils;

import java.util.List;

import rx.Subscriber;

/**
 * Created by chen on 17-2-5.
 */

public class MainFragment extends BaseFragment{

    private RecyclerView mRecyclerView;
    private MainFragmentAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private boolean isRefreshing;
    private boolean isRefresh = false;
    private int mFragmentType;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    public MainFragment(int type){
        mFragmentType = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mAdapter = new MainFragmentAdapter(getContext());
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewItemDecoration(60));
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataList();
                isRefresh = true;
            }
        });
        getDataList();
        return view;
    }

    private void getDataList() {
        switch (mFragmentType){
            case 1:
                getMainDataList();
                break;
            case 2:
                getUncensoredDataList();
                break;
        }
    }

    private void getUncensoredDataList() {
        Subscriber subscriber = new Subscriber<ResourcesVideo>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResourcesVideo resourcesVideo) {
                mRefreshLayout.setRefreshing(false);
                List<ResourcesVideo.ItemList> list = resourcesVideo.list;
                if(list.size() == 0){
                    ToastUtils.showMessage(getContext(), "没有获取到数据，检查网络后重试");
                    return;
                }
                if(!isRefresh){
                    mAdapter.addItems(list);
                }else{
                    mAdapter.refreshItems(list);
                    isRefresh = false;
                }

            }

        };
        HttpMethods.getInstance().getUncensoredData(subscriber);
    }

    private void getMainDataList() {

        Subscriber subscriber = new Subscriber<ResourcesVideo>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResourcesVideo resourcesVideo) {
                mRefreshLayout.setRefreshing(false);
                List<ResourcesVideo.ItemList> list = resourcesVideo.list;
                if(list.size() == 0){
                    ToastUtils.showMessage(getContext(), "没有获取到数据，检查网络后重试");
                    return;
                }
                if(!isRefresh){
                    mAdapter.addItems(list);
                }else{
                    mAdapter.refreshItems(list);
                    isRefresh = false;
                }

                Log.i("It`s first succeed.", resourcesVideo.name);
                Log.i("item_", list.get(0).item_title);
            }

        };
        HttpMethods.getInstance().getMainData(subscriber);
    }


}
