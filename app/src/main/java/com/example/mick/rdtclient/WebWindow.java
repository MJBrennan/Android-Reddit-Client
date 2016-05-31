package com.example.mick.rdtclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class WebWindow extends Activity  {


    private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_window);

        Intent i = getIntent();
        // getting attached intent data
        String url = i.getStringExtra("product");

        wv1=(WebView)findViewById(R.id.webView1);
        wv1.setWebViewClient(new ourViewClient());


                wv1.getSettings().setLoadsImagesAutomatically(true);
                wv1.getSettings().setJavaScriptEnabled(true);
                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv1.loadUrl(url);

    }






}



/****
 Intent i = getIntent();
 // getting attached intent data
 String url = i.getStringExtra("product");
 *****/