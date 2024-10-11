package com.example.hofprog.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.hofprog.repository.OldRepository;
import com.example.hofprog.viewmodel.OldViewModel;

public class OldViewModelFactory implements ViewModelProvider.Factory {
    private final OldRepository application;

    public OldViewModelFactory(OldRepository application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(OldViewModel.class)) {
            return (T) new OldViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}