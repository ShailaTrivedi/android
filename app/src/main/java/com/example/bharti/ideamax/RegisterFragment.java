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
public class RegisterFragment extends Fragment {

    EditText firstname,middlename,lastname,email,mobile,password,confirmpassword;
    public Button btn;
    JSONParser jsonParser = new JSONParser();

    private static String url_registration = "http://192.168.1.105/IdeaMaxima/registrationjson.php";

    private static final String TAG_SUCCESS = "success";

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        firstname=(EditText)view.findViewById(R.id.firstname);
        middlename=(EditText)view.findViewById(R.id.middlename);
        lastname=(EditText)view.findViewById(R.id.lastname);
        email=(EditText)view.findViewById(R.id.email);
        mobile=(EditText)view.findViewById(R.id.mobile);
        password=(EditText)view.findViewById(R.id.password);
        confirmpassword=(EditText)view.findViewById(R.id.confirmpassword);
        Button btn=(Button)view.findViewById(R.id.register_submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Your Idea Posted Successfully", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getActivity(),PostIdeaMsg.class);
//                startActivity(i);
                new CreateRegistration().execute();
            }
        });
        return view;

    }
    private class CreateRegistration extends AsyncTask<String, String, JSONObject> {
        String regfirstname = firstname.getText().toString();
        String regmiddlename = middlename.getText().toString();
        String reglastname = lastname.getText().toString();
        String regemail = email.getText().toString();
        String regmobile = mobile.getText().toString();
        String regpassword = password.getText().toString();
        String regconfirmpassword = confirmpassword.getText().toString();


        @Override
        protected JSONObject doInBackground(String... arg0) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("firstname", regfirstname));
            params.add(new BasicNameValuePair("middlename", regmiddlename));
            params.add(new BasicNameValuePair("lastname", reglastname));
            params.add(new BasicNameValuePair("email", regemail));
            params.add(new BasicNameValuePair("mobile", regmobile));
            params.add(new BasicNameValuePair("password", regpassword));
            params.add(new BasicNameValuePair("confirmpassword", regconfirmpassword));

            JSONObject json = jsonParser.makeHttpRequest(url_registration, "POST", params);

            Log.d("Create Response", json.toString());


            return json;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {


            try {
                int success = jsonObject.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Toast.makeText(getActivity(), "Reg Succesfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(),Login.class);
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
