package com.example.hofprog.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hofprog.model.proger;

import java.util.List;

@Dao

public interface ProgerDao {
    // Метод для вставки пользователя в базу данных
    @Insert
    long insert(proger user);

    // Метод для удаления пользователя по ID
    @Query("DELETE FROM Programmer WHERE prog_id = :userId")
    void deleteById(int userId);

    // Метод для получения всех пользователей из базы данных
    @Query("SELECT * FROM Programmer")
    LiveData<List<proger>> getAllUsers();

    // Метод для поиска пользователя по ID
    @Query("SELECT * FROM Programmer WHERE login = :userId")
    LiveData<proger> findUserById(String userId);
    @Query("SELECT COUNT(*) FROM Programmer WHERE login = :name")
    int countUsersByName(String name);
    @Query("SELECT COUNT(*) FROM Programmer WHERE login = :name and password = :psw")
    int findAll(String name, String psw);
}
