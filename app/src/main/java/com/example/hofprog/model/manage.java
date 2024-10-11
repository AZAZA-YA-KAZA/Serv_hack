package com.example.hofprog.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Managers")
public class manage {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String fio;
    private String login;
    private String password;
    private String tel;
    private String god_id = "1";
    public manage() {}

    @Override
    public String toString() {
        return "manage{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", god_id='" + god_id + '\'' +
                '}';
    }

    public manage(String fio, String login, String password, String tel) {
        this.login = login;
        this.fio = fio;
        this.password = password;
        this.tel = tel;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String nick) {
        this.login = nick;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String name) {
        this.fio = name;
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

    public String getGod_id() {
        return god_id;
    }

    public void setGod_id(String progr_id) {
        this.god_id = progr_id;
    }

    public String findbyid(Integer id){ return this.password;}

    public String tostr(Integer[] mas){
        String q = "";
        if (mas == null) return "";
        for (int i: mas){q+= i+"";}
        return q;
    }
    public Integer[] toint(String str) {
        String[] strArr = str.split(" ");
        Integer[] intArr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

}