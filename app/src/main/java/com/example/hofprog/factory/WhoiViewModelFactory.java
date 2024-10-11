package com.example.hofprog.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.hofprog.repository.WhoiRepository;
import com.example.hofprog.viewmodel.WhoiViewModel;

public class WhoiViewModelFactory implements ViewModelProvider.Factory {
    private final WhoiRepository application;

    public WhoiViewModelFactory(WhoiRepository application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WhoiViewModel.class)) {
            return (T) new WhoiViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}