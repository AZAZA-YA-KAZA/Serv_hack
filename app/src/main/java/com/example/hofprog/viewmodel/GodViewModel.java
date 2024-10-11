package com.example.hofprog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.hofprog.model.god;
import com.example.hofprog.repository.GodRepository;

import java.util.List;

public class GodViewModel  extends ViewModel {

    // Репозиторий и LiveData для пользователей
    private GodRepository repository;
    private LiveData<List<god>> allUsers;

    // Конструктор класса
    public GodViewModel(GodRepository manageRepository) {
        this.repository = manageRepository;
        this.allUsers = repository.getAllUsers();
    }

    // Метод для добавления пользователя
    public long insert(god user) {

        return repository.insert(user);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        repository.deleteById(userId);
    }

    // Метод для удаления пользователя по ID
    public int countUsersByNamecountUsersByName(String name) {
        return repository.countUsersByName(name);
    }

    // Метод для получения всех пользователей
    public LiveData<List<god>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<god> findUserById(int userId) {
        return repository.findUserById(userId);
    }
}