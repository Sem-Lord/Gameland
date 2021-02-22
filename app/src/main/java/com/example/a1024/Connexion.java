package com.example.a1024;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1024.Base.BaseActivity;
import com.example.a1024.Models.User.DataBaseManager1;
import com.example.a1024.Models.User.UserData;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;



import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class Connexion extends BaseActivity {

    private EditText edt_mail;
    private EditText edt_password;
    private CheckBox cb_politique;
    private Button btn_sign_in;
    private Button btn_sign_up;
    private Button btn_sign_face;

    private TextView tv_ignorer;

    private DataBaseManager1 databaseManager1;

    //FOR DATA
    // 1 - Identifier for Sign-In Activity
    private static final int RC_SIGN_IN = 123;

    // 1 - Get Coordinator Layout
    @BindView(R.id.main_activity_coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 4 - Handle SignIn Activity response on activity result
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }

   /* @Override
    protected void onResume() {
        super.onResume();
        // 5 - Update UI when activity is resuming
        this.updateUIWhenResuming();
    }*/


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        //this.updateUIWhenResuming();

        //recuperation des variable dans le xml
        /*edt_mail = (EditText) findViewById(R.id.edt_mail);
        edt_password = (EditText) findViewById(R.id.edt_password);

        cb_politique = (CheckBox) findViewById(R.id.cb_politique);

        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);*/

        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        /*btn_sign_face = (Button) findViewById(R.id.btn_sign_face);*/

        tv_ignorer = (TextView)findViewById(R.id.tv_ignorer);

        /*btn_sign_in.setEnabled(false);*/

        //bdd initalisation
        databaseManager1 = new DataBaseManager1( this );


        /*btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = edt_mail.getText().toString();
                String password = edt_password.getText().toString();
                List<UserData> users = databaseManager1.readUser(pseudo, password);

                if (users != null ){
                    Intent i = new Intent(Connexion.this, MainScreen.class);
                    startActivity(i);
                    Toast.makeText(Connexion.this, "Connexion reussir", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Connexion.this, "Mot de passe ou pseudo erroné", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        //clique le bouton sign up
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lancer l'activité d'inscription

                // 4 - Start appropriate activity
                if (isCurrentUserLogged()){
                    startProfileActivity();
                } else {
                    startSignInActivity();
                }
            }
        });

        //clique sur le textview ignorer
        tv_ignorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Connexion.this, MainScreen2.class);
                startActivity(i);

            }
        });



        /*btn_sign_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(Connexion.this, Login.class);
                    startActivity(i);
                    Toast.makeText(Connexion.this, "Connexion reussir", Toast.LENGTH_SHORT).show();
            }
        });*/


        //rendre le bouton enable des qu'il y a du saisir dans edittext
        /*edt_mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This is where we'll check the user input
                edt_password.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // This is where we'll check the user input
                        btn_sign_in.setEnabled(s.toString().length() != 0);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //mPlayButton.setBackgroundColor(0xFF00FF00);

                    }
                });

            }

            @Override
            public void afterTextChanged(Editable s) {
                //mPlayButton.setBackgroundColor(0xFF00FF00);
            }
        });*/


    }

    @Override
    public int getFragmentLayout() { return R.layout.activity_connexion; }


    // Launch Sign-In Activity
    private void startSignInActivity() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build());


        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                        .setLogo(R.drawable.image11)
                        .build(),
                RC_SIGN_IN);
    };



    // Show Snack Bar with a message
    /*private void showSnackBar(CoordinatorLayout coordinatorLayout, String message){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }*/



    // Method that handles response after SignIn Activity close
    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data){

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) { // SUCCESS
                //showSnackBar(this.coordinatorLayout, getString(R.string.connection_succeed));
                Toast.makeText(Connexion.this, "Authentification reussir", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Connexion.this, MainScreen2.class);
                startActivity(i);

            } else { // ERRORS
                if (response == null) {
                    //showSnackBar(this.coordinatorLayout, getString(R.string.error_authentication_canceled));
                    Toast.makeText(Connexion.this, "Authentification echoué", Toast.LENGTH_SHORT).show();

                } else if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    //showSnackBar(this.coordinatorLayout, getString(R.string.error_no_internet));
                    Toast.makeText(Connexion.this, "Vous n'avez pas accès a internet. Veuillez réesayer avec une connnexion active", Toast.LENGTH_SHORT).show();
                } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    //showSnackBar(this.coordinatorLayout, getString(R.string.error_unknown_error));
                    Toast.makeText(Connexion.this, "Authentification annulé. Veuillez réesayé", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    // 3 - Launching Profile Activity
    private void startProfileActivity(){
        Intent intent = new Intent(Connexion.this, MainScreen2.class);
        startActivity(intent);
    }

    // --------------------
    // UI
    // --------------------


    // 2 - Update UI when activity is resuming
    /*private void updateUIWhenResuming(){
        btn_sign_up.setText(this.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
    }*/

}