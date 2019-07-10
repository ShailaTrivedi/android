package com.example.bharti.ideamax;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareIdeaFragment extends Fragment {

    EditText name,idea;
    public Button btn;
    JSONParser jsonParser = new JSONParser();

    private static String url_ideashare = "http://192.168.43.150/IdeaMaxima/ideasharejson.php";

    private static final String TAG_SUCCESS = "success";

    public ShareIdeaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_idea, container, false);
        name=(EditText)view.findViewById(R.id.name);
        idea=(EditText)view.findViewById(R.id.idea);
        Button btn=(Button)view.findViewById(R.id.shareideasubmit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Your Idea Posted Successfully", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getActivity(),PostIdeaMsg.class);
//                startActivity(i);
                new IdeaShared().execute();
            }
        });
        return view;
    }
    private class IdeaShared extends AsyncTask<String, String, JSONObject> {
        String ideaname = name.getText().toString();
        String idea1 = idea.getText().toString();

        @Override
        protected JSONObject doInBackground(String... arg0) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", ideaname));
            params.add(new BasicNameValuePair("idea", idea1));

            JSONObject json = jsonParser.makeHttpRequest(url_ideashare, "POST", params);

            Log.d("Create Response", json.toString());


            return json;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {


            try {
                int success = jsonObject.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Toast.makeText(getActivity(), "Your Idea Shared Succesfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(),ShareIdeaMsg.class);
                    startActivity(i);
                } else {

                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(jsonObject);
        }
    }


}

