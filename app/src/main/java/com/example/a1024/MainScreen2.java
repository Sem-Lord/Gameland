package com.example.a1024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainScreen2 extends AppCompatActivity {

    private ImageButton btn_play;
    private ImageButton btn_ranking;
    private ImageButton btn_htp;
    private ImageButton btn_setting;
    private ImageButton btn_profil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen2);

        btn_play = (ImageButton)findViewById(R.id.btn_play);
        btn_ranking = (ImageButton)findViewById(R.id.btn_ranking);
        btn_htp = (ImageButton)findViewById(R.id.btn_rules);
        btn_setting = (ImageButton)findViewById(R.id.btn_setting);
        btn_profil = (ImageButton)findViewById(R.id.btn_profil);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen2.this, Play_screen2.class);
                startActivity(i);
            }
        });

        btn_htp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen2.this, HowtoPlay.class);
                startActivity(i);

            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen2.this, Setting.class);
                startActivity(i);

            }
        });

        btn_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen2.this, Ranking.class);
                startActivity(i);

            }
        });

        btn_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen2.this, ProfileActivity.class);
                startActivity(i);

            }
        });

    }
}