package com.example.hofprog.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.hofprog.Dao.GodDao;
import com.example.hofprog.bd.AppDatabase;
import com.example.hofprog.model.god;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GodRepository {
    private final GodDao userDao;
    // LiveData для всех пользователей
    private final LiveData<List<god>> allUsers;
    // Пул потоков для выполнения асинхронных операций
    private final ExecutorService executorService;

    // Конструктор класса
    public GodRepository(Application application) {
        // Получение экземпляра базы данных через DatabaseProvider
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.godDao();
        allUsers = userDao.getAllUsers();
        executorService = Executors.newFixedThreadPool(2);
    }

    // Метод для вставки пользователя в базу данных
    public long insert(god user) {
        return userDao.insert(user);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        executorService.execute(() -> userDao.deleteById(userId));
    }
    // Метод для удаления пользователя по ID
    public int countUsersByName(String name) {
        return userDao.countUsersByName(name);
    }

    // Метод для получения LiveData всех пользователей
    public LiveData<List<god>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<god> findUserById(int userId) {
        return userDao.findUserById(userId);
    }
}
