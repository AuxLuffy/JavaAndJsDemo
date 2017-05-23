package com.lenovo.sunzh.javaandjsdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.lenovo.sunzh.javaandjsdemo.R.id.webview;

public class JsCallJavaVideoActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_java_video);
        webView = (WebView) findViewById(webview);
        initWebview();
    }

    private void initWebview() {
        WebSettings settings = webView.getSettings();
        //设置支持javascript脚本语言
        settings.setJavaScriptEnabled(true);

        //页面支持缩放按钮，前提是页面要支持
        settings.setBuiltInZoomControls(true);

        //设置客户端不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());

        //设置支持js调用java
        //以后在js中就可以通过"Android"字段来调用AndroidAndJSInterface里的方法了
//        webView.addJavascriptInterface(new AndroidAndJSInterface(), "Android");
        webView.addJavascriptInterface(new AndroidAndJSInterface(), "android");


        //加载网络资源
//        webView.loadUrl("http://10.0.2.2:8080/assets/JavaAndJavaScriptCall.html");
        webView.loadUrl("file:///android_asset/RealNetJSCallJavaActivity.htm");
    }

    private class AndroidAndJSInterface {
        @JavascriptInterface
        public void playVideo(int itemid, String videourl, String itemtitle) {
            Intent intent = new Intent();
            intent.setDataAndType(Uri.parse(videourl), "video/*");
            startActivity(intent);
        }
    }
}
