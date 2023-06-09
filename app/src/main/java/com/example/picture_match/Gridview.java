package com.example.picture_match;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gridview extends AppCompatActivity {

        GridView gridView;
        GridviewAdapter adapter;
        private ArrayList<String> imgArr=new ArrayList<>();
        private List<String> arrayList=new ArrayList<>();
        TextView countdown;
        ProgressBar progressBar;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gridview);
            countdown=findViewById(R.id.time);
            gridView = findViewById(R.id.gridview);
            progressBar=findViewById(R.id.progress);

            String[] images = new String[0];
            try {
                images = getAssets().list("images/");
                imgArr = new ArrayList<String>(Arrays.asList(images));
            } catch (IOException e) {
                e.getLocalizedMessage();
            }
            Collections.sort(imgArr);
            imgArr.remove("a1.png");
            imgArr.remove("android-logo-shine.png");
            imgArr.remove("android-logo-mask.png");
            imgArr.remove("progress_font.png");
            //Log.d("TTT", "getImage: All Images= "+imgArr);
            arrayList= imgArr.subList(0,6);

            arrayList.addAll(arrayList);
            Collections.shuffle(arrayList);
            Collections.shuffle(arrayList);
            Log.d("TTT", "getImage: Selected Images= "+arrayList);


            adapter = new GridviewAdapter(Gridview.this, arrayList,countdown,progressBar);
            gridView.setAdapter(adapter);

        }
    }