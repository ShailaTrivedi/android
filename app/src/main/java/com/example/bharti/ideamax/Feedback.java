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
public class Feedback extends Fragment {

    EditText name,email,dep,msg;
    public Button btn;
    JSONParser jsonParser = new JSONParser();
    private static String url_feedback = "http://192.168.43.150/IdeaMaxima/feedbackjson.php";

    private static final String TAG_SUCCESS = "success";

    public Feedback() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        name=(EditText)view.findViewById(R.id.name);
        email=(EditText)view.findViewById(R.id.email);
        dep=(EditText)view.findViewById(R.id.dep);
        msg=(EditText)view.findViewById(R.id.msg);
        Button btn=(Button)view.findViewById(R.id.feedbacksubmit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Your Idea Posted Successfully", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getActivity(),PostIdeaMsg.class);
//                startActivity(i);
                new IdeaPosted().execute();
            }
        });
        return view;
    }
    private class IdeaPosted extends AsyncTask<String, String, JSONObject> {
        String postname = name.getText().toString();
        String postemail =email.getText().toString();
        String postdep = dep.getText().toString();
        String postmsg = msg.getText().toString();



        @Override
        protected JSONObject doInBackground(String... arg0) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", postname));
            params.add(new BasicNameValuePair("email", postemail));
            params.add(new BasicNameValuePair(" dep", postdep));
            params.add(new BasicNameValuePair("msg", postmsg));


            JSONObject json = jsonParser.makeHttpRequest(url_feedback, "POST", params);

            Log.d("Create Response", json.toString());


            return json;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {


            try {
                int success = jsonObject.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Toast.makeText(getActivity(), "Feedback Posted Succesfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(),Feedbackmsg.class);
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




