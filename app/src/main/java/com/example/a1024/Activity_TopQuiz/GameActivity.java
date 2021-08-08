package com.example.a1024.Activity_TopQuiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1024.R;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer victoire;
    private MediaPlayer buzz;


    private TextView mQuestionTextView;
    private Button mAnswerButton1;
    private Button mAnswerButton2;
    private Button mAnswerButton3;
    private Button mAnswerButton4;

    private com.example.a1024.Models_TopQuiz.QuestionBank mQuestionBank;
    private com.example.a1024.Models_TopQuiz.Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    private boolean mEnableTouchEvents;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity::onCreate()");

        mQuestionBank = this.generateQuestions();

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 4;
        }

        mEnableTouchEvents = true;

        // Wire widgets
        mQuestionTextView = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswerButton1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswerButton2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswerButton3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswerButton4 = (Button) findViewById(R.id.activity_game_answer4_btn);

        //variable des chanson
        this.victoire = MediaPlayer.create(getApplicationContext(),R.raw.victoire);
        this.buzz = MediaPlayer.create(getApplicationContext(),R.raw.buzz);

        // Use the tag property to 'name' the buttons
        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        mAnswerButton1.setOnClickListener((View.OnClickListener) this);
        mAnswerButton2.setOnClickListener((View.OnClickListener) this);
        mAnswerButton3.setOnClickListener((View.OnClickListener) this);
        mAnswerButton4.setOnClickListener((View.OnClickListener) this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }

    //condition si la reponse selectionner est la bonne
    public void onClick(final View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer

            //v.setBackgroundColor(Color.BLACK);

            btn_color_green(v);

            Toast.makeText(this, "Correcte", Toast.LENGTH_SHORT).show();
            mScore++;
            victoire.start();

        } else {
            // Wrong answer
            //v.setBackgroundColor(Color.RED);
            btn_color_red(v);
            Toast.makeText(this, "Mauvaise reposne!", Toast.LENGTH_SHORT).show();
            buzz.start();
        }

        //v.setBackgroundColor(Color.WHITE);
        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mEnableTouchEvents = true;

                // Si c'est la derniere question arreter la partie.
                // Sinon afficher la procahine question.
                if (--mNumberOfQuestions == 0) {
                    // End the game
                    btn_color_white(v);
                    endGame();
                } else {
                    btn_color_white(v);
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);

                }
            }
        }, 2000); // LENGTH_SHORT is usually 2 second long
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }


    private void btn_color_red(View btn){
        btn.setBackgroundColor(Color.RED);
    }

    private void btn_color_white(View btn){
        btn.setBackgroundColor(Color.WHITE);
    }

    private void btn_color_green(View btn){
        btn.setBackgroundColor(Color.GREEN);
    }

    //boite de dialogue a la fin de la partie
    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Fin de la partie!")
                .setMessage("Votre score est de " + mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void displayQuestion(final com.example.a1024.Models_TopQuiz.Question question) {
        mQuestionTextView.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));
    }

    //liste des questions
    private com.example.a1024.Models_TopQuiz.QuestionBank generateQuestions() {
        com.example.a1024.Models_TopQuiz.Question question1 = new com.example.a1024.Models_TopQuiz.Question("Comment s'appelle le preident actuel de la France?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        com.example.a1024.Models_TopQuiz.Question question2 = new com.example.a1024.Models_TopQuiz.Question("Combien de pays a t'il dans l'union européenne?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        com.example.a1024.Models_TopQuiz.Question question3 = new com.example.a1024.Models_TopQuiz.Question("Qui a créer le syteme android?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        com.example.a1024.Models_TopQuiz.Question question4 = new com.example.a1024.Models_TopQuiz.Question("En quelle année le premier voyage sur la lune a été faite?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        com.example.a1024.Models_TopQuiz.Question question5 = new com.example.a1024.Models_TopQuiz.Question("Quel est la capital de la Roomanie?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        com.example.a1024.Models_TopQuiz.Question question6 = new com.example.a1024.Models_TopQuiz.Question("Qui est l'auteur de la peinture Mona Lisa?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        com.example.a1024.Models_TopQuiz.Question question7 = new com.example.a1024.Models_TopQuiz.Question("Qui est le meilleur chanteur de R&B de tout les temps?",
                Arrays.asList("Sagbohan", "Chris Brown", "Bruno Mars", "Booder"),
                1);

        com.example.a1024.Models_TopQuiz.Question question8 = new com.example.a1024.Models_TopQuiz.Question("Quel est le code iso de la Belgique?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        com.example.a1024.Models_TopQuiz.Question question9 = new com.example.a1024.Models_TopQuiz.Question("Qui est l'auteur du titre \"Go crazy\"??",
                Arrays.asList("Chris Brown", "Khalid", "Sem-Lord", "Toviho"),
                0);

        com.example.a1024.Models_TopQuiz.Question question10= new com.example.a1024.Models_TopQuiz.Question("Quel est le deuxième prénom d'Elvis Presley??",
                Arrays.asList("Aaron", "John", "Harry", "Jacque"),
                0);

        com.example.a1024.Models_TopQuiz.Question question11 = new com.example.a1024.Models_TopQuiz.Question("Qui est le chanteur de The Counting Crows??",
                Arrays.asList("Chris Brown", "Adam Duritz", "John Legend", "Lionel Richie"),
                1);

        com.example.a1024.Models_TopQuiz.Question question12 = new com.example.a1024.Models_TopQuiz.Question("Qui était la reine de la soul?",
                Arrays.asList("Sharon Djones", "Nana Mouskouri", "Aretha Franklin", "Alicia Keys"),
                2);

        com.example.a1024.Models_TopQuiz.Question question13 = new com.example.a1024.Models_TopQuiz.Question("Quel groupe célèbre était autrefois connu sous le nom de The Quarrymen?",
                Arrays.asList("Nirvana", "Indochine", "One direction", "The Beatles"),
                3);

        com.example.a1024.Models_TopQuiz.Question question14 = new com.example.a1024.Models_TopQuiz.Question("Quel était le nom du chanteur principal d'AC / DC décédé en 1980?",
                Arrays.asList("Bon Scott", "Harry", "James Brown", "Michael Jackson"),
                0);

        com.example.a1024.Models_TopQuiz.Question question15 = new com.example.a1024.Models_TopQuiz.Question("Comment s'appelle le createur de McAfee?",
                Arrays.asList("John Mcafee", "Steeve Mcafee", "Benoit McAfee", "James MacAfee"),
                0);

        com.example.a1024.Models_TopQuiz.Question question16 = new com.example.a1024.Models_TopQuiz.Question("En quelle année Maradona a marquée son fameu but avec la main?",
                Arrays.asList("1980", "1984", "1986", "1990"),
                2);

        com.example.a1024.Models_TopQuiz.Question question17 = new com.example.a1024.Models_TopQuiz.Question("Combien de minute dure un match de ruby?",
                Arrays.asList("90", "80", "60", "100"),
                1);

        com.example.a1024.Models_TopQuiz.Question question18 = new com.example.a1024.Models_TopQuiz.Question("Dans quel pays s'est tenu les premiers jeux olymppiques?",
                Arrays.asList("Italie", "Angleterre", "Grece", "Suisse"),
                2);

        com.example.a1024.Models_TopQuiz.Question question19 = new com.example.a1024.Models_TopQuiz.Question("Combien de match a perdu Mohamed Ali dans toutes sa carriere?",
                Arrays.asList("4", "3", "1", "0"),
                3);


        com.example.a1024.Models_TopQuiz.Question question20 = new com.example.a1024.Models_TopQuiz.Question("Combien de mettre de profondeur mesure les piscine durant les jeux olympique?",
                Arrays.asList("15", "10", "7", "5"),
                3);

        com.example.a1024.Models_TopQuiz.Question question21 = new com.example.a1024.Models_TopQuiz.Question("Combien de fois Michael Schumacher a t'il été champions de formule 1?",
                Arrays.asList("4", "7", "8", "12"),
                1);

        com.example.a1024.Models_TopQuiz.Question question22 = new com.example.a1024.Models_TopQuiz.Question("Quel équipe a été champions NBA en 2020?",
                Arrays.asList("Miami Heat", "Lakers LA", "LS Lakers", "Portland"),
                1);

        com.example.a1024.Models_TopQuiz.Question question23 = new com.example.a1024.Models_TopQuiz.Question("Qui à remporté le plus de grammy awards en 1980?",
                Arrays.asList("Elvis Presley", "Micheal Jackson", "Whitney Houston", "Beatles"),
                1);

        com.example.a1024.Models_TopQuiz.Question question24 = new com.example.a1024.Models_TopQuiz.Question("Quel était la nationalité de Mozart?",
                Arrays.asList("Australien", "Suisse", "Norvegiens", "Arméniene"),
                0);

        com.example.a1024.Models_TopQuiz.Question question25 = new com.example.a1024.Models_TopQuiz.Question("Qui est Tina Snow?",
                Arrays.asList("Nicki Minaj", "Black China", "Cardi B", "Megan the Stallion"),
                3);

        com.example.a1024.Models_TopQuiz.Question question26 = new com.example.a1024.Models_TopQuiz.Question("Qui à écrit le livre Mobby Dick?",
                Arrays.asList("Herman Melville", "Hermanne Melville", "Heman Melville", "Hemanne Melville"),
                0);

        com.example.a1024.Models_TopQuiz.Question question27 = new com.example.a1024.Models_TopQuiz.Question("Qui à ecrit le livre \"Jungle Book\"?",
                Arrays.asList("Rudyard Kipling", "Emili Zola", "Denis Diderot", "Victor Hugot"),
                0);

        com.example.a1024.Models_TopQuiz.Question question28 = new com.example.a1024.Models_TopQuiz.Question("Quelle est le nom du petit dragon dans Mulan?",
                Arrays.asList("Mushu", "Mush", "Happy", "Mushui"),
                0);

        com.example.a1024.Models_TopQuiz.Question question29 = new com.example.a1024.Models_TopQuiz.Question("Qui était l'acteur principal de Scarface en 1983?",
                Arrays.asList("Belmondo", "DeparDieu", "Al Pacino", "Daniel Craig"),
                0);

        com.example.a1024.Models_TopQuiz.Question question30 = new com.example.a1024.Models_TopQuiz.Question("C'est quoi la definition du sigle FAI?",
                Arrays.asList("Fournisseur d'accès a internet", "Frais d'agence inclus", "Frais aromtisé indolore", "Fonctionnaire de l'accompagenment intellectuel"),
                0);



        return new com.example.a1024.Models_TopQuiz.QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9, question10, question11, question12, question13, question14, question15, question16,
                question17, question18, question19, question20, question21, question22, question23, question24, question25,
                question26, question27, question28, question29, question30));
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");
    }
}