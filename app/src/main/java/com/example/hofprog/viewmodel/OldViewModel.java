package com.example.hofprog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.hofprog.model.oldtask;
import com.example.hofprog.repository.OldRepository;

import java.util.List;

public class OldViewModel extends ViewModel {

    // Репозиторий и LiveData для пользователей
    private OldRepository repository;
    private LiveData<List<oldtask>> allUsers;

    // Конструктор класса
    public OldViewModel(OldRepository manageRepository) {
        this.repository = manageRepository;
        this.allUsers = repository.getAllUsers();
    }

    // Метод для добавления пользователя
    public long insert(oldtask user) {
        return repository.insert(user);
    }
    public int findAll(String user) {
        return repository.findAll(user);
    }
    public void updateById(int userId) {
        repository.updateById(userId);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        repository.deleteById(userId);
    }

    // Метод для получения всех пользователей
    public LiveData<List<oldtask>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<oldtask> findUserById(int userId) {
        return repository.findUserById(userId);
    }
}
