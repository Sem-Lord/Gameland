package com.example.a1024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class Login extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private ImageView ImageView;
    private CallbackManager callbackManager;
    private TextView tv_skip;
    private Button btn_suivant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        ImageView = (ImageView)findViewById(R.id.imageIcon);
        tv_skip = (TextView)findViewById(R.id.tv_skip);
        btn_suivant = (Button)findViewById(R.id.btn_suivant);

        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        checkLoginStatus();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //info.setText("User_fb ID: " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());

            }

            @Override
            public void onCancel() {
                info.setText("Echec de la connexion.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Connexion echouer.");
            }
        });

        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MainScreen.class);
                startActivity(i);

            }
        });

        btn_suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MainScreen.class);
                startActivity(i);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode , resultCode , data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTraker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken==null){
                info.setText("");
                ImageView.setImageResource(R.drawable.image9);

            }else{
                loaduserProfil(currentAccessToken);
                btn_suivant.setVisibility(View.VISIBLE);
            }

        }
    };

    private void loaduserProfil (AccessToken newAccessToken){
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/"+id+ "/picture?type=normal";

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    info.setText(email);
                    Glide.with(Login.this).load(image_url).into(ImageView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name, last_name, email, id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void checkLoginStatus(){
        if(AccessToken.getCurrentAccessToken() != null){
            loaduserProfil(AccessToken.getCurrentAccessToken());
            btn_suivant.setVisibility(View.VISIBLE);
            tv_skip.setVisibility(View.INVISIBLE);
        }else{
            btn_suivant.setVisibility(View.INVISIBLE);
            tv_skip.setVisibility(View.VISIBLE);
        }
    }

}