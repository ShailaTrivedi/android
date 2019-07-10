package com.example.bharti.ideamax;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
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


public class RegActivity extends AppCompatActivity {

    JSONParser jsonParser = new JSONParser();
    private static String url_registration = "http://192.168.43.150/IdeaMaxima/registrationjson.php";

    Button register_submit;
    private String firstname,middlename,lastname,email,mobile,password,confirmpassword;
    private EditText nEdtTextFirstName;
    private EditText nEdtTextMiddleName;
    private EditText nEdtTextLastName;
    private EditText nEdtTextEmail;
    private EditText nEdtTextMobile;
    private EditText nEdtTextPassword;
    private EditText nEdtTexConfirmPassword;
    private Button nbtn;

    private static final String TAG_SUCCESS = "success";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        setTitle("Register Here");
        nEdtTextFirstName = (EditText) findViewById(R.id.firstname);
        nEdtTextMiddleName = (EditText) findViewById(R.id.middlename);
        nEdtTextLastName = (EditText) findViewById(R.id.lastname);
        nEdtTextEmail = (EditText) findViewById(R.id.email);
        nEdtTextMobile = (EditText) findViewById(R.id.mobile);
        nEdtTextPassword = (EditText) findViewById(R.id.password);
        nEdtTexConfirmPassword = (EditText) findViewById(R.id.confirmpassword);

        nbtn=(Button)findViewById(R.id.register_submit);
        nbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initial();
                if (!validate()) {
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                } else {
                    new CreateRegistration().execute();
                }

            }
        });

    }
    public void initial(){
        firstname = nEdtTextFirstName.getText().toString().trim();
        middlename = nEdtTextMiddleName.getText().toString().trim();
        lastname = nEdtTextLastName.getText().toString().trim();
        email = nEdtTextEmail.getText().toString().trim();
        mobile = nEdtTextMobile.getText().toString().trim();
        password= nEdtTextPassword.getText().toString().trim();
        confirmpassword= nEdtTexConfirmPassword.getText().toString().trim();
    }
    public boolean validate() {
        boolean valid = true;
        if (firstname.isEmpty() || firstname.length() <= 1 || firstname.length() > 32) {
            nEdtTextFirstName.setError("Please Enter A Valid name");
            valid = false;
        }
        if (middlename.isEmpty() || middlename.length() <= 1|| middlename.length() > 32) {
           nEdtTextMiddleName.setError("Please Enter A Valid name");
            valid = false;
        }
        if (lastname.isEmpty() || lastname.length() <= 1 || lastname.length() > 32) {
            nEdtTextLastName.setError("Please Enter A Valid name");
            valid = false;
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            nEdtTextEmail.setError("Please Enter A Valid Email");
            valid = false;
        }
        if (mobile.isEmpty() || mobile.length() > 10 || mobile.length() < 10) {
            nEdtTextMobile.setError("Please Enter A Valid mobile");
            valid = false;
        }
        if (password.isEmpty() || password.length() <= 4 || password.length() > 32) {
            nEdtTextPassword.setError("Please Enter A Valid Password");
            valid = false;
        }
        if (confirmpassword.isEmpty() || confirmpassword.length() <= 4 || confirmpassword.length() > 32) {
            nEdtTexConfirmPassword.setError("Please Enter A Same Password");
            valid = false;
        }


        return valid;
    }
    public void onBackPressed() {
        System.exit(0);
    }
    class CreateRegistration extends AsyncTask<String, String,String>
    {
        String firstname = nEdtTextFirstName.getText().toString();
        String middlename = nEdtTextMiddleName.getText().toString();
        String lastname = nEdtTextLastName.getText().toString();
        String email = nEdtTextEmail.getText().toString();
        String mobile = nEdtTextMobile.getText().toString();
        String password = nEdtTextPassword.getText().toString();
        String confirmpassword = nEdtTexConfirmPassword.getText().toString();



        @Override
        protected String doInBackground(String... arg0) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("firstname",firstname));
            params.add(new BasicNameValuePair("middlename",middlename));
            params.add(new BasicNameValuePair("lastname",lastname));
            params.add(new BasicNameValuePair("email",email));
            params.add(new BasicNameValuePair("mobile",mobile));
            params.add(new BasicNameValuePair("password",password));
            params.add(new BasicNameValuePair("confirmpassword",confirmpassword));


            JSONObject json = jsonParser.makeHttpRequest(url_registration, "POST", params);

            Log.d("Create Response", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    Intent i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);

                } else {

                    Toast.makeText(RegActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

}
