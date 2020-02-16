package com.aysel.rssreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Bundle bundle = getIntent().getExtras();
        String haberLink = "";
        if(bundle != null){
            haberLink = bundle.getString("link");
        }

        WebView mwebview = findViewById(R.id.webview);
        mwebview.getSettings().setJavaScriptEnabled(true);
        mwebview.getSettings().setDomStorageEnabled(true);
        mwebview.loadUrl(haberLink);
    }
}