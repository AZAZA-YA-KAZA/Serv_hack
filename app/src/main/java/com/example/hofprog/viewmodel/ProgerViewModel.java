package com.example.hofprog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.hofprog.model.proger;
import com.example.hofprog.repository.ProgerRepository;

import java.util.List;

public class ProgerViewModel extends ViewModel {

    // Репозиторий и LiveData для пользователей
    private ProgerRepository repository;
    private LiveData<List<proger>> allUsers;

    // Конструктор класса
    public ProgerViewModel(ProgerRepository manageRepository) {
        this.repository = manageRepository;
        this.allUsers = repository.getAllUsers();
    }

    // Метод для добавления пользователя
    public long insert(proger user) {
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
    public LiveData<List<proger>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<proger> findUserById(String userId) {
        return repository.findUserById(userId);
    }
}
