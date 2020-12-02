package com.example.a1024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.a1024.Models.User.DataBaseManager1;
import com.example.a1024.Models.User.UserData;

import java.util.List;

public class User_list extends AppCompatActivity {

    private TextView userView;
    private DataBaseManager1 databaseManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userView = (TextView) findViewById( R.id.userView );
        databaseManager1 = new DataBaseManager1( this );

        //databaseManager.deleteScore(6);

        databaseManager1.insertUser( "shaneza", "shaneza@gmail.com", "1234" );

        //databaseManager1.readUser("slordg", "1234" );

        List<UserData> users = databaseManager1.readUser("slordg", "1234");
        for ( UserData user : users ) {
            userView.append( user.toString() + "\n\n" );
        }

        databaseManager1.close();

    }
}