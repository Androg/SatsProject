package se.tuppload.android.satstrainingapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowWebActivity extends ShowMapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        Bundle extras = getIntent().getExtras();

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl((String) extras.get("URL"));
    }
}