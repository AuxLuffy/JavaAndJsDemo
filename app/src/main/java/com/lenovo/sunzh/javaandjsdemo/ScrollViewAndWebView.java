package com.lenovo.sunzh.javaandjsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ScrollView;

public class ScrollViewAndWebView extends AppCompatActivity {

    private WebView mWebview;
    private ScrollView mScrollView;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_and_web_view);
        initView();
        initData();
    }

    private void initData() {
        settings = mWebview.getSettings();
        //支持任意比例缩放
//        settings.setUseWideViewPort(false);
//        //不出现内置的缩放工具
//        settings.setBuiltInZoomControls(false);
//        //不可以缩放
//        settings.setSupportZoom(false);
//        settings.setBuiltInZoomControls(false);
        //设置不支持变焦
//        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(true);
//        settings.setUseWideViewPort(false);
        //支持js
        settings.setJavaScriptEnabled(true);
        //自适应屏幕
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        //自适应屏幕大小界面
//        settings.setLoadWithOverviewMode(true);
//        CookieSyncManager.createInstance(this);//现在的webview都会自动同步cookies,每隔五分钟同步一次，也可以在{@link *WebViewClient#onPageFinished}中调用CookieSyncManager.getInstance().sync()来手动同步
//        mWebview.clearCache(true);//会清空当前应用中的所有缓存

//        mWebview.clearHistory();
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mScrollView.scrollTo(0, 0);
                //                javascript:App.resize(document.body.getBoundingClientRect().height)
                mWebview.loadUrl("javascript:App.resize(document.body.getBoundingClientRect().height)");
                super.onPageFinished(view, url);
//                int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//                int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//
//                mWebview.measure(w, h);
            }
        });
        mWebview.addJavascriptInterface(this, "App");
        mWebview.loadUrl("http://www.jianshu.com/");

    }

    @JavascriptInterface
    public void resize(final float height) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebview.setLayoutParams(new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources().getDisplayMetrics().density)));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        CookieSyncManager.getInstance().startSync();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        CookieSyncManager.getInstance().stopSync();
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
    }
}
