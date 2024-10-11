package com.example.hofprog.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hofprog.model.god;

import java.util.List;

@Dao
public interface GodDao {

    // Метод для вставки пользователя в базу данных
    @Insert
    long insert(god user);

    // Метод для удаления пользователя по ID
    @Query("DELETE FROM God WHERE ad_id = :userId")
    void deleteById(int userId);

    // Метод для получения всех пользователей из базы данных
    @Query("SELECT * FROM God")
    LiveData<List<god>> getAllUsers();

    // Метод для поиска пользователя по ID
    @Query("SELECT * FROM God WHERE ad_id = :userId")
    LiveData<god> findUserById(int userId);
    @Query("SELECT COUNT(*) FROM God WHERE login = :name")
    int countUsersByName(String name);
}
