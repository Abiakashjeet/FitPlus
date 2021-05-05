package com.example.fitplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import javax.security.auth.callback.Callback;

public class Webview extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webview = findViewById(R.id.webview_web);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl("https://www.diabetes.ca/managing-my-diabetes/tools---resources/body-mass-index-(bmi)-calculator");
        webview.setWebViewClient(new Callback());

    }
    private class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}