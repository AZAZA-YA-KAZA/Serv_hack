package com.example.hofprog.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.hofprog.repository.ManageRepository;
import com.example.hofprog.viewmodel.ManagerViewModel;

public class ManagerViewModelFactory implements ViewModelProvider.Factory {
    private final ManageRepository application;

    public ManagerViewModelFactory(ManageRepository application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ManagerViewModel.class)) {
            return (T) new ManagerViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
