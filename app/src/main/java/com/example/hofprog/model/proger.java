package com.example.hofprog.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Programmer")
public class proger {
    @PrimaryKey(autoGenerate = true)
    private Integer prog_id;
    private String login;
    private String fio;
    private String password;
    private String tel;
    private String who_rab;

    public proger() {
    }
    public proger(String fio, String login, String password, String tel, String who_rab) {
        this.login = login;
        this.fio = fio;
        this.password = password;
        this.tel = tel;
        this.who_rab = who_rab;
    }

    @Override
    public String toString() {
        return "proger{" +
                "prog_id=" + prog_id +
                ", login='" + login + '\'' +
                ", fio='" + fio + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", who_rab='" + who_rab + '\'' +
                '}';
    }

    public Integer getProg_id() {
        return prog_id;
    }

    public void setProg_id(Integer prog_id) {
        this.prog_id = prog_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWho_rab() {
        return who_rab;
    }

    public void setWho_rab(String who_rab) {
        this.who_rab = who_rab;
    }
}
