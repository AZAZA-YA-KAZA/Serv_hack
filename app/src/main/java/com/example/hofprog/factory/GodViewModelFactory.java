package com.example.hofprog.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.hofprog.repository.GodRepository;
import com.example.hofprog.viewmodel.GodViewModel;

public class GodViewModelFactory implements ViewModelProvider.Factory {
    private final GodRepository application;

    public GodViewModelFactory(GodRepository application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GodViewModel.class)) {
            return (T) new GodViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}