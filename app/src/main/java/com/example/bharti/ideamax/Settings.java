package com.example.bharti.ideamax;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {Context context;
    ListView list;
    ArrayList<String> name;
    ArrayList<Integer>image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context = this;
        name = new ArrayList<String>();
        name.add("Account");
        name.add("Notification");
        name.add("Contacts");
        name.add("About");
        name.add("Help");
        image = new ArrayList<Integer>();
        image.add(R.drawable.account);
        image.add(R.drawable.notification);
        image.add(R.drawable.contact);
        image.add(R.drawable.aboutus);
        image.add(R.drawable.help);

        list = (ListView)findViewById(R.id.listView1);
        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);
        
    }

    class MyAdapter extends BaseAdapter {
        LayoutInflater lif;
        public MyAdapter() {
            lif=(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return name.size();
        }
        @Override
        public Object getItem(int arg0) {
            return null;
        }
        @Override
        public long getItemId(int arg0) {
            return 0;}
        @Override
        public View getView(int position, View convertview, ViewGroup arg2) {
            if(convertview == null){
                convertview = lif.inflate(R.layout.row,null);
            }
            TextView t = (TextView)convertview.findViewById(R.id.textView1);
            t.setText(name.get(position));
            ImageView img = (ImageView)convertview.findViewById(R.id.imageView1);
            img.setImageResource(image.get(position));
            return convertview;
        }   }

    }


