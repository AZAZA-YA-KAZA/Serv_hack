package com.example.hofprog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.hofprog.model.newtask;
import com.example.hofprog.repository.NewRepository;

import java.util.List;

public class NewViewModel extends ViewModel {

    // Репозиторий и LiveData для пользователей
    private NewRepository repository;
    private LiveData<List<newtask>> allUsers;

    // Конструктор класса
    public NewViewModel(NewRepository manageRepository) {
        this.repository = manageRepository;
        this.allUsers = repository.getAllUsers();
    }

    // Метод для добавления пользователя
    public long insert(newtask user) {
        return repository.insert(user);
    }
    public void updateById(int userId) {
        repository.updateById(userId);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        repository.deleteById(userId);
    }

    // Метод для получения всех пользователей
    public LiveData<List<newtask>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<newtask> findUserById(int userId) {
        return repository.findUserById(userId);
    }
}
