package com.example.hofprog.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.hofprog.repository.ProgerRepository;
import com.example.hofprog.viewmodel.ProgerViewModel;

public class ProgerrViewModelFactory implements ViewModelProvider.Factory {
    private final ProgerRepository application;

    public ProgerrViewModelFactory(ProgerRepository application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProgerViewModel.class)) {
            return (T) new ProgerViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}