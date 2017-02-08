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
import com.mimishi.mimishi.model.ResourcesMain;

import java.util.ArrayList;
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

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
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
        mRecyclerView.addItemDecoration(new RecyclerViewItemDecoration(40));
//        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                isRefresh = true;
            }
        });
        getData();
        return view;
    }

    private void getData() {
        List<ResourcesMain.ItemList> list = new ArrayList<>();
        ResourcesMain.ItemList itemList = new ResourcesMain.ItemList();
        itemList.item_index = 1;
        itemList.item_name = "项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行";
        itemList.item_url = "http://www.sekongge4.com/?jump";

        ResourcesMain.ItemList itemList0 = new ResourcesMain.ItemList();
        itemList0.item_index = 2;
        itemList0.item_name = "项目名称，，最多两行项目名称，，最多两行项目名称最多两行项目名称最多两行项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行最多两行最多两行最多两行最多两行最多两行";
        itemList0.item_url = "https://www.kink.com/";

        ResourcesMain.ItemList itemList1 = new ResourcesMain.ItemList();
        itemList1.item_index = 3;
        itemList1.item_name = "项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行";
        itemList1.item_url = "http://www.javchan.me";

        ResourcesMain.ItemList itemList2 = new ResourcesMain.ItemList();
        itemList2.item_index = 4;
        itemList2.item_name = "项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行项目名称，，最多两行";
        itemList2.item_url = "https://www.gg231.com";


        list.add(itemList);
        list.add(itemList1);
        list.add(itemList2);
        list.add(itemList0);
        mAdapter.addItems(list);

        mRefreshLayout.setRefreshing(false);

        Subscriber subscriber = new Subscriber<ResourcesMain>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResourcesMain resourcesMain) {
                List<ResourcesMain.ItemList> list = resourcesMain.list;
                if(!isRefresh){
                    mAdapter.addItems(list);
                }else{
                    mAdapter.refreshItems(list);
                    isRefresh = false;
                }
                mRefreshLayout.setRefreshing(false);

                Log.i("It`s first succed.", resourcesMain.name);
                Log.i("item_", list.get(0).item_name);
            }


        };

//        HttpMethods.getInstance().getMainData(subscriber);

    }

}
