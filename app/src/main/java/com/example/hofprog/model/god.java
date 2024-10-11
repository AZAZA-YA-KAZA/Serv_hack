package com.example.hofprog.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "God")
public class god {

    @PrimaryKey(autoGenerate = true)
    private Integer ad_id;
    private String FIO;
    private String login;
    private String password;

    public god() {
    }
    public god(String FIO, String login, String password) {
        this.FIO = FIO;
        this.login = login;
        this.password = password;
    }

    public Integer getAd_id() {
        return ad_id;
    }

    public void setAd_id(Integer ad_id) {
        this.ad_id = ad_id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
