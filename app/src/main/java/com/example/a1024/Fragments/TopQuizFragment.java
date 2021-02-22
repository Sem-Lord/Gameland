package com.example.a1024.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a1024.Acceuil;
import com.example.a1024.Activity_TopQuiz.MainActivity2_TopQuiz;
import com.example.a1024.R;


public class TopQuizFragment extends Fragment {

    private Button btn_commencer;

    public TopQuizFragment() {
        // Required empty public constructor
    }

    public static TopQuizFragment newInstance() {
        TopQuizFragment fragment = new TopQuizFragment();

        return fragment;
    }


    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // associer le fichire xml correspondant
        View view = inflater.inflate(R.layout.fragment_top_quiz, container, false);
        btn_commencer = (Button) view.findViewById(R.id.btn_commencer_topquiz);

        btn_commencer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent topQuizActivity = new Intent(getActivity(), MainActivity2_TopQuiz.class);
                startActivity(topQuizActivity);

            }
        });

        return view;
    }
}