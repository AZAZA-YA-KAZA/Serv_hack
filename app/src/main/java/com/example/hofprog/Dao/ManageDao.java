package com.example.hofprog.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hofprog.model.manage;

import java.util.List;
import androidx.room.Dao;

@Dao

public interface ManageDao {
    // Метод для вставки пользователя в базу данных
    @Insert
    long insert(manage user);

    // Метод для удаления пользователя по ID
    @Query("DELETE FROM Managers WHERE id = :userId")
    void deleteById(int userId);

    // Метод для получения всех пользователей из базы данных
    @Query("SELECT * FROM Managers")
    LiveData<List<manage>> getAllUsers();

    // Метод для поиска пользователя по ID
    @Query("SELECT * FROM Managers WHERE login = :userId")
    LiveData<manage> findUserById(String userId);
    @Query("SELECT COUNT(*) FROM Managers WHERE login = :name")
    int countUsersByName(String name);
    @Query("SELECT COUNT(*) FROM Managers WHERE login = :name and password = :psw")
    int findAll(String name, String psw);
}
