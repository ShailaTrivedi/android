package com.example.bharti.ideamax;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

        import android.app.Dialog;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
import android.util.Patterns;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.UnsupportedEncodingException;
        import java.util.ArrayList;
        import java.util.List;

import static com.example.bharti.ideamax.JSONParser.jObj;

public class Login extends ActionBarActivity {
    private String email, password;
    private EditText mEdtTextUserName;
    private EditText mEdtTextUserPass;

    public static final String USER_NAME = "email";

    SessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOGIN");

        // Session Manager
        session = new SessionManager(getApplicationContext());

        mEdtTextUserName = (EditText) findViewById(R.id.email);
        mEdtTextUserPass = (EditText) findViewById(R.id.password);
        Button submit = (Button) findViewById(R.id.login_submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initial();
                if (!validate()) {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                } else {

                    session.createLoginSession("Android Hive"
                            , "anroidhive@gmail.com");
                    AppPersistance.start(Login.this).save(AppPersistance.keys.USER_NAME, email);
                    login(email,password);
                }
            }

        });

        findViewById(R.id.reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, RegActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.loginfacebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Facebook.class);
                startActivity(i);
            }
        });
        findViewById(R.id.logingmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Google.class);
                startActivity(i);
            }
        });
    }

    public void initial(){
        email = mEdtTextUserName.getText().toString().trim();
        password = mEdtTextUserPass.getText().toString().trim();
    }
    public boolean validate() {
        boolean valid = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEdtTextUserName.setError("Please Enter A Valid Email");
            valid = false;
        }
        if (password.isEmpty() ) {
            mEdtTextUserPass.setError("Please Enter A Valid Password");
            valid = false;
        }
        return valid;
    }
    public void onBackPressed() {
        System.exit(0);
    }
    private void login(final String emailid, String password) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Login.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String email = params[0];
                String password = params[1];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://192.168.43.150/IdeaMaxima/loginjson.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                String s = result.trim();
                loadingDialog.dismiss();
                if(s.equalsIgnoreCase("success")){

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra(USER_NAME, email);

                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(email, password);

    }

}



