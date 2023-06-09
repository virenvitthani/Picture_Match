package com.example.picture_match;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

    public class GridviewAdapter extends BaseAdapter {
        Gridview gridview;
        List<String> arrayList = new ArrayList<>();
        TextView countdown;
        private long delaytime = 5000;
        private Runnable runnable;
        int click = 1, pos1 = 0, pos2 = 0;
        private View firstview;
        ProgressBar progressBar;


        public GridviewAdapter(Gridview grid_page, List<String> arrayList, TextView countdown, ProgressBar progressBar) {
            this.gridview = grid_page;
            this.arrayList = arrayList;
            this.countdown = countdown;
            this.progressBar=progressBar;
        }

        @Override
        public int getCount() {
            return arrayList.size();
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

            convertView = LayoutInflater.from(gridview).inflate(R.layout.griditem, parent, false);
            ImageView imageView = convertView.findViewById(R.id.gridimg);
            InputStream inputStream = null;
            try {
                inputStream = gridview.getAssets().open("images/" + arrayList.get(position));
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                imageView.setImageDrawable(drawable);
                inputStream.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            RelativeLayout relativeLayout = convertView.findViewById(R.id.relative);
            View mask = convertView.findViewById(R.id.mask);
            //playGame(relativeLayout,mask);
            new CountDownTimer(delaytime, 1000) {
                @Override
                public void onTick(long l) {
                    int interval = (int) (l / 1000);
                    countdown.setText("Time: " + interval + "/0");
                    Log.d("TTT", "onTick: " + interval);
                }
                @Override
                public void onFinish() {
                    //startGame(mask, relativeLayout, position, arrayList);
                    progressBar.setMax(10000);
                    new CountDownTimer(10000, 1000) {
                        @Override
                        public void onTick(long l) {
                            int time = (int) (l / 1000);
                            progressBar.setProgress(progressBar.getMax()-time);
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                    startGame(mask, relativeLayout, position, arrayList);

                }




            }.start();
            return convertView;

        }
        private void startGame(View mask, RelativeLayout relativeLayout, int position, List<String> arrayList)
        {
            int interval=100;
            Handler handler = new Handler();
            runnable = new Runnable() {
                public void run() {
                    mask.setVisibility(View.VISIBLE);
                }
            };
            //handler.postAtTime(runnable, System.currentTimeMillis() + interval);
            handler.postDelayed(runnable, interval);
            relativeLayout.setOnClickListener(v -> {
                if (click == 1) {
                    mask.setVisibility(View.INVISIBLE);
                    pos1 = position;//0
                    firstview = mask;
                    click = 3;

                    runnable = new Runnable() {
                        public void run() {
                            click = 2;
                        }
                    };
                    handler.postAtTime(runnable, System.currentTimeMillis() + interval);
                    handler.postDelayed(runnable, 100);
                    System.out.println("first click");
                }
                if (click == 2) {
                    mask.setVisibility(View.INVISIBLE);
                    pos2 = position;//5
                    click = 3;
                    System.out.println("second click");
                    if (arrayList.get(pos1).equals(arrayList.get(pos2))) {
                        System.out.println("match");
                        runnable = new Runnable() {
                            public void run() {
                                click = 1;
                            }
                        };
                        handler.postAtTime(runnable, System.currentTimeMillis() + interval);
                        handler.postDelayed(runnable, 500);
                    } else {
                        System.out.println("not match");
                        runnable = new Runnable() {
                            public void run() {
                                mask.setVisibility(View.VISIBLE);
                                firstview.setVisibility(View.VISIBLE);
                                click = 1;
                            }
                        };
                        handler.postAtTime(runnable, System.currentTimeMillis() + interval);
                        handler.postDelayed(runnable, 500);
                    }
                }
            });
        }
    }


