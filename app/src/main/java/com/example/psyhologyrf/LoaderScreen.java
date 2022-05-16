package com.example.psyhologyrf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.psyhologyrf.ui.SayWhithTime;

public class LoaderScreen extends AppCompatActivity {

    private ConstraintLayout loaderscreen;
    SayWhithTime sayWhithTime = new SayWhithTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_screen);
        loaderscreen  = (findViewById(R.id.loaderscreen));
        sayWhithTime.SetcolorWithCurrentTime(loaderscreen);
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