package com.example.mick.rdtclient;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;






public class MainFragment2 extends FragmentActivity {




    public String Term2 = "Ferrari";
    public String Term1 = "Seinfeld" ;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_holder);


        addFragment();
    }



  public void addFragment(){

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragments_holder
                        , PostsFragment.newInstance(  Term2  + "+"   + Term1 ))
                .commit();
    }
}