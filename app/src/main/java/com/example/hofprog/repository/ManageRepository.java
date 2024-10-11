package com.example.hofprog.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.hofprog.Dao.ManageDao;
import com.example.hofprog.bd.AppDatabase;
import com.example.hofprog.model.manage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ManageRepository {
    private final ManageDao userDao;
    // LiveData для всех пользователей
    private final LiveData<List<manage>> allUsers;
    // Пул потоков для выполнения асинхронных операций
    private final ExecutorService executorService;

    // Конструктор класса
    public ManageRepository(Application application) {
        // Инициализация базы данных Room и UserDao
        AppDatabase db = Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, "database-name").build();
        userDao = db.manageDao();
        // Получение LiveData для всех пользователей
        allUsers = userDao.getAllUsers();
        // Инициализация пула потоков
        executorService = Executors.newFixedThreadPool(2);
    }
    public int countUsersByName(String name) {
        return userDao.countUsersByName(name);
    }
    public int findAll(String name, String psw) {
        return userDao.findAll(name, psw);
    }

    // Метод для вставки пользователя в базу данных
    public long insert(manage user) {
        return userDao.insert(user);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        executorService.execute(() -> userDao.deleteById(userId));
    }

    // Метод для получения LiveData всех пользователей
    public LiveData<List<manage>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<manage> findUserById(String userId) {
        return userDao.findUserById(userId);
    }
}
