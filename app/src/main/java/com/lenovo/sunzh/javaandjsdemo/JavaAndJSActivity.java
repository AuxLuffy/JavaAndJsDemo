package com.lenovo.sunzh.javaandjsdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JavaAndJSActivity extends AppCompatActivity {
    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;
    private WebView webView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_and_js);

        initview();

        setlistener();

        initWebview();
    }

    private void initWebview() {
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        //设置支持javascript脚本语言
        settings.setJavaScriptEnabled(true);

        //页面支持缩放按钮，前提是页面要支持
        settings.setBuiltInZoomControls(true);

        //设置客户端不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());

        //设置支持js调用java
        //以后在js中就可以通过"Android"字段来调用AndroidAndJSInterface里的方法了
        webView.addJavascriptInterface(new AndroidAndJSInterface(), "Android");

        //加载网络资源
//        webView.loadUrl("http://10.0.2.2:8080/assets/JavaAndJavaScriptCall.html");
//        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");
        webView.loadUrl("http://www.baidu.com");


        //显示页面
//        setContentView(webView);
    }

    private void setlistener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String numebr = etNumber.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(numebr) || TextUtils.isEmpty(password)) {
            Toast.makeText(JavaAndJSActivity.this, "账号或者密码为空", Toast.LENGTH_SHORT).show();
            webView.loadUrl("http://www.baidu.com");
            setContentView(webView);
        } else {
            //登录
            login(numebr);
        }
    }

    /**
     * Java调用javaScript
     *
     * @param numebr
     */
    private void login(String numebr) {
        webView.loadUrl("javascript:javaCallJs(" + "'" + numebr + "'" + ")");
        setContentView(webView);
    }

    private void initview() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        etNumber = (EditText) findViewById(R.id.et_number);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    /**
     * js可以调用该类的方法
     */
    class AndroidAndJSInterface {
        //注解必须写
        @JavascriptInterface
        public void showToast() {
            Toast.makeText(JavaAndJSActivity.this, "我被js调用了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.btn_bar:
                action();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void action() {
        String currentUrl = webView.getUrl();
        String originalUrl = webView.getOriginalUrl();
        Toast.makeText(JavaAndJSActivity.this, "currentUrl: " + currentUrl + "\r\noriginalUrl: " + originalUrl, Toast.LENGTH_SHORT).show();
        Log.e("TAG", "currentUrl: " + currentUrl + "\r\noriginalUrl: " + originalUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
