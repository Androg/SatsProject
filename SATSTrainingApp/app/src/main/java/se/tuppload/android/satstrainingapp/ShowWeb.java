package se.tuppload.android.satstrainingapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowWeb extends ShowMap {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        Bundle extras = getIntent().getExtras();

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl((String) extras.get("URL"));
    }
}