package com.example.picture_match;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
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

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Level_secondpage.this);
                    builder1.setTitle("\tTIME: NO TIME LIMIT");
                    builder1.setMessage("YOU HAVE 5 SECONDS TO MEMORIZE ALL IMAGES");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Go",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Level_secondpage.this,Gridview.class);
                                    startActivity(intent);
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });
        }


}
