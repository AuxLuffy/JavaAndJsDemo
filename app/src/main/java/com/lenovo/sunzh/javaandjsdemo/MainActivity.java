package com.lenovo.sunzh.javaandjsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnJavaAndJs;
    private Button mBtnJsCallJava;
    private Button mBtnJsCallPhone;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initview();
        setListener();
    }

    private void setListener() {
        mBtnJavaAndJs.setOnClickListener(this);
        mBtnJsCallJava.setOnClickListener(this);
        mBtnJsCallPhone.setOnClickListener(this);
    }

    private void initview() {
        mBtnJavaAndJs = (Button) findViewById(R.id.btn_java_and_js);
        mBtnJsCallJava = (Button) findViewById(R.id.btn_js_call_java);
        mBtnJsCallPhone = (Button) findViewById(R.id.btn_js_call_phone);
        text = (TextView) findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_java_and_js:
                // Handle clicks for btnJavaAndJs
                Intent intent1 = new Intent(this, JavaAndJSActivity.class);
                startActivity(intent1);
//                float textSize = text.getTextSize();
////                Toast.makeText(MainActivity.this, "gettextsize(): " + textSize, Toast.LENGTH_SHORT).show();
//                DisplayMetrics displayMetrics1 = getResources().getDisplayMetrics();
//                Toast.makeText(MainActivity.this, "h: " + displayMetrics1.heightPixels + ", w:" + displayMetrics1.widthPixels + ", density: " + displayMetrics1.density+", w: "+displayMetrics1.xdpi+"dpi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_js_call_java:
                // Handle clicks for btnJsCallJava
//                Intent intent2 = new Intent(this, JsCallJavaVideoActivity.class);
                Intent intent2 = new Intent(this, ScrollViewAndWebView.class);
//                float tv_18_size = MainActivity.this.getResources().getDimension(R.dimen.tv_18_size);
////                Toast.makeText(MainActivity.this, "R.dimen.tv_18_size = " + tv_18_size, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "默认字体大小为：" + text.getTextSize(), Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                break;
            case R.id.btn_js_call_phone:
                // Handle clicks for btnJsCallPhone
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                float density = displayMetrics.density;
                Toast.makeText(MainActivity.this, "density: " + density, Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this, JsCallJavaCallPhoneActivity.class);
                startActivity(intent3);

                /*File filePath = new File(Environment.getExternalStorageDirectory(), "Download");
                File apkFile = new File(filePath, "nhdz.apk");
                if (!apkFile.exists()) {
                    Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
                    text.setText("文件不存在");
                    break;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (Build.VERSION.SDK_INT >= 24) {
                    Uri uri = FileProvider.getUriForFile(this, "com.lenovo.sunzh.javaandjsdemo.fileprovider", apkFile);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(uri, MimeTypeMap.getSingleton().getMimeTypeFromExtension("apk"));
                } else {
                    intent.setDataAndType(Uri.fromFile(apkFile), MimeTypeMap.getSingleton().getMimeTypeFromExtension("apk"));
                }
                startActivity(intent);
                FileNameMap map = URLConnection.getFileNameMap();*/
//                map.getContentTypeFor("aad.apk");
//                Toast.makeText(MainActivity.this, "" + map.getContentTypeFor("aa.apk"), Toast.LENGTH_SHORT).show();
////                text.setText(map.getContentTypeFor("aa.apk"));
////                String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension("apk");
//                String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(apkFile.getPath());
//                text.setText(fileExtensionFromUrl + "   " + MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl));
//
////                Toast.makeText(MainActivity.this, "MIME:" + mime, Toast.LENGTH_SHORT).show();
////                text.setText(mime);
//                try {
//                    MediaStore.Images.Media.insertImage(getContentResolver(),apkFile.getAbsolutePath(),"apk",null);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
                break;
        }
    }
}
