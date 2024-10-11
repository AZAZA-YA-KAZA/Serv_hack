package com.example.hofprog.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.hofprog.Dao.WhoiDao;
import com.example.hofprog.bd.AppDatabase;
import com.example.hofprog.model.whoi;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class WhoiRepository {

    private final WhoiDao userDao;
    // LiveData для всех пользователей
    private final LiveData<List<whoi>> allUsers;
    // Пул потоков для выполнения асинхронных операций
    private final ExecutorService executorService;

    // Конструктор класса
    public WhoiRepository(Application application) {
        // Инициализация базы данных Room и UserDao
        AppDatabase db = Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, "Whoi").build();
        userDao = db.whoiDao();
        // Получение LiveData для всех пользователей
        allUsers = userDao.getAllUsers();
        // Инициализация пула потоков
        executorService = Executors.newFixedThreadPool(2);
    }

    // Метод для вставки пользователя в базу данных
    public long insert(whoi user) {
        return userDao.insert(user);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        executorService.execute(() -> userDao.deleteById(userId));
    }

    // Метод для получения LiveData всех пользователей
    public LiveData<List<whoi>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<whoi> findUserById(int userId) {
        return userDao.findUserById(userId);
    }
    public int findAll(String nam) {
        return userDao.findAll(nam);
    }
}