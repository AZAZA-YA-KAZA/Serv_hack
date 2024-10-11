package com.example.hofprog.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hofprog.model.newtask;
import com.example.hofprog.model.oldtask;

import java.util.List;

@Dao
public interface NewTaskDao {

    // Метод для вставки пользователя в базу данных
    @Insert
    long insert(newtask user);

    // Метод для удаления пользователя по ID
    @Query("DELETE FROM NewTask WHERE new_id = :userId")
    void deleteById(int userId);

    // Метод для получения всех пользователей из базы данных
    @Query("SELECT * FROM NewTask")
    LiveData<List<newtask>> getAllUsers();

    // Метод для поиска пользователя по ID
    @Query("SELECT * FROM NewTask WHERE new_id = :userId")
    LiveData<newtask> findUserById(int userId);
    @Query("UPDATE NewTask SET stat=2 WHERE new_id=:id")
    void updateById(int id);
}
