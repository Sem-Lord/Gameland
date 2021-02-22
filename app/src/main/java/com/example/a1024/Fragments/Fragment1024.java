package com.example.a1024.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.example.a1024.Activity_TopQuiz.MainActivity2_TopQuiz;
import com.example.a1024.Connexion;
import com.example.a1024.Demarrage;
import com.example.a1024.MainScreen2;
import com.example.a1024.R;


public class Fragment1024 extends Fragment {

    private Button btn_commencer_1024;

    public Fragment1024() {
        // Required empty public constructor
    }

    public static Fragment1024 newInstance() {
        Fragment1024 fragment = new Fragment1024();

        return fragment;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // associer le fichire xml correspondant
        View view = inflater.inflate(R.layout.fragment_1024, container, false);
        btn_commencer_1024 = (Button) view.findViewById(R.id.btn_commencer_1024);

        btn_commencer_1024.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent topQuizActivity = new Intent(getActivity(), Connexion.class);
                startActivity(topQuizActivity);
                Log.e("succes", "envoyer");

            }
        });

        return view;
    }


}