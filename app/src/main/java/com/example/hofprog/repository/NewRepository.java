package com.example.hofprog.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.hofprog.Dao.NewTaskDao;
import com.example.hofprog.bd.AppDatabase;
import com.example.hofprog.model.newtask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewRepository {

    private final NewTaskDao userDao;
    // LiveData для всех пользователей
    private final LiveData<List<newtask>> allUsers;
    // Пул потоков для выполнения асинхронных операций
    private final ExecutorService executorService;

    // Конструктор класса
    public NewRepository(Application application) {
        // Получение экземпляра базы данных через DatabaseProvider
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.newTaskDao();
        allUsers = userDao.getAllUsers();
        executorService = Executors.newFixedThreadPool(2);
    }

    // Метод для вставки пользователя в базу данных
    public long insert(newtask user) {
        return userDao.insert(user);
    }
    public void updateById(int userId) {
        executorService.execute(() -> userDao.updateById(userId));
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        executorService.execute(() -> userDao.deleteById(userId));
    }

    // Метод для получения LiveData всех пользователей
    public LiveData<List<newtask>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<newtask> findUserById(int userId) {
        return userDao.findUserById(userId);
    }
}

