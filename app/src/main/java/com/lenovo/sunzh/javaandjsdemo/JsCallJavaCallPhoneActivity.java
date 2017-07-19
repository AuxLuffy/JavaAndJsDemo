package com.lenovo.sunzh.javaandjsdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.KeyEvent.KEYCODE_BACK;
import static com.lenovo.sunzh.javaandjsdemo.R.id.webview;

public class JsCallJavaCallPhoneActivity extends AppCompatActivity {

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
//        startActivity(new Intent(), ActivityOptions.makeSceneTransitionAnimation(this,webview,"sharedView").toBundle());
        //页面支持缩放按钮，前提是页面要支持
        settings.setBuiltInZoomControls(true);

        //设置客户端不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress>=20) {
//                    webView.loadUrl(BrowserJsInject.fullScreenByJs(luyan_bean.videoPath));
                }
            }
        });
        //设置支持js调用java
        //以后在js中就可以通过"Android"字段来调用AndroidAndJSInterface里的方法了
//        webView.addJavascriptInterface(new AndroidAndJSInterface(), "Android");
        webView.addJavascriptInterface(new AndroidAndJSInterface(), "Android");


        //加载网络资源
//        webView.loadUrl("http://10.0.2.2:8080/assets/JavaAndJavaScriptCall.html");
//        webView.loadUrl("file:///android_asset/JsCallJavaCallPhone.html");
        webView.loadUrl("http://www.baidu.com/");

    }

    public void supportFlash(){

        String temp =
                "\"> "

                + "\" height=\"" + "90%" + "\" scale=\"" + "noscale"

                + "\" type=\"" + "application/x-shockwave-flash"

                + "\"> ";

        String mimeType = "text/html";

        String encoding = "utf-8";

        webView.loadDataWithBaseURL("null", temp, mimeType, encoding, "");
    }

    private class AndroidAndJSInterface {
        @JavascriptInterface
        public void showcontacts() {
            final String json = "[{\"name\":\"阿福\", \"phone\":\"18600012345\"}]";
            // 调用JS中的方法
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:show('" + json + "')");
                }
            });

        }

        @JavascriptInterface
        public void call(String phone) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            checkPermissions();
            if (ActivityCompat.checkSelfPermission(JsCallJavaCallPhoneActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        }
    }

    private void checkPermissions() {
        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.CALL_PHONE);
            if (permissionsList.size() != 0) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults != null && requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(JsCallJavaCallPhoneActivity.this, "make phone!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
