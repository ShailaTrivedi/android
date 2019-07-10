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

import static com.example.bharti.ideamax.R.id.department;
import static com.example.bharti.ideamax.R.id.lastname;
import static com.example.bharti.ideamax.R.id.middlename;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostIdea extends Fragment {

    EditText title, abs, department, document, url;
    public Button btn;
    JSONParser jsonParser = new JSONParser();

    private static String url_ideapost = "http://192.168.43.150/IdeaMaxima/ideajson.php";

    private static final String TAG_SUCCESS = "success";

    public PostIdea() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_idea, container, false);
        title=(EditText)view.findViewById(R.id.title);
        abs=(EditText)view.findViewById(R.id.abs);
        department=(EditText)view.findViewById(R.id.department);
        document=(EditText)view.findViewById(R.id.document);
        url=(EditText)view.findViewById(R.id.url);
        Button btn=(Button)view.findViewById(R.id.postideasubmit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Your Idea Posted Successfully", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getActivity(),PostIdeaMsg.class);
//                startActivity(i);
                new PostIdea.IdeaPosted().execute();
            }
        });
        return view;
    }
    private class IdeaPosted extends AsyncTask<String, String, JSONObject> {
        String ideatitle = title.getText().toString();
        String ideaabs = abs.getText().toString();
        String ideadep = department.getText().toString();
        String ideadoc = document.getText().toString();
        String ideaurl = url.getText().toString();


        @Override
        protected JSONObject doInBackground(String... arg0) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("title", ideatitle));
            params.add(new BasicNameValuePair("abs", ideaabs));
            params.add(new BasicNameValuePair(" department", ideadep));
            params.add(new BasicNameValuePair("document", ideadoc));
            params.add(new BasicNameValuePair("url", ideaurl));

            JSONObject json = jsonParser.makeHttpRequest(url_ideapost, "POST", params);

            Log.d("Create Response", json.toString());


            return json;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {


            try {
                int success = jsonObject.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Toast.makeText(getActivity(), "Your Idea Posted Succesfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(),PostIdeaMsg.class);
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
