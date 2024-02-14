package com.android.cookbook.screens.register.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.cookbook.model.UserModel;
import com.android.cookbook.remote.AuthRepository;

import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    private AuthRepository authRepository;


    RegisterViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public MutableLiveData<Response<String>> register(String username, String password) {
        return authRepository.register(username, password);
    }
}