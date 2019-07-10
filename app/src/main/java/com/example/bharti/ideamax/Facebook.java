package com.example.bharti.ideamax;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class Facebook extends AppCompatActivity {
    WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        mWebview  = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        //final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient());

        mWebview .loadUrl("http://facebook.com");
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
