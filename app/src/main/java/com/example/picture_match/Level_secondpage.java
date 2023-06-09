package com.example.picture_match;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Level_secondpage extends AppCompatActivity{

        ListView listView;
        Level_secondpage_Adapter adapter;
        int[] levelno = {1,2,3,4,5,6,7,8,9,10};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_level_secondpage);

            listView = findViewById(R.id.secondpage_listview);
            adapter = new Level_secondpage_Adapter(Level_secondpage.this,levelno);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(Level_secondpage.this,Gridview.class);
                    startActivity(intent);
                }
            });
        }


}
