package com.mimishi.mimishi.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.adapter.MainFragmentAdapter;
import com.mimishi.mimishi.base.BaseActivity;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by chen on 17-2-6.
 */

public class WebViewActivity extends BaseActivity{

    private WebView mWebView;
    private Toolbar mToolbar;
    private String mUrl;
    @Override
    protected int getLayout() {
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
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

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebSettings webSettings = mWebView.getSettings();

        Intent intent = getIntent();
        mUrl = intent.getStringExtra(MainFragmentAdapter.INTENT_URL);

        Log.i("url", mUrl);
        mUrl = "http://www.baidu.com/";
        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return false;
            }

           /* public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }else{
                    view.loadUrl(mUrl);
                }

                return false;
//                        super.shouldOverrideUrlLoading(view, request);
            }*/

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.stopLoading();
                view.loadUrl("about:blank");
                view.clearCache(true);
                view.reload();
            }
        });

        final Activity activity = this;
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                setTitle("页面加载中.....");
                setProgress(progress * 1000);

            }
        });

        //webview配置
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
//        webSettings.setPluginsEnabled(true);  //支持插件
        webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片

        mWebView.setDownloadListener(new WebViewDownLoadListener());
        mWebView.loadUrl(mUrl);

    }


    class WebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
            //下载任务有两种方式
            //自定义下载任务
            //调用系统的下载模块
            Uri url = Uri.parse(s);
            Intent intent = new Intent(Intent.ACTION_VIEW, url);
            startActivity(intent);

        }
    }


    private void exitThisActivity() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                exitThisActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
