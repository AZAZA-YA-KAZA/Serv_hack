package com.example.hofprog.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hofprog.model.whoi;

import java.util.List;

@Dao
public interface WhoiDao {
    @Insert
    long insert(whoi user);

    @Query("DELETE FROM Whoi WHERE id = :id_us")
    void deleteById(int id_us);

    @Query("SELECT * FROM Whoi")
    LiveData<List<whoi>> getAllUsers();

    // Метод для поиска пользователя по ID
    @Query("SELECT * FROM Whoi WHERE id = :userId")
    LiveData<whoi> findUserById(int userId);
    @Query("SELECT prog FROM Whoi WHERE nick = :name")
    int findAll(String name);

}
