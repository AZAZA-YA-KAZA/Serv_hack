package com.example.hofprog.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hofprog.model.oldtask;

import java.util.List;

@Dao
public interface OldTaskDao {

    // Метод для вставки пользователя в базу данных
    @Insert
    long insert(oldtask user);

    // Метод для удаления пользователя по ID
    @Query("DELETE FROM OldTask WHERE old_id = :userId")
    void deleteById(int userId);

    // Метод для получения всех пользователей из базы данных
    @Query("SELECT * FROM OldTask")
    LiveData<List<oldtask>> getAllUsers();

    // Метод для поиска пользователя по ID
    @Query("SELECT * FROM OldTask WHERE old_id = :userId")
    LiveData<oldtask> findUserById(int userId);
    @Query("UPDATE Oldtask SET stat=2 WHERE old_id=:id")
    void updateById(int id);
    @Query("SELECT stat FROM OldTask WHERE name = :name")
    int findAll(String name);
}
