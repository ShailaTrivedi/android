package com.example.bharti.ideamax;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
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

public class ShareViewIdeaActivity extends ListActivity {

    JSONParser jParser = new JSONParser();


    ArrayList<HashMap<String, String>> IdeaShareList;

    private static String url_all_IdeaShare = "http://192.168.43.150/IdeaMaxima/view_share_idea.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_IdeaShare= "ideashare";
    private static final String TAG_ShareID = "share_id";
    private static final String TAG_IdeaName= "name";
    private static final String TAG_Idea = "idea";
    // offers JSONArray
    JSONArray IdeaShare = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_view_idea);
        setTitle("Share Idea");


        IdeaShareList = new ArrayList<HashMap<String, String>>();

        new LoadAllIdeaShare().execute();

        ListView list = getListView();

    }

    class LoadAllIdeaShare extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(url_all_IdeaShare, "GET", params);

            Log.d("ideashare: ", json.toString());

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    IdeaShare = json.getJSONArray(TAG_IdeaShare);

                    for (int i = 0; i < IdeaShare.length(); i++) {
                        JSONObject c = IdeaShare.getJSONObject(i);
                        String share_id = c.getString(TAG_ShareID);
                        String name = c.getString(TAG_IdeaName);
                        String idea = c.getString(TAG_Idea);

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_ShareID, share_id);
                        map.put(TAG_IdeaName,name);
                        map.put(TAG_Idea, idea);

                        IdeaShareList.add(map);
                    }
                } else {

                    Toast.makeText(ShareViewIdeaActivity.this, "Idea not found", Toast.LENGTH_LONG).show();
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
                            ShareViewIdeaActivity.this, IdeaShareList,
                            R.layout.list_shareidea_view_activity, new String[] {TAG_ShareID, TAG_IdeaName,TAG_Idea},
                            new int[] {R.id.id, R.id.name, R.id.idea});
                    setListAdapter(adapter);
                }
            });

        }
    }
}

