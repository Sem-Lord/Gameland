package com.example.a1024.Activity_TopQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.a1024.Models_TopQuiz.User;
import com.example.a1024.R;

public class MainActivity2_TopQuiz extends AppCompatActivity {

    private User mUser;
    private SharedPreferences mPreferences;
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    MediaPlayer player;

    private final int interval = 5000; // 5 Second
    private final int interval1 = 2000;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        public void run() {
            Intent gameActivity = new Intent(MainActivity2_TopQuiz.this, MainActivity_TopQuiz.class);
            startActivity(gameActivity);

            finish();
        }
    };

    private Runnable runnable1 = new Runnable(){
        public void run() {

            player.stop();
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__top_quiz);

        player = MediaPlayer.create(this, R.raw.baguette);
        player.start();

        //thread pour lancer l'activite suivante
        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);

        //thread pour stopper la musique
        handler.postAtTime(runnable1, System.currentTimeMillis()+interval1);
        handler.postDelayed(runnable1, interval1);

    }

}