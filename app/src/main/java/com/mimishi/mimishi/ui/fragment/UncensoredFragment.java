package com.mimishi.mimishi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.base.BaseFragment;

/**
 * Created by chen on 17-2-14.
 */

public class UncensoredFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_uncensored;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }
}
