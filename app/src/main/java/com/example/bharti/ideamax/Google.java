package com.example.bharti.ideamax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Google extends AppCompatActivity {
    WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);
        mWebview  = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        //final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient());

        mWebview .loadUrl("http://gmail.com");
        setContentView(mWebview );
    }

    Boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack())
            mWebview.goBack();
        else
            super.onBackPressed();

    }
}

