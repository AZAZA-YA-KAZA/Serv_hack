package com.example.hofprog.bd;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
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
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

@Database(entities = {manage.class, proger.class, newtask.class, oldtask.class, whoi.class, god.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ManageDao manageDao();
    public abstract ProgerDao progerDao();
    public abstract WhoiDao whoiDao();
    public abstract NewTaskDao newTaskDao();
    public abstract OldTaskDao oldTaskDao();
    public abstract GodDao godDao();


    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "mydatabase.db")
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            importDataFromAssets(context, db);
                        }
                    })
                    .build();
        }
        return instance;
    }

    private static void importDataFromAssets(Context context, SupportSQLiteDatabase db) {
        AssetManager assetManager = context.getAssets();
        try {
            String[] files = assetManager.list(""); // Получаем список файлов в assets
            for (String file : files) {
                if (file.endsWith(".sql")) { // Проверяем только на SQL файлы
                    InputStream is = assetManager.open(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sqlBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sqlBuilder.append(line).append("n");
                    }
                    reader.close();
                    db.execSQL(sqlBuilder.toString()); // Выполняем SQL запросы
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static AppDatabase getDatabase(Application application) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(application.getApplicationContext(),
                            AppDatabase.class, "database-name").build();
                }
            }
        }
        return instance;
    }
}