package com.example.webview;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    private WebView myWebview;
    public MyWebViewClient(WebView w){
        this.myWebview=w;
    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url){
        if(Uri.parse(url).getHost().equals("https://www.w3schools.com/sql/sql_foreignkey.asp")){
            return false;
        }

        myWebview.loadUrl("https://viblo.asia/p/gioi-thieu-ve-content-provider-trong-android-PDOkqLoaejx");
        return true;
    }
}
