package com.example.mick.rdtclient;


        import java.util.ArrayList;
        import java.util.List;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import android.content.Context;
        import android.content.SharedPreferences;
        import android.util.Log;

/**
 * This is the class that creates Post objects out of the Reddit
 * API, and maintains a list of these posts for other classes
 * to use.
 *
 * @author Hathy
 */
public class PostsHolder {

    /**
     * We will be fetching JSON data from the API.
     */

    public String term1 = "Tottenham";
    public  String term2 = "Football";






    private final String URL_TEMPLATE=
            "https://www.reddit.com/search.json?q=TERM1?";

    String subreddit;
    String score;
    String url;
    String after;



    PostsHolder(String sr){
        subreddit=sr;
        after="";
        generateURL();
    }

 public void   RatingHolder(String rating){
        score=rating;
        after="";
        changeURL();
    }



    /**
     * Generates the actual URL from the template based on the
     * subreddit name and the 'after' property.
     */
    private void generateURL(){
        url=URL_TEMPLATE.replace("TERM1", subreddit);
        url=url.replace("AFTER", after);
    }

    private void changeURL(){
        url=URL_TEMPLATE.replace("RATING", score);
        url=url.replace("AFTER", after);
    }


    /**
     * Returns a list of Post objects after fetching data from
     * Reddit using the JSON API.
     *
     * @return
     */
    List<Post> fetchPosts(){
        String raw=RemoteData.readContents(url);
        List<Post> list=new ArrayList<Post>();
        try{
            JSONObject data=new JSONObject(raw)
                    .getJSONObject("data");
            JSONArray children=data.getJSONArray("children");

            //Using this property we can fetch the next set of
            //posts from the same subreddit
            after=data.getString("after");

            for(int i=0;i<children.length();i++){
                JSONObject cur=children.getJSONObject(i)
                        .getJSONObject("data");
                Post p=new Post();
                p.title=cur.optString("title");
                p.url=cur.optString("url");
                p.numComments=cur.optInt("num_comments");
                p.points=cur.optInt("score");
                p.author=cur.optString("author");
                p.subreddit=cur.optString("subreddit");
                p.permalink=cur.optString("permalink");
                p.domain=cur.optString("domain");
                p.id=cur.optString("id");
                if(p.title!=null)
                    list.add(p);
            }
        }catch(Exception e){
            Log.e("fetchPosts()",e.toString());
        }
        return list;
    }

    /**
     * This is to fetch the next set of posts
     * using the 'after' property
     * @return
     */
    List<Post> fetchMorePosts(){
        generateURL();
        return fetchPosts();
    }
}