package com.example.mick.rdtclient;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;


public class WebWindow extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_window);

        WebView  ourBrow = (WebView) findViewById(R.id.webView1);

        ourBrow.getSettings().setJavaScriptEnabled(true);
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        ourBrow.getSettings().setUseWideViewPort(true);





        ourBrow.setWebViewClient(new ourViewClient());
        try{



            ourBrow.loadUrl("http://www.google.com");
        } catch (Exception e){
            e.printStackTrace();
        }



    }

}