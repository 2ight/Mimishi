package com.mimishi.mimishi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.adapter.MainFragmentAdapter;
import com.mimishi.mimishi.base.BaseActivity;

/**
 * Created by chen on 17-2-6.
 */

public class WebViewActivity extends BaseActivity{

    private WebView mWebView;
    private Toolbar mToolbar;
    private String mUrl;
    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = (WebView) findViewById(R.id.web_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(MainFragmentAdapter.INTENT_URL);

//        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(mUrl);
                return true;
//                        super.shouldOverrideUrlLoading(view, request);
            }
        });

    }
}
