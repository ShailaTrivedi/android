package com.example.bharti.ideamax;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Button button,feedbacksubmit,postideasubmit,reg;
    private TextView name;
    private String name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Google.class);
                startActivity(i);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
//    public void invokesetting() {
//
//        Intent i = new Intent(getApplicationContext(), Setting.class);
//        startActivity(i);
//
//    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            Intent in = new Intent(MainActivity.this,Setting.class);
            startActivity(in);
        }
        if(id == R.id.logout)
        {
            final DialogInterface.OnClickListener dialouge=new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            Intent intent = new Intent(MainActivity.this, Login.class);
                            startActivity(intent);
                            finish();
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are You Sure?").setPositiveButton("Yes",dialouge).setNegativeButton("No",dialouge).show();

        }


        return super.onOptionsItemSelected(item);
    }

    public void postidea(){

        Fragment fragment = new PostIdea();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.cony,fragment);
        fragmentTransaction.commit();



    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.home) {
            Fragment fragment = new HomeFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.cony,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.calender) {
            Fragment fragment = new CalenderFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.cony,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.postidea) {
            Fragment fragment = new PostIdea();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.cony,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.viewidea) {
            Intent i=new Intent(MainActivity.this,ViewIdeaActivity.class);
            startActivity(i);

        } else if (id == R.id.shareidea) {
            Fragment fragment = new ShareIdeaFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.cony,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.viewshareidea) {
            Intent i=new Intent(MainActivity.this,ShareViewIdeaActivity.class);
            startActivity(i);

        } else if (id == R.id.about) {
            Fragment fragment = new AboutFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.cony,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.contactus) {
            Fragment fragment = new ContactFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.cony,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.feedback) {
            Fragment fragment = new Feedback();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.cony,fragment);
            fragmentTransaction.commit();


        } else if (id == R.id.help) {
            Fragment fragment = new HelpFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.cony,fragment);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
