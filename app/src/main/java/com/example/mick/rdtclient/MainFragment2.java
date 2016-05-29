package com.example.mick.rdtclient;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * As of now, all this activity does is create and
 * render a fragment.
 *
 * @author Hathy
 */
public class MainFragment2 extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_holder);
        addFragment();
    }

    void addFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragments_holder
                        , PostsFragment.newInstance("askreddit"))
                .commit();
    }
}