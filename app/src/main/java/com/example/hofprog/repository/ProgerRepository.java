package com.example.hofprog.repository;
import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.hofprog.Dao.ProgerDao;
import com.example.hofprog.bd.AppDatabase;
import com.example.hofprog.model.proger;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ProgerRepository {

    private final ProgerDao userDao;
    // LiveData для всех пользователей
    private final LiveData<List<proger>> allUsers;
    // Пул потоков для выполнения асинхронных операций
    private final ExecutorService executorService;

    // Конструктор класса
    public ProgerRepository(Application application) {
        // Инициализация базы данных Room и UserDao
        AppDatabase db = Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, "Programmer").build();
        userDao = db.progerDao();
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
    public long insert(proger user) {
        return userDao.insert(user);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        executorService.execute(() -> userDao.deleteById(userId));
    }

    // Метод для получения LiveData всех пользователей
    public LiveData<List<proger>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<proger> findUserById(String userId) {
        return userDao.findUserById(userId);
    }
}
