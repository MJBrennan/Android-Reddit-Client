package com.example.mick.rdtclient;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ListActivity extends ActionBarActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        SharedPreferences prefs = getSharedPreferences(  MyPREFERENCES  , MODE_PRIVATE );

        String key1 = prefs.getString(  "nameKey" , null );

        if (key1 != null) {
            String name = prefs.getString("nameKey", "No name defined");//"No name defined" is the default value.
        }

        TextView tv =  (TextView) findViewById(R.id.field2);
        tv.setText(key1);
    }

}
