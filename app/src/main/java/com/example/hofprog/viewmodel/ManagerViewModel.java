package com.example.hofprog.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.hofprog.model.manage;
import com.example.hofprog.repository.ManageRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ManagerViewModel extends ViewModel {

    // Репозиторий и LiveData для пользователей
    private ManageRepository repository;
    private LiveData<List<manage>> allUsers;

    // Конструктор класса
    public ManagerViewModel(ManageRepository manageRepository) {
        this.repository = manageRepository;
        this.allUsers = repository.getAllUsers();
    }

    // Метод для добавления пользователя
    public long insert(manage user) {
        return repository.insert(user);
    }
    public int countUsersByNamecountUsersByName(String name) {
        return repository.countUsersByName(name);
    }

    public int findAll(String name, String psw) {
        return repository.findAll(name, psw);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        repository.deleteById(userId);
    }

    // Метод для получения всех пользователей
    public LiveData<List<manage>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<manage> findUserById(String userId) {
        return repository.findUserById(userId);
    }
}
