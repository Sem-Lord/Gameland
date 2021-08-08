package com.example.a1024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Demarrage extends AppCompatActivity {

    private final int interval = 3000; // 1 Second
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        public void run() {
            Intent gameActivity = new Intent(Demarrage.this, Acceuil.class);
            startActivity(gameActivity);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demarrage);

        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);
    }
}