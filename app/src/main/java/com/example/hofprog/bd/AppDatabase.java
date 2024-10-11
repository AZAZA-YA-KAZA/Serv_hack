package com.example.hofprog.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hofprog.Dao.GodDao;
import com.example.hofprog.Dao.ManageDao;
import com.example.hofprog.Dao.NewTaskDao;
import com.example.hofprog.Dao.OldTaskDao;
import com.example.hofprog.Dao.ProgerDao;
import com.example.hofprog.Dao.WhoiDao;
import com.example.hofprog.model.god;
import com.example.hofprog.model.manage;
import com.example.hofprog.model.newtask;
import com.example.hofprog.model.oldtask;
import com.example.hofprog.model.proger;
import com.example.hofprog.model.whoi;

@Database(entities = {manage.class, proger.class, newtask.class, oldtask.class, whoi.class, god.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ManageDao manageDao();

    public abstract ProgerDao progerDao();

    public abstract WhoiDao whoiDao();

    public abstract NewTaskDao newTaskDao();

    public abstract OldTaskDao oldTaskDao();

    public abstract GodDao godDao();
}
