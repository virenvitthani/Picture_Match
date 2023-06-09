package com.example.picture_match;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Level_secondpage_Adapter extends BaseAdapter {

    Level_secondpage level_secondpage;
    int[] levelno;

    public Level_secondpage_Adapter(Level_secondpage level_secondpage, int[] levelno) {
        this.level_secondpage = level_secondpage;
        this.levelno = levelno;
    }

    @Override
    public int getCount() {
        return levelno.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(level_secondpage).inflate(R.layout.levelpage_item,parent,false);

        TextView textView = convertView.findViewById(R.id.levelpage_txtview);
        textView.setText("LEVEL"+levelno[position]);
        return convertView;
    }
}
