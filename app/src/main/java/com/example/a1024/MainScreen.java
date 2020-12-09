package com.example.a1024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    private Button btn_play;
    private Button btn_ranking;
    private Button btn_htp;
    private Button btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        btn_play = (Button)findViewById(R.id.btn_play);
        btn_ranking = (Button)findViewById(R.id.btn_ranking);
        btn_htp = (Button)findViewById(R.id.btn_htp);
        btn_setting = (Button)findViewById(R.id.btn_setting);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen.this, Play_screen2.class);
                startActivity(i);
            }
        });

        btn_htp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen.this, HowtoPlay.class);
                startActivity(i);

            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen.this, Setting.class);
                startActivity(i);

            }
        });

        btn_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen.this, Ranking.class);
                startActivity(i);

            }
        });


    }
}