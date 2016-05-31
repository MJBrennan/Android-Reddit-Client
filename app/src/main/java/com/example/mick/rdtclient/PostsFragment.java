package com.example.mick.rdtclient;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.widget.AdapterView.OnItemClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * While this looks like a lot of code, all this class
 * actually does is load the posts in to the listview.
 *
 * @author Hathy
 */
public class PostsFragment extends Fragment {

    ListView postsList;
    ArrayAdapter<Post> adapter;
    Handler handler;

    String term1;
    String subreddit;
    List<Post> posts;
    PostsHolder postsHolder;
    Spinner spinner;











    public PostsFragment() {
        handler = new Handler();
        posts = new ArrayList<Post>();
    }

    public static Fragment newInstance(String subreddit) {
        PostsFragment pf = new PostsFragment();
        pf.subreddit = subreddit;
        pf.postsHolder = new PostsHolder(pf.subreddit);
        return pf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_feed
                , container
                , false);
        postsList = (ListView) v.findViewById(R.id.posts_list);

        spinner = (Spinner) v.findViewById(R.id.rating_spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity() ,  " You hav selected" + item , Toast.LENGTH_LONG).show() ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Hot");
        categories.add("Best");
        categories.add("New");
        categories.add("Worst");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        return v;
    }







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        // This should run only once for the fragment as the
        // setRetainInstance(true) method has been called on
        // this fragment

        if (posts.size() == 0) {

            // Must execute network tasks outside the UI
            // thread. So create a new thread.

            new Thread() {
                public void run() {
                    posts.addAll(postsHolder.fetchPosts());

                    // UI elements should be accessed only in
                    // the primary thread, so we must use the
                    // handler here.

                    handler.post(new Runnable() {
                        public void run() {
                            createAdapter();
                        }
                    });
                }
            }.start();
        } else {
            createAdapter();
        }
    }

    /**
     * This method creates the adapter from the list of posts
     * , and assigns it to the list.
     */
    private void createAdapter() {

        // Make sure this fragment is still a part of the activity.
        if (getActivity() == null) return;

        adapter = new ArrayAdapter<Post>(getActivity()
                , R.layout.listview_list
                , posts) {
            @Override
            public View getView(int position,
                                View convertView,
                                ViewGroup parent) {

                if (convertView == null) {
                    convertView = getActivity()
                            .getLayoutInflater()
                            .inflate(R.layout.listview_list, null);
                }

                TextView postTitle;
                postTitle = (TextView) convertView
                        .findViewById(R.id.post_title);


                TextView postDetails;
                postDetails = (TextView) convertView
                        .findViewById(R.id.post_details);

                TextView postScore;
                postScore = (TextView) convertView
                        .findViewById(R.id.post_score);

                postTitle.setText(posts.get(position).title);
                postDetails.setText(posts.get(position).getDetails());
                postScore.setText(posts.get(position).getScore());
                String yes = posts.get(position).getURL();
                return convertView;
            }
        };
        postsList.setAdapter(adapter);

        postsList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected
                String yes = posts.get(position).getURL();

                Intent i = new Intent(getActivity(), WebWindow.class);
                // sending data to new activity
                i.putExtra("product", yes);
                startActivity(i);


            }
        });

    }

}













