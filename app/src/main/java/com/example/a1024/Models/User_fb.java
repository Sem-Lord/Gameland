package com.example.a1024.Models;

import androidx.annotation.Nullable;

public class User_fb {

    private String uid;
    private String username;
    private String user_pseudo;
    @Nullable
    private String urlPicture;

    public User_fb() { }

    public User_fb(String uid, String username, String user_pseudo, String urlPicture) {
        this.uid = uid;
        this.username = username;
        this.urlPicture = urlPicture;
        this.user_pseudo = user_pseudo;
    }

    // --- GETTERS ---
    public String getUid() { return uid; }
    public String getUsername() { return username; }
    public String getUrlPicture() { return urlPicture; }
    public String getUser_pseudo() { return user_pseudo; }


    // --- SETTERS ---
    public void setUsername(String username) { this.username = username; }
    public void setUid(String uid) { this.uid = uid; }
    public void setUrlPicture(String urlPicture) { this.urlPicture = urlPicture; }
    public void setUser_pseudo(String user_pseudo) { this.user_pseudo = user_pseudo; }
}
