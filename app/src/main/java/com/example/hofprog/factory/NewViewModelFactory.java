package com.example.hofprog.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.hofprog.repository.NewRepository;
import com.example.hofprog.viewmodel.NewViewModel;

public class NewViewModelFactory implements ViewModelProvider.Factory {
    private final NewRepository application;

    public NewViewModelFactory(NewRepository application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewViewModel.class)) {
            return (T) new NewViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

