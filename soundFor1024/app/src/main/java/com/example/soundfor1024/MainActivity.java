package com.example.soundfor1024;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static com.example.soundfor1024.R.drawable.icon_sound;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer musicPlayer;
    private MediaPlayer clickPlayer;
    private MediaPlayer swooshPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.musicPlayer = MediaPlayer.create(getApplicationContext(),R.raw.music_sound);
        this.clickPlayer = MediaPlayer.create(getApplicationContext(),R.raw.clic_sound);
        this.swooshPlayer = MediaPlayer.create(getApplicationContext(),R.raw.swoosh_sound);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    public void buttonMusic(View view) {

        ImageButton button = (ImageButton) view;

        if(musicPlayer.isPlaying()){
            musicPlayer.pause();
            button.setImageResource(R.drawable.icon_sound);
        }
        else{
            musicPlayer.start();
            button.setImageResource(R.drawable.icon_music);
        }

        }

    public void buttonClick(View view) {
        clickPlayer.start();
    }

    public void buttonSwoosh(View view) {
        swooshPlayer.start();
    }


    public void buttonActivateSon(View view) {
    }
}