package com.example.hofprog.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NewTask")
public class newtask {
    @PrimaryKey(autoGenerate = true)
    private Integer new_id;
    private Integer stat = 0;
    private String name;//SROKI V IMENI
    private String opis;
    private String for_who;

    public newtask() {
    }
    public newtask(String nick, String nam, String opis) {
        this.for_who = nick;
        this.name = nam;
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "newtask{" +
                "new_id=" + new_id +
                ", stat=" + stat +
                ", name='" + name + '\'' +
                ", opis='" + opis + '\'' +
                ", for_who='" + for_who + '\'' +
                '}';
    }

    public Integer getNew_id() {
        return new_id;
    }

    public void setNew_id(Integer new_id) {
        this.new_id = new_id;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSroki() {
        return name.substring(name.lastIndexOf(' ')+1);
    }

    public String getFor_who() {
        return for_who;
    }

    public void setFor_who(String for_who) {
        this.for_who = for_who;
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
