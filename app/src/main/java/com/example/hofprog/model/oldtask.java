package com.example.hofprog.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "OldTask")

public class oldtask {
    @PrimaryKey(autoGenerate = true)
    private Integer old_id;
    private Integer stat = 1;
    private String name;//SROKI V IMENI
    private String opis;
    private String for_who;

    public oldtask() {
    }

    public oldtask(String nick, String nam, String opis) {
        this.for_who = nick;
        this.name = nam;
        this.opis = opis;
    }

    public Integer[] toint(String str) {
        String[] strArr = str.split(" ");
        Integer[] intArr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }
    public String getSroki() {
        return name.substring(name.lastIndexOf(' ')+1);
    }


    public Integer getOld_id() {
        return old_id;
    }

    public void setOld_id(Integer old_id) {
        this.old_id = old_id;
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

    public String getFor_who() {
        return for_who;
    }

    public void setFor_who(String for_who) {
        this.for_who = for_who;
    }

    @Override
    public String toString() {
        return "oldtask{" +
                "old_id=" + old_id +
                ", stat=" + stat +
                ", name='" + name + '\'' +
                ", opis='" + opis + '\'' +
                ", for_who='" + for_who + '\'' +
                '}';
    }
}