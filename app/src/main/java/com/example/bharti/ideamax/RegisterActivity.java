package com.example.bharti.ideamax;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    JSONParser jsonParser = new JSONParser();

    EditText firstname,middlename,lastname,email,mobile,password,confirmpassword;

    Button register_submit;


    private static String url_registration = "http://192.168.0.103/IdeaMaxima/registrationjson.php";

    private static final String TAG_SUCCESS = "success";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname = (EditText) findViewById(R.id.firstname);
        middlename = (EditText) findViewById(R.id.middlename);
        lastname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);

        register_submit=(Button)findViewById(R.id.register_submit);
        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateRegistration().execute();
            }
        });
    }
    private class CreateRegistration extends AsyncTask<String, String, String> {
        String fname = firstname.getText().toString();
        String mname = middlename.getText().toString();
        String lname = lastname.getText().toString();
        String mail = email.getText().toString();
        String no = mobile.getText().toString();
        String pwd = password.getText().toString();
        String confirm_password = confirmpassword.getText().toString();


        @Override
        protected String doInBackground(String... arg0) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("firstname", fname));
            params.add(new BasicNameValuePair("middlename", mname));
            params.add(new BasicNameValuePair("lastname", lname));
            params.add(new BasicNameValuePair("email", mail));
            params.add(new BasicNameValuePair("mobile", no));
            params.add(new BasicNameValuePair("password", pwd));
            params.add(new BasicNameValuePair("confirmpassword", confirm_password));


            JSONObject json = jsonParser.makeHttpRequest(url_registration, "POST", params);

            Log.d("Create Response", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Toast toast=Toast.makeText(getApplicationContext(),"Registraion Succesfully",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                    Intent i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);

                    finish();
                } else {

                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}