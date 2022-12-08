package com.example.psyhologyrf;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.psyhologyrf.ui.SayWhithTime;

import java.util.ArrayList;
import java.util.Random;

public class LoaderScreen extends AppCompatActivity {

    private ConstraintLayout loaderscreen;
   // SayWhithTime sayWhithTime = new SayWhithTime();
    private static final TimeInterpolator GAUGE_ANIMATION_INTERPOLATOR = new DecelerateInterpolator(2);
    private static final int MAX_LEVEL = 100;
    private static final long GAUGE_ANIMATION_DURATION = 1000;
    private ProgressBar pb_horizontal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_screen);
        loaderscreen  = (findViewById(R.id.loaderscreen));
        pb_horizontal = (findViewById(R.id.pb_horizontal));
        ImageView imageView = findViewById(R.id.imageView2);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.ptn_5);
        images.add(R.drawable.ptn_3);
        images.add(R.drawable.ptn_2);

        int index = new Random().nextInt(images.size());
        int image = images.get(index);
        imageView.setImageResource(image);



        ObjectAnimator animator = ObjectAnimator.ofInt(pb_horizontal, "progress", 0, MAX_LEVEL);

        animator.setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
        animator.setDuration(GAUGE_ANIMATION_DURATION);
        animator.start();

       // sayWhithTime.SetcolorWithCurrentTime(loaderscreen);
        Thread thread =new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();



    }
}