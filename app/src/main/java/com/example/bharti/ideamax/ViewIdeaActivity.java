package com.example.bharti.ideamax;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class ViewIdeaActivity extends ListActivity {

    JSONParser jParser = new JSONParser();


    ArrayList<HashMap<String, String>> IdeaPostList;

    private static String url_all_IdeaPost = "http://192.168.43.150/IdeaMaxima/view_post_idea.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_IdeaPost= "ideapost";
    private static final String TAG_IdeaID = "idea_id";
    private static final String TAG_IdeaTitle= "title";
    private static final String TAG_IdeaAbs = "abs";
    private static final String TAG_Document = "document";
    private static final String TAG_Department = "department";
    private static final String TAG_URL = "url";
    // offers JSONArray
    JSONArray IdeaPost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_idea);
        setTitle("Post Idea");


        IdeaPostList = new ArrayList<HashMap<String, String>>();

        new LoadAllIdeaPost().execute();

        ListView list = getListView();

    }

    class LoadAllIdeaPost extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(url_all_IdeaPost, "GET", params);

            Log.d("ideapost: ", json.toString());

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    IdeaPost = json.getJSONArray(TAG_IdeaPost);

                    for (int i = 0; i < IdeaPost.length(); i++) {
                        JSONObject c = IdeaPost.getJSONObject(i);
                        String idea_id = c.getString(TAG_IdeaID);
                        String title = c.getString(TAG_IdeaTitle);
                        String abs = c.getString(TAG_IdeaAbs);
                        String document = c.getString(TAG_Document);
                        String department = c.getString(TAG_Department);
                        String url = c.getString(TAG_URL);

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_IdeaID, idea_id);
                        map.put(TAG_IdeaTitle, title);
                        map.put(TAG_IdeaAbs, abs);
                        map.put(TAG_Document, document);
                        map.put(TAG_Department, department);
                        map.put(TAG_URL, url);

                        IdeaPostList.add(map);
                    }
                } else {

                    Toast.makeText(ViewIdeaActivity.this, "Idea not found", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {

            runOnUiThread(new Runnable() {
                public void run() {

                    ListAdapter adapter = new SimpleAdapter(
                            ViewIdeaActivity.this, IdeaPostList,
                            R.layout.list_postview_activity, new String[] {TAG_IdeaID, TAG_IdeaTitle,TAG_IdeaAbs,TAG_Document,TAG_Department,TAG_URL},
                            new int[] {R.id.id, R.id.title, R.id.abs, R.id.document,R.id.department,R.id.url});
                    setListAdapter(adapter);
                }
            });

        }
    }
}

