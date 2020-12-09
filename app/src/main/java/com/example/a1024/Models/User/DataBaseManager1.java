package com.example.a1024.Models.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.a1024.Models.Score.ScoreData;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager1 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "g_1024_user.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseManager1(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create table user ("
                + "    id integer primary key autoincrement,"
                + "    pseudo varchar not null,"
                + "    mail varchar not null,"
                + "    password varchar not null,"
                + "    when_ integer not null"
                + ")";
        db.execSQL( strSql );
        Log.i( "DATABASE", "onCreate invoked" );
    }


    public void insertUser( String pseudo, String mail, String password ) {
        pseudo = pseudo.replace( "'", "''" );
        mail = mail.replace( "'", "''" );
        password = password.replace( "'", "''" );
        String strSql = "insert into user (pseudo, mail, password, when_) values ('"
                + pseudo + "', '"+ mail + "', '"+ password + "', " + new java.util.Date().getTime() + ")";
        this.getWritableDatabase().execSQL( strSql );
        Log.i( "DATABASE", "insertScore invoked" );
    }

    public void deleteScore(int id ) {
        //name = name.replace( "'", "''" );
        String strSql = "DELETE FROM user WHERE id = " + id + "";
        this.getWritableDatabase().execSQL( strSql );
        Log.i( "DATABASE", "insertScore invoked" );
    }



    public List<UserData> readUsers() {
        List<UserData> users = new ArrayList<>();

        // 1ère technique : SQL
        String strSql = "select * from user";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );

        // 2nd technique "plus objet"
        /*Cursor cursor = this.getReadableDatabase().query( "T_Scores",
                new String[] { "idScore", "name", "score", "when_" },
                null, null, null, null, "score desc", "10" );*/
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            UserData user = new UserData( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getString( 2 ), cursor.getString( 3 ), new Date( cursor.getLong( 4 ) ) );
            users.add( user );
            cursor.moveToNext();
        }
        cursor.close();

        return users;
    }

    public List<UserData> readUser(String pseudo, String password) {
        List<UserData> users = new ArrayList<>();

        // 1ère technique : SQL
        String strSql = "select * from user where pseudo = '" + pseudo + "' and password = '" + password + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );

        // 2nd technique "plus objet"
        /*Cursor cursor = this.getReadableDatabase().query( "T_Scores",
                new String[] { "idScore", "name", "score", "when_" },
                null, null, null, null, "score desc", "10" );*/
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            UserData user = new UserData( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getString( 2 ), cursor.getString( 3 ), new Date( cursor.getLong( 4 ) ) );
            users.add( user );
            cursor.moveToNext();
        }
        cursor.close();

        return users;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String strSql = "alter table T_Scores add column ...";
        String strSql = "drop table user";
        db.execSQL( strSql );
        this.onCreate( db );
        Log.i( "DATABASE", "onUpgrade invoked" );
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
