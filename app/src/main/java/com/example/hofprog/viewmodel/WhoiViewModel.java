package com.example.hofprog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.hofprog.model.whoi;
import com.example.hofprog.repository.WhoiRepository;

import java.util.List;

public class WhoiViewModel extends ViewModel {

    // Репозиторий и LiveData для пользователей
    private WhoiRepository repository;
    private LiveData<List<whoi>> allUsers;

    // Конструктор класса
    public WhoiViewModel(WhoiRepository manageRepository) {
        this.repository = manageRepository;
        this.allUsers = repository.getAllUsers();
    }

    // Метод для добавления пользователя
    public long insert(whoi user) {
        return repository.insert(user);
    }
    public int findAll(String nam) {
        return repository.findAll(nam);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        repository.deleteById(userId);
    }

    // Метод для получения всех пользователей
    public LiveData<List<whoi>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<whoi> findUserById(int userId) {
        return repository.findUserById(userId);
    }
}