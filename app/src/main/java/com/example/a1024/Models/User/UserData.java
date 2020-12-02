package com.example.a1024.Models.User;

import java.util.Date;

public class UserData {

    private int id;
    private String pseudo;
    private String mail;
    private String password;
    private Date when;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public UserData(int id, String pseudo, String mail, String password, Date when) {
        this.setId( id );
        this.setPseudo( pseudo );
        this.setMail( mail );
        this.setPassword( password );
        this.setWhen( when );
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", when=" + when +
                '}';
    }
}
