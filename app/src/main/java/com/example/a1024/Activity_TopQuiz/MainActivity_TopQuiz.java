package com.example.a1024.Activity_TopQuiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1024.Models_TopQuiz.User;
import com.example.a1024.R;

public class MainActivity_TopQuiz extends AppCompatActivity {



    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";
    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    private SharedPreferences mPreferences;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    public final String PREF_KEY_SCORE = "PREF_KEY_SCORE";

    public static String firstname_pref = "";
    //public int score_pref = 0;

    private final int interval = 2000; // 2 Second
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        public void run() {
            Toast.makeText(MainActivity_TopQuiz.this, "Bienvenue dans TopQuiz!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();

            int score_pref = mPreferences.getInt(PREF_KEY_SCORE, score);
            System.out.println(score_pref);

            firstname_pref = mPreferences.getString(PREF_KEY_FIRSTNAME, mUser.getFirstname());
            mGreetingText.setText("Oooh " + firstname_pref + " vous nous quitter deja!\nDommage Votre score a été de " + score_pref);

            mNameInput.setText(firstname_pref);
            mNameInput.setSelection(mNameInput.length());
            System.out.println(firstname_pref);

            greetUser();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__top_quiz);

        mUser = new User();

        mPreferences = getPreferences(MODE_PRIVATE);

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);

        mPlayButton.setEnabled(false);



        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This is where we'll check the user input
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //mPlayButton.setBackgroundColor(0xFF00FF00);

            }
        });

        greetUser();

        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = mNameInput.getText().toString();
                mUser.setFirstname(firstname);

                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstname()).apply();

                // User clicked the button
                Intent gameActivity = new Intent(MainActivity_TopQuiz.this, GameActivity.class);
                startActivityForResult(gameActivity, GAME_ACTIVITY_REQUEST_CODE);

            }
        });

    }

    private void greetUser() {
        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);

        if (null != firstname) {
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

            String fulltext = "Bon retour, Ca faisait un bail, " + firstname
                    + "!\nVotre dernier score etait de " + score
                    + ", Feriez-vous cette fois?";
            mGreetingText.setText(fulltext);
            mNameInput.setText(firstname);
            mNameInput.setSelection(firstname.length());
            mPlayButton.setEnabled(true);
        }
    }

}