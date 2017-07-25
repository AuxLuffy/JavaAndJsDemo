package com.lenovo.sunzh.javaandjsdemo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.HashMap;

public class ScrollViewAndWebView extends Activity {

    private static final String TAG = "ScrollViewAndWebView";
    private WebView mWebview;
    private ScrollView mScrollView;
    private WebSettings settings;
    private HashMap<String, Integer> map = new HashMap<>();
    private boolean backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_and_web_view);
        initView();
        initData();
    }

    public void refresh(View v) {
//        mWebview.reload();
        openNotificationSettings();
    }

    /**
     * 打开app通知设置
     */
    private void openNotificationSettings(){
        try {

            Intent intent = new Intent();

            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");

            intent.putExtra("app_package", getPackageName());

            intent.putExtra("app_uid", getApplicationInfo().uid);

            startActivity(intent);

        } catch (ActivityNotFoundException e) {

            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

            Uri uri = Uri.fromParts("package", getPackageName(), null);

            intent.setData(uri);

            startActivity(intent);

        }
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
            public void onPageFinished(WebView view, final String url) {

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    mWebview.evaluateJavascript("js方法", new ValueCallback<String>() {
//                        @Override
//                        public void onReceiveValue(String value) {
//                            if (backpress) {
//                                if (null != value && value.startsWith("\"")) {
//                                    value = value.substring(1);
//                                }
//                                Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
//                                while (iter.hasNext()) {
//                                    Map.Entry<String, Integer> entry = iter.next();
//                                    if (url.contains(entry.getKey().toString())) {
//                                        mWebview.scrollTo(0, map.get(entry.getKey()));
//                                    }
//                                }
//                                backpress = false;
//                            } else {
//                                mScrollView.scrollTo(0, 0);
//                            }
//                        }
//                    });
//                } else {
//                    if (backpress) {
//                        mWebview.loadUrl("javascript:onWebviewFinishLoad();");
//                        backpress = false;
//                    } else {
//                        mScrollView.scrollTo(0, 0);
//                    }
//                }
                Log.e(TAG, "onPageFinished-------");
                mScrollView.scrollTo(0, 0);
                //                javascript:App.resize(document.body.getBoundingClientRect().height)
                mWebview.loadUrl("javascript:App.resize(document.body.getBoundingClientRect().height)");

                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Log.e(TAG, "shouldOverrideUrlLoading----");
//                WebBackForwardList backForwardList = mWebview.copyBackForwardList();
//                if (backForwardList != null && backForwardList.getSize() != 0) {
//                    int currentIndex = backForwardList.getCurrentIndex();
//                    WebHistoryItem historyItem = backForwardList.getItemAtIndex(currentIndex);
//                    if (historyItem != null) {
//                        String backPageUrl = historyItem.getUrl();
//                        int scrollY = mWebview.getScrollY();
//                        map.put(backPageUrl, scrollY);
//                    }
//                }
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e(TAG, "onPageStarted");
                super.onPageStarted(view, url, favicon);
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
                mWebview.setLayoutParams(new RelativeLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources().getDisplayMetrics().density)));
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            backpress = true;
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
