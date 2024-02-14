package com.android.cookbook.screens.login.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.cookbook.model.UserModel;
import com.android.cookbook.remote.AuthRepository;

import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private AuthRepository authRepository;

    LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    
    public MutableLiveData<Response<UserModel>> login(String username, String password) {
        return authRepository.signIn(username, password);
    }
}