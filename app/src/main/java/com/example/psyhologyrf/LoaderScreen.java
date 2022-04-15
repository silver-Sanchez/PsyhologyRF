package com.example.psyhologyrf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoaderScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_screen);


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