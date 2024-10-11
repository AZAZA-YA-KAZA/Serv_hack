package com.example.hofprog.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.hofprog.Dao.OldTaskDao;
import com.example.hofprog.bd.AppDatabase;
import com.example.hofprog.model.oldtask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OldRepository {

    private final OldTaskDao userDao;
    // LiveData для всех пользователей
    private final LiveData<List<oldtask>> allUsers;
    // Пул потоков для выполнения асинхронных операций
    private final ExecutorService executorService;

    // Конструктор класса
    public OldRepository(Application application) {
        // Инициализация базы данных Room и UserDao
        AppDatabase db = Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, "database-name").build();
        userDao = db.oldTaskDao();
        // Получение LiveData для всех пользователей
        allUsers = userDao.getAllUsers();
        // Инициализация пула потоков
        executorService = Executors.newFixedThreadPool(2);
    }

    // Метод для вставки пользователя в базу данных
    public long insert(oldtask user) {
        return userDao.insert(user);
    }
    public int findAll(String user) {
        return userDao.findAll(user);
    }
    public void updateById(int userId) {
        executorService.execute(() -> userDao.updateById(userId));
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        executorService.execute(() -> userDao.deleteById(userId));
    }

    // Метод для получения LiveData всех пользователей
    public LiveData<List<oldtask>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<oldtask> findUserById(int userId) {
        return userDao.findUserById(userId);
    }
}
