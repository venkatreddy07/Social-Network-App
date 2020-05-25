package com.task.socialapp.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private HomeActivity activity;

    HomeViewModelFactory(HomeActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(activity);
    }
}
