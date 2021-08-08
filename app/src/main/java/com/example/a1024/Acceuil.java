package com.example.a1024;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.a1024.Adapter.PageAdapter;
import com.google.android.material.tabs.TabLayout;

public class Acceuil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        //3 - Configure ViewPager
        this.configureViewPagerAndTabs();

    }

    public void click (){
        MediaPlayer player;

        player = MediaPlayer.create(this, R.raw.click);
        player.start();
    }

    public void echec (){
        MediaPlayer player;

        player = MediaPlayer.create(this, R.raw.echec);
        player.stop();
    }

    public void stop () {
        MediaPlayer player;

        player = MediaPlayer.create(this, R.raw.baguette);
        player.stop();
    }

    private void configureViewPagerAndTabs(){
        //Get ViewPager from layout
        ViewPager pager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        // 1 - Get TabLayout from layout
        TabLayout tabs= (TabLayout)findViewById(R.id.activity_main_tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // 3 - Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
}